package com.example.capitecassessment.presenation.ui.auth.register.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.capitecassessment.databinding.FragmentLoginBinding
import com.example.capitecassessment.presenation.ui.auth.viewmodel.LoginViewModel
import com.example.capitecassessment.presenation.ui.auth.viewmodel.mvi.LoginState
import com.example.capitecassessment.presenation.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.LoginBtn.setOnClickListener {
            validation()
        }
        observeViewModel()

        binding.createnewaccount.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToNewAccountFragment())
        }
    }

    private fun validation() {
        val email = binding.EmailText.text.toString().trim()
        val password = binding.passwordText.text.toString().trim()

        if (email.isEmpty()) {
            binding.userEmail.error = "Email cannot be empty"
            return
        } else {
            binding.userEmail.error = null
        }

        if (password.isEmpty()) {
            binding.userPassword.error = "Password cannot be empty"
            return
        } else {
            binding.userPassword.error = null
        }
        binding.LoginBtn.isEnabled = false
        viewModel.login(email, password)
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.loginState.collect { state ->
                when (state) {
                    is LoginState.Idle -> {

                    }

                    is LoginState.Loading -> {
                        Toast.makeText(requireContext(), "Loading..", Toast.LENGTH_LONG).show()
                    }

                    is LoginState.Success -> {
                        startActivity(Intent(requireActivity(), HomeActivity::class.java))
                        requireActivity().finish()
                    }

                    is LoginState.Error -> {
                        binding.LoginBtn.isEnabled = true
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}


