package com.vj.newhair.fragment;

import java.util.LinkedList;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import butterknife.InjectView;

import com.vj.newhair.R;
import com.vj.newhair.adapter.FindHairAdapter;
import com.vj.newhair.utils.ImageFetcher;
import com.vj.newhair.waterfall.widget.XListView;

public class FindHairFragment extends Fragment implements IXListViewListener {

	@InjectView(R.id.water_list) XListView mWaterList;
	private LinkedList<FindHairItem> mListInfos = new LinkedList<FindHairItem>();
	private FindHairAdapter mAdapter;
	private ImageFetcher mImageFetcher;
	private int currentPage= 1;
	
	private Handler mHandler= new Handler(){
		public void handleMessage(Message msg){
			switch(msg.what){
			case R.id.ui_show_dialog://TODO what component
				HProgess.show(getActivity(), null);
				break;
			case R.id.ui_dismiss_dialog://TODO what component
				HProgess.dismiss();
			}
		}
	};
	
	
}
