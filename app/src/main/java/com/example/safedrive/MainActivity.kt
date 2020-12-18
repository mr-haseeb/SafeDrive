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

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.safedrive.fragments.dashborad.Dashborad
import com.example.safedrive.fragments.Profile.Profile
import com.example.safedrive.fragments.collect_data.CollectData
import com.example.safedrive.lib.PosenetActivity
import com.ismaeldivita.chipnavigation.ChipNavigationBar


class MainActivity : AppCompatActivity() {

  val dashborad = Dashborad()
  val collect_data = CollectData()
  val profile = Profile()
  val posenetActivity = PosenetActivity()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // Default Selected Fragment
    supportFragmentManager.beginTransaction()
      .replace(R.id.fragment_holder, dashborad)
      .commit()



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
          supportFragmentManager.beginTransaction()
            // id of container and object of fragment class
            .replace(R.id.fragment_holder, collect_data)
            .commit()

          // Respond to navigation item 2 click
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
}
