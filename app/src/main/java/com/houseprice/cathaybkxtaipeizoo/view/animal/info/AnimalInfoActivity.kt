package com.houseprice.cathaybkxtaipeizoo.view.animal.info

import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.houseprice.cathaybkxtaipeizoo.R
import com.houseprice.cathaybkxtaipeizoo.data.gone
import com.houseprice.cathaybkxtaipeizoo.databinding.ActivityAnimalInfoBinding
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

        viewModel.infoData.observe(this) { info ->

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

            if (info.aAlsoknown.isNotEmpty()) {
                binding.tvAlsoknown.text = info.aAlsoknown
            } else {
                binding.tvAlsoknown.gone()
                binding.tvAlsoknownTitle.gone()
            }

            if (info.aConservation.isNotEmpty()) {
                binding.tvConservation.text = info.aConservation
            } else {
                binding.tvConservation.gone()
                binding.tvConservationTitle.gone()
            }

            if (info.aFeature.isNotEmpty()) {
                binding.tvFeature.text = info.aFeature
            } else {
                binding.tvFeature.gone()
                binding.tvFeatureTitle.gone()
            }

            if (info.aBehavior.isNotEmpty()) {
                binding.tvBehavior.text = info.aBehavior
            } else {
                binding.tvBehavior.gone()
                binding.tvBehaviorTitle.gone()
            }

            if (info.aCrisis.isNotEmpty()) {
                binding.tvCrisis.text = info.aCrisis
            } else {
                binding.tvCrisis.gone()
                binding.tvCrisisTitle.gone()
            }

            if (info.aUpdate.isNotEmpty()) {
                binding.tvUpdate.text = this.getString(R.string.update_time, info.aUpdate)
            } else{
                binding.tvUpdate.gone()
            }
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