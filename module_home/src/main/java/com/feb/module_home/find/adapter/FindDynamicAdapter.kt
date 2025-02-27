package com.feb.module_home.find.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.feb.module_home.R

class FindDynamicAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.find_list_dynamic_item) {

    init {

        addChildClickViewIds(R.id.iv_promulgator_avatar,R.id.iv_call_answer,R.id.iv_like)
    }


    override fun convert(holder: BaseViewHolder, item: String) {

    }
}