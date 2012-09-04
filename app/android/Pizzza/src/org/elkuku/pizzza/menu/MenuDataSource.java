package org.elkuku.pizzza.menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elkuku.pizzza.R;
import org.elkuku.pizzza.R.string;
import org.elkuku.pizzza.helpers.HttpHelper;
import org.elkuku.pizzza.helpers.SQLiteHelper;
import org.elkuku.pizzza.types.TEntry;
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

public class MenuDataSource {

	private Context context;
	private MenuListFragment fragment;
	private SQLiteDatabase database;
	private SQLiteHelper dbhelper;
	private Cursor cursor;
	private String table = "menu";
	private String[] columns = { "id", "catid", "title", "description", "price_peq", "price_med", "price_gra",
			"favorite" };

	public MenuDataSource(Context context, MenuListFragment fragment) {

		this.context = context;
		this.fragment = fragment;

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

		String url = context.getString(R.string.pizzza_link_base) + "/" + context.getString(R.string.pizzza_menu_link);

		new BackgroundTaskText().execute(url);
	}

	public TEntry createEntry(int catid, String title, String description, double p_peq, double p_med, double p_gra) {

		ContentValues values = new ContentValues();

		values.put("catid", catid);
		values.put("title", title);
		values.put("description", description);
		values.put("price_peq", p_peq);
		values.put("price_med", p_med);
		values.put("price_gra", p_gra);

		long insertId = database.insert(table, null, values);

		cursor = database.query(table, columns, "id = " + insertId, null, null, null, null);

		cursor.moveToFirst();

		TEntry entry = cursorToEntry(cursor);

		cursor.close();

		return entry;
	}

	public List<Long> getFavorites() {
		List<Long> list = new ArrayList<Long>();

		Cursor cursor = database.query(table, new String[] { "id" }, "favorite=?", new String[] { "1" }, null, null,
				null);

		cursor.moveToFirst();

		if (cursor.getCount() == 0)
			return list;

		while (cursor.isAfterLast() == false) {
			list.add(cursor.getLong(0));
			cursor.moveToNext();
		}

		cursor.close();

		return list;
	}

	public void setFavorite(long id, int favorite) {

		open();

		ContentValues values = new ContentValues();
		values.put("favorite", favorite);

		String[] ids = new String[] { String.valueOf(id) };

		database.update(table, values, "id = ?", ids);
	}

	private void updateFromUpstream(JSONObject jsonObject) throws JSONException {

		// -- Store favorites
		List<Long> oldFavs = getFavorites();

		String sql = "DELETE FROM " + table;

		database.execSQL(sql);

		JSONArray categories = jsonObject.getJSONArray("data");

		for (int i = 0; i < categories.length(); i++) {

			createEntry(0, categories.getJSONObject(i).getString("name").toString(), "", 0, 0, 0);

			JSONArray items = categories.getJSONObject(i).getJSONArray("items");

			for (int i1 = 0; i1 < items.length(); i1++) {

				JSONObject item = items.getJSONObject(i1);

				createEntry(item.getInt("catid"), item.getString("field_name"), item.getString("field_ingredientes"),
						item.getDouble("field_precio_peq"), item.getDouble("field_precio_med"),
						item.getDouble("field_precio_gra"));
			}
		}

		// -- Re setting the favorites
		for (Long id : oldFavs) {
			Log.w("PIZZZA", "" + id);
			setFavorite(id, 1);
		}

	}

	protected List<TEntry> getAllEntries() {
		List<TEntry> list = new ArrayList<TEntry>();

		cursor = database.query(table, columns, null, null, null, null, null);
		cursor.moveToFirst();

		if (cursor.getCount() == 0)
			return list;

		while (cursor.isAfterLast() == false) {
			TEntry entry = cursorToEntry(cursor);
			list.add(entry);
			cursor.moveToNext();
		}

		cursor.close();

		return list;
	}

	private TEntry cursorToEntry(Cursor cursor) {
		TEntry entry = new TEntry();

		entry.setId(cursor.getLong(0));
		entry.setCatid(cursor.getLong(1));
		entry.setTitle(cursor.getString(2));
		entry.setDescription(cursor.getString(3));
		entry.setPrice_peq(cursor.getFloat(4));
		entry.setPrice_med(cursor.getFloat(5));
		entry.setPrice_gra(cursor.getFloat(6));
		entry.setFavorite(cursor.getInt(7));

		return entry;
	}

	private class BackgroundTaskText extends AsyncTask<String, Void, String> {

		public BackgroundTaskText() {

			fragment.setListShown(false);
		}

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

					fragment.update();
					fragment.setListShown(true);

				} catch (JSONException e) {
					e.printStackTrace();
					Log.e("PIZZZA", e.getMessage());
				}
			}
		}
	}
}
