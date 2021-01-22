package com.example.moneytask.views.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.domain.models.Account
import com.example.domain.models.AccountRequest
import com.example.domain.models.AccountType
import com.example.moneytask.ACCOUNT_KEY
import com.example.moneytask.BUDGET_ID
import com.example.moneytask.MainActivity
import com.example.moneytask.R
import com.example.moneytask.callBacks.AccountListener
import com.example.moneytask.callBacks.CreateAccountCallBack
import com.example.moneytask.databinding.CreateAccountLayoutBinding
import com.example.moneytask.viewModels.CreateAccountViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateAccountFragment : BottomSheetDialogFragment() , CreateAccountCallBack, AdapterView.OnItemSelectedListener {
    lateinit var viewBinding:CreateAccountLayoutBinding
    var selectType:AccountType?=null
    lateinit var budget_id:String
    var listener:AccountListener?=null
    val viewModel:CreateAccountViewModel<CreateAccountCallBack> by viewModels()
    val adapter by lazy { ArrayAdapter(requireContext(),R.layout.account_type_view,resources.getStringArray(R.array.account_type)) }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.create_account_layout,container,false)
        viewModel.attachView(this)
        viewBinding.vm=viewModel
        with( viewBinding.typeSpinner) {
            adapter = this@CreateAccountFragment.adapter
            onItemSelectedListener = this@CreateAccountFragment
        }
        arguments?.getString(BUDGET_ID)?.let {
            budget_id=it
        }
        return viewBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as AccountListener
    }

    override fun showLoader() {
        ProgressFragment.start().show(activity?.supportFragmentManager!!,"ProgressFragment")
    }

    override fun dismiss() {
        ProgressFragment.stop()
    }

    override fun error(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun accountCreated(account: AccountRequest) {
        listener?.addAccount(account.account)
        dialog?.dismiss()
    }

    override fun createClick() {
        when {
            selectType==null -> {
                error("Select One Of Account Type")
            }
            viewBinding.nameTxt.text.isNullOrBlank() -> {
                error("Insert Account Name ")
            }
            viewBinding.balanceTxt.text.isNullOrBlank() -> {
                error("Insert Account Balance")
            }
            else -> {
                val account=Account(name = viewBinding.nameTxt.text.toString(),type = selectType,balance =   viewBinding.balanceTxt.text.toString().toLong())
                viewModel.createNewAccount(budget_id,AccountRequest(account))
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (position==0){
            selectType=null
        }else{
            selectType=AccountType.values()[position-1]

        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}