package com.vj.newhair.adapter;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.vj.newhair.common.model.FindHairItem;
import com.vj.newhair.utils.ImageFetcher;

public class FindHairAdapter extends BaseAdapter {
	private Context context;
	public LinkedList<FindHairItem> mListInfos;
	private ImageFetcher mImageFetcher;
	
	public FindHairAdapter(Context context, ImageFetcher mImageFetcher) {
		// TODO Auto-generated constructor stub
		this.context= context;
		this.mImageFetcher= mImageFetcher;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mListInfos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mListInfos.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

    public void addItemLast(List<FindHairItem> datas) {
   	 mListInfos.addAll(datas);
    }

    public void addItemTop(List<FindHairItem> datas) {
        for (FindHairItem info : datas) {
       	 mListInfos.addFirst(info);
        }
    }
    
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder= null;
		if(convertView==null){
			
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		return null;
	}
	
	public class ViewHolder{
		
	}

}
