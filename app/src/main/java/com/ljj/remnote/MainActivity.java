package com.ljj.remnote;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.ljj.remnote.Countdown.AddCountdownDialogActivity;
import com.ljj.remnote.Countdown.CountdownManager;
import com.ljj.remnote.Countdown.RecyclerViewAdapterCountdownList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
//                NotificationUtils notificationUtils = new NotificationUtils(
//                        getApplicationContext(),
//                        0,
//                        "",
//                        R.mipmap.ic_launcher,
//                        "标题",
//                        "内容");
//                notificationUtils.notified();
                Intent intent = new Intent(MainActivity.this, AddCountdownDialogActivity.class);
                startActivity(intent);
            }
        });

        updateRecyclerViewCountdownTask();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("rem", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("rem", "onResume");
        updateRecyclerViewCountdownTask();
    }

    public void updateRecyclerViewCountdownTask() {
        Toast.makeText(getApplicationContext(), "updateRecyclerViewCountdownTask, cnt=" + CountdownManager.getInstance().getTaskList().size(), Toast.LENGTH_LONG).show();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_countdown_task);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerViewAdapterCountdownList adapter = new RecyclerViewAdapterCountdownList(CountdownManager.getInstance().getTaskList());
        recyclerView.setAdapter(adapter);
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
