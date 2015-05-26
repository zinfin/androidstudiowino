package sandie.wino.tasks;

import android.app.Application;
import android.os.AsyncTask;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import sandie.wino.WinoApp;
import sandie.wino.interfaces.OnTaskCompleted;
import sandie.wino.model.List;
public class SearchWineTask extends AsyncTask<Object, Void, java.util.List<sandie.wino.model.List>> {
	
	private WinoApp _app;
	private OnTaskCompleted _taskCompleted;

	public SearchWineTask(Application application, OnTaskCompleted activity){
		_app = (WinoApp) application;
		_taskCompleted = activity;

	}
	
	@Override
	protected java.util.List<List> doInBackground(Object... params) {
		// Get selected items from application
		HashMap<String,Integer> searchMap = (HashMap<String, Integer>) _app.getSelectedItems();
		// Pull out the values
		Collection<Integer> ids =searchMap.values();
		Iterator<Integer> iter = ids.iterator();
		int [] wineIds = new int[ids.size()];
		int z=0;
		while (iter.hasNext()){
			wineIds[z] = iter.next();
			z = z + 1;
		}
		int _resultSize = _app.getResultSize();
		int _offset = _app.getOffset();
		/*final HttpClient httpClient = ShowSearchOptionsActivity.getHttpClient();
		return WineSearchFactory.searchByCategories(httpClient, wineIds, _resultSize, _offset);*/
		return null;
	}
	@Override
	protected void onPostExecute(java.util.List<List> results){
		_app.setResults(results);
		_taskCompleted.callback();		
	}
}
