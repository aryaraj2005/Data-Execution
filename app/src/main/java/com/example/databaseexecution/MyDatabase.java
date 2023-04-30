package com.example.databaseexecution;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {

    private static final  String DATABASE_NAME = "Contacts";
    private  static final int DATABASE_VERSION= 1;
    private  static  final  String TABLE_NAME = "tables1";
    private static  final  String KEY_ID =  "id";
    private static  final  String KEY_NAME =  "Name";
    private static  final  String KEY_PHONE =  "Phone_no";

    public MyDatabase( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
     // here we execute our sqlite
          //name + id_constrain + name + phone no

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME +
                "("+ KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_NAME + "TEXT ,"  + KEY_PHONE +"TEXT " + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
      sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
      onCreate(sqLiteDatabase);
    }
    public  void  addContact(String Name , String Phone_no){
        // Creating object
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        // for inserting the values create a new object ;
        ContentValues values = new ContentValues();
        values.put(KEY_NAME , Name);
        values.put(KEY_PHONE , Phone_no);

        sqLiteDatabase.insert(TABLE_NAME , null, values );
    }

    public ArrayList<ContactModel> fetchContact() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        //creatintg a cursor pointer
        Cursor cursor = sqLiteDatabase.rawQuery(" SELECT * FROM " + TABLE_NAME, null);
        ArrayList<ContactModel> contactModels = new ArrayList<>();
        while (cursor.moveToNext()) {
            ContactModel model = new ContactModel();
            model.id=cursor.getInt(0);
            model.Name = cursor.getString(1);
            model.phone_no = cursor.getString(2);
            contactModels.add(model);

            }
        return contactModels;
        }

       public  void update_contact(ContactModel contactModel){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv  = new ContentValues();
        cv.put(KEY_PHONE , "1234567890");
        database.update(TABLE_NAME , cv, KEY_ID+"="+contactModel.id , null);
       }
        // for delety the singular data
       public void delete_contact(int id){
        SQLiteDatabase database = getWritableDatabase();
        database.delete(TABLE_NAME ,KEY_ID+"= ?" , new String[]{String.valueOf(id)} );
       }
    }
