package com.example.safedrive.nav_components.dashborad

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.safedrive.R
import com.example.safedrive.activities.AppGuide
import com.example.safedrive.activities.Prediction
import com.example.safedrive.activities.ViewData
import com.example.safedrive.activities.cnn_tflite.CnnTflite
import com.example.safedrive.activities.driveralert.FaceTrackerActivity
import com.example.safedrive.activities.driveralert.MainActivity
import com.example.safedrive.sqlite.DatabaseHelper
import kotlinx.android.synthetic.main.dashborad.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Dashborad.newInstance] factory method to
 * create an instance of this fragment.
 */
class Dashborad : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val myDb = DatabaseHelper(this.activity)

        val v = inflater.inflate(R.layout.dashborad, container, false)
        val values = arrayOf(
            "safe_drive",
            "texting",
            "reaching_behind",
            "drinking",
            "operating_the_music",
            "talking_ont_the_phone"
        )
//        val spinner = v.findViewById(R.id.spinner) as Spinner
//        val adapter = ArrayAdapter(
//            this.activity!!,
//            android.R.layout.simple_spinner_item,
//            values
//        )
//        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
//        spinner.adapter = adapter
//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>,
//                view: View?,
//                position: Int,
//                l: Long
//            ) {
//                when (position) {
//                    0 -> {
//                        textView.text="safe_drive"
//                    }
//                    1 -> {
//                        textView.text="texting"
//                    }
//                    2 -> {
//                        textView.text="reaching_behind"
//                    }
//                    3->{
//                        textView.text="drinking"
//                    }
//                    4->{
//                        textView.text="operating_the_music"
//
//                    }
//                    5->{
//                        textView.text="talking_ont_the_phone"
//                    }
//                }
//            }
//
//            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
//        }


        return v
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view_data.setOnClickListener { val intent = Intent(activity, ViewData::class.java)
            startActivity(intent)
        }

        posenet.setOnClickListener { val intent = Intent(activity, Prediction::class.java)
            startActivity(intent)
        }
        driveralert.setOnClickListener { val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }

//        cnn.setOnClickListener { val intent = Intent(activity, CnnTflite::class.java)
//            startActivity(intent)
//        }
        cnn.setOnClickListener {
            val intent =
                Intent(Intent.ACTION_MAIN)
            intent.component = ComponentName(
                "org.tensorflow.demo",
                "org.tensorflow.demo.ClassifierActivity"
            )
            startActivity(intent)


        }
        help.setOnClickListener{
            val intent=Intent(activity,AppGuide::class.java)
            startActivity(intent)
        }

        super.onViewCreated(view, savedInstanceState)
    }

}
