// CollectData.java
package com.example.safedrive.nav_components.CollectData;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.CameraCaptureSession.CaptureCallback;
import android.hardware.camera2.CameraDevice.StateCallback;
import android.hardware.camera2.CaptureRequest.Builder;
import android.media.Image;
import android.media.ImageReader;
import android.media.Image.Plane;
import android.media.ImageReader.OnImageAvailableListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.example.safedrive.R.id;
import com.example.safedrive.lib.BodyPart;
import com.example.safedrive.lib.ConfirmationDialog;
import com.example.safedrive.lib.Device;
import com.example.safedrive.lib.ImageUtils;
import com.example.safedrive.lib.KeyPoint;
import com.example.safedrive.lib.Person;
import com.example.safedrive.lib.Posenet;
import com.example.safedrive.lib.Position;
import com.example.safedrive.sqlite.DatabaseHelper;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 1, 16},
   bv = {1, 0, 3},
   k = 1,
   d1 = {"\u0000\u0086\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b7\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b3\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e*\u0004\u001a#¨\u0001\u0018\u0000 ê\u00012\u00020\u0001:\u0004ê\u0001ë\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010²\u0001\u001a\u00020\u00042\u0007\u0010³\u0001\u001a\u00020tH\u0002J\n\u0010´\u0001\u001a\u00030µ\u0001H\u0002J\n\u0010¶\u0001\u001a\u00030µ\u0001H\u0002J\u0014\u0010·\u0001\u001a\u00030¸\u00012\b\u0010¹\u0001\u001a\u00030¸\u0001H\u0002J\u0014\u0010º\u0001\u001a\u00030µ\u00012\b\u0010»\u0001\u001a\u00030¼\u0001H\u0002J(\u0010½\u0001\u001a\u00030µ\u00012\b\u0010¾\u0001\u001a\u00030¿\u00012\b\u0010»\u0001\u001a\u00030¼\u00012\b\u0010¹\u0001\u001a\u00030¸\u0001H\u0002J4\u0010À\u0001\u001a\u00030µ\u00012\u000f\u0010Á\u0001\u001a\n\u0012\u0005\u0012\u00030Â\u00010¯\u00012\u0011\u0010®\u0001\u001a\f\u0012\u0007\u0012\u0005\u0018\u00010°\u00010¯\u0001H\u0002¢\u0006\u0003\u0010Ã\u0001J\u0014\u0010Ä\u0001\u001a\u00030µ\u00012\b\u0010Å\u0001\u001a\u00030Æ\u0001H\u0016J.\u0010Ç\u0001\u001a\u0005\u0018\u00010È\u00012\b\u0010É\u0001\u001a\u00030Ê\u00012\n\u0010Ë\u0001\u001a\u0005\u0018\u00010Ì\u00012\n\u0010Í\u0001\u001a\u0005\u0018\u00010Î\u0001H\u0016J\n\u0010Ï\u0001\u001a\u00030µ\u0001H\u0016J\n\u0010Ð\u0001\u001a\u00030µ\u0001H\u0016J2\u0010Ñ\u0001\u001a\u00030µ\u00012\u0007\u0010Ò\u0001\u001a\u00020\u00062\u000e\u0010Ó\u0001\u001a\t\u0012\u0004\u0012\u00020\u00160¯\u00012\u0007\u0010³\u0001\u001a\u00020tH\u0016¢\u0006\u0003\u0010Ô\u0001J\n\u0010Õ\u0001\u001a\u00030µ\u0001H\u0016J\n\u0010Ö\u0001\u001a\u00030µ\u0001H\u0016J \u0010×\u0001\u001a\u00030µ\u00012\b\u0010Ø\u0001\u001a\u00030È\u00012\n\u0010Í\u0001\u001a\u0005\u0018\u00010Î\u0001H\u0016J\n\u0010Ù\u0001\u001a\u00030µ\u0001H\u0002J\u0014\u0010Ú\u0001\u001a\u00030µ\u00012\b\u0010¹\u0001\u001a\u00030¸\u0001H\u0002J\n\u0010Û\u0001\u001a\u00030µ\u0001H\u0002J\u0013\u0010Ü\u0001\u001a\u00030µ\u00012\u0007\u0010Ý\u0001\u001a\u00020oH\u0002J\n\u0010Þ\u0001\u001a\u00030µ\u0001H\u0002J\n\u0010ß\u0001\u001a\u00030µ\u0001H\u0002J\u001a\u0010à\u0001\u001a\u00030µ\u00012\u0007\u0010á\u0001\u001a\u00020\u00162\u0007\u0010â\u0001\u001a\u00020\u0016J\u0013\u0010ã\u0001\u001a\u00030µ\u00012\u0007\u0010ä\u0001\u001a\u00020\u0016H\u0002J\n\u0010å\u0001\u001a\u00030µ\u0001H\u0002J\u0014\u0010æ\u0001\u001a\u00030µ\u00012\b\u0010»\u0001\u001a\u00030¼\u0001H\u0002J\n\u0010ç\u0001\u001a\u00030µ\u0001H\u0002J\u0014\u0010è\u0001\u001a\u00030µ\u00012\b\u0010»\u0001\u001a\u00030¼\u0001H\u0002J\n\u0010é\u0001\u001a\u00030µ\u0001H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00120\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001bR\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u00020#X\u0082\u000e¢\u0006\u0004\n\u0002\u0010$R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010'\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001a\u0010+\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001a\u00100\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010-\"\u0004\b2\u0010/R\u001a\u00103\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010-\"\u0004\b5\u0010/R\u001a\u00106\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010-\"\u0004\b8\u0010/R\u001a\u00109\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010-\"\u0004\b;\u0010/R\u001a\u0010<\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010-\"\u0004\b>\u0010/R\u001a\u0010?\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010-\"\u0004\bA\u0010/R\u001a\u0010B\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010-\"\u0004\bD\u0010/R\u001a\u0010E\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010-\"\u0004\bG\u0010/R\u001a\u0010H\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010-\"\u0004\bJ\u0010/R\u001a\u0010K\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010-\"\u0004\bM\u0010/R\u001a\u0010N\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010-\"\u0004\bP\u0010/R\u001a\u0010Q\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010-\"\u0004\bS\u0010/R\u001a\u0010T\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010-\"\u0004\bV\u0010/R\u001a\u0010W\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010-\"\u0004\bY\u0010/R\u001a\u0010Z\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010-\"\u0004\b\\\u0010/R\u000e\u0010]\u001a\u00020^X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010_\u001a\u0004\u0018\u00010`X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010a\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u0010-\"\u0004\bc\u0010/R\u001a\u0010d\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010-\"\u0004\bf\u0010/R\u000e\u0010g\u001a\u00020hX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010i\u001a\u00020jX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010k\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010l\u001a\u0004\u0018\u00010mX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010n\u001a\u0004\u0018\u00010oX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010p\u001a\u0004\u0018\u00010qX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010r\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010s\u001a\u00020tX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010u\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bv\u0010-\"\u0004\bw\u0010/R\u001a\u0010x\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\by\u0010-\"\u0004\bz\u0010/R\u001a\u0010{\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b|\u0010-\"\u0004\b}\u0010/R\u001b\u0010~\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000f\n\u0000\u001a\u0004\b\u007f\u0010-\"\u0005\b\u0080\u0001\u0010/R\u001d\u0010\u0081\u0001\u001a\u00020\u001fX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0082\u0001\u0010-\"\u0005\b\u0083\u0001\u0010/R\u001d\u0010\u0084\u0001\u001a\u00020\u001fX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0085\u0001\u0010-\"\u0005\b\u0086\u0001\u0010/R\u001d\u0010\u0087\u0001\u001a\u00020\u001fX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0088\u0001\u0010-\"\u0005\b\u0089\u0001\u0010/R\u001d\u0010\u008a\u0001\u001a\u00020\u001fX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008b\u0001\u0010-\"\u0005\b\u008c\u0001\u0010/R\u001d\u0010\u008d\u0001\u001a\u00020\u001fX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008e\u0001\u0010-\"\u0005\b\u008f\u0001\u0010/R\u001d\u0010\u0090\u0001\u001a\u00020\u001fX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0091\u0001\u0010-\"\u0005\b\u0092\u0001\u0010/R\u001d\u0010\u0093\u0001\u001a\u00020\u001fX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0094\u0001\u0010-\"\u0005\b\u0095\u0001\u0010/R\u001d\u0010\u0096\u0001\u001a\u00020\u001fX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0097\u0001\u0010-\"\u0005\b\u0098\u0001\u0010/R\u001d\u0010\u0099\u0001\u001a\u00020\u001fX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009a\u0001\u0010-\"\u0005\b\u009b\u0001\u0010/R\u001d\u0010\u009c\u0001\u001a\u00020\u001fX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009d\u0001\u0010-\"\u0005\b\u009e\u0001\u0010/R\u001d\u0010\u009f\u0001\u001a\u00020\u001fX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b \u0001\u0010-\"\u0005\b¡\u0001\u0010/R\u001d\u0010¢\u0001\u001a\u00020\u001fX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b£\u0001\u0010-\"\u0005\b¤\u0001\u0010/R\u0014\u0010¥\u0001\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0005\n\u0003\u0010¦\u0001R\u0013\u0010§\u0001\u001a\u00030¨\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010©\u0001R\u0012\u0010ª\u0001\u001a\u0005\u0018\u00010«\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010¬\u0001\u001a\u0005\u0018\u00010\u00ad\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010®\u0001\u001a\f\u0012\u0007\u0012\u0005\u0018\u00010°\u00010¯\u0001X\u0082\u000e¢\u0006\u0005\n\u0003\u0010±\u0001¨\u0006ì\u0001"},
   d2 = {"Lcom/example/safedrive/nav_components/CollectData/CollectData;", "Landroidx/fragment/app/Fragment;", "()V", "DISCARD", "", "PREVIEW_HEIGHT", "", "PREVIEW_WIDTH", "SAVE", "START", "STOP", "backgroundHandler", "Landroid/os/Handler;", "backgroundThread", "Landroid/os/HandlerThread;", "bodyJoints", "", "Lkotlin/Pair;", "Lcom/example/safedrive/lib/BodyPart;", "cameraDevice", "Landroid/hardware/camera2/CameraDevice;", "cameraId", "", "cameraOpenCloseLock", "Ljava/util/concurrent/Semaphore;", "captureCallback", "com/example/safedrive/nav_components/CollectData/CollectData$captureCallback$1", "Lcom/example/safedrive/nav_components/CollectData/CollectData$captureCallback$1;", "captureSession", "Landroid/hardware/camera2/CameraCaptureSession;", "circleRadius", "", "flashSupported", "frameCounter", "imageAvailableListener", "com/example/safedrive/nav_components/CollectData/CollectData$imageAvailableListener$1", "Lcom/example/safedrive/nav_components/CollectData/CollectData$imageAvailableListener$1;", "imageReader", "Landroid/media/ImageReader;", "isInserted", "()Z", "setInserted", "(Z)V", "left_ankle_x", "getLeft_ankle_x", "()F", "setLeft_ankle_x", "(F)V", "left_ankle_y", "getLeft_ankle_y", "setLeft_ankle_y", "left_ear_x", "getLeft_ear_x", "setLeft_ear_x", "left_ear_y", "getLeft_ear_y", "setLeft_ear_y", "left_elbow_x", "getLeft_elbow_x", "setLeft_elbow_x", "left_elbow_y", "getLeft_elbow_y", "setLeft_elbow_y", "left_eye_x", "getLeft_eye_x", "setLeft_eye_x", "left_eye_y", "getLeft_eye_y", "setLeft_eye_y", "left_hip_x", "getLeft_hip_x", "setLeft_hip_x", "left_hip_y", "getLeft_hip_y", "setLeft_hip_y", "left_knee_x", "getLeft_knee_x", "setLeft_knee_x", "left_knee_y", "getLeft_knee_y", "setLeft_knee_y", "left_shoulder_x", "getLeft_shoulder_x", "setLeft_shoulder_x", "left_shoulder_y", "getLeft_shoulder_y", "setLeft_shoulder_y", "left_wrist_x", "getLeft_wrist_x", "setLeft_wrist_x", "left_wrist_y", "getLeft_wrist_y", "setLeft_wrist_y", "minConfidence", "", "myDb", "Lcom/example/safedrive/sqlite/DatabaseHelper;", "noise_x", "getNoise_x", "setNoise_x", "noise_y", "getNoise_y", "setNoise_y", "paint", "Landroid/graphics/Paint;", "posenet", "Lcom/example/safedrive/lib/Posenet;", "previewHeight", "previewRequest", "Landroid/hardware/camera2/CaptureRequest;", "previewRequestBuilder", "Landroid/hardware/camera2/CaptureRequest$Builder;", "previewSize", "Landroid/util/Size;", "previewWidth", "rgbBytes", "", "right_ankle_x", "getRight_ankle_x", "setRight_ankle_x", "right_ankle_y", "getRight_ankle_y", "setRight_ankle_y", "right_ear_x", "getRight_ear_x", "setRight_ear_x", "right_ear_y", "getRight_ear_y", "setRight_ear_y", "right_elbow_x", "getRight_elbow_x", "setRight_elbow_x", "right_elbow_y", "getRight_elbow_y", "setRight_elbow_y", "right_eye_x", "getRight_eye_x", "setRight_eye_x", "right_eye_y", "getRight_eye_y", "setRight_eye_y", "right_hip_x", "getRight_hip_x", "setRight_hip_x", "right_hip_y", "getRight_hip_y", "setRight_hip_y", "right_knee_x", "getRight_knee_x", "setRight_knee_x", "right_knee_y", "getRight_knee_y", "setRight_knee_y", "right_shoulder_x", "getRight_shoulder_x", "setRight_shoulder_x", "right_shoulder_y", "getRight_shoulder_y", "setRight_shoulder_y", "right_wrist_x", "getRight_wrist_x", "setRight_wrist_x", "right_wrist_y", "getRight_wrist_y", "setRight_wrist_y", "sensorOrientation", "Ljava/lang/Integer;", "stateCallback", "com/example/safedrive/nav_components/CollectData/CollectData$stateCallback$1", "Lcom/example/safedrive/nav_components/CollectData/CollectData$stateCallback$1;", "surfaceHolder", "Landroid/view/SurfaceHolder;", "surfaceView", "Landroid/view/SurfaceView;", "yuvBytes", "", "", "[[B", "allPermissionsGranted", "grantResults", "closeCamera", "", "createCameraPreviewSession", "cropBitmap", "Landroid/graphics/Bitmap;", "bitmap", "discardCollectingData", "person", "Lcom/example/safedrive/lib/Person;", "draw", "canvas", "Landroid/graphics/Canvas;", "fillBytes", "planes", "Landroid/media/Image$Plane;", "([Landroid/media/Image$Plane;[[B)V", "onAttach", "context", "Landroid/content/Context;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onRequestPermissionsResult", "requestCode", "permissions", "(I[Ljava/lang/String;[I)V", "onResume", "onStart", "onViewCreated", "view", "openCamera", "processImage", "requestCameraPermission", "setAutoFlash", "requestBuilder", "setPaint", "setUpCameraOutputs", "showMessage", "title", "message", "showToast", "text", "startBackgroundThread", "startcollectingData", "stopBackgroundThread", "stopCollectingData", "viewAllCollectedData", "Companion", "ErrorDialog", "app"}
)
public final class CollectData extends Fragment {
   private final List bodyJoints;
   private DatabaseHelper myDb;
   private final double minConfidence;
   private final float circleRadius;
   private Paint paint;
   private final int PREVIEW_WIDTH;
   private final int PREVIEW_HEIGHT;
   private Posenet posenet;
   private String cameraId;
   private SurfaceView surfaceView;
   private CameraCaptureSession captureSession;
   private CameraDevice cameraDevice;
   private Size previewSize;
   private int previewWidth;
   private int previewHeight;
   private int frameCounter;
   private int[] rgbBytes;
   private byte[][] yuvBytes;
   private HandlerThread backgroundThread;
   private Handler backgroundHandler;
   private ImageReader imageReader;
   private Builder previewRequestBuilder;
   private CaptureRequest previewRequest;
   private final Semaphore cameraOpenCloseLock;
   private boolean flashSupported;
   private Integer sensorOrientation;
   private SurfaceHolder surfaceHolder;
   private float noise_x;
   private float noise_y;
   private float left_eye_x;
   private float left_eye_y;
   private float right_eye_x;
   private float right_eye_y;
   private float left_ear_x;
   private float left_ear_y;
   private float right_ear_x;
   private float right_ear_y;
   private float left_shoulder_x;
   private float left_shoulder_y;
   private float right_shoulder_x;
   private float right_shoulder_y;
   private float left_elbow_x;
   private float left_elbow_y;
   private float right_elbow_x;
   private float right_elbow_y;
   private float left_wrist_x;
   private float left_wrist_y;
   private float right_wrist_x;
   private float right_wrist_y;
   private float left_hip_x;
   private float left_hip_y;
   private float right_hip_x;
   private float right_hip_y;
   private float left_knee_x;
   private float left_knee_y;
   private float right_knee_x;
   private float right_knee_y;
   private float left_ankle_x;
   private float left_ankle_y;
   private float right_ankle_x;
   private float right_ankle_y;
   private boolean START;
   private boolean STOP;
   private boolean SAVE;
   private boolean DISCARD;
   private final <undefinedtype> stateCallback;
   private final <undefinedtype> captureCallback;
   private boolean isInserted;
   private <undefinedtype> imageAvailableListener;
   private static final SparseIntArray ORIENTATIONS = new SparseIntArray();
   private static final String FRAGMENT_DIALOG = "dialog";
   private static final String TAG = "PosenetActivity";
   public static final CollectData.Companion Companion = new CollectData.Companion((DefaultConstructorMarker)null);
   private HashMap _$_findViewCache;

   public final float getNoise_x() {
      return this.noise_x;
   }

   public final void setNoise_x(float var1) {
      this.noise_x = var1;
   }

   public final float getNoise_y() {
      return this.noise_y;
   }

   public final void setNoise_y(float var1) {
      this.noise_y = var1;
   }

   public final float getLeft_eye_x() {
      return this.left_eye_x;
   }

   public final void setLeft_eye_x(float var1) {
      this.left_eye_x = var1;
   }

   public final float getLeft_eye_y() {
      return this.left_eye_y;
   }

   public final void setLeft_eye_y(float var1) {
      this.left_eye_y = var1;
   }

   public final float getRight_eye_x() {
      return this.right_eye_x;
   }

   public final void setRight_eye_x(float var1) {
      this.right_eye_x = var1;
   }

   public final float getRight_eye_y() {
      return this.right_eye_y;
   }

   public final void setRight_eye_y(float var1) {
      this.right_eye_y = var1;
   }

   public final float getLeft_ear_x() {
      return this.left_ear_x;
   }

   public final void setLeft_ear_x(float var1) {
      this.left_ear_x = var1;
   }

   public final float getLeft_ear_y() {
      return this.left_ear_y;
   }

   public final void setLeft_ear_y(float var1) {
      this.left_ear_y = var1;
   }

