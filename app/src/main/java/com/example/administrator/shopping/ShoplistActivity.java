package com.example.administrator.shopping;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.shopping.adapter.ShopAdapter;
import com.example.administrator.shopping.bean.ShopBean;
import com.example.administrator.shopping.database.SQLiteHelper;

import java.util.List;


public class ShoplistActivity extends AppCompatActivity {
    ListView listView;
    List<ShopBean> list;
    SQLiteHelper mSQLiteHelper;
    ShopAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoplist);
        listView=(ListView) findViewById(R.id.listview);
        ImageView back=(ImageView) findViewById(R.id.go_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShoplistActivity.this,ShopActivity.class);
                startActivityForResult(intent,1);
            }
        });
        initData();
    }
    protected void initData(){
        mSQLiteHelper=new SQLiteHelper(this);
        showQueryData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShopBean notepadBean=list.get(position);
                Intent intent=new Intent(ShoplistActivity.this,ShopActivity.class);
                intent.putExtra("id",notepadBean.getId());
                intent.putExtra("title",notepadBean.getShoppingTitle());
                intent.putExtra("price",notepadBean.getShoppingPrice());
                ShoplistActivity.this.startActivityForResult(intent,1);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog dialog;
                AlertDialog.Builder builder=new AlertDialog.Builder(ShoplistActivity.this).setMessage("是否删除此记录?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ShopBean shoppingBean = list.get(position);
                                if (mSQLiteHelper.deleteData(shoppingBean.getId())) {
                                    list.remove(position);
                                    adapter.notifyDataSetChanged();
                                    Toast.makeText(ShoplistActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                dialog=builder.create();
                dialog.show();
                return true;
            }
        });
    }
    private void showQueryData(){
        if (list!=null){
            list.clear();
        }
        list=mSQLiteHelper.query();
        adapter=new ShopAdapter(this , list);
        listView.setAdapter(adapter);
    }
    @Override
    protected  void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1 && resultCode==2){
            showQueryData();
        }
    }
}
