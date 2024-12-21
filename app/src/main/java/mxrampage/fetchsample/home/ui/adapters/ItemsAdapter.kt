package mxrampage.fetchsample.home.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import mxrampage.fetchsample.R
import mxrampage.fetchsample.models.ItemsModel

class ItemsAdapter : ListAdapter<ItemsModel, ItemsViewHolder>(ItemsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.items_list_item, parent, false)
        return ItemsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.bind(getItem(position))
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
