package com.example.suitmedia.view.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suitmedia.data.response.DataItem
import com.example.suitmedia.databinding.ItemUserBinding
import com.example.suitmedia.view.secondscreen.SecondScreenActivity

class UserAdapter : PagingDataAdapter<DataItem, UserAdapter.MyViewHolder>(DIFF_CALLBACK) {

    inner class MyViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: DataItem) {
            binding.apply {
                tvFirstname.text = user.firstName
                tvLastname.text = user.lastName
                tvEmail.text = user.email
                Glide.with(itemView.context)
                    .load(user.avatar)
                    .into(ivProfile)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, SecondScreenActivity::class.java)
                    intent.putExtra(SecondScreenActivity.KEY_DATA, user)
                    itemView.context.startActivity(intent)
                    (itemView.context as Activity).finish()
                }
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null) {
            holder.bind(user)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DataItem,
                newItem: DataItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}