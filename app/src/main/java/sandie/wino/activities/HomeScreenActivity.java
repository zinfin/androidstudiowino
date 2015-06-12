package sandie.wino.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import sandie.wino.R;
import sandie.wino.WineConstants;

/**
 * Created by sandie on 6/11/15.
 */
public class HomeScreenActivity extends LifecycleLoggingActivity {
    /**
     * When activity is first created, some initialization occurs as well as setting up the ui
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        // Long press on the list displays the context menu
        registerForContextMenu(findViewById(R.id.listView));
    }

    /**
     * Hook method called after onCreate() or after onRestart() (when
     * the activity is being restarted from stopped state).  Should
     * re-acquire resources relinquished when activity was stopped
     * (onStop()) or acquire those resources for the first time after
     * onCreate().
     */
    @Override
    protected void onStart(){
        // Always call super class for necessary
        // initialization/implementation.
        super.onStart();
        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, WineConstants.LIST_NAMES);
        listView.setAdapter(adapter);
    }

    /**
     * Set up the context menu for the home screen list view
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_menu, menu);
    }
}
