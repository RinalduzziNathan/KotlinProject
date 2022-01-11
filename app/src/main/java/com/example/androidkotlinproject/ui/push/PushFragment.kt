package com.example.androidkotlinproject.ui.push

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidkotlinproject.R
import com.example.androidkotlinproject.data.prefs.Prefs
import com.example.androidkotlinproject.databinding.FragmentHomeBinding

class PushFragment : Fragment() {

    private lateinit var pushViewModel: PushViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

   override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pushViewModel =
            ViewModelProvider(this).get(PushViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        pushViewModel.name.observe(viewLifecycleOwner, Observer {
            textView.text = pushViewModel.getName()
        })

        val textHome : TextView = binding.textHome2
        pushViewModel.description.observe(viewLifecycleOwner, Observer {
            textHome.text = it
        })
        val btn : Button = binding.push
        btn.setOnClickListener {
            val prefs = context?.let { it1 -> Prefs(it1) }
            if (prefs != null) {

                prefs.myStringArray.forEach {
                textView.text = textView.text.toString() +" et "+ "$it"

                }


            }
        }
        return root


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}