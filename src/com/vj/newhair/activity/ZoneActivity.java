package com.vj.newhair.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.vj.newhair.R;
import com.vj.newhair.adapter.ZoneFragmentAdapter;

public class ZoneActivity extends FragmentActivity{
	
	/**
	 * 用来左右滑动：全部话题、热门标签
	 */
	@InjectView(R.id.vp_zone_view) ViewPager mViewPager;
	
	/**
	 * 两个按钮：全部话题、热门标签
	 */
	@InjectView(R.id.zone_radioGroup) RadioGroup mRadioGroup;
	
	/**
	 * 加载转动的状态条
	 */
	@InjectView(R.id.layout_loading) LinearLayout mLayoutLoading;
	
//	@OnClick(R.id.iv_zone_search)
//	public void onSearchClicked(View v){
//		startActivity(new Intent(ZoneActivity.this, TopicSearchActivity.class));
//	}
//	@OnClick(R.id.iv_zone_publish)
//	public void onPublishClicked(View v){
//		startActivity(new Intent(ZoneActivity.this, PublishActivity.class));
//	}
	
	/**
	 * 适配器，用来处理左右滑动页面
	 */
	private ZoneFragmentAdapter mFragmentAdapter;
	
//	public void onSearchClicked(View v){
//		startActivity(new Intent(ZoneActivity.class, TopicSearchActivity.class));
//	}
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zone);
		ButterKnife.inject(this);
		// 进入到此主逻辑后直接隐藏进度条
		mLayoutLoading.setVisibility(View.GONE);
		
		mFragmentAdapter = new ZoneFragmentAdapter(getSupportFragmentManager(), this);
		mViewPager.setAdapter(mFragmentAdapter);
		// 左右滑动时，触发单选按钮的点击
		mViewPager.setOnPageChangeListener(new OnPageChangeListener(){

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				switch(position){
				case 0:
					mRadioGroup.check(R.id.btn_zone_topic_all);
					break;
				case 1:
					mRadioGroup.check(R.id.btn_zone_hot_tag);
					break;
				default:
					break;
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch(checkedId){
				case R.id.btn_zone_topic_all:
					mViewPager.setCurrentItem(0);
					break;
				case R.id.btn_zone_hot_tag:
					mViewPager.setCurrentItem(1);
					break;
				default:
					break;
				}
			}
		});
		
	}
	
	/**
	 * TODO 这个方法什么作用
	 * @param viewId
	 * @param fragment
	 */
	protected void replaceFragment(int viewId, Fragment fragment){
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(viewId, fragment).commitAllowingStateLoss();
	}
	

}
