// Generated code from Butter Knife. Do not modify!
package com.vj.newhair.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class MainActivity$$ViewInjector {
  public static void inject(Finder finder, final com.vj.newhair.activity.MainActivity target, Object source) {
    View view;
    view = finder.findById(source, 2131296281);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296281' for field 'mTabHost' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mTabHost = (android.widget.TabHost) view;
    view = finder.findById(source, 2131296283);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296283' for field 'mRadioGroup' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mRadioGroup = (android.widget.RadioGroup) view;
    view = finder.findById(source, 2131296288);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296288' for field 'mLayoutComment' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mLayoutComment = (android.widget.LinearLayout) view;
    view = finder.findById(source, 2131296290);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296290' for field 'mEditInput' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mEditInput = (android.widget.EditText) view;
    view = finder.findById(source, 2131296291);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296291' for method 'onPostSendClicked' was not found. If this view is optional add '@Optional' annotation.");
    }
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.onPostSendClicked(p0);
        }
      });
    view = finder.findById(source, 2131296289);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296289' for method 'onPostCameraClicked' was not found. If this view is optional add '@Optional' annotation.");
    }
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.onPostCameraClicked(p0);
        }
      });
  }

  public static void reset(com.vj.newhair.activity.MainActivity target) {
    target.mTabHost = null;
    target.mRadioGroup = null;
    target.mLayoutComment = null;
    target.mEditInput = null;
  }
}
