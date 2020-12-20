package com.example.safedrive.fragments.collect_data

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.safedrive.R
import com.example.safedrive.lib.StartCollectingDataActivity
import kotlinx.android.synthetic.main.collect_data.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CollectDataMenu.newInstance] factory method to
 * create an instance of this fragment.
 */
class CollectData : Fragment() ,View.OnClickListener{
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
        return inflater.inflate(R.layout.collect_data, container, false)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.safe_driving -> {
                val intent = Intent(context, StartCollectingDataActivity::class.java)
                intent.putExtra("activity", "safe_driving")
                startActivity(intent)
            }
            R.id.texting -> {
                val intent = Intent(context, StartCollectingDataActivity::class.java)

                intent.putExtra("activity", "texting")
                startActivity(intent)
            }
            R.id.reaching_behind -> {
                val intent = Intent(context, StartCollectingDataActivity::class.java)

                intent.putExtra("activity", "reaching_behind")
                startActivity(intent)
            }
            R.id.drinking -> {
                val intent = Intent(context, StartCollectingDataActivity::class.java)

                intent.putExtra("activity", "drinking")
                startActivity(intent)
            }
            R.id.operating_the_music -> {
                val intent = Intent(context, StartCollectingDataActivity::class.java)

                intent.putExtra("activity", "operating_the_music")
                startActivity(intent)
            }
            R.id.talking_on_the_phone -> {
                val intent = Intent(context, StartCollectingDataActivity::class.java)

                intent.putExtra("activity", "talking_on_the_phone")
                startActivity(intent)
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
         * @return A new instance of fragment CollectDataMenu.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CollectData().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}