package com.vj.newhair.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.ButterKnife;

import com.vj.newhair.R;
import com.vj.newhair.common.refresh_list.RefreshListView.IHListViewListener;

public class ZoneAllFragment extends Fragment implements OnClickListener, IHListViewListener {

	public static ZoneAllFragment newInstance(){
		ZoneAllFragment fragment = new ZoneAllFragment();
		Bundle bundle = new Bundle();
		fragment.setArguments(bundle);
		return fragment;
	}
	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// View android.view.LayoutInflater.inflate(XmlPullParser parser, ViewGroup root, boolean attachToRoot)
		View contentView = inflater.inflate(R.layout.view_zone_all, container, false);
		// 用来加载LAYOUT - VIEW_ZONE_ALL中的RefreshListView - list_zone_topic
		ButterKnife.inject(this, contentView);
		addHeadView(inflater);
		
		
		return contentView;
	}



	private void addHeadView(LayoutInflater inflater) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		
	}

}
