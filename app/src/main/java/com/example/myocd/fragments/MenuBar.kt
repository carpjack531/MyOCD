package com.example.myocd.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModel
import com.example.myocd.R
import com.example.myocd.pages.MainActivity





private const val TITLE_ARG:String = "Test"
class MenuBar : Fragment() {
    private lateinit var return_home_btn: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu_bar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        return_home_btn = view.findViewById(R.id.return_home_btn)

        return_home_btn.setOnClickListener {
            requireActivity().finish();

        }
        val title = arguments?.getString(TITLE_ARG) ?: "Default Title"

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = title;



    }


    companion object {
        fun newInstance(header: String) = MenuBar().apply {
            arguments = Bundle().apply {
                putString(TITLE_ARG, header)
            }
        }
    }
}