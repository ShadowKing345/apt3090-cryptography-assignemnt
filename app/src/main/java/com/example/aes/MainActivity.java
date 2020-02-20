package com.example.aes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonWriter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    public enum Options {
        ENCRYPT,
        DECRYPT,
        NEITHER
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void onEncryptButtonClick(View view) {
        changePageToEncrypt(Options.ENCRYPT);
    }

    public void onDecryptButtonClick(View view) {
        changePageToEncrypt(Options.DECRYPT);
    }

    private void changePageToEncrypt(Options option) {
        Intent intent = new Intent(this, EncryptDecryptActivity.class);

        JSONObject data = new JSONObject();
        try {
            data.put("choice", option);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(data.toString());
        intent.putExtra(EncryptDecryptActivity.IntentExtraName, data.toString());
        startActivity(intent);
    }

    public void onBackButtonClick(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
