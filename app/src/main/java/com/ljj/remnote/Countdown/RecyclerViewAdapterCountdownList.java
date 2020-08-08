package com.ljj.remnote.Countdown;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.ljj.remnote.R;
import com.ljj.remnote.RemLog;
import com.ljj.remnote.StringTool;
import com.ljj.remnote.TimeTool;

import java.sql.Time;
import java.util.List;

public class RecyclerViewAdapterCountdownList extends RecyclerView.Adapter<RecyclerViewAdapterCountdownList.ViewHolder> {
    public static final String TAG = "RecyclerViewAdapterCountdownList";

    private List<CountdownTask> mCountdownTaskLIst;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTaskName;
        TextView textViewTaskTime;
        Button buttonStart;
        TextView textViewRemainTime;
        Button buttonDelete;

        public ViewHolder(View view) {
            super(view);
            textViewTaskName = (TextView) view.findViewById(R.id.recyclerViewItem_countDownList_name);
            textViewTaskTime = (TextView) view.findViewById(R.id.recyclerViewItem_countDownList_time);
            buttonStart = (Button) view.findViewById(R.id.button_recyclerViewItem_countDownList_start);
            textViewRemainTime = (TextView) view.findViewById(R.id.textView_recyclerViewItem_countDownList_remainTime);
            buttonDelete = (Button) view.findViewById(R.id.button_recyclerViewItem_countDownList_delete);
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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final CountdownTask task = mCountdownTaskLIst.get(position);
        holder.textViewTaskName.setText(task.getName());
        holder.textViewTaskTime.setText(task.getH() + "h" + task.getM() + "m" + task.getS() + "s");
        if (!task.getRunning()) {
            holder.buttonStart.setVisibility(View.VISIBLE);
            holder.textViewRemainTime.setVisibility(View.GONE);

            holder.buttonStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    task.start();
                    notifyItemChanged(position);
                }
            });
        } else {
            holder.textViewRemainTime.setVisibility(View.VISIBLE);
            holder.buttonStart.setVisibility(View.GONE);

            Long remainTime = task.getRemainTime();
            Long h = remainTime / TimeTool.MILLIS_PER_HOUR;
            Long m = remainTime % TimeTool.MILLIS_PER_HOUR / TimeTool.MILLIS_PER_MINUTE;
            Long s = remainTime % TimeTool.MILLIS_PER_MINUTE / TimeTool.MILLIS_PER_SECOND;
            Long ms = remainTime % TimeTool.MILLIS_PER_SECOND;
            String strH = StringTool.getStringFromLongWithLeadingZero(h, 0);
            String strM = StringTool.getStringFromLongWithLeadingZero(m, 2);
            String strS = StringTool.getStringFromLongWithLeadingZero(s, 2);
            String strMS = StringTool.getStringFromLongWithLeadingZero(ms, 3);
            String strRemainTime = strH + ':' + strM + ':' + strS + ':' + strMS;
            holder.textViewRemainTime.setText(strRemainTime);
        }
        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!CountdownManager.getInstance().deleteCountdownTask(task)) {
                    RemLog.LogE(TAG, "delete invalid task");
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCountdownTaskLIst.size();
    }
}
