package com.example.safedrive.fragments.collect_data

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.fragment.app.Fragment
import com.example.safedrive.MainActivity
import com.example.safedrive.R
import com.example.safedrive.fragments.CollectData.CollectData
import kotlinx.android.synthetic.main.select_label.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SelectLabel.newInstance] factory method to
 * create an instance of this fragment.
 */
class SelectLabel : Fragment() {
    // TODO: Rename and change types of parameters

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view: View = inflater.inflate(R.layout.select_label, container, false)

        mainGrid.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                if (view != null) {
                    when (view.id) {
                        R.id.safe_driving -> {

                            val intent = Intent(
                                activity!!.baseContext,
                                MainActivity::class.java
                            )
                            intent.putExtra("activity","safe_drive")

                            activity!!.startActivity(intent)


                            Log.d("button","safe driving")

                            val bundle = Bundle()

                            bundle.putString("activity","safe_driving")
                            val collectData = CollectData();

                            collectData.setArguments(bundle)
                            getFragmentManager()?.beginTransaction()?.replace(R.id.fragment_holder,collectData)?.commit()


                        }
                        R.id.texting -> {
                            val bundle = Bundle()

                            bundle.putString("activity","texting")
                            val collectData = CollectData();

                            collectData.setArguments(bundle)
                            getFragmentManager()?.beginTransaction()?.replace(R.id.fragment_holder,collectData)?.commit()

                        }
                        R.id.reaching_behind -> {

                            val bundle = Bundle()

                            bundle.putString("activity","reaching_behind")
                            val collectData = CollectData();

                            collectData.setArguments(bundle)
                            getFragmentManager()?.beginTransaction()?.replace(R.id.fragment_holder,collectData)?.commit()

                        }
                        R.id.drinking -> {
                            val bundle = Bundle()

                            bundle.putString("activity","drinking")
                            val collectData = CollectData();

                            collectData.setArguments(bundle)
                            getFragmentManager()?.beginTransaction()?.replace(R.id.fragment_holder,collectData)?.commit()


                        }

                        R.id.operating_the_music -> {

                            val bundle = Bundle()

                            bundle.putString("activity","operating_the_music")
                            val collectData = CollectData();

                            collectData.setArguments(bundle)
                            getFragmentManager()?.beginTransaction()?.replace(R.id.fragment_holder,collectData)?.commit()

                        }
                        R.id.talking_on_the_phone -> {
                            val bundle = Bundle()

                            bundle.putString("activity","talking_ont_the_phone")
                            val collectData = CollectData();

                            collectData.setArguments(bundle)
                            getFragmentManager()?.beginTransaction()?.replace(R.id.fragment_holder,collectData)?.commit()
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

        })
        return view
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.safe_driving -> {

                val intent = Intent(
                    activity!!.baseContext,
                    MainActivity::class.java
                )
                intent.putExtra("activity","safe_drive")

                activity!!.startActivity(intent)


                Log.d("button","safe driving")

                val bundle = Bundle()

                bundle.putString("activity","safe_driving")
                val collectData = CollectData();

                collectData.setArguments(bundle)
                getFragmentManager()?.beginTransaction()?.replace(R.id.fragment_holder,collectData)?.commit()


            }
            R.id.texting -> {
                val bundle = Bundle()

                bundle.putString("activity","texting")
                val collectData = CollectData();

                collectData.setArguments(bundle)
                getFragmentManager()?.beginTransaction()?.replace(R.id.fragment_holder,collectData)?.commit()

            }
            R.id.reaching_behind -> {

                val bundle = Bundle()

                bundle.putString("activity","reaching_behind")
                val collectData = CollectData();

                collectData.setArguments(bundle)
                getFragmentManager()?.beginTransaction()?.replace(R.id.fragment_holder,collectData)?.commit()

            }
            R.id.drinking -> {
                val bundle = Bundle()

                bundle.putString("activity","drinking")
                val collectData = CollectData();

                collectData.setArguments(bundle)
                getFragmentManager()?.beginTransaction()?.replace(R.id.fragment_holder,collectData)?.commit()



            }

            R.id.operating_the_music -> {

                val bundle = Bundle()

                bundle.putString("activity","operating_the_music")
                val collectData = CollectData();

                collectData.setArguments(bundle)
                getFragmentManager()?.beginTransaction()?.replace(R.id.fragment_holder,collectData)?.commit()

            }
            R.id.talking_on_the_phone -> {
                val bundle = Bundle()

                bundle.putString("activity","talking_ont_the_phone")
                val collectData = CollectData();

                collectData.setArguments(bundle)
                getFragmentManager()?.beginTransaction()?.replace(R.id.fragment_holder,collectData)?.commit()
            }
            R.id.export_all -> {
            }
            R.id.view_data -> {
            }

            else -> {
            }
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SelectLabel.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                SelectLabel().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
            }
    }
}