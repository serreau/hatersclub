package sero.com.driverscommunicator.ui.main.dialog

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.dialog_comment.*
import kotlinx.android.synthetic.main.dialog_comment.view.*
import kotlinx.android.synthetic.main.user_read_only_dialog_layout.view.*
import sero.com.driverscommunicator.ui.main.MainViewModel
import sero.com.driverscommunicator.utils.hideSoftKeyboard
import sero.com.driverscommunicator.utils.showSoftKeyboard

class CommentDialog(private val mContext: Context,
                    private val mView : View,
                    private val model : MainViewModel,
                    private val numberPlate : String,
                    var isUsernameEmpty : Boolean,
                    private val userAlertDialog : UsernameDialog) : UsernameDialog{

    override fun showDialog() {
        if (mView.parent != null) (mView.parent as ViewGroup).removeView(mView)
        AlertDialog.Builder(mContext)
            .setView(mView)
            .setCancelable(false)
            .setNegativeButton("ANNULER"){ _, _ ->
                hideSoftKeyboard(mView.commentText.context, mView.commentText)
            }
            .setPositiveButton("ENVOYER", null)
            .create().apply {
                setOnShowListener {
                    showSoftKeyboard(mView.commentText.context, mView.commentText)
                    this.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                        when {
                            commentText.length() <= 5 -> mView.errorCommentSizeText.visibility = View.VISIBLE
                            isUsernameEmpty -> (userAlertDialog as UsernameCreationDialog).showDialog(this@CommentDialog)
                            else -> {
                                hideSoftKeyboard(mView.commentText.context, mView.commentText)
                                this.dismiss()
                                model.send(numberPlate, commentText.text.toString())
                            }
                        }
                    }
                }
                show()
            }
    }
}