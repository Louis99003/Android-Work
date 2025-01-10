package com.houseprice.cathaybkxtaipeizoo.view.zoo.info

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.houseprice.cathaybkxtaipeizoo.R
import com.houseprice.cathaybkxtaipeizoo.data.enumclass.ExhibitInfoCardType
import com.houseprice.cathaybkxtaipeizoo.data.model.ExhibitInfoCardView
import com.houseprice.cathaybkxtaipeizoo.databinding.ItemListTitleCardBinding
import com.houseprice.cathaybkxtaipeizoo.databinding.ItemNormalCardBinding
import com.houseprice.cathaybkxtaipeizoo.databinding.ItemTopInfoCardBinding

class ExhibitInfoAdapter(var context: Context) :
    RecyclerView.Adapter<ExhibitInfoAdapter.MyViewHolder>() {
    private var items = mutableListOf<ExhibitInfoCardView>()
    private var onOpenInternetClick: ((position: Int, url: String) -> Unit)? = null
    private var onAnimalItemClick: ((position: Int, item: ExhibitInfoCardView) -> Unit)? = null

    fun setOnItemClickListener(
        onOpenInternetClick: ((position: Int, url: String) -> Unit)? = null,
        onAnimalItemClick: ((position: Int, item: ExhibitInfoCardView) -> Unit)? = null
    ) {
        this.onOpenInternetClick = onOpenInternetClick
        this.onAnimalItemClick = onAnimalItemClick
    }

    class MyViewHolder : RecyclerView.ViewHolder {
        var normalCardBinding: ItemNormalCardBinding? = null
        var topCardBinding: ItemTopInfoCardBinding? = null
        var titleCardBinding: ItemListTitleCardBinding? = null

        constructor(binding: ItemNormalCardBinding) : super(binding.root) {
            normalCardBinding = binding
        }

        constructor(binding: ItemTopInfoCardBinding) : super(binding.root) {
            topCardBinding = binding
        }

        constructor(binding: ItemListTitleCardBinding) : super(binding.root) {
            titleCardBinding = binding
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setLoadMoreItems(list: MutableList<ExhibitInfoCardView>) {
        if (list.size != 0) {
            val originalSize = items.size
            items = ArrayList(list) // 創建一個新的列表
            notifyItemRangeChanged(originalSize, list.size - originalSize)
        } else {
            items.clear()
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ExhibitInfoCardType.TOP_INFO.viewType -> {
                val binding = ItemTopInfoCardBinding.inflate(layoutInflater, parent, false)
                MyViewHolder(binding)
            }

            ExhibitInfoCardType.LIST_TITLE_CARD.viewType -> {
                val binding = ItemListTitleCardBinding.inflate(layoutInflater, parent, false)
                MyViewHolder(binding)
            }

            else -> {
                val binding = ItemNormalCardBinding.inflate(layoutInflater, parent, false)
                MyViewHolder(binding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].cardType.viewType
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        when (holder.itemViewType) {
            ExhibitInfoCardType.TOP_INFO.viewType -> {
                val binding = holder.topCardBinding
                binding?.let {
                    item.exhibitInfoView?.let { exhibitInfo ->
                        Glide.with(context)
                            .load(exhibitInfo.ePicUrl)
                            .placeholder(
                                AppCompatResources.getDrawable(
                                    context,
                                    R.drawable.no_image
                                )
                            )
                            .error(AppCompatResources.getDrawable(context, R.drawable.no_image))
                            .centerCrop()
                            .into(binding.ivCover)

                        binding.tvEInfo.text = exhibitInfo.eInfo

                        if (exhibitInfo.eMemo.isEmpty()) {
                            binding.tvEMemo.text = "無休館資訊"
                        } else {
                            binding.tvEMemo.text = exhibitInfo.eMemo
                        }

                        binding.tvECategory.text = exhibitInfo.eCategory

                        if (exhibitInfo.eUrl.isEmpty()) {
                            binding.tvEUrl.visibility = View.GONE
                        } else {
                            binding.tvEUrl.visibility = View.VISIBLE
                            binding.tvEUrl.setOnClickListener {
                                onOpenInternetClick?.invoke(position, exhibitInfo.eUrl)
                            }
                        }
                    }
                }
            }

            ExhibitInfoCardType.LIST_TITLE_CARD.viewType -> {
                val binding = holder.titleCardBinding
                binding?.let {
                }
            }

            else -> {
                val binding = holder.normalCardBinding
                binding?.let {
                    item.animalInfoView?.let { animalInfo ->
                        Glide.with(context)
                            .load(animalInfo.picture)
                            .placeholder(
                                AppCompatResources.getDrawable(
                                    context,
                                    R.drawable.no_image
                                )
                            )
                            .error(AppCompatResources.getDrawable(context, R.drawable.no_image))
                            .centerCrop()
                            .into(binding.ivCover)

                        binding.tvTitle.text = animalInfo.aNameCh

                        binding.tvInfo.text = animalInfo.aFeature
                        binding.tvMemo.visibility = View.GONE
                        binding.rootView.setOnClickListener {
                            onAnimalItemClick?.invoke(position, item)
                        }
                    }

                }
            }
        }
    }
}