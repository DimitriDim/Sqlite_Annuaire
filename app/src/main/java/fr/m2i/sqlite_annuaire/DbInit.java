package fr.m2i.sqlite_annuaire;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrateur on 16/01/2018.
 */

public class DbInit extends SQLiteOpenHelper {

    public DbInit(Context ctx, String dbName, SQLiteDatabase.CursorFactory factory, int version) {
        super(ctx, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE contacts (" +
                " id INTEGER PRIMARY KEY NOT NULL" +
                ", name TEXT NOT NULL" +
                ", tel TEXT" +
                ")";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
