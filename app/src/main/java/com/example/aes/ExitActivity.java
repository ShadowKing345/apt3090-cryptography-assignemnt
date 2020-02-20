package com.example.aes;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ExitActivity extends AppCompatActivity {

    public static final String INTENT_EXTRA_STRING = "com.aturcanu.AES.END_DATA";
    Progress progress;

    private TextView resultTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exit_page);

        Intent intent = getIntent();
        progress = (Progress) intent.getSerializableExtra(INTENT_EXTRA_STRING);

        resultTextView = (TextView) findViewById(R.id.exit_page_result_text);
    }

    @Override
    protected void onStart() {
        super.onStart();

        resultTextView.setText(progress.getInput());
    }

    public void onCopyButtonClick(View view) {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("cypher text", "Hello World");
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
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
