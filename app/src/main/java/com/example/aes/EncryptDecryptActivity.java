package com.example.aes;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import static com.example.aes.Progress.*;

public class EncryptDecryptActivity extends AppCompatActivity {
    public static final String INTENT_EXTRA_STRING = "com.aturcanu.AES.ENCRYPT_DECRYPT_DATA";

    public Progress progress;

    private ConstraintLayout page;
    private TextView headerTextView;
    private TextView subTextView;
    private EditText input;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encrypt_decrypt_page);

        Intent intent  = getIntent();
        progress = (Progress)intent.getSerializableExtra(INTENT_EXTRA_STRING);

        page = (ConstraintLayout) findViewById(R.id.encrypt_decrypt_page);
        headerTextView = (TextView) findViewById(R.id.encrypt_decrypt_page_header);
        subTextView = (TextView) findViewById(R.id.encrypt_decrypt_page_subtext);
        input = (EditText) findViewById(R.id.encrypt_decrypt_page_input);
        confirmButton = (Button) findViewById(R.id.encrypt_decrypt_page_confirm_button);

        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo
                            .IME_ACTION_DONE:
                        onConfirmButtonClick(v);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        AlterPagesLook(progress.getChoice());
    }

    private void AlterPagesLook(Options choice) {
        switch (choice) {
            case ENCRYPT:
                page.setBackgroundResource(R.color.encrypt_page);
                headerTextView.setText(R.string.encrypt_header_label_text);
                headerTextView.setTextColor(getResources().getColor(R.color.encrypt_page_text));
                subTextView.setText(R.string.encrypt_subtext_label_text);
                subTextView.setTextColor(getResources().getColor(R.color.encrypt_page_text));
                input.setBackgroundResource(R.color.encrypt_page_button);
                input.setTextColor(getResources().getColor(R.color.encrypt_page_text));
                confirmButton.setText(R.string.encrypt_button_text);
                confirmButton.setBackgroundResource(R.color.encrypt_page_button);
                confirmButton.setTextColor(getResources().getColor(R.color.encrypt_page_text));

                break;
            case DECRYPT:
                page.setBackgroundResource(R.color.decrypt_page);
                headerTextView.setText(R.string.decrypt_header_label_text);
                headerTextView.setTextColor(getResources().getColor(R.color.decrypt_page_text));
                subTextView.setText(R.string.decrypt_subtext_label_text);
                subTextView.setTextColor(getResources().getColor(R.color.decrypt_page_text));
                input.setBackgroundResource(R.color.decrypt_page_button);
                input.setTextColor(getResources().getColor(R.color.decrypt_page_text));
                confirmButton.setText(R.string.decrypt_button_text);
                confirmButton.setBackgroundResource(R.color.decrypt_page_button);
                confirmButton.setTextColor(getResources().getColor(R.color.decrypt_page_text));
                break;
            case NEITHER:
            default:
                goBack();

        }
    }

    private void goBack(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void onConfirmButtonClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.alert_dialog_box_title);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();

        String inputText = input.getText().toString();

        if(inputText == null){
            dialog.setMessage(getResources().getString(R.string.what));
            dialog.show();
            return;
        }

        if(inputText.length() <= 0){
            dialog.setMessage(getResources().getString(R.string.alert_dialog_message_empty_text));
            dialog.show();
            return;
        }

        progress.setInput(inputText);

        Intent intent = new Intent(this, PasswordActivity.class);
        intent.putExtra(PasswordActivity.INTENT_EXTRA_STRING, progress);
        startActivity(intent);
    }

    public void onBackButtonClick(View view){
        goBack();
    }
}
