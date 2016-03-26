package com.test.tonychuang.tmuhttc_0_5.Z_other.JSON;

import android.util.Log;

import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.RowDataFormat.PsnDataSettingRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.RowDataFormat.PsnSettingRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.RowDataFormat.WLevelRow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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

    public ArrayList<PsnDataSettingRow> parsePsnDataSettingRow(JSONObject object) {
        ArrayList<PsnDataSettingRow> arrayList = new ArrayList<PsnDataSettingRow>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new PsnDataSettingRow(
                        jsonObj.getString("pid"),
                        jsonObj.getString("pwd"),
                        jsonObj.getString("aid"),
                        jsonObj.getString("sid"),
                        jsonObj.getString("memberflag"),
                        jsonObj.getString("avatar"),
                        jsonObj.getString("name"),
                        jsonObj.getString("nickname"),
                        jsonObj.getInt("sex"),
                        jsonObj.getString("birthday"),
                        jsonObj.getString("email"),
                        jsonObj.getString("phone"),
                        jsonObj.getString("gcmId"),
                        jsonObj.getString("updatetime")
                ));
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser =>Department", e.getMessage());
        }
        return arrayList;
    }

    public ArrayList<PsnSettingRow> parsePsnSettingRow(JSONObject object) {
        ArrayList<PsnSettingRow> arrayList = new ArrayList<PsnSettingRow>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new PsnSettingRow(
                        jsonObj.getString("data_not_flag"),
                        jsonObj.getString("medicine_not_flag"),
                        jsonObj.getString("pay_not_flag"),
                        jsonObj.getString("report_not_flag"),
                        jsonObj.getString("record_not_flag"),
                        jsonObj.getString("center_not_flag"),
                        jsonObj.getString("center_msg_flag"),
                        jsonObj.getString("location_flag"),
                        jsonObj.getString("updatetime")
                ));
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser =>Department", e.getMessage());
        }
        return arrayList;
    }

    public ArrayList<WLevelRow> parseWLevelRow(JSONObject object) {
        ArrayList<WLevelRow> arrayList = new ArrayList<WLevelRow>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new WLevelRow(
                        jsonObj.getInt("BP_SY_Max"),
                        jsonObj.getInt("BP_SY_Min"),
                        jsonObj.getInt("BP_DI_Max"),
                        jsonObj.getInt("BP_DI_Min"),
                        jsonObj.getInt("BP_HR_Max"),
                        jsonObj.getInt("BP_HR_Min"),
                        jsonObj.getInt("BP_SY_MaxDang"),
                        jsonObj.getInt("BP_SY_MinDang"),
                        jsonObj.getInt("BP_DI_MaxDang"),
                        jsonObj.getInt("BP_DI_MinDang"),
                        jsonObj.getInt("BP_HR_MaxDang"),
                        jsonObj.getInt("BP_HR_MinDang"),
                        jsonObj.getInt("BG_BM_Max"),
                        jsonObj.getInt("BG_BM_Min"),
                        jsonObj.getInt("BG_BM_MaxDang"),
                        jsonObj.getInt("BG_BM_MinDang"),
                        jsonObj.getInt("BG_AM_Max"),
                        jsonObj.getInt("BG_AM_Min"),
                        jsonObj.getInt("BG_AM_MaxDang"),
                        jsonObj.getInt("BG_AM_MinDang")
                ));
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser =>Department", e.getMessage());
        }
        return arrayList;
    }
}
