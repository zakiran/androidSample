package com.example.katsuya.namescoreapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MyForm extends Activity {

    public final static String EXTRA_MYNAME = "com.example.katsuya.namescoreapp.MYNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_form);
    }

    public void getScore(View view){
        EditText myEditText = (EditText) findViewById(R.id.myEditText);


        String myName = myEditText.getText().toString().trim(); //getTestの返り値はEditable型

        if(myName.equals("")){
            //エラー処理(3パターン)

            // ①.setError
            myEditText.setError("名前を入力してください");

            /* ②.Toast
            Toast.makeText(
                    this,
                    "名前を入力してください",
                    Toast.LENGTH_LONG
            ).show();
            */

            /* ③.Dialog
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Error!").setMessage("名前を入力してください").setPositiveButton("OK", null);
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            */

        }else{
            //次の画面へ(Intent)
            Intent intent = new Intent(this, MyResult.class);
            intent.putExtra(EXTRA_MYNAME, myName);
            startActivity(intent);
        }
    }

}
