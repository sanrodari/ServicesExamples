package co.edu.uniquindio.servicesexamples;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TweetView extends LinearLayout {
	
	private TextView title;
	private TextView content;
	
    public TweetView(Context context, String stringTitle, String words) {
        super(context);

        this.setOrientation(VERTICAL);
        
        title = new TextView(context);
        title.setText(stringTitle);
        title.setTextColor(Color.rgb(0, 0, 0));
        addView(title, new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        content = new TextView(context);
        content.setText(words);
        content.setTextColor(Color.rgb(0, 0, 0));
        addView(content, new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    }

    public void setTitle(String stringTitle) {
        title.setText(stringTitle);
    }

    public void setContent(String words) {
        content.setText(words);
    }

}