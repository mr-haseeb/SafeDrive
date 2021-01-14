package com.example.safedrive.nav_components.dashborad

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.safedrive.R
import com.example.safedrive.sqlite.DatabaseHelper


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
        super.onViewCreated(view, savedInstanceState)
    }

}
