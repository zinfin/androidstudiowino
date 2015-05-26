package sandie.wino.strategy;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import sandie.wino.activities.MainActivity;
import sandie.wino.activities.ShowSearchOptionsActivity;
import sandie.wino.activities.ShowSearchResultsActivity;

/**
 * Created by sandie on 5/25/15.
 */
public class ShowSearchResultStrategy extends WineStrategy {

    public ShowSearchResultStrategy(MainActivity activity ){
        super(activity);
    }
    @Override
    public void doRequest(Uri uri) {

    }

    @Override
    public void doRequest(Intent data) {
        Intent intent = makeIntent(null);
        String jsonString = data.getStringExtra("SEARCH_RESULT");
        intent.putExtra("SEARCH_RESULT", jsonString);
        try{
            mActivity.get().startActivityForResult(intent, DataStrategyManager.StrategyType.SHOW_SEARCH_RESULTS.ordinal());
        }catch (NullPointerException npe){
            Log.d(TAG, "Null pointer in doRequest()");
        }
    }

    @Override
    public void doRequest() {

    }

    @Override
    public void doResult(Intent data) {

    }

    @Override
    public void doError(Intent data) {

    }

    @Override
    protected Intent makeIntent(Uri uri) {
        return new Intent(mActivity.get(), ShowSearchResultsActivity.class);
    }
}
