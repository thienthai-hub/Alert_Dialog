package com.thienthai.alertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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
//        custom dialog
        TextView textViewbtn;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            btnAdd = (Button) findViewById(R.id.btnAdd);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        edt = (EditText) findViewById(R.id.edt);
        lv = (ListView) findViewById(R.id.lvDanhsach);
        textViewbtn = (TextView) findViewById(R.id.textViewLogin);
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
    //customdialog
            textViewbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customDialog();
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
        //        phài show() thì nó mới hiện ra
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

//    custom dialog
        private void customDialog(){
            Dialog dialog = new Dialog(this);

            //không cho show title requestWindowFeature có san trong máy
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

            dialog.setContentView(R.layout.alert_dialog_custom);

    //        set khi chạm màng hình bên ngoài kok bị hủy setCanceledOnTouchOutside
            dialog.setCanceledOnTouchOutside(false);

    //        Anhxa
            EditText edtusername = (EditText) dialog.findViewById(R.id.adtName);
            EditText edtPassWord =  (EditText) dialog.findViewById(R.id.edtPassword);
            Button btnAcess = (Button) dialog.findViewById(R.id.btnAcess);
            Button btnCancel = (Button) dialog.findViewById(R.id.btnHuy);

            btnAcess.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = edtusername.getText().toString().trim();
                    String password = edtPassWord.getText().toString().trim();
                    if(name.equals("ThienThai") && password.equals("123456")){
                        Toast.makeText(MainActivity.this, "Sucess", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                }
            });
            dialog.show();
    }
}