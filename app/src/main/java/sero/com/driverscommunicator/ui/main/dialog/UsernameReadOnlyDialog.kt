package sero.com.driverscommunicator.ui.main.dialog

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.user_read_only_dialog_layout.view.*

class UsernameReadOnlyDialog(private val mContext: Context, private val mView : View, val username : String) : UsernameDialog{

    override fun showDialog() {
        if (mView.parent != null) (mView.parent as ViewGroup).removeView(mView)
        mView.username.text = username
        AlertDialog.Builder(mContext)
            .setView(mView)
            .setPositiveButton("Ok",) { _, _ -> }
            .show()
    }
}