package com.example.boomquack.todolist;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;




/**
 * Created by boomquack on 2018/11/2.
 */

public class Plan_FruitAdapter extends RecyclerView.Adapter<Plan_FruitAdapter.ViewHolder> {
    private List<String> mplan_fruitlist;
    private static final String TAG = "Plan_FruitAdapter";


    static class ViewHolder extends RecyclerView.ViewHolder
    {
        View planfruitview;
        TextView plan_fruitname;
        Button addData;
        EditText edit;

        public ViewHolder(View view)
        {
            super(view);
            plan_fruitname = (TextView) view.findViewById(R.id.plan_fruit_name);
            addData =(Button)view.findViewById(R.id.addData);
            edit=(EditText)view.findViewById(R.id.et);
        }
    }



    public Plan_FruitAdapter(List<String> PlanFruitList)
    {
        mplan_fruitlist = PlanFruitList;
    }


    public void addItem(int position, List<String> planfruitlist)
    {
        planfruitlist.add(position,"今天的小目标！！");
        notifyItemInserted(position);
        Log.d(TAG, "addItem: ");
    }


    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_fruit_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText;
                inputText = holder.edit.getText().toString();
                Plan_Fruit plan_fruit = new Plan_Fruit();
                      plan_fruit.setPlan(inputText);
                      plan_fruit.save();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position)
    {
        String plan_fruit=mplan_fruitlist.get(position);
        holder.plan_fruitname.setText(mplan_fruitlist.get(position));
    }

    @Override
    public int getItemCount(){
        return mplan_fruitlist.size();
    }


    public void onItemMove(int fromPosition,int toPosition)
    {
        Collections.swap(mplan_fruitlist,fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);
        Log.d(TAG, "onItemMove: ");
    }

    public void onItemDelete(int position)
    {
        mplan_fruitlist.remove(position);
        notifyItemRemoved(position);
        Log.d(TAG, "onItemDelete: ");
    }
}







