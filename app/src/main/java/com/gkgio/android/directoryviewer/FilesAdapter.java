package com.gkgio.android.directoryviewer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.File;

/**
 * Created by Gigauri Georgy on 07.06.2017.
 * gkgio
 */

public class FilesAdapter extends RecyclerView.Adapter<FilesAdapter.CategoryItemViewHolder> {

    private final String INTENT_FILE_PARAM = "File";

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
                    Intent intent = new Intent(context, DirectoryInfoActivity.class);
                    intent.putExtra(INTENT_FILE_PARAM, file);
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