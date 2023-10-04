package com.grud.mobilbimestre.ui
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.grud.mobilbimestre.R
import com.grud.mobilbimestre.databinding.FragmentLoginBinding
import com.grud.mobilbimestre.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {

    //implementação do view Binding Fragment
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!


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

        clickSplash()
    }



    //função de NAVEGAÇÃO para botão de login
    private fun clickSplash(){
        binding.btnContinuar.setOnClickListener { // Correção na chamada da função
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}