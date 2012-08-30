package org.elkuku.pizzza;

import java.util.List;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class MenuListFragment extends ListFragment {

	private List<TEntry> menuList;

	private MenuDataSource datasource;

	private static final int REQUEST_CODE = 10;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		update();
	}

	public MenuDataSource getDataSource() {
		return datasource;
	}

	public void update() {
		/*
		 * SharedPreferences prefs =
		 * PreferenceManager.getDefaultSharedPreferences(getActivity());
		 *
		 * String a1 = prefs.getString("foox", "bar");
		 *
		 * Log.w("PREFTEST", a1);
		 */
		datasource = new MenuDataSource(getActivity(), this);

		datasource.open();
		menuList = datasource.getAllEntries();
		datasource.close();

		// TODO we are creating a dummy list - learn java => ArrayAdapter ;(
		String[] dummylist = new String[menuList.size()];

		MenuAdapter adapter = new MenuAdapter(getActivity(), menuList, dummylist);

		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		MenuAdapter adapter = (MenuAdapter) getListAdapter();

		TEntry item = adapter.getEntry(position);

		// -- Click on a category
		if (0 == item.getCatid())
			return;

		Intent intent = adapter.getEntryToIntent(position, REQUEST_CODE);

		startActivityForResult(intent, REQUEST_CODE);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode == android.app.Activity.RESULT_OK && requestCode == REQUEST_CODE) {
			if (data.hasExtra("favorite")) {
				datasource.setFavorite(data.getLongExtra("id", 0), data.getIntExtra("favorite", 0));

				update();

				//Toast.makeText(getActivity(), "favorite set: "+data.getLongExtra("id", 0)+"- - "+data.getIntExtra("favorite", 0), Toast.LENGTH_SHORT).show();
			}
		}
	}

}
