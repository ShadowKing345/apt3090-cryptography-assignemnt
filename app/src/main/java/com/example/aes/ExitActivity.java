package com.example.aes;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ExitActivity extends AppCompatActivity {

    public static final String INTENT_EXTRA_STRING = "com.aturcanu.AES.END_DATA";
    private Progress progress;
    private String result;

    private TextView resultTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exit_page);

        Intent intent = getIntent();
        progress = (Progress) intent.getSerializableExtra(INTENT_EXTRA_STRING);

        resultTextView = (TextView) findViewById(R.id.exit_page_result_text);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onStart() {
        super.onStart();

        switch (progress.getChoice()) {
            case ENCRYPT:
                result = AES.encrypt(progress.getInput(), progress.getKey());
                break;
            case DECRYPT:
                result = AES.decrypt(progress.getInput(), progress.getKey());
                break;
            case NEITHER:
                default:
                    goBack();
        }
                resultTextView.setText(result);
    }

    public void onCopyButtonClick(View view) {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("cypher text", result);
        assert clipboardManager != null;
        clipboardManager.setPrimaryClip(clipData);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.success_dialog_box_title).setMessage(R.string.dialog_message_copied_clipboard);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onBackButtonClick(View view) {
        goBack();
    }

    public void goBack() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
