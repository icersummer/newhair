// Generated code from Butter Knife. Do not modify!
package com.vj.newhair.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ZoneActivity$$ViewInjector {
  public static void inject(Finder finder, final com.vj.newhair.activity.ZoneActivity target, Object source) {
    View view;
    view = finder.findById(source, 2131296263);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296263' for field 'mViewPager' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mViewPager = (android.support.v4.view.ViewPager) view;
    view = finder.findById(source, 2131296262);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296262' for field 'mLayoutLoading' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mLayoutLoading = (android.widget.LinearLayout) view;
    view = finder.findById(source, 2131296259);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296259' for field 'mRadioGroup' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mRadioGroup = (android.widget.RadioGroup) view;
  }

  public static void reset(com.vj.newhair.activity.ZoneActivity target) {
    target.mViewPager = null;
    target.mLayoutLoading = null;
    target.mRadioGroup = null;
  }
}
