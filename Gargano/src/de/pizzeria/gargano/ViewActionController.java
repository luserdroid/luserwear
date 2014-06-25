package de.pizzeria.gargano;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import de.pizzeria.gargano.tools.IntentTools;

public class ViewActionController implements OnClickListener {

	private Activity activity;
	private ImageView call;
	private ImageView share;
	private ImageView facebook;
	private ImageView maps;
	
	public ViewActionController(Activity activity) {
		this.activity = activity;
		call = (ImageView) (activity.findViewById(R.id.calling));
		share = (ImageView) activity.findViewById(R.id.sms);
		facebook = (ImageView) activity.findViewById(R.id.facebook);
		maps = (ImageView) activity.findViewById(R.id.maps);
	}

	public void registerActions() {
		if(call != null && share != null && facebook != null && maps != null){
			call.setOnClickListener(this);
			share.setOnClickListener(this);
			facebook.setOnClickListener(this);
			maps.setOnClickListener(this);			
		}
	}
	
	public void unregisterActions(){
		call = null;
		share = null;
		facebook = null;
		maps = null;	
	}

	@Override
	public void onClick(View v) {

		Intent intent = null;
		switch (v.getId()) {
		case R.id.maps:
			String name = "Pizzeria Gargano";
			intent = new Intent(
					android.content.Intent.ACTION_VIEW,
					Uri.parse("geo:0,0?q=Pizzeria Gargano Gumbertstrasse 99 40231 D�sseldorf Deutschland("
							+ name + ")"));
			break;
		case R.id.facebook:
			String url = "http://www.facebook.com/pages/Pizzeria-Gargano/198759966873712";
			intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse(url));
			break;
		case R.id.sms:
			IntentTools.send("Pizzeria Gargano", "Pizzeria Gargano",
					"02112291303", "", false, activity);
			break;
		case R.id.calling:
			try {
				intent = new Intent(Intent.ACTION_CALL);
				intent.setData(Uri.parse("tel:+492112291303"));
			} catch (ActivityNotFoundException activityException) {
				Log.e("helloandroid dialing example", "Call failed");
			}
			break;
		}
		
		if(intent != null){
			activity.startActivity(intent);
		}
	}

}
