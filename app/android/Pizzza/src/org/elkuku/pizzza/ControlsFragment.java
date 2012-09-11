package org.elkuku.pizzza;

import org.elkuku.pizzza.catalog.CatalogActivity;
import org.elkuku.pizzza.catalog.CatalogDataSource;
import org.elkuku.pizzza.catalog.CatalogListFragment;
import org.elkuku.pizzza.news.NewsActivity;
import org.elkuku.pizzza.news.NewsDataSource;
import org.elkuku.pizzza.news.NewsListFragment;
import org.elkuku.pizzza.orders.OrdersActivity;
import org.elkuku.pizzza.orders.OrdersDataSource;
import org.elkuku.pizzza.orders.OrdersFragment;
import org.elkuku.pizzza.promos.PromosActivity;
import org.elkuku.pizzza.promos.PromosDataSource;
import org.elkuku.pizzza.promos.PromosListFragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ControlsFragment extends ListFragment {
	boolean mDualPane;
	int mCurCheckPosition = 0;

	@Override
	public void onActivityCreated(Bundle savedState) {
		super.onActivityCreated(savedState);

		// TODO: save a last update timestamp "somewhere" =;)
		refreshDb();

		String[] list = { getString(R.string.btn_catalog), getString(R.string.btn_promos),
				getString(R.string.btn_news), getString(R.string.btn_orders) };// ,
																				// "DB Refresh"
																				// };

		setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, list));

		View detailsFrame = getActivity().findViewById(R.id.details);

		mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

		if (savedState != null) {
			mCurCheckPosition = savedState.getInt("curChoice", 0);
		}

		if (mDualPane) {
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			showDetails(mCurCheckPosition);
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putInt("curChoice", mCurCheckPosition);
	}

	@Override
	public void onListItemClick(ListView l, View v, int pos, long id) {
		showDetails(pos);
	}

	void showDetails(int index) {
		mCurCheckPosition = index;

		if (mDualPane) {

			Fragment fragment = null;

			switch (index) {
			case 0:
				fragment = new CatalogListFragment();
				break;

			case 1:
				fragment = new PromosListFragment();
				break;

			case 2:
				fragment = new NewsListFragment();
				break;

			case 3:
				fragment = new OrdersFragment();
				break;

			case 4:
				refreshDb();
				return;

			default:
				Log.e("PIZZZA", "Undefined index " + index);
				return;
			}

			getListView().setItemChecked(index, true);

			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.replace(R.id.details, fragment);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.commit();
		} else {
			Intent intent = new Intent();

			switch (index) {
			case 0:
				intent.setClass(getActivity(), CatalogActivity.class);
				break;

			case 1:
				intent.setClass(getActivity(), PromosActivity.class);
				break;

			case 2:
				intent.setClass(getActivity(), NewsActivity.class);
				break;

			case 3:
				intent.setClass(getActivity(), OrdersActivity.class);
				break;

			case 4:
				refreshDb();
				return;

			default:
				Log.e("PIZZZA", "Undefined index " + index);
				return;
			}

			intent.putExtra("index", index);
			startActivity(intent);
		}
	}

	private void refreshDb() {
		CatalogListFragment fragment = (CatalogListFragment) getFragmentManager().findFragmentById(R.id.details);

		CatalogDataSource menuDatasource = new CatalogDataSource(getActivity(), fragment);
		OrdersDataSource pedidosDatasource = new OrdersDataSource(getActivity());
		PromosDataSource promosDatasource = new PromosDataSource(getActivity());
		NewsDataSource newsDatasource = new NewsDataSource(getActivity());

		try {
			menuDatasource.update();
			pedidosDatasource.update();
			promosDatasource.update();
			newsDatasource.update();
		} catch (Exception e) {
			Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
	}
}

/*
 *
 *
 *
 *
 * /* T E S T ing
 */

/*
 * Delete db Button button2 = (Button)
 * getActivity().findViewById(R.id.btnKillDb);
 *
 * button2.setOnClickListener(new OnClickListener() {
 *
 * @Override public void onClick(View v) {
 *
 * MenuListFragment fragment = (MenuListFragment)
 * getActivity().getSupportFragmentManager
 * ().findFragmentById(R.id.listFragment);
 *
 * SQLiteHelper dbhelper = new SQLiteHelper(getActivity());
 *
 * dbhelper.resetDb();
 *
 * Toast.makeText(getActivity(), "Database has been deleted",
 * Toast.LENGTH_LONG).show();
 *
 * fragment.onActivityCreated(null); } });
 *
 * /* Refresh db Button btnRefresh = (Button)
 * getActivity().findViewById(R.id.btnRefresh);
 *
 * btnRefresh.setOnClickListener(new OnClickListener() {
 *
 * @Override public void onClick(View v) {
 *
 * MenuListFragment fragment = (MenuListFragment)
 * getFragmentManager().findFragmentById(R.id.listFragment);
 *
 * MenuDataSource menuDatasource = new MenuDataSource(getActivity(), fragment);
 * OrdersDataSource pedidosDatasource = new OrdersDataSource(getActivity());
 * PromosDataSource promosDatasource = new PromosDataSource(getActivity());
 * NewsDataSource newsDatasource = new NewsDataSource(getActivity());
 *
 * try { menuDatasource.update(); pedidosDatasource.update();
 * promosDatasource.update(); newsDatasource.update(); } catch (Exception e) {
 * Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
 * e.printStackTrace(); } } }); }
 *
 * }
 */