package com.thienthai.alertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<String> arrayList;
    ArrayAdapter arrayAdapter;
    Button btnAdd, btnEdit;
    EditText edt;
    int vitri = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        edt = (EditText) findViewById(R.id.edt);
        lv = (ListView) findViewById(R.id.lvDanhsach);
        arrayList = new ArrayList<>();
        Arraylist();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        lv.setAdapter(arrayAdapter);

//      add
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String duLieu = edt.getText().toString().trim();
                arrayList.add(duLieu);
                arrayAdapter.notifyDataSetChanged();
                edt.getText().clear();
            }
        });
//        tìm vị trí trong mãng
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                edt.setText(arrayList.get(position));
                vitri = position;
            }
        });
//        edit
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edt.getText().toString().trim())){
                    Toast.makeText(MainActivity.this, "không dk bỏ trống", Toast.LENGTH_SHORT).show();
                }else {
                    arrayList.set(vitri, edt.getText().toString());
                    arrayAdapter.notifyDataSetChanged();
                }
            }
        });
//      delete
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                XacNhanXoa(position);
                edt.getText().clear();
                return false;
            }
        });
    }

//    hàm xác nhận xóa Alert
    private void XacNhanXoa(int vitri){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Thông Báo");
        alertDialog.setIcon(R.mipmap.ic_launcher);
        alertDialog.setMessage("Bạn có chắc muốn xóa không");
        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                edt.getText().clear();
                arrayList.remove(vitri);
                arrayAdapter.notifyDataSetChanged();
            }
        });
        alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }
//    hàm ánh xạ
    private void Arraylist(){
        arrayList.add("php");
        arrayList.add("Html");
        arrayList.add("Html1");
        arrayList.add("Html2");
        arrayList.add("Html3");
    }
}