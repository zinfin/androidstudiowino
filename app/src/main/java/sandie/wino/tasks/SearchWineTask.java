package sandie.wino.tasks;

import android.app.Application;
import android.os.AsyncTask;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import sandie.wino.WineSearchFactory;
import sandie.wino.WinoApp;
import sandie.wino.interfaces.OnTaskCompleted;
import sandie.wino.model.List;
import sandie.wino.utils.WinoUtils;

public class SearchWineTask extends AsyncTask<Object, Void, String> {
	
	private WinoApp _app;
	private OnTaskCompleted _taskCompleted;

	public SearchWineTask(Application application, OnTaskCompleted activity){
		_app = (WinoApp) application;
		_taskCompleted = activity;

	}
	
	@Override
	protected String doInBackground(Object... params) {
		// Get selected items from application
		HashMap<String,Integer> searchMap = (HashMap<String, Integer>) _app.getSelectedItems();

		int _resultSize = _app.getResultSize();
		int _offset = _app.getOffset();

		int[]  searchIds = WineSearchFactory.extractSearchCategoryIds(searchMap);
		String searchUrl = WineSearchFactory.generateSearchURL(searchIds,_resultSize, _offset);
		return WinoUtils.getJSONStream(searchUrl);
	}
	@Override
	protected void onPostExecute(String results){
		//_app.setResults(results);
		_taskCompleted.callback();		
	}
}
