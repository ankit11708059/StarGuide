package com.ankit626.startup;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ankit Sharma on 11-03-2018.
 */

public class Intromanager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    public Intromanager(Context context){
        this.context=context;
        pref=context.getSharedPreferences("first",0);
        editor=pref.edit();
    }

    public void setfirst(boolean isFirst){
          editor.putBoolean("check",isFirst);
          editor.commit();

    }
    public boolean check(){
        return pref.getBoolean("check",true);
    }
}
