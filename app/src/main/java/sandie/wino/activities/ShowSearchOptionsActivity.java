package sandie.wino.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sandie.wino.R;
import sandie.wino.json.JsonWineParser;
import sandie.wino.model.Category;
import sandie.wino.model.Refinement;
import sandie.wino.utils.WinoUtils;
import sandie.wino.view.NoDefaultSpinner;

public class ShowSearchOptionsActivity extends LifecycleLoggingActivity{

	private NoDefaultSpinner wineTypeSpinner, varietalSpinner, wineStyleSpinner,
	regionSpinner,vintageSpinner,foodSpinner, appellationSpinner;

	private Button searchBtn ;
	private List<Category> categories;
	private Map<String,Integer> selectedItems;
	


   private final String TAG = getClass().getSimpleName();

	
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		// Start by showing the search options

		setContentView(R.layout.activity_show_search);
		searchBtn = (Button)findViewById(R.id.searchWineBtn);
		if (this.getIntent() != null) {
			String jsonCategories = this.getIntent().getStringExtra("CATEGORIES");
			if (jsonCategories != null) {
				categories = JsonWineParser.parseJsonStringCategories(jsonCategories);
				setUpDropdowns();
			}
		}
	}

	public void reset(View view){
		searchBtn.setEnabled(false);
		setUpDropdowns();
		selectedItems = null;
	}


	public void searchForWine(View view){
		if (selectedItems !=null){
			// Clear any results
			//  app.clearResultList();
			// Save the items to the app
			//app.setSelectedItems(selectedItems);

			Intent searchResults = new Intent(ShowSearchOptionsActivity.this, ShowSearchResultsActivity.class);
			startActivity(searchResults);
		}
	}

    @Override
    protected void onStop(){
        super.onStop();
	}

    /**
     * Notify user back button was pressed
     */
	@Override
	public void onBackPressed(){
		Log.d(TAG, "back button pressed");
		Intent intent = new Intent();
		// Handle in activity result handler
		intent.putExtra("REASON", "back button pressed");
		WinoUtils.setActivityResult(this, 0, intent);
		super.onBackPressed();
	}

	/**
	 * Set up drop downs for view
	 */
	public void setUpDropdowns(){
        final String wType = getString(R.string.wine_type);
        final String wVarietal = getString(R.string.wine_vartietal);
        final String wStyle = getString(R.string.wine_style);
        final String wRegion = getString(R.string.wine_region);
        final String wVintage = getString(R.string.wine_vintage);
        final String wFood = getString(R.string.wine_food);
        final String wAppellation = getString(R.string.wine_appellation);

		if ((categories != null) && !categories.isEmpty()){
			
			// Extract specific categories and populate appropriate spinners with refinements
			for (Category category : categories){
				List<Refinement> items = category.getRefinements();
				
				if (category.getName().equalsIgnoreCase(wType)){
					wineTypeSpinner=(NoDefaultSpinner) findViewById(R.id.wine_type_spinner);
					assignSpinner(wineTypeSpinner, items);
				}
				if (category.getName().equalsIgnoreCase(wVarietal)){
					varietalSpinner=(NoDefaultSpinner) findViewById(R.id.varietal_spinner);
					assignSpinner(varietalSpinner, items);
				}
				if (category.getName().equalsIgnoreCase(wStyle)){
					wineStyleSpinner=(NoDefaultSpinner) findViewById(R.id.wine_style_spinner);
					assignSpinner(wineStyleSpinner, items);
				}	
				if (category.getName().equalsIgnoreCase(wRegion)){
					regionSpinner=(NoDefaultSpinner) findViewById(R.id.region_spinner);
					assignSpinner(regionSpinner, items);
				}
				if (category.getName().equalsIgnoreCase(wVintage)){
					vintageSpinner=(NoDefaultSpinner) findViewById(R.id.vintage_spinner);
					assignSpinner(vintageSpinner, items);
				}
				if (category.getName().equalsIgnoreCase(wFood)){
					foodSpinner=(NoDefaultSpinner) findViewById(R.id.food_type_spinner);
					assignSpinner(foodSpinner, items);
				}
				if (category.getName().equalsIgnoreCase(wAppellation)){
					appellationSpinner=(NoDefaultSpinner) findViewById(R.id.apellation_spinner);
					assignSpinner(appellationSpinner, items);
				}
			}
		}
	}
	protected void assignSpinner(final NoDefaultSpinner spinner, List<Refinement> list){
		ArrayAdapter<Refinement>  spinnerAdapter =
				new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);

		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(spinnerAdapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
	         @Override
	         public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
	        	 // Store a hash of spinner name to selected index
	        	 if (selectedItems==null){
	        		 selectedItems = new HashMap<>();
	        		 
	        	 }
	        	 searchBtn.setEnabled(true);
	        	 String key = null;
	        	 int spinnerId = parentView.getId();
	        	 if (spinnerId ==wineStyleSpinner.getId()){
	        		 key = getString(R.string.wine_style);
	        		 
	        	 }if (spinnerId == wineTypeSpinner.getId()) {
	        		 key = getString(R.string.wine_type);
	        	 }else if(spinnerId == regionSpinner.getId()){
	        		 key = getString(R.string.wine_region);
	        	 }else if (spinnerId == vintageSpinner.getId()){
	        		 key = getString(R.string.wine_vintage);
	        	 }else if (spinnerId == foodSpinner.getId()){
	        		 key = getString(R.string.wine_food);
	        	 }else if (spinnerId == appellationSpinner.getId()){
	        		 key = getString(R.string.wine_appellation);
	        	 }else if (spinnerId == varietalSpinner.getId()){
	        		 key = getString(R.string.wine_vartietal);
	        	 }
	        	 if (key!=null){
	        		// Look up the associated id for this selected item
	        		 int itemId = getIdForItem(key, position);
	        		 
	        		 selectedItems.put(key, itemId);
	        	 }
	         }

	         @Override
	         public void onNothingSelected(AdapterView<?> parentView) {
	            // do nothing
	         }
	      }); 
	}
	private int getIdForItem(String itemName, int index){
		int itemId = 0;
		for (Category category: categories){
			if (category.getName().equalsIgnoreCase(itemName)){
				Refinement item = category.getRefinements().get(index);
				itemId = item.getId().intValue();
				break;
			}
		}
		return itemId;
	}

}
