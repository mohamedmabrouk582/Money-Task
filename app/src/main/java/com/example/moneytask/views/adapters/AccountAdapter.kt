package com.example.moneytask.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Account
import com.example.moneytask.R
import com.example.moneytask.databinding.AccountItemViewBinding

class AccountAdapter(val listener:AccountListener) : RecyclerView.Adapter<AccountAdapter.Holder>() {
   var accounts:ArrayList<Account> = ArrayList()
    set(value) {
        field=value
        field.sortByDescending { it.balance }
        notifyDataSetChanged()
    }
    inner class Holder(private val viewBinding:AccountItemViewBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(account: Account){
            viewBinding.account=account
            viewBinding.root.setOnClickListener { listener.onAccountClick(account) }
            viewBinding.executePendingBindings()
        }
    }

    fun addAccount(account:Account){
        accounts.add(account)
        accounts.sortByDescending { it.balance }
        notifyDataSetChanged()
       // notifyItemInserted(accounts.size)

    }

    interface AccountListener{
        fun onAccountClick(account: Account)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.account_item_view,parent,false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(accounts[position])
    }

    override fun getItemCount(): Int =accounts.size
}