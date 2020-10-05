package ru.max.bk252_with_coroutines.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject
import ru.max.bk252_with_coroutines.*
import ru.max.bk252_with_coroutines.Activities.Done_Hw_activity
import ru.max.bk252_with_coroutines.Data.SaveAndLoadJson
import ru.max.bk252_with_coroutines.Data.UserData
import ru.max.bk252_with_coroutines.Fragment_Adapters.Adapter
import ru.max.bk252_with_coroutines.Fragment_Adapters.ItemClickListener


class DoneHomeworkFragment : Fragment(), ItemClickListener {

    private var recyclerView: RecyclerView? = null
    private var listTasks = ArrayList<UserData>()

    override fun onItemClick(item: UserData) {
        val intent = Intent(view?.context, Done_Hw_activity::class.java)
        intent.putExtra("DESCRIPTION", item.description)
        intent.putExtra("NAME", item.name)
        intent.putExtra("STATUS", item.status)
        intent.putExtra("BEGIN_TIME", item.begin_date)
        intent.putExtra("END_TIME", item.end_date)
        view?.context?.startActivity(intent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.done_homework_fragment, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        initView()
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getList()
    }

    private fun getList(){
        val jsoN = this.context?.let { SaveAndLoadJson().Load(it, "JSON", "json") }
        val json = JSONObject(jsoN)
        val SubjectStr = json.getString("subjects")
        val SubjectArray = JSONArray(SubjectStr)
        val GetTasks = SubjectArray.get(0).toString()
        val ItemObject = JSONObject(GetTasks)
        val TasksStr = ItemObject.getString("tasks")
        val TaskArray = JSONArray(TasksStr)
        for (i in 0 until TaskArray.length()) {
            val str = TaskArray.getString(i)
            val Object = JSONObject(str)
            if (Object.getString("status") == "faild")
            {
                continue
            }
            listTasks.add(
                UserData(
                    i,
                    Object.getString("begin_date"),
                    Object.getString("description"),
                    Object.getString("end_date"),
                    Object.getString("name"),
                    Object.getString("short"),
                    Object.getString("status")
                )
            )

        }
    }

    private fun initView(){
        val layoutManager = GridLayoutManager(this.context, 1)
        recyclerView!!.layoutManager = layoutManager
        val CardAdapter = Adapter(listTasks, this)
        recyclerView!!.adapter = CardAdapter
    }
}