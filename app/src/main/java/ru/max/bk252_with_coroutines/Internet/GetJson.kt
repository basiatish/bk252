package ru.max.bk252_with_coroutines.Internet

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import org.jetbrains.anko.runOnUiThread
import org.json.JSONException
import org.json.JSONObject
import ru.max.bk252_with_coroutines.Data.SaveAndLoadJson
import ru.max.bk252_with_coroutines.Activities.SecondActivity
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import javax.net.ssl.HttpsURLConnection

class GetJson() {

    fun sendPost(
        appcontext: Context,
        nick: Editable?,
        pass: Editable?,
        log: TextInputLayout,
        pas: TextInputLayout,
        btn: Button
    ) {
        val urlPost = "https://bk252.ru/mobile";
        val nickPost = nick.toString()
        val passPost = pass.toString()


        var flag = "false"
        val reqParam = URLEncoder.encode("nick", "UTF-8") + "=" + URLEncoder.encode(
            nickPost,
            "UTF-8"
        ) + "&" + URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(passPost, "UTF-8")
        val mURL = URL(urlPost)

        val sslTrustManager = SSLTrustManager()
        HttpsURLConnection.setDefaultSSLSocketFactory(sslTrustManager.GetSocketFactory())


        with(mURL.openConnection() as HttpURLConnection) {
            requestMethod = "POST"
            setRequestProperty("application/json", "UTF-8");

            val wr = OutputStreamWriter(getOutputStream());
            wr.write(reqParam);
            wr.flush();
            wr.close()

            println("\n POST: $url")
            println("Response : $responseCode")

            BufferedReader(InputStreamReader(inputStream)).use {
                try {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    it.close()
                    val JSonstring = response.toString()
                    val json = JSONObject(JSonstring)
                    val success = json.getString("status")
                    println(success)
                    if (success == "done") {
                        SaveAndLoadJson().Save(appcontext, JSonstring, "JSON", "json")
                        println("done")
                        flag = "true"
                    } else {
                        appcontext.runOnUiThread {
                            Toast.makeText(appcontext, "Incorrect Login or Password", Toast.LENGTH_SHORT).show()
                            log.editText?.text = null
                            pas.editText?.text = null
                            btn.isClickable = true
                            btn.isFocusable = true
                        }
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }


            }
        }
        if (flag == "true") {
            appcontext.startActivity(Intent(appcontext, SecondActivity::class.java))
        }
    }
}