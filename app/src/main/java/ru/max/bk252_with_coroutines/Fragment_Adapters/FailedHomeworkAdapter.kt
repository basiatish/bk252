package ru.max.bk252_with_coroutines.Fragment_Adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.max.bk252_with_coroutines.Data.UserData
import ru.max.bk252_with_coroutines.R

class FailedHomeworkAdapter(private val mDataList: ArrayList<UserData>, val clickListener: FailedItemClickListener)
    : RecyclerView.Adapter<FailedHomeworkAdapter.FailedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FailedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        val picture = view.findViewById<ImageView>(R.id.picture)
        val draw = view.resources.getDrawable(R.drawable.fail_hw)
        picture.setImageDrawable(draw)
        return FailedViewHolder(view)
    }

    override fun onBindViewHolder(holder: FailedViewHolder, position: Int) {
        val item = mDataList.get(position)
        holder.title.text = mDataList[position].name
        holder.status.setTextColor(Color.rgb(220, 0, 0))
        holder.status.text = "Провалено"
        holder.itemView.setOnClickListener(View.OnClickListener {
            clickListener.onItemClick(item)
        })
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    inner class FailedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var title: TextView
        internal var status: TextView

        init {
            title = itemView.findViewById<View>(R.id.tasktitle) as TextView
            status = itemView.findViewById<View>(R.id.status) as TextView
        }
    }
}

interface FailedItemClickListener {
    fun onItemClick(item: UserData)
}