package com.example.safedrive.activities

import android.content.Context
import android.content.res.AssetManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.safedrive.R
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.io.IOException
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel


class Prediction : AppCompatActivity() {
    var tflite: Interpreter? = null
    var context: Context? = null

    private val mModelPath = "model_try.tflite"

    // Output you get from your model, this is essentially as we saw in netron
    //    val resultArray = Array(1) { ByteArray(3) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prediction)

        try {
            initInterpreter()
            Log.d("load", "model loaded")
            Toast.makeText(
                this, "Model Loaded !",
                Toast.LENGTH_LONG
            ).show()
        } catch (ex: Exception) {
            ex.printStackTrace()
            //            Toast.makeText(this, "Model Not Loaded !",
//                    Toast.LENGTH_LONG).show();
            Toast.makeText(
                this, "Model Not Loaded !",
                Toast.LENGTH_LONG
            ).show()
        }
        //        Toast.makeText(this,  " " +doInference(),Toast.LENGTH_SHORT).show();

        val value=doInference()
        Toast.makeText(
            this, value.toString(),
            Toast.LENGTH_LONG
        ).show()

    }

    private fun initInterpreter(){
        val options = Interpreter.Options()
        options.setNumThreads(5)
        options.setUseNNAPI(true)
        tflite = Interpreter(loadModelFile(assets, mModelPath), options)


    }



    @Throws(IOException::class)
    private fun loadModelFile(assetManager: AssetManager, modelPath: String): MappedByteBuffer {

        val fileDescriptor = assetManager.openFd(modelPath)
//        val fileDescriptor = context!!.assets.openFd("model.tflite")
//        Toast.makeText(context, " $fileDescriptor", Toast.LENGTH_LONG).show()
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)

    }

    private fun doInference(): Float {

//        String inputString
        val  inputVal = FloatArray(34)
        //        inputVal[0]=Float.valueOf(inputString);
        val data = doubleArrayOf(
            228.0,
            86.0,
            233.0,
            151.0,
            188.0,
            151.0,
            178.0,
            225.0,
            175.0,
            299.0, 275.0, 148.0, 299.0, 230.0, 275.0, 302.0, 195.0, 314.0, 206.0, 419.0, 208.0, 515.0, 257.0, 315.0, 265.0, 422.0, 268.0, 528.0, 220.0, 76.0, 237.0, 76.0, 207.0, 86.0
        )
        for (j in 1..34) {
            inputVal[j - 1] = data[j - 1].toFloat().toDouble().toFloat()
        }

        //3 classes
        val output =
            Array(1) { FloatArray(3) }
        tflite!!.run(inputVal, output)
//        tflite?.runForMultipleInputsOutputs(arrayOf(inputVal), output);
        return output[0][0]
//          return output
//        The run() method takes only one input and returns only one output. So if your model has multiple inputs or multiple outputs, instead use:
//        interpreter.runForMultipleInputsOutputs(inputs, map_of_indices_to_outputs);

    }
}