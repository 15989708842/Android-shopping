package com.example.administrator.shopping.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.administrator.shopping.bean.ShopBean;
import com.example.administrator.shopping.utils.DBUtils;

import java.util.ArrayList;
import java.util.List;


public class SQLiteHelper extends SQLiteOpenHelper {
    private SQLiteDatabase sqLiteDatabase;
    public SQLiteHelper(Context context){
        super( context , DBUtils.DATABASE_NAME ,null , DBUtils.DATABASE_VERION );
        sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table "+ DBUtils.DATABASE_TABLE + "(" + DBUtils.SHOPPING_ID +
                " integer primary key autoincrement,"+ DBUtils.SHOPPING_TITLE+
                " text ," + DBUtils.SHOPPING_PRICE + " text , img int(30)  ) " );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

    public boolean insertData(String title,String price,int img){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBUtils.SHOPPING_TITLE,title);
        contentValues.put(DBUtils.SHOPPING_PRICE,price);
        contentValues.put("img",img);
        return sqLiteDatabase.insert(DBUtils.DATABASE_TABLE,null,contentValues)>0;
    }
    public boolean deleteData(String id){
        String sql =DBUtils.SHOPPING_ID + " =? ";
        String[] contentValuesArray=new String[] {String.valueOf(id)};
        return sqLiteDatabase.delete(DBUtils.DATABASE_TABLE,sql,contentValuesArray)>0;
    }
    public List<ShopBean> query(){
        List<ShopBean> list = new ArrayList<ShopBean>();
        Cursor cursor = sqLiteDatabase.query( DBUtils.DATABASE_TABLE ,null ,null ,null ,null ,null ,DBUtils.SHOPPING_ID + " desc " );
        if(cursor != null){
            while(cursor.moveToNext()){
                ShopBean shopInfo = new ShopBean();
                String id = String.valueOf(cursor.getInt(cursor.getColumnIndex( DBUtils.SHOPPING_ID )));
                String title = cursor.getString(cursor.getColumnIndex( DBUtils.SHOPPING_TITLE));
                String price = cursor.getString(cursor.getColumnIndex( DBUtils.SHOPPING_PRICE ));
                int img = cursor.getInt(cursor.getColumnIndex( "img" ));
                shopInfo.setId(id);
                shopInfo.setShoppingTitle(title);
                shopInfo.setShoppingPrice(price);
                shopInfo.setImg(img);
                list.add(shopInfo);
            }
            cursor.close();
        }
        return list;
    }
}

