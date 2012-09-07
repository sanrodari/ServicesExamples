package co.edu.uniquindio.servicesexamples;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Messenger;

public class ServiceConnectionForMessenger implements ServiceConnection {
	
	private MessengerActivity messengerActivity;
	
	public ServiceConnectionForMessenger(MessengerActivity messengerActivity) {
		this.messengerActivity = messengerActivity;
	}

	@Override
	public void onServiceConnected(ComponentName className, IBinder binder) {
		Messenger messenger = new Messenger(binder);
		messengerActivity.setMessenger(messenger);
	}

	@Override
	public void onServiceDisconnected(ComponentName binder) {	}

}
