package com.houseprice.cathaybkxtaipeizoo.view.zoo.info

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.houseprice.cathaybkxtaipeizoo.R
import com.houseprice.cathaybkxtaipeizoo.databinding.ActivityExhibitInfoBinding
import com.houseprice.cathaybkxtaipeizoo.view.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExhibitInfoActivity : BaseActivity<ActivityExhibitInfoBinding, ExhibitInfoViewModel>() {

    companion object {
        const val BUNDLE_PARAM_EXHIBIT_INFO = "exhibitInfo"
        const val BUNDLE_PARAM_ACTIVITY_TITLE = "activityTitle"
    }

    private var pageIsLoading = false

    private val listAdapter: ExhibitInfoAdapter by lazy {
        ExhibitInfoAdapter(this)
    }

    /**
     * @return R.layout.ActivityYourLayoutResId
     */
    override fun getLayoutId(): Int {
        return R.layout.activity_exhibit_info
    }

    /**
     *  @return ViewModelProvider(this)[YourViewModel::class.java]
     */
    override fun initViewModel(): ExhibitInfoViewModel {
        return ViewModelProvider(this)[ExhibitInfoViewModel::class.java]
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

        viewModel.activityTitle.observe(this) {
            supportActionBar?.title = it
        }

        viewModel.listData.observe(this) {
            listAdapter.setLoadMoreItems(it)
            pageIsLoading = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvExhibitInfo.layoutManager = layoutManager
        binding.rvExhibitInfo.adapter = listAdapter
        viewModel.getListData()
        binding.rvExhibitInfo.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!pageIsLoading) {
                    if (layoutManager.findLastVisibleItemPosition() == listAdapter.itemCount - 1 && viewModel.shouldListLoadingData && viewModel.shouldListLoadingData) {
                        //bottom of list!
                        pageIsLoading = true
                        viewModel.getListData()
                    }
                }
            }
        })

        listAdapter.setOnItemClickListener(onOpenInternetClick = { _, url ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            this@ExhibitInfoActivity.startActivity(intent)
        }, onAnimalItemClick = { _, animal ->

        })
    }
}