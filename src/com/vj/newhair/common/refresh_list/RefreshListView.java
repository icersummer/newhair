package com.vj.newhair.common.refresh_list;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.vj.newhair.R;
import com.vj.newhair.waterfall.widget.XListViewFooter;
import com.vj.newhair.waterfall.widget.XListViewHeader;

/**
 * 下拉屏幕时，做出响应；
 * 同时显示“显示更多……”
 * TODO note:这里使用了一个新的类：DecelerateInterpolator
 * TODO MotionEvent is what ?
 */
/**
 * @author gjia
 *
 */
public class RefreshListView extends ListView implements OnScrollListener{

	/**
	 * save event y
	 */
	private float mLastY = -1;
	/**
	 * USED for scroll back
	 */
	private Scroller mScroller;
	/**
	 * user's scroll listener
	 */
	private OnScrollListener mScrollListener;
	
	/**
	 * the interface to trigger refresh and load more
	 */
	private IHListViewListener mListViewListener;
	
	private boolean mEnablePullLoad;
	
	/**
	 * -- header view
	 */
	public XListViewHeader mHeaderView;
	
	/**
	 * header view content, use it to calculate the Header's height.
	 * And hide it when disable pull refresh
	 */
	private RelativeLayout mHeaderViewContent;
	
	/**
	 * 上次更新时间
	 */
	private TextView mHeaderTimeView;
	
	/**
	 * header view's height
	 */
	private int mHeaderViewHeight;
	
	private boolean mEnablePullRefresh = true;
	
	/**
	 * is refreshing
	 */
	private boolean mPullRefreshing = false; 
	
	/**
	 * -- footer view
	 */
	public XListViewFooter mFooterView;

	/**
	 * TODO what for ?
	 */
	private boolean mPullLoading;
	
	private boolean mIsFooterReady = false;
	
	/**
	 * total list items, used to detect is at the bottom of listview
	 */
	private int mTotalItemCount;
	
	
	// for mScroller, scroll back from header or footer
	private int mScrollBack;
	private final static int SCROLLBACK_HEADER = 0;
	private final static int SCROLLBACK_FOOTER = 1;
	
	/**
	 * scroll back duration
	 */
	private final static int SCROLL_DURATION = 400;
	
	/**
	 * when pull up >= 50 px at bottom, trigger load more.
	 */
	private final static int PULL_LOAD_MORE_DELTA = 40;
	
	/**
	 * support iOS like pull feature
	 */
	private final static float OFFSET_RADIO = 1.8f;


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
		mScroller = new Scroller(context, new DecelerateInterpolator());
		// XListView needs the scroll event, and it will dispatch the event to user's listener (as a proxy)
		super.setOnScrollListener(this);
		
		// init header view
		mHeaderView = new XListViewHeader(context);
		mHeaderViewContent = (RelativeLayout) mHeaderView.findViewById(R.id.xlistview_header_content);
		mHeaderTimeView = (TextView) mHeaderView.findViewById(R.id.xlistview_header_time);
		addHeaderView(mHeaderView);
		
		// init footer view
		mFooterView = new XListViewFooter(context);
		
