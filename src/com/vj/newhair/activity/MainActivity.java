package com.vj.newhair.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TabHost;

import com.vj.newhair.R;

public class MainActivity extends Activity {
	TabHost mTabHost;
	RadioGroup mRadioGroup;
	LinearLayout mLayoutComment;
	EditText mEditInput;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

}
