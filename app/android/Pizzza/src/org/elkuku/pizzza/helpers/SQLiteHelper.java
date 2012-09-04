package org.elkuku.pizzza.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {

	private static final String DATABASE = "pizzza.db";

	private static final String TABLE_MENU = "menu";
	private static final String TABLE_PREFS = "prefs";
	private static final String TABLE_CONTACT = "contact";
	private static final String TABLE_PROMOS = "promos";
	private static final String TABLE_NEWS = "news";

	private static final int DB_VERSION = 12;

	private static final String CREATE_TABLE_MENU =
			"CREATE TABLE " + TABLE_MENU
			+ " ("
			+ "id INTEGER PRIMARY KEY"
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
			+ "id INTEGER PRIMARY KEY"
			+ ", key TEXT"
			+ ", value TEXT"
			+ ");";

	private static final String CREATE_TABLE_CONTACT =
			"CREATE TABLE IF NOT EXISTS " + TABLE_CONTACT
			+ " ("
			+ "id INTEGER PRIMARY KEY"
			+ ", name TEXT"
			+ ", address TEXT"
			+ ", city TEXT"
			+ ", phone1 TEXT"
			+ ", phone2 TEXT"
			+ ", phone3 TEXT"
			+ ", email TEXT"
			+ ", webpage TEXT"
			+ ", misc TEXT"
			+ ");";

	private static final String CREATE_TABLE_PROMOS =
			"CREATE TABLE IF NOT EXISTS " + TABLE_PROMOS
			+ " ("
			+ "id INTEGER PRIMARY KEY"
			+ ", name TEXT"
			+ ", day TEXT"
			+ ");";

	private static final String CREATE_TABLE_NEWS =
			"CREATE TABLE IF NOT EXISTS " + TABLE_NEWS
			+ " ("
			+ "id INTEGER PRIMARY KEY"
			+ ", title TEXT"
			+ ", text TEXT"
			+ ", date TEXT"
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
		database.execSQL(CREATE_TABLE_CONTACT);
		database.execSQL(CREATE_TABLE_PROMOS);
		database.execSQL(CREATE_TABLE_NEWS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

		Log.i(SQLiteHelper.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");

		database.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_PROMOS);
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_NEWS);

		onCreate(database);
	}

	public void resetDb() {
		SQLiteDatabase database = getWritableDatabase();

		database.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);
		database.execSQL(CREATE_TABLE_MENU);

		database.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);
		database.execSQL(CREATE_TABLE_CONTACT);

		database.execSQL("DROP TABLE IF EXISTS " + TABLE_PROMOS);
		database.execSQL(CREATE_TABLE_PROMOS);

		database.execSQL("DROP TABLE IF EXISTS " + TABLE_NEWS);
		database.execSQL(CREATE_TABLE_NEWS);
	}

}
