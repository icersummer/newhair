package com.vj.newhair.activity;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
	boolean useInjection = false;
	/**
	 * TabHost 包括2部分：TagWidget和FrameLayout。
	 */
	@InjectView(R.id.tabhost) TabHost mTabHost;
	RadioGroup mRadioGroup;
	LinearLayout mLayoutComment;
	EditText mEditInput;

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
	}


}
