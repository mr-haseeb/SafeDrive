package com.example.safedrive.activities

import android.app.AlertDialog
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.view.Display
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.safedrive.R
import com.example.safedrive.sqlite.DatabaseHelper
import com.example.safedrive.sqlite.Model
import kotlinx.android.synthetic.main.activity_view_data.*

class ViewData : AppCompatActivity() {
    private var myDb: DatabaseHelper? = null
    val dataholder = ArrayList<Model>()


    val list = arrayListOf<String>(
         "id", "clas", "NOSE_X"," NOSE_Y"," LEFT_EYE_X"," LEFT_EYE_Y", "RIGHT_EYE_X", "RIGHT_EYE_Y", "LEFT_EAR_X","LEFT_EAR_Y","RIGHT_EAR_X","RIGHT_EAR_Y",
        "LEFT_SHOULDER_X","LEFT_SHOULDER_Y","RIGHT_SHOULDER_X","RIGHT_SHOULDER_Y","LEFT_ELBOW_X","LEFT_ELBOW_Y","RIGHT_ELBOW_X" ,"RIGHT_ELBOW_Y","LEFT_WRIST_X","LEFT_WRIST_Y","RIGHT_WRIST_X","RIGHT_WRIST_Y",
        "LEFT_HIP_X","LEFT_HIP_Y","RIGHT_HIP_X" ,"RIGHT_HIP_Y","LEFT_KNEE_X","LEFT_KNEE_Y","RIGHT_KNEE_X","RIGHT_KNEE_Y","LEFT_ANKLE_X","LEFT_ANKLE_Y","RIGHT_ANKLE_X","RIGHT_ANKLE_Y","score")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_data)

        recyclerView.setLayoutManager(LinearLayoutManager(this))
//        recyclerView.setOrientation(LinearLayoutManager.VERTICAL)
        val myadapter=MyAdapter(dataholder)
        recyclerView.setAdapter(myadapter)



        val cursor: Cursor? = myDb?.getAllData()
        if (cursor != null) {
            Log.i("data",cursor.getString(0).toString())
        }

        if (cursor != null) {
            while (cursor.moveToNext()) {

                    val obj = Model(cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4), cursor.getString(5), cursor.getString(6),
                        cursor.getString(7), cursor.getString(8), cursor.getString(9),cursor.getString(10), cursor.getString(11), cursor.getString(12)
                    ,cursor.getString(13), cursor.getString(14), cursor.getString(15),cursor.getString(16), cursor.getString(17), cursor.getString(18)
                    ,cursor.getString(19), cursor.getString(20), cursor.getString(21),cursor.getString(22), cursor.getString(23), cursor.getString(24)
                    ,cursor.getString(25), cursor.getString(26), cursor.getString(27),cursor.getString(28), cursor.getString(29), cursor.getString(30)
                    ,cursor.getString(31), cursor.getString(32), cursor.getString(33),cursor.getString(34), cursor.getString(35), cursor.getString(36),
                        cursor.getString(37))

                    dataholder.add(obj)



            }


        }


        delete_all_data.setOnClickListener{
            myDb?.deleteAll()
            Toast.makeText(this, "Data Delete", Toast.LENGTH_SHORT).show()
        }

        view_all.setOnClickListener{
//            val res: Cursor? = myDb?.getAllData()
//            if (res != null) {
//                if (res.count == 0) {
//                    showMessage("Error", "Nothing Found")
//                    return@setOnClickListener
//                }
//
//                val buffer = StringBuffer()
//                while (res.moveToNext()) {
//                    buffer.append("Id : " + res.getString(0) + "\n")
//                    buffer.append("NOISE_X : " + res.getString(1) + "\n")
//                    buffer.append("NOSE_Y : " + res.getString(2) + "\n")
////                buffer.append("Id : "+res.getString(3)+"\n")
//
//                    showMessage("Data", buffer.toString())
//                }
//
//            }


        }


    }

    public fun showMessage(title: String, message: String) {
            val builder = AlertDialog.Builder(this)
            builder.setCancelable(true)
            builder.setTitle(title)
            builder.setMessage(message)

        }




}