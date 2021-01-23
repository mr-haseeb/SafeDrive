// Dashborad.java
package com.example.safedrive.nav_components.dashborad;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import com.example.safedrive.R.id;
import com.example.safedrive.activities.Prediction;
import com.example.safedrive.activities.ViewData;
import com.example.safedrive.activities.cnn_tflite.CnnTflite;
import com.example.safedrive.activities.driveralert.MainActivity;
import com.example.safedrive.sqlite.DatabaseHelper;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 1, 16},
   bv = {1, 0, 3},
   k = 1,
   d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u001a\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u000e"},
   d2 = {"Lcom/example/safedrive/nav_components/dashborad/Dashborad;", "Landroidx/fragment/app/Fragment;", "()V", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "app"}
)
public final class Dashborad extends Fragment {
   private HashMap _$_findViewCache;

   @Nullable
   public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      Intrinsics.checkParameterIsNotNull(inflater, "inflater");
      new DatabaseHelper((Context)this.getActivity());
      View v = inflater.inflate(1300142, container, false);
      String[] var10000 = new String[]{"safe_drive", "texting", "reaching_behind", "drinking", "operating_the_music", "talking_ont_the_phone"};
      return v;
   }

   public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
      Intrinsics.checkParameterIsNotNull(view, "view");
      ((ImageView)this._$_findCachedViewById(id.view_data)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            Intent intent = new Intent((Context)Dashborad.this.getActivity(), ViewData.class);
            Dashborad.this.startActivity(intent);
         }
      }));
      ((ImageView)this._$_findCachedViewById(id.posenet)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            Intent intent = new Intent((Context)Dashborad.this.getActivity(), Prediction.class);
            Dashborad.this.startActivity(intent);
         }
      }));
      ((ImageView)this._$_findCachedViewById(id.driveralert)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            Intent intent = new Intent((Context)Dashborad.this.getActivity(), MainActivity.class);
            Dashborad.this.startActivity(intent);
         }
      }));
      ((ImageView)this._$_findCachedViewById(id.cnn)).setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            Intent intent = new Intent((Context)Dashborad.this.getActivity(), CnnTflite.class);
            Dashborad.this.startActivity(intent);
         }
      }));
      super.onViewCreated(view, savedInstanceState);
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
}
// DashboradKt.java
package com.example.safedrive.nav_components.dashborad;

import kotlin.Metadata;

@Metadata(
   mv = {1, 1, 16},
   bv = {1, 0, 3},
   k = 2,
   d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0003"},
   d2 = {"ARG_PARAM1", "", "ARG_PARAM2", "app"}
)
public final class DashboradKt {
   private static final String ARG_PARAM1 = "param1";
   private static final String ARG_PARAM2 = "param2";
}
