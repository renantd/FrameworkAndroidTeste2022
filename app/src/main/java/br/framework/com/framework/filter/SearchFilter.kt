package br.framework.com.framework.filter

import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import br.framework.com.framework.adapter.ProdutoAdapter
import br.framework.com.framework.databinding.ActivityMainBinding
import br.framework.com.framework.model.Produto

class SearchFilter(var Lista: ArrayList<Produto>, var main: ActivityMainBinding, var adapterCustom: ProdutoAdapter) {

    private var matchedItem: ArrayList<Produto> = arrayListOf()

    /*
     * Author: Renan Costa e Silva
     * Data: 06/03/2022
     * Versão: 1.0
     * Objetivo: Metodo inicializar o SearchView
    */
    fun init(){
        initRecycler()
        performanceProcura()
    }

    /*
     * Author: Renan Costa e Silva
     * Data: 06/03/2022
     * Versão: 1.0
     * Objetivo: Metodo para inicializar o ReciclerView
    */
    fun initRecycler(){
        main.rvProduto.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = ProdutoAdapter(Lista)
        }
    }

    /*
     * Author: Renan Costa e Silva
     * Data: 06/03/2022
     * Versão: 1.0
     * Objetivo: Metodo para inicializar a procura do item
    */
    private fun performanceProcura() {
        main.searchProduto.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                procurar(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                procurar(newText)
                return true
            }
        })
    }

    /*
     * Author: Renan Costa e Silva
     * Data: 06/03/2022
     * Versão: 1.0
     * Objetivo: Metodo para verificar se foi encontrado algum item na pesquisa
    */
    private fun procurar(text: String?) {
        matchedItem = arrayListOf()

        Lista.forEach {
                item ->
            if (text?.let { item.name.contains(it, true) } == true ||
                text?.let { item.descricao.contains(it, true) } == true){
                matchedItem.add(item)
            }
        }
        updateRecyclerView()
    }

    /*
     * Author: Renan Costa e Silva
     * Data: 06/03/2022
     * Versão: 1.0
     * Objetivo: Metodo para atualizar e mostrar os ou o item encontrado
    */
    private fun updateRecyclerView() {
        main.rvProduto.apply {
            adapter = ProdutoAdapter( matchedItem)
            adapterCustom.notifyDataSetChanged()
        }

    }

}