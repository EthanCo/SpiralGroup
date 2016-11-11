package com.ethanco.spiralgroupsample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ethanco.spiralgroup.ISpiralItem;
import com.ethanco.spiralgroup.OnCheckedChangeListener;
import com.ethanco.spiralgroupsample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements OnCheckedChangeListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.spiralItem1.addOnCheckedChangeListener(this);
        binding.spiralItem2.addOnCheckedChangeListener(this);
        binding.spiralItem3.addOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(ISpiralItem view, boolean isChecked) {
        View v = (View) view;
        if (isChecked) {
            v.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        } else {
             v.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
    }
}
