package hu.uni.miskolc.transport;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    // konstans változók létrehozása az adatbázishoz.
    // ez a változó az adatbázis nevére vonatkozik.
    private static final String DB_NAME = "tourdb";

    // ez az "int" változó az adatbázis verziója
    private static final int DB_VERSION = 1;

    // ez a változó a táblázatunk nevére vonatkozik.
    private static final String TABLE_NAME = "mytours";

    // ez a változó az id oszlopra vonatkozik.
    private static final String ID_COL = "id";

    // ez a változó a túranév oszlopra vonatkozik
    private static final String NAME_COL = "name";

    // ez a változó a túra hossza oszlopra vonatkozik.
    private static final String LENGTH_COL = "duration";

    // ez a változó a túraleírás oszlophoz vonatkozik.
    private static final String DESCRIPTION_COL = "description";

    // ez a változó a túraszámok oszlopra vonatkozik.
    private static final String TRACKS_COL = "tracks";

    // konstruktor létrehozása adatbázis-kezelőhöz.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // az adatbázis létrehozása, sqlite lekérdezés futtatásával
    @Override
    public void onCreate(SQLiteDatabase db) {
        // az alábbi sorban hozunk létre
        // egy sqlite lekérdezés és az
        // oszlopneveink beállítása
        // adattípusaikkal együtt.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + LENGTH_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT,"
                + TRACKS_COL + " TEXT)";

        // végre meghívunk egy exec sql-t
        // metódus az sql lekérdezés feletti végrehajtásához
        db.execSQL(query);
    }

    // új túra hozzáadása az sqlite adatbázisunkhoz.
    public void addNewTour(String tourName, String tourLength, String tourDescription, String tourTracks) {

        // az alábbi sorban egy változót hozunk létre
        // az sqlite adatbázisunk és az írható metódus meghívására
        // mivel adatokat írunk az adatbázisunkba.
        SQLiteDatabase db = this.getWritableDatabase();

        // az alábbi sorban létrehozzunk egy
        // változót a tartalmi értékekhez.
        ContentValues values = new ContentValues();

        // az alábbi sorban minden értéket átadunk
        // kulcs- és értékpárjával együtt.
        values.put(NAME_COL, tourName);
        values.put(LENGTH_COL, tourLength);
        values.put(DESCRIPTION_COL, tourDescription);
        values.put(TRACKS_COL, tourTracks);

        // az összes érték hozzáadása után továbbadunk
        // tartalomértékek a táblázatunkhoz.
        db.insert(TABLE_NAME, null, values);

        // lezárjuk
        // az adatbázis hozzáadása után.
        db.close();
    }

    // Létrehozunk egy új metódust az összes "túra" olvasásához.
    public ArrayList<TourModal> readTours() {
        // az alábbi sorban létrehozzuk a
        // adatbázis adatbázisunk olvasásához.
        SQLiteDatabase db = this.getReadableDatabase();

        // létrehozunk egy kurzort, lekérdezéssel, az adatbázisból való adatok olvasásához.
        Cursor cursorTours = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // egy új tömblistát hozunk létre.
        ArrayList<TourModal> tourModalArrayList = new ArrayList<>();

        // kurzor mozgatása az első pozícióba.
        if (cursorTours.moveToFirst()) {
            do {
                // hozzáadjuk a kurzor adatait a tömblistánkhoz.
                tourModalArrayList.add(new TourModal(cursorTours.getString(1),
                        cursorTours.getString(4),
                        cursorTours.getString(2),
                        cursorTours.getString(3)));
            } while (cursorTours.moveToNext());
            // mozgassa a kurzort a következőre.
        }
        // bezárjuk a kurzort
        // és visszaadjuk a tömblistát.
        cursorTours.close();
        return tourModalArrayList;
    }

    // a "túrák" frissítése(update):
    public void updateTour(String originalTourName, String tourName, String tourDescription,
                             String tourTracks, String tourLength) {

        // metódus meghívása írható adatbázis-hoz.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // minden értéket átadunk
        // kulcs- és értékpárjával együtt.
        values.put(NAME_COL, tourName);
        values.put(LENGTH_COL, tourLength);
        values.put(DESCRIPTION_COL, tourDescription);
        values.put(TRACKS_COL, tourTracks);

        // az alábbi sorban egy frissítési metódust hívunk meg adatbázisunk frissítésére és az értékek átadására.
        // és összehasonlítjuk a túránk nevével, amely az eredeti névváltozóban van tárolva.
        db.update(TABLE_NAME, values, "name=?", new String[]{originalTourName});
        db.close();
    }


    // alább a "túrák" törlése.
    public void deleteTour(String tourName) {

        // az alábbi sorban létrehozunk
        // egy változót az adatbázisunk írásához.
        SQLiteDatabase db = this.getWritableDatabase();

        // az alábbi sorban egy metódust hívunk meg az adatok törlésére
        // összehasonlítjuk a "túra" nevével.
        db.delete(TABLE_NAME, "name=?", new String[]{tourName});
        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // annak ellenőrzése, hogy létezik-e már a tábla.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}

