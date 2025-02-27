package com.feb.module_home.find

import android.content.Intent
import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import com.alibaba.android.arouter.facade.annotation.Route
import com.feb.lib_common.GlideEngine
import com.feb.lib_common.base.BaseMVVMActivity
import com.feb.lib_common.constant.ARouterConstants
import com.feb.lib_common.view.ItemTouchHelperCallback
import com.feb.module_home.R
import com.feb.module_home.databinding.ActivityPostUpdatesBinding
import com.feb.module_home.find.adapter.AddPhotoAdapter
import com.feb.module_home.find.adapter.TrendPictureRvAdapter
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.style.PictureSelectorStyle
import com.luck.picture.lib.style.SelectMainStyle
import java.util.ArrayList

@Route(path = ARouterConstants.FIND_POST_UPDATES)
class FindPostUpdatesActivity : BaseMVVMActivity<ActivityPostUpdatesBinding>() {


    lateinit var adapter: TrendPictureRvAdapter
    override fun getLayoutId(): Int {
        return R.layout.activity_post_updates
    }

    override fun initView() {
        mBinding.customBar.apply {

            setColor(Color.WHITE)
        }
        adapter = TrendPictureRvAdapter(1)
        mBinding.rvAddPhoto.adapter = adapter
        val myItemTouchHelper = ItemTouchHelperCallback(adapter)
        val itemTouchHelper = ItemTouchHelper(myItemTouchHelper)
        itemTouchHelper.attachToRecyclerView(mBinding.rvAddPhoto)
    }

    override fun initData() {

    }

    override fun initListener() {
        super.initListener()
        adapter.onItemChildClickListener = object : TrendPictureRvAdapter.OnItemChildClickListener {
            override fun onClick(view: View, position: Int) {
                if (view.id == R.id.iv_delete) {
                    if (position >= adapter.data.size) {
                        return
                    }
                    adapter.data.removeAt(position)
                    adapter.notifyItemRemoved(position)
                    adapter.notifyItemRangeChanged(position, adapter.itemCount - position)
                } else if (view.id == R.id.iv_trend_content) {
                    val itemViewType = adapter.getItemViewType(position)
                    if (itemViewType == TrendPictureRvAdapter.ADD_VIEW_TYPE)
                        selectPicture(1)
                }
            }
        }
    }

    fun selectPicture(type: Int) {

        // 根据选择类型决定是选择图片还是视频
        if (type == 1) {

            PictureSelector.create(this)
                .openGallery(SelectMimeType.ofImage())
                .setImageEngine(GlideEngine.createGlideEngine())
                .setMaxSelectNum(9)
                .setSelectedData(adapter.data)
                .forResult(PictureConfig.CHOOSE_REQUEST)
        } else {
//            PictureSelector.create(this)
//                .openGallery(SelectMimeType.ofVideo()) // 选择视频
//                .imageEngine(GlideEngine.createGlideEngine()) // 图片加载引擎
//                .maxSelectNum(1) // 最大选择数量
//                .videoMaxSecond(60) // 最大视频时长
//                .recordVideoSecond(60) // 最大录制时长
//                .setSelectorStyle(selectorStyle) // 设置自定义UI样式
//                .forResult(PictureConfig.CHOOSE_REQUEST) // 结果回调
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                PictureConfig.CHOOSE_REQUEST -> {
                    if (data != null) {
                        val localMedia = PictureSelector.obtainSelectorList(data)
                        adapter.setNewData(localMedia as ArrayList<LocalMedia>)
                    }
                }
            }
        }
    }


}