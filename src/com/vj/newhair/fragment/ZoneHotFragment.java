package com.vj.newhair.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.vj.newhair.R;
import com.vj.newhair.adapter.ZoneHotAdapter;

public class ZoneHotFragment extends Fragment{
	@InjectView(R.id.grid_zone_hot_tag) GridView mGridView;
	private ZoneHotAdapter mAdapter;

	public static ZoneHotFragment newInstance(){
		ZoneHotFragment fragment=new ZoneHotFragment();
		Bundle bundle=new Bundle();
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View contentView = inflater.inflate(R.layout.view_zone_hot_tag, container, false);
		ButterKnife.inject(this,  contentView);
		mAdapter=new ZoneHotAdapter(getActivity());
		mGridView.setAdapter(mAdapter);
		return contentView;
	}
	
}
