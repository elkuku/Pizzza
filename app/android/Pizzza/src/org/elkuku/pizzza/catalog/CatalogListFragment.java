package org.elkuku.pizzza.catalog;

import java.util.Calendar;
import java.util.List;

import org.elkuku.pizzza.R;
import org.elkuku.pizzza.promos.PromosDataSource;
import org.elkuku.pizzza.types.TEntry;
import org.elkuku.pizzza.types.TPromo;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class CatalogListFragment extends ListFragment {

	private CatalogDataSource datasource;

	private static final int REQUEST_CODE = 10;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		datasource = new CatalogDataSource(getActivity(), this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		View view = inflater.inflate(R.layout.catalog, container, false);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		Calendar now = Calendar.getInstance();

		String[] strDays = new String[] { "domingo", "lunes", "martes", "miercoles", "jueves", "viernes", "sabado" };

		String curDay = strDays[now.get(Calendar.DAY_OF_WEEK) - 1];

		PromosDataSource datasource = new PromosDataSource(getActivity());

		datasource.open();
		List<TPromo> listO = datasource.getAllEntries();
		datasource.close();

		String promoText = "";

		for (TPromo item : listO) {
			if (item.getDay().equals(curDay)) {
				promoText = item.getName();
			}
		}

		TextView txtPromo = (TextView) getView().findViewById(R.id.txtPromo);

		if (promoText.contentEquals("")) {
			// -- No Promo today :(
			txtPromo.setVisibility(View.GONE);
		} else {
			txtPromo.setText(String.format(getResources().getString(R.string.promo_slogan), promoText));
		}

		update();
	}

	public CatalogDataSource getDataSource() {
		return datasource;
	}

	public void update() {
		List<TEntry> list;

		datasource.open();
		list = datasource.getAllEntries();
		datasource.close();

		// TODO we are creating a dummy list - learn java => ArrayAdapter ;(
		String[] dummylist = new String[list.size()];

		CatalogAdapter adapter = new CatalogAdapter(getActivity(), list, dummylist);

		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		CatalogAdapter adapter = (CatalogAdapter) getListAdapter();

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
			}
		}
	}

}
