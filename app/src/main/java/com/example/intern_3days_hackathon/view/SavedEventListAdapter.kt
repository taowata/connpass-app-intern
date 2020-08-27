package com.example.intern_3days_hackathon.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.intern_3days_hackathon.data.SavedEvent
import com.example.intern_3days_hackathon.databinding.SavedEventListItemBinding

class SavedEventListAdapter(
        private val clickListener: SavedEventListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<SavedEvent> = mutableListOf()

    class SavedEventViewHolder constructor(val binding: SavedEventListItemBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(event: SavedEvent, clickListener: SavedEventListener) {
            binding.savedEvent = event
            binding.clickListener = clickListener
            binding.executePendingBindings()

        }

        companion object {
            fun from(parent: ViewGroup): SavedEventViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SavedEventListItemBinding.inflate(layoutInflater, parent, false)
                return SavedEventViewHolder(
                        binding
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SavedEventViewHolder.from(
                parent
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(favoriteList: List<SavedEvent>) {
        items = favoriteList
    }

    fun getSavedRecipe(position: Int): SavedEvent {
        return items[position]
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is SavedEventViewHolder -> {
                holder.bind(items[position], clickListener)
            }
        }
    }

}

class SavedEventListener(
        val clickListener1: (recipe: SavedEvent) -> Unit,
        val clickListener2: (recipe: SavedEvent) -> Unit
) {
    fun onClickDelete(savedRecipe: SavedEvent) = clickListener1(savedRecipe)
    fun onClickDetail(savedRecipe: SavedEvent) = clickListener2(savedRecipe)
}
