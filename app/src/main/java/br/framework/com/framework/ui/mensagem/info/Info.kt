package br.framework.com.framework.ui.mensagem.info

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

open class Info(var context: Context) {

    //companion object private lateinit var context: Context

    fun msg_Println(mensagem: String){ println("Erro : ${mensagem}") }
    fun msg_LogE( tagIdentificacao: String , mensagem: String){ Log.e("${tagIdentificacao}" , "${mensagem}"); }
    fun msg_ToastLong(mensagem: String){ Toast.makeText(context, "Info: ${mensagem}", Toast.LENGTH_LONG).show(); }
    fun msg_ToastShort(mensagem: String){ Toast.makeText(context, "Info: ${mensagem}", Toast.LENGTH_SHORT).show(); }
    fun msg_SnackBar(mensagem: String) {

        Snackbar.make(
            (context as Activity).window.decorView,
            mensagem, Snackbar.LENGTH_LONG)
            .setTextColor(Color.BLACK)
            .setActionTextColor(Color.GREEN)
            .setBackgroundTint(Color.WHITE)
            .show()
    }

}