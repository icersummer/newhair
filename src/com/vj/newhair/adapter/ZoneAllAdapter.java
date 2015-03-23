package com.vj.newhair.adapter;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.vj.newhair.R;
import com.vj.newhair.common.model.ZoneAllItem;
import com.vj.newhair.common.refresh_list.RefreshListView;
import com.vj.newhair.utils.ImageFetcher;

public class ZoneAllAdapter extends BaseAdapter {
	private Context mContext;
	public LinkedList<ZoneAllItem> mListInfo;
	private ImageFetcher mImageFetcher;
	/**
	 * Initialize providing a single target image size (used for both width and height);
	 * 400px for this case
	 */
	private ImageFetcher mImagePic;
	private RefreshListView mListView;
	private int color;

	public ZoneAllAdapter(Context context,
			LinkedList<ZoneAllItem> mListInfos, ImageFetcher mImageFetcher,
			RefreshListView mListView) {
		// TODO Auto-generated constructor stub
		this.mContext=context;
		this.mListInfo=mListInfos;
		this.mImageFetcher=mImageFetcher;
		this.mImagePic=new ImageFetcher(context, 400);
		this.mImagePic.setLoadingImage(R.drawable.hairscan_loading);
		color=context.getResources().getColor(R.color.zone_list_username);
		this.mListView=mListView;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mListInfo==null?1:mListInfo.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	public void addItemLast(List<ZoneAllItem> datas){
		mListInfo.addAll(datas);
	}
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder holder;
		if(convertView==null){
			convertView=LayoutInflater.from(mContext).inflate(R.layout.item_topic, null);
			holder=new ViewHolder(convertView);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		
		final ZoneAllItem info = mListInfo.get(position);
		if(position==0){
			holder.m
		}
	}
	
	public class ViewHolder{
		
		CircleImageView mIvHead;
		TextView mTvName;
		
		
	}

}
