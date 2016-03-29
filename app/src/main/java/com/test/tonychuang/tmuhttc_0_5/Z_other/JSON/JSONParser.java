package com.test.tonychuang.tmuhttc_0_5.Z_other.JSON;

import android.util.Log;

import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.CtrMsgRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.CtrNotRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FGRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FRecvNotSetRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FShrNotSetRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FShrSetRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.GlyAvgRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.GlyDataRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.GlyMsgRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.GlyThumbRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PreAvgRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PreDataRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PreMsgRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PreThumbRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PsnNotRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.SMedRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.SPayRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.SRcrdRow;
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

    public ArrayList<PreDataRow> parsePreDataRow(JSONObject object) {
        ArrayList<PreDataRow> arrayList = new ArrayList<PreDataRow>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new PreDataRow(
                        jsonObj.getString("PData_sid"),
                        jsonObj.getLong("PData_table_id"),
                        jsonObj.getString("PData_datetime"),
                        jsonObj.getInt("PData_sys"),
                        jsonObj.getInt("PData_dia"),
                        jsonObj.getInt("PData_hr")
                ));
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser =>Department", e.getMessage());
        }
        return arrayList;
    }

    public ArrayList<PreThumbRow> parsePreThumbRow(JSONObject object) {
        ArrayList<PreThumbRow> arrayList = new ArrayList<PreThumbRow>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new PreThumbRow(
                        jsonObj.getLong("PData_thumb_table_id"),
                        jsonObj.getString("PData_thumb_sid"),
                        jsonObj.getString("PData_thumb_datetime"),
                        jsonObj.getInt("PData_thumb_count"),
                        jsonObj.getString("PData_thumb_aids")
                ));
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser =>Department", e.getMessage());
        }
        return arrayList;
    }

    public ArrayList<PreMsgRow> parsePreMsgRow(JSONObject object) {
        ArrayList<PreMsgRow> arrayList = new ArrayList<PreMsgRow>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new PreMsgRow(
                        jsonObj.getString("PMsg_sid"),
                        jsonObj.getLong("PMsg_table_id"),
                        jsonObj.getString("PMsg_writer_aid"),
                        jsonObj.getString("PMsg_datetime"),
                        jsonObj.getString("PMsg_content"),
                        jsonObj.getInt("PMsg_status")
                ));
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser =>Department", e.getMessage());
        }
        return arrayList;
    }

    public ArrayList<PreAvgRow> parsePreAvgRow(JSONObject object) {
        ArrayList<PreAvgRow> arrayList = new ArrayList<PreAvgRow>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new PreAvgRow(
                        jsonObj.getString("PAvg_sid"),
                        jsonObj.getString("PAvg_datetime"),
                        jsonObj.getInt("PAvg_sys"),
                        jsonObj.getInt("PAvg_dia"),
                        jsonObj.getInt("PAvg_hr")
                ));
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser =>Department", e.getMessage());
        }
        return arrayList;
    }

    public ArrayList<GlyDataRow> parseGlyDataRow(JSONObject object) {
        ArrayList<GlyDataRow> arrayList = new ArrayList<GlyDataRow>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new GlyDataRow(
                        jsonObj.getString("GData_sid"),
                        jsonObj.getLong("GData_table_id"),
                        jsonObj.getString("GData_datetime"),
                        jsonObj.getInt("GData_value"),
                        jsonObj.getString("GData_meal_flag")
                ));
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser =>Department", e.getMessage());
        }
        return arrayList;
    }

    public ArrayList<GlyThumbRow> parseGlyThumbRow(JSONObject object) {
        ArrayList<GlyThumbRow> arrayList = new ArrayList<GlyThumbRow>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new GlyThumbRow(
                        jsonObj.getLong("GData_thumb_table_id"),
                        jsonObj.getString("GData_thumb_sid"),
                        jsonObj.getString("GData_thumb_datetime"),
                        jsonObj.getInt("GData_thumb_count"),
                        jsonObj.getString("GData_thumb_aids")
                ));
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser =>Department", e.getMessage());
        }
        return arrayList;
    }

    public ArrayList<GlyMsgRow> parseGlyMsgRow(JSONObject object) {
        ArrayList<GlyMsgRow> arrayList = new ArrayList<GlyMsgRow>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new GlyMsgRow(
                        jsonObj.getString("GMsg_sid"),
                        jsonObj.getLong("GMsg_table_id"),
                        jsonObj.getString("GMsg_writer_aid"),
                        jsonObj.getString("GMsg_datetime"),
                        jsonObj.getString("GMsg_content"),
                        jsonObj.getInt("GMsg_status")
                ));
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser =>Department", e.getMessage());
        }
        return arrayList;
    }

    public ArrayList<GlyAvgRow> parseGlyAvgRow(JSONObject object) {
        ArrayList<GlyAvgRow> arrayList = new ArrayList<GlyAvgRow>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new GlyAvgRow(
                        jsonObj.getString("GAvg_sid"),
                        jsonObj.getString("GAvg_datetime"),
                        jsonObj.getInt("GAvg_bm"),
                        jsonObj.getInt("GAvg_am")
                ));
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser =>Department", e.getMessage());
        }
        return arrayList;
    }

    public ArrayList<PsnNotRow> parsePsnNotRow(JSONObject object) {
        ArrayList<PsnNotRow> arrayList = new ArrayList<PsnNotRow>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new PsnNotRow(
                        jsonObj.getLong("PsnNot_table_id"),
                        jsonObj.getInt("PsnNot_type"),
                        jsonObj.getString("PsnNot_title"),
                        jsonObj.getString("PsnNot_content"),
                        jsonObj.getString("PsnNot_sendr_name"),
                        jsonObj.getString("PsnNot_datetime"),
                        jsonObj.getInt("PsnNot_status")
                ));
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser =>Department", e.getMessage());
        }
        return arrayList;
    }

    public ArrayList<CtrNotRow> parseCtrNotRow(JSONObject object) {
        ArrayList<CtrNotRow> arrayList = new ArrayList<CtrNotRow>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new CtrNotRow(
                        jsonObj.getLong("CtrNot_table_id"),
                        jsonObj.getInt("CtrNot_type"),
                        jsonObj.getString("CtrNot_title"),
                        jsonObj.getString("CtrNot_content"),
                        jsonObj.getString("CtrNot_sendr_name"),
                        jsonObj.getString("CtrNot_datetime"),
                        jsonObj.getInt("CtrNot_status")
                ));
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser =>Department", e.getMessage());
        }
        return arrayList;
    }

    public ArrayList<FRow> parseFRow(JSONObject object) {
        ArrayList<FRow> arrayList = new ArrayList<FRow>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new FRow(
                        jsonObj.getLong("F_table_id"),
                        jsonObj.getString("F_fri_aid"),
                        jsonObj.getString("F_fri_sid"),
                        jsonObj.getInt("F_relation_flag"),
                        jsonObj.getString("F_active_datetime"),
                        jsonObj.getString("F_member_flag"),
                        jsonObj.getString("F_avatar"),
                        jsonObj.getString("F_name"),
                        jsonObj.getString("F_nickname"),
                        jsonObj.getString("F_nickname_flag"),
                        jsonObj.getInt("F_sex"),
                        jsonObj.getString("F_birthday"),
                        jsonObj.getString("F_phone"),
                        jsonObj.getString("F_email")
                ));
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser =>Department", e.getMessage());
        }
        return arrayList;
    }

    public ArrayList<FGRow> parseFGRow(JSONObject object) {
        ArrayList<FGRow> arrayList = new ArrayList<FGRow>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new FGRow(
                        jsonObj.getString("FG_group_name"),
                        jsonObj.getString("FG_fri_aid")
                ));
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser =>Department", e.getMessage());
        }
        return arrayList;
    }

    public ArrayList<CtrMsgRow> parseCtrMsgRow(JSONObject object) {
        ArrayList<CtrMsgRow> arrayList = new ArrayList<CtrMsgRow>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new CtrMsgRow(
                        jsonObj.getLong("CtrMsg_table_id"),
                        jsonObj.getInt("CtrMsg_type"),
                        jsonObj.getInt("CtrMsg_status"),
                        jsonObj.getString("CtrMsg_content"),
                        jsonObj.getString("CtrMsg_isSend"),
                        jsonObj.getString("CtrMsg_send_success_flag"),
                        jsonObj.getString("CtrMsg_datetime"),
                        jsonObj.getInt("CtrMsg_status_flag")
                ));
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser =>Department", e.getMessage());
        }
        return arrayList;
    }

    public ArrayList<SMedRow> parseSMedRow(JSONObject object) {
        ArrayList<SMedRow> arrayList = new ArrayList<SMedRow>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new SMedRow(
                        jsonObj.getString("SMed_sid"),
                        jsonObj.getLong("SMed_table_id"),
                        jsonObj.getString("SMed_datetime"),
                        jsonObj.getString("SMed_bm")
                ));
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser =>Department", e.getMessage());
        }
        return arrayList;
    }

    public ArrayList<SPayRow> parseSPayRow(JSONObject object) {
        ArrayList<SPayRow> arrayList = new ArrayList<SPayRow>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new SPayRow(
                        jsonObj.getString("SPay_sid"),
                        jsonObj.getLong("SPay_table_id"),
                        jsonObj.getString("SPay_datetime"),
                        jsonObj.getString("SPay_money"),
                        jsonObj.getString("SPay_status")
                ));
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser =>Department", e.getMessage());
        }
        return arrayList;
    }

    public ArrayList<SRcrdRow> parseSRcrdRow(JSONObject object) {
        ArrayList<SRcrdRow> arrayList = new ArrayList<SRcrdRow>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new SRcrdRow(
                        jsonObj.getString("SRcrd_sid"),
                        jsonObj.getLong("SRcrd_table_id"),
                        jsonObj.getString("SRcrd_datetime"),
                        jsonObj.getInt("SRcrd_type")
                ));
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser =>Department", e.getMessage());
        }
        return arrayList;
    }

    public ArrayList<FShrSetRow> parseFShrSetRow(JSONObject object) {
        ArrayList<FShrSetRow> arrayList = new ArrayList<FShrSetRow>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new FShrSetRow(
                        jsonObj.getString("FShrSet_fri_aid"),
                        jsonObj.getString("FShrSet_data_flag"),
                        jsonObj.getString("FShrSet_medicine_flag"),
                        jsonObj.getString("FShrSet_pay_flag"),
                        jsonObj.getString("FShrSet_report_flag"),
                        jsonObj.getString("FShrSet_record_flag"),
                        jsonObj.getString("FShrSet_location_flag")
                ));
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser =>Department", e.getMessage());
        }
        return arrayList;
    }

    public ArrayList<FShrNotSetRow> parseFShrNotSetRow(JSONObject object) {
        ArrayList<FShrNotSetRow> arrayList = new ArrayList<FShrNotSetRow>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new FShrNotSetRow(
                        jsonObj.getString("FShrNotSet_fri_aid"),
                        jsonObj.getString("FShrNotSet_data_flag"),
                        jsonObj.getString("FShrNotSet_medicine_flag"),
                        jsonObj.getString("FShrNotSet_pay_flag"),
                        jsonObj.getString("FShrNotSet_report_flag"),
                        jsonObj.getString("FShrNotSet_record_flag")
                ));
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser =>Department", e.getMessage());
        }
        return arrayList;
    }

    public ArrayList<FRecvNotSetRow> parseFRecvNotSetRow(JSONObject object) {
        ArrayList<FRecvNotSetRow> arrayList = new ArrayList<FRecvNotSetRow>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new FRecvNotSetRow(
                        jsonObj.getString("FRecvNotSet_fri_aid"),
                        jsonObj.getString("FRecvNotSet_data_flag"),
                        jsonObj.getString("FRecvNotSet_medicine_flag"),
                        jsonObj.getString("FRecvNotSet_pay_flag"),
                        jsonObj.getString("FRecvNotSet_report_flag"),
                        jsonObj.getString("FRecvNotSet_record_flag")
                ));
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser =>Department", e.getMessage());
        }
        return arrayList;
    }
}
