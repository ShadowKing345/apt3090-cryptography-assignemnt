package com.example.aes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class EncryptDecryptActivity extends AppCompatActivity {
    public static final String IntentExtraName = "com.aturcanu.AES.ENCRYPTDECRYPTDATA";

    public MainActivity.Options choice;

    private ConstraintLayout page;
    private TextView headerTextView;
    private TextView subTextView;
    private EditText input;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encrypt_decrypt_page);

        page = findViewById(R.id.encrypt_decrypt_page);
        headerTextView = findViewById(R.id.encrypt_decrypt_page_header);
        subTextView = findViewById(R.id.encrypt_decrypt_page_subtext);
        input = findViewById(R.id.encrypt_decrypt_page_input);
        confirmButton = findViewById(R.id.encrypt_decrypt_page_confirm_button);

        Intent intent  = getIntent();
        String stringedData = intent.getStringExtra(IntentExtraName);

        JSONObject data = new JSONObject();
        try {
            if (stringedData != null)
                data = new JSONObject(stringedData);
            else
                data = new JSONObject("{ 'choice':0 }");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        choice = MainActivity.Options.NEITHER;
        try {
            choice = MainActivity.Options.valueOf(data.getString("choice"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        AlterPagesLooks(choice);
    }

    private void AlterPagesLooks(MainActivity.Options choice) {
        switch (choice) {
            case ENCRYPT:
                page.setBackgroundResource(R.color.encrypt_page);
                headerTextView.setText(R.string.encrypt_header_label_text);
                subTextView.setText(R.string.encrypt_subtext_label_text);
                input.setBackgroundResource(R.color.encrypt_page_button);
                confirmButton.setText(R.string.encrypt_button_text);
                confirmButton.setBackgroundResource(R.color.encrypt_page_button);

                break;
            case DECRYPT:
                page.setBackgroundResource(R.color.decrypt_page);
                headerTextView.setText(R.string.decrypt_header_label_text);
                subTextView.setText(R.string.decrypt_subtext_label_text);
                input.setBackgroundResource(R.color.decrypt_page_button);
                confirmButton.setText(R.string.decrypt_button_text);
                confirmButton.setBackgroundResource(R.color.decrypt_page_button);
                break;
            case NEITHER:
            default:
                GoBack();

        }
    }

    private void GoBack(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onBackButtonClick(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
