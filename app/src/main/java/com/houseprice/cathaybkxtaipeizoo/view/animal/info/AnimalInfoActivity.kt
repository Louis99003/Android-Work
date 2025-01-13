package com.houseprice.cathaybkxtaipeizoo.view.animal.info

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.houseprice.cathaybkxtaipeizoo.R
import com.houseprice.cathaybkxtaipeizoo.databinding.ActivityAnimalInfoBinding
import com.houseprice.cathaybkxtaipeizoo.databinding.ActivityExhibitInfoBinding
import com.houseprice.cathaybkxtaipeizoo.view.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimalInfoActivity : BaseActivity<ActivityAnimalInfoBinding, AnimalInfoViewModel>() {

    companion object {
        const val BUNDLE_PARAM_ANIMAL_INFO = "animalInfo"
    }

    /**
     * @return R.layout.ActivityYourLayoutResId
     */
    override fun getLayoutId(): Int {
        return R.layout.activity_animal_info
    }

    /**
     *  @return ViewModelProvider(this)[YourViewModel::class.java]
     */
    override fun initViewModel(): AnimalInfoViewModel {
        return ViewModelProvider(this)[AnimalInfoViewModel::class.java]
    }

    /**
     * 將要observe的LiveData寫在這裡
     */
    override fun observeLiveData() {

        viewModel.activityTitle.observe(this) {
            supportActionBar?.title = it
        }

        viewModel.infoData.observe(this) {info->

            Glide.with(this)
                .load(info.picture)
                .placeholder(
                    AppCompatResources.getDrawable(
                        this,
                        R.drawable.no_image
                    )
                )
                .error(AppCompatResources.getDrawable(this, R.drawable.no_image))
                .centerCrop()
                .into(binding.ivCover)

            binding.tvCnName.text = info.aNameCh
            binding.tvEnName.text = info.aNameEn


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

    }
}