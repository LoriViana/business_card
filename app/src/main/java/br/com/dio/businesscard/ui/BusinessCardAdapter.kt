package br.com.dio.businesscard.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.dio.businesscard.data.BusinessCard
import br.com.dio.businesscard.databinding.ItemBusinessCardBinding
import com.squareup.picasso.Picasso


class BusinessCardAdapter :
    ListAdapter<BusinessCard, BusinessCardAdapter.ViewHolder>(DiffCallback()) {

    var listenerShare: (View) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBusinessCardBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

    inner class ViewHolder(
        private val binding: ItemBusinessCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        //Carrregamento das informações no card gravado no banco local

        fun bind(item: BusinessCard) {
           val corfonte: String
           val fundoPersonalizado: String

            //testar o item cor

            if (item.corTexto.toString().isEmpty()){
                corfonte = "#081e54"
            }
            else corfonte= item.corTexto.toString()

            if (item.fundoPersonalizado.toString().isEmpty()) {
                fundoPersonalizado = "https://loriviana.github.io/business-card-api/imagem/01.jpg"
            }
            else fundoPersonalizado= item.fundoPersonalizado.toString()

            binding.tvNome.text = item.nome
            binding.tvNome.setTextColor(Color.parseColor(corfonte))
            binding.tvTelefone.text = item.telefone
            binding.tvTelefone.setTextColor(Color.parseColor(corfonte))
            binding.tvEmail.text = item.email
            binding.tvEmail.setTextColor(Color.parseColor(corfonte))
            binding.tvNomeEmpresa.text = item.empresa
            binding.tvNomeEmpresa.setTextColor(Color.parseColor(corfonte))

            Picasso.get().load(item.fundoPersonalizado).fit().into(binding.ivPersonalizado)


//            try {
//                binding.cdContent.setCardBackgroundColor(Color.parseColor(item.fundoPersonalizado))
//
//            }  catch (e: IllegalArgumentException) {
//                binding.cdContent.setCardBackgroundColor(Color.parseColor("#E53935"))
//            }
            binding.cdContent.setOnClickListener {
                listenerShare(it)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<BusinessCard>() {
    override fun areItemsTheSame(oldItem: BusinessCard, newItem: BusinessCard) = oldItem == newItem
    override fun areContentsTheSame(oldItem: BusinessCard, newItem: BusinessCard) =
        oldItem.id == newItem.id
}