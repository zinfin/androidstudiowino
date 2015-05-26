package sandie.wino;




import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import sandie.wino.json.JsonWineParser;
import sandie.wino.utils.WinoUtils;

public class WineSearchFactory {
	public static  List<sandie.wino.model.List> searchByCategories( Double [] ids, int resultSize, int offset){
		// Convert double array to concatenate category ids
		String categoryIds = "";
		for (Double id : ids) {
			categoryIds = categoryIds + id + "+";
		}
		categoryIds = categoryIds + "490";
		String PARAM = "catalog?filter=categories("+categoryIds+")&offset="+offset+"&size="+resultSize+"&apikey=";
		String searchURL = WineConstants.ENDPOINT+PARAM+WineConstants.API_KEY;
		System.out.println(searchURL);
		return doSearch(searchURL,resultSize);
		
	}
	public static  String generateSearchURL( int [] ids, int resultSize, int offset){
		// Convert int array to concatenate category ids
		String categoryIds = "";
		for (int id : ids) {
			categoryIds = categoryIds + id + "+";
		}
		categoryIds = categoryIds + "490";
		String PARAM = "catalog?filter=categories("+categoryIds+")&offset="+offset+"&size="+resultSize+"&apikey=";
		return WineConstants.ENDPOINT+PARAM+WineConstants.API_KEY;

		
	}
	public static List<sandie.wino.model.List> searchByCategory(int id, int resultSize, int offset){
		String PARAM = "catalog?filter=categories(490+"+id+")&offset="+offset+"&size="+resultSize+"&apikey=";
		String searchURL = WineConstants.ENDPOINT+PARAM+WineConstants.API_KEY;
		System.out.println(searchURL);
		return doSearch(searchURL,resultSize);
		
	}
	private static List<sandie.wino.model.List> doSearch( String searchURL, int resultSize){

		List<sandie.wino.model.List> productList = new ArrayList<>();
		try{
			String jsonString = WinoUtils.getJSONStream(searchURL);
			JsonWineParser jsonParser = new JsonWineParser();
			productList = jsonParser.parseProducts(jsonString);
		}catch (Exception e){
			e.printStackTrace();
		}
		return productList;
	}
	public static int[] extractSearchCategoryIds(Map<String,Integer> searchMap){
		// Pull out the values
		Collection<Integer> ids =searchMap.values();
		Iterator<Integer> iter = ids.iterator();
		int [] wineIds = new int[ids.size()];
		int z=0;
		while (iter.hasNext()){
			wineIds[z] = iter.next();
			z = z + 1;
		}
		return wineIds;
	}
}
