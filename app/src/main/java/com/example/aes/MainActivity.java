package com.example.aes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout encryptDecryptPage;
    private TextView encryptDecryptHeaderTextView;
    private TextView encryptDecryptSubTextView;
    private EditText encryptDecryptBrowseInput;
    private Button encryptDecryptConfirmButton;

    private enum Options {
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
        setContentView(R.layout.encrypt_decrypt_page);
        this.encryptDecryptPage = (ConstraintLayout) findViewById(R.id.encrypt_decrypt_page);
        this.encryptDecryptHeaderTextView = (TextView) findViewById(R.id.encrypt_decrypt_page_header);
        this.encryptDecryptSubTextView = (TextView) findViewById(R.id.encrypt_decrypt_page_subtext);
        this.encryptDecryptBrowseInput = (EditText) findViewById(R.id.encrypt_decrypt_page_input);
        this.encryptDecryptConfirmButton = (Button) findViewById(R.id.encrypt_decrypt_page_confirm_button);

        switch (option) {
            case ENCRYPT:
                this.encryptDecryptPage.setBackgroundResource(R.color.encrypt_page);
                this.encryptDecryptHeaderTextView.setText(R.string.encrypt_header_label_text);
                this.encryptDecryptSubTextView.setText(R.string.encrypt_subtext_label_text);
                this.encryptDecryptBrowseInput.setBackgroundResource(R.color.encrypt_page_button);
                this.encryptDecryptConfirmButton.setText(R.string.encrypt_button_text);
                this.encryptDecryptConfirmButton.setBackgroundResource(R.color.encrypt_page_button);
                break;
            case DECRYPT:
                this.encryptDecryptPage.setBackgroundResource(R.color.decrypt_page);
                this.encryptDecryptHeaderTextView.setText(R.string.decrypt_header_label_text);
                this.encryptDecryptSubTextView.setText(R.string.decrypt_subtext_label_text);
                this.encryptDecryptBrowseInput.setBackgroundResource(R.color.decrypt_page_button);
                this.encryptDecryptConfirmButton.setText(R.string.decrypt_button_text);
                this.encryptDecryptConfirmButton.setBackgroundResource(R.color.decrypt_page_button);
                break;
            case NEITHER:
            default:
                onBackButtonClick(findViewById(R.id.encrypt_decrypt_page_back_button));
        }

    }

    public void onBackButtonClick(View view){
        setContentView(R.layout.landing_page);
    }
}
