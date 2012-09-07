package co.edu.uniquindio.servicesexamples;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class TweetList extends Activity {
	
    private ListView tweetList;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_list);
        
        Bundle extras = getIntent().getExtras();
        
        final String[] titles;
        final String[] contents;
        if(extras != null) {
	        titles   = extras.getStringArray("titles");
	        contents = extras.getStringArray("contents");
        } else {
        	titles   = new String[]{"Ningun tweet ha sido recuperado"};
	        contents = new String[]{"Ningun tweet ha sido recuperado"};
        }
        
        tweetList = (ListView) findViewById(R.id.tweetList);
        tweetList.setAdapter(new BaseAdapter() {
        	
        	private Context context = getApplicationContext();
        	
        	public View getView(int position, View convertView, ViewGroup parent) {
        		
        		TweetView tweetView;
        		if (convertView == null) {
        			tweetView = new TweetView(context, titles[position], contents[position]);
        		} else {
        			tweetView = (TweetView) convertView;
        			tweetView.setTitle(titles[position]);
        			tweetView.setContent(contents[position]);
        		}
        		
        		return tweetView;
        	}
        	public long getItemId(int position) {
        		return position;
        	}
        	public Object getItem(int position) {
        		return position;
        	}
        	public int getCount() {
        		return titles.length;
        	}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_tweet_list, menu);
        return true;
    }
}
