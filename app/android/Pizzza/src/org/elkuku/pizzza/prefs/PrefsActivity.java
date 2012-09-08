package org.elkuku.pizzza.prefs;

import java.util.List;

import org.elkuku.pizzza.R;

import android.preference.PreferenceActivity;

public class PrefsActivity extends PreferenceActivity {

	@Override
	public void onBuildHeaders(List<Header> target) {
		loadHeadersFromResource(R.xml.preferences_headers, target);
	}

}
