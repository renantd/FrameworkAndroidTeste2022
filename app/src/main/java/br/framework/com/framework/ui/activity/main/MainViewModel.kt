package br.framework.com.framework.ui.activity.main

import android.content.ClipData
import androidx.lifecycle.ViewModel
import br.framework.com.framework.model.Item

class MainViewModel: ViewModel() {

    var count = 0

    fun upadteItemCarrinho(): Int{
        //count = Item(1 ,"" , 0 , 0.0 , 0).quantidade
        return  4
    }


}