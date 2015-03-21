package com.vj.newhair.common.refresh_list;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

public class RefreshListView extends ListView implements OnScrollListener{

	private boolean mEnablePullLoad;
	
	/**
	 * -- footer view
	 */
	public XListViewFooter mFooterView;

	/**
	 * TODO what for ?
	 */
	private boolean mPullLoading;

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
	
	private void initWithContext(Context context){
		
		// init footer view
		mFooterView = new XListViewFooter(context);
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

	/**
	 * enable or disable pull up load more feature
	 * @param b
	 */
	public void setPullLoadEnable(boolean enable) {
		// TODO Auto-generated method stub
		mEnablePullLoad=enable;
		if(mEnablePullLoad){
			mFooterView.hide();
			mFooterView.setOnClickListener(null);
		}else{
			mPullLoading=false;
		}
	}

}
