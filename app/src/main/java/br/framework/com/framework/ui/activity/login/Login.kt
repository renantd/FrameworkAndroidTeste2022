package br.framework.com.framework.ui.activity.login

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.framework.com.framework.R
import br.framework.com.framework.databinding.ActivityLoginBinding
import br.framework.com.framework.service.validacao.Validation
import br.framework.com.framework.ui.activity.main.MainActivity
import br.framework.com.framework.util.intents.Intents
import br.framework.com.framework.util.intents.Util
import br.renandev.com.validacoes.mensagem.Mensagem


class Login : AppCompatActivity() {

    private lateinit var login : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Databinding e ViewModel
        login = DataBindingUtil.setContentView(this , R.layout.activity_login)
        val viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        // Botão do evento para Logar
        login.btnLogar.setOnClickListener {
            viewModel.checkLogin(this, login.campoLogin.editText?.text.toString() , login.campoSenha.editText?.text.toString())
        }

         /* Metódo para da classe útil */
        Util().esconderTeclado(this )

    }

}