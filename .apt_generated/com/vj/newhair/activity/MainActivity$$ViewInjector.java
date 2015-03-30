// Generated code from Butter Knife. Do not modify!
package com.vj.newhair.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class MainActivity$$ViewInjector {
  public static void inject(Finder finder, final com.vj.newhair.activity.MainActivity target, Object source) {
    View view;
    view = finder.findById(source, 2131099696);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131099696' for field 'mTabHost' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mTabHost = (android.widget.TabHost) view;
    view = finder.findById(source, 2131099705);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131099705' for field 'mEditInput' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mEditInput = (android.widget.EditText) view;
    view = finder.findById(source, 2131099698);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131099698' for field 'mRadioGroup' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mRadioGroup = (android.widget.RadioGroup) view;
    view = finder.findById(source, 2131099703);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131099703' for field 'mLayoutComment' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mLayoutComment = (android.widget.LinearLayout) view;
    view = finder.findById(source, 2131099704);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131099704' for method 'onPostCameraClicked' was not found. If this view is optional add '@Optional' annotation.");
    }
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.onPostCameraClicked(p0);
        }
      });
    view = finder.findById(source, 2131099706);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131099706' for method 'onPostSendClicked' was not found. If this view is optional add '@Optional' annotation.");
    }
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.onPostSendClicked(p0);
        }
      });
  }

  public static void reset(com.vj.newhair.activity.MainActivity target) {
    target.mTabHost = null;
    target.mEditInput = null;
    target.mRadioGroup = null;
    target.mLayoutComment = null;
  }
}
