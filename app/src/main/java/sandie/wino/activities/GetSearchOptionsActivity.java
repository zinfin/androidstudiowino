package sandie.wino.activities;

import java.io.InputStream;
import java.util.List;

import sandie.wino.R;
import sandie.wino.json.JsonWineParser;
import sandie.wino.model.Category;
import sandie.wino.utils.WinoUtils;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class GetSearchOptionsActivity extends GenericBackgroundActivity<List<Category>> {

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
	protected List<Category> onExecute(Uri url) {
		Log.d(TAG, "getting data from :" + url.toString());
		return WinoUtils.getJSONSTream(url.toString());
	}

	/**
	 * Set the result of main activity after background task has completed. 
	 * This runs on the UI thread.
	 */
	@Override
	protected boolean onPostExecute(List<Category> categories){
		int resultCode = Activity.RESULT_CANCELED;
		String failureReason = "Problem getting JSON data";
		if (categories !=null){
			resultCode = Activity.RESULT_OK;
		}
		WinoUtils.setActivityResult(this, resultCode, failureReason);
		return true;
		
	}
	
}
