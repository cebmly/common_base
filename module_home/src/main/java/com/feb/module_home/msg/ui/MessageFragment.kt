package com.feb.module_home.msg.ui

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.feb.lib_common.base.BaseMVVMFragment
import com.feb.module_home.R
import com.feb.module_home.databinding.ChatMessageFragmentBinding
import com.feb.module_home.msg.adapter.ChatMsgAdapter

class MessageFragment : BaseMVVMFragment<ChatMessageFragmentBinding>() {

    val adapter = ChatMsgAdapter()
    override fun getLayoutId(): Int {
        return R.layout.chat_message_fragment
    }

    override fun initView() {
        mBinding.msgListRv.layoutManager = LinearLayoutManager(activity)
        mBinding.msgListRv.adapter = adapter
    }

    override fun initData() {
        val data = mutableListOf<String>()
        for (i in 0 until 20) {
            data.add("${i}")
        }
        adapter.setNewData(data)
    }

    companion object {
        @JvmStatic
        fun newInstance(type: Int) = MessageFragment().apply {
            arguments = Bundle().apply {
                putInt("type", type)
            }
        }
    }
}