package com.example.android.example;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by Michał on 22.03.2018.
 */

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView textView;
    CheckBox checkBox;

    ItemClickListener itemClickListener;

    public ViewHolder(View itemView) {
        super(itemView);

        textView = (TextView) itemView.findViewById(R.id.text_view);
        checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.OnItemClick(v, getAdapterPosition());
    }
}
