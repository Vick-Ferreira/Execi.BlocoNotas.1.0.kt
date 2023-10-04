package com.grud.mobilbimestre.ui
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.grud.mobilbimestre.R
import com.grud.mobilbimestre.databinding.FragmentRegistroBinding


class RegistroFragment : Fragment() {

    //implementação do view Binding Fragment
    private var _binding: FragmentRegistroBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegistroBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnCadastrar = view.findViewById<TextView>(R.id.btnCadastrar)
        btnCadastrar.setOnClickListener{
            cadastrarUser()
            findNavController().navigate(R.id.action_registroFragment_to_loginFragment)
        }
    }

    private  fun cadastrarUser(){
        // Obtém o email e senha do usuário do seu formulário de cadastro
        val email = binding.edtEmail.text.toString().trim()


        // Salva o email  nas SharedPreferences

        val sharedPreferences = requireContext().getSharedPreferences("MinhasPreferencias", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("email_cadastrado", email)

        editor.apply()

        Log.d("SharedPreferences", "Email armazenado: $email")

    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}