package com.example.boomquack.todolist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by boomquack on 2018/11/2.
 */

public class Plan_FruitAdapter extends RecyclerView.Adapter<Plan_FruitAdapter.ViewHolder> {
    private List<String> mplan_fruitlist;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View planfruitview;
        TextView plan_fruitname;

        public ViewHolder(View view) {
            super(view);
            plan_fruitname = (TextView) view.findViewById(R.id.plan_fruit_name);
        }
    }

    public Plan_FruitAdapter(List<String> PlanFruitList) {
        mplan_fruitlist = PlanFruitList;
    }

    public void addItem(int position, List<String> planfruitlist){
        planfruitlist.add(position,"plan"+position);
        notifyItemInserted(position);

    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_fruit_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        String plan_fruit=mplan_fruitlist.get(position);
        holder.plan_fruitname.setText(mplan_fruitlist.get(position));
    }

    @Override
    public int getItemCount(){
        return mplan_fruitlist.size();
    }


    public void onItemMove(int fromPosition,int toPosition){
        Collections.swap(mplan_fruitlist,fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);
    }

    public void onItemDelete(int position){
        mplan_fruitlist.remove(position);
        notifyItemRemoved(position);
    }
 }



