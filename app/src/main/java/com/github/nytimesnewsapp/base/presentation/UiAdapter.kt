package com.github.nytimesnewsapp.base.presentation

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.github.nytimesnewsapp.ViewHolderFactory
import com.github.nytimesnewsapp.base.Constants
import com.github.nytimesnewsapp.base.presentation.model.BaseUIModel

class UiAdapter : RecyclerView.Adapter<BaseViewHolder<BaseUIModel>>() {

    companion object {
        const val SPAN_COUNT = 6
    }

    fun layoutManager(context: Context?) = GridLayoutManager(context, 6).apply {
        spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int =
                getItem(position)?.spanSize ?: SPAN_COUNT
        }
    }

    private val currentList: List<BaseUIModel> get() = mDiffer.currentList

    private val mDiffer by lazy {
        val listUpdateCallBack = BaseAdapterListUpdateCallback()
        val diffCallBack = BaseAdapterDiffCallback()
        val config: AsyncDifferConfig<BaseUIModel> = AsyncDifferConfig.Builder(diffCallBack).build()
        AsyncListDiffer(listUpdateCallBack, config)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<BaseUIModel> = ViewHolderFactory.create(viewType, parent)

    override fun getItemViewType(position: Int): Int =
        getItem(position)?.itemViewType ?: Constants.ViewType.EMPTY_VIEW_TYPE

    override fun getItemCount(): Int = currentList.size

    @Suppress("unchecked_cast")
    override fun onBindViewHolder(
        holder: BaseViewHolder<BaseUIModel>,
        position: Int,
        payloads: MutableList<Any>
    ) {

        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
            return
        }

        val diffSet: Set<String>? = payloads.firstOrNull() as? Set<String>?
        diffSet?.let { diff ->
            holder.bindClicksAnd(position) {
                bindViewPayloads(position, it, diff)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BaseUIModel>, position: Int) =
        holder.bindClicksAnd(position) {
            bindView(position, it)
        }

    private inline fun BaseViewHolder<BaseUIModel>.bindClicksAnd(
        position: Int,
        func: BaseViewHolder<BaseUIModel>.(BaseUIModel) -> Unit
    ) {

        getItem(position)?.let {
            itemView.setOnClickListener { _ ->
                it.onItemClicked(it)
            }
            func(it)
        }
    }

    fun submitList(list: List<BaseUIModel>) {
        mDiffer.submitList(list)
    }

    inner class BaseAdapterDiffCallback : DiffUtil.ItemCallback<BaseUIModel>() {

        override fun areItemsTheSame(oldItem: BaseUIModel, newItem: BaseUIModel) =
            oldItem.getIdentifier() == newItem.getIdentifier() && oldItem.spanSize == newItem.spanSize

        override fun areContentsTheSame(
            oldItem: BaseUIModel,
            newItem: BaseUIModel
        ) =
            oldItem.areContentsTheSame(newItem)

        override fun getChangePayload(
            oldItem: BaseUIModel,
            newItem: BaseUIModel
        ) =
            oldItem.getChangePayload(newItem)
    }

    inner class BaseAdapterListUpdateCallback : ListUpdateCallback {

        override fun onChanged(position: Int, count: Int, payload: Any?) {
            notifyItemRangeChanged(position, count, payload)
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            notifyItemMoved(fromPosition, toPosition)
        }

        override fun onInserted(position: Int, count: Int) {
            notifyItemRangeInserted(position, count)
        }

        override fun onRemoved(position: Int, count: Int) {
            notifyItemRangeRemoved(position, count)
        }
    }

    fun getItem(position: Int): BaseUIModel? = currentList.getOrNull(position)

}