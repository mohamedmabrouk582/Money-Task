package com.example.moneytask.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.domain.models.Account
import com.example.moneytask.ACCOUNT_KEY
import com.example.moneytask.R
import com.example.moneytask.databinding.AccountDetailsLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AccountDetailsFragment: BottomSheetDialogFragment() {
    lateinit var viewBinding:AccountDetailsLayoutBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding=DataBindingUtil.inflate(inflater, R.layout.account_details_layout,container,false)
        arguments?.getParcelable<Account>(ACCOUNT_KEY)?.let {
            viewBinding.account=it
        }
        return viewBinding.root
    }
}