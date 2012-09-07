package co.edu.uniquindio.servicesexamples;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import co.edu.uniquindio.servicesexamples.BinderService.LocalBinder;

public class ServiceConnectionForBinder implements ServiceConnection {
	
	private MainActivity mainActivity;
	
	public ServiceConnectionForBinder(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
	}

	@Override
	public void onServiceConnected(ComponentName className, IBinder binder) {
		
		// Se obtiene el servicio del Binder y se asigna al atributo de la
		// actividad principal para allí ser usado.
		LocalBinder localBinder = (LocalBinder) binder;
		mainActivity.setBinderService(localBinder.getService());
	}

	@Override
	public void onServiceDisconnected(ComponentName className) {}

}
