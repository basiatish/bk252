package ru.max.bk252_with_coroutines.Activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import ru.max.bk252_with_coroutines.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Done_Hw_activity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_details_activity)

        val title = findViewById<TextView>(R.id.name)
        val detail_description = findViewById<TextView>(R.id.description)
        val image = findViewById<ImageView>(R.id.detail_image)
        val date = findViewById<TextView>(R.id.date)

        val name = intent.getStringExtra("NAME")
        val description = intent.getStringExtra("DESCRIPTION")
        val status = intent.getStringExtra("STATUS")
        val begin_time = intent.getStringExtra("BEGIN_TIME")
        val end_time = intent.getStringExtra("END_TIME")


        if (status == "complite")
        {
            val draw = resources.getDrawable(R.drawable.done_hw)
            image.setImageDrawable(draw)
        }
        if (status == "faild")
        {
            val draw = resources.getDrawable(R.drawable.fail_hw)
            image.setImageDrawable(draw)
        }
        if (status != "complite" && status != "faild")
        {
            val draw = resources.getDrawable(R.drawable.progress)
            image.setImageDrawable(draw)
        }

        val bgtime = LocalDate.parse(begin_time, DateTimeFormatter.ISO_DATE)
        val endtime = LocalDate.parse(end_time, DateTimeFormatter.ISO_DATE)

        title.text = name
        date.text = "С  ${bgtime.dayOfMonth}.${bgtime.monthValue}.${bgtime.year}  по  ${endtime.dayOfMonth}.${endtime.monthValue}.${endtime.year}"
        detail_description.text = Html.fromHtml(description)
    }
}