package com.gkgio.android.directoryviewer;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File[] files = Environment.getRootDirectory().listFiles();
        FilesAdapter filesAdapter = new FilesAdapter(this, files);

        RecyclerView rvDirectories = (RecyclerView) findViewById(R.id.rvDirectories);

        rvDirectories.setLayoutManager(new LinearLayoutManager(this));
        rvDirectories.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        rvDirectories.setAdapter(filesAdapter);
    }
}