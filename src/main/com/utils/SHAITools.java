package utils;

import com.google.gson.Gson;
import net.sf.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 加密工具类
 * Created by zhangxiunan on 2016/05/30.
 */
public class SHAITools {

    public static byte[] eccrypt(String info) {
        MessageDigest sha = null;
        byte[] resultBytes = null;
        try {
            sha = MessageDigest.getInstance("SHA-1");

            byte[] srcBytes = info.getBytes();
            // 使用srcBytes更新摘要
            sha.update(srcBytes);
            // 完成哈希计算，得到result
            resultBytes = sha.digest();
        } catch (NoSuchAlgorithmException e) {
        }
        return resultBytes;
    }

    public static String hexString(byte[] bytes) {
        StringBuffer hexValue = new StringBuffer();

        for (int i = 0; i < bytes.length; i++) {
            int val = ((int) bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static String getEccrypt(HashMap<String, String> map) {
        String sha1 = "";
        Object[] objs = map.keySet().toArray();
        String[] keys = new String[objs.length];
        for (int i = 0; i < objs.length; i++) {
            keys[i] = objs[i].toString();
        }
        Arrays.sort(keys, String.CASE_INSENSITIVE_ORDER);
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < keys.length; i++) {
            stringBuilder.append(keys[i])
                    .append("=")
                    .append(map.get(keys[i]));
            stringBuilder.append("&");
        }
        stringBuilder.append("secret=android");
//        stringBuilder.append("&");
//        stringBuilder.append("dateline=" + Util.getDate());
        sha1 = SHAITools.hexString(SHAITools.eccrypt(stringBuilder.toString()));
        return sha1;
    }

    public static String getEccryptObject(HashMap<String, Object> map) {
        String sha1 = "";
        Object[] objs = map.keySet().toArray();
        String[] keys = new String[objs.length];
        for (int i = 0; i < objs.length; i++) {
            keys[i] = objs[i].toString();
        }
        Arrays.sort(keys, String.CASE_INSENSITIVE_ORDER);
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < keys.length; i++) {
            stringBuilder.append(keys[i])
                    .append("=");
            Object value = map.get(keys[i]);
            if (value instanceof ArrayList) {
                stringBuilder.append(new Gson().toJson(value));
            } else {
                stringBuilder.append(value + "");
            }
            stringBuilder.append("&");
        }
        stringBuilder.append("secret=android");
//        stringBuilder.append("&");
//        stringBuilder.append("dateline=" + Util.getDate());
        sha1 = SHAITools.hexString(SHAITools.eccrypt(stringBuilder.toString()));
        return sha1;
    }


    public static String getEccrypt(HashMap<String, String> map, String time) {
        String sha1 = "";
        Object[] objs = map.keySet().toArray();
        String[] keys = new String[objs.length];
        for (int i = 0; i < objs.length; i++) {
            keys[i] = objs[i].toString();
        }
        Arrays.sort(keys, String.CASE_INSENSITIVE_ORDER);
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < keys.length; i++) {
            stringBuilder.append(keys[i])
                    .append("=")
                    .append(map.get(keys[i]));
            stringBuilder.append("&");
        }
        stringBuilder.append("secret=android");
//        stringBuilder.append("&");
//        String systemTime = time.equals("") ? Util.getDate() : time;
//        stringBuilder.append("dateline=" + systemTime);
//        Log.e("test", stringBuilder.toString());
        sha1 = SHAITools.hexString(SHAITools.eccrypt(stringBuilder.toString()));
        return sha1;
    }

    public static void main(String[] args) {
        HashMap<String,String> map = new HashMap<String,String>();
//        map.put("bank_no","622908211844887718");
//        map.put("account_type","11");
//        map.put("phone","13817504921");
//        map.put("id_card","120106198204074552");
//        map.put("realname","刘超");
        map.put("phone_code","409793");
        String sign = SHAITools.getEccrypt(map);
        System.out.println(sign);
    }
}