package mxrampage.fetchsample.home.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import mxrampage.fetchsample.R
import mxrampage.fetchsample.models.ItemsModel
import mxrampage.fetchsample.utils.Constants

class ItemsAdapter : ListAdapter<ItemsModel, RecyclerView.ViewHolder>(ItemsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Constants.TYPE_HEADER ->
                HeaderViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.header_list_item, parent, false)
                )
            else ->
                ItemsViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.items_list_item, parent, false)
                )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (item.listId) {
            null -> (holder as ItemsViewHolder).bind(item)
            else -> (holder as HeaderViewHolder).bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).listId != null) Constants.TYPE_HEADER else Constants.TYPE_ITEM
    }

    internal class ItemsDiffUtil : DiffUtil.ItemCallback<ItemsModel>() {
        override fun areItemsTheSame(oldItem: ItemsModel, newItem: ItemsModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ItemsModel, newItem: ItemsModel): Boolean {
            return oldItem == newItem
        }
    }
}
