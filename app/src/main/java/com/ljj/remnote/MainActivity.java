package com.ljj.remnote;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.ljj.remnote.Countdown.AddCountdownDialogActivity;
import com.ljj.remnote.Countdown.CountdownManager;
import com.ljj.remnote.Countdown.RecyclerViewAdapterCountdownList;
import com.ljj.remnote.Thread.RemThread;
import com.ljj.remnote.Thread.RemThreadTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    private Handler uiFlushHandler = new Handler();
    private Runnable uiFlushRunnalbe = new Runnable() {
        @Override
        public void run() {
            this.flushUi();
            uiFlushHandler.postDelayed(this,500);
        }
        private void flushUi(){
            updateRecyclerViewCountdownTask();
        }
    };

    RecyclerViewAdapterCountdownList recyclerViewAdapterCountdownList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //日志初始化
        RemLog.setContext(getApplicationContext());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button buttonAddCountdownDialog = findViewById(R.id.button_add_countdown_dialog);
        buttonAddCountdownDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddCountdownDialogActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_countdown_task);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerViewAdapterCountdownList = new RecyclerViewAdapterCountdownList(CountdownManager.getInstance().getTaskList());
        recyclerView.setAdapter(recyclerViewAdapterCountdownList);

        uiFlushHandler.postDelayed(uiFlushRunnalbe,500);


    }

    public static Integer testFunc() {
        RemLog.LogD(TAG, "RemThread test");
        return 0;
    }

    @Override
    protected void onStart() {
        super.onStart();
        RemLog.LogD(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        RemLog.LogD(TAG, "onResume");
        //updateRecyclerViewCountdownTask();
    }

    //TODO:定时任务实现实时刷新
    private Integer updateRecyclerViewCountdownTask() {
        RemLog.LogD(TAG,"updateRecyclerViewCountdownTask");
        recyclerViewAdapterCountdownList.notifyDataSetChanged();
        return 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
