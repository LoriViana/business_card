package br.com.dio.businesscard.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import br.com.dio.businesscard.ui.BackgroundCardsActivity

class MyContact : ActivityResultContract<Int, String>() {

    override fun createIntent(context: Context, cardId: Int) =
        Intent(context, BackgroundCardsActivity::class.java).apply {
            putExtra(CARD_ID_EXTRA, cardId)
        }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        if (resultCode != Activity.RESULT_OK) {
            return null
        }

        return intent?.getStringExtra(VALUE_EXTRA)
    }

    companion object {
        const val CARD_ID_EXTRA = "card_id"
        const val VALUE_EXTRA = "fundo_card"
    }
}



/*
class MyContract : ActivityResultContract<Int, String>() {
    override fun createIntent(context: Context, cardId: Int) =
        Intent(context, SecondActivity::class.java).apply {
            putExtra(CARD_ID_EXTRA, cardId)
        }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        if (resultCode != Activity.RESULT_OK) {
            return null
        }

        return intent?.getStringExtra(VALUE_EXTRA)
    }

    companion object {
        const val CARD_ID_EXTRA = "card_id"
        const val VALUE_EXTRA = "valor"
    }
}
 */