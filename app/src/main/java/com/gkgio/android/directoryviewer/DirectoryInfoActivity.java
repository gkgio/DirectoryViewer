package com.gkgio.android.directoryviewer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class DirectoryInfoActivity extends AppCompatActivity {

    private static final String INTENT_FILE_PARAM = "File";

    private TextView tvFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory_info);

        final File rootFile = (File) getIntent().getSerializableExtra(INTENT_FILE_PARAM);

        tvFilePath = (TextView) findViewById(R.id.tvFilePath);

        if (rootFile.listFiles() != null) {
            displayDirectoryContents(rootFile);
        } else tvFilePath.setText(R.string.tv_directory_is_empty);
    }

    private void displayDirectoryContents(File dir) {
        try {
            final File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    tvFilePath.append(getString(R.string.directory_filter, file.getCanonicalPath()));
                    displayDirectoryContents(file);
                } else {
                    tvFilePath.append(getString(R.string.file_filter, file.getCanonicalPath()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}