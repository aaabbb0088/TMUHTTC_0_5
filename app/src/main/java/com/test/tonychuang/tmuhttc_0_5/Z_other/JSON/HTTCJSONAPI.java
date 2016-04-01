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
//    private final String urlString = "http://192.168.11.92:9999/HTTCJSONAPI/Handler.ashx";
    private final String urlString = "http://10.15.2.113:9999/HTTCJSONAPI/Handler.ashx";
//    private final String urlString = "http://120.97.32.179:9999/Handler.ashx";
//    private final String urlString = "http://192.168.0.12:9999/HTTCJSONAPI/Handler.ashx";

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

    public JSONObject UpdateFriendAddNoticeTable(String aid, String lastDataTime) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateFriendAddNoticeTable");
        p.put("aid", mapObject(aid));
        p.put("lastDataTime", mapObject(lastDataTime));
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

    public JSONObject UpdateFWLevelShrPref(ArrayList<String> aids) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface", "HTTCJSONAPI");
        o.put("method", "UpdateFWLevelShrPref");
        p.put("aids", mapObject(aids));
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

    public JSONObject UpdatePressListTableData(String sid,String startDate,String endDate) throws Exception {
        JSONObject result = null;
        JSONObject o = new JSONObject();
        JSONObject p = new JSONObject();
        o.put("interface","HTTCJSONAPI");
        o.put("method", "UpdatePressListTableData");
        p.put("sid",mapObject(sid));
        p.put("startDate",mapObject(startDate));
        p.put("endDate",mapObject(endDate));
        o.put("parameters", p);
        String s = o.toString();
        String r = load(s);
        result = new JSONObject(r);
        return result;
    }
}