   public final float getRight_ear_x() {
      return this.right_ear_x;
   }

   public final void setRight_ear_x(float var1) {
      this.right_ear_x = var1;
   }

   public final float getRight_ear_y() {
      return this.right_ear_y;
   }

   public final void setRight_ear_y(float var1) {
      this.right_ear_y = var1;
   }

   public final float getLeft_shoulder_x() {
      return this.left_shoulder_x;
   }

   public final void setLeft_shoulder_x(float var1) {
      this.left_shoulder_x = var1;
   }

   public final float getLeft_shoulder_y() {
      return this.left_shoulder_y;
   }

   public final void setLeft_shoulder_y(float var1) {
      this.left_shoulder_y = var1;
   }

   public final float getRight_shoulder_x() {
      return this.right_shoulder_x;
   }

   public final void setRight_shoulder_x(float var1) {
      this.right_shoulder_x = var1;
   }

   public final float getRight_shoulder_y() {
      return this.right_shoulder_y;
   }

   public final void setRight_shoulder_y(float var1) {
      this.right_shoulder_y = var1;
   }

   public final float getLeft_elbow_x() {
      return this.left_elbow_x;
   }

   public final void setLeft_elbow_x(float var1) {
      this.left_elbow_x = var1;
   }

   public final float getLeft_elbow_y() {
      return this.left_elbow_y;
   }

   public final void setLeft_elbow_y(float var1) {
      this.left_elbow_y = var1;
   }

   public final float getRight_elbow_x() {
      return this.right_elbow_x;
   }

   public final void setRight_elbow_x(float var1) {
      this.right_elbow_x = var1;
   }

   public final float getRight_elbow_y() {
      return this.right_elbow_y;
   }

   public final void setRight_elbow_y(float var1) {
      this.right_elbow_y = var1;
   }

   public final float getLeft_wrist_x() {
      return this.left_wrist_x;
   }

   public final void setLeft_wrist_x(float var1) {
      this.left_wrist_x = var1;
   }

   public final float getLeft_wrist_y() {
      return this.left_wrist_y;
   }

   public final void setLeft_wrist_y(float var1) {
      this.left_wrist_y = var1;
   }

   public final float getRight_wrist_x() {
      return this.right_wrist_x;
   }

   public final void setRight_wrist_x(float var1) {
      this.right_wrist_x = var1;
   }

   public final float getRight_wrist_y() {
      return this.right_wrist_y;
   }

   public final void setRight_wrist_y(float var1) {
      this.right_wrist_y = var1;
   }

   public final float getLeft_hip_x() {
      return this.left_hip_x;
   }

   public final void setLeft_hip_x(float var1) {
      this.left_hip_x = var1;
   }

   public final float getLeft_hip_y() {
      return this.left_hip_y;
   }

   public final void setLeft_hip_y(float var1) {
      this.left_hip_y = var1;
   }

   public final float getRight_hip_x() {
      return this.right_hip_x;
   }

   public final void setRight_hip_x(float var1) {
      this.right_hip_x = var1;
   }

   public final float getRight_hip_y() {
      return this.right_hip_y;
   }

   public final void setRight_hip_y(float var1) {
      this.right_hip_y = var1;
   }

   public final float getLeft_knee_x() {
      return this.left_knee_x;
   }

   public final void setLeft_knee_x(float var1) {
      this.left_knee_x = var1;
   }

   public final float getLeft_knee_y() {
      return this.left_knee_y;
   }

   public final void setLeft_knee_y(float var1) {
      this.left_knee_y = var1;
   }

   public final float getRight_knee_x() {
      return this.right_knee_x;
   }

   public final void setRight_knee_x(float var1) {
      this.right_knee_x = var1;
   }

   public final float getRight_knee_y() {
      return this.right_knee_y;
   }

   public final void setRight_knee_y(float var1) {
      this.right_knee_y = var1;
   }

   public final float getLeft_ankle_x() {
      return this.left_ankle_x;
   }

   public final void setLeft_ankle_x(float var1) {
      this.left_ankle_x = var1;
   }

   public final float getLeft_ankle_y() {
      return this.left_ankle_y;
   }

   public final void setLeft_ankle_y(float var1) {
      this.left_ankle_y = var1;
   }

   public final float getRight_ankle_x() {
      return this.right_ankle_x;
   }

   public final void setRight_ankle_x(float var1) {
      this.right_ankle_x = var1;
   }

   public final float getRight_ankle_y() {
      return this.right_ankle_y;
   }

   public final void setRight_ankle_y(float var1) {
      this.right_ankle_y = var1;
   }

   @Nullable
   public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      Intrinsics.checkParameterIsNotNull(inflater, "inflater");
      this.myDb = new DatabaseHelper(this.getContext());
      View v = inflater.inflate(1300072, container, false);
      String[] values = new String[]{"safe_drive", "texting", "reaching_behind", "drinking", "operating_the_music", "talking_ont_the_phone"};
      View var10000 = v.findViewById(1000443);
      if (var10000 == null) {
         throw new TypeCastException("null cannot be cast to non-null type android.widget.Spinner");
      } else {
         Spinner spinner = (Spinner)var10000;
         ArrayAdapter var8 = new ArrayAdapter;
         FragmentActivity var10002 = this.getActivity();
         if (var10002 == null) {
            Intrinsics.throwNpe();
         }

         var8.<init>((Context)var10002, 17367048, values);
         ArrayAdapter adapter = var8;
         adapter.setDropDownViewResource(17367050);
         spinner.setAdapter((SpinnerAdapter)adapter);
         spinner.setOnItemSelectedListener((OnItemSelectedListener)(new OnItemSelectedListener() {
            public void onItemSelected(@NotNull AdapterView parent, @Nullable View view, int position, long l) {
               Intrinsics.checkParameterIsNotNull(parent, "parent");
               TextView var10000;
               switch(position) {
               case 0:
                  var10000 = (TextView)CollectData.this._$_findCachedViewById(id.textView);
                  Intrinsics.checkExpressionValueIsNotNull(var10000, "textView");
                  var10000.setText((CharSequence)"safe_drive");
                  break;
               case 1:
                  var10000 = (TextView)CollectData.this._$_findCachedViewById(id.textView);
                  Intrinsics.checkExpressionValueIsNotNull(var10000, "textView");
                  var10000.setText((CharSequence)"texting");
                  break;
               case 2:
                  var10000 = (TextView)CollectData.this._$_findCachedViewById(id.textView);
                  Intrinsics.checkExpressionValueIsNotNull(var10000, "textView");
                  var10000.setText((CharSequence)"reaching_behind");
                  break;
               case 3:
                  var10000 = (TextView)CollectData.this._$_findCachedViewById(id.textView);
                  Intrinsics.checkExpressionValueIsNotNull(var10000, "textView");
                  var10000.setText((CharSequence)"drinking");
                  break;
               case 4:
                  var10000 = (TextView)CollectData.this._$_findCachedViewById(id.textView);
                  Intrinsics.checkExpressionValueIsNotNull(var10000, "textView");
                  var10000.setText((CharSequence)"operating_the_music");
                  break;
               case 5:
                  var10000 = (TextView)CollectData.this._$_findCachedViewById(id.textView);
                  Intrinsics.checkExpressionValueIsNotNull(var10000, "textView");
                  var10000.setText((CharSequence)"talking_ont_the_phone");
               }

            }

            public void onNothingSelected(@Nullable AdapterView adapterView) {
            }
         }));
         return v;
      }
   }

   public void onAttach(@NotNull Context context) {
      Intrinsics.checkParameterIsNotNull(context, "context");
      super.onAttach(context);
      this.myDb = new DatabaseHelper((Context)this.getActivity());
   }

   public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
      Intrinsics.checkParameterIsNotNull(view, "view");
      super.onViewCreated(view, savedInstanceState);
      this.surfaceView = (SurfaceView)view.findViewById(1000101);
      SurfaceView var10001 = this.surfaceView;
      if (var10001 == null) {
         Intrinsics.throwNpe();
      }

      this.surfaceHolder = var10001.getHolder();
      ((Button)this._$_findCachedViewById(id.start)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            CollectData.this.STOP = false;
            CollectData.this.SAVE = false;
            CollectData.this.START = true;
            CollectData.this.showToast("start button");
            Log.d("start", "start");
         }
      }));
      ((Button)this._$_findCachedViewById(id.stop)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            CollectData.this.STOP = true;
            CollectData.this.START = false;
            CollectData.this.SAVE = false;
            CollectData.this.showToast("stop button");
            Log.d("stop", "stop");
         }
      }));
   }

   public final boolean isInserted() {
      return this.isInserted;
   }

   public final void setInserted(boolean var1) {
      this.isInserted = var1;
   }

   private final void startcollectingData(Person person) {
      if (!this.STOP && !this.SAVE && this.START) {
         Iterator var3 = person.getKeyPoints().iterator();

         while(var3.hasNext()) {
            KeyPoint keyPoint = (KeyPoint)var3.next();
            if ((double)keyPoint.getScore() > this.minConfidence) {
               if (StringsKt.contains$default((CharSequence)keyPoint.getBodyPart().toString(), (CharSequence)"NOSE", false, 2, (Object)null)) {
                  this.noise_x = (float)keyPoint.getPosition().getX();
                  this.noise_y = (float)keyPoint.getPosition().getY();
               }

               if (StringsKt.contains$default((CharSequence)keyPoint.getBodyPart().toString(), (CharSequence)"LEFT_EYE", false, 2, (Object)null)) {
                  this.left_eye_x = (float)keyPoint.getPosition().getX();
                  Log.d("lll", String.valueOf(this.left_eye_x));
                  this.left_eye_y = (float)keyPoint.getPosition().getY();
               }

               if (StringsKt.contains$default((CharSequence)keyPoint.getBodyPart().toString(), (CharSequence)"RIGHT_EYE", false, 2, (Object)null)) {
                  this.right_eye_x = (float)keyPoint.getPosition().getX();
                  this.right_eye_y = (float)keyPoint.getPosition().getY();
               }

               if (StringsKt.contains$default((CharSequence)keyPoint.getBodyPart().toString(), (CharSequence)"LEFT_EAR", false, 2, (Object)null)) {
                  this.left_ear_x = (float)keyPoint.getPosition().getX();
                  this.left_ear_y = (float)keyPoint.getPosition().getY();
               }

               if (StringsKt.contains$default((CharSequence)keyPoint.getBodyPart().toString(), (CharSequence)"RIGHT_EAR", false, 2, (Object)null)) {
                  this.right_ear_x = (float)keyPoint.getPosition().getX();
                  this.right_ear_y = (float)keyPoint.getPosition().getY();
               }

               if (StringsKt.contains$default((CharSequence)keyPoint.getBodyPart().toString(), (CharSequence)"LEFT_SHOULDER", false, 2, (Object)null)) {
                  this.left_shoulder_x = (float)keyPoint.getPosition().getX();
                  this.right_shoulder_y = (float)keyPoint.getPosition().getY();
               }

               if (StringsKt.contains$default((CharSequence)keyPoint.getBodyPart().toString(), (CharSequence)"RIGHT_SHOULDER", false, 2, (Object)null)) {
                  this.right_shoulder_x = (float)keyPoint.getPosition().getX();
                  this.right_shoulder_y = (float)keyPoint.getPosition().getY();
               }

               if (StringsKt.contains$default((CharSequence)keyPoint.getBodyPart().toString(), (CharSequence)"LEFT_ELBOW", false, 2, (Object)null)) {
                  this.left_elbow_x = (float)keyPoint.getPosition().getX();
                  this.left_elbow_y = (float)keyPoint.getPosition().getY();
               }

               if (StringsKt.contains$default((CharSequence)keyPoint.getBodyPart().toString(), (CharSequence)"RIGHT_ELBOW", false, 2, (Object)null)) {
                  this.right_elbow_x = (float)keyPoint.getPosition().getX();
                  this.right_elbow_y = (float)keyPoint.getPosition().getY();
               }

               if (StringsKt.contains$default((CharSequence)keyPoint.getBodyPart().toString(), (CharSequence)"LEFT_WRIST", false, 2, (Object)null)) {
                  this.left_wrist_x = (float)keyPoint.getPosition().getX();
                  this.left_wrist_y = (float)keyPoint.getPosition().getY();
               }

               if (StringsKt.contains$default((CharSequence)keyPoint.getBodyPart().toString(), (CharSequence)"RIGHT_WRIST", false, 2, (Object)null)) {
                  this.right_wrist_x = (float)keyPoint.getPosition().getX();
                  this.right_wrist_y = (float)keyPoint.getPosition().getY();
               }

               if (StringsKt.contains$default((CharSequence)keyPoint.getBodyPart().toString(), (CharSequence)"LEFT_HIP", false, 2, (Object)null)) {
                  this.left_hip_x = (float)keyPoint.getPosition().getX();
                  this.left_hip_y = (float)keyPoint.getPosition().getY();
               }

               if (StringsKt.contains$default((CharSequence)keyPoint.getBodyPart().toString(), (CharSequence)"RIGHT_HIP", false, 2, (Object)null)) {
                  this.right_hip_x = (float)keyPoint.getPosition().getX();
                  this.right_hip_y = (float)keyPoint.getPosition().getY();
               }

               if (StringsKt.contains$default((CharSequence)keyPoint.getBodyPart().toString(), (CharSequence)"LEFT_KNEE", false, 2, (Object)null)) {
                  this.left_knee_x = (float)keyPoint.getPosition().getX();
                  this.left_knee_y = (float)keyPoint.getPosition().getY();
               }

               if (StringsKt.contains$default((CharSequence)keyPoint.getBodyPart().toString(), (CharSequence)"RIGHT_KNEE", false, 2, (Object)null)) {
                  this.right_knee_x = (float)keyPoint.getPosition().getX();
                  this.right_knee_y = (float)keyPoint.getPosition().getY();
               }

               if (StringsKt.contains$default((CharSequence)keyPoint.getBodyPart().toString(), (CharSequence)"LEFT_ANKLE", false, 2, (Object)null)) {
                  this.left_ankle_x = (float)keyPoint.getPosition().getX();
                  this.left_ankle_y = (float)keyPoint.getPosition().getY();
               }

               if (StringsKt.contains$default((CharSequence)keyPoint.getBodyPart().toString(), (CharSequence)"RIGHT_ANKLE", false, 2, (Object)null)) {
                  this.right_ankle_x = (float)keyPoint.getPosition().getX();
                  this.right_ankle_y = (float)keyPoint.getPosition().getY();
               }

               DatabaseHelper var10001 = this.myDb;
               Boolean var4;
               if (var10001 != null) {
                  TextView var10002 = (TextView)this._$_findCachedViewById(id.textView);
                  Intrinsics.checkExpressionValueIsNotNull(var10002, "textView");
                  var4 = var10001.insertData(var10002.getText().toString(), this.noise_x, this.noise_y, this.left_eye_x, this.left_eye_y, this.right_eye_x, this.right_eye_y, this.left_ear_x, this.left_ear_y, this.right_ear_x, this.right_ear_y, this.left_shoulder_x, this.left_shoulder_y, this.right_shoulder_x, this.right_shoulder_y, this.left_elbow_x, this.left_elbow_y, this.right_elbow_x, this.right_elbow_y, this.left_wrist_x, this.left_wrist_y, this.right_wrist_x, this.left_wrist_y, this.left_hip_x, this.left_hip_y, this.right_hip_x, this.right_hip_y, this.left_knee_x, this.left_knee_y, this.right_knee_x, this.right_knee_y, this.left_ankle_x, this.left_ankle_y, this.right_ankle_x, this.right_ankle_y, keyPoint.getScore());
               } else {
                  var4 = null;
               }

               if (var4 == null) {
                  Intrinsics.throwNpe();
               }

               this.isInserted = var4;
               if (this.isInserted) {
                  Log.d("save", "data_save");
               } else {
                  Log.d("save", "data not saved");
               }
            }
         }
      }

   }

   private final void stopCollectingData(Person person) {
      if (this.STOP && !this.SAVE && !this.START) {
         Log.d("keypointstop", person.getKeyPoints().toString());
      }

   }

   private final void viewAllCollectedData() {
      if (!this.STOP && this.SAVE && !this.START) {
         DatabaseHelper var10000 = this.myDb;
         Cursor res = var10000 != null ? var10000.getAllData() : null;
         if (res != null) {
            if (res.getCount() == 0) {
               this.showMessage("Error", "Nothing Found");
               return;
            }

            StringBuffer buffer = new StringBuffer();

            while(res.moveToNext()) {
               buffer.append("Id : " + res.getString(0) + "\n");
               buffer.append("NOISE_X : " + res.getString(1) + "\n");
               buffer.append("NOSE_Y : " + res.getString(2) + "\n");
               String var10002 = buffer.toString();
               Intrinsics.checkExpressionValueIsNotNull(var10002, "buffer.toString()");
               this.showMessage("Data", var10002);
            }
         }
      }

   }

   public final void showMessage(@NotNull String title, @NotNull String message) {
      Intrinsics.checkParameterIsNotNull(title, "title");
      Intrinsics.checkParameterIsNotNull(message, "message");
      android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder((Context)this.getActivity());
      builder.setCancelable(true);
      builder.setTitle((CharSequence)title);
      builder.setMessage((CharSequence)message);
   }

   private final void discardCollectingData(Person person) {
      if (!this.STOP && !this.SAVE && this.DISCARD) {
         DatabaseHelper var10000 = this.myDb;
         if (var10000 != null) {
            var10000.deleteAll();
         }

         Log.d("keypointsdiscard", person.getKeyPoints().toString());
      }

   }

   public void onResume() {
      super.onResume();
      this.startBackgroundThread();
   }

   public void onStart() {
      super.onStart();
      this.openCamera();
      Posenet var10001 = new Posenet;
      Context var10003 = this.getContext();
      if (var10003 == null) {
         Intrinsics.throwNpe();
      }

      Intrinsics.checkExpressionValueIsNotNull(var10003, "this.context!!");
      var10001.<init>(var10003, (String)null, (Device)null, 6, (DefaultConstructorMarker)null);
      this.posenet = var10001;
   }

   public void onPause() {
      this.closeCamera();
      this.stopBackgroundThread();
      super.onPause();
   }

   public void onDestroy() {
      super.onDestroy();
   }

   private final void requestCameraPermission() {
      if (this.shouldShowRequestPermissionRationale("android.permission.CAMERA")) {
         (new ConfirmationDialog()).show(this.getChildFragmentManager(), FRAGMENT_DIALOG);
      } else {
         this.requestPermissions(new String[]{"android.permission.CAMERA"}, 1);
      }

   }

   public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
      Intrinsics.checkParameterIsNotNull(permissions, "permissions");
      Intrinsics.checkParameterIsNotNull(grantResults, "grantResults");
      if (requestCode == 1) {
         if (this.allPermissionsGranted(grantResults)) {
            CollectData.ErrorDialog.Companion var10000 = CollectData.ErrorDialog.Companion;
            String var10001 = this.getString(1900076);
            Intrinsics.checkExpressionValueIsNotNull(var10001, "getString(R.string.tfe_pn_request_permission)");
            var10000.newInstance(var10001).show(this.getChildFragmentManager(), FRAGMENT_DIALOG);
         }
      } else {
         super.onRequestPermissionsResult(requestCode, permissions, grantResults);
      }

   }

   private final boolean allPermissionsGranted(int[] grantResults) {
      int $i$f$all = false;
      int[] var4 = grantResults;
      int var5 = grantResults.length;
      int var6 = 0;

      boolean var10000;
      while(true) {
         if (var6 >= var5) {
            var10000 = true;
            break;
         }

         int element$iv = var4[var6];
         int var9 = false;
         if (element$iv != 0) {
            var10000 = false;
            break;
         }

         ++var6;
      }

      return var10000;
   }

   private final void setUpCameraOutputs() {
      FragmentActivity activity = this.getActivity();
      if (activity == null) {
         Intrinsics.throwNpe();
      }

      Object var10000 = activity.getSystemService("camera");
      if (var10000 == null) {
         throw new TypeCastException("null cannot be cast to non-null type android.hardware.camera2.CameraManager");
      } else {
         CameraManager manager = (CameraManager)var10000;

         try {
            String[] var5 = manager.getCameraIdList();
            int var6 = var5.length;
            int var4 = 0;

            String cameraId;
            CameraCharacteristics characteristics;
            while(true) {
               if (var4 >= var6) {
                  return;
               }

               cameraId = var5[var4];
               CameraCharacteristics var13 = manager.getCameraCharacteristics(cameraId);
               Intrinsics.checkExpressionValueIsNotNull(var13, "manager.getCameraCharacteristics(cameraId)");
               characteristics = var13;
               Integer cameraDirection = (Integer)characteristics.get(CameraCharacteristics.LENS_FACING);
               if (cameraDirection == null) {
                  break;
               }

               boolean var9 = false;
               if (cameraDirection != 0) {
                  break;
               }

               ++var4;
            }

            this.previewSize = new Size(this.PREVIEW_WIDTH, this.PREVIEW_HEIGHT);
            this.imageReader = ImageReader.newInstance(this.PREVIEW_WIDTH, this.PREVIEW_HEIGHT, 35, 2);
            Object var14 = characteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
            if (var14 == null) {
               Intrinsics.throwNpe();
            }

            this.sensorOrientation = (Integer)var14;
            Size var15 = this.previewSize;
            if (var15 == null) {
               Intrinsics.throwNpe();
            }

            this.previewHeight = var15.getHeight();
            var15 = this.previewSize;
            if (var15 == null) {
               Intrinsics.throwNpe();
            }

            this.previewWidth = var15.getWidth();
            this.rgbBytes = new int[this.previewWidth * this.previewHeight];
            this.flashSupported = Intrinsics.areEqual((Boolean)characteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE), true);
            this.cameraId = cameraId;
            return;
         } catch (CameraAccessException var10) {
            Log.e("PosenetActivity", var10.toString());
         } catch (NullPointerException var11) {
            CollectData.ErrorDialog.Companion var12 = CollectData.ErrorDialog.Companion;
            String var10001 = this.getString(1900038);
            Intrinsics.checkExpressionValueIsNotNull(var10001, "getString(R.string.tfe_pn_camera_error)");
            var12.newInstance(var10001).show(this.getChildFragmentManager(), FRAGMENT_DIALOG);
         }

      }
   }

   private final void openCamera() {
      Context var10000 = this.getContext();
      if (var10000 == null) {
         Intrinsics.throwNpe();
      }

      int permissionCamera = var10000.checkPermission("android.permission.CAMERA", Process.myPid(), Process.myUid());
      if (permissionCamera != 0) {
         this.requestCameraPermission();
      }

      this.setUpCameraOutputs();
      FragmentActivity var6 = this.getActivity();
      if (var6 == null) {
         Intrinsics.throwNpe();
      }

      Object var7 = var6.getSystemService("camera");
      if (var7 == null) {
         throw new TypeCastException("null cannot be cast to non-null type android.hardware.camera2.CameraManager");
      } else {
         CameraManager manager = (CameraManager)var7;

         try {
            if (!this.cameraOpenCloseLock.tryAcquire(2500L, TimeUnit.MILLISECONDS)) {
               throw (Throwable)(new RuntimeException("Time out waiting to lock camera opening."));
            }

            String var10001 = this.cameraId;
            if (var10001 == null) {
               Intrinsics.throwNpe();
            }

            manager.openCamera(var10001, (StateCallback)this.stateCallback, this.backgroundHandler);
         } catch (CameraAccessException var4) {
            Log.e("PosenetActivity", var4.toString());
         } catch (InterruptedException var5) {
            throw (Throwable)(new RuntimeException("Interrupted while trying to lock camera opening.", (Throwable)var5));
         }

      }
   }

   private final void closeCamera() {
      if (this.captureSession != null) {
         try {
            this.cameraOpenCloseLock.acquire();
            CameraCaptureSession var10000 = this.captureSession;
            if (var10000 == null) {
               Intrinsics.throwNpe();
            }

            var10000.close();
            this.captureSession = (CameraCaptureSession)null;
            CameraDevice var6 = this.cameraDevice;
            if (var6 == null) {
               Intrinsics.throwNpe();
            }

            var6.close();
            this.cameraDevice = (CameraDevice)null;
            ImageReader var7 = this.imageReader;
            if (var7 == null) {
               Intrinsics.throwNpe();
            }

            var7.close();
            this.imageReader = (ImageReader)null;
         } catch (InterruptedException var4) {
            throw (Throwable)(new RuntimeException("Interrupted while trying to lock camera closing.", (Throwable)var4));
         } finally {
            this.cameraOpenCloseLock.release();
         }

      }
   }

   private final void startBackgroundThread() {
      HandlerThread var1 = new HandlerThread("imageAvailableListener");
      boolean var2 = false;
      boolean var3 = false;
      int var5 = false;
      var1.start();
      this.backgroundThread = var1;
      Handler var10001 = new Handler;
      HandlerThread var10003 = this.backgroundThread;
      if (var10003 == null) {
         Intrinsics.throwNpe();
      }

      var10001.<init>(var10003.getLooper());
      this.backgroundHandler = var10001;
   }

   private final void stopBackgroundThread() {
      HandlerThread var10000 = this.backgroundThread;
      if (var10000 != null) {
         var10000.quitSafely();
      }

      try {
         var10000 = this.backgroundThread;
         if (var10000 != null) {
            var10000.join();
         }

         this.backgroundThread = (HandlerThread)null;
         this.backgroundHandler = (Handler)null;
      } catch (InterruptedException var2) {
         Log.e("PosenetActivity", var2.toString());
      }

   }

   private final void fillBytes(Plane[] planes, byte[][] yuvBytes) {
      int i = 0;

      for(int var4 = planes.length; i < var4; ++i) {
         ByteBuffer buffer = planes[i].getBuffer();
         if (yuvBytes[i] == null) {
            yuvBytes[i] = new byte[buffer.capacity()];
         }

         byte[] var10001 = yuvBytes[i];
         if (yuvBytes[i] == null) {
            Intrinsics.throwNpe();
         }

         buffer.get(var10001);
      }

   }

   private final Bitmap cropBitmap(Bitmap bitmap) {
      float bitmapRatio = (float)bitmap.getHeight() / (float)bitmap.getWidth();
      float modelInputRatio = 1.0F;
      double maxDifference = 1.0E-5D;
      if ((double)Math.abs(modelInputRatio - bitmapRatio) < maxDifference) {
         return bitmap;
      } else {
         Bitmap var10000;
         Bitmap croppedBitmap;
         float cropHeight;
         if (modelInputRatio < bitmapRatio) {
            cropHeight = (float)bitmap.getHeight() - (float)bitmap.getWidth() / modelInputRatio;
            var10000 = Bitmap.createBitmap(bitmap, 0, (int)(cropHeight / (float)2), bitmap.getWidth(), (int)((float)bitmap.getHeight() - cropHeight));
            Intrinsics.checkExpressionValueIsNotNull(var10000, "Bitmap.createBitmap(\n   …toInt()\n                )");
            croppedBitmap = var10000;
         } else {
            cropHeight = (float)bitmap.getWidth() - (float)bitmap.getHeight() * modelInputRatio;
            var10000 = Bitmap.createBitmap(bitmap, (int)(cropHeight / (float)2), 0, (int)((float)bitmap.getWidth() - cropHeight), bitmap.getHeight());
            Intrinsics.checkExpressionValueIsNotNull(var10000, "Bitmap.createBitmap(\n   ….height\n                )");
            croppedBitmap = var10000;
         }

         return croppedBitmap;
      }
   }

   private final void setPaint() {
      this.paint.setColor(-65536);
      this.paint.setTextSize(80.0F);
      this.paint.setStrokeWidth(8.0F);
   }

   private final void draw(Canvas canvas, Person person, Bitmap bitmap) {
      canvas.drawColor(0, Mode.CLEAR);
      int screenWidth = false;
      int screenHeight = false;
      int left = false;
      int right = false;
      int top = false;
      int bottom = false;
      int screenWidth;
      int screenHeight;
      int left;
      int top;
      if (canvas.getHeight() > canvas.getWidth()) {
         screenWidth = canvas.getWidth();
         screenHeight = canvas.getWidth();
         left = 0;
         top = (canvas.getHeight() - canvas.getWidth()) / 2;
      } else {
         screenWidth = canvas.getHeight();
         screenHeight = canvas.getHeight();
         left = (canvas.getWidth() - canvas.getHeight()) / 2;
         top = 0;
      }

      int right = left + screenWidth;
      int bottom = top + screenHeight;
      this.setPaint();
      canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), new Rect(left, top, right, bottom), this.paint);
      float widthRatio = (float)screenWidth / (float)257;
      float heightRatio = (float)screenHeight / (float)257;
      Iterator var13 = person.getKeyPoints().iterator();

      KeyPoint keyPoint;
      while(var13.hasNext()) {
         keyPoint = (KeyPoint)var13.next();
         if ((double)keyPoint.getScore() > this.minConfidence) {
            Position position = keyPoint.getPosition();
            float adjustedX = (float)position.getX() * widthRatio + (float)left;
            float adjustedY = (float)position.getY() * heightRatio + (float)top;
            canvas.drawCircle(adjustedX, adjustedY, this.circleRadius, this.paint);
         }
      }

      var13 = person.getKeyPoints().iterator();

      while(var13.hasNext()) {
         keyPoint = (KeyPoint)var13.next();
         Log.d("KEYPOINT", "" + keyPoint.getBodyPart() + " " + (float)keyPoint.getPosition().getX() + ", " + (float)keyPoint.getPosition().getY() + " " + keyPoint.getScore());
      }

      var13 = this.bodyJoints.iterator();

      while(var13.hasNext()) {
         Pair line = (Pair)var13.next();
         if ((double)((KeyPoint)person.getKeyPoints().get(((BodyPart)line.getFirst()).ordinal())).getScore() > this.minConfidence & (double)((KeyPoint)person.getKeyPoints().get(((BodyPart)line.getSecond()).ordinal())).getScore() > this.minConfidence) {
            canvas.drawLine((float)((KeyPoint)person.getKeyPoints().get(((BodyPart)line.getFirst()).ordinal())).getPosition().getX() * widthRatio + (float)left, (float)((KeyPoint)person.getKeyPoints().get(((BodyPart)line.getFirst()).ordinal())).getPosition().getY() * heightRatio + (float)top, (float)((KeyPoint)person.getKeyPoints().get(((BodyPart)line.getSecond()).ordinal())).getPosition().getX() * widthRatio + (float)left, (float)((KeyPoint)person.getKeyPoints().get(((BodyPart)line.getSecond()).ordinal())).getPosition().getY() * heightRatio + (float)top, this.paint);
         }
      }

      String var26 = "Score: %.2f";
      Object[] var27 = new Object[]{person.getScore()};
      boolean var28 = false;
      String var10000 = String.format(var26, Arrays.copyOf(var27, var27.length));
      Intrinsics.checkExpressionValueIsNotNull(var10000, "java.lang.String.format(this, *args)");
      String var18 = var10000;
      canvas.drawText(var18, 15.0F * widthRatio, 30.0F * heightRatio + (float)bottom, this.paint);
      var26 = "Device: %s";
      Object[] var10001 = new Object[1];
      Posenet var10004 = this.posenet;
      if (var10004 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("posenet");
      }

      var10001[0] = var10004.getDevice();
      var27 = var10001;
      var28 = false;
      var10000 = String.format(var26, Arrays.copyOf(var27, var27.length));
      Intrinsics.checkExpressionValueIsNotNull(var10000, "java.lang.String.format(this, *args)");
      var18 = var10000;
      canvas.drawText(var18, 15.0F * widthRatio, 50.0F * heightRatio + (float)bottom, this.paint);
      var26 = "Time: %.2f ms";
      var10001 = new Object[1];
      var10004 = this.posenet;
      if (var10004 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("posenet");
      }

      var10001[0] = (float)var10004.getLastInferenceTimeNanos() * 1.0F / (float)1000000;
      var27 = var10001;
      var28 = false;
      var10000 = String.format(var26, Arrays.copyOf(var27, var27.length));
      Intrinsics.checkExpressionValueIsNotNull(var10000, "java.lang.String.format(this, *args)");
      var18 = var10000;
      canvas.drawText(var18, 15.0F * widthRatio, 70.0F * heightRatio + (float)bottom, this.paint);
      SurfaceHolder var29 = this.surfaceHolder;
      if (var29 == null) {
         Intrinsics.throwNpe();
      }

      var29.unlockCanvasAndPost(canvas);
   }

   private final void processImage(Bitmap bitmap) {
      Bitmap croppedBitmap = this.cropBitmap(bitmap);
      Bitmap scaledBitmap = Bitmap.createScaledBitmap(croppedBitmap, 257, 257, true);
      Posenet var10000 = this.posenet;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("posenet");
      }

      Intrinsics.checkExpressionValueIsNotNull(scaledBitmap, "scaledBitmap");
      Person person = var10000.estimateSinglePose(scaledBitmap);
      this.startcollectingData(person);
      this.stopCollectingData(person);
      this.discardCollectingData(person);
      SurfaceHolder var6 = this.surfaceHolder;
      if (var6 == null) {
         Intrinsics.throwNpe();
      }

      Canvas var7 = var6.lockCanvas();
      Intrinsics.checkExpressionValueIsNotNull(var7, "surfaceHolder!!.lockCanvas()");
      Canvas canvas = var7;
      this.draw(canvas, person, scaledBitmap);
   }

   private final void createCameraPreviewSession() {
      try {
         Size var10001 = this.previewSize;
         if (var10001 == null) {
            Intrinsics.throwNpe();
         }

         int var5 = var10001.getWidth();
         Size var10002 = this.previewSize;
         if (var10002 == null) {
            Intrinsics.throwNpe();
         }

         this.imageReader = ImageReader.newInstance(var5, var10002.getHeight(), 35, 2);
         ImageReader var10000 = this.imageReader;
         if (var10000 == null) {
            Intrinsics.throwNpe();
         }

         var10000.setOnImageAvailableListener((OnImageAvailableListener)this.imageAvailableListener, this.backgroundHandler);
         var10000 = this.imageReader;
         if (var10000 == null) {
            Intrinsics.throwNpe();
         }

         Surface recordingSurface = var10000.getSurface();
         CameraDevice var6 = this.cameraDevice;
         if (var6 == null) {
            Intrinsics.throwNpe();
         }

         this.previewRequestBuilder = var6.createCaptureRequest(1);
         Builder var3 = this.previewRequestBuilder;
         if (var3 == null) {
            Intrinsics.throwNpe();
         }

         var3.addTarget(recordingSurface);
         CameraDevice var4 = this.cameraDevice;
         if (var4 == null) {
            Intrinsics.throwNpe();
         }

         var4.createCaptureSession(CollectionsKt.listOf(recordingSurface), (android.hardware.camera2.CameraCaptureSession.StateCallback)(new android.hardware.camera2.CameraCaptureSession.StateCallback() {
            public void onConfigured(@NotNull CameraCaptureSession cameraCaptureSession) {
               Intrinsics.checkParameterIsNotNull(cameraCaptureSession, "cameraCaptureSession");
               if (CollectData.this.cameraDevice != null) {
                  CollectData.this.captureSession = cameraCaptureSession;

                  try {
                     Builder var10000 = CollectData.this.previewRequestBuilder;
                     if (var10000 == null) {
                        Intrinsics.throwNpe();
                     }

                     var10000.set(CaptureRequest.CONTROL_AF_MODE, 4);
                     CollectData var4 = CollectData.this;
                     Builder var10001 = CollectData.this.previewRequestBuilder;
                     if (var10001 == null) {
                        Intrinsics.throwNpe();
                     }

                     var4.setAutoFlash(var10001);
                     var4 = CollectData.this;
                     var10001 = CollectData.this.previewRequestBuilder;
                     if (var10001 == null) {
                        Intrinsics.throwNpe();
                     }

                     var4.previewRequest = var10001.build();
                     CameraCaptureSession var5 = CollectData.this.captureSession;
                     if (var5 == null) {
                        Intrinsics.throwNpe();
                     }

                     CaptureRequest var6 = CollectData.this.previewRequest;
                     if (var6 == null) {
                        Intrinsics.throwNpe();
                     }

                     var5.setRepeatingRequest(var6, (CaptureCallback)CollectData.this.captureCallback, CollectData.this.backgroundHandler);
                  } catch (CameraAccessException var3) {
                     Log.e("PosenetActivity", var3.toString());
                  }

               }
            }

            public void onConfigureFailed(@NotNull CameraCaptureSession cameraCaptureSession) {
               Intrinsics.checkParameterIsNotNull(cameraCaptureSession, "cameraCaptureSession");
               CollectData.this.showToast("Failed");
            }
         }), (Handler)null);
      } catch (CameraAccessException var2) {
         Log.e("PosenetActivity", var2.toString());
      }

   }

   private final void showToast(final String text) {
      final FragmentActivity activity = this.getActivity();
      if (activity != null) {
         activity.runOnUiThread((Runnable)(new Runnable() {
            public final void run() {
               Toast.makeText((Context)activity, (CharSequence)text, 0).show();
            }
         }));
      }

   }

   private final void setAutoFlash(Builder requestBuilder) {
      if (this.flashSupported) {
         requestBuilder.set(CaptureRequest.CONTROL_AE_MODE, 2);
      }

   }

   public CollectData() {
      this.bodyJoints = CollectionsKt.listOf(new Pair[]{new Pair(BodyPart.LEFT_WRIST, BodyPart.LEFT_ELBOW), new Pair(BodyPart.LEFT_ELBOW, BodyPart.LEFT_SHOULDER), new Pair(BodyPart.LEFT_SHOULDER, BodyPart.RIGHT_SHOULDER), new Pair(BodyPart.RIGHT_SHOULDER, BodyPart.RIGHT_ELBOW), new Pair(BodyPart.RIGHT_ELBOW, BodyPart.RIGHT_WRIST), new Pair(BodyPart.LEFT_SHOULDER, BodyPart.LEFT_HIP), new Pair(BodyPart.LEFT_HIP, BodyPart.RIGHT_HIP), new Pair(BodyPart.RIGHT_HIP, BodyPart.RIGHT_SHOULDER), new Pair(BodyPart.LEFT_HIP, BodyPart.LEFT_KNEE), new Pair(BodyPart.LEFT_KNEE, BodyPart.LEFT_ANKLE), new Pair(BodyPart.RIGHT_HIP, BodyPart.RIGHT_KNEE), new Pair(BodyPart.RIGHT_KNEE, BodyPart.RIGHT_ANKLE)});
      this.minConfidence = 0.5D;
      this.circleRadius = 8.0F;
      this.paint = new Paint();
      this.PREVIEW_WIDTH = 640;
      this.PREVIEW_HEIGHT = 480;
      this.yuvBytes = new byte[3][];
      this.cameraOpenCloseLock = new Semaphore(1);
      this.stateCallback = new StateCallback() {
         public void onOpened(@NotNull CameraDevice cameraDevice) {
            Intrinsics.checkParameterIsNotNull(cameraDevice, "cameraDevice");
            CollectData.this.cameraOpenCloseLock.release();
            CollectData.this.cameraDevice = cameraDevice;
            CollectData.this.createCameraPreviewSession();
         }

         public void onDisconnected(@NotNull CameraDevice cameraDevice) {
            Intrinsics.checkParameterIsNotNull(cameraDevice, "cameraDevice");
            CollectData.this.cameraOpenCloseLock.release();
            cameraDevice.close();
            CollectData.this.cameraDevice = (CameraDevice)null;
         }

         public void onError(@NotNull CameraDevice cameraDevice, int error) {
            Intrinsics.checkParameterIsNotNull(cameraDevice, "cameraDevice");
            this.onDisconnected(cameraDevice);
            FragmentActivity var10000 = CollectData.this.getActivity();
            if (var10000 != null) {
               var10000.finish();
            }

         }
      };
      this.captureCallback = new CaptureCallback() {
         public void onCaptureProgressed(@NotNull CameraCaptureSession session, @NotNull CaptureRequest request, @NotNull CaptureResult partialResult) {
            Intrinsics.checkParameterIsNotNull(session, "session");
            Intrinsics.checkParameterIsNotNull(request, "request");
            Intrinsics.checkParameterIsNotNull(partialResult, "partialResult");
         }

         public void onCaptureCompleted(@NotNull CameraCaptureSession session, @NotNull CaptureRequest request, @NotNull TotalCaptureResult result) {
            Intrinsics.checkParameterIsNotNull(session, "session");
            Intrinsics.checkParameterIsNotNull(request, "request");
            Intrinsics.checkParameterIsNotNull(result, "result");
         }
      };
      this.imageAvailableListener = new OnImageAvailableListener() {
         public void onImageAvailable(@NotNull ImageReader imageReader) {
            Intrinsics.checkParameterIsNotNull(imageReader, "imageReader");
            if (CollectData.this.previewWidth != 0 && CollectData.this.previewHeight != 0) {
               Image var10000 = imageReader.acquireLatestImage();
               if (var10000 != null) {
                  Image image = var10000;
                  CollectData var8 = CollectData.this;
                  Plane[] var10001 = image.getPlanes();
                  Intrinsics.checkExpressionValueIsNotNull(var10001, "image.planes");
                  var8.fillBytes(var10001, CollectData.this.yuvBytes);
                  ImageUtils var9 = ImageUtils.INSTANCE;
                  byte[] var10 = CollectData.this.yuvBytes[0];
                  if (var10 == null) {
                     Intrinsics.throwNpe();
                  }

                  byte[] var10002 = CollectData.this.yuvBytes[1];
                  if (var10002 == null) {
                     Intrinsics.throwNpe();
                  }

                  byte[] var10003 = CollectData.this.yuvBytes[2];
                  if (var10003 == null) {
                     Intrinsics.throwNpe();
                  }

                  int var10004 = CollectData.this.previewWidth;
                  int var10005 = CollectData.this.previewHeight;
                  Plane var10006 = image.getPlanes()[0];
                  Intrinsics.checkExpressionValueIsNotNull(var10006, "image.planes[0]");
                  int var6 = var10006.getRowStride();
                  Plane var10007 = image.getPlanes()[1];
                  Intrinsics.checkExpressionValueIsNotNull(var10007, "image.planes[1]");
                  int var7 = var10007.getRowStride();
                  Plane var10008 = image.getPlanes()[1];
                  Intrinsics.checkExpressionValueIsNotNull(var10008, "image.planes[1]");
                  var9.convertYUV420ToARGB8888(var10, var10002, var10003, var10004, var10005, var6, var7, var10008.getPixelStride(), CollectData.access$getRgbBytes$p(CollectData.this));
                  Bitmap imageBitmap = Bitmap.createBitmap(CollectData.access$getRgbBytes$p(CollectData.this), CollectData.this.previewWidth, CollectData.this.previewHeight, Config.ARGB_8888);
                  Matrix rotateMatrix = new Matrix();
                  rotateMatrix.postRotate(90.0F);
                  Bitmap rotatedBitmap = Bitmap.createBitmap(imageBitmap, 0, 0, CollectData.this.previewWidth, CollectData.this.previewHeight, rotateMatrix, true);
                  image.close();
                  var8 = CollectData.this;
                  Intrinsics.checkExpressionValueIsNotNull(rotatedBitmap, "rotatedBitmap");
                  var8.processImage(rotatedBitmap);
               }
            }
         }
      };
   }

   static {
      ORIENTATIONS.append(0, 90);
      ORIENTATIONS.append(1, 0);
      ORIENTATIONS.append(2, 270);
      ORIENTATIONS.append(3, 180);
   }

   // $FF: synthetic method
   public static final boolean access$getSTOP$p(CollectData $this) {
      return $this.STOP;
   }

   // $FF: synthetic method
   public static final boolean access$getSAVE$p(CollectData $this) {
      return $this.SAVE;
   }

   // $FF: synthetic method
   public static final boolean access$getSTART$p(CollectData $this) {
      return $this.START;
   }

   // $FF: synthetic method
   public static final void access$setPreviewRequestBuilder$p(CollectData $this, Builder var1) {
      $this.previewRequestBuilder = var1;
   }

   // $FF: synthetic method
   public static final void access$setBackgroundHandler$p(CollectData $this, Handler var1) {
      $this.backgroundHandler = var1;
   }

   // $FF: synthetic method
   public static final void access$setPreviewWidth$p(CollectData $this, int var1) {
      $this.previewWidth = var1;
   }

   // $FF: synthetic method
   public static final void access$setPreviewHeight$p(CollectData $this, int var1) {
      $this.previewHeight = var1;
   }

   // $FF: synthetic method
   public static final void access$setYuvBytes$p(CollectData $this, byte[][] var1) {
      $this.yuvBytes = var1;
   }

   // $FF: synthetic method
   public static final int[] access$getRgbBytes$p(CollectData $this) {
      int[] var10000 = $this.rgbBytes;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("rgbBytes");
      }

      return var10000;
   }

   // $FF: synthetic method
   public static final void access$setRgbBytes$p(CollectData $this, int[] var1) {
      $this.rgbBytes = var1;
   }

   public View _$_findCachedViewById(int var1) {
      if (this._$_findViewCache == null) {
         this._$_findViewCache = new HashMap();
      }

      View var2 = (View)this._$_findViewCache.get(var1);
      if (var2 == null) {
         View var10000 = this.getView();
         if (var10000 == null) {
            return null;
         }

         var2 = var10000.findViewById(var1);
         this._$_findViewCache.put(var1, var2);
      }

      return var2;
   }

   public void _$_clearFindViewByIdCache() {
      if (this._$_findViewCache != null) {
         this._$_findViewCache.clear();
      }

   }

   // $FF: synthetic method
   public void onDestroyView() {
      super.onDestroyView();
      this._$_clearFindViewByIdCache();
   }

   @Metadata(
      mv = {1, 1, 16},
      bv = {1, 0, 3},
      k = 1,
      d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\b"},
      d2 = {"Lcom/example/safedrive/nav_components/CollectData/CollectData$ErrorDialog;", "Landroidx/fragment/app/DialogFragment;", "()V", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "app"}
   )
   public static final class ErrorDialog extends DialogFragment {
      private static final String ARG_MESSAGE = "message";
      public static final CollectData.ErrorDialog.Companion Companion = new CollectData.ErrorDialog.Companion((DefaultConstructorMarker)null);
      private HashMap _$_findViewCache;

      @NotNull
      public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
         android.app.AlertDialog.Builder var10000 = new android.app.AlertDialog.Builder((Context)this.getActivity());
         Bundle var10001 = this.getArguments();
         if (var10001 == null) {
            Intrinsics.throwNpe();
         }

         AlertDialog var2 = var10000.setMessage((CharSequence)var10001.getString(ARG_MESSAGE)).setPositiveButton(17039370, (android.content.DialogInterface.OnClickListener)(new android.content.DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface $noName_0, int $noName_1) {
               FragmentActivity var10000 = ErrorDialog.this.getActivity();
               if (var10000 == null) {
                  Intrinsics.throwNpe();
               }

               var10000.finish();
            }
         })).create();
         Intrinsics.checkExpressionValueIsNotNull(var2, "AlertDialog.Builder(acti…                .create()");
         return (Dialog)var2;
      }

      public View _$_findCachedViewById(int var1) {
         if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
         }

         View var2 = (View)this._$_findViewCache.get(var1);
         if (var2 == null) {
            View var10000 = this.getView();
            if (var10000 == null) {
               return null;
            }

            var2 = var10000.findViewById(var1);
            this._$_findViewCache.put(var1, var2);
         }

         return var2;
      }

      public void _$_clearFindViewByIdCache() {
         if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
         }

      }

      // $FF: synthetic method
      public void onDestroyView() {
         super.onDestroyView();
         this._$_clearFindViewByIdCache();
      }

      @JvmStatic
      @NotNull
      public static final CollectData.ErrorDialog newInstance(@NotNull String message) {
         return Companion.newInstance(message);
      }

      @Metadata(
         mv = {1, 1, 16},
         bv = {1, 0, 3},
         k = 1,
         d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004H\u0007R\u0016\u0010\u0003\u001a\u00020\u00048\u0002X\u0083D¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006\t"},
         d2 = {"Lcom/example/safedrive/nav_components/CollectData/CollectData$ErrorDialog$Companion;", "", "()V", "ARG_MESSAGE", "", "ARG_MESSAGE$annotations", "newInstance", "Lcom/example/safedrive/nav_components/CollectData/CollectData$ErrorDialog;", "message", "app"}
      )
      public static final class Companion {
         /** @deprecated */
         // $FF: synthetic method
         @JvmStatic
         private static void ARG_MESSAGE$annotations() {
         }

         @JvmStatic
         @NotNull
         public final CollectData.ErrorDialog newInstance(@NotNull String message) {
            Intrinsics.checkParameterIsNotNull(message, "message");
            CollectData.ErrorDialog var2 = new CollectData.ErrorDialog();
            boolean var3 = false;
            boolean var4 = false;
            int var6 = false;
            Bundle var7 = new Bundle();
            boolean var9 = false;
            boolean var10 = false;
            int var12 = false;
            var7.putString(CollectData.ErrorDialog.ARG_MESSAGE, message);
            var2.setArguments(var7);
            return var2;
         }

         private Companion() {
         }

         // $FF: synthetic method
         public Companion(DefaultConstructorMarker $constructor_marker) {
            this();
         }
      }
   }

   @Metadata(
      mv = {1, 1, 16},
      bv = {1, 0, 3},
      k = 1,
      d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"},
      d2 = {"Lcom/example/safedrive/nav_components/CollectData/CollectData$Companion;", "", "()V", "FRAGMENT_DIALOG", "", "ORIENTATIONS", "Landroid/util/SparseIntArray;", "TAG", "app"}
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
// CollectDataKt.java
package com.example.safedrive.nav_components.CollectData;

import kotlin.Metadata;

@Metadata(
   mv = {1, 1, 16},
   bv = {1, 0, 3},
   k = 2,
   d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0004"},
   d2 = {"ARG_PARAM1", "", "ARG_PARAM2", "CSV_HEADER", "app"}
)
public final class CollectDataKt {
   private static final String CSV_HEADER = "id,class,NOSE,LEFT_EYE,RIGHT_EYE,LEFT_EAR,RIGHT_EAR,LEFT_SHOULDER,RIGHT_SHOULDER,LEFT_ELBOW,RIGHT_ELBOW,LEFT_WRIST,RIGHT_WRIST,LEFT_HIP,RIGHT_HIP,LEFT_KNEE,RIGHT_KNEE,LEFT_ANKLE,RIGHT_ANKLE,score";
   private static final String ARG_PARAM1 = "param1";
   private static final String ARG_PARAM2 = "param2";
}
