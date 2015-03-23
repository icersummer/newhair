package com.vj.newhair.activity;

import android.app.ActivityGroup;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TabWidget;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.vj.newhair.R;

public class MainActivity extends ActivityGroup {
	///////////////////////////////////////////////////////////////////////////////////////////
	private static final String tag = MainActivity.class.getName();
	boolean useInjection = true;
	/**
	 * TabHost 包括2部分：TagWidget和FrameLayout。
	 */
	@InjectView(R.id.tabhost) TabHost mTabHost;
	@InjectView(R.id.radiogroup) RadioGroup mRadioGroup;
	@InjectView(R.id.layout_comment) LinearLayout mLayoutComment;
	@InjectView(R.id.post_comment_input) EditText mEditInput;
	
	@OnClick(R.id.post_input_camera)
	public void onPostCameraClicked(View v){
		
	}
	@OnClick(R.id.post_input_send)
	public void onPostSendClicked(View v){
		
	}
	
	public static final String CLICK_RECEIVED_ACTION = "click_action";
	private ActionClickReceiver mActionClickReceiver;
	private InputMethodManager mInputManager;

	///////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		if(useInjection) {
			ButterKnife.inject(this);//TODO find why it's not working
		} else {
			mTabHost = (TabHost) this.findViewById(R.id.tabhost);
		}
		Log.e(tag, "mTabHost = " + mTabHost);
		mTabHost.setup(getLocalActivityManager());
		final TabWidget tabWidget=mTabHost.getTabWidget();
		tabWidget.setStripEnabled(false);// 圆角边线不启用
		addTabIntent();
		mTabHost.setCurrentTab(0);
		
		mInputManager=(InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
		
		// 底部单选组：RADIO GROUP
		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch(checkedId) {
				case R.id.radio_choumeibang:
					mTabHost.setCurrentTab(0);
					break;
				case R.id.radio_hairscan:
					mTabHost.setCurrentTab(1);
					break;
				case R.id.radio_discover:
					mTabHost.setCurrentTab(2);
					break;
				case R.id.radio_me:
					mTabHost.setCurrentTab(3);
					break;
				default :
					break;
				}
			}
		});
		
		registerMessageReceiver();
	}
	
	public void registerMessageReceiver(){
		mActionClickReceiver = new ActionClickReceiver();
		//TODO IntentFilter used for ?
		IntentFilter filter = new IntentFilter();
		filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
		filter.addAction(CLICK_RECEIVED_ACTION);
		registerReceiver(mActionClickReceiver, filter);
	}
	
	/**
	 * TODO 新的类：BroadcastReceiver
	 *
	 */
	public class ActionClickReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if(CLICK_RECEIVED_ACTION.equals(intent.getAction())){
				int uid = intent.getIntExtra("uid", 0);//TODO 这个方法不错，getIntExtra()
				mLayoutComment.setVisibility(View.VISIBLE);
				mInputManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
				mEditInput.requestFocus();
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
			if(mLayoutComment.isShown()){
				mLayoutComment.setVisibility(View.GONE);
				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////
	@Override
	protected void onDestroy(){
		super.onDestroy();
		if(mActionClickReceiver != null)
			unregisterReceiver(mActionClickReceiver);
	}

	///////////////////////////////////////////////////////////////////////////////////////////
	private TabHost.TabSpec buildTabSpec(String tag, String label, final Intent intent) {
		return this.mTabHost
				.newTabSpec(tag)
				.setIndicator(label)
				.setContent(intent);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * ZoneActivity: 添加顶部的2个TAB : 全部话题、热门标签             <br/>
	 * FindHairActivity:          							    <br/>
	 * DisCoverActivity:                                        <br/>
	 * MeActivity:                                              <br/>
	 */
	private void addTabIntent() {
		this.mTabHost.addTab(buildTabSpec("tab1", "0", new Intent(this, ZoneActivity.class)));
		this.mTabHost.addTab(buildTabSpec("tab2","1",new Intent(this, FindHairActivity.class)));
		this.mTabHost.addTab(buildTabSpec("tab3","2",new Intent(this,DisCoverActivity.class)));
		this.mTabHost.addTab(buildTabSpec("tab4","3",new Intent(this,MeActivity.class)));
	}


}
