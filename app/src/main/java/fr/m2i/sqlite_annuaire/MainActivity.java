package fr.m2i.sqlite_annuaire;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    EditText etSearch, etId, etName, etTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSearch = findViewById(R.id.txtSearch);
        etId = findViewById(R.id.txtId);
        etName = findViewById(R.id.txtName);
        etTel = findViewById(R.id.txtTel);

        //ouverture de la base de donnée
        DbInit dbInit = new DbInit(this);
        db = dbInit.getWritableDatabase();

        /* Principales méthodes de l'objet SQLiteDatabase:
                ExecSQL()   pour les SQL de mise à jour
                query()     pour les SELECT
                update()
                insert()
                delete()

                // execution en bloc
                beginTransaction()
                endTransaction()
         */
    }

    public void search(View v) {

        String nom = etSearch.getText().toString();

        String columns[] = {"id", "name", "tel"};
        String where = "name" + " = '" + nom + "'";
        Cursor cursor = db.query("contacts", columns, where, null, null, null, null);
        cursor.moveToFirst();
        String id = cursor.getString(0);
        String name = cursor.getString(1);
        String tel = cursor.getString(2);
        etId.setText(id);
        etName.setText(name);
        etTel.setText(tel);

        //Toast.makeText(this,name,Toast.LENGTH_LONG).show();

    }

    public void clear(View v) {

        etSearch.setText("");
        etId.setText("");
        etName.setText("");
        etTel.setText("");

    }

    public void save(View v) {

        String nom = etName.getText().toString();
        String tel = etTel.getText().toString();
        ContentValues value = new ContentValues();
        value.put("name", nom);
        value.put("tel", tel);
        insert(value);

    }

    public void insert(ContentValues values) {

        db.insert("contacts", null, values);
        Toast.makeText(this, "enregistrement ok", Toast.LENGTH_LONG).show();

    }

    public void update(ContentValues values, String id) {

    }

    public void delete(View v) {
    }

}
