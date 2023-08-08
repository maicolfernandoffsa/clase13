package com.example.sesion13.ui.auth

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavGraph
import androidx.navigation.fragment.findNavController
import com.example.sesion13.databinding.FragmentRegisterBinding
import com.example.sesion13.databinding.FragmentLoginBinding
import com.example.sesion13.util.*
import dagger.hilt.android.AndroidEntryPoint
import com.example.sesion13.R

class Login : Fragment() {

    val TAG: String = "RegisterFragment"
    lateinit var binding: FragmentLoginBinding

    val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observer()
        binding.btnLogin.setOnClickListener{
            if(validation()){
                viewModel.login(
                    correo = binding.editCorreo.text.toString(),
                    contrasena = binding.editPasword.text.toString()
                )
            }
        }
    }

    fun observer(){

        viewModel.login.observe(viewLifecycleOwner){state ->
            when(state){
                is UiState.Loading -> {
                    binding.btnLogin.setText("")
                    binding.loginProgress.show()
                }
                is UiState.Faile -> {
                    binding.btnLogin.setText("Login")
                    binding.loginProgress.hide()
                    toast(state.error)
                }
                is UiState.Success -> {
                    binding.btnLogin.setText("")
                    binding.loginProgress.show()
                    toast(state.data)
                    findNavController().navigate(R.id.action_loginFragment_to_home_navigation)
                }
            }
        }
    }

    fun validation(): Boolean {
        var isValid= true

        if(binding.editCorreo.text.isNullOrEmpty){
            isValid = false
            toast("Campo correo no puede estar vacío")
        }
        else{
            if(!binding.editCorreo.text.toString().isValidEmail()){
                isValid = false
                toast("Correo es inválido")
            }
        }
        if(binding.editPasword.text.isNullOrEmpty){
            isValid = false
            toast("Campo Contraseña no puede estar vacío")
        }
        else{
            if(binding.editPasword.text.toString().length < 6){
                isValid = false
                toast("Campo Contraseña no puede tener menos de 6 caracteres")
            }

        }

        return isValid
    }

    override  fun onStart(){
        super.onStart()
        viewModel.getSession{usuario ->
            if(usuario != null){
                findNavController().navigate(R.id.action_loginFragment_to_home_navigation)
            }

        }
    }

}