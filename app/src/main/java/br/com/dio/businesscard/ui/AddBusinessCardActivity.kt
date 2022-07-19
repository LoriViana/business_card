package br.com.dio.businesscard.ui


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.dio.businesscard.App
import br.com.dio.businesscard.R
import br.com.dio.businesscard.data.BusinessCard
import br.com.dio.businesscard.data.TipoBackground
import br.com.dio.businesscard.databinding.ActivityAddBusinessCardBinding
import br.com.dio.businesscard.ui.BackgroundCardsActivity.Companion.NAME


class AddBusinessCardActivity : AppCompatActivity() {


    public lateinit var fundocartao:String

    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater) }

    private val responseLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ activityResult ->
        if (activityResult.resultCode == RESULT_OK){

            val name = activityResult.data?.getStringExtra(NAME).orEmpty()
            Toast.makeText(this,name,Toast.LENGTH_SHORT).show()
        }else{
        Toast.makeText(this,"NO :(",Toast.LENGTH_SHORT).show()
    }

    }

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)    }


        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

       // binding.btnSelecaoBackground.setOnClickListener {
       // val intent = Intent(this, BackgroundCardsActivity::class.java)
       // responseLauncher.launch(intent)

       //     }

        insertListeners()
    }


    private fun insertListeners() {
        var corpadrao: String

        binding.btnSelecaoBackground.setOnClickListener{

            val intent = Intent(this, BackgroundCardsActivity::class.java)
            startActivity(intent)
        }


        binding.btnConfirm.setOnClickListener {

           if (binding.tilCor.editText?.text.toString().isEmpty()) {
               corpadrao= "#FF03DAC5"

           } else {
               corpadrao= binding.tilCor.editText?.text.toString()
           }
            //recebendo valor que o usário digitar na tela de adição de card
            //criação da entidade

            val businessCard = BusinessCard(
                nome = binding.tilNome.editText?.text.toString(),
                empresa = binding.tilEmpresa.editText?.text.toString(),
                telefone = binding.tilTelefone.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),
              fundoPersonalizado =corpadrao
           )

            mainViewModel.insert(businessCard)
            Toast.makeText(this, R.string.label_show_success, Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.btnClose.setOnClickListener {
            finish()
        }
    }
}
