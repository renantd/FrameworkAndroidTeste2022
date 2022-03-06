package br.renandev.com.validacoes.mensagem.erro

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.framework.com.framework.R
import com.google.android.material.snackbar.Snackbar

open class Erro(var context: Context): AppCompatActivity() {

    //companion object private lateinit var context: Context

    fun erro_ToastLong(mensagem: String){ Toast.makeText(context, "Erro: ${mensagem}", Toast.LENGTH_LONG).show(); }
    fun erro_SnackBar(mensagem: String) {

        Snackbar.make(
            (context as Activity).window.decorView,
            mensagem, Snackbar.LENGTH_LONG)
            .setTextColor(Color.BLACK)
            .setActionTextColor(Color.GREEN)
            .setBackgroundTint(Color.WHITE)
            .show()
    }

}