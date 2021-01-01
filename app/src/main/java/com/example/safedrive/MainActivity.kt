/*
 * Copyright 2019 The TensorFlow Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.safedrive

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.safedrive.components.CollectData.CollectData
import com.example.safedrive.components.Profile.Profile
import com.example.safedrive.components.collect_data.SelectLabel
import com.example.safedrive.components.dashborad.Dashborad
import com.example.safedrive.lib.PosenetActivity
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import kotlinx.android.synthetic.main.collect_data.*


class MainActivity : AppCompatActivity() {

  val dashborad = Dashborad()
//  val collect_data = CollectData()
  val profile = Profile()
  val posenetActivity = PosenetActivity()
  val selectLabel=SelectLabel();

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)


    // Default Selected Fragment
//    supportFragmentManager.beginTransaction()
//      .replace(R.id.fragment_holder, dashborad)
//      .commit()


    val intent = intent
    val message = intent.getStringExtra("activity")

    if (message != null) {
      collectData(message)
    }else{
//       Default Selected Fragment
    supportFragmentManager.beginTransaction()
      .replace(R.id.fragment_holder, dashborad)
      .commit()
    }


    val bottomNavigation: ChipNavigationBar = findViewById(R.id.bottom_navigation)
    bottomNavigation.setOnItemSelectedListener { item ->
      when (item) {
        R.id.bottom_nav_dashborad -> {
          // Respond to navigation item 1 click
          supportFragmentManager.beginTransaction()
            // id of container and object of fragment class
//            .replace(R.id.fragment_holder, posenetActivity)
            .replace(R.id.fragment_holder, dashborad)
            .commit()

          true
        }
        R.id.bottom_nav_collect_data -> {
//          supportFragmentManager.beginTransaction()
//            // id of container and object of fragment class
//            .replace(R.id.fragment_holder, selectLabel)
//            .commit()

          // Respond to navigation item 2 click

          val intent = Intent(this@MainActivity, SelectLabel::class.java)
          startActivity(intent)
          true
        }
        R.id.bottom_nav_profile -> {

          supportFragmentManager.beginTransaction()
            // id of container and object of fragment class
            .replace(R.id.fragment_holder, profile)
            .commit()

          true

        }
        else -> false
      }
    }





  }
  fun collectData(activity:String){

    if(activity.equals("safe_drive")){
      val bundle = Bundle()
      bundle.putString("actvity", activity)
      val collectData = CollectData()
      collectData.setArguments(bundle)

      supportFragmentManager.beginTransaction()
        // id of container and object of fragment class
        .replace(R.id.fragment_holder, collectData)
        .commit()

    }


  }

}
