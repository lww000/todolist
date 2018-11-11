package com.example.boomquack.todolist;

import org.litepal.crud.DataSupport;

/**
 * Created by boomquack on 2018/11/2.
 */

public class  Plan_Fruit  extends DataSupport
{
    private static final String TAG = "Plan_Fruit";
    private String plan;

    public String getPlan()
    {
        return plan;
    }
    public void setPlan(String plan)
    {
        this.plan=plan;
    }
}
