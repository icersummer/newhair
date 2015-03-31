package com.vj.newhair.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.vj.newhair.R;

public class FindHairActivity extends FragmentActivity{
	
	@InjectView(R.id.hair_radioGroup) RadioGroup mRadioGroup;
	@InjectView(R.id.viewpager) ViewPager mViewPager;//TODO what
	private FindHairFragmentAdapter mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_findhair);
		ButterKnife.inject(this);
		
		mAdapter = new FindHairFragmentAdapter(this.getSupportFragmentManager());
		mViewPager.setAdapter(mAdapter);
		
		///////////////////// BEGIN LINSTENER OF RADIO_GROUP //////////////////////////////////////////////
		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch(checkedId){
				case R.id.btn_tecomm_tag:
					mViewPager.setCurrentItem(0);// RECOMMENDATION
					break;
				case R.id.btn_new_tag:
					mViewPager.setCurrentItem(1);// LATEST
					break;
				case R.id.btn_main_topic_tag:
					mViewPager.setCurrentItem(2);// SPECIAL TOPIC
					break;
				}
			}
		});
		mViewPager.setOnPageChangeListener(new OnPageChangeListener(){
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				switch(position){
				case 0:
					mRadioGroup.check(R.id.btn_recomm_tag);
					break;
				case 1:
					mRadioGroup.check(R.id.btn_new_tag);
					break;
				case 2:
					mRadioGroup.check(R.id.btn_main_topic_tag);
				}
			}
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
		});
		///////////////////// END LINSTENER OF RADIO_GROUP //////////////////////////////////////////////
	}
	
	
	

}
