package com.ljj.remnote.Countdown;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.ljj.remnote.R;

import java.util.List;

public class RecyclerViewAdapterCountdownList extends RecyclerView.Adapter<RecyclerViewAdapterCountdownList.ViewHolder> {

    private List<CountdownTask> mCountdownTaskLIst;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView taskName;
        TextView taskTime;

        public ViewHolder(View view) {
            super(view);
            taskName = (TextView) view.findViewById(R.id.recyclerviewitem_countdownlist_name);
            taskTime = (TextView) view.findViewById(R.id.recyclerviewitem_countdownlist_time);
        }
    }

    public RecyclerViewAdapterCountdownList(List<CountdownTask> countdownTaskList) {
        mCountdownTaskLIst = countdownTaskList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewitem_countdownlist, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CountdownTask task = mCountdownTaskLIst.get(position);
        holder.taskName.setText(task.getName());
        holder.taskTime.setText(task.getH()+"h"+task.getM()+"m"+task.getS()+"s");
    }

    @Override
    public int getItemCount() {
        return mCountdownTaskLIst.size();
    }
}
