package com.example.boomquack.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<PlanItem> planItemList;
    private LinearLayoutManager layoutmanager;
    private RecyclerView recyclerView;
    private Button bt1,bt2;
    private Plan_FruitAdapter plan_fruitAdapter;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        initView();


        final Plan_FruitAdapter finalPlan_fruitAdapter = plan_fruitAdapter;
        bt1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                finalPlan_fruitAdapter.addItem(planItemList.size(),planItemList);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"侧滑删除",Toast.LENGTH_SHORT).show();
            }
        });




    }



    public void initView()
    {   bt1=(Button)findViewById(R.id.bt1);
        bt2=(Button)findViewById(R.id.bt2);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);




        layoutmanager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutmanager);

        plan_fruitAdapter=new Plan_FruitAdapter(MainActivity.this,planItemList);
        recyclerView.setAdapter(plan_fruitAdapter);

        ItemTouchHelper.Callback callback=new SLIDESLIP(plan_fruitAdapter);
        ItemTouchHelper  touchHelper=new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
    }
}








