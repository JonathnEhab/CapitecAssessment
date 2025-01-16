package com.example.capitecassessment.presenation.ui.auth.register.newaccount

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
import com.example.capitecassessment.databinding.FragmentNewAccountBinding
import com.example.capitecassessment.presenation.ui.auth.viewmodel.CreateNewAccountViewModel
import com.example.capitecassessment.presenation.ui.auth.viewmodel.mvi.NewAccountState
import com.example.capitecassessment.presenation.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewAccountFragment : Fragment() {
    private var _binding: FragmentNewAccountBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CreateNewAccountViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.Login.setOnClickListener {
            findNavController().navigate(NewAccountFragmentDirections.actionNewAccountFragmentToLoginFragment())
        }
        binding.createNewAccoountBtn.setOnClickListener {
            validation()
        }
        observeViewModel()
    }


    private fun validation() {
        val name = binding.NameText.text.toString().trim()
        val email = binding.EmailText.text.toString().trim()
        val password = binding.passwordText.text.toString().trim()
        val confirmPassword = binding.reenterpasswordText.text.toString().trim()

        if (name.isEmpty()) {
            Toast.makeText(requireContext(), "Name cannot be empty", Toast.LENGTH_SHORT).show()
            binding.username.isErrorEnabled = true
            binding.username.error = ""
            return
        } else {
            binding.username.error = null
        }
        if (email.isEmpty()) {
            Toast.makeText(requireContext(), "Email cannot be empty", Toast.LENGTH_SHORT).show()
            binding.userEmail.error = ""
            binding.username.isErrorEnabled = true
            return
        } else {
            binding.userEmail.error = null
        }

        if (password.isEmpty()) {
            Toast.makeText(requireContext(), "Password cannot be empty", Toast.LENGTH_SHORT)
                .show()
            binding.userPassword.error = ""
            binding.username.isErrorEnabled = true
            return
        } else {
            binding.userPassword.error = null
        }
        if (confirmPassword.isEmpty()) {
            binding.username.isErrorEnabled = true
            Toast.makeText(
                requireContext(), "Confirm Password cannot be empty", Toast.LENGTH_SHORT
            ).show()
            binding.reenamteruserPassword.error = ""
            return
        } else {
            binding.reenamteruserPassword.error = null
        }
        if (password.length < 7) {
            binding.username.isErrorEnabled = true
            Toast.makeText(
                requireContext(), "Password must be at least 7 characters", Toast.LENGTH_SHORT
            ).show()
            binding.userPassword.error = ""
            return
        } else {
            binding.userPassword.error = null
        }
        if (password != confirmPassword) {
            binding.username.isErrorEnabled = true
            Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_SHORT)
                .show()
            binding.reenamteruserPassword.error = ""
            return
        } else {
            binding.reenamteruserPassword.error = null
        }
        binding.createNewAccoountBtn.isEnabled = false
        viewModel.CreateNewAccount(email, password)


    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.newAccount.collect { state ->
                when (state) {
                    is NewAccountState.Idle -> {

                    }

                    is NewAccountState.Loading -> {
                        Toast.makeText(requireContext(), "Loading..", Toast.LENGTH_LONG).show()
                    }


                    is NewAccountState.Success -> {
                        startActivity(Intent(requireActivity(), HomeActivity::class.java))
                        requireActivity().finish()

                    }

                    is NewAccountState.Error -> {
                        binding.createNewAccoountBtn.isEnabled = true
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