package br.com.dio.businesscard.ui



import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.dio.businesscard.App
import br.com.dio.businesscard.R
import br.com.dio.businesscard.data.BusinessCard
import br.com.dio.businesscard.data.TipoBackground
import br.com.dio.businesscard.databinding.ActivityAddBusinessCardBinding
import com.squareup.picasso.Picasso


class AddBusinessCardActivity : AppCompatActivity() {


    private  var fundocartao="https://loriviana.github.io/business-card-api/imagem/01.jpg"
    private var corFonte= "#081e54"

    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater) }


    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)    }


        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

            resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
                if(result.resultCode == RESULT_OK){
                    result.data?.apply {

                        var backgroundTipo= this.getSerializableExtra("RETURN") as TipoBackground

                        fundocartao = backgroundTipo.link.toString()
                        corFonte=  backgroundTipo.cor.toString()
                    }
                }
            }
            insertListeners()
    }

     override fun onResume() {
        super.onResume()

        binding.btnSelecaoBackground.setOnClickListener{

        val intent = Intent(this, BackgroundCardsActivity::class.java)
            resultLauncher.launch(intent)
        }
    }



    private fun insertListeners() {

        binding.btnConfirm.setOnClickListener {


            // Dados recebidos da digitação pelo usuário

            val businessCard = BusinessCard(
                nome = binding.tilNome.editText?.text.toString(),
                empresa = binding.tilEmpresa.editText?.text.toString(),
                telefone = binding.tilTelefone.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),

                fundoPersonalizado =fundocartao,
                corTexto = corFonte
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
