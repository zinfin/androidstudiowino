package sandie.wino.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import sandie.wino.json.JsonWineParser;

public class WinoUtils {
	
	/** Some constants used for HTTP communication */
	private static final int TIMEOUT = 15000;
	private static final String GET = "GET";
	private static final String CONTENT_LENGTH_STRING = "Content-length";
	private static final String CONTENT_LENGTH = "0";
	
	/**
	 * Checks for a network connection
	 */
	public static boolean isConnected(ConnectivityManager connMgr){
		boolean isConnected = false;
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			isConnected = true;
		} 
		return isConnected;
	}
	/**
    * Set the result of the Activity to indicate whether the
    * operation on the content succeeded or not.
    * 
    * @param activity
    *          The Activity whose result is being set.
    * @param resultCode
    *          The result of the Activity, i.e., RESULT_CANCELED or
    *          RESULT_OK. 
    * @param data
    *          An intent containing data and extras of interest.
    */
   public static void setActivityResult(Activity activity,
                                        int resultCode,
                                        Intent data) {
       if (resultCode == Activity.RESULT_CANCELED) {
		   // Indicate why the operation on the content was
		   // unsuccessful or was cancelled.
           String failReason = data.getStringExtra("REASON");
		   activity.setResult(Activity.RESULT_CANCELED,
				   new Intent("").putExtra("reason",
						   failReason));
	   }else {
		   // Everything is ok.
		   activity.setResult(Activity.RESULT_OK, data);
	   }
   }
   /**
    * Return an JSON String from a GET request given a valid url.
    * @param url JSON endpoint
    * @return String
    */
   public static String getJSONStream (String url) {
	   HttpURLConnection urlConnection = null;
	   try{
		   URL u = new URL(url);
		   urlConnection = (HttpURLConnection) u.openConnection();
		   urlConnection.setRequestMethod(GET);
		   urlConnection.setRequestProperty(CONTENT_LENGTH_STRING, CONTENT_LENGTH);
		   urlConnection.setUseCaches(false);
		   urlConnection.setAllowUserInteraction(false);
		   urlConnection.setConnectTimeout(TIMEOUT);
		   urlConnection.connect();
		   int status = urlConnection.getResponseCode();
		   
		   switch (status){
			   case 200:
		   		case 201:
		   			InputStream stream =  urlConnection.getInputStream();
					JsonWineParser jsonParser = new JsonWineParser();
					return jsonParser.getJSONString(stream);
		
		   }
		   
	   }catch (MalformedURLException mex){
		   Log.d("WinoUtils", "Malformed URL Exception: " + mex.getMessage());
		   mex.printStackTrace();
		   
	   }catch (IOException io){
		   Log.d("WinoUtils", "IO Exception "+ io.getMessage());  
		   io.printStackTrace();
		   
	   }finally{
		   if (urlConnection !=null){
			   try{
				   urlConnection.disconnect();
			   }catch (Exception e){
				   Log.d("WinoUtils", "Exception in finally block");
				   e.printStackTrace();
			   }
		   }
		  
	   }
	return null;
	   
   }


}
