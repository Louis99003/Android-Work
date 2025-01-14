package com.houseprice.cathaybkxtaipeizoo.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.houseprice.cathaybkxtaipeizoo.R
import com.houseprice.cathaybkxtaipeizoo.data.model.ZooExhibitViewItem
import com.houseprice.cathaybkxtaipeizoo.databinding.ItemNormalCardBinding

class ZooExhibitAdapter(var context: Context) :
    RecyclerView.Adapter<ZooExhibitAdapter.MyViewHolder>() {
    private var items = mutableListOf<ZooExhibitViewItem>()
    private var onItemClick: ((position: Int, item: ZooExhibitViewItem) -> Unit)? = null

    fun setOnItemClickListener(onItemClick: ((position: Int, cover: ZooExhibitViewItem) -> Unit)? = null) {
        this.onItemClick = onItemClick
    }

    class MyViewHolder : RecyclerView.ViewHolder {
        var cardBinding: ItemNormalCardBinding? = null

        constructor(binding: ItemNormalCardBinding) : super(binding.root) {
            cardBinding = binding
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setLoadMoreItems(list: MutableList<ZooExhibitViewItem>) {
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
        val binding = ItemNormalCardBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        val binding = holder.cardBinding
        if (binding != null) {
            with(binding) {
                Glide.with(context)
                    .load(item.ePicUrl)
                    .placeholder(
                        AppCompatResources.getDrawable(
                            context,
                            R.drawable.no_image
                        )
                    )
                    .error(AppCompatResources.getDrawable(context, R.drawable.no_image))
                    .centerCrop()
                    .into(ivCover)

                tvTitle.text = item.eName

                tvInfo.text = item.eInfo

                if (item.eMemo.isEmpty()) {
                    tvMemo.text = context.getString(R.string.no_closed_days_info)
                } else {
                    tvMemo.text = item.eMemo
                }

                rootView.setOnClickListener {
                    onItemClick?.invoke(position, item)
                }
            }
        }
    }
}