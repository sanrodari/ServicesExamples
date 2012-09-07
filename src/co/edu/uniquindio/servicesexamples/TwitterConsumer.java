package co.edu.uniquindio.servicesexamples;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TwitterConsumer {
	
	private static final String TWEETS_URL = "http://api.twitter.com/1/statuses/user_timeline.json?user_id=93711247";
	private static final int NOTIFICATION_ID = 1;
	
	private Map<String, String> recoveryTweets;
	private String[] titles;
	private String[] contents;
	
	private Context context;
	
	public TwitterConsumer(Context context) {
		this.context = context;
	}

	public void consumeTweetsAndCreateNotification() {
		consumeTweets();
		populateArrays();
		
		createNotification(context);
	}
	
	private void createNotification(Context context) {
		NotificationManager mNotificationManager = (NotificationManager)
				context.getSystemService(Context.NOTIFICATION_SERVICE);
		
		int icon = R.drawable.ic_launcher;
		CharSequence tickerText = "Tus tweets han sido cargados";
		long when = System.currentTimeMillis();

		Notification notification = new Notification(icon, tickerText, when);
		
		CharSequence contentTitle = "Tus tweets han sido cargados";
		CharSequence contentText = "Revisalos aqui";
		
		Intent notificationIntent = new Intent(context, TweetList.class);
		notificationIntent.putExtra("titles", titles);
		notificationIntent.putExtra("contents", contents);
		
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
		notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
		mNotificationManager.notify(NOTIFICATION_ID, notification);
	}
	
	private void populateArrays() {
		titles = new String[recoveryTweets.size()];
		contents = new String[recoveryTweets.size()];
		
		int i = 0;
		for (String id : recoveryTweets.keySet()) {
			titles[i] = id;
			contents[i] = recoveryTweets.get(id);
			i++;
		}
	}

	private void consumeTweets() {
	    String responseString = getStringFromGetRequest();
	    
	    try {
	    	recoveryTweets = new HashMap<String, String>();
			
	    	JSONArray responseArray = new JSONArray(responseString);
			for (int i = 0; i < responseArray.length(); i++) {
				JSONObject jsonTweet = responseArray.getJSONObject(i);
				String idTweet = jsonTweet.getString("id_str");
				String tweetText = jsonTweet.getString("text");
				
				recoveryTweets.put(idTweet, tweetText);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			Log.e("ServicesExamples", "No se ha interpretado correctamente el JSON");
			recoveryTweets = new HashMap<String, String>();
		}
	}
	
	private String getStringFromGetRequest() {
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = httpclient.execute(new HttpGet(TWEETS_URL));
			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				String responseString = out.toString();
				
				return responseString;
			} else {
				response.getEntity().getContent().close();
				throw new IOException(statusLine.getReasonPhrase());
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("ServicesExamples", "No se ha podido cargar los tweets");
		}
		return null;
	}

}
