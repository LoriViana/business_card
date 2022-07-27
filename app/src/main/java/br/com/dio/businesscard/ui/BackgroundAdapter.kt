package br.com.dio.businesscard.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.dio.businesscard.data.domain.BackgroundCards
import br.com.dio.businesscard.databinding.ItemBackgroundCardsBinding
import com.squareup.picasso.Picasso


class BackgroundAdapter(private val backgroundList: List<BackgroundCards>, val onClick: (BackgroundCards)-> Unit):
    RecyclerView.Adapter<BackgroundAdapter.BackgroundViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackgroundViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBackgroundCardsBinding.inflate(inflater, parent, false)

        return BackgroundViewHolder(binding)

    }

    override fun onBindViewHolder(holder: BackgroundViewHolder, position: Int) {
        holder.bind(backgroundList[position],onClick)

    }



    override fun getItemCount(): Int = backgroundList.size

    inner class BackgroundViewHolder (private val binding: ItemBackgroundCardsBinding):RecyclerView.ViewHolder(binding.root){


            fun bind(item: BackgroundCards, onClick: (BackgroundCards) -> Unit) {

            Picasso.get().load(item.imagem).fit().into(binding.ivBackground)
            binding.tvDescricao.text = item.descricao


                binding.ivBackground.setOnClickListener {
                    onClick(item)
                }

            }

     }

}

//    fun updateItems (newItems: List<BackgroundCards>){
//        listBackgroundCards = newItems
//        notifyDataSetChanged()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackgroundViewHolder {
//
//         val inflater = LayoutInflater.from(parent.context)
//         val binding = ItemBackgroundCardsBinding.inflate(inflater, parent, false)
//
//        return BackgroundViewHolder(binding)
//
//    }
//
//
//    override fun getItemCount(): Int= listBackgroundCards.size
//
//
//    inner class BackgroundViewHolder( private val binding: ItemBackgroundCardsBinding):RecyclerView.ViewHolder(binding.root) {
//
//
//        fun bind(item: BackgroundCards) {
//            Picasso.get().load(item.imagem).fit().into(binding.ivBackground)
//            binding.tvDescricao.text = item.descricao
//
//            binding.btSelecao.setOnClickListener {
//                val linkImagem = item.imagem.toString()
//                val tipoBackground = TipoBackground(linkImagem, "#FF000000")
//
//                var descricao = item.descricao
//
//                Log.i("INF", linkImagem)
//
//            }
//        }
//    }
//
//
//
//    override fun onBindViewHolder(holder: BackgroundViewHolder, position: Int) {
//
//        holder.bind(listBackgroundCards[position])
//
//    }
//
//}









