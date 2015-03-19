package com.vj.newhair.common.refresh_list;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

public class RefreshListView extends ListView implements OnScrollListener{

	public RefreshListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public RefreshListView(Context context, AttributeSet attrs) {
		super(context,attrs);
	}
	public RefreshListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScrollStateChanged(AbsListView arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * implements this interface to get refresh/load more event.
	 *
	 */
	public interface IHListViewListener {
		public void onRefresh();
		public void onLoadMore();
	}

}
