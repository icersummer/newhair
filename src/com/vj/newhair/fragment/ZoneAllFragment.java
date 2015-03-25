package com.vj.newhair.fragment;

import java.util.LinkedList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.vj.newhair.R;
import com.vj.newhair.adapter.ZoneAllAdapter;
import com.vj.newhair.common.model.ZoneAllItem;
import com.vj.newhair.common.refresh_list.RefreshListView;
import com.vj.newhair.common.refresh_list.RefreshListView.IHListViewListener;
import com.vj.newhair.utils.ImageFetcher;

public class ZoneAllFragment extends Fragment implements OnClickListener, IHListViewListener {

	@InjectView(R.id.list_zone_topic) RefreshListView mListView;
	
	/**
	 * 适配器
	 */
	private ZoneAllAdapter mAdapter;
	/**
	 * 布局：公告区
	 */
	private LinearLayout mLayoutNotice;
	/**
	 * 布局：精华区
	 */
	private LinearLayout mLayoutGood;
	/**
	 * 布局：人气榜
	 */
	private LinearLayout mLayoutHot;
	/**
	 * 布局：积分榜
	 */
	private LinearLayout mLayoutScore;
	
	private ImageFetcher mImageFetcher;
	
	private LinkedList<ZoneAllItem> mListInfos=new LinkedList<ZoneAllItem>();
	
	
	//TODO 为什么这么定义
	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch(msg.what){
			case R.id.ui_show_dialog:
				
				break;
			case R.id.ui_dismiss_dialog:
				
				break;
			case R.id.ui_show_text://网络超时和数据解析异常
				
				break;
			case R.id.ui_update_ui:
				break;
			}
		}
	};
	
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
		
		// 加载4个话题
		addHeadView(inflater);
		
		
		mAdapter = new ZoneAllAdapter(getActivity(), mListInfos, mImageFetcher, mListView);
		mListView.setAdapter(mAdapter);
		mListView.setPullLoadEnable(true);
		mListView.setHListViewListener(this);
		mListView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				
			}
		});
		
		return contentView;
	}



	private void addHeadView(LayoutInflater inflater) {
		// TODO inflater.inflate 作用是？
		View headView = inflater.inflate(R.layout.view_zone_topic_head, null);
		mLayoutNotice=(LinearLayout) headView.findViewById(R.id.layout_notice);
		mLayoutGood=(LinearLayout) headView.findViewById(R.id.layout_good);
		mLayoutHot=(LinearLayout) headView.findViewById(R.id.layout_hot);
		mLayoutScore=(LinearLayout) headView.findViewById(R.id.layout_score);
		mLayoutNotice.setOnClickListener(this);
		mLayoutGood.setOnClickListener(this);
		mLayoutHot.setOnClickListener(this);
		mLayoutScore.setOnClickListener(this);		
	}



	@Override
	public void onClick(View v) {
		// TODO vj
		switch(v.getId()){
		case R.id.layout_notice:
			
			break;
		case R.id.layout_good:
			
			break;
		case R.id.layout_hot:
			
			break;
		case R.id.layout_score:
			
			break;
		default:
			break;
		}
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
