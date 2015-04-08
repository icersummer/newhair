// Generated code from Butter Knife. Do not modify!
package com.vj.newhair.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class FindHairFragment$$ViewInjector {
  public static void inject(Finder finder, final com.vj.newhair.fragment.FindHairFragment target, Object source) {
    View view;
    view = finder.findById(source, 2131099769);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131099769' for field 'mWaterList' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mWaterList = (com.vj.newhair.waterfall.widget.XListView) view;
  }

  public static void reset(com.vj.newhair.fragment.FindHairFragment target) {
    target.mWaterList = null;
  }
}
