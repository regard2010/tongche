package utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESCTools {
    private String version = "1.3.3";
    /*
     * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
     */
    private String AD_sKey = MySqlUtil.getInstance().mySqlCURD("SELECT code FROM app_aes_key WHERE client_secret=\"AD\" AND version_code = '" + version + "' ORDER BY key_id DESC LIMIT 1");//安卓key，可自行修改
    private String IOS_sKey = MySqlUtil.getInstance().mySqlCURD("SELECT code FROM app_aes_key WHERE client_secret=\"IOS\" AND version_code = '" + version + "' ORDER BY key_id DESC LIMIT 1");//iosKey
    private String WX_sKey = MySqlUtil.getInstance().mySqlCURD("SELECT code FROM app_aes_key WHERE client_secret=\"WX\" AND version_code = '" + version + "' ORDER BY key_id DESC LIMIT 1");
    private String PC_sKey = MySqlUtil.getInstance().mySqlCURD("SELECT code FROM app_aes_key WHERE client_secret=\"PC\" AND version_code = '" + version + "' ORDER BY key_id DESC LIMIT 1");
    //    private String ivParameter = "0392039203920300";//偏移量,可自行修改
    private String ivParameter = "ac731dfb7ddd7106";//偏移量,可自行修改

    private static AESCTools instance = null;

    private AESCTools() {
    }

    public static AESCTools getInstance() {
        if (instance == null)
            instance = new AESCTools();
        return instance;
    }


    public static String Encrypt(String encData, String secretKey, String vector) throws Exception {

        if (secretKey == null) {
            return null;
        }
        if (secretKey.length() != 16) {
            return null;
        }
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = secretKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(vector.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(encData.getBytes("utf-8"));
        return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码。
    }


    // ios加密
    public String encryptIOS(String sSrc) {
        Cipher cipher = null;
        byte[] encrypted = null;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = IOS_sKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
            return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码。
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    // ad加密
    public String encryptAD(String sSrc) {
        Cipher cipher = null;
        byte[] encrypted = null;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = AD_sKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
            return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码。
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    // wx加密
    public String encryptWX(String sSrc) {
        Cipher cipher = null;
        byte[] encrypted = null;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = WX_sKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
            return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码。
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    // wx加密
    public String encryptPC(String sSrc) {
        Cipher cipher = null;
        byte[] encrypted = null;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = PC_sKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
            return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码。
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    // ios解密
    public String decryptIOS(String sSrc) throws Exception {
        try {
            byte[] raw = IOS_sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }

    // ad解密
    public String decryptAD(String sSrc) throws Exception {
        try {
            byte[] raw = AD_sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }

    public String decrypt(String sSrc, String key, String ivs) throws Exception {
        try {
            byte[] raw = key.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivs.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }

    public static String encodeBytes(byte[] bytes) {
        StringBuffer strBuf = new StringBuffer();

        for (int i = 0; i < bytes.length; i++) {
            strBuf.append((char) (((bytes[i] >> 4) & 0xF) + ((int) 'a')));
            strBuf.append((char) (((bytes[i]) & 0xF) + ((int) 'a')));
        }

        return strBuf.toString();
    }

    /**
     * 加密
     * @param toSercetSql
     * @return
     */
    public static String AESAdd(String toSercetSql){
        String AesToSercetSql = "SELECT to_base64(AES_ENCRYPT('" + toSercetSql + "', '8e11000c294ed406'));";
        String AesToSercet = MySqlUtil.getInstance().mySqlCURD_URI("ixijian_dev_encrypt",AesToSercetSql);
        return AesToSercet;
    }

    /**
     * 解密
     * @param toSercetSql
     * @return
     */
    public static String AESDecrypt(String toSercetSql){
        String AesDecryptSercetSql = "SELECT cast(aes_decrypt(from_base64('" + toSercetSql + "'),'8e11000c294ed406') as char );";
        String AesDecryptSercet = MySqlUtil.getInstance().mySqlCURD_URI("ixijian_dev_encrypt",AesDecryptSercetSql);
        return AesDecryptSercet;
    }

    public static void main(String[] args) {
        String pw = "123456";
        System.out.println("IOS加密结果：" + AESCTools.getInstance().encryptIOS(pw));
        System.out.println("AD加密结果：" + AESCTools.getInstance().encryptAD(pw));
        System.out.println("WX加密结果：" + AESCTools.getInstance().encryptWX(pw));
        System.out.println("PC加密结果：" + AESCTools.getInstance().encryptPC(pw));
    }
}
