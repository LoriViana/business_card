package br.com.dio.businesscard.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.dio.businesscard.data.TipoBackground
import br.com.dio.businesscard.data.domain.BackgroundCards
import br.com.dio.businesscard.data.remote.BusinessCardApi
import br.com.dio.businesscard.databinding.ActivityBackgroundBinding
import br.com.dio.businesscard.databinding.ItemBackgroundCardsBinding
import br.com.dio.businesscard.util.MyContact
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BackgroundCardsActivity : AppCompatActivity() {

    companion object{
        const val NAME = "NAME"

    }

    private val binding by lazy { ActivityBackgroundBinding.inflate(layoutInflater) }
    private val bindingI by lazy { ItemBackgroundCardsBinding.inflate(layoutInflater) }
    private lateinit var cards : List<BackgroundCards>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupBackgroundList()
        setupBackgroundRefresh()
        backListener()
        btnSelecao()

    }

    private fun btnSelecao() {

            bindingI.btSelecao.setOnClickListener{
            Log.i("INF","CLICOU NO ITEM")
            val intent = Intent()
            intent.putExtra(NAME,"CLICOU NO RECYCLERVIEW")
            setResult(RESULT_OK,intent)
            finish()
        }

    }

   fun backListener() {
        bindingI.btnVoltar.setOnClickListener {
            finish()
        }
    }

    private fun setupBackgroundRefresh() {
      binding.srlBackground.setOnRefreshListener{findBackgroundFromApi(binding.rvBackground)}
    }


    private fun setupBackgroundList() {


        var recyclerView: RecyclerView = binding.rvBackground
        recyclerView.setHasFixedSize(true)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager= (LinearLayoutManager(this))

        findBackgroundFromApi(recyclerView)
    }

    private fun findBackgroundFromApi(recyclerView: RecyclerView) {
        binding.srlBackground.isRefreshing = true

        val retrofitClient =
            NetworkUtils.getRetrofitInstance("https://loriviana.github.io/business-card-api/")
        val endpoint = retrofitClient.create(BusinessCardApi::class.java)

        endpoint.getBackgroundCards().enqueue(object : Callback<List<BackgroundCards>> {
            override fun onResponse(
                call: Call<List<BackgroundCards>>,
                response: Response<List<BackgroundCards>>
            ) {
                if (response.isSuccessful) {

                  cards= response.body()!!
                    //updateItems(cards)
                    recyclerView.adapter = BackgroundAdapter(cards)

                } else {
                    Log.e("Fail", "erro na execução")
                }
                binding.srlBackground.isRefreshing = false
            }



            override fun onFailure(call: Call<List<BackgroundCards>>, t: Throwable) {
                Log.e("Fail", "erro na execução")
                binding.srlBackground.isRefreshing = false
            }
        })
    }

}