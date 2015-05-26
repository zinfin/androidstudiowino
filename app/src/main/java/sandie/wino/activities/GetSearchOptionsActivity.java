package sandie.wino.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import sandie.wino.utils.WinoUtils;

public class GetSearchOptionsActivity extends GenericBackgroundActivity<String> {

	/**
	 * String name of the Intent Action starting this activity
	 */
	public static String ACTION_DOWNLOAD_SEARCH_OPTIONS = "android.intent.action.DOWNLOAD_SEARCH_OPTIONS";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            Uri url = getIntent().getData();
        }

    }
	/**
	 *  Use an AsyncTask to get content from given URL and parse resulting JSON into list of category items
	 */
	@Override
	protected String onExecute(Uri url) {
		Log.d(TAG, "getting data from :" + url.toString());
		return WinoUtils.getJSONStream(url.toString());
	}

	/**
	 * Set the result of main activity after background task has completed. 
	 * This runs on the UI thread.
	 */
	@Override
	protected boolean onPostExecute(String jsonCategoryString){
		int resultCode = Activity.RESULT_CANCELED;
		Intent intent = new Intent();
		String failureReason = "Problem getting JSON data";
		if (jsonCategoryString !=null){
			resultCode = Activity.RESULT_OK;
			intent.putExtra("CATEGORIES", jsonCategoryString);
		}
		WinoUtils.setActivityResult(this, resultCode, intent);
		finish();
		return true;
		
	}
	
}
