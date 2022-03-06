package br.framework.com.framework.util.intents

import android.R
import android.app.Activity
import android.content.Context
import android.content.Intent
import java.util.*


class Intents(private val context: Context) {

    /*
     * Author: Renan Costa e Silva
     * Data: 06/03/2022
     * Versão: 1.0
     * Objetivo: Metodo para redirecionar para outra activity
    */
    fun <T> redirect(classe: Class<T>) = context.startActivity(Intent(context , classe))

    /*
     * Author: Renan Costa e Silva
     * Data: 06/03/2022
     * Versão: 1.0
     * Objetivo: Metodo para redirecionar com delay
    */
    fun <T> redirectComDelay(classe: Class<T> , delay: Long) {
        Timer().schedule(object : TimerTask() {
            override fun run() {
                context.startActivity(Intent(context , classe))
            }
        }, delay)
    }

    /*
     * Author: Renan Costa e Silva
     * Data: 06/03/2022
     * Versão: 1.0
     * Objetivo: Metodo para redirecionar com delay e animação
    */
    fun <T> redirectComDelayAnimacao(classe: Class<T> , delay: Long){
        Timer().schedule(object : TimerTask() {
            override fun run() {
                context.startActivity(Intent(context , classe))
                (context as Activity).overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }
        }, delay)
    }


}