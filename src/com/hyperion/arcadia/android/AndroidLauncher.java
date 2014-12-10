package com.hyperion.arcadia.android;

import java.util.HashMap;

import android.os.Bundle;
import android.util.Log;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.Tracker;
import com.google.android.gms.analytics.HitBuilders;
import com.hyperion.arcadia.MainGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		MainGame.isAndroid = true;
		// Get tracker.
		Tracker t = getTracker(TrackerName.APP_TRACKER);

		// Set screen name.
//		t.setScreenName("Android Main Screen");

		// Send a screen view.
		t.send(new HitBuilders.AppViewBuilder().build());
		Log.d("Analytics", "Main");
		initialize(new MainGame(), config);
	}

	@Override
	public void onStart() {
		super.onStart();
		EasyTracker.getInstance(this).activityStart(this); // Add this method.
		Log.d("Analytics", "Start");
	}

	@Override
	public void onStop() {
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this); // Add this method.
		Log.d("Analytics", "Stop");
	}

	public enum TrackerName {
		APP_TRACKER, // Tracker used only in this app.
	}

	HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();

	synchronized Tracker getTracker(TrackerName trackerId) {
		if (!mTrackers.containsKey(trackerId)) {

			GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
			Tracker t = analytics.getTracker("" + R.xml.app_tracker);
			mTrackers.put(trackerId, t);

		}
		return mTrackers.get(trackerId);
	}

}
