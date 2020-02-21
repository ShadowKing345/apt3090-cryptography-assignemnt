package com.example.aes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import static com.example.aes.Progress.*;

public class MainActivity extends AppCompatActivity {

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

        Progress progress = new Progress(option);

        intent.putExtra(EncryptDecryptActivity.INTENT_EXTRA_STRING, progress);
        startActivity(intent);
    }
}
