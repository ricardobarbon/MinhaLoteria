package com.barbon.minhaloteria.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by Barbon on 08/03/2015.
 */
public class DbDAO {
    protected static final String CATEGORIA = "BARBON-LOTERIA";

    protected SQLiteDatabase database;
    private DataBaseHelper dbHelper;
    private Context mContext;

    public DbDAO(Context context){
        this.mContext = context;
        dbHelper = DataBaseHelper.getHelper(mContext);

        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void open() throws SQLException{
        if (dbHelper == null)
            dbHelper = DataBaseHelper.getHelper(mContext);

        database = dbHelper.getWritableDatabase();
    }

    public long inserir(String tabela, ContentValues values){
        return database.insert(tabela, "", values);
    }

    public int atualizar(String tabela, ContentValues values, String where, String[] whereArgs){

        int count = database.update(tabela, values, where, whereArgs);

        return count;
    }

    public int deletar(String tabela, String where, String[] whereArgs){
        int count = database.delete(tabela, where, whereArgs);
        return count;
    }

    protected Context getmContext(){
        return mContext;
    }
}
