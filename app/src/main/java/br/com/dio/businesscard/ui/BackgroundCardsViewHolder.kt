package br.com.dio.businesscard.ui

import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.dio.businesscard.data.domain.BackgroundCards
import br.com.dio.businesscard.databinding.ItemBackgroundCardsBinding
import com.squareup.picasso.Picasso

class BackgroundCardsViewHolder
    ( val binding: ItemBackgroundCardsBinding):RecyclerView.ViewHolder(binding.root)
 {

         fun bind(item: BackgroundCards) {
             Picasso.get().load(item.imagem).fit().into(binding.ivBackground)
             binding.tvDescricao.text= item.descricao

         }



     }

