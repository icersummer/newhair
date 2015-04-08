// Generated code from Butter Knife. Do not modify!
package com.vj.newhair.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ZoneActivity$$ViewInjector {
  public static void inject(Finder finder, final com.vj.newhair.activity.ZoneActivity target, Object source) {
    View view;
    view = finder.findById(source, 2131099708);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131099708' for field 'mLayoutLoading' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mLayoutLoading = (android.widget.LinearLayout) view;
    view = finder.findById(source, 2131099712);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131099712' for field 'mRadioGroup' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mRadioGroup = (android.widget.RadioGroup) view;
    view = finder.findById(source, 2131099715);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131099715' for field 'mViewPager' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mViewPager = (android.support.v4.view.ViewPager) view;
  }

  public static void reset(com.vj.newhair.activity.ZoneActivity target) {
    target.mLayoutLoading = null;
    target.mRadioGroup = null;
    target.mViewPager = null;
  }
}
