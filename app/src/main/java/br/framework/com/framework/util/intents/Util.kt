package br.framework.com.framework.util.intents

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


class Util {

    /*
     * Author: Renan Costa e Silva
     * Data: 06/03/2022
     * Versão: 1.0
     * Objetivo: Esconder o teclado , quando a tela é carregada
    */
    fun esconderTeclado(context: Context) {
        var view: View = (context as Activity).getWindow().decorView
        view.clearFocus();
    }

}