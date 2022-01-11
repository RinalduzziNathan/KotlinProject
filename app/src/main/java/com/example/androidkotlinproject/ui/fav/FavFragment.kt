package com.example.androidkotlinproject.ui.fav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidkotlinproject.data.prefs.Prefs
import com.example.androidkotlinproject.databinding.FragmentNotificationsBinding
import com.example.androidkotlinproject.ui.marvel.MarvelViewModel

class FavFragment : Fragment() {

    private lateinit var favViewModel: MarvelViewModel
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favViewModel =
            ViewModelProvider(requireActivity()).get(MarvelViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}