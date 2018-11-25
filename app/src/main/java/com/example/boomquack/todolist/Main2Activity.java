package com.example.boomquack.todolist;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;
    private EditText editText;
    private Button addData;
    private static final String TAG = "Main2Activity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView1();



    }

    //设置一次闹钟
    public void setAlarmOne(View view) {
        //获取当前系统时间
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        //弹出对话框
        TimePickerDialog timePickerDialog = new TimePickerDialog
                (this, new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute)
                    {
                        Calendar calendar1=Calendar.getInstance();
                        calendar1.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar1.set(Calendar.MINUTE,minute);
                        Log.i("test","调了1");
                        Intent intent=new Intent("AlarmClock");
                        pendingIntent=PendingIntent.getBroadcast(Main2Activity.this,0,intent,0);
                        alarmManager.set(AlarmManager.RTC_WAKEUP,calendar1.getTimeInMillis(),pendingIntent);
                        Log.i("test","调了2");
                    }
                }, hour, minute, true);
        timePickerDialog.show();
        Log.d(TAG, "setAlarmOne: ");
    }

    public void initView1(){
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        editText=(EditText)findViewById(R.id.edit);

    }
}
