package mxrampage.fetchsample.home.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import mxrampage.fetchsample.databinding.ActivityItemsBinding
import mxrampage.fetchsample.home.ui.adapters.ItemsAdapter
import mxrampage.fetchsample.home.ui.viewmodel.ItemsViewModel
import mxrampage.fetchsample.utils.Status

@AndroidEntryPoint
class ItemsActivity : AppCompatActivity() {
    private lateinit var itemsViewModel: ItemsViewModel
    private lateinit var binding: ActivityItemsBinding
    private lateinit var adapter: ItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        itemsViewModel = ViewModelProvider(this)[ItemsViewModel::class.java]
        binding.lifecycleOwner = this
        setupViews()
        setObservers()
    }

    override fun onResume() {
        super.onResume()
        itemsViewModel.getItemsList()
    }

    private fun setupViews() {
        adapter = ItemsAdapter()
        binding.itemsRecycler.adapter = adapter
    }

    private fun setObservers() {
        itemsViewModel.itemsData.observe(this) { itemsData ->
            when (itemsData.status) {
                Status.SUCCESS -> {
                    binding.loaderContainer.isVisible = false
                    itemsData.data?.let {
                        adapter.submitList(it)
                    }
                }
                Status.ERROR -> {
                    binding.loaderContainer.isVisible = false
                    Toast.makeText(
                        this, itemsData.message ?: "Error", Toast.LENGTH_SHORT
                    ).show()
                }
                Status.LOADING -> binding.loaderContainer.isVisible = true
            }
        }
    }
}
