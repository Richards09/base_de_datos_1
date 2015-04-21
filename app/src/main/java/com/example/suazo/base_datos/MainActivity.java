package com.example.suazo.base_datos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    EditText id, nombre, jj, jg,jp, pts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = (EditText) findViewById(R.id.e_id);
        nombre = (EditText) findViewById(R.id.e_nombre);
        jj = (EditText) findViewById(R.id.e_jj);
        jg = (EditText) findViewById(R.id.e_jg);
        jp = (EditText) findViewById(R.id.e_jp);
        pts = (EditText) findViewById(R.id.e_pts);
    }

    public void alta (View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "futbol", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String e_id = id.getText().toString();
        String e_nombre = nombre.getText().toString();
        String e_jj = jj.getText().toString();
        String e_jg = jg.getText().toString();
        String e_jp = jp.getText().toString();
        String e_pts = pts.getText().toString();


        ContentValues registro = new ContentValues();

        registro.put("id", e_id);
        registro.put("nombre", e_nombre);
        registro.put("jj", e_jj);
        registro.put("jg", e_jg);
        registro.put("jp", e_jp);
        registro.put("pts", e_pts);

        bd.insert("futbol", null, registro);
        bd.close();

        id.setText("");
        nombre.setText("");
        jj.setText("");
        jg.setText("");
        jp.setText("");
        pts.setText("");

        Toast.makeText(this,"Se agrego un nuevo usuario",Toast.LENGTH_SHORT).show();
    }

    public void consulta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "futbol", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String id_e = id.getText().toString();
        Cursor fila = bd.rawQuery("select id, nombre, jj, jg, jp, pts from futbol where id=" + id_e, null);
        if (fila.moveToFirst()) {
            id.setText(fila.getString(0));
            nombre.setText(fila.getString(1));
            jj.setText(fila.getString(2));
            jg.setText(fila.getString(3));
            jp.setText(fila.getString(4));
            pts.setText(fila.getString(5));
        } else {
            Toast.makeText(this,"No existe el usuario",Toast.LENGTH_SHORT).show();
        }
        bd.close();
    }

    public void baja(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "futbol", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String id_e = id.getText().toString();
        int cant = bd.delete("futbol","id=" + id_e, null);
        bd.close();

        id.setText("");
        nombre.setText("");
        jj.setText("");
        jg.setText("");
        jp.setText("");
        pts.setText("");

        if (cant == 1) {
            Toast.makeText(this, "Se borró el usuario",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe el usuario",Toast.LENGTH_SHORT).show();
        }
    }

    public void modificacion (View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "futbol", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String e_id = id.getText().toString();
        String e_nombre = nombre.getText().toString();
        String e_jj = jj.getText().toString();
        String e_jg = jg.getText().toString();
        String e_jp = jp.getText().toString();
        String e_pts = pts.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("id", e_id);
        registro.put("nombre", e_nombre);
        registro.put("jj", e_jj);
        registro.put("jg", e_jg);
        registro.put("jp", e_jp);
        registro.put("pts", e_pts);

        int cant = bd.update("futbol", registro, "id=" + e_id, null);
        bd.close();

        if (cant == 1) {
            Toast.makeText(this, "Se modificaron los datos del usuario",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe el usuario",Toast.LENGTH_SHORT).show();
        }
    }

    public void limpia (View v){
        id.setText("");
        nombre.setText("");
        jj.setText("");
        jg.setText("");
        jp.setText("");
        pts.setText("");
    }
}
