package org.elkuku.pizzza;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class PromosDataSource {
	private Context context;
	private SQLiteDatabase database;
	private SQLiteHelper dbhelper;
	private Cursor cursor;

	private String table = "promos";
	private String[] columns = { "id", "name", "day" };

	public PromosDataSource(Context context) {

		this.context = context;

		dbhelper = new SQLiteHelper(context);

		database = dbhelper.getWritableDatabase();
	}

	public void open() throws SQLException {
		database = dbhelper.getWritableDatabase();
	}

	public void close() {
		dbhelper.close();
	}

	public void update() {
		String url = context.getString(R.string.pizzza_link_base) + "/" + context.getString(R.string.pizzza_promo_link);

		new BackgroundTaskText().execute(url);
	}

	public TPromo createEntry(String name, String day) {

		ContentValues values = new ContentValues();

		values.put("name", name);
		values.put("day", day);

		long insertId = database.insert(table, null, values);

		cursor = database.query(table, columns, "id = " + insertId, null, null, null, null);

		cursor.moveToFirst();

		TPromo item = cursorToItem(cursor);

		cursor.close();

		return item;
	}
/*
	public TPromo getItem() {
		cursor = database.query(table, columns, null, null, null, null, null);
		cursor.moveToFirst();

		TPromo item = new TPromo();

		if (cursor.getCount() == 0)
			return item;

		while (cursor.isAfterLast() == false) {
			item = cursorToItem(cursor);

			cursor.moveToNext();
		}

		cursor.close();

		return item;
	}
 */

	protected List<TPromo> getAllEntries() {
		List<TPromo> list = new ArrayList<TPromo>();

		cursor = database.query(table, columns, null, null, null, null, null);
		cursor.moveToFirst();

		if (cursor.getCount() == 0)
			return list;

		while (cursor.isAfterLast() == false) {
			TPromo entry = cursorToItem(cursor);
			list.add(entry);
			cursor.moveToNext();
		}

		cursor.close();

		return list;
	}

	protected List<String> getList() {
		List<String> list = new ArrayList<String>();

		cursor = database.query(table, columns, null, null, null, null, null);
		cursor.moveToFirst();

		if (cursor.getCount() == 0)
			return list;

		while (cursor.isAfterLast() == false) {
			String entry = cursor.getString(1);
			list.add(entry);
			cursor.moveToNext();
		}

		cursor.close();

		return list;
	}

	private void updateFromUpstream(JSONObject jsonObject) throws JSONException {

		String sql = "DELETE FROM " + table;

		database.execSQL(sql);

		JSONArray data = jsonObject.getJSONArray("data");

		for (int i = 0; i < data.length(); i++) {

			JSONArray items = data.getJSONObject(i).getJSONArray("items");

			for (int i1 = 0; i1 < items.length(); i1++) {

				JSONObject item = items.getJSONObject(i1);

				createEntry(item.getString("field_name"), item.getString("field_dia_promo"));
			}
		}
	}

	private TPromo cursorToItem(Cursor cursor) {
		TPromo item = new TPromo();

		item.setId(cursor.getLong(0));
		item.setName(cursor.getString(1));
		item.setDay(cursor.getString(2));

		return item;
	}

	private class BackgroundTaskText extends AsyncTask<String, Void, String> {

		protected String doInBackground(String... url) {
			String s = "";

			try {
				HttpHelper HttpHelper = new HttpHelper();

				s = HttpHelper.fetchUrl(url[0]);
			} catch (IOException e) {
				Log.e("PIZZA", e.getMessage());
			}

			return s;
		}

		protected void onPostExecute(String string) {

			if (0 == string.length()) {
				Toast.makeText(context, R.string.request_failed, Toast.LENGTH_LONG).show();
			} else {
				try {
					updateFromUpstream(new JSONObject(string));
				} catch (JSONException e) {
					e.printStackTrace();
					Log.e("PIZZZA", e.getMessage());
				}
			}
		}
	}

}
