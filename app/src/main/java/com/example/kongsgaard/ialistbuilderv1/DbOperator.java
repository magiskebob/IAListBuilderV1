package com.example.kongsgaard.ialistbuilderv1;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kongsgaard on 05-02-2017.
 */

public class DbOperator extends SQLiteOpenHelper {
    public static final String DBNAME = "ailistbuilderdatabase.db";
    public static final String DBLOCATION = "/data/data/com.example.kongsgaard.ialistbuilderv1/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;



    public DbOperator(Context context){
        super(context,DBNAME, null, 1);
        this.mContext = context;
    }

    public void createDatabase() throws IOException {
        boolean dbExist = checkDatabase();
        if (dbExist) {
        } else {
            this.getReadableDatabase();
            try {
                copyDatabase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }
    private boolean checkDatabase(){
        SQLiteDatabase checkDB = null;
        try{
            String myPath = DBLOCATION + DBNAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }catch (SQLiteException e){

        }
        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null? true:false;
    }

    private void copyDatabase() throws IOException{
        // open your local db as the input stream
        InputStream myInput = mContext.getAssets().open(DBNAME);
        //path to hte just created empty db
        String outFileName = DBLOCATION + DBNAME;
        //open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);
        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDatabase() throws SQLException{
        //Open the database
        String myPath = DBLOCATION + DBNAME;
        mDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }
    @Override
    public synchronized void close(){
        if (mDatabase != null)
            mDatabase.close();
        super.close();
    }

    public List<CardClass> getRebelList(){
        CardClass card = null;
        List<CardClass> cardList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM REBELCARDS", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            card = new CardClass(cursor.getString(1),cursor.getInt(2),Integer.parseInt(cursor.getString(3)));
            cardList.add(card);
            cursor.moveToNext();
        }
        cursor.close();
        close();
        return cardList;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
