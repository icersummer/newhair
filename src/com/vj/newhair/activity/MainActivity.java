package com.vj.newhair.activity;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TabWidget;
import butterknife.ButterKnife;
import butterknife.InjectView;

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
	LinearLayout mLayoutComment;
	EditText mEditInput;
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
		final TabWidget tagWidget=mTabHost.getTabWidget();
		tagWidget.setStripEnabled(false);// 圆角边线不启用
		addTabIntent();
		mTabHost.setCurrentTab(0);
		
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
	}

	///////////////////////////////////////////////////////////////////////////////////////////
	private TabHost.TabSpec buildTabSpec(String tag, String label, final Intent intent) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		this.mTabHost.addTab(buildTabSpec("tab1", "0", new Intent(this, ZoneActivity.class)));
		this.mTabHost.addTab(buildTabSpec("tab2","1",new Intent(this, FindHairActivity.class)));
		this.mTabHost.addTab(buildTabSpec("tab3","2",new Intent(this,DisCoverActivity.class)));
		this.mTabHost.addTab(buildTabSpec("tab4","3",new Intent(this,MeActivity.class)));
	}


}
