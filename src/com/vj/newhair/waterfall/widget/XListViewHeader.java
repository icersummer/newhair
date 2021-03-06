package com.vj.newhair.waterfall.widget;

import com.vj.newhair.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * XListView's header
 * 
 * TODO: new class - Animation,
 *
 */
public class XListViewHeader extends LinearLayout {
	
	/**
	 * view of xlistview_header layout
	 */
	private LinearLayout mContainer;
	
	private ImageView mArrowImageView;
	
	private ProgressBar mProgressBar;
	
	private TextView mHintTextView;
	
	private int mState = STATE_NORMAL;
	
	private Animation mRotateUpAnim;
	private Animation mRotateDownAnim;
	
	private final int ROTATE_ANIM_DURATION = 180;
	
	public final static int STATE_NORMAL = 0;
	public final static int STATE_READY = 1;
	public final static int STATE_REFRESHING = 2;

	public XListViewHeader(Context context) {
		super(context);
		initView(context);
		// TODO Auto-generated constructor stub
	}
	
	public XListViewHeader(Context context, AttributeSet attrs){
		super(context, attrs);
		initView(context);
	}

	private void initView(Context context) {
		// TODO Auto-generated method stub
		// 初始情况，设置下拉刷新view高度为0
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, 0);
		mContainer = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.xlistview_header, null);
		
		addView(mContainer, lp);
		
		this.setGravity(Gravity.BOTTOM);
		
		mArrowImageView=(ImageView) this.findViewById(R.id.xlistview_header_arrow);
		mHintTextView=(TextView) this.findViewById(R.id.xlistview_header_hint_textview);
		mProgressBar=(ProgressBar) this.findViewById(R.id.xlistview_header_progressbar);
		
		// TODO 这些数值的设定规则和意义是什么
		mRotateUpAnim = new RotateAnimation(0.0f, -180.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		mRotateUpAnim.setDuration(ROTATE_ANIM_DURATION);
		mRotateUpAnim.setFillAfter(true);
		mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		mRotateDownAnim.setDuration(ROTATE_ANIM_DURATION);
		mRotateDownAnim.setFillAfter(true);
	}
	
	/**
	 * 什么功能？
	 * @param state
	 */
	public void setState(int state) {
		if(state==mState)
			return;
		
		if(state==STATE_REFRESHING){
			// 显示进度
			mArrowImageView.clearAnimation();
			mArrowImageView.setVisibility(View.INVISIBLE);
			mProgressBar.setVisibility(View.VISIBLE);
		} else {
			// 显示箭头图片
			mArrowImageView.setVisibility(View.VISIBLE);
			mProgressBar.setVisibility(View.INVISIBLE);
		}
		
		// TODO 到底这里是什么逻辑
		switch(state){
		case STATE_NORMAL:
			if(mState == STATE_READY){
				mArrowImageView.startAnimation(mRotateDownAnim);
			}
			if(mState==STATE_REFRESHING){
				mArrowImageView.clearAnimation();
			}
			mHintTextView.setText(R.string.xlistview_header_hint_normal);
			break;
		case STATE_READY:
			if(mState!=STATE_READY){
				mArrowImageView.clearAnimation();
				mArrowImageView.startAnimation(mRotateUpAnim);
				mHintTextView.setText(R.string.xlistview_header_hint_ready);
			}
			break;
		case STATE_REFRESHING:
			mHintTextView.setText(R.string.xlistview_header_hint_loading);
			break;
		default:
			break;
		}
		
		mState = state;
	}
	
	/**
	 * 重设xlistview_header layout的高度
	 * @param height
	 */
	public void setVisiableHeight(int height){
		if(height < 0){
			height = 0;			
		}
		
		LinearLayout.LayoutParams lp = (LayoutParams) mContainer.getLayoutParams();
		lp.height=height;
		mContainer.setLayoutParams(lp);
	}

	public int getVisiableHeight() {
		// TODO Auto-generated method stub
		return mContainer.getHeight();
	}

}
