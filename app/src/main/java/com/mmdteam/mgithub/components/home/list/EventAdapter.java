package com.mmdteam.mgithub.components.home.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmdteam.mgithub.R;
import com.mmdteam.mgithub.model.Event;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private ArrayList<Event> listEvents = new ArrayList<>();

    public void swapAdapter(ArrayList<Event> events) {
        listEvents.clear();
        listEvents.addAll(events);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        holder.bind(listEvents.get(position));
    }

    @Override
    public int getItemCount() {
        if (listEvents != null && listEvents.size() > 0) {
            return listEvents.size();
        }
        return 0;
    }
}
