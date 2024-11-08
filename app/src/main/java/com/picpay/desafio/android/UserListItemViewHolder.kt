package com.picpay.desafio.android

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.domain.entity.User
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private val nameTextView: TextView = itemView.findViewById(R.id.name)
    private val usernameTextView: TextView = itemView.findViewById(R.id.username)
    private val pictureImageView: ImageView = itemView.findViewById(R.id.picture)
    private val progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)


    fun bind(user: User) {
        nameTextView.text = user.name
        usernameTextView.text = user.username
        progressBar.visibility = View.VISIBLE
        Picasso.get()
            .load(user.img)
            .error(R.drawable.ic_round_account_circle)
            .into(pictureImageView, object : Callback {
                override fun onSuccess() {
                    progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    progressBar.visibility = View.GONE
                }
            })
    }
}