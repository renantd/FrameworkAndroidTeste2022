package br.framework.com.framework.ui.activity.login

import android.content.Context
import androidx.lifecycle.ViewModel
import br.framework.com.framework.service.validacao.Validation
import br.framework.com.framework.ui.activity.main.MainActivity
import br.framework.com.framework.util.intents.Intents
import br.renandev.com.validacoes.mensagem.Mensagem

class LoginViewModel: ViewModel() {

    // validação de ViewModel
    fun checkLogin(context: Context , login: String , senha: String){
        if(Validation(context).validaFormLogin(login , senha)){

            // Mostra mensagem de sucesso
            Mensagem(context).dSSB("Logado com Sucesso !")

            // Redireciona para outra tela
            Intents(context).redirectComDelay(MainActivity::class.java , 1200)

        } else Mensagem(context).dEETL("Login inválido")
    }

}