package com.example.test2;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class ViewPageListener implements OnPageChangeListener {
	private ViewPager viewPager;
	private int bmpW;
	private int offset;
	private int one;
	private int two;
	private int curIndex = 0 ;
	private ImageView imageView ;

	public ViewPageListener(ViewPager viewPager, int bmpW, int offset,ImageView imageView) {
		this.viewPager = viewPager;
		this.bmpW = bmpW;
		this.offset = offset;
		one = offset * 2 + bmpW;
		this.imageView = imageView ;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int position) {
		
		Animation animation = new TranslateAnimation(one * curIndex, one * position, 0 ,0 ) ;
		curIndex = position ;
		animation.setFillAfter(true);
		animation.setDuration(300);
		imageView.startAnimation(animation) ;
		
	}

}
