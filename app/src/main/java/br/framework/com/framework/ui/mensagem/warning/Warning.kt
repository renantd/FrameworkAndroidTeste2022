package br.framework.com.framework.ui.mensagem.warning

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

class Warning(var context: Context): AppCompatActivity() {

    fun warnning_Println(mensagem: String){ println("Aviso : ${mensagem}") }
    fun warningCustomToast(mensagem: String){
        val inflater = layoutInflater
        val layout: View = inflater.inflate(R.layout.warning_toast , findViewById(R.id.lay_warningToast))

        val image = layout.findViewById<View>(R.id.img_warningToast) as ImageView
        image.setImageResource(R.drawable.warning_custom_yellow)
        val text = layout.findViewById<View>(R.id.tv_warningToast) as TextView
        text.text = mensagem

        val toast = Toast(applicationContext)
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
        toast.duration = Toast.LENGTH_LONG
        toast.setView(layout)
        toast.show()
    }
    fun warnning_ToastLong(mensagem: String){ Toast.makeText(context, "Erro: ${mensagem}", Toast.LENGTH_LONG).show(); }
    fun warnning_SnackBar(mensagem: String) {

        Snackbar.make(
            (context as Activity).window.decorView,
            mensagem, Snackbar.LENGTH_LONG)
            .setTextColor(Color.BLACK)
            .setActionTextColor(Color.GREEN)
            .setBackgroundTint(Color.WHITE)
            .show()
    }

}