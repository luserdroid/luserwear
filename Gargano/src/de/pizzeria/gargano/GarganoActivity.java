package de.pizzeria.gargano;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.Window;

public class GarganoActivity extends Activity {
	
	public static final String TAG = GarganoActivity.class.getSimpleName();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        TimerTask timertask = new TimerTask() {
			
			@Override
			public void run() {
				DashboardActivity.start(GarganoActivity.this);
				Log.i(TAG,"go to ");
				GarganoActivity.this.finish();				
			}
		};
		
		Timer timer = new Timer();
		timer.schedule(timertask, 2000);

    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		
		return true;
	}
}