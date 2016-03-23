package com.test.tonychuang.tmuhttc_0_5.Z_other.JSON;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by TonyChuang on 2016/3/23.
 */
public class JSONParser {
    public JSONParser() {
        super();
    }

    public String parseResultStrig(JSONObject object){
        String string = new String();
        try {
            string = object.getString("Successful");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser =>Department", e.getMessage());
        }
        return string;
    }

    public String parseString(JSONObject object){
        String string = new String();
        try {
            string = object.getString("Value");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser =>Department", e.getMessage());
        }
        return string;
    }

    public boolean parseBoolean(JSONObject object){
        boolean aboolean = false;
        try {
            aboolean = object.getBoolean("Value");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return aboolean;
    }
}
