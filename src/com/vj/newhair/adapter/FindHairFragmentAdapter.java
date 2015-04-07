package com.vj.newhair.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vj.newhair.fragment.FindHairFragment;

public class FindHairFragmentAdapter extends FragmentPagerAdapter{
	
	private int pageCount= 3;

	public FindHairFragmentAdapter(FragmentManager fm) {
		// TODO Auto-generated constructor stub
		super(fm);
	}
	
	public FindHairFragmentAdapter(FragmentManager fm, Context context){
		super(fm);
	}
	

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		switch(position){
		case 0://TODO why it's nothing here
		case 1:
			return FindHairFragment.newInstance(position);
		case 2:
//			return MainTopicFragment.newInstance();
		}
		return null;
	}
	
	
	public CharSequence getPageTitle(int position){
		return null;
	}
	
	public int getItemPosition(Object object){
		return POSITION_NONE;
	}
	
	
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return pageCount;
	}
	
	

}
