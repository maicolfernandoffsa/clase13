package com.example.sesion13.ui.auth

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sesion13.R

class RecuperarContrasenaFragment : Fragment() {

    companion object {
        fun newInstance() = RecuperarContrasenaFragment()
    }

    private lateinit var viewModel: RecuperarContrasenaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recuperar_contrasena, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecuperarContrasenaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}