package com.ethanco.spiralgroup;

import android.view.View;
import android.view.ViewGroup;

import com.ethanco.spiralgroup.abs.ISpiralItem;
import com.ethanco.spiralgroup.abs.OnCheckedChangeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description Spiral实施者
 * Created by EthanCo on 2016/11/11.
 */

public class SpiralExecutor implements ISpiralItem {
    protected SpiralGroup mParent;
    protected ISpiralItem mChild;
    protected boolean isChecked;

    private List<OnCheckedChangeListener> onCheckedChangeListeners = new ArrayList<>();

    public SpiralExecutor() {

    }

    public ISpiralItem getChild() {
        return mChild;
    }

    public void setViewAndParent(View view) {
        if (view instanceof ISpiralItem) {
            mChild = (ISpiralItem) view;
        } else {
            throw new IllegalStateException("view must be SpiralItem");
        }

        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent instanceof SpiralGroup) {
            mParent = (SpiralGroup) parent;
        } else {
            throw new IllegalStateException("parent must be SpiralGroup");
        }
    }

    @Override
    public void addOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        if (!onCheckedChangeListeners.contains(onCheckedChangeListener)) {
            onCheckedChangeListeners.add(onCheckedChangeListener);
        }
    }

    @Override
    public void setChecked(boolean isCheck) {
        isChecked = isCheck;

        for (OnCheckedChangeListener onCheckedChangeListener : onCheckedChangeListeners) {
            onCheckedChangeListener.onCheckedChanged(mChild, isChecked);
        }
    }

    @Override
    public boolean isChecked() {
        return false;
    }

    @Override
    public void toggle() {
        throw new IllegalStateException("not support toggle()");
    }

    public void onClick(View view) {
        if (mParent == null) return;

        mParent.scheduleChild(mChild);
    }
}
