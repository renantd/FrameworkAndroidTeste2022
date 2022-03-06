package br.framework.com.framework.ui.activity.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.framework.com.framework.R
import br.framework.com.framework.adapter.ProdutoAdapter
import br.framework.com.framework.databinding.ActivityMainBinding
import br.framework.com.framework.filter.SearchFilter
import br.framework.com.framework.model.Item
import br.framework.com.framework.model.Produto
import br.framework.com.framework.ui.activity.carrinho.Carrinho

class MainActivity : AppCompatActivity() {

    private lateinit var main : ActivityMainBinding
    private          var products = arrayListOf<Produto>()

    private var produto:         ArrayList<Produto> = arrayListOf()
    private var produtoAdapter:  ProdutoAdapter = ProdutoAdapter(produto)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main = DataBindingUtil.setContentView(this , R.layout.activity_main)

        //inicializa a classe de procurade itens
        SearchFilter(initArrayItens() , main , produtoAdapter).init()

    }

    //inicializa a lista de itens
    fun initArrayItens(): ArrayList<Produto>{

        products.add( Produto("Maçã Argentina"    , 4.39 , R.drawable.maca    , "Fonte de fibras e antioxidantes."))
        products.add( Produto("Pêra"              , 3.49 , R.drawable.pera    , "Baixas calorias, rica em vitaminas, sais minerais e fonte de fibras."))
        products.add( Produto("Abacaxi (Graudo)"  , 9.49 , R.drawable.abacaxi , "Rico em vitamina C. Repleto de antioxidantes. Ajuda perder peso."))
        products.add( Produto("Manga Palmer"      , 3.79 , R.drawable.manga   , "Doce e nutritiva. Rica em Vitamina C e fonte de cálcio."))

        return products
    }

}