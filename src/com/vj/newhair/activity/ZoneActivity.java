package com.vj.newhair.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import butterknife.ButterKnife;

import com.vj.newhair.R;

public class ZoneActivity extends FragmentActivity{
	
	ViewPager mViewPager;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zone);
		ButterKnife.inject(this);
		
	}
	

}
