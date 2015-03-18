package com.vj.newhair.widget;

import android.view.View;
import android.widget.PopupWindow;

public class ModelPopup extends PopupWindow implements android.view.View.OnClickListener {

	public interface OnDialogListener {
		/**
		 * 选择本地照片
		 */
		public void onChoosePhoto();

		/**
		 * 照相
		 */
		public void onTakePhoto();

		/**
		 * 照相
		 */
		public void onModel();

		/**
		 * 取消
		 */
		public void onCancel();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

}
