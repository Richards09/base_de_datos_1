package com.example.suazo.base_datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

  // Creamos el constructor
  public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
      super(context, name, factory, version);
  }

  // Se crea la tabla
  @Override
  public void onCreate(SQLiteDatabase db) {
      db.execSQL("create table futbol (id integer primary key unique, nombre text unique, jj integer, jg integer, jp integer, pts integer) ");
  }

  // borrar la tabla y crear la nueva tabla

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("drop table if exists futbol");
      db.execSQL("create table futbol (id integer primary key unique, nombre text unique, jj integer, jg integer, jp integer, pts integer) ");
  }
}
