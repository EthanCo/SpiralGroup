package com.ethanco.spiralgroup.abs;

import android.widget.Checkable;

/**
 * @Description TODO
 * Created by EthanCo on 2016/11/11.
 */

public interface ISpiralItem extends Checkable {
    void addOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener);
}
