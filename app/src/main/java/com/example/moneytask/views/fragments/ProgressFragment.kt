package com.example.moneytask.views.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.moneytask.R
import com.example.moneytask.databinding.ProgressLayoutBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class ProgressFragment : DialogFragment()  {

    lateinit var layoutBinding:ProgressLayoutBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       layoutBinding=DataBindingUtil.inflate(inflater, R.layout.progress_layout,container,false)
        this.dialog?.setCanceledOnTouchOutside(false)
        return layoutBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }




    companion object{
        val fragment:ProgressFragment = ProgressFragment()
        fun start() : ProgressFragment {
            return fragment
        }

        fun stop(){
            try {
                fragment.dismiss()
            } catch (e:Exception){}
        }
    }

}