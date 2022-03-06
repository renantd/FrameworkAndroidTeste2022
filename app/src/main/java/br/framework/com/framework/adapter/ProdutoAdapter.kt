package br.framework.com.framework.adapter

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import br.framework.com.framework.R
import br.framework.com.framework.adapter.ProdutoAdapter.RecyclerViewHolder
import br.framework.com.framework.model.Item
import br.framework.com.framework.model.Produto
import br.framework.com.framework.ui.activity.carrinho.Carrinho
import br.renandev.com.validacoes.mensagem.Mensagem


class ProdutoAdapter(var produtos: ArrayList<Produto> ) : RecyclerView.Adapter<RecyclerViewHolder>() {

    private var itens = arrayListOf<Item>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.produto_item, viewGroup , false))
    }

    override fun getItemCount(): Int = produtos.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        /* Seta os valores no Layout  */
        holder.name.text      = produtos[position].name
        holder.price.text     = "R$ ${produtos[position].price} (kg)"
        holder.descricao.text = produtos[position].descricao

        /* Pega o contexto pelo itemView Holder */
        var mContext = holder.itemView.context

        /* Seta a Imagem no Layout */
        val drawable: Bitmap? = BitmapFactory.decodeResource(mContext.resources, produtos[position].foto)
        holder.foto.setImageDrawable(BitmapDrawable(mContext.resources,drawable) as Drawable)

        /* Contador de quantidade */
        var count = 1

        /* Evento de adicionar +1 na quantidade */
        holder.btnAdd.setOnClickListener {
            holder.Edti_Qtde.setText((++count).toString())
        }

        /* Evento subtrair 1 na quantidade */
        holder.btnMinus.setOnClickListener {

            /* Valor mínimo de 1 */
            if(count == 1) {
                count = 1
                holder.Edti_Qtde.setText(count.toString())
            }
            else holder.Edti_Qtde.setText((--count).toString())

        }

        /* Evento redirecionar para o carrinho de compras */
        holder.btnCheckout.setOnClickListener {

            Mensagem(mContext).dSSB("Carregando..")
            itens.forEach {

                /* Pega os valores para a próxima tela */
                var i = Intent(mContext , Carrinho::class.java)
                i.putExtra("nome"        , it.nome)
                i.putExtra("quantidade"  , it.quantidade)
                i.putExtra("foto"        , it.foto)
                i.putExtra("total"       ,  "%.2f".format((it.quantidade * it.preco)))
                i.putExtra("preco"       , it.preco)
                mContext.startActivity(i)

            }


        }

        /* Evento para comprar uma fruta e adicionar na classe */
        holder.btnComprar.setOnClickListener {

          itens.add(  Item("${produtos[position].name}"  , produtos[position].foto , produtos[position].price , "%.2f".format(count * produtos[position].price) , count))

        }

    }

    class RecyclerViewHolder(view : View ): RecyclerView.ViewHolder(view) {

        val name        : TextView      = view.findViewById(R.id.product_name)
        val price       : TextView      = view.findViewById(R.id.product_price)
        val descricao   : TextView      = view.findViewById(R.id.product_description)
        val foto        : ImageView     = view.findViewById(R.id.product_image)

        val Edti_Qtde    : EditText      = view.findViewById(R.id.edit_Qtde)
        val btnAdd       : ImageButton   = view.findViewById(R.id.btn_add)
        val btnMinus     : ImageButton   = view.findViewById(R.id.btn_minus)
        val btnCheckout  : ImageButton   = view.findViewById(R.id.btn_Checkout)
        val btnComprar   : Button        = view.findViewById(R.id.btn_Comprar)

    }

}