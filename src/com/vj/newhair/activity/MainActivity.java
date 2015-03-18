package com.vj.newhair.activity;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TabWidget;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.vj.newhair.R;

public class MainActivity extends ActivityGroup {
	private static final String tag = MainActivity.class.getName();
	boolean useInjection = true;
	/**
	 * TabHost 包括2部分：TagWidget和FrameLayout。
	 */
	@InjectView(R.id.tabhost) TabHost mTabHost;
	RadioGroup mRadioGroup;
	LinearLayout mLayoutComment;
	EditText mEditInput;
	private InputMethodManager mInputManager;

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
	}

	
	private TabHost.TabSpec buildTabSpec(String tag, String label, final Intent intent) {
		// TODO Auto-generated method stub
		return this.mTabHost
				.newTabSpec(tag)
				.setIndicator(label)
				.setContent(intent);
	}
	private void addTabIntent() {
		// TODO Auto-generated method stub
		this.mTabHost.addTab(buildTabSpec("tab1", "0", new Intent(this, ZoneActivity.class)));
		this.mTabHost.addTab(buildTabSpec("tab2","1",new Intent(this, FindHairActivity.class)));
		this.mTabHost.addTab(buildTabSpec("tab3","2",new Intent(this,DisCoverActivity.class)));
		this.mTabHost.addTab(buildTabSpec("tab4","3",new Intent(this,MeActivity.class)));
	}


}
