package com.vj.newhair.fragment;

import java.util.LinkedList;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.vj.newhair.R;
import com.vj.newhair.activity.HairCommentActivity;
import com.vj.newhair.adapter.FindHairAdapter;
import com.vj.newhair.common.json.FindHairParser.HairResult;
import com.vj.newhair.common.model.FindHairItem;
import com.vj.newhair.common.net.ActionOfUrl.JsonAction;
import com.vj.newhair.common.net.NetAsyncTask;
import com.vj.newhair.utils.AppToast;
import com.vj.newhair.utils.HProgess;
import com.vj.newhair.utils.ImageFetcher;
import com.vj.newhair.waterfall.base.PLA_AdapterView;
import com.vj.newhair.waterfall.base.PLA_AdapterView.OnItemClickListener;
import com.vj.newhair.waterfall.widget.XListView;
import com.vj.newhair.waterfall.widget.XListView.IXListViewListener;

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
				break;
			case R.id.ui_show_text://网络超时和数据解析异常
				if(getActivity()!=null)
					AppToast.showShortText(getActivity(), msg.arg1);//TODO what's msg.arg1 ?
				break;
			case R.id.ui_update_ui:
				break;
				
			}
		}
	};
	
	public static FindHairFragment newInstance(int position){
		FindHairFragment fragment= new FindHairFragment();
		Bundle bundle= new Bundle();
		bundle.putInt("position", position);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View contentView= inflater.inflate(R.layout.view_hair_recomm, container, false);
		ButterKnife.inject(this, contentView);
		mWaterList.setPullLoadEnable(true);
		mWaterList.setXListViewListener(this);
		mImageFetcher= new ImageFetcher(getActivity(), 300);
		mImageFetcher.setLoadingImage(R.drawable.hairscan_loading);//TODO api of loading image
		mAdapter= new FindHairAdapter(getActivity(), mImageFetcher);
		mWaterList.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(PLA_AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if(position <1) return;
				FindHairItem info= mAdapter.mListInfos.get(position-1);
				Intent intent = new Intent(getActivity(), HairCommentActivity.class);
				Bundle mBundle= new Bundle();
				mBundle.putSerializable("hairInfo", info);//TODO A GOOD API TO DELIVER OBJECT
				intent.putExtras(mBundle);
				startActivity(intent);
			}
		});
		
		parseArgument();
		return contentView;
	}

	private void parseArgument() {
		// TODO Auto-generated method stub
		mWaterList.setAdapter(mAdapter);
		Bundle bundle= getArguments();//TODO what's this API used for what, where
		int position= bundle.getInt("position");
		switch(position){
		case 0:
		case 1:
			AddItemToContainer(++currentPage, 1);
		}
		
		
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		
	}

	public class NetContent extends NetAsyncTask{

		private HairResult result;
		private int mType= 1;
		public NetContent(Handler uiHandler, int type){
			super(uiHandler);
			this.mType= type;
			if(mListInfos.size()==0){
				setDialogId(1);
			}
		}
		
		@Override
		protected void handlePreExecute() {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected String handleNetworkProcess(String... arg0) throws Exception {
			// TODO Auto-generated method stub
			result= (HairResult) httptask.getRequestByPost(JsonAction.FINDHAIR, arg0[0], null);
			return result!=null ? HANDLE_SUCCESS : HANDLE_FAILED;
		}

		@Override
		protected void handleResult() {
			// TODO Auto-generated method stub
			if(result!=null && result.isSuccess()){
				mListInfos= result.getmListInfo();
				if(mType==1){
					mAdapter.mListInfos=mListInfos;
					mAdapter.notifyDataSetChanged();//TODO study notify here
					mWaterList.stopRefresh();	//TODO study stop here
				}else if(mType==2){
					mWaterList.stopLoadMore();//TODO
					mAdapter.addItemLast(mListInfos);//TODO stop current refresh, and add loaded items
					mAdapter.notifyDataSetChanged();
				}
			}
		}
		
	}

	/**
	 * TODO whaaaaaaaaaaaaaaaaaat ?
	 * @param pageindex
	 * @param type
	 */
	private void AddItemToContainer(int pageindex, int type) {
		String url = "album/1733789/masn/p/" + pageindex + "/24/";
		new NetContent(mHandler,type).execute(url);
	}
	
	public void onResume(){
		super.onResume();
		mImageFetcher.setExitTasksEarly(false);
	}
	
	public void onDestroyView(){
		super.onDestroyView();
		ButterKnife.reset(this);
	}
	
	
}
