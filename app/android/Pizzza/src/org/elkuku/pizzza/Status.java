package org.elkuku.pizzza;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Status extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		addButtonListeners();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.status, container, false);
		return view;
	}

	public void addButtonListeners() {

		Button button = (Button) getActivity().findViewById(R.id.button1);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				TextView txt1 = (TextView) getActivity().findViewById(
						R.id.textView1);

				Button button = (Button) v.findViewById(R.id.button1);

				String bb = button.getText().toString();

				txt1.setText(bb);
				Log.e("BBB", bb);
			}

		});

		/* Delete db */
		Button button2 = (Button) getActivity().findViewById(R.id.button2);

		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				PizzzaList fragment = (PizzzaList)getFragmentManager().findFragmentById(R.id.listFragment);

				SQLiteHelper dbhelper = new SQLiteHelper(getActivity());
				dbhelper.resetDb();

				Toast.makeText(getActivity(), "Database has been deleted",
						Toast.LENGTH_LONG).show();

				fragment.onActivityCreated(null);
			}

		});

		/* Insert db */
		Button button3 = (Button) getActivity().findViewById(R.id.btnInsert);

		button3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				/*
				PizzzaList fragment = (PizzzaList)getFragmentManager().findFragmentById(R.id.listFragment);

				fragment.addEntry();

//				PizzzaList p = new PizzzaList();

	//			p.addEntry();
*/

				PizzzaList fragment = (PizzzaList)getFragmentManager().findFragmentById(R.id.listFragment);

				DataSourceMenu datasource = new DataSourceMenu(getActivity());
				//fragment.setListShown(false);



				int catid = 123;
				String title = "First entryxx";
				String description = "First description";

				Entry entry = datasource.createEntry(catid, title, description);

				fragment.update();
				//fragment.setListShown(true);
				/*
ArrayAdapter adapter = (ArrayAdapter)fragment.getListAdapter();
adapter.notifyDataSetChanged();
fragment.onStart();
*/
//fragment.setListAdapter(adapter);
				/*
*/


				//View vw = getActivity().findViewById(R.id.tutlist_fragment);



				datasource.close();

				//Toast.makeText(getActivity(), "Entry "+entry.getTitle()+" has been created",
				//		Toast.LENGTH_LONG).show();
			}

		});

		/* Refresh db */
		Button btnRefresh = (Button) getActivity().findViewById(R.id.btnRefresh);

		btnRefresh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				PizzzaList fragment = (PizzzaList)getFragmentManager().findFragmentById(R.id.listFragment);

				DataSourceMenu datasource = new DataSourceMenu(getActivity());

				ContentProvider contentProvider = new ContentProvider(getActivity());

				//String foo = contentProvider.getFoo();

				try {
					String list = contentProvider.provide("menu", datasource, fragment);
					Toast.makeText(getActivity(), list, Toast.LENGTH_SHORT).show();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}

			//	DataSourceMenu datasource = new DataSourceMenu(getActivity());

			//	SQLiteHelper dbhelper = new SQLiteHelper(getActivity());

//				dbhelper.resetDb();




/*
				int catid = 123;
				String title = "First entry";
				String description = "First description";

				Entry entry = datasource.createEntry(catid, title, description);

				Toast.makeText(getActivity(), "Entries have been refreshed.",
						Toast.LENGTH_LONG).show();
 */
			}

		});

	}
}
