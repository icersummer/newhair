package com.vj.newhair.activity;

import butterknife.ButterKnife;
import android.R;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class ZoneActivity extends FragmentActivity{
	
	ViewPager mViewPager;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zone);
		ButterKnife.inject(this);
		
	}
	

}
