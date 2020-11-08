package sero.com.driverscommunicator.ui.main.dialog

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.user_creation_dialog_layout.view.*
import sero.com.driverscommunicator.ui.main.MainViewModel
import sero.com.driverscommunicator.utils.isValidUsername


class UsernameCreationDialog(
    private val mContext: Context,
    private val mView: View,
    private val mModel: MainViewModel
) : UsernameDialog {

    override fun showDialog() {
        showDialog(null)
    }

    fun showDialog(commentDialog : CommentDialog? = null){
        if (mView.parent != null) (mView.parent as ViewGroup).removeView(mView)
        AlertDialog.Builder(mContext)
            .setView(mView)
            .setPositiveButton("Valider", null)
            .setNegativeButton("Plus tard") { _, _ ->}
            .create().apply {
                setOnShowListener {
                    this.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                        if(mView.username.text.toString().isValidUsername()){
                            mModel.setUserName(mView.username.text.toString())
                            this.dismiss()
                            if(commentDialog != null) commentDialog.isUsernameEmpty = false
                        }
                        else
                            mView.errorUsernameFormatText.visibility = View.VISIBLE
                    }
                }
                show()
            }
    }
}