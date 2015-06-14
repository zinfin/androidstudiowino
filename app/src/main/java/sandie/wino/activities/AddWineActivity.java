package sandie.wino.activities;

import android.os.Bundle;

import sandie.wino.R;

/**
 * Created by sandie on 6/13/15.
 */
public class AddWineActivity extends LifecycleLoggingActivity {
    /**
     * When activity is first created, some initialization occurs as well as setting up the ui
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wine_detail);
    }
}
