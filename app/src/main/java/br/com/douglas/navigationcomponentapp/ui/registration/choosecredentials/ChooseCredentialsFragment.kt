package br.com.douglas.navigationcomponentapp.ui.registration.choosecredentials

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.douglas.navigationcomponentapp.R
import kotlinx.android.synthetic.main.fragment_choose_credentials.*

class ChooseCredentialsFragment : Fragment() {

    private val args: ChooseCredentialsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_credentials, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = args.name
        textChooseCredentialsName.text = getString(R.string.choose_credentials_text_name, name)
    }
}