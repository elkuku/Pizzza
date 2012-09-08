package org.elkuku.pizzza.prefs;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class PrefsFragment extends PreferenceFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		int res = getActivity().getResources().getIdentifier(getArguments().getString("resource"), "xml",
				getActivity().getPackageName());

		addPreferencesFromResource(res);
	}
}
