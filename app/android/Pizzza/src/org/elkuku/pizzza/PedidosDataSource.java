package org.elkuku.pizzza;

import java.io.IOException;

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

public class PedidosDataSource {
	private Context context;
	private SQLiteDatabase database;
	private SQLiteHelper dbhelper;
	private Cursor cursor;

	private String table = "contact";
	private String[] columns = { "id", "name", "address", "city", "phone1", "phone2", "phone3", "email", "webpage", "misc" };

	public PedidosDataSource(Context context) {

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
		String url = context.getString(R.string.pizzza_link_base) + "/"
				+ context.getString(R.string.pizzza_contact_link);

		new BackgroundTaskText().execute(url);
	}

	public TContact createEntry(String name, String address, String city, String phone1, String phone2, String phone3,
			String email, String webpage, String misc) {

		ContentValues values = new ContentValues();

		values.put("name", name);
		values.put("address", address);
		values.put("city", city);
		values.put("phone1", phone1);
		values.put("phone2", phone2);
		values.put("phone3", phone3);
		values.put("email", email);
		values.put("webpage", webpage);
		values.put("misc", misc);

		long insertId = database.insert(table, null, values);

		cursor = database.query(table, columns, "id = " + insertId, null, null, null, null);

		cursor.moveToFirst();

		TContact contact = cursorToContact(cursor);

		cursor.close();

		return contact;
	}

	private void updateFromUpstream(JSONObject jsonObject) throws JSONException {

		String sql = "DELETE FROM " + table;

		database.execSQL(sql);

		JSONObject item = jsonObject.getJSONObject("data");

		createEntry(item.getString("name"), item.getString("address"), item.getString("suburb"),
				item.getString("telephone"), item.getString("fax"), item.getString("mobile"), item.getString("email_to"),
				item.getString("webpage"), item.getString("misc"));
	}

	public TContact getContact() {
		cursor = database.query(table, columns, null, null, null, null, null);
		cursor.moveToFirst();

		TContact contact = new TContact();

		if (cursor.getCount() == 0)
			return contact;

		while (cursor.isAfterLast() == false) {
			contact = cursorToContact(cursor);

			cursor.moveToNext();
		}

		cursor.close();

		return contact;
	}

	private TContact cursorToContact(Cursor cursor) {
		TContact entry = new TContact();

		entry.setId(cursor.getLong(0));
		entry.setName(cursor.getString(1));
		entry.setAddress(cursor.getString(2));
		entry.setCity(cursor.getString(3));
		entry.setPhone1(cursor.getString(4));
		entry.setPhone2(cursor.getString(5));
		entry.setPhone3(cursor.getString(6));
		entry.setEmail(cursor.getString(7));
		entry.setWebpage(cursor.getString(8));
		entry.setMisc(cursor.getString(9));

		return entry;
	}

	private class BackgroundTaskText extends AsyncTask<String, Void, String> {

		public BackgroundTaskText() {

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

				} catch (JSONException e) {
					e.printStackTrace();
					Log.e("PIZZZA", e.getMessage());
				}
			}
		}
	}

}
