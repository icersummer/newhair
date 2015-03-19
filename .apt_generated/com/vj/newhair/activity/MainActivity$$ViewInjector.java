// Generated code from Butter Knife. Do not modify!
package com.vj.newhair.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class MainActivity$$ViewInjector {
  public static void inject(Finder finder, final com.vj.newhair.activity.MainActivity target, Object source) {
    View view;
    view = finder.findById(source, 2131296267);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296267' for field 'mRadioGroup' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mRadioGroup = (android.widget.RadioGroup) view;
    view = finder.findById(source, 2131296265);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296265' for field 'mTabHost' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mTabHost = (android.widget.TabHost) view;
  }

  public static void reset(com.vj.newhair.activity.MainActivity target) {
    target.mRadioGroup = null;
    target.mTabHost = null;
  }
}
