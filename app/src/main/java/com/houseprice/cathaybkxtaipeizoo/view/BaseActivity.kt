package com.houseprice.cathaybkxtaipeizoo.view

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    private var _binding: VB? = null
    private var _viewModel: VM? = null
    val binding get() = _binding!!
    val viewModel get() = _viewModel!!

    /**
     * @return R.layout.ActivityYourLayoutResId
     */
    @LayoutRes
    protected abstract fun getLayoutId(): Int

    /**
     *  @return ViewModelProvider(this)[YourViewModel::class.java]
     */
    protected abstract fun initViewModel(): VM

    /**
     * 將要observe的LiveData寫在這裡
     */
    protected abstract fun observeLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, getLayoutId())
        _viewModel = initViewModel()

        //Base Observer
        viewModel.toast.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        observeLiveData()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}