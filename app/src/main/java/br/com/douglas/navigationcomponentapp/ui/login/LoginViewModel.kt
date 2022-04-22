package br.com.douglas.navigationcomponentapp.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.douglas.navigationcomponentapp.R

class LoginViewModel : ViewModel() {

    sealed class AuthenticationState {
        object Authenticated : AuthenticationState()
        object Unauthenticated : AuthenticationState()
        class InvalidAuthentication(val fields: List<Pair<String, Int>>) : AuthenticationState()
    }

    val authenticationStateEvent = MutableLiveData<AuthenticationState>()

    var userName: String = ""

    init {
        refuseAuthentication()
    }

    fun refuseAuthentication() {
        authenticationStateEvent.value = AuthenticationState.Unauthenticated
    }

    fun authentication(userName: String, password: String) {
        if (isValidForm(userName, password)) {
            this.userName = userName
            authenticationStateEvent.value = AuthenticationState.Authenticated
        }
    }

    private fun isValidForm(userName: String, password: String): Boolean {
        val invalidFiels = arrayListOf<Pair<String, Int>>()

        if (userName.isEmpty()) {
            invalidFiels.add(INPUT_USERNAME)
        }
        if (password.isEmpty()) {
            invalidFiels.add(INPUT_PASSWORD)
        }

        if (invalidFiels.isNotEmpty()) {
            authenticationStateEvent.value = AuthenticationState.InvalidAuthentication(invalidFiels)
            return false
        }

        return true
    }

    companion object {
        val INPUT_USERNAME = "INPUT_USERNAME" to R.string.login_input_layout_error_invalid_username
        val INPUT_PASSWORD = "INPUT_PASSWORD" to R.string.login_input_layout_error_invalid_password
    }
}