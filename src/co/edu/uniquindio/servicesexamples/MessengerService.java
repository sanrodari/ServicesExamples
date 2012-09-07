package co.edu.uniquindio.servicesexamples;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

public class MessengerService extends Service {
	
	public static final int CONSUME_TWEETS_AND_NOTIFICATE = 1;
	
	private MessengerService instance = this;
	
	private class MessageHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case CONSUME_TWEETS_AND_NOTIFICATE:
				Log.i("ServicesExamples", "Por medio de servicio ligado y Messenger");
				TwitterConsumer consumer = new TwitterConsumer(instance);
				consumer.consumeTweetsAndCreateNotification();
				break;
			}
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		Messenger messenger = new Messenger(new MessageHandler());
		return messenger.getBinder();
	}

}
