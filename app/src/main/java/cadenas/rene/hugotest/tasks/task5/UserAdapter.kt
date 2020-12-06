package cadenas.rene.hugotest.tasks.task5

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cadenas.rene.hugotest.R
import cadenas.rene.hugotest.tasks.task5.db.entities.User
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter(
    onSelected: (user: User) -> Unit
): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private val items = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.name.text = "${item.name}, ${item.surName}"

        holder.itemView.setTag(R.id.view_holder, holder)
        holder.itemView.setOnClickListener(onClick)
    }

    override fun getItemCount(): Int = items.size

    fun add(item: User) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun remove(item: User) {
        val pos = items.indexOf(item)
        if (pos > -1) {
            items.removeAt(pos)
            notifyItemRemoved(pos)
        }
    }

    fun update() {
        notifyDataSetChanged()
    }

    private val onClick = View.OnClickListener {
        val viewHolder = it.getTag(R.id.view_holder) as ViewHolder
        onSelected(items[viewHolder.adapterPosition])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.textView
    }

}