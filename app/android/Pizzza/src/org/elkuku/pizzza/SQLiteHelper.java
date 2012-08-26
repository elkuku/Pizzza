package org.elkuku.pizzza;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {

	private static final String DATABASE = "pizzza.db";

	private static final String TABLE_MENU = "menu";
	private static final String TABLE_PREFS = "prefs";

	private static final int DB_VERSION = 11;

	private static final String CREATE_TABLE_MENU =
			"CREATE TABLE " + TABLE_MENU
			+ " ("
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT"
			+ ", catid INTEGER"
			+ ", title TEXT"
			+ ", description TEXT"
			+ ", price_peq REAL"
			+ ", price_med REAL"
			+ ", price_gra REAL"
			+ ", favorite INTEGER"
			+ ");";

	private static final String CREATE_TABLE_PREFS =
			"CREATE TABLE IF NOT EXISTS " + TABLE_PREFS
			+ " ("
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT"
			+ ", key TEXT"
			+ ", value TEXT"
			+ ");";

	public SQLiteHelper(Context context) {
		super(context, DATABASE, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		Log.i("DBDEBUG", "SQLiteHelper onCreate: \n"
			+ CREATE_TABLE_MENU
			+"\n"+ CREATE_TABLE_PREFS);

		database.execSQL(CREATE_TABLE_MENU);
		database.execSQL(CREATE_TABLE_PREFS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {

		Log.i(SQLiteHelper.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");

		database.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);

		onCreate(database);
	}

	public void resetDb() {
		SQLiteDatabase database = getWritableDatabase();

		database.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);
		database.execSQL(CREATE_TABLE_MENU);
	}

}
