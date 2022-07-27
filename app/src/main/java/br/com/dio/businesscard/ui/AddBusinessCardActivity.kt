package br.com.dio.businesscard.ui



import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

         var ultimoCaracterDigitado: String = ""

        binding.btnSelecaoBackground.setOnClickListener{

        val intent = Intent(this, BackgroundCardsActivity::class.java)
            resultLauncher.launch(intent)
        }

         binding.edtTelefone.addTextChangedListener(object: TextWatcher {

             override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
               var  tamanhoEditTelefone:Int = binding.edtTelefone.text.toString().length
                 if (tamanhoEditTelefone >1) {
                     ultimoCaracterDigitado = binding.edtTelefone.text.toString().substring(tamanhoEditTelefone -1)
                 }
             }

             override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


                 var tamanhoEditTelefone: Int = binding.edtTelefone.text.toString().length

                 if (tamanhoEditTelefone == 2) {
                     if (!ultimoCaracterDigitado.equals(" ")) {
                         binding.edtTelefone.append(" ")
                     } else {
                         binding.edtTelefone.text?.delete(
                             tamanhoEditTelefone - 1,
                             tamanhoEditTelefone
                         )
                     }
                 } else if (tamanhoEditTelefone == 8) {
                     if (!ultimoCaracterDigitado.equals("-")) {
                         binding.edtTelefone.append("-")
                     } else {
                         binding.edtTelefone.text?.delete(
                             tamanhoEditTelefone - 1,
                             tamanhoEditTelefone
                         )
                     }
                 }
             }

             override fun afterTextChanged(p0: Editable?) {

             }
         })
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
