package ru.max.bk252_with_coroutines.Fragment_Adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import ru.max.bk252_with_coroutines.R
import ru.max.bk252_with_coroutines.Data.UserData


class Adapter(private val mDataList: ArrayList<UserData>, val clickListener: ItemClickListener) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        val picture = view.findViewById<ImageView>(R.id.picture)
        val draw = view.resources.getDrawable(R.drawable.done_hw)
        picture.setImageDrawable(draw)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mDataList.get(position)
        holder.title.text = mDataList[position].name
        holder.status.setTextColor(Color.rgb(0, 110, 0))
        holder.status.text = "Принято"
        holder.itemView.setOnClickListener(View.OnClickListener {
            clickListener.onItemClick(item)
        })
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var title: TextView
        internal var status: TextView

        init {
            title = itemView.findViewById<View>(R.id.tasktitle) as TextView
            status = itemView.findViewById<View>(R.id.status) as TextView
        }
    }
}

interface ItemClickListener {
    fun onItemClick(item: UserData)
}