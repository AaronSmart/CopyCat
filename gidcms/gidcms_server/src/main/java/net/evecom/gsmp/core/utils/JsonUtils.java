/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.utils;

import com.google.gson.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;

/**
 * <P><B>Description: </B> TODO json转换工具类  </P>
 * Revision Trail: (Date/Author/Description)
 * 2016年6月7日 Json Lai CREATE
 *
 * @author Nick Lv
 * @version 1.0
 * @created 2016 /12/13 14:47:36
 */
public class JsonUtils {

    /**
     * TODO 将对象转换为json字符串
     * Revision Trail: (Date/Author/Description)
     * 2016年6月7日 Json Lai CREATE
     *
     * @param obj the obj
     * @return string string
     * @author Nick Lv
     * @created 2016 /12/13 14:47:36 To json string.
     */
    public static String toJson(Object obj) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Timestamp.class, new TimestampAdapter()).create();
        return gson.toJson(obj);
    }

    /**
     * TODO 将json字符串转为指定的对象
     * Revision Trail: (Date/Author/Description)
     * 2016年6月7日 Json Lai CREATE
     *
     * @param <T>  the type parameter
     * @param json the json
     * @param cls  the cls
     * @return t t
     * @author Nick Lv
     * @created 2016 /12/13 14:47:36 Object from json t.
     */
    public static <T> T objectFromJson(String json, Class<T> cls) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Timestamp.class, new TimestampAdapter()).create();
        return gson.fromJson(json, cls);
    }

    /**
     * json字符串取单个键值对
     * Revision Trail: (Date/Author/Description)
     * 2016年7月25日 Json Lai CREATE
     *
     * @param jsonString the json string
     * @param string     the string
     * @return string string
     * @author Nick Lv
     * @created 2016 /12/13 14:47:36 To string string.
     */
    public static String toString(String jsonString, String string) {
        String returnString = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            returnString = jsonObject.getString(string);
        } catch (JSONException e) {
            /*e.printStackTrace();*/
            return null;
        }
        return returnString;
    }

    /**
     * json字符串取单个键值对
     * Revision Trail: (Date/Author/Description)
     * 2016年7月25日 Json Lai CREATE
     *
     * @param jsonString the json string
     * @param string     the string
     * @return int int
     * @author Nick Lv
     * @created 2016 /12/13 14:47:36 To int int.
     */
    public static int toInt(String jsonString, String string) {
        int num = 0;
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            num = jsonObject.getInt(string);
        } catch (JSONException e) {
            /*e.printStackTrace();*/
            return num;
        }
        return num;
    }

    /**
     * <P><B>Description: </B> 时间转换器  </P>
     * Revision Trail: (Date/Author/Description)
     * 2016年7月29日 Timer He CREATE
     *
     * @author Nick Lv
     * @version 1.0
     * @created 2016 /12/13 14:47:36
     */
    public static class TimestampAdapter implements JsonSerializer<Timestamp>, JsonDeserializer<Timestamp> {

        @Override
        public Timestamp deserialize(JsonElement json, Type type, JsonDeserializationContext arg2)
                throws JsonParseException {
            if (json == null) {
                return null;
            } else {
                try {
                    return new Timestamp(json.getAsLong());
                } catch (Exception e) {
                    return null;
                }
            }
        }

        @Override
        public JsonElement serialize(Timestamp src, Type type, JsonSerializationContext arg2) {
            String value = "";
            if (src != null) {
                value = String.valueOf(src.getTime());
            }
            return new JsonPrimitive(value);
        }

    }

    /**
     * TODO json转map
     * Revision Trail: (Date/Author/Description)
     * 2016年8月2日 Wade CREATE
     *
     * @param jsonStr the json str
     * @return hash map
     * @author Nick Lv
     * @created 2016 /12/13 14:47:36 To hash map hash map.
     */
    public static HashMap<String, String> toHashMap(String jsonStr) {
        HashMap<String, String> data = new HashMap<String, String>();
        // 将json字符串转换成jsonObject
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(jsonStr);
            Iterator it = jsonObject.keys();
            // 遍历jsonObject数据，添加到Map对象
            while (it.hasNext()) {
                String key = String.valueOf(it.next());
                String value = (jsonObject.get(key) + "");
                data.put(key, value);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            /*e.printStackTrace();*/
            return null;
        }
        return data;
    }

}
