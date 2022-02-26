package com.example.android02ex01.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android02ex01.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android02ex01.databinding.LayoutCelebListItemBinding
import com.example.android02ex01.models.Celeb

class CelebRecyclerAdapter(
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Celeb> = ArrayList()

    interface OnItemClickListener {
        fun onItemClick(view: View, item: Celeb)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CelebViewHolder(
            LayoutCelebListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ), listener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is CelebViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(blogList: List<Celeb>) {
        items = blogList
    }

    open class CelebViewHolder constructor(
        private val binding: LayoutCelebListItemBinding, listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.run {
                this.listener =listener
            }
        }
        val celebImage =binding.celebImage
        val celebName =binding.celebName
        val celebDesc =binding.celebDescription
        val celebAuthor =binding.celebAuthor

        fun bind(celeb:Celeb){
            binding.celebPost=celeb

            celebName.text=celeb.name
            celebAuthor.text=celeb.username
            celebDesc.text=celeb.description

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.white_background)
                .error(R.drawable.white_background)

            Glide.with(celebAuthor.context)
                .setDefaultRequestOptions(requestOptions)
                .load(celeb.image)
                .into(celebImage)
        }
    }

}
