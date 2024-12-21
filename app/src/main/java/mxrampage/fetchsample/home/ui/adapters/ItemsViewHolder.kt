package mxrampage.fetchsample.home.ui.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mxrampage.fetchsample.R
import mxrampage.fetchsample.models.ItemsModel

class ItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val textItemId: TextView = itemView.findViewById(R.id.item_id_text)
    private val textItemName: TextView = itemView.findViewById(R.id.item_name_text)
    private val context = itemView.context

    fun bind(item: ItemsModel) {
        textItemId.text = context.getString(R.string.item_id_template, item.id)
        textItemName.text = item.name
    }
}
