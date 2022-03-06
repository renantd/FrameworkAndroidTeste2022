package br.framework.com.framework.service.validacao

import android.content.Context
import br.renandev.com.validacoes.mensagem.Mensagem
import br.renandev.com.validacoes.validacao.senha.Senha

class Validation(var context: Context) {
    var senha = Senha()

    /* Validação de campo de EditText */
    fun checkCampoEdit(editText: String?): Boolean {

        var check = if(!editText.isNullOrEmpty() )
        {
            editText.isNotBlank()
        }
        else  false
        return check
    }

    /* Validação de campo de Login */
    fun checkCampoLogin(editText: String?): Boolean {
        var check = if(!editText.isNullOrEmpty() ) {
            if(editText.isNotBlank()) true
            else{
                //Mensagem().dEEP("Login em branco !")
                Mensagem(context).dSSB("Login em branco !")
                false
            }
        }
        else {
            //Mensagem().dEEP("Login vazio !")
            Mensagem(context).dSSB("Login em branco !")
            false
        }
        return check
    }

    /* Validação de Login */
    fun validaSenhaCad(Senha: String): Boolean {

        var check = false
        if(checkCampoEdit(Senha)){
            if(senha.tamanhoSenha(Senha) && senha.existCharEspecial(Senha) && senha.existCharDigito(Senha) &&
                senha.existOneUpper(Senha) && senha.existOneLower(Senha) ){
                check = true
            }else{

                Mensagem(context).dEETL("A senha inválida. Ela deverá ter:\n" +
                        "    Pelo menos 8 caracteres\n" +
                        "1 - Caracter Maiúsculo\n" +
                        "1 - Caracter Minúsculo\n" +
                        "1 - Dígito\n" +
                        "1 - pelo menos um dígito\n" +
                        "1 - Pelo menos um caracter especial ")
            }
        }

        return check
    }
    fun validaSenha(Senha: String): Boolean {

        var check = false
        if(checkCampoEdit(Senha)){
            if(senha.tamanhoSenha(Senha) && senha.existCharEspecial(Senha) && senha.existCharDigito(Senha) &&
                senha.existOneUpper(Senha) && senha.existOneLower(Senha) ){
                check = true
            }else{
                Mensagem(context).dEETL("Senha incorreta. Tente novamente")
            }
        }

        return check
    }

    /* Validação de formulário de Login */
    fun validaFormLogin(login: String , senha: String): Boolean {
        var check = false
        if(checkCampoLogin(login) && validaSenha(senha) ) check = true
        return check
    }
}