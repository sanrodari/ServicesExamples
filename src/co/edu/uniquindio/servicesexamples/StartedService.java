package co.edu.uniquindio.servicesexamples;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class StartedService extends IntentService {
	
	public StartedService() {
		super("StartedService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.i("ServicesExamples", "Por medio de servicio iniciado e IntentService");
		
		TwitterConsumer consumer = new TwitterConsumer(this);
		consumer.consumeTweetsAndCreateNotification();
	}
}
