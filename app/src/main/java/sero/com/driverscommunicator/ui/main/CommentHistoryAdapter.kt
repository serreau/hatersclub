package sero.com.driverscommunicator.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_main_layout.view.*
import sero.com.driverscommunicator.R
import sero.com.driverscommunicator.data.remote.response.GetCommentResponse
import java.text.SimpleDateFormat

class CommentHistoryAdapter(var comments: MutableList<GetCommentResponse>,
                            private val model: MainViewModel):
    RecyclerView.Adapter<CommentHistoryAdapter.CommentHistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHistoryViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_main_layout, parent, false) as CardView
        return CommentHistoryViewHolder(item)
    }

    override fun onBindViewHolder(holder: CommentHistoryViewHolder, position: Int) {
        holder.initCommentHistoryViewHolder(comments, position)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    class CommentHistoryViewHolder(private val layout: CardView) : RecyclerView.ViewHolder(layout){
        fun initCommentHistoryViewHolder(comments: MutableList<GetCommentResponse>, position: Int) {
            layout.username.text = "@"+comments[position].username
            val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(comments[position].date)
            layout.date.text = SimpleDateFormat("dd-MM-yyyy HH:mm").format(date)
            layout.comment.text = comments[position].comment

        }
    }
}