package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 签名认证
 *
 * @author danly.feng
 * @create 2017-12-11 11:33
 **/
public class SignUtil {

    private static final Logger logger = Logger.getLogger(SignUtil.class);

    public static String json2link(JSONObject json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, Object> params = mapper.readValue(json.toString(), Map.class);
            List<String> keys = new ArrayList<String>(params.keySet());
            Collections.sort(keys);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < keys.size(); i++) {
                String key = (String) keys.get(i);
                Object value = params.get(key);
                if ("".equals(value) || value == null
                        || key.equalsIgnoreCase("sign")
                        || key.equalsIgnoreCase("sign_type")
                        || key.equalsIgnoreCase("dateline")) {
                    continue;
                }
                sb.append(key).append("=").append(value).append("&");
            }
            if (sb.length() == 0) {
                return null;
            }
            return sb.deleteCharAt(sb.length() - 1).toString();
        } catch (IOException e) {
            logger.error("签名参数拼接失败", e);
        }
        return null;
    }

    public static boolean checkSign(JSONObject paramsJson, String clientSecret) {
        logger.debug("请求签名参数 ==> ".concat(paramsJson.toString()).concat("  secret ==> ".concat(clientSecret)));
        String linkString = json2link(paramsJson);
        if (StringUtil.isBlank(linkString)) {
            logger.error("签名失败，签名参数为null");
            return false;
        }
        linkString = linkString.concat("&secret=".concat(clientSecret));

        return StringUtil.equals(DigestUtils.sha1Hex(linkString), paramsJson.getString("sign"));
    }

    public static String generateSign (JSONObject paramsJson, String clientSecret) {
        String linkString = json2link(paramsJson);
        linkString = linkString.concat("&secret=".concat(clientSecret));
        return DigestUtils.sha1Hex(linkString);
    }

    public static void main(String[] args) {
        JSONObject json = new JSONObject();
//        json.put("name", "xiaomiMix");
//        json.put("model", "MIX");
//        json.put("system_version", "7.0");
        json.put("borrow_id", "2117");
//        String consumer_secret = "xdaa6d74ed91a14e2adbd68fb9223129";
//        System.out.println("加密前密码："+consumer_secret);
//        consumer_secret = AESCTools.getInstance().encrypt(consumer_secret);
//        System.out.println("加密后密码："+consumer_secret);
//        json.put("consumer_secret", consumer_secret);
        json.put("amount", "1000");
//        json.put("coupon_code", "62362");
//        json.put("password", "PBn119LJs4XNsg+vEvZO4Q==");
        System.out.println(generateSign(json, "android"));
    }

}
