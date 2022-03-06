package br.renandev.com.validacoes.mensagem

import android.content.Context
import br.framework.com.framework.ui.mensagem.info.Info
import br.framework.com.framework.ui.mensagem.warning.Warning
import br.renandev.com.validacoes.mensagem.erro.Erro
import br.renandev.com.validacoes.mensagem.sucess.Sucess

class Mensagem(var context: Context) {


    //displayErrorEdit_Toast
    fun dEETL(mensagemDeErro: String) { Erro(context).erro_ToastLong( mensagemDeErro) }
    //displayErrorSnackBar
    fun dESB(mensagemDeErro: String) { Erro(context).erro_SnackBar("mensagemDeErro")}


    //displayWarningEdit_Println
    fun dWEP(mensagemDeWarning: String) { Warning(context).warningCustomToast(mensagemDeWarning) }


    //displaySucessEdit_Println
    fun dSSB(mensagemDeSucess: String) { Sucess(context).sucess_SnackBar(mensagemDeSucess) }

    // Info
    fun dIToast(mensagemDeSucess: String) { Info(context).msg_ToastLong(mensagemDeSucess) }
    fun dISnack(mensagemDeSucess: String) { Info(context).msg_SnackBar(mensagemDeSucess) }

}