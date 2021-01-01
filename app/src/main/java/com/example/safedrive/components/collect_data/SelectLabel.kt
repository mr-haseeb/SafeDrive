package com.example.safedrive.components.collect_data

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.GridLayout
import com.example.safedrive.MainActivity
import com.example.safedrive.R
import com.example.safedrive.components.CollectData.CollectData

class SelectLabel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_label)
        val grid: GridLayout = findViewById<View>(R.id.mainGrid) as GridLayout
        val childCount: Int = grid.getChildCount()

        for (i in 0 until childCount) {
            grid.getChildAt(i).setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View?) {
                    // your click code here

                    if (view != null) {
                        when (view.id) {
                            R.id.safe_driving -> {

                                val intent = Intent(this@SelectLabel, MainActivity::class.java)
                                intent.putExtra("activity","safe_drive")
                                startActivity(intent)

//                                                            val intent = Intent(
//                                                                SelectLabel!!.baseContext,
//                                                                MainActivity::class.java
//                                                            )
//
//
//                                                            activity!!.startActivity(intent)
//                                Log.d("button", "safe driving")
//                                val bundle = Bundle()
//                                bundle.putString("activity", "safe_driving")
//                                val collectData = CollectData();
//                                collectData.setArguments(bundle)
//                                supportFragmentManager.beginTransaction()
//                                    .replace(R.id.fragment_holder, collectData)
//                                    .commit()

                            }
                            R.id.texting -> {
                                val bundle = Bundle()

                                bundle.putString("activity", "texting")
                                val collectData = CollectData();
                                collectData.setArguments(bundle)
                                supportFragmentManager.beginTransaction()
                                    .replace(R.id.fragment_holder, collectData)
                                    .commit()

                            }
                            R.id.reaching_behind -> {

                                val bundle = Bundle()

                                bundle.putString("activity", "reaching_behind")
                                val collectData = CollectData();
                                collectData.setArguments(bundle)
                                supportFragmentManager.beginTransaction()
                                    .replace(R.id.fragment_holder, collectData)
                                    .commit()

                            }
                            R.id.drinking -> {
                                val bundle = Bundle()

                                bundle.putString("activity", "drinking")
                                val collectData = CollectData();
                                collectData.setArguments(bundle)
                                supportFragmentManager.beginTransaction()
                                    .replace(R.id.fragment_holder, collectData)
                                    .commit()


                            }

                            R.id.operating_the_music -> {

                                val bundle = Bundle()

                                bundle.putString("activity", "operating_the_music")
                                val collectData = CollectData();
                                collectData.setArguments(bundle)
                                supportFragmentManager.beginTransaction()
                                    .replace(R.id.fragment_holder, collectData)
                                    .commit()

                            }
                            R.id.talking_on_the_phone -> {
                                val bundle = Bundle()

                                bundle.putString("activity", "talking_ont_the_phone")
                                val collectData = CollectData();
                                collectData.setArguments(bundle)
                                supportFragmentManager.beginTransaction()
                                    .replace(R.id.fragment_holder, collectData)
                                    .commit()
                            }
                            R.id.export_all -> {
                            }
                            R.id.view_data -> {
                            }

                            else -> {
                            }
                        }
                    }


                }
            }
            )
    }

}
    fun selectLabel(view:View){

        if (view != null) {
            when (view.id) {
                R.id.safe_driving -> {

                    //                            val intent = Intent(
                    //                                activity!!.baseContext,
                    //                                MainActivity::class.java
                    //                            )
                    //                            intent.putExtra("activity","safe_drive")
                    //
                    //                            activity!!.startActivity(intent)
                    Log.d("button", "safe driving")
                    val bundle = Bundle()
                    bundle.putString("activity", "safe_driving")
                    val collectData = CollectData();
                    collectData.setArguments(bundle)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_holder, collectData)
                        .commit()

                }
                R.id.texting -> {
                    val bundle = Bundle()

                    bundle.putString("activity", "texting")
                    val collectData = CollectData();
                    collectData.setArguments(bundle)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_holder, collectData)
                        .commit()

                }
                R.id.reaching_behind -> {

                    val bundle = Bundle()

                    bundle.putString("activity", "reaching_behind")
                    val collectData = CollectData();
                    collectData.setArguments(bundle)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_holder, collectData)
                        .commit()

                }
                R.id.drinking -> {
                    val bundle = Bundle()

                    bundle.putString("activity", "drinking")
                    val collectData = CollectData();
                    collectData.setArguments(bundle)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_holder, collectData)
                        .commit()


                }

                R.id.operating_the_music -> {

                    val bundle = Bundle()

                    bundle.putString("activity", "operating_the_music")
                    val collectData = CollectData();
                    collectData.setArguments(bundle)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_holder, collectData)
                        .commit()

                }
                R.id.talking_on_the_phone -> {
                    val bundle = Bundle()

                    bundle.putString("activity", "talking_ont_the_phone")
                    val collectData = CollectData();
                    collectData.setArguments(bundle)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_holder, collectData)
                        .commit()
                }
                R.id.export_all -> {
                }
                R.id.view_data -> {
                }

                else -> {
                }
            }
        }
    }

}