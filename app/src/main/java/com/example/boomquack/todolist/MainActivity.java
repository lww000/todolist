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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import org.litepal.tablemanager.Connector;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity {

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
        plan_fruitAdapter=new Plan_FruitAdapter(MainActivity.this,planfruitlist);
        ItemTouchHelper.Callback callback=new SLIDESLIP(plan_fruitAdapter);
        ItemTouchHelper  touchHelper=new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setLayoutManager(layoutmanager);
        recyclerView.setAdapter(plan_fruitAdapter);
//        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
//                startActivity(intent);
//            }
//        });




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


    }



    public void initView()
    {   bt1=(Button)findViewById(R.id.bt1);
        bt2=(Button)findViewById(R.id.bt2);
        bt3=(Button)findViewById(R.id.bt3);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }
}








