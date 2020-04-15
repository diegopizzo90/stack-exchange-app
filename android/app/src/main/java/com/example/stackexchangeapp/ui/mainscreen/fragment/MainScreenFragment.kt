package com.example.stackexchangeapp.ui.mainscreen.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stackexchangeapp.R
import com.example.stackexchangeapp.ui.mainscreen.MainScreenStateView
import com.example.stackexchangeapp.ui.mainscreen.viewmodel.MainScreenViewModel
import kotlinx.android.synthetic.main.fragment_main_screen.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainScreenFragment : Fragment() {

    private val viewModel: MainScreenViewModel by viewModel()
    private lateinit var adapter: MainScreenAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = MainScreenAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_main_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setButtonClickListener()
        viewModel.mainScreenState.observe(viewLifecycleOwner, Observer {
            renderView(it)
        })
        //Restore data value after the orientation change
        val valueRetained = savedInstanceState?.getString(EDIT_TEXT_VALUE_KEY)
        if (!valueRetained.isNullOrEmpty()) viewModel.getUsersByName(valueRetained)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //Saving edit text value after the orientation change
        outState.putString(EDIT_TEXT_VALUE_KEY, et_main_screen_name.text.toString())
    }

    private fun setRecyclerView() {
        rv_main_screen.layoutManager = LinearLayoutManager(context)
        rv_main_screen.adapter = adapter
    }

    private fun setButtonClickListener() {
        bt_main_screen_search.setOnClickListener {
            viewModel.getUsersByName(et_main_screen_name.text.toString())
            dismissKeyboard()
        }
    }

    private fun renderView(mainScreenStateView: MainScreenStateView) {
        when (mainScreenStateView) {
            MainScreenStateView.ShowLoading -> pb_main_screen_loading.visibility = View.VISIBLE
            MainScreenStateView.HideLoading -> pb_main_screen_loading.visibility = View.GONE
            is MainScreenStateView.ShowData -> adapter.addUsers(mainScreenStateView.data)
            MainScreenStateView.ShowError -> {
                Toast.makeText(context, getString(R.string.error_message), Toast.LENGTH_SHORT)
                    .show()
                adapter.clearUsers()
            }
            MainScreenStateView.ShowEmptyResult -> {
                Toast.makeText(context, getString(R.string.empty_message), Toast.LENGTH_SHORT)
                    .show()
                adapter.clearUsers()
            }
        }
    }

    private fun dismissKeyboard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (activity?.currentFocus != null) imm.hideSoftInputFromWindow(
            activity?.currentFocus!!.applicationWindowToken,
            0
        )
    }

    companion object {
        const val TAG_MAIN_SCREEN_FRAGMENT = "mainScreenFragment"
        private const val EDIT_TEXT_VALUE_KEY = "editTextValueKey"
        fun newInstance(bundle: Bundle? = null): MainScreenFragment {
            val transactionsListFragment = MainScreenFragment()
            if (bundle != null) {
                transactionsListFragment.arguments = bundle
            }
            return transactionsListFragment
        }
    }
}