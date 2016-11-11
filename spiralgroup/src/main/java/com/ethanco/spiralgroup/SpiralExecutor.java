package com.ethanco.spiralgroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description Spiral实施者
 * Created by EthanCo on 2016/11/11.
 */

public class SpiralExecutor {
    protected SpiralGroup mParent;
    protected boolean isChecked;

    private List<OnCheckedChangeListener> onCheckedChangeListeners = new ArrayList<>();

    public void addOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        if (!onCheckedChangeListeners.contains(onCheckedChangeListener)) {
            onCheckedChangeListeners.add(onCheckedChangeListener);
        }
    }
}
