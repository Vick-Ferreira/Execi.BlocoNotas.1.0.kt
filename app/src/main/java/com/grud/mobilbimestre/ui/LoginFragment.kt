package com.grud.mobilbimestre.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.grud.mobilbimestre.R
import com.grud.mobilbimestre.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val senhaPadrao = "123456"  //encapsulamento

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnEntrar = view.findViewById<Button>(R.id.btn_Entrar)
        btnEntrar.setOnClickListener {
            entrar(view)
        }

        val btnRegistrar = view.findViewById<TextView>(R.id.btnCriarConta)
        btnRegistrar.setOnClickListener{
        findNavController().navigate(R.id.action_loginFragment_to_registroFragment)
        }



    }
   //função de NAVEGAÇÃO para botão de login
       private fun entrar(view: View) {
      try {
          val email = binding.edtEmail.text.toString().trim()
          val senha = binding.edtSenha.text.toString().trim()

          // Verifica se o email está cadastrado nas SharedPreferences
          val sharedPreferences =
              requireContext().getSharedPreferences("MinhasPreferencias", Context.MODE_PRIVATE)
          val emailCadastrado = sharedPreferences.getString("email_cadastrado", "")

          if (email.isNotEmpty()) {
              if (senha.isNotEmpty()) {
                  if (email == emailCadastrado) { // Verifica se o email está cadastrado
                      if (senha == senhaPadrao) { // Verifica se a senha é igual à senha padrão
                          // Navegue para a próxima tela ou realize a ação desejada
                          findNavController().navigate(R.id.action_loginFragment_to_listaFragment)
                          Toast.makeText(requireContext(), "Conectando...", Toast.LENGTH_SHORT)
                              .show()
                      } else {
                          Toast.makeText(requireContext(), "Senha incorreta!", Toast.LENGTH_SHORT)
                              .show()
                      }
                  } else {
                      Toast.makeText(requireContext(), "Email não cadastrado!", Toast.LENGTH_SHORT)
                          .show()
                  }
              } else {
                  Toast.makeText(requireContext(), "Informe sua senha", Toast.LENGTH_SHORT).show()
              }
          } else {
              Toast.makeText(requireContext(), "Informe seu email", Toast.LENGTH_SHORT).show()
          }
      }catch (e: Exception) {
                   // Tratar exceções aqui
                   e.printStackTrace()
                   Toast.makeText(requireContext(), "Ocorreu um erro: ${e.message}", Toast.LENGTH_SHORT).show()
      }
    }
    //ciclo de vida

    override fun onStop() {
        super.onStop()

        // Limpar os campos de entrada de texto
        binding.edtEmail.text.clear()
        binding.edtSenha.text.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}