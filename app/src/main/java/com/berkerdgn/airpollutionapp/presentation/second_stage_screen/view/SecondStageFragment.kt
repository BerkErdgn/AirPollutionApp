package com.berkerdgn.airpollutionapp.presentation.second_stage_screen.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.berkerdgn.airpollutionapp.R
import com.berkerdgn.airpollutionapp.databinding.FragmentSecondStageBinding
import com.berkerdgn.airpollutionapp.presentation.list_screen.view.view.ListFragment
import com.berkerdgn.airpollutionapp.presentation.save_screen.view.view.SavedFragment


class SecondStageFragment (): Fragment() {

    private var _binding: FragmentSecondStageBinding?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSecondStageBinding.inflate(inflater,container,false)
        replaceFragment(ListFragment())
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.navBar.setOnItemSelectedListener {
            when (it){
                R.id.home -> replaceFragment(ListFragment())
                R.id.save -> replaceFragment(SavedFragment())
            }
            true
        }

    }


    private fun replaceFragment (fragment: Fragment){
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransection = fragmentManager.beginTransaction()
        fragmentTransection.replace(R.id.secondFragmentContainerView, fragment)
        fragmentTransection.commit()
    }

}