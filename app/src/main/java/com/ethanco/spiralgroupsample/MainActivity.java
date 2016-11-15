package com.ethanco.spiralgroupsample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ethanco.spiralgroup.abs.ISpiralItem;
import com.ethanco.spiralgroup.abs.OnCheckedChangeListener;
import com.ethanco.spiralgroupsample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements OnCheckedChangeListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //当所有的spiralGroup的ISpiral子类添加同一个addOnCheckedChangeListener接口时，可使用setAllSpiralItemOnCheckedChangeListener快速设置
        binding.layoutSpiralGroup.setAllSpiralItemOnCheckedChangeListener(this);
        /*binding.spiralItem1.addOnCheckedChangeListener(this);
        binding.spiralItem2.addOnCheckedChangeListener(this);
        binding.spiralItem3.addOnCheckedChangeListener(this);*/
    }

    @Override
    public void onCheckedChanged(ISpiralItem view, boolean isChecked) {
        if (view instanceof View) {
            View v = (View) view;

            View viewImgView = v.findViewWithTag(getString(R.string.tag_imageview));
            View viewTextView = v.findViewWithTag(getString(R.string.tag_textview));

            @DrawableRes int imageRes = isChecked ? R.mipmap.ic_test_21 : R.mipmap.ic_test_20;
            @ColorRes int textColor = isChecked ? R.color.colorWhite : R.color.colorBlack;


            if (isChecked) {
                v.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            } else {
                v.setBackgroundResource(R.drawable.selector_1);
            }
            if (viewImgView != null) {
                ImageView imgView = (ImageView) viewImgView;
                imgView.setBackgroundResource(imageRes);
            }
            if (viewTextView != null) {
                TextView textView = (TextView) viewTextView;
                textView.setTextColor(getResources().getColor(textColor));
            }
        }
    }
}
