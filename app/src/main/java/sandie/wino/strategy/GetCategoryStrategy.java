package sandie.wino.strategy;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import sandie.wino.WineConstants;
import sandie.wino.activities.GetSearchOptionsActivity;
import sandie.wino.activities.MainActivity;
import sandie.wino.strategy.DataStrategyManager.StrategyType;

public class GetCategoryStrategy extends WineStrategy {

	public GetCategoryStrategy(MainActivity activity){

		super(activity);
	}
	@Override
	/**
	 * Run the strategy to download search options
	 */
	public void doRequest(Uri uri) {
		Log.d(TAG,"doRequest() " + uri.toString());
		String categoryURL = WineConstants.ENDPOINT+WineConstants.CATEGORY+WineConstants.API_KEY;
		Uri url = Uri.parse(categoryURL);
		Intent intent = makeIntent(url);
		try{
			mActivity.get().startActivityForResult(intent, StrategyType.DOWNLOAD_SEARCH_OPTIONS.ordinal());
		}catch (NullPointerException npe){
			Log.d(TAG, "Null pointer in doRequest()");
		}
	}

	public void doRequest(Intent data){
		Log.d(TAG,"doRequest() with Intent");
		String categoryURL = WineConstants.ENDPOINT+WineConstants.CATEGORY+WineConstants.API_KEY;
		Uri url = Uri.parse(categoryURL);
		Intent intent = makeIntent(url);
		try{
			mActivity.get().startActivityForResult(intent, StrategyType.DOWNLOAD_SEARCH_OPTIONS.ordinal());
		}catch (NullPointerException npe){
			Log.d(TAG, "Null pointer in doRequest()");
		}
	}
	@Override
	public void doRequest() {
		
		Log.d(TAG,"doRequest()");
		String categoryURL = WineConstants.ENDPOINT+WineConstants.CATEGORY+WineConstants.API_KEY;

		// TODO Make intent download search options, passing ordinal for strategy
		Uri url = Uri.parse(categoryURL);
		Intent intent = makeIntent(url);
		try{
			mActivity.get().startActivityForResult( intent,StrategyType.DOWNLOAD_SEARCH_OPTIONS.ordinal());
		}catch (NullPointerException npe){
			Log.d(TAG, "Null pointer in doRequest()");
		}

	}
	@Override
	public void doResult(Intent data) {
		Log.d(TAG, "doResult()");
		mActivity.get().doRequest(StrategyType.SHOW_SEARCH_OPTIONS, data);
	}
	@Override
	public void doError(Intent data) {
		// TODO Auto-generated method stub
		try{
			String reason = data.getStringExtra("reason");
			Toast.makeText(mActivity.get(), reason, Toast.LENGTH_SHORT).show();
			if (reason.equals("back button pressed")){
				mActivity.get().finish();
				Log.d(TAG, "finishing activity due to back button being pressed");
			}
		}catch(NullPointerException npe){
			Log.d(TAG, "Null pointer exception in doError()");
			npe.printStackTrace();
		}
		
	}

	/**
	 * Build an intent that will be delivered
	 * to the GetSearchOptionActivity
	 * @param uri  The url used for the web service
	 * @return an intent specific for getting search options
	 */
	@Override
	protected Intent makeIntent(Uri uri) {
		Log.d(TAG,"Making GetCategoryStrategy intent");
		Intent intent = new Intent(mActivity.get(), GetSearchOptionsActivity.class);
		intent.setData(uri);
		return intent;
	}

}
