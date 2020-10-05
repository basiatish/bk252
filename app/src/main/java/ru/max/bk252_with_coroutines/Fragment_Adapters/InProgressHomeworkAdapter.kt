package ru.max.bk252_with_coroutines.Fragment_Adapters

import android.app.PendingIntent.getActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import ru.max.bk252_with_coroutines.Data.UserData
import ru.max.bk252_with_coroutines.Fragments.InProgressHomeworkFragment
import ru.max.bk252_with_coroutines.R

class InProgressHomeworkAdapter(private val mDataList: ArrayList<UserData>, val clickListener: InProgressItemClickListener)
    : RecyclerView.Adapter<InProgressHomeworkAdapter.InProgressViewHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InProgressViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        val picture = view.findViewById<ImageView>(R.id.picture)
        val draw = view.resources.getDrawable(R.drawable.progress)
        picture.setImageDrawable(draw)
        return InProgressViewHolder(view)
    }

    override fun onBindViewHolder(holder: InProgressViewHolder, position: Int) {
        val item = mDataList.get(position)

        holder.title.text = mDataList[position].name
        holder.status.text = "В процессе"
        holder.itemView.setOnClickListener(View.OnClickListener {
            clickListener.onItemClick(item)
        })
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    inner class InProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var title: TextView
        internal var status: TextView

        init {
            title = itemView.findViewById<View>(R.id.tasktitle) as TextView
            status = itemView.findViewById<View>(R.id.status) as TextView
        }
    }
}

interface InProgressItemClickListener {
    fun onItemClick(item: UserData)
}