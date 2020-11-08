package sero.com.driverscommunicator.ui.main

import android.os.Bundle
import android.text.InputFilter
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.main_fragment.*
import sero.com.driverscommunicator.R
import sero.com.driverscommunicator.ui.main.dialog.*
import sero.com.driverscommunicator.utils.hideSoftKeyboard


class MainFragment : Fragment() {
    private val model: MainViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var userAlertDialog : UsernameDialog
    private var isUsernameEmpty: Boolean = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
            = inflater.inflate(R.layout.main_fragment, container, false).apply { setHasOptionsMenu(true) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userAlertDialog = UsernameCreationDialog(requireContext(), layoutInflater.inflate(R.layout.user_creation_dialog_layout, null), model)
        setUsernameDialogListeners()
        setInformationsDialogListeners()
        setNumberPlateListeners()
        setCommentButtonListeners()
        setCommentListListeners()
    }

    private fun setUsernameDialogListeners() =
        model.user.observe(viewLifecycleOwner, {
            if(it != null) {
                isUsernameEmpty = false
                val userAlertDialogLayout = layoutInflater.inflate(R.layout.user_read_only_dialog_layout, null)
                userAlertDialog = UsernameReadOnlyDialog(requireContext(), userAlertDialogLayout, it.username)
            }
        })

    private fun setInformationsDialogListeners() =
        model.settings.observe(viewLifecycleOwner, { if(it == null) openInformationsDialog() })

    private fun setNumberPlateListeners() {
        numberPlate1.doOnTextChanged { text, _, _, _ ->
            val maxLength = (numberPlate1.filters.first() as InputFilter.LengthFilter).max
            if(maxLength == text?.length) numberPlate2.requestFocus()
            commonAfterTextChange()
        }
        numberPlate2.doOnTextChanged { text, _, _, _ ->
            val maxLength = (numberPlate2.filters.first() as InputFilter.LengthFilter).max
            if(maxLength == text?.length) numberPlate3.requestFocus()
            if(text?.length == 0) numberPlate1.requestFocus()
            commonAfterTextChange()
        }
        numberPlate3.doOnTextChanged { text, _, _, _ ->
            if(text?.length == 0)
                if(numberPlate2.length() != 0) numberPlate2.requestFocus()
                else numberPlate1.requestFocus()
            commonAfterTextChange()
        }
    }

    private fun setCommentButtonListeners() =
        commentButton.setOnClickListener {
            val customAlertDialogView = layoutInflater.inflate(R.layout.dialog_comment, null)
            CommentDialog(requireContext(), customAlertDialogView, model, getNumberPlateToString(), isUsernameEmpty, userAlertDialog).showDialog()
        }

    private fun setCommentListListeners() =
            model.allComments.observe(viewLifecycleOwner, {
                loadingBar.visibility = View.GONE
                viewManager = LinearLayoutManager(this.requireContext()).apply {
                    stackFromEnd = true
                }
                viewAdapter = CommentHistoryAdapter(it.toMutableList(), model)
                recyclerView = recycler_view.apply {
                    setHasFixedSize(false)
                    layoutManager = viewManager
                    adapter = viewAdapter
                }
                if(it.isEmpty()){
                    recycler_view.visibility = View.GONE
                    emoji.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.peur))
                }
                else{
                    recycler_view.visibility = View.VISIBLE
                    emoji.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.colere))
                }
            })

    private fun commonAfterTextChange() =
        if(isReadyToShow()) {
            loadingBar.visibility = View.VISIBLE
            commentButton.isEnabled = true
            hideSoftKeyboard(requireContext(), requireView())
            model.numberPlate.postValue(getNumberPlateToString())
            numberPlate3.clearFocus()
        } else{
            loadingBar.visibility = View.GONE
            recycler_view.visibility = View.GONE
            commentButton.isEnabled = false
            if(getNumberPlateToString().isNotEmpty())
                emoji.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.colere))
            else
                emoji.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.enerve))
        }

    private fun isReadyToShow() = getNumberPlateToString().length == 7

    private fun getNumberPlateToString() : String
            = numberPlate1.text.toString() + numberPlate2.text.toString() + numberPlate3.text.toString()

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun openInformationsDialog() =
        InformationsDialog(requireContext(), layoutInflater.inflate(R.layout.informations_layout, null), model).showDialog()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_settings -> userAlertDialog.showDialog()
            R.id.action_informations -> openInformationsDialog()
        }
        return super.onOptionsItemSelected(item)
    }
}