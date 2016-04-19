/* JSON API for android appliation */
package com.test.tonychuang.tmuhttc_0_5.Z_other.JSON;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class HTTCJSONAPI {

    private final String urlString = "http://192.168.11.92:9999/HTTCJSONAPI/Handler.ashx";
//    private final String urlString = "http://192.168.0.12:9999//HTTCJSONAPI/Handler.ashx";
//    private final String urlString = "http://120.97.32.179:9999/Handler.ashx";
//    private final String urlString = "http://10.15.2.113:9999/HTTCJSONAPI/Handler.ashx";
//    private final String urlString = "http://192.168.0.107:9999/HTTCJSONAPI/Handler.ashx";


    private static String convertStreamToUTF8String(InputStream stream) throws IOException {
        String result = "";
        StringBuilder sb = new StringBuilder();
        try {
            InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
            char[] buffer = new char[4096];
            int readedChars = 0;
            while (readedChars != -1) {
                readedChars = reader.read(buffer);
                if (readedChars > 0)
                    sb.append(buffer, 0, readedChars);
            }
            result = sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }


    private String load(String contents) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(60000);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        OutputStreamWriter w = new OutputStreamWriter(conn.getOutputStream());
        w.write(contents);
        w.flush();
        InputStream istream = conn.getInputStream();
        String result = convertStreamToUTF8String(istream);
        return result;
    }


    private Object mapObject(Object o) {
        Object finalValue = null;
        if (o.getClass() == String.class) {
            finalValue = o;
        } else if (Number.class.isInstance(o)) {
            finalValue = String.valueOf(o);
        } else if (Date.class.isInstance(o)) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss", new Locale("en", "USA"));
            finalValue = sdf.format((Date) o);
        } else if (Collection.class.isInstance(o)) {
            Collection<?> col = (Collection<?>) o;
            JSONArray jarray = new JSONArray();
            for (Object item : col) {
                jarray.put(mapObject(item));
            }
            finalValue = jarray;
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            Method[] methods = o.getClass().getMethods();
            for (Method method : methods) {
                if (method.getDeclaringClass() == o.getClass()
                        && method.getModifiers() == Modifier.PUBLIC
                        && method.getName().startsWith("get")) {
                    String key = method.getName().substring(3);
                    try {
                        Object obj = method.invoke(o, null);
                        Object value = mapObject(obj);
                        map.put(key, value);
                        finalValue = new JSONObject(map);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return finalValue;
    }

    public JSONObject Input3Data(String pid, String pwd, String email, String phone_sid) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "Input3Data");
        p.put("pid", mapObject(pid));
        p.put("pwd", mapObject(pwd));
        p.put("email", mapObject(email));
        p.put("phone_sid", mapObject(phone_sid));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject InputNamePhome(String pid, String name, String phone) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "InputNamePhome");
        p.put("pid", mapObject(pid));
        p.put("name", mapObject(name));
        p.put("phone", mapObject(phone));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject InputRegNum(String pid, String regNum) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "InputRegNum");
        p.put("pid", mapObject(pid));
        p.put("regNum", mapObject(regNum));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject reSendreg(String pid, String email) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "reSendreg");
        p.put("pid", mapObject(pid));
        p.put("email", mapObject(email));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject frgtReSendreg(String pid, String email) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "frgtReSendreg");
        p.put("pid", mapObject(pid));
        p.put("email", mapObject(email));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject InputRegNumFrgt(String pid, String regNum) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "InputRegNumFrgt");
        p.put("pid", mapObject(pid));
        p.put("regNum", mapObject(regNum));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject changePwd(String pid, String pwd) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "changePwd");
        p.put("pid", mapObject(pid));
        p.put("pwd", mapObject(pwd));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject SignIn(String pid, String pwd, String phone_sid) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "SignIn");
        p.put("pid", mapObject(pid));
        p.put("pwd", mapObject(pwd));
        p.put("phone_sid", mapObject(phone_sid));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject SignOut(String aid, String phone_sid) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "SignOut");
        p.put("aid", mapObject(aid));
        p.put("phone_sid", mapObject(phone_sid));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdatePsnDataSetting(String aid) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdatePsnDataSetting");
        p.put("aid", mapObject(aid));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdatePsnSetting(String aid) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdatePsnSetting");
        p.put("aid", mapObject(aid));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateWLevelShrPref(String sid) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateWLevelShrPref");
        p.put("sid", mapObject(sid));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdatePressDataTable(String sid, String lastDataTime) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdatePressDataTable");
        p.put("sid", mapObject(sid));
        p.put("lastDataTime", mapObject(lastDataTime));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdatePressThumbTable(String sid, String lastDataTime) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdatePressThumbTable");
        p.put("sid", mapObject(sid));
        p.put("lastDataTime", mapObject(lastDataTime));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdatePressMsgTable(String sid, String lastDataTime) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdatePressMsgTable");
        p.put("sid", mapObject(sid));
        p.put("lastDataTime", mapObject(lastDataTime));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdatePressAvgTable(String sid, String lastDataTime) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdatePressAvgTable");
        p.put("sid", mapObject(sid));
        p.put("lastDataTime", mapObject(lastDataTime));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateGlycemiaDataTable(String sid, String lastDataTime) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateGlycemiaDataTable");
        p.put("sid", mapObject(sid));
        p.put("lastDataTime", mapObject(lastDataTime));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateGlycemiaThumbTable(String sid, String lastDataTime) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateGlycemiaThumbTable");
        p.put("sid", mapObject(sid));
        p.put("lastDataTime", mapObject(lastDataTime));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateGlycemiaMsgTable(String sid, String lastDataTime) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateGlycemiaMsgTable");
        p.put("sid", mapObject(sid));
        p.put("lastDataTime", mapObject(lastDataTime));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateGlycemiaAvgTable(String sid, String lastDataTime) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateGlycemiaAvgTable");
        p.put("sid", mapObject(sid));
        p.put("lastDataTime", mapObject(lastDataTime));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdatePersonalNoticeTable(String sid, String lastDataTime) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdatePersonalNoticeTable");
        p.put("sid", mapObject(sid));
        p.put("lastDataTime", mapObject(lastDataTime));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateCenterNoticeTable(String aid, String lastDataTime) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateCenterNoticeTable");
        p.put("aid", mapObject(aid));
        p.put("lastDataTime", mapObject(lastDataTime));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateFriendTable(String aid) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateFriendTable");
        p.put("aid", mapObject(aid));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateFriendGroupTable(String aid) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateFriendGroupTable");
        p.put("aid", mapObject(aid));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateCenterMessageTable(String aid, String lastDataTime) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateCenterMessageTable");
        p.put("aid", mapObject(aid));
        p.put("lastDataTime", mapObject(lastDataTime));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateServiceMedicineTable(String sid, String lastDataTime) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateServiceMedicineTable");
        p.put("sid", mapObject(sid));
        p.put("lastDataTime", mapObject(lastDataTime));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateServicePayTable(String sid, String lastDataTime) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateServicePayTable");
        p.put("sid", mapObject(sid));
        p.put("lastDataTime", mapObject(lastDataTime));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateServiceRecordTable(String sid, String lastDataTime) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateServiceRecordTable");
        p.put("sid", mapObject(sid));
        p.put("lastDataTime", mapObject(lastDataTime));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateFriendShareSettingTable(String aid) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateFriendShareSettingTable");
        p.put("aid", mapObject(aid));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateFriendShareNoticeSettingTable(String aid) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateFriendShareNoticeSettingTable");
        p.put("aid", mapObject(aid));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateFriendReceiveNoticeSettingTable(String aid) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateFriendReceiveNoticeSettingTable");
        p.put("aid", mapObject(aid));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateFriendShareDataFlagTable(String aid) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateFriendShareDataFlagTable");
        p.put("aid", mapObject(aid));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateFriendAddNoticeTable(String aid) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateFriendAddNoticeTable");
        p.put("aid", mapObject(aid));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateFriendNoticeTable(String aid, String lastDataTime) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateFriendNoticeTable");
        p.put("aid", mapObject(aid));
        p.put("lastDataTime", mapObject(lastDataTime));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateFWLevelShrPref(ArrayList<String> sids) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateFWLevelShrPref");
        p.put("sids", mapObject(sids));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateFriPressDataTable(ArrayList<String> sids, ArrayList<String> LastTimes) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateFriPressDataTable");
        p.put("sids", mapObject(sids));
        p.put("LastTimes", mapObject(LastTimes));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateFriGlycemiaDataTable(ArrayList<String> sids, ArrayList<String> LastTimes) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateFriGlycemiaDataTable");
        p.put("sids", mapObject(sids));
        p.put("LastTimes", mapObject(LastTimes));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdatePressAvgdraweData(String sid, String startDate, String endDate) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdatePressAvgdraweData");
        p.put("sid", mapObject(sid));
        p.put("startDate", mapObject(startDate));
        p.put("endDate", mapObject(endDate));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdatePressListTableData(String sid, String startDate, String endDate) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdatePressListTableData");
        p.put("sid", mapObject(sid));
        p.put("startDate", mapObject(startDate));
        p.put("endDate", mapObject(endDate));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateGlycemiaAvgdraweData(String sid, String startDate, String endDate) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateGlycemiaAvgdraweData");
        p.put("sid", mapObject(sid));
        p.put("startDate", mapObject(startDate));
        p.put("endDate", mapObject(endDate));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateGlycemiaListTableData(String sid, String startDate, String endDate) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateGlycemiaListTableData");
        p.put("sid", mapObject(sid));
        p.put("startDate", mapObject(startDate));
        p.put("endDate", mapObject(endDate));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateMedicineListTableData(String sid, String startDate, String endDate) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateMedicineListTableData");
        p.put("sid", mapObject(sid));
        p.put("startDate", mapObject(startDate));
        p.put("endDate", mapObject(endDate));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateRecordListTableData(String sid, String startDate, String endDate) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateRecordListTableData");
        p.put("sid", mapObject(sid));
        p.put("startDate", mapObject(startDate));
        p.put("endDate", mapObject(endDate));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject pressThumbPlus(String tableId, String aid) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "pressThumbPlus");
        p.put("tableId", mapObject(tableId));
        p.put("aid", mapObject(aid));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject glycemiaThumbPlus(String tableId, String aid) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "glycemiaThumbPlus");
        p.put("tableId", mapObject(tableId));
        p.put("aid", mapObject(aid));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject WritePressMsg(String PMsg_table_id, String PMsg_sid, String PMsg_writer_aid,
                                    String PMsg_datetime, String PMsg_content,
                                    String PMsg_status_flag) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "WritePressMsg");
        p.put("PMsg_table_id", mapObject(PMsg_table_id));
        p.put("PMsg_sid", mapObject(PMsg_sid));
        p.put("PMsg_writer_aid", mapObject(PMsg_writer_aid));
        p.put("PMsg_datetime", mapObject(PMsg_datetime));
        p.put("PMsg_content", mapObject(PMsg_content));
        p.put("PMsg_status_flag", mapObject(PMsg_status_flag));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject WriteGlycemiaMsg(String GMsg_table_id, String GMsg_sid, String GMsg_writer_aid,
                                       String GMsg_datetime, String GMsg_content,
                                       String GMsg_status_flag) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "WriteGlycemiaMsg");
        p.put("GMsg_table_id", mapObject(GMsg_table_id));
        p.put("GMsg_sid", mapObject(GMsg_sid));
        p.put("GMsg_writer_aid", mapObject(GMsg_writer_aid));
        p.put("GMsg_datetime", mapObject(GMsg_datetime));
        p.put("GMsg_content", mapObject(GMsg_content));
        p.put("GMsg_status_flag", mapObject(GMsg_status_flag));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject SearchAddingFriend(String id, String addWayFlag) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "SearchAddingFriend");
        p.put("id", mapObject(id));
        p.put("addWayFlag", mapObject(addWayFlag));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject InviteAddingFriend(String aid, String friAid, String addWayFlag) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "InviteAddingFriend");
        p.put("aid", mapObject(aid));
        p.put("friAid", mapObject(friAid));
        p.put("addWayFlag", mapObject(addWayFlag));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject AgreeAddingFriend(String aid, String friAid) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "AgreeAddingFriend");
        p.put("aid", mapObject(aid));
        p.put("friAid", mapObject(friAid));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateNewFWLevelShrPref(String friSid) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateNewFWLevelShrPref");
        p.put("friSid", mapObject(friSid));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateNewFPressDataTable(String friSid, String date) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateNewFPressDataTable");
        p.put("friSid", mapObject(friSid));
        p.put("date", mapObject(date));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateNewFGlycemiaDataTable(String friSid, String date) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateNewFGlycemiaDataTable");
        p.put("friSid", mapObject(friSid));
        p.put("date", mapObject(date));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateNewFriendShareSettingTable(String aid, String friAid) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateNewFriendShareSettingTable");
        p.put("aid", mapObject(aid));
        p.put("friAid", mapObject(friAid));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateNewFriendReceiveNoticeSettingTable(String aid,String friAid) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","HTTCJSONAPI");
        o.put("method", "UpdateNewFriendReceiveNoticeSettingTable");
        p.put("aid",mapObject(aid));
        p.put("friAid",mapObject(friAid));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject UpdateNewFriendShareDataFlagTable(String aid,String friAid) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","HTTCJSONAPI");
        o.put("method", "UpdateNewFriendShareDataFlagTable");
        p.put("aid",mapObject(aid));
        p.put("friAid",mapObject(friAid));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject RefuseAddingFriend(String aid, String friAid) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "RefuseAddingFriend");
        p.put("aid", mapObject(aid));
        p.put("friAid", mapObject(friAid));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject AddFriendGroup(String aid, String groupName, String friAids) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "AddFriendGroup");
        p.put("aid", mapObject(aid));
        p.put("groupName", mapObject(groupName));
        p.put("friAids", mapObject(friAids));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject EditFriendGroup(String aid, String beforeChangeName, String afterChangeName, String friAids) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "EditFriendGroup");
        p.put("aid", mapObject(aid));
        p.put("beforeChangeName", mapObject(beforeChangeName));
        p.put("afterChangeName", mapObject(afterChangeName));
        p.put("friAids", mapObject(friAids));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject DeleteFriendGroup(String aid, String groupName) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "DeleteFriendGroup");
        p.put("aid", mapObject(aid));
        p.put("groupName", mapObject(groupName));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject DeleteFriend(String aid, String friAid) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "DeleteFriend");
        p.put("aid", mapObject(aid));
        p.put("friAid", mapObject(friAid));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject EditFriendNickName(String aid, String friAid, String friNickName) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "EditFriendNickName");
        p.put("aid", mapObject(aid));
        p.put("friAid", mapObject(friAid));
        p.put("friNickName", mapObject(friNickName));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject EditFriendShrRecvSetting(String aid,String friAid,String ShrSettingSameFlag,
                                               ArrayList<String> afFriShrSetting,
                                               String RecSettingSameFlag,
                                               ArrayList<String> afFriRecSetting) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","HTTCJSONAPI");
        o.put("method", "EditFriendShrRecvSetting");
        p.put("aid",mapObject(aid));
        p.put("friAid",mapObject(friAid));
        p.put("ShrSettingSameFlag",mapObject(ShrSettingSameFlag));
        p.put("afFriShrSetting",mapObject(afFriShrSetting));
        p.put("RecSettingSameFlag",mapObject(RecSettingSameFlag));
        p.put("afFriRecSetting",mapObject(afFriRecSetting));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject RestoreDelFriAnd(String aid,ArrayList<String> friAids) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","HTTCJSONAPI");
        o.put("method", "RestoreDelFriAnd");
        p.put("aid",mapObject(aid));
        p.put("friAids",mapObject(friAids));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject EditPsnNickName(String aid,String nickName) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","HTTCJSONAPI");
        o.put("method", "EditPsnNickName");
        p.put("aid",mapObject(aid));
        p.put("nickName",mapObject(nickName));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject EditPsnSex(String aid,String sex) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","HTTCJSONAPI");
        o.put("method", "EditPsnSex");
        p.put("aid",mapObject(aid));
        p.put("sex",mapObject(sex));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject EditPsnBirthday(String aid,String birthday) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","HTTCJSONAPI");
        o.put("method", "EditPsnBirthday");
        p.put("aid",mapObject(aid));
        p.put("birthday",mapObject(birthday));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject EditPsnPwd(String aid,String pwd,String email) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","HTTCJSONAPI");
        o.put("method", "EditPsnPwd");
        p.put("aid",mapObject(aid));
        p.put("pwd",mapObject(pwd));
        p.put("email",mapObject(email));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject EditPsnNotSetting(String aid,ArrayList<String> settings) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","HTTCJSONAPI");
        o.put("method", "EditPsnNotSetting");
        p.put("aid",mapObject(aid));
        p.put("settings",mapObject(settings));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }

    public JSONObject EditPsnGPSSetting(String aid,String gpsSetting) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","HTTCJSONAPI");
        o.put("method", "EditPsnGPSSetting");
        p.put("aid",mapObject(aid));
        p.put("gpsSetting",mapObject(gpsSetting));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }
}


