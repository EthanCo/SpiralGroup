# SpiralGroup #
一个类似于RadioGroup的ViewGroup，支持所有View的互斥  

  

![](/SpiralGroup.gif) |  ![](http://p1.bpimg.com/567571/86bd38603040ee69.gif)   


## 添加依赖 ##
### Step 1. Add the JitPack repository to your build file ###
Add it in your root build.gradle at the end of repositories:  

	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}

### Step 2. Add the dependency ###

	dependencies {
        compile 'com.github.EthanCo:SpiralGroup:1.0.0'
	}

## 使用 ##
###使用SpiralGroup布局，内部嵌套Spiral_______Layout布局

	<com.ethanco.spiralgroup.SpiralGroup
            android:id="@+id/layout_spiral_group"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">

            <com.ethanco.spiralgroup.SpiralRelativeLayout
                android:id="@+id/spiral_item_1"
                android:layout_width="match_parent"
                android:layout_height="100dp"
                android:layout_margin="5dp"
                android:background="@drawable/selector_1"
                app:spiralChecked="true">
            </com.ethanco.spiralgroup.SpiralRelativeLayout>

            <com.ethanco.spiralgroup.SpiralRelativeLayout
                android:id="@+id/spiral_item_2"
                android:layout_width="match_parent"
                android:layout_height="100dp"
                android:layout_margin="5dp"
                android:background="@drawable/selector_1"
                app:spiralChecked="false">
            </com.ethanco.spiralgroup.SpiralRelativeLayout>
    </com.ethanco.spiralgroup.SpiralGroup>  

已提供以下布局

SpiralFrameLayout (继承自FrameLayout)  
SpiralLinearLayout (继承自LinearLayout)  
SpiralRelativeLayout (继承自RelativeLayout)  

在这些布局内，可添加任意的View，之后，在OnCheckedChangeListener监听中将实现互斥

### 在Activity中设置监听  

	spiralLayout1.addOnCheckedChangeListener(this);
    spiralLayout2.addOnCheckedChangeListener(this);

> 如果SpiralGroup的所有OnCheckedChangeListener都为同一个，则可使用  

	layoutSpiralGroup.setAllSpiralItemOnCheckedChangeListener(this);  

### 实现监听  

	 @Override
    public void onCheckedChanged(ISpiralItem view, boolean isChecked) {
        if (view instanceof View) {
            View v = (View) view;

			//尝试通过TAG查找View
            View viewImgView = v.findViewWithTag(getString(R.string.tag_imageview));
            View viewTextView = v.findViewWithTag(getString(R.string.tag_textview));

            @DrawableRes int imageRes = isChecked ? R.mipmap.ic_test_21 : R.mipmap.ic_test_20;
            @ColorRes int textColor = isChecked ? R.color.colorWhite : R.color.colorBlack;


            if (isChecked) {
                v.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            } else {
                v.setBackgroundResource(R.drawable.selector_1);
            }
			//如果存在，则进行设置
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


