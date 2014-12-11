package com.hyperion.arcadia.android;

import java.util.HashMap;

import android.os.Bundle;
import android.util.Log;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import com.hyperion.arcadia.MainGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		MainGame.isAndroid = true;
		
		// Get tracker .
		Tracker t = getTracker(TrackerName.APP_TRACKER);

		// Set screen name.
//		t.setScreenName("Android Main Screen");

		// Send a screen view.
		t.send(new HitBuilders.ScreenViewBuilder().build());
		Log.d("Analytics", "Maina");
		
		
		Tracker t2 = GoogleAnalytics.getInstance(getContext()).newTracker("UA-376062-56");
		 t2.set("Arcadia", "ArcadiaMainActivity");
		 t2.send(new HitBuilders.ScreenViewBuilder().build());

		initialize(new MainGame(), config);
	}

//	@Override
//	public void onStart() {
//		super.onStart();
//		EasyTracker.getInstance(this).activityStart(this); // Add this method.
//		Log.d("Analytics", "Start");
//	}
//
//	@Override
//	public void onStop() {
//		super.onStop();
//		EasyTracker.getInstance(this).activityStop(this); // Add this method.
//		Log.d("Analytics", "Stop");
//	}

	public enum TrackerName {
		APP_TRACKER, // Tracker used only in this app.
	}

	HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();

	synchronized Tracker getTracker(TrackerName trackerId) {
		if (!mTrackers.containsKey(trackerId)) {

			GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
			Tracker t = analytics.newTracker("UA-376062-56" 
//			+ R.xml.app_tracker
			);
			mTrackers.put(trackerId, t);
			Log.d("Analytics", "Tracker creado:\n"+t);
		}
		return mTrackers.get(trackerId);
	}

}
