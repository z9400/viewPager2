package com.example.test2;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ViewPager viewPager ;
	private List<View> views ;
	private ImageView imageView ;
	private int bmpW ;
	private int offset ;
	private int[] frames = new int[]{
			R.layout.fragment_1,
			R.layout.fragment_2,
			R.layout.fragment_3,
			R.layout.fragment_4,
			R.layout.fragment_5
	} ;
	
	private String[] titles = new String[]{
			"首页",
			"分类",
			"附近",
			"推荐",
			"更多"
	} ;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		InitImageView() ;
		InitTextView() ;
		InitViewPager() ;
	}
	
	private void InitViewPager(){
		viewPager = (ViewPager)findViewById(R.id.vPager) ;
		views = new ArrayList<View>() ;
		LayoutInflater inflater = getLayoutInflater() ;
		for(int i : frames){
			views.add(inflater.inflate(i, null)) ;
		}
		
		viewPager.setAdapter(new PageAdapter(views));
		viewPager.setCurrentItem(0);
		viewPager.setOnPageChangeListener(new ViewPageListener(viewPager,bmpW,offset,imageView));
	}
	
	private void InitImageView(){
		imageView = (ImageView)findViewById(R.id.cursor) ;
		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.a).getWidth() ;
		
		DisplayMetrics dm = new DisplayMetrics() ;
		
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels ;
		
		offset = (screenW/titles.length-bmpW)/2 ;
		Matrix matrix = new Matrix() ;
		matrix.postTranslate(offset, 0) ;
		imageView.setImageMatrix(matrix);
		
		
	}
	
	
	private void InitTextView(){
		LayoutInflater inflater = getLayoutInflater() ;
		LinearLayout layout = (LinearLayout)findViewById(R.id.layout1) ;
		for(int i =0;i<titles.length;i++){
			TextView text = new TextView(this) ;
			text.setTextSize(getResources().getDimension(R.dimen.text_size));
			text.setGravity(Gravity.CENTER);
			text.setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.MATCH_PARENT,
					1
			));
			
			text.setText(titles[i]);
			text.setOnClickListener(new MyOnClickListener(i));
			layout.addView(text);
		}
	}
	
	private class MyOnClickListener implements OnClickListener{
		int index ;
		public MyOnClickListener(int i) {
				index = i ;
		}
		
		@Override
		public void onClick(View v) {
			viewPager.setCurrentItem(index);
		}
		
	}

}
