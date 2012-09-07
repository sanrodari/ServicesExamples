package co.edu.uniquindio.servicesexamples;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {


	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
	
	//--------------------------------------
	//----------- Iniciado -----------------
	
	/**
	 * Por medio de un servicio de tipo iniciado y con la utilidad de 
	 * {@link IntentService}.
	 */
	public void onClickLoadTweets(View view) {
		Log.i("ServicesExamples", "Desde el Hilo principal");
		
		Intent intent = new Intent(this, StartedService.class);
		startService(intent);
	}

	
	//---------------------------------
	//------------Binder---------------
	
	private BinderService binderService;
	
	private ServiceConnectionForBinder sForBinder = new ServiceConnectionForBinder(this);
	
	@Override
	protected void onStart() {
		super.onStart();
		
		Intent intent = new Intent(this, BinderService.class);
		bindService(intent, sForBinder, BIND_AUTO_CREATE);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		
		try {
			unbindService(sForBinder);
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("ServicesExamples", "No se puede desligar el servicio.");
		}
	}
	
	/**
	 * Por medio de un servicio de tipo ligado y con la utilidad de
	 * {@link Binder}.
	 */
	public void onClickLoadTweetsBinder(View view) {
		Log.i("ServicesExamples", "Desde el Hilo principal");
		
		if(binderService != null) {
			binderService.consumeTweetsAndCreateNotification();
		}
	}
	
	public void setBinderService(BinderService binderService) {
		this.binderService = binderService;
	}
	
}
