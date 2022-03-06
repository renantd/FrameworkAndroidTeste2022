package br.framework.com.framework.adapter

import android.content.Context
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
import br.framework.com.framework.model.Item

class CarrinhoAdapter(var item: ArrayList<Item>) : RecyclerView.Adapter<CarrinhoAdapter.RecyclerViewHolder>() {

    class RecyclerViewHolder(view : View ): RecyclerView.ViewHolder(view){
        val name                : TextView = view.findViewById(R.id.product_name)
        val price               : TextView = view.findViewById(R.id.product_price)
        val total               : TextView = view.findViewById(R.id.product_total)
        val produto_quantidade  : TextView = view.findViewById(R.id.product_quantity)
        val foto                : ImageView = view.findViewById(R.id.product_image)
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerViewHolder =
        RecyclerViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_carrinho, viewGroup, false))
    override fun getItemCount(): Int = item.size
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        /* Seta os valores no Layout  */
        holder.name.text                   = item[position].nome
        holder.price.text                  = "R$ ${item[position].preco}"
        holder.total.text                  = "Total:  R$ ${"%.2f".format((item[position].quantidade * item[position].preco))}"

        /* Seta a Imagem no Layout */
        var mContext = holder.itemView.context
        val drawable: Bitmap? = BitmapFactory.decodeResource(mContext.resources, item[position].foto)
        holder.foto.setImageDrawable(BitmapDrawable(mContext.resources,drawable) as Drawable)

        holder.produto_quantidade.text     = "qtde: ${item[position].quantidade}"

    }


}