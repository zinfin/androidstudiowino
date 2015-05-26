package sandie.wino.activities;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import sandie.wino.R;
import sandie.wino.WineListAdapter;
import sandie.wino.WineSearchFactory;
import sandie.wino.WinoApp;
import sandie.wino.fragment.RunSearchFragment;
import sandie.wino.json.JsonWineParser;

public class ShowSearchResultsActivity extends LifecycleLoggingActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_list);
		if (getIntent() != null) {
			String jsonString = getIntent().getStringExtra("SEARCH_RESULT");
			if (jsonString != null) {
				List<sandie.wino.model.List> searchResults = JsonWineParser.parseProducts(jsonString);
				showSearchResults(searchResults);
			}
		}
	}
	private void showSearchResults(List<sandie.wino.model.List> searchResults){
		WinoApp _app = (WinoApp)getApplication();
		WineListAdapter wineListAdapter = new WineListAdapter(this, R.layout.wine_search_results, searchResults, _app);
		ListView lv = (ListView) findViewById(R.id.wine_list);
		lv.setAdapter(wineListAdapter);
	}


}
