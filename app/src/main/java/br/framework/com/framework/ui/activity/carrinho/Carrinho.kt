package br.framework.com.framework.ui.activity.carrinho

import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import br.framework.com.framework.R
import br.framework.com.framework.adapter.CarrinhoAdapter
import br.framework.com.framework.arquivo.PDF
import br.framework.com.framework.databinding.ActivityCarrinhoBinding
import br.framework.com.framework.model.Item
import br.renandev.com.validacoes.mensagem.Mensagem
import com.google.android.material.snackbar.Snackbar
import com.itextpdf.text.*
import com.itextpdf.text.pdf.PdfWriter
import java.io.*
import java.text.DecimalFormat


class Carrinho : AppCompatActivity() {

    private lateinit var carrinho : ActivityCarrinhoBinding
    private val STORAGE_CODE = 1001
    private var itens = arrayListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrinho)

        carrinho = DataBindingUtil.setContentView(this , R.layout.activity_carrinho)

        // Seta o valor toal da compra na tela
        var total = intent.getStringExtra("total" ).toString()
        carrinho.tvTotalProdutos.text = "R$ ${total}"

        //inicializa a lista de itens
        initLista()

        //inicializa a reciclerview
        initRecycler()

        carrinho.btnFinalizar.setOnClickListener {
          checkPermission(itens)
        }

    }

    /*
     * Author: Renan Costa e Silva
     * Data: 06/03/2022
     * Versão: 1.0
     * Objetivo: Metodo para inicializar o ReciclerView
    */
    fun initRecycler() {
        carrinho.rvCarrinho.apply {
            layoutManager = LinearLayoutManager(this@Carrinho)
            adapter = CarrinhoAdapter(itens)
        }
    }

    /*
     * Author: Renan Costa e Silva
     * Data: 06/03/2022
     * Versão: 1.0
     * Objetivo: Metodo para inicializar a lista de itens
    */
    fun initLista(): ArrayList<Item>{
        //var itens = arrayListOf<Item>()
        itens.add( Item(
            intent.getStringExtra("nome").toString() ,
            intent.getIntExtra("foto" , 0) ,
            intent.getDoubleExtra("preco" , 0.0),
            intent.getStringExtra("total" ).toString(),
            intent.getIntExtra("quantidade" , 0)))

        return itens
    }

    /*
     * Author: Renan Costa e Silva
     * Data: 06/03/2022
     * Versão: 1.0
     * Objetivo: Metodo savar os dados no PDF
    */
    private fun savePDF(itens: ArrayList<Item>) {

        //destino do arquivo
        var pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()
        var file = File(pdfPath , "Comprovante.pdf")

        Mensagem(this).dESB("Comprovante gerando com sucesso em: ${file.absoluteFile}")
        PDF(this ).gerarNovoDocumento(file , file.absoluteFile , itens)

    }

    /*
     * Author: Renan Costa e Silva
     * Data: 06/03/2022
     * Versão: 1.0
     * Objetivo: Metodo para checar a permissão e salvar
    */
    fun checkPermission(itens: ArrayList<Item>) {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){

                val permission = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                requestPermissions(permission, STORAGE_CODE)

            }else{
                savePDF(itens)
            }
        }
    }

}