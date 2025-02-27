package com.feb.lib_common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.feb.lib_common.R;
import com.feb.lib_common.ResourceUtil;
import com.feb.lib_common.databinding.CommonViewEmptyBinding;

public class CommonEmptyView extends FrameLayout {

    private CommonViewEmptyBinding mBinding;

    public CommonEmptyView(@NonNull Context context) {
        this(context, null, -1);
    }

    public CommonEmptyView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public CommonEmptyView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.common_view_empty, this, true);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CommonEmptyView);
            int imageRes = typedArray.getInt(R.styleable.CommonEmptyView_emptyImageRes, -1);
            String content = typedArray.getString(R.styleable.ScanTextLayout_scanTextColor);
            typedArray.recycle();
            if (imageRes != -1) {
                setImg(imageRes);
            }
            if (!TextUtils.isEmpty(content)) {
                setContent(content);
            }
        }
    }


    public void setContent(String content) {
        mBinding.tvContent.setText(content);
    }

    public void setImg(int res) {
        mBinding.image.setImageResource(res);
    }


    public void setFontColor(int color) {
        mBinding.tvContent.setTextColor(color);
    }


    public void setImageWH(int w, int h) {
        ViewGroup.LayoutParams layoutParams = mBinding.image.getLayoutParams();
        layoutParams.width = ResourceUtil.getDimen(w);
        layoutParams.height = ResourceUtil.getDimen(h);
        mBinding.image.setLayoutParams(layoutParams);
    }
}
