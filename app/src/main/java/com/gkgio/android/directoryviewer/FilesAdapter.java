package com.gkgio.android.directoryviewer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.File;

/**
 * Created by Gigauri Georgy on 07.06.2017.
 * gkgio
 */

public class FilesAdapter extends RecyclerView.Adapter<FilesAdapter.CategoryItemViewHolder> {

    private final File[] files;
    private Context context;

    public FilesAdapter(Context context, File[] files) {
        this.context = context;
        this.files = files;
    }

    @Override
    public CategoryItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.files_list_item, parent, false);
        return new CategoryItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryItemViewHolder holder, int position) {

        final File file = files[position];

        holder.btnFileName.setText(file.getName());

        if (file.isDirectory()) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gson gson = new Gson();
                    final String jsonFile = gson.toJson(file, File.class);
                    Intent intent = new Intent(context, DirectoryInfo.class);
                    intent.putExtra("File", jsonFile);
                    context.startActivity(intent);
                }
            });
        } else holder.btnFileName.setClickable(false);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return files.length;
    }

    class CategoryItemViewHolder extends RecyclerView.ViewHolder {

        final Button btnFileName;

        public CategoryItemViewHolder(View itemView) {
            super(itemView);
            btnFileName = (Button) itemView.findViewById(R.id.btnFileName);
        }
    }
}