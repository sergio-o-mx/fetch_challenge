package mxrampage.fetchsample.home.ui.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mxrampage.fetchsample.R
import mxrampage.fetchsample.models.ItemsModel

class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val textHeader: TextView = itemView.findViewById(R.id.item_header_text)
    private val context = itemView.context

    fun bind(item: ItemsModel) {
        textHeader.text = context.getString(R.string.header_id_template, item.listId)
    }
}
