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
        final boolean currClick = spiralItem.isChecked();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
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

    /**
     * 所有实现SpiralItem接口的子view设置为该接口，当所有子View 的逻辑相同时可使用
     *
     * @param onCheckedChangeListener
     */
    public void setAllSpiralItemOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child instanceof ISpiralItem) {
                ISpiralItem spiralChild = (ISpiralItem) child;
                spiralChild.addOnCheckedChangeListener(onCheckedChangeListener);
            }
        }
    }
}
