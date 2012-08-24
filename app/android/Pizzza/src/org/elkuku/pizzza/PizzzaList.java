package org.elkuku.pizzza;

import java.util.List;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PizzzaList extends ListFragment {

	private List<Entry> menuList;

	private DataSourceMenu datasource;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		datasource = new DataSourceMenu(getActivity());

		datasource.open();
		menuList = datasource.getAllEntries();
		datasource.close();

		ArrayAdapter<Entry> adapter = new ArrayAdapter<Entry>(getActivity(),
				android.R.layout.simple_list_item_1, menuList);

		setListAdapter(adapter);
	}

	public void update() {
		datasource = new DataSourceMenu(getActivity());

		datasource.open();
		menuList = datasource.getAllEntries();
		datasource.close();

		ArrayAdapter<Entry> adapter = new ArrayAdapter<Entry>(getActivity(),
				android.R.layout.simple_list_item_1, menuList);

		setListAdapter(adapter);
	}
/*
	public void addEntry() {
		try {
			int catid = 4711;
			String title = "testTitle";
			String description = "testDesc";

			datasource.open();
			// datasource.createEntry(catid, title, description);

			Entry entry = datasource.createEntry(catid, title, description);

			ArrayAdapter a = (ArrayAdapter) getListAdapter();
			a.notifyDataSetChanged();

			// notifyAll();
			// getView().invalidate();
			datasource.close();
			// View vvv = adapter.getView();
		} catch (Exception e) {
			Log.e("DBDEBUG", e.toString());
			// Toast.makeText(getActivity(), e.toString(),
			// Toast.LENGTH_LONG).show();
		}
	}
*/
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Entry item = (Entry) getListAdapter().getItem(position);

		TextView txt1 = (TextView) getActivity().findViewById(R.id.textView1);
		txt1.setText(item.getTitle());

		TextView txt2 = (TextView) getActivity().findViewById(R.id.textView2);
		txt2.setText(String.valueOf(item.getId()));
	}

}
