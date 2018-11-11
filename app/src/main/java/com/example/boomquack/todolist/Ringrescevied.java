package com.example.boomquack.todolist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by boomquack on 2018/11/6.
 */

public class Ringrescevied extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if("AlarmClock".equals(intent.getAction()))
        {
            Log.i("test","收到广播了");
            //跳转到另一个activity
            Intent intent1=new Intent(context,Ring.class);
            //给intent设置标志位flag
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);
        }
    }
}