		// TODO what's ViewTreeObserver ?
		// init header height
		mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener(){
					@Override
					public void onGlobalLayout() {
						// TODO Auto-generated method stub
						mHeaderViewHeight = mHeaderViewContent.getHeight();
					}
				} );
	}

	@Override
	public void setAdapter(ListAdapter adapter) {
		// TODO Auto-generated method stub
		// make sure XListViewFooter is the last footer view, and only add once
		if(mIsFooterReady == false){
			mIsFooterReady = true;
//			if(SHOWFOOTER)
				addFooterView(mFooterView);
		}
		super.setAdapter(adapter);
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		// send to user's listener
		mTotalItemCount = totalItemCount;
		if(mScrollListener != null){
			mScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		if(mScrollListener!=null){
			mScrollListener.onScrollStateChanged(view, scrollState);
		}
	}
	
	/**
	 * 继承自 AbsListView
	 */
	@Override
	public void setOnScrollListener(OnScrollListener listener) {
		// TODO Auto-generated method stub
		mScrollListener = listener;
	}

	/**
	 * you can listen ListView.OnScrollListener or this one.
	 * It will invokde onXScrolling when header/footer scroll back.
	 */
	public interface OnXScrollListener extends OnScrollListener{
		public void onXScrolling(View view);
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

	/**
	 * 覆写 AbsListView.onTouchEvent()
	 */
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		if(mLastY == -1){
			mLastY = ev.getRawY();
		}
		
		switch(ev.getAction()){
		case MotionEvent.ACTION_DOWN:// 下滑？
			mLastY = ev.getRawY();
			break;
		case MotionEvent.ACTION_MOVE://上下左右移动？
			final float deltaY = ev.getRawY() - mLastY;
			mLastY = ev.getRawY();
			//TODO: getFirstVisiblePosition() is what?
			if(getFirstVisiblePosition() == 0 
					&& (mHeaderView.getVisiableHeight() > 0 || deltaY > 0)){
				// the first item is showing, header has shown or pull down.
				updateHeaderHeight(deltaY / OFFSET_RADIO);
				invokeOnScrolling();
			} else if(getLastVisiblePosition() == mTotalItemCount-1
					&& (mFooterView.getBottomMargin() > 0 || deltaY < 0)){
				// last item, already pulled up or want to pull up.
				updateFooterHeight(-deltaY / OFFSET_RADIO);
			}
			break;
		default:
			// reset
			mLastY = -1;
			if(getFirstVisiblePosition() == 0) {
				// invoke refresh
				if(mEnablePullRefresh && mHeaderView.getVisiableHeight() > mHeaderViewHeight){
					mPullRefreshing = true;
					mHeaderView.setState(XListViewHeader.STATE_REFRESHING);
					if(mListViewListener != null){
						mListViewListener.onRefresh();
					}
				}
				resetHeaderHeight();
			} else if(getLastVisiblePosition() == mTotalItemCount - 1) {
				// invoke load more
				if(mEnablePullLoad && mFooterView.getBottomMargin() > PULL_LOAD_MORE_DELTA){
					startLoadMore();
				}
				resetFooterHeight();
			}
			break;
		}
		return super.onTouchEvent(ev);
	}


	private void updateHeaderHeight(float delta) {
		// TODO Auto-generated method stub
		mHeaderView.setVisibleHeight((int)delta + mHeaderView.getVisiableHeight());
		// 未处于刷新状态，更新箭头
		if(mEnablePullRefresh && !mPullRefreshing){
			if(mHeaderView.getVisiableHeight() > mHeaderViewHeight){
				mHeaderView.setState(XListViewHeader.STATE_READY);
			}else{
				mHeaderView.setState(XListViewHeader.STATE_NORMAL);
			}
		}
		// scroll to top each time
		setSelection(0);
	}
	
	/**
	 * reset header view's height
	 */
	private void resetHeaderHeight(){
		int height = mHeaderView.getVisiableHeight();
		if(height == 0) // not visible
			return;
		
		// refreshing and header isn't shown fully. do nothing.
		if(mPullRefreshing && height < mHeaderViewHeight){
			return;
		}
		int finalHeight = 0; // default: scroll back to dismiss header.
		// is refreshing, just scroll back to show all the header.
		if(mPullRefreshing && height > mHeaderViewHeight) {
			finalHeight = mHeaderViewHeight;
		}
		mScrollBack = SCROLLBACK_HEADER;
		// TODO how to use good of this API
		mScroller.startScroll(0, height, finalHeight - height, SCROLL_DURATION);
		// trigger computeScroll
		invalidate();
	}
	
	
	private void updateFooterHeight(float delta) {
		// TODO Auto-generated method stub
		int height=mFooterView.getBottomMargin() + (int)delta;
		if(mEnablePullLoad && !mPullLoading){
			// height enough to invoke load more
			if(height > PULL_LOAD_MORE_DELTA){
				mFooterView.setState(XListViewFooter.STATE_READY);
			} else {
				mFooterView.setState(XListViewFooter.STATE_NORMAL);
			}
		}
		mFooterView.setBottomMargin(height);
		
		// scroll to bottom
//		setSelection(mTotalItemCount - 1);
	}
	
	private void resetFooterHeight(){
		int bottomMargin = mFooterView.getBottomMargin();
		if(bottomMargin > 0){
			mScrollBack = SCROLLBACK_FOOTER;
			mScroller.startScroll(0, bottomMargin, 0, -bottomMargin, SCROLL_DURATION);
			invalidate();
		}
	}
	
	private void startLoadMore(){
		mPullLoading = true;
		mFooterView.setState(XListViewFooter.STATE_LOADING);
		if(mListViewListener!=null){
			mListViewListener.onLoadMore();
		}
	}

	private void invokeOnScrolling(){
		if(mScrollListener instanceof OnXScrollListener){
			OnXScrollListener l = (OnXScrollListener) mScrollListener;
			l.onXScrolling(this);
		}
	}
	
	public void setHListViewListener(IHListViewListener lis){
		mListViewListener = lis;
	}

}
