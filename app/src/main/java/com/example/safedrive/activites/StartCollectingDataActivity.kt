
package com.example.safedrive.lib

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ImageFormat
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.Rect
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.hardware.camera2.CaptureRequest
import android.hardware.camera2.CaptureResult
import android.hardware.camera2.TotalCaptureResult
import android.media.Image
import android.media.ImageReader
import android.media.ImageReader.OnImageAvailableListener
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Process
import android.provider.Settings
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import android.util.Log
import android.util.Size
import android.util.SparseIntArray
import android.view.LayoutInflater
import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.example.safedrive.R
import com.example.safedrive.activites.YuvToRgbConverter
import kotlinx.android.synthetic.main.activity_start_collecting_data.*
import java.security.AccessController.getContext
import java.util.concurrent.Executors
import java.util.concurrent.Semaphore
import java.util.concurrent.TimeUnit
import kotlin.math.abs
import kotlin.math.min
import kotlin.random.Random





class StartCollectingDataActivity :
    AppCompatActivity()  {

    private val permissions = listOf(Manifest.permission.CAMERA)
    private val permissionsRequestCode = Random.nextInt(0, 10000)
    private lateinit var bitmapBuffer: Bitmap
    private val executor = Executors.newSingleThreadExecutor()
    private var imageCapture: ImageCapture? = null

    /** An object for the Posenet library.    */
    private lateinit var posenet: Posenet

    /** Paint class holds the style and color information to draw geometries,text and bitmaps. */
    private var paint = Paint()


    /** Threshold for confidence score. */
    private val minConfidence = 0.5

    /** Radius of circle used to draw keypoints.  */
    private val circleRadius = 8.0f


//    private lateinit var outputDirectory: File
//    private lateinit var cameraExecutor: ExecutorService

    /** List of body joints that should be connected.    */
    private val bodyJoints = listOf(
        Pair(BodyPart.LEFT_WRIST, BodyPart.LEFT_ELBOW),
        Pair(BodyPart.LEFT_ELBOW, BodyPart.LEFT_SHOULDER),
        Pair(BodyPart.LEFT_SHOULDER, BodyPart.RIGHT_SHOULDER),
        Pair(BodyPart.RIGHT_SHOULDER, BodyPart.RIGHT_ELBOW),
        Pair(BodyPart.RIGHT_ELBOW, BodyPart.RIGHT_WRIST),
        Pair(BodyPart.LEFT_SHOULDER, BodyPart.LEFT_HIP),
        Pair(BodyPart.LEFT_HIP, BodyPart.RIGHT_HIP),
        Pair(BodyPart.RIGHT_HIP, BodyPart.RIGHT_SHOULDER),
        Pair(BodyPart.LEFT_HIP, BodyPart.LEFT_KNEE),
        Pair(BodyPart.LEFT_KNEE, BodyPart.LEFT_ANKLE),
        Pair(BodyPart.RIGHT_HIP, BodyPart.RIGHT_KNEE),
        Pair(BodyPart.RIGHT_KNEE, BodyPart.RIGHT_ANKLE)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_collecting_data)


        // Request camera permissions
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }


    }
    /** Declare and bind preview and analysis use cases */
    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener(Runnable {
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(preview.createSurfaceProvider())
                }

            imageCapture = ImageCapture.Builder()
                .build()

            val imageAnalyzer = ImageAnalysis.Builder()
                .build().setAnalyzer(executor, ImageAnalysis.Analyzer { image ->
                    if (!::bitmapBuffer.isInitialized) {
                        // The image rotation and RGB image buffer are initialized only once
                        // the analyzer has started running
//                imageRotationDegrees = image.imageInfo.rotationDegrees
                       val imageBitmap = Bitmap.createBitmap(
                            image.width, image.height, Bitmap.Config.ARGB_8888
                        )
                        // Create rotated version for portrait display
                        val rotateMatrix = Matrix()
                        rotateMatrix.postRotate(90.0f)

                        val rotatedBitmap = Bitmap.createBitmap(
                            imageBitmap, 0, 0, 0, 0,
                            rotateMatrix, true
                        )
                        image.close()

                        processImage(rotatedBitmap)


                    }


                }
                )
//                .also {
//                    it.setAnalyzer(cameraExecutor, LuminosityAnalyzer { luma ->
//                        Log.d(TAG, "Average luminosity: $luma")
//                    })
//                }

            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture)

            } catch(exc: Exception) {
                Log.e("Message", "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(this))
    }



    /** Crop Bitmap to maintain aspect ratio of model input.   */
    private fun cropBitmap(bitmap: Bitmap): Bitmap {
        val bitmapRatio = bitmap.height.toFloat() / bitmap.width
        val modelInputRatio = MODEL_HEIGHT.toFloat() / MODEL_WIDTH
        var croppedBitmap = bitmap

        // Acceptable difference between the modelInputRatio and bitmapRatio to skip cropping.
        val maxDifference = 1e-5

        // Checks if the bitmap has similar aspect ratio as the required model input.
        when {
            Math.abs(modelInputRatio - bitmapRatio) < maxDifference -> return croppedBitmap
            modelInputRatio < bitmapRatio -> {
                // New image is taller so we are height constrained.
                val cropHeight = bitmap.height - (bitmap.width.toFloat() / modelInputRatio)
                croppedBitmap = Bitmap.createBitmap(
                    bitmap,
                    0,
                    (cropHeight / 2).toInt(),
                    bitmap.width,
                    (bitmap.height - cropHeight).toInt()
                )
            }
            else -> {
                val cropWidth = bitmap.width - (bitmap.height.toFloat() * modelInputRatio)
                croppedBitmap = Bitmap.createBitmap(
                    bitmap,
                    (cropWidth / 2).toInt(),
                    0,
                    (bitmap.width - cropWidth).toInt(),
                    bitmap.height
                )
            }
        }
        return croppedBitmap
    }

    /** Set the paint color and size.    */
    private fun setPaint() {
        paint.color = Color.RED
        paint.textSize = 80.0f
        paint.strokeWidth = 8.0f
    }


    /** Process image using Posenet library.   */
    private fun processImage(bitmap: Bitmap) {
        // Crop bitmap.
        val croppedBitmap = cropBitmap(bitmap)

        // Created scaled version of bitmap for model input.
        val scaledBitmap =
            Bitmap.createScaledBitmap(croppedBitmap,
                MODEL_WIDTH,
                MODEL_HEIGHT, true)

        // Perform inference.
        val person = posenet.estimateSinglePose(scaledBitmap)

        val canvas: Canvas =  surfaceView!!.holder.lockCanvas()
        draw(canvas, person, scaledBitmap)

    }

    /** Draw bitmap on Canvas.   */
    private fun draw(canvas: Canvas, person: Person, bitmap: Bitmap) {
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
        // Draw `bitmap` and `person` in square canvas.
        val screenWidth: Int
        val screenHeight: Int
        val left: Int
        val right: Int
        val top: Int
        val bottom: Int
        if (canvas.height > canvas.width) {
            screenWidth = canvas.width
            screenHeight = canvas.width
            left = 0
            top = (canvas.height - canvas.width) / 2
        } else {
            screenWidth = canvas.height
            screenHeight = canvas.height
            left = (canvas.width - canvas.height) / 2
            top = 0
        }
        right = left + screenWidth
        bottom = top + screenHeight

        setPaint()
        canvas.drawBitmap(
            bitmap,
            Rect(0, 0, bitmap.width, bitmap.height),
            Rect(left, top, right, bottom),
            paint
        )

        val widthRatio = screenWidth.toFloat() / MODEL_WIDTH
        val heightRatio = screenHeight.toFloat() / MODEL_HEIGHT

        // Draw key points over the image.
        for (keyPoint in person.keyPoints) {
            if (keyPoint.score > minConfidence) {
                val position = keyPoint.position
                val adjustedX: Float = position.x.toFloat() * widthRatio + left
                val adjustedY: Float = position.y.toFloat() * heightRatio + top

                canvas.drawCircle(adjustedX, adjustedY, circleRadius, paint)
            }
        }

        for (keyPoint in person.keyPoints) {
//            if (keyPoint.score > minConfidence) {

            Log.d("KEYPOINT",
                "" + keyPoint.bodyPart + " " + keyPoint.position.x.toFloat()
                    .toString() + ", " + keyPoint.position.y.toFloat()
                    .toString() + " " + keyPoint.score
            );
//                Log.d("KEYPOINT",
//                    "Body Part : " + keyPoint.bodyPart + ", Keypoint Location : (" + keyPoint.position.x.toFloat()
//                        .toString() + ", " + keyPoint.position.y.toFloat()
//                        .toString() + "), Confidence" + keyPoint.score
//                );
//            }
        }

        for (line in bodyJoints) {
            if (
                (person.keyPoints[line.first.ordinal].score > minConfidence) and
                (person.keyPoints[line.second.ordinal].score > minConfidence)
            ) {
                canvas.drawLine(
                    person.keyPoints[line.first.ordinal].position.x.toFloat() * widthRatio + left,
                    person.keyPoints[line.first.ordinal].position.y.toFloat() * heightRatio + top,
                    person.keyPoints[line.second.ordinal].position.x.toFloat() * widthRatio + left,
                    person.keyPoints[line.second.ordinal].position.y.toFloat() * heightRatio + top,
                    paint
                )
            }
        }


        canvas.drawText(
            "Score: %.2f".format(person.score),
            (15.0f * widthRatio),
            (30.0f * heightRatio + bottom),
            paint
        )
        canvas.drawText(
            "Device: %s".format(posenet.device),
            (15.0f * widthRatio),
            (50.0f * heightRatio + bottom),
            paint
        )
        canvas.drawText(
            "Time: %.2f ms".format(posenet.lastInferenceTimeNanos * 1.0f / 1_000_000),
            (15.0f * widthRatio),
            (70.0f * heightRatio + bottom),
            paint
        )
        // Draw!
        surfaceView!!.holder.unlockCanvasAndPost(canvas)

    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == permissionsRequestCode && hasPermissions(this)) {
            startCamera()
        } else {
            finish() // If we don't have the required permissions, we can't run
        }
    }

    /** Convenience method used to check if all permissions required by this app are granted */
    private fun hasPermissions(context: Context) = permissions.all {
        ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }
}
