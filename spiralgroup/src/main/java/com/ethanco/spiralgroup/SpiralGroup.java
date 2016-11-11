package com.ethanco.spiralgroup;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child instanceof ISpiralItem) {
                ISpiralItem spiralItem = (ISpiralItem) child;
                if (i == 0) {
                    spiralItem.setChecked(true);
                } else {
                    spiralItem.setChecked(false);
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    //安排Child
    public void scheduleChild(ISpiralItem spiralItem) {
        boolean currClick = spiralItem.isChecked();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child instanceof ISpiralItem) {
                ISpiralItem spiralChild = (ISpiralItem) child;
                if (child.equals(spiralItem)) {
                    spiralChild.setChecked(!currClick);
                } else {
                    spiralChild.setChecked(currClick);
                }
            }
        }
    }
}
