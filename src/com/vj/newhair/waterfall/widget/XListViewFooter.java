package com.vj.newhair.waterfall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vj.newhair.R;

/**
 * TODO
 * what's this class used for ?
 * 
 * XListView's footer
 *
 */
public class XListViewFooter extends LinearLayout{

	public static final int STATE_NORMAL = 0;
	public static final int STATE_READY = 1;
	public static final int STATE_LOADING = 2;
	
	/**
	 * what for ?
	 */
	private Context mContext;
	
	/**
	 * RelativeLayout:xlistview_footer_content
	 */
	private View mContentView;
	
	private View mProgressBar;
	
	private TextView mHintView;
	
	public XListViewFooter(Context context) {
		// TODO Auto-generated constructor stub
		super(context);
		initView(context);
	}
	
	public XListViewFooter(Context context, AttributeSet attrs){
		super(context, attrs);
		initView(context);
	}
	
	public void setState(int state){
		mHintView.setVisibility(View.INVISIBLE);
		mProgressBar.setVisibility(View.INVISIBLE);
		if(state == STATE_READY){
			mHintView.setVisibility(View.VISIBLE);
			mHintView.setText(R.string.xlistview_footer_hint_ready);
		}else if(state == STATE_LOADING){
			mProgressBar.setVisibility(View.VISIBLE);
		}else{
			mHintView.setVisibility(View.VISIBLE);
			mHintView.setText(R.string.xlistview_footer_hint_normal);
		}
	}
	
	/**
	 * 作用是什么？
	 * @param height
	 */
	public void setBottomMargin(int height){
		if(height<0)
			return;
		
		LinearLayout.LayoutParams lp =  (LayoutParams) mContentView.getLayoutParams();
		lp.bottomMargin=height;
		mContentView.setLayoutParams(lp);
	}
	
	// TODO download by http://www.codefans.net
	public int getBottomMargin(){
		LinearLayout.LayoutParams lp =  (LayoutParams) mContentView.getLayoutParams();
		return lp.bottomMargin;
	}
	
	/**
	 * normal status
	 */
	public void normal(){
		mHintView.setVisibility(View.VISIBLE);
		mProgressBar.setVisibility(View.GONE);
	}

	/**
	 * loading status
	 */
	public void loading(){
		mHintView.setVisibility(View.GONE);
		mProgressBar.setVisibility(View.VISIBLE);
	}
	
	/**
	 * hide footer when disable pull load more
	 */
	public void hide(){
		LinearLayout.LayoutParams lp = (LayoutParams) mContentView.getLayoutParams();
		lp.height=0;
		mContentView.setLayoutParams(lp);
	}
	
	/**
	 * show footer
	 */
	public void show(){
		LinearLayout.LayoutParams lp = (LayoutParams) mContentView.getLayoutParams();
		lp.height = LayoutParams.WRAP_CONTENT;
		mContentView.setLayoutParams(lp);
		
	}
	
	/**
	 * xlistview是layout：当下拉屏幕时，显示“显示更多……”
	 */
	private void initView(Context context) {
		// TODO Auto-generated method stub
		mContext = context;
		LinearLayout moreView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.xlistview_footer, null);
		// 可以直接在代码中硬编码加入某个组件
		addView(moreView);
		// 代码逻辑中设置某个组件的高度、宽度
		moreView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		
		mContentView = moreView.findViewById(R.id.xlistview_footer_content); // RelativeLayout
		mProgressBar=moreView.findViewById(R.id.xlistview_footer_progressbar);
		mHintView=(TextView) moreView.findViewById(R.id.xlistview_footer_hint_textview);
	}

}
