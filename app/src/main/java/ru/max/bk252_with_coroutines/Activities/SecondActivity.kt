package ru.max.bk252_with_coroutines.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_second.*
import ru.max.bk252_with_coroutines.Fragments.DoneHomeworkFragment
import ru.max.bk252_with_coroutines.R
import ru.max.bk252_with_coroutines.Data.UserData
import ru.max.bk252_with_coroutines.Fragments.FailedHomeworkFragment
import ru.max.bk252_with_coroutines.Fragments.InProgressHomeworkFragment

class SecondActivity : AppCompatActivity() {

    private var listTasks = ArrayList<UserData>()

    var len: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        if (savedInstanceState == null) {
            val fragment = DoneHomeworkFragment()
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container, fragment,
                fragment.javaClass.simpleName).commit()
        }

        bottomNavigationView.inflateMenu(R.menu.bottom_navigation_menu)

        val appcontext = this@SecondActivity

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.done_menu -> {

                    val Fragment = DoneHomeworkFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                        Fragment, Fragment.javaClass.simpleName).commit()

                    true
                }
                R.id.in_progress_menu -> {

                    val Fragment = InProgressHomeworkFragment()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fragment_container,
                        Fragment, Fragment.javaClass.simpleName).commit()

                    true
                }
                R.id.failed_menu -> {

                    val Fragment = FailedHomeworkFragment()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fragment_container,
                        Fragment, Fragment.javaClass.simpleName).commit()

                    true
                }
                else -> false
            }
        }
    }
}