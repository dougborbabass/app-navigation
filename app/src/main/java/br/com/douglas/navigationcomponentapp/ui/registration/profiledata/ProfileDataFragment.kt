package br.com.douglas.navigationcomponentapp.ui.registration.profiledata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.douglas.navigationcomponentapp.R
import br.com.douglas.navigationcomponentapp.ui.registration.RegistrationViewModel
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_profile_data.*

class ProfileDataFragment : Fragment() {

    private val registrationViewModel: RegistrationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val validationFields = initValidationFields()
        listenToRegistrationViewModelEvents(validationFields)

        buttonProfileDataNext.setOnClickListener {
            val name = inputProfileDataName.text.toString()
            val profileBio = inputProfileDataBio.text.toString()
            registrationViewModel.collectProfileData(name, profileBio)
        }
    }

    fun initValidationFields() = mapOf(
        RegistrationViewModel.INPUT_NAME.first to inputLayoutProfileDataName,
        RegistrationViewModel.INPUT_BIO.first to inputLayoutProfileDataBio
    )

    private fun listenToRegistrationViewModelEvents(validationsFields: Map<String, TextInputLayout>) {
        registrationViewModel.registrationStateEvent.observe(
            viewLifecycleOwner,
            Observer { registrationState ->
                when (registrationState) {
                    is RegistrationViewModel.RegistrationState.CollectCredentials -> {
                        val name = inputProfileDataName.text.toString()
                        val directions = ProfileDataFragmentDirections
                            .actionProfileDataFragmentToChooseCredentialsFragment(name)

                        findNavController().navigate(directions)
                    }
                    is RegistrationViewModel.RegistrationState.InvalidProfileData -> {
                        registrationState.fields.forEach { fieldError ->
                            validationsFields[fieldError.first]?.error =
                                getString(fieldError.second)
                        }
                    }
                }
            })
    }
}