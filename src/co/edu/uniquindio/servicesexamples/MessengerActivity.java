package co.edu.uniquindio.servicesexamples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

public class MessengerActivity extends Activity {
	
	private Messenger messenger;
	private ServiceConnectionForMessenger sForMessenger = 
			new ServiceConnectionForMessenger(this);
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
    }
    
    @Override
    protected void onStart() {
    	super.onStart();
    	
    	Intent intent = new Intent(this, MessengerService.class);
    	bindService(intent, sForMessenger, BIND_AUTO_CREATE);
    }
    
    protected void onStop() {
    	super.onStop();

    	try {
			unbindService(sForMessenger);
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("ServicesExamples", "No se puede desligar el servicio.");
		}
    }
    
    public void onClickLoadTweetsMessenger(View view) {
    	Log.i("ServicesExamples", "Desde el Hilo principal");
    	
		if (messenger != null) {
			Message message = Message.obtain(null,
					MessengerService.CONSUME_TWEETS_AND_NOTIFICATE, 0, 0);
			try {
				// El mensaje es enviado.
				messenger.send(message);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
    }

	public void setMessenger(Messenger messenger) {
		this.messenger = messenger;
	}

}
