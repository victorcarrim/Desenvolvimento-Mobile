package com.example.trabalhofinal.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "seriesDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "series";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_GENRE = "genre";
    private static final String KEY_SEASONS = "seasons";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_GENRE + " TEXT," + KEY_SEASONS + " INTEGER,"
                + "'')";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addSerie(Serie serie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, serie.getNome());
        values.put(KEY_GENRE, serie.getGenero());
        values.put(KEY_SEASONS, serie.getQuantidadeTemporadas());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    public List<Serie> getAllSeries() {
        List<Serie> seriesList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Serie serie = new Serie();
                serie.setId(cursor.getInt(0));
                serie.setNome(cursor.getString(1));
                serie.setGenero(cursor.getString(2));
                serie.setQuantidadeTemporadas(cursor.getInt(3));
                seriesList.add(serie);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return seriesList;
    }

    public void updateSerie(Serie serie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, serie.getNome());
        values.put(KEY_GENRE, serie.getGenero());
        values.put(KEY_SEASONS, serie.getQuantidadeTemporadas());

        db.update(TABLE_NAME, values, KEY_ID + "=?", new String[] {String.valueOf(serie.getId())});
        db.close();
    }

    public void deleteSerie(Serie serie) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + "=?", new String[]{String.valueOf(serie.getId())});
        db.close();
    }

}
