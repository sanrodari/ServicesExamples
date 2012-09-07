package co.edu.uniquindio.servicesexamples;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class BinderService extends Service {
	
	private BinderService instance = this;

	/**
	 * Implementación del IBinder por medio de una subclase de
	 * {@link Binder}
	 */
	public class LocalBinder extends Binder {
		
		/** Se retorna la instancia del servicio. */
		public BinderService getService() {
			return instance;
		}
		
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return new LocalBinder();
	}
	
	//------------------------------
	//-------- Servicios -----------
	
	/**
	 * Para consumir los tweets y crear la notificación.
	 */
	public void consumeTweetsAndCreateNotification() {
		
		// Por medio del Binder no se crea un hilo diferente al principal, por
		// lo tanto el programador lo debe crear manualmente.
		
		Thread consumerThread = new Thread(new Runnable() {
			public void run() {
				Log.i("ServicesExamples", "Por medio de servicio ligado y Binder");
				
				TwitterConsumer consumer = new TwitterConsumer(instance);
				consumer.consumeTweetsAndCreateNotification();
			}
		});
		
		consumerThread.start();
	}

}
