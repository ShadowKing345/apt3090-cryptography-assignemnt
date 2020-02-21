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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import static com.example.aes.Progress.Options;

public class PasswordActivity extends AppCompatActivity {

    public static final String INTENT_EXTRA_STRING = "com.aturcanu.AES.PASSWORD_DATA";
    private Progress progress;

    private ConstraintLayout page;
    private TextView headerTextView;
    private TextView subTextView;
    private EditText input;
    private Button confirmButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_page);

        Intent intent = getIntent();
        progress = (Progress) intent.getSerializableExtra(INTENT_EXTRA_STRING);

        page = (ConstraintLayout) findViewById(R.id.password_page);
        headerTextView = (TextView) findViewById(R.id.password_page_header_text);
        subTextView = (TextView) findViewById(R.id.password_page_subtext_text);
        input = (EditText) findViewById(R.id.password_page_input);
        confirmButton = (Button) findViewById(R.id.password_page_confirm_button);

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
        alterPagesLook(progress.getChoice());
    }

    private void alterPagesLook(Options choice) {
        page.setBackgroundResource(R.color.password_page);
        headerTextView.setText(R.string.password_page_header_label_text);
        headerTextView.setTextColor(getResources().getColor(R.color.password_page_text));
        subTextView.setTextColor(getResources().getColor(R.color.password_page_text));
        input.setTextColor(getResources().getColor(R.color.password_page_text));
        confirmButton.setTextColor(getResources().getColor(R.color.password_page_text));

        switch (choice) {
            case ENCRYPT:
                subTextView.setText(R.string.password_page_encrypt_subtext_label_text);
                input.setBackgroundResource(R.color.password_page_encrypt_button);
                confirmButton.setText(R.string.password_page_encrypt_button_text);
                confirmButton.setBackgroundResource(R.color.password_page_encrypt_button);
                break;

            case DECRYPT:
                subTextView.setText(R.string.password_page_decrypt_subtext_label_text);
                input.setBackgroundResource(R.color.password_page_decrypt_button);
                confirmButton.setText(R.string.password_page_decrypt_button_text);
                confirmButton.setBackgroundResource(R.color.password_page_decrypt_button);
                break;

            case NEITHER:
            default:
                goBack();

        }

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

        if(inputText.length() < 5) {
            dialog.setMessage(getResources().getString(R.string.alert_dialog_message_less_then_5));
            dialog.show();
            return;
        }

        progress.setKey(inputText);

        Intent intent = new Intent(this, ExitActivity.class);
        intent.putExtra(ExitActivity.INTENT_EXTRA_STRING, progress);
        startActivity(intent);
    }

    private void goBack(){
        Intent intent = new Intent(getApplicationContext(), EncryptDecryptActivity.class);
        intent.putExtra(EncryptDecryptActivity.INTENT_EXTRA_STRING, progress);
        startActivity(intent);
    }


    public void onBackButtonClick(View view) {
        goBack();
    }
}
