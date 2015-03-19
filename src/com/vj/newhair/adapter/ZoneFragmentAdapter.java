package com.vj.newhair.adapter;

import com.vj.newhair.fragment.ZoneAllFragment;
import com.vj.newhair.fragment.ZoneHotFragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ZoneFragmentAdapter extends FragmentPagerAdapter{

	private final int pageCount = 2;

	public ZoneFragmentAdapter(FragmentManager fm){
		super(fm);
	}
	public ZoneFragmentAdapter(FragmentManager supportFragmentManager,
			Context context) {
		// TODO Auto-generated constructor stub
		super(supportFragmentManager);
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		switch(position){
		case 0:
			return ZoneAllFragment.newInstance();
		case 1:
			return ZoneHotFragment.newInstance();
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return pageCount ;
	}
	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return POSITION_NONE;
	}
	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
