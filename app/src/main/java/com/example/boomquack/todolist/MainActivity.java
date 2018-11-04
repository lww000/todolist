package com.example.boomquack.todolist;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    private EditText edit;
    private RecyclerView recyclerView;
    private List<String> planfruitlist=new ArrayList<>();
    Plan_FruitAdapter plan_fruitAdapter=new Plan_FruitAdapter(planfruitlist);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initDate();
        initAdapter();
    }
    protected  void onDestory(){
        super.onDestroy();
        String inputText=edit.getText().toString();
        save(inputText);
    }

    public void save(String inputText){
        FileOutputStream out=null;
        BufferedWriter writer=null;
        try{
            out=openFileOutput("data", Context.MODE_PRIVATE);
            writer=new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                if(writer!=null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public String load(){
        FileInputStream in=null;
        BufferedReader reader=null;
        StringBuilder content=new StringBuilder();
        try{
            in=openFileInput("data");
            reader=new BufferedReader(new InputStreamReader(in));
            String line="";
            while((line=reader.readLine())!=null){
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader!=null){
                try{
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  content.toString();
    }
    private void initView(){
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        Button bt1=(Button)findViewById(R.id.bt1);
        bt1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                plan_fruitAdapter.addItem(planfruitlist.size(),planfruitlist);
            }
        });

    }
    private void initDate(){}

    private void initAdapter(){
        if(plan_fruitAdapter==null){
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(linearLayoutManager);
            plan_fruitAdapter=new Plan_FruitAdapter(planfruitlist);
            ItemTouchHelper.Callback callback=new SLIDESLIP(plan_fruitAdapter);
            ItemTouchHelper touchHelper=new ItemTouchHelper(callback);
            touchHelper.attachToRecyclerView(recyclerView);
        }

        recyclerView.setAdapter(plan_fruitAdapter);
    }

}








