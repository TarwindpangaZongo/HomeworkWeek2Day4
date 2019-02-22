package com.example.homeworkweek2day4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import static com.example.homeworkweek2day4.PersonDatabaseContract.COLUMN_ADDRSS;
import static com.example.homeworkweek2day4.PersonDatabaseContract.COLUMN_CITY;
import static com.example.homeworkweek2day4.PersonDatabaseContract.COLUMN_EMAIL;
import static com.example.homeworkweek2day4.PersonDatabaseContract.COLUMN_ID;
import static com.example.homeworkweek2day4.PersonDatabaseContract.COLUMN_NAME;
import static com.example.homeworkweek2day4.PersonDatabaseContract.COLUMN_PHONE;
import static com.example.homeworkweek2day4.PersonDatabaseContract.COLUMN_STATE;
import static com.example.homeworkweek2day4.PersonDatabaseContract.COLUMN_ZIP;
import static com.example.homeworkweek2day4.PersonDatabaseContract.DATABASE_NAME;
import static com.example.homeworkweek2day4.PersonDatabaseContract.DATABASE_VERSION;
import static com.example.homeworkweek2day4.PersonDatabaseContract.TABLE_NAME;

public class PersonDatabaseHelper extends SQLiteOpenHelper {

    //Constructor for
    public PersonDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //Create the tables(will run only once per install)
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(PersonDatabaseContract.createQuery());
    }
    //If version database changes, make adjustments here
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        onCreate(database);
    }

    //Insert a Person into the database
    public long insertPersonIntoDatabase(@NonNull Person person) {
        SQLiteDatabase writeableDatabase = this.getWritableDatabase();
        //Data container used for database key value pairs
        ContentValues contentValues = new ContentValues();

        //inset key value pairs into the contentValues container
        contentValues.put(COLUMN_NAME, person.getName());
        contentValues.put(COLUMN_ADDRSS, person.getAddress());
        contentValues.put(COLUMN_CITY, person.getCity());
        contentValues.put(COLUMN_STATE, person.getState());
        contentValues.put(COLUMN_ZIP, person.getZip());
        contentValues.put(COLUMN_PHONE, person.getPhone());
        contentValues.put(COLUMN_EMAIL, person.getEmail());

        //insert the Person into the table using contentValues
        return writeableDatabase.insert(TABLE_NAME, null, contentValues);
    }


    //Get All people from Database and return an ArrayList
    public ArrayList<Person> getAllPeopleFromDatabase() {
        ArrayList<Person> returnPersonList = new ArrayList<>();
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        //Get results from query and hold in cursor(iterable object for database operations
        Cursor cursor = readableDatabase.rawQuery(PersonDatabaseContract.getAllPeopleQuery(), null);
        //Check to see if we have any results
        if(cursor.moveToFirst()) {
            //while we have results, get the values and place in list
            do {
                //get values
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRSS));
                String city = cursor.getString(cursor.getColumnIndex(COLUMN_CITY));
                String state = cursor.getString(cursor.getColumnIndex(COLUMN_STATE));
                String zip = cursor.getString(cursor.getColumnIndex(COLUMN_ZIP));
                String phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE));
                String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));

                //add to list
                returnPersonList.add(new Person(name, address, city, state, zip,phone,email, id));
            } while (cursor.moveToNext());
            //return the result in a list
        }
        cursor.close();
        return returnPersonList;
    }

    //Get One entry from database
    public Person getPersonById(int id) {
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        //Person to return
        Person returnPerson = new Person();
        //cursor to hold results
        Cursor cursor = readableDatabase.rawQuery(PersonDatabaseContract.getOnePersonById(id), null);
        if(cursor.moveToFirst()){
            int idFromDB = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRSS));
            String city = cursor.getString(cursor.getColumnIndex(COLUMN_CITY));
            String state = cursor.getString(cursor.getColumnIndex(COLUMN_STATE));
            String zip = cursor.getString(cursor.getColumnIndex(COLUMN_ZIP));
            String phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE));
            String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
            //set return Person
            returnPerson = new Person(name, address, city, state, zip,phone,email, idFromDB);
        }
        cursor.close();
        return returnPerson;
    }

}
