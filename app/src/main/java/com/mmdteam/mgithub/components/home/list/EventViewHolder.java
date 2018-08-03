package com.mmdteam.mgithub.components.home.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mmdteam.mgithub.R;
import com.mmdteam.mgithub.model.Event;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.events_id)
    TextView id;

    EventViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Event event) {
        id.setText(event.getId());
    }
}
