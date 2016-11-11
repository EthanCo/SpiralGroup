package com.ethanco.spiralgroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * Created by EthanCo on 2016/11/11.
 */

public class SpiralRelativeLayout extends RelativeLayout implements ISpiralItem, View.OnClickListener {
    protected SpiralGroup mParent;
    protected boolean isChecked;

    public SpiralRelativeLayout(Context context) {
        super(context);
        init(context);
    }

    public SpiralRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs);
        init(context);
    }

    public SpiralRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
        init(context);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SpiralRelativeLayout);
        isChecked = ta.getBoolean(R.styleable.SpiralRelativeLayout_spiralChecked, false);
        ta.recycle();
    }

    private void init(Context context) {
        setClickable(true);
        setOnClickListener(this);
        setChecked(isChecked);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        ViewGroup parent = (ViewGroup) getParent();
        if (parent instanceof SpiralGroup) {
            mParent = (SpiralGroup) parent;
        } else {
            throw new IllegalStateException("parent must be SpiralGroup");
        }
    }

    @Override
    public void setChecked(boolean ischeck) {
        isChecked = ischeck;

        for (OnCheckedChangeListener onCheckedChangeListener : onCheckedChangeListeners) {
            onCheckedChangeListener.onCheckedChanged(this, ischeck);
        }
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        throw new IllegalStateException("not support toggle()");
    }

    @Override
    public void onClick(View view) {
        if (mParent == null) return;

        if (isChecked) {
            return;
        }

        mParent.scheduleChild(this);
    }

    private List<OnCheckedChangeListener> onCheckedChangeListeners = new ArrayList<>();

    public void addOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        if (!onCheckedChangeListeners.contains(onCheckedChangeListener)) {
            onCheckedChangeListeners.add(onCheckedChangeListener);
        }
    }
}
