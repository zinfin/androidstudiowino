package sandie.wino.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import sandie.wino.strategy.DoSearchStrategy;
import sandie.wino.utils.WinoUtils;

/**
 * Created by sandie on 5/25/15.
 */
public class DoSearchActivity extends GenericBackgroundActivity<String> {
    @Override
    protected String onExecute(Uri searchUrl) {
        Log.d(TAG, searchUrl.toString());
        return WinoUtils.getJSONStream(searchUrl.toString());

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            Uri url = getIntent().getData();
        }
    }

    @Override
    protected boolean onPostExecute(String jsonSearchResult) {
        super.onPostExecute(jsonSearchResult);
        int resultCode = Activity.RESULT_CANCELED;
        Intent intent = new Intent();
        String failureReason = "Problem getting JSON data";
        if (jsonSearchResult !=null){
            resultCode = Activity.RESULT_OK;
            intent.putExtra("SEARCH_RESULT", jsonSearchResult);
        }
        WinoUtils.setActivityResult(this, resultCode, intent);
        finish();
        return true;
    }

}
