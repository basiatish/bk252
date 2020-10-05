package ru.max.bk252_with_coroutines.Activities

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.*
import ru.max.bk252_with_coroutines.Internet.GetJson
import ru.max.bk252_with_coroutines.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appcontext = this@MainActivity

        val log = findViewById<TextInputLayout>(R.id.login)
        val pas = findViewById<TextInputLayout>(R.id.password)
        val btn = findViewById<Button>(R.id.btnPost)

        btn.setOnClickListener {
            btn.isClickable = false
            btn.isFocusable = false
            val nick = log.editText?.text
            val pass = pas.editText?.text
            println("nickname    $nick")
            println("password    $pass")
            GetData(appcontext, nick, pass, log, pas, btn)
            log.clearFocus()
            pas.clearFocus()
        }
    }

    private fun GetData(
        appcontext: Context,
        nick: Editable?,
        pass: Editable?,
        log: TextInputLayout,
        pas: TextInputLayout,
        btn: Button
    ){
        GlobalScope.async {
            GetJson().sendPost(appcontext, nick, pass, log, pas, btn)
        }
    }

    override fun onResume() {
        super.onResume()
        val log = findViewById<TextInputLayout>(R.id.login)
        val pas = findViewById<TextInputLayout>(R.id.password)
        val btn = findViewById<Button>(R.id.btnPost)

        log.editText?.text = null
        pas.editText?.text = null
        btn.isClickable = true
        btn.isFocusable = true

    }
}
