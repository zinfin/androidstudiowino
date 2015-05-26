package sandie.wino.strategy;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import sandie.wino.activities.DoSearchActivity;
import sandie.wino.activities.MainActivity;

/**
 * Created by sandie on 5/25/15.
 */
public class DoSearchStrategy extends WineStrategy {

    public DoSearchStrategy (MainActivity activity ){
        super(activity);
    }
    @Override
    public void doRequest(Uri uri) {

    }

    @Override
    public void doRequest(Intent data) {
        // Start background activity to search
        Intent intent = makeIntent(data.getData());
        try{
            mActivity.get().startActivityForResult(intent, DataStrategyManager.StrategyType.DO_SEARCH.ordinal());
        }catch (NullPointerException npe){
            Log.d(TAG, "Null pointer in doRequest()");
        }
    }

    @Override
    public void doRequest() {

    }

    @Override
    public void doResult(Intent data) {
        mActivity.get().doRequest(DataStrategyManager.StrategyType.SHOW_SEARCH_RESULTS, data);
    }

    @Override
    public void doError(Intent data) {

    }

    @Override
    protected Intent makeIntent(Uri uri) {
        Log.d(TAG, "Making DoSearchActivity intent");
        Intent intent = new Intent(mActivity.get(), DoSearchActivity.class);
        intent.setData(uri );
        return intent;
    }
}
