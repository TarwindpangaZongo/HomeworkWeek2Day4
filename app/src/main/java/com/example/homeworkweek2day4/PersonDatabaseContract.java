package com.example.homeworkweek2day4;

import android.util.Log;

public class PersonDatabaseContract {
    //Database name and default version
    public static final String DATABASE_NAME = "person_db";
    public static final int DATABASE_VERSION = 1;
    //Database table name
    public static final String TABLE_NAME = "People";
    //Columns in database names
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ADDRSS = "address";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_STATE = "state";
    public static final String COLUMN_ZIP = "zip";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";

    public static final String COLUMN_ID = "id";

    //
    // Create the create table query for the database
    //
    public static String createQuery() {
        StringBuilder queryBuilder = new StringBuilder();
        //Query to create Table
        queryBuilder.append("CREATE TABLE ");
        queryBuilder.append(TABLE_NAME);
        queryBuilder.append(" ( ");
        //Must have unique primary key
        queryBuilder.append(COLUMN_ID);
        queryBuilder.append(" ");
        queryBuilder.append(" INT NONNULL IDENTITY PRIMARY KEY, ");
        //Add rest of the columns
        queryBuilder.append(COLUMN_NAME);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_ADDRSS);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_CITY);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_STATE);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_ZIP);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_PHONE);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_EMAIL);
        queryBuilder.append(" TEXT )");

        //Log the query so we can check for correctness
        Log.d("TAG", "createQuery: " + queryBuilder.toString());

        return queryBuilder.toString();
    }
    public static String getAllPeopleQuery() {
        return "SELECT * FROM " + TABLE_NAME;
    }

    public static String getOnePersonById(int id) {
        return String.format("SELECT * FROM %s WHERE %s = \"%d\"", TABLE_NAME, COLUMN_ID, id);
    }
}
