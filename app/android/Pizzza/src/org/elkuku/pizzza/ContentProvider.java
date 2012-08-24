package org.elkuku.pizzza;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class ContentProvider {

	private Context context;

	private DataSourceMenu datasource;

	private PizzzaList fragment;

	public ContentProvider(Context c) {
		context = c;
	}

	public String getFoo() {
		String foo = "bar";

		return foo;
	}

	/*
	 * public List<Entry> provide(String type) { List<Entry> list = new
	 * ArrayList<Entry>(); }
	 */
	public String provide(String type, DataSourceMenu d, PizzzaList f) throws Exception {
		datasource = d;
		fragment = f;

		String resp = "";
		String url;

		if (type == "menu") {
			url = "http://192.168.0.100/pizza-brazil/index.php?option=com_pizzza&task=request.listall&format=json&Itemid=107";

			new BackgroundTaskText().execute(url);
			resp = "background task started...";

		} else {
			throw new Exception("Invalid type");
		}

		return resp;
	}

	private class BackgroundTaskText extends AsyncTask<String, Void, String> {

		private JSONObject jsonObject;

		String strParsedValue = null;

		public BackgroundTaskText() {
			// TODO Auto-generated constructor stub
		}

		protected String doInBackground(String... url) {
			String s = "";

			try {
				s = DownloadText(url[0]);

				return s;
			} catch (IOException e) {
				Log.e("PIZZA", e.getMessage());
				s = e.getMessage();
			}

			return s;
		}

		protected void onPreExecute(Bitmap bitmap) {

		}

		protected void onPostExecute(String string) {
			try {
				Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
				// this.strJSONValue = string;
				ArrayList<ContentValues> ccc = parseJSON(string);
				String aaa = ccc.toString();

				Toast.makeText(context, ccc.toString(), Toast.LENGTH_SHORT).show();

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public ArrayList<ContentValues> parseJSON(String string) throws JSONException {

			datasource.reset();

			ArrayList<ContentValues> list = new ArrayList<ContentValues>();

			jsonObject = new JSONObject(string);

			JSONArray categories = jsonObject.getJSONArray("data");

			for (int i = 0; i < categories.length(); i++) {
				strParsedValue += "\n" + categories.getJSONObject(i).getString("name").toString();

				JSONArray items = categories.getJSONObject(i).getJSONArray("items");

				for (int i1 = 0; i1 < items.length(); i1++) {
					ContentValues values = new ContentValues();


					values.put("catid", 123);
					values.put("title", items.getJSONObject(i1).getString("title").toString());
					values.put("description", items.getJSONObject(i1).getString("title").toString());

					JSONObject item = items.getJSONObject(i1);

					datasource.createEntry(
							123,
							item.getString("title"),
							item.getString("description")
							);

					list.add(values);

					// list.add(putData(items.getJSONObject(i1).getString("title")
					// .toString(),
					// items.getJSONObject(i1).getString("description")
					// .toString()));
					strParsedValue += "\n---" + items.getJSONObject(i1).getString("title").toString();
					strParsedValue += "\n------" + items.getJSONObject(i1).getString("description").toString();
				}
			}

			Toast.makeText(context, strParsedValue, Toast.LENGTH_LONG).show();

			fragment.update();

			return list;
		}
	}// class

	private String DownloadText(String URL) throws IOException {
		int BUFFER_SIZE = 2000;
		InputStream in = null;

		in = OpenHttpConnection(URL);

		InputStreamReader isr = new InputStreamReader(in);
		int charRead;
		String str = "";
		char[] inputBuffer = new char[BUFFER_SIZE];

		while ((charRead = isr.read(inputBuffer)) > 0) {
			String readString = String.copyValueOf(inputBuffer, 0, charRead);
			str += readString;
			inputBuffer = new char[BUFFER_SIZE];
		}

		in.close();

		return str;
	}

	private InputStream OpenHttpConnection(String urlString) throws IOException {
		InputStream in = null;
		int response = -1;
		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();

		if (!(conn instanceof HttpURLConnection))
			throw new IOException("Not an HTTP connection");

		try {
			HttpURLConnection httpConn = (HttpURLConnection) conn;
			httpConn.setAllowUserInteraction(false);
			httpConn.setInstanceFollowRedirects(true);
			httpConn.setRequestMethod("GET");
			httpConn.connect();
			response = httpConn.getResponseCode();
			if (response == HttpURLConnection.HTTP_OK) {
				in = httpConn.getInputStream();
			}
		} catch (Exception ex) {
			throw new IOException("Error connecting");
		}

		return in;
	}

}
