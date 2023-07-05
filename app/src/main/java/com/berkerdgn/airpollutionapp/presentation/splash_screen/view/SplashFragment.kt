package com.berkerdgn.airpollutionapp.presentation.splash_screen.view

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.berkerdgn.airpollutionapp.R
import com.berkerdgn.airpollutionapp.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {


    private var _binding : FragmentSplashBinding?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTimer(view)
    }


    private fun setTimer (view: View){
        val timer = object : CountDownTimer(4000,1000){
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                val actionOneToSecond = SplashFragmentDirections.actionSplashFragmentToSecondStageFragment()
                Navigation.findNavController(view).navigate(actionOneToSecond)
            }

        }

        timer.start()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}