package br.framework.com.framework.ui.activity.apresentacao

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import br.framework.com.framework.R
import br.framework.com.framework.databinding.ActivityApresentacaoBinding
import br.framework.com.framework.databinding.ActivityMainBinding
import br.framework.com.framework.ui.activity.carrinho.Carrinho
import br.framework.com.framework.ui.activity.login.Login
import br.framework.com.framework.ui.activity.main.MainActivity
import br.framework.com.framework.util.intents.Intents

class Apresentacao : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apresentacao)

        val apresentacao : ActivityApresentacaoBinding = DataBindingUtil.setContentView(this , R.layout.activity_apresentacao)

        //Redireciona
        Intents(this).redirectComDelayAnimacao(Login::class.java , 10)

    }
}