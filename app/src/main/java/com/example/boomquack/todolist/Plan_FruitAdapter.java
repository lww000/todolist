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
    private  List<PlanItem> mplanItemList;
   // private List<String> mplan_fruitlist;
    private Context  context;
    private static final String TAG = "Plan_FruitAdapter";

    public Plan_FruitAdapter(Context context,List<PlanItem> PlanFruitList)
    {
        mplanItemList = PlanFruitList;
        this.context=context;
    }


    static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView plantitle;
        View planview;

        public ViewHolder(View view)
        {
            super(view);
            planview=view;
            plantitle = (TextView) view.findViewById(R.id.plan_fruit_name);
        }
    }


    public void addItem(int position, List<PlanItem> planfruitlist)
    {
        PlanItem plan1=new PlanItem();
        planfruitlist.add(position,plan1);
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
        PlanItem planitem=mplanItemList.get(position);
        holder.plantitle.setText(planitem.getContent());
    }

    @Override
    public int getItemCount(){
        return mplanItemList.size();
    }


    public void onItemMove(int fromPosition,int toPosition)
    {
        Collections.swap(mplanItemList,fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);
        Log.d(TAG, "onItemMove: ");
    }

    public void onItemDelete(int position)
    {
        mplanItemList.remove(position);
        notifyItemRemoved(position);
        Log.d(TAG, "onItemDelete: ");
    }
}







