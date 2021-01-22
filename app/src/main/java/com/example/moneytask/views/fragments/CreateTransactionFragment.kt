package com.example.moneytask.views.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.domain.models.*
import com.example.moneytask.BUDGET_PAYEES
import com.example.moneytask.R
import com.example.moneytask.callBacks.CreateTransactionCallBack
import com.example.moneytask.databinding.CreateTransactionLayoutBinding
import com.example.moneytask.viewModels.CreateTransactionViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

@AndroidEntryPoint
class CreateTransactionFragment : BottomSheetDialogFragment(), CreateTransactionCallBack {
    val viewModel:CreateTransactionViewModel<CreateTransactionCallBack> by viewModels()
    lateinit var viewBinding: CreateTransactionLayoutBinding
    lateinit var budgetAdapter : ArrayAdapter<String>
    lateinit var payeeAdapter: ArrayAdapter<String>
    lateinit var accountAdapter: ArrayAdapter<String>
    lateinit var budgetPayees : HashMap<Budget,ArrayList<Payee>>
    var budgetAccounts : HashMap<Budget,ArrayList<Account>> =  HashMap()
    lateinit var currentBudgetName:Budget
    lateinit var currentPayee:Payee
     var currentAccount:Account?=null
     var date:String?=null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding=DataBindingUtil.inflate(inflater, R.layout.create_transaction_layout,container,false)
        viewModel.attachView(this)
        viewBinding.vm=viewModel
        arguments?.getParcelable<BudgetPayees>(BUDGET_PAYEES)?.let {
            budgetPayees=it.data
            setupBudget(ArrayList(budgetPayees.keys.map { it.name }))
        }

        return viewBinding.root
    }

    private fun setupPayee(data: ArrayList<String>){
        payeeAdapter= ArrayAdapter(requireContext(),R.layout.account_type_view, data)
        with(viewBinding.spinnerPayees){
            adapter=payeeAdapter
            onItemSelectedListener= payeeListener
        }
    }

    private fun setupAccount(data: ArrayList<String>){
        accountAdapter= ArrayAdapter(requireContext(),R.layout.account_type_view, data)
        with(viewBinding.spinnerAccounts){
            adapter=accountAdapter
            onItemSelectedListener= accountListener
        }
    }

    private fun setupBudget(data:ArrayList<String>){
        budgetAdapter=ArrayAdapter(requireContext(),R.layout.account_type_view, data)
        with(viewBinding.spinnerBudget){
            adapter=budgetAdapter
            onItemSelectedListener=budgetListener
        }
    }

   private val budgetListener = object : AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            currentBudgetName= budgetPayees.keys.filter { it.name==budgetAdapter.getItem(position) }[0]
            if (budgetAccounts[currentBudgetName] ==null){
                viewModel.getBudgetAccounts(currentBudgetName.id)
            }else{
                budgetAccounts[currentBudgetName]?.let {
                    setupAccount(ArrayList(it.map { it.name }))
                }
            }
            budgetPayees[currentBudgetName]?.let { setupPayee(ArrayList(it.map{it.name})) }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

    }

   private val payeeListener = object : AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
             currentPayee = budgetPayees[currentBudgetName]?.filter { payeeAdapter.getItem(position)!! == it.name }?.get(0)!!
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

    }

   private val accountListener = object : AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            try {
                currentAccount = budgetAccounts[currentBudgetName]?.filter { accountAdapter.getItem(position)!! == it.name }?.get(0)
            } catch (e:Exception){}
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

    }

    override fun showLoader() {
        ProgressFragment.start().show(activity?.supportFragmentManager!!,"ProgressFragment")
    }

    override fun dismiss() {
        ProgressFragment.stop()
    }

    override fun error(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        dismiss()
    }

    override fun loadAccounts(accounts: ArrayList<Account>) {
        budgetAccounts[currentBudgetName] = accounts
        setupAccount(ArrayList(accounts.map { it.name }))
    }

    override fun accountTransaction(transaction: Transaction) {
        Toast.makeText(requireContext(), "Transaction Created Successfully", Toast.LENGTH_SHORT).show()
        dialog?.dismiss()
    }

    override fun createClick() {
        when {
            date.isNullOrEmpty() -> {
                error("Select Date ")
            }
            viewBinding.editAmount.text.isNullOrBlank() -> {
                error("Insert Amount ")
            }
            viewBinding.editMemo.text.isNullOrBlank() -> {
                error("Insert Memo")
            }
            else -> {
                viewModel.createTransaction(currentBudgetName.id, TransactionRequest(Transaction(
                        account_id = currentAccount?.id,
                        payee_id = currentPayee.id,
                        amount = viewBinding.editAmount.text.toString().toLong(),
                        memo = viewBinding.editMemo.text.toString(),
                        date = date
                )))
            }
        }
    }

    override fun datePicker() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(requireContext(),{ view, year, monthOfYear, dayOfMonth ->
             date="$year-${monthOfYear+1}-$dayOfMonth"
            viewBinding.btnDate.text=date
        }, year, month, day)
        dpd.show()
    }


}