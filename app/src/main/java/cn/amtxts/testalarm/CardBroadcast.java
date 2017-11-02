package cn.amtxts.testalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

public class CardBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getStringExtra("type");
        if (!TextUtils.isEmpty(action) && action.equals("AUTO_SERVER_CARD")) {
            Log.e("CardBroadcast", "action = " + action);
        }
    }
}