package com.example.boomquack.todolist;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import org.litepal.tablemanager.Connector;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;
    private List<String> planfruitlist = new ArrayList<>();
    private LinearLayoutManager layoutmanager;
    private RecyclerView recyclerView;
    private Button bt1,bt2,bt3;
    private Plan_FruitAdapter plan_fruitAdapter;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        initView();

        layoutmanager=new LinearLayoutManager(this);
        plan_fruitAdapter=new Plan_FruitAdapter(planfruitlist);
        ItemTouchHelper.Callback callback=new SLIDESLIP(plan_fruitAdapter);
        ItemTouchHelper  touchHelper=new ItemTouchHelper(callback);

        recyclerView.setLayoutManager(layoutmanager);
        recyclerView.setAdapter(plan_fruitAdapter);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        touchHelper.attachToRecyclerView(recyclerView);


        final Plan_FruitAdapter finalPlan_fruitAdapter = plan_fruitAdapter;


        bt1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                finalPlan_fruitAdapter.addItem(planfruitlist.size(),planfruitlist);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"侧滑删除",Toast.LENGTH_SHORT).show();
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
            }
        });

//        bt4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                      String inputText = edit.getText().toString();
//                      Plan_Fruit plan_fruit = new Plan_Fruit();
//                      plan_fruit.setPlan(inputText);
//                      plan_fruit.save();
//            }
//        });
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
                        pendingIntent=PendingIntent.getBroadcast(MainActivity.this,0,intent,0);
                        alarmManager.set(AlarmManager.RTC_WAKEUP,calendar1.getTimeInMillis(),pendingIntent);
                        Log.i("test","调了2");
                    }
                }, hour, minute, true);
        timePickerDialog.show();
        Log.d(TAG, "setAlarmOne: ");
    }

    //取消
    public void cancelAlarmCycle(View view) {
        alarmManager.cancel(pendingIntent);
        Log.d(TAG, "cancelAlarmCycle: ");
    }

    public void initView()
    {   bt1=(Button)findViewById(R.id.bt1);
        bt2=(Button)findViewById(R.id.bt2);
        bt3=(Button)findViewById(R.id.bt3);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }
}








