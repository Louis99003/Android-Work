package com.houseprice.cathaybkxtaipeizoo.view

import android.content.Intent
import android.os.Bundle
import android.view.View

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.houseprice.cathaybkxtaipeizoo.R
import com.houseprice.cathaybkxtaipeizoo.databinding.ActivityMainBinding
import com.houseprice.cathaybkxtaipeizoo.view.zoo.info.ExhibitInfoActivity
import com.houseprice.cathaybkxtaipeizoo.view.zoo.info.ExhibitInfoActivity.Companion.BUNDLE_PARAM_EXHIBIT_INFO
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private var pageIsLoading = false

    private val listAdapter: ZooExhibitAdapter by lazy {
        ZooExhibitAdapter(this)
    }

    /**
     * @return R.layout.ActivityYourLayoutResId
     */
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    /**
     *  @return ViewModelProvider(this)[YourViewModel::class.java]
     */
    override fun initViewModel(): MainViewModel {
        return ViewModelProvider(this)[MainViewModel::class.java]
    }

    /**
     * 將要observe的LiveData寫在這裡
     */
    override fun observeLiveData() {
        viewModel.listIsLoading.observe(this) {
            if (it) {
                binding.progressbar.visibility = View.VISIBLE
            } else {
                binding.progressbar.visibility = View.GONE
            }
        }

        viewModel.listData.observe(this) {
            listAdapter.setLoadMoreItems(it)
            pageIsLoading = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val layoutManager = LinearLayoutManager(this)
        binding.rvZooExhibit.layoutManager = layoutManager
        binding.rvZooExhibit.adapter = listAdapter
        viewModel.getListData()
        binding.rvZooExhibit.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!pageIsLoading) {
                    if (layoutManager.findLastVisibleItemPosition() == listAdapter.itemCount - 1 && viewModel.shouldListLoadingData &&
                        viewModel.shouldListLoadingData
                    ) {
                        //bottom of list!
                        pageIsLoading = true
                        viewModel.getListData()
                    }
                }
            }
        })

        listAdapter.setOnItemClickListener(
            onItemClick = { position, item ->
                startActivity(Intent(this@MainActivity, ExhibitInfoActivity::class.java).apply {
                    putExtra(BUNDLE_PARAM_EXHIBIT_INFO, Gson().toJson(item))
                })
            }
        )
    }
}