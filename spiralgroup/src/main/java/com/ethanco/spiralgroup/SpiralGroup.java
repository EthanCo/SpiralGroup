package com.ethanco.spiralgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.ethanco.spiralgroup.abs.ISpiralItem;
import com.ethanco.spiralgroup.abs.OnCheckedChangeListener;

/**
 * Created by EthanCo on 2016/11/11.
 */

public class SpiralGroup extends LinearLayout {
    public SpiralGroup(Context context) {
        super(context);
    }

    public SpiralGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SpiralGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //安排Child
    public void scheduleChild(ISpiralItem spiralItem) {
        if (spiralItem instanceof View) {
            if (isNotAdd((View) spiralItem)) {
                spiralItem.setChecked(true);
                return;
            }
        }

        final boolean currClick = spiralItem.isChecked();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (isNotAdd(child)) {
                return;
            }

            if (child instanceof ISpiralItem) {
                ISpiralItem spiralChild = (ISpiralItem) child;

                if (spiralChild.equals(spiralItem)) {
                    spiralChild.setChecked(!currClick);
                } else {
                    spiralChild.setChecked(currClick);
                }
            }
        }
    }

    //如果是tag为R.string.tag_not_add的child 返回true，否则返回false
    private boolean isNotAdd(View spiralItem) {
        String tagNotAdd = getContext().getString(R.string.tag_not_add);
        View spiralChildView = spiralItem;
        return tagNotAdd.equals(spiralChildView.getTag());
    }

    /**
     * 所有实现SpiralItem接口的子view设置为该接口，当所有子View 的逻辑相同时可使用
     *
     * @param onCheckedChangeListener
     */
    public void setAllSpiralItemOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child instanceof ISpiralItem && !isNotAdd(child)) { //child的tag为R.string.tag_not_add的不添加
                ISpiralItem spiralChild = (ISpiralItem) child;
                spiralChild.addOnCheckedChangeListener(onCheckedChangeListener);
            }
        }
    }
}
