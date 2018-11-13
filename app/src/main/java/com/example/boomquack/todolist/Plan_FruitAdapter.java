package com.example.boomquack.todolist;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    private Context  context;
    private static final String TAG = "Plan_FruitAdapter";


    static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView plan_fruitname;
        View planview;

        public ViewHolder(View view)
        {
            super(view);
            planview=view;
            plan_fruitname = (TextView) view.findViewById(R.id.plan_fruit_name);
        }
    }



    public Plan_FruitAdapter(Context context,List<String> PlanFruitList)
    {
        mplan_fruitlist = PlanFruitList;
        this.context=context;
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
        holder.planview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Main2Activity.class);
                context.startActivity(intent);
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







