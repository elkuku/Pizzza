package org.elkuku.pizzza;

import java.util.List;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class PizzzaList extends ListFragment {

	private List<TEntry> menuList;

	private DataSourceMenu datasource;

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

	public DataSourceMenu getDataSource() {
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
		datasource = new DataSourceMenu(getActivity());

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

		TextView txt1 = (TextView) getActivity().findViewById(R.id.textView1);
		txt1.setText(item.getTitle());

		TextView txt2 = (TextView) getActivity().findViewById(R.id.textView2);
		txt2.setText(String.valueOf(item.getId()));

		Intent i = new Intent(getActivity(), InfoActivity.class);

		i.putExtra("id", item.getId());
		i.putExtra("title", item.getTitle());
		i.putExtra("description", item.getDescription());
		i.putExtra("price_peq", item.getPrice_peq());
		i.putExtra("price_med", item.getPrice_med());
		i.putExtra("price_gra", item.getPrice_gra());
		i.putExtra("favorite", item.getFavorite());

		startActivityForResult(i, REQUEST_CODE);
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
