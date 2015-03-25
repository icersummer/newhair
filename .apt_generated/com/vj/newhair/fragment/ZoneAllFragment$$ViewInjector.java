// Generated code from Butter Knife. Do not modify!
package com.vj.newhair.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ZoneAllFragment$$ViewInjector {
  public static void inject(Finder finder, final com.vj.newhair.fragment.ZoneAllFragment target, Object source) {
    View view;
    view = finder.findById(source, 2131099706);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131099706' for field 'mListView' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mListView = (com.vj.newhair.common.refresh_list.RefreshListView) view;
  }

  public static void reset(com.vj.newhair.fragment.ZoneAllFragment target) {
    target.mListView = null;
  }
}
