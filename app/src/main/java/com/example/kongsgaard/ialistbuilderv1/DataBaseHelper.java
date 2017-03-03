package com.example.kongsgaard.ialistbuilderv1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by magiskebob on 06-02-2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/com.example.kongsgaard.ialistbuilderv1/databases/";

    private static String DB_NAME = "ailistbuilderdatabase.db";

    private SQLiteDatabase myDataBase;

    private final Context myContext;
    public static final String ARMYLISTS_TABLE_NAME = "ArmyLists";
    public static final String ARMYLISTS_COLUMN_LISTNAME = "ListName";
    public static final String ARMYLISTS_COLUMN_LISTCOST = "ListCost";

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    public DataBaseHelper(Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if(dbExist){
            //do nothing - database already exist
        }else{

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){

            //database does't exist yet.

        }

        if(checkDB != null){

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
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

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if(myDataBase != null)
            myDataBase.close();

        super.close();

    }

    public List<CardClass> getRebelList(){
        CardClass card = null;
        boolean elite;
        List<CardClass> cardList = new ArrayList<>();
        openDataBase();
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM RebelCards", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
 //           elite = cursor.getInt(4)>0;
            card = new CardClass(cursor.getString(1),cursor.getInt(2), myContext.getResources().getIdentifier("com.example.kongsgaard.ialistbuilderv1:drawable/"+cursor.getString(3), null, null),cursor.getInt(4)>0);
            cardList.add(card);
            cursor.moveToNext();
        }
        cursor.close();
        close();
        return cardList;
    }
    public List<CardClass> getEmpireList(){
        CardClass card = null;
        List<CardClass> cardList = new ArrayList<>();
        openDataBase();
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM EmpireCards", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            card = new CardClass(cursor.getString(1),cursor.getInt(2), myContext.getResources().getIdentifier("com.example.kongsgaard.ialistbuilderv1:drawable/"+cursor.getString(3), null, null),(cursor.getInt(4)>0));
            cardList.add(card);
            cursor.moveToNext();
        }
        cursor.close();
        close();
        return cardList;
    }
    public List<CardClass> getScumList(){
        CardClass card = null;
        List<CardClass> cardList = new ArrayList<>();
        openDataBase();
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM ScumCards", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            card = new CardClass(cursor.getString(1),cursor.getInt(2), myContext.getResources().getIdentifier("com.example.kongsgaard.ialistbuilderv1:drawable/"+cursor.getString(3), null, null),(cursor.getInt(4)>0));
            cardList.add(card);
            cursor.moveToNext();
        }
        cursor.close();
        close();
        return cardList;
    }
    public List<ArmyList> getArmyLists(){
        int tempCardId = 0;
        ArmyList tempArmy = null;
        CardClass tempcard = null;
        List<ArmyList>templist = new ArrayList<>();
        List<CardClass>armycards = new ArrayList<>();
        openDataBase();
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM ArmyLists", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            tempArmy = new ArmyList(new List<CardClass>() {
                @Override
                public int size() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public boolean contains(Object o) {
                    return false;
                }

                @NonNull
                @Override
                public Iterator<CardClass> iterator() {
                    return null;
                }

                @NonNull
                @Override
                public Object[] toArray() {
                    return new Object[0];
                }

                @NonNull
                @Override
                public <T> T[] toArray(T[] a) {
                    return null;
                }

                @Override
                public boolean add(CardClass cardClass) {
                    return false;
                }

                @Override
                public boolean remove(Object o) {
                    return false;
                }

                @Override
                public boolean containsAll(Collection<?> c) {
                    return false;
                }

                @Override
                public boolean addAll(Collection<? extends CardClass> c) {
                    return false;
                }

                @Override
                public boolean addAll(int index, Collection<? extends CardClass> c) {
                    return false;
                }

                @Override
                public boolean removeAll(Collection<?> c) {
                    return false;
                }

                @Override
                public boolean retainAll(Collection<?> c) {
                    return false;
                }

                @Override
                public void clear() {

                }

                @Override
                public CardClass get(int index) {
                    return null;
                }

                @Override
                public CardClass set(int index, CardClass element) {
                    return null;
                }

                @Override
                public void add(int index, CardClass element) {

                }

                @Override
                public CardClass remove(int index) {
                    return null;
                }

                @Override
                public int indexOf(Object o) {
                    return 0;
                }

                @Override
                public int lastIndexOf(Object o) {
                    return 0;
                }

                @Override
                public ListIterator<CardClass> listIterator() {
                    return null;
                }

                @NonNull
                @Override
                public ListIterator<CardClass> listIterator(int index) {
                    return null;
                }

                @NonNull
                @Override
                public List<CardClass> subList(int fromIndex, int toIndex) {
                    return null;
                }
            },cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
            Cursor cursor2 = myDataBase.rawQuery("SELECT CardID FROM ArmyContent WHERE ArmyID = "+tempArmy._id, null);
            cursor2.moveToFirst();
            while(!cursor2.isAfterLast()) {
                tempCardId = cursor2.getInt(0);
                Cursor cardCursor = myDataBase.rawQuery("SELECT * FROM RebelCards WHERE _id = "+tempCardId,null);
                cardCursor.moveToFirst();
                tempcard = new CardClass(cardCursor.getInt(0),cardCursor.getString(1),cardCursor.getInt(2), myContext.getResources().getIdentifier("com.example.kongsgaard.ialistbuilderv1:drawable/"+cardCursor.getString(3), null, null),(cardCursor.getInt(4)>0));
                armycards.add(tempcard);
                cursor2.moveToNext();
            }
            for (CardClass card:armycards
                 ) {tempArmy.armylist.add(card);

            }
            templist.add(tempArmy);
            armycards.clear();
            cursor.moveToNext();
        }
        cursor.close();
        close();
        return templist;
    }
    public void addArmyList(ArmyList list){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ARMYLISTS_COLUMN_LISTNAME,list.Name);
        contentValues.put(ARMYLISTS_COLUMN_LISTCOST, list.TotalCost);
        db.insert(ARMYLISTS_TABLE_NAME, null, contentValues);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Add your public helper methods to access and get content from the database.
    // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
    // to you to create adapters for your views.
}
