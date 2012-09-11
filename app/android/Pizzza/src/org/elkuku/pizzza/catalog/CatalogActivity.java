package org.elkuku.pizzza.catalog;

import android.app.Activity;
import android.os.Bundle;

public class CatalogActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (savedInstanceState == null) {
			CatalogListFragment fragment = new CatalogListFragment();
			fragment.setArguments(getIntent().getExtras());
			getFragmentManager().beginTransaction().add(android.R.id.content, fragment).commit();
		}
	}
}
