package sandie.wino.strategy;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import sandie.wino.activities.MainActivity;
import sandie.wino.activities.ShowSearchOptionsActivity;

public class ShowSearchFilterStrategy extends WineStrategy {

	public ShowSearchFilterStrategy(MainActivity activity){
		super(activity);
	}
	@Override
	public void doRequest(Uri uri) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doRequest(Intent data) {
		// Send intent to ShowSearchOptionsActivity
		Intent intent = makeIntent(null);
		// Add data from passed intent
		String jsonCategories = data.getStringExtra("CATEGORIES");
		intent.putExtra("CATEGORIES", jsonCategories);
		try{
			mActivity.get().startActivityForResult(intent, DataStrategyManager.StrategyType.SHOW_SEARCH_OPTIONS.ordinal());
		}catch (NullPointerException npe){
			Log.d(TAG, "Null pointer in doRequest()");
		}


	}
	@Override
	public void doRequest() {
		// TODO Auto-generated method stub

	}
	@Override
	public void doResult(Intent data) {
		mActivity.get().doRequest(DataStrategyManager.StrategyType.DO_SEARCH, data);


	}

	@Override
	public void doError(Intent data) {
		// TODO Auto-generated method stub
		Log.d(TAG, "doError()");
	}

	@Override
	protected Intent makeIntent(Uri uri) {
		return new Intent(mActivity.get(), ShowSearchOptionsActivity.class);

	}

}
