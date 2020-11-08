package sero.com.driverscommunicator.ui.main.dialog

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.user_read_only_dialog_layout.view.*
import sero.com.driverscommunicator.R
import sero.com.driverscommunicator.ui.main.MainViewModel

class InformationsDialog(private val mContext: Context, private val mView : View, private val model : MainViewModel) : UsernameDialog{

    override fun showDialog() {
        if (mView.parent != null) (mView.parent as ViewGroup).removeView(mView)
        AlertDialog.Builder(mContext)
            .setView(mView)
            .setPositiveButton("Ok") { _, _ -> model.setInformationsAlreadySeen() }
            .show()
    }
}