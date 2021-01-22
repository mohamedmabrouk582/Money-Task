package com.example.moneytask.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Transaction
import com.example.moneytask.R
import com.example.moneytask.databinding.TransactionItemViewBinding

class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.Holder>(){
    var data:ArrayList<Transaction> = ArrayList()
    set(value) {
        field=value
        notifyDataSetChanged()
    }

    inner class Holder(private val viewBinding:TransactionItemViewBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item:Transaction){
            viewBinding.trans=item
            viewBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
       return Holder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.transaction_item_view,parent,false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}