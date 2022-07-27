package br.com.dio.businesscard.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.dio.businesscard.data.TipoBackground
import br.com.dio.businesscard.data.domain.BackgroundCards
import br.com.dio.businesscard.databinding.ActivityBackgroundBinding
import br.com.dio.businesscard.ui.backgroundviewmodel.BackgroundViewModel

class BackgroundCardsActivity : AppCompatActivity() {

    private lateinit var backgroundViewModel: BackgroundViewModel

    private val binding by lazy { ActivityBackgroundBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        backgroundViewModel = ViewModelProvider.NewInstanceFactory().create(BackgroundViewModel::class.java)
        backgroundViewModel.init()

        setupBackgroundRefresh()

        iniObserver()
    }


    private fun iniObserver() {
        binding.srlBackground.isRefreshing = true
        backgroundViewModel.backgroundList.observe(this) { list ->
            if (list.isNotEmpty()) {
                populateList(list)
                binding.srlBackground.isRefreshing = false
            }
        }
    }

    private fun populateList(list: List<BackgroundCards>) {
        binding.rvBackground.apply{
            hasFixedSize()
            adapter = BackgroundAdapter(list){

                val dados = TipoBackground(it.imagem,it.cor) //dados que vem da API

                val data = Intent()
                data.putExtra("RETURN",dados) //DADOS SELECIONADOS PELO USUARIO
                setResult(RESULT_OK,data)
                finish()
            }

        }
    }

      private fun setupBackgroundRefresh() {
      binding.srlBackground.setOnRefreshListener{this.iniObserver()}

          binding.srlBackground.isRefreshing = !binding.srlBackground.isRefreshing
      }

}