package utils;

/*
 * 接口测试使用此类地址
 * UI测试直接读取testng.xml中的定义地址
 */
public class TestUrl {

    //    static String version = MySqlUtil.getInstance().mySqlCURD("SELECT version_code FROM app_aes_key ORDER BY key_id DESC LIMIT 1");
    static String version = "1.3.3";

    /****************************************************************************老版本****************************************************************************/

    /*
     * 前台pc测试Url
     */
    public static String getPcURL() {

        return "http://testwww.ixijian.com";
    }

    /*
     * 前台weixin测试Url
     */
    public static String getWXOldURL() {

        return "http://testweixin.ixijian.com";
    }

    /*
     * app测试Url
     */
    public static String getAppURL() {

        return "http://testapi.ixijian.com";
    }

    /*
     * 后台admin测试Url
     */
    public static String getAdminURL() {

        return "http://192.168.16.225:8080";
    }

    /*
     * 后台crm测试Url
     */
    public static String getCrmURL() {

        return "http://192.168.16.246:8080";
    }

    /**
     * 正式环境pc
     * @return
     */
    public static String getReleasedPcUrl() {
        return "https://www.ixijian.com";
    }

    /****************************************************************************新版本****************************************************************************/

    /*
     * 前台weixin测试Url
     */
    public static String getWXURL() {

        return "http://newweixin.ixijian.com";
    }

    /*
     * 玺鉴2.0接口测试地址
     */
    public static String getInterFaceNewURL() {

        return "http://newapi.ixijian.com";
    }

    /*
     * 玺鉴2.0接口测试地址带版本号
     */
    public static String getInterFaceNewURLVersion() {

        return "http://newapi.ixijian.com"  + "/" + getVersion();
    }

    /**
     * 版本号
     * @return 版本号
     */
    public static String getVersion(){
        return version;
    }

    /****************************************************************************开拍****************************************************************************/

    /**
     * 开拍测试admin
     * @return
     */
    public static String getKaiPaiAdminUrl() {

        return "http://192.168.16.205:8080";
    }

    /****************************************************************************砼车Admin****************************************************************************/

    /**
     * 正式环境admin
     * @return
     */
    public static String getTongCheAdminReleaseUrl(){

        return "http://admin.mytongche.com";
    }

    /**
     * 测试地址api
     * @return
     */
    public static String getTongCheTestUrl(){

        return "http://alpha.mytongche.com";
    }

    /****************************************************************************砼车****************************************************************************/

    /**
     * adminURL
     * @return
     */
    public static String getTongCheAdminUrl(){

//        return "https://admin.mytongche.com";
        return "https://adminbeta.mytongche.com";
//        return "http://tcweb.mytongche.com";

    }

    /**
     * apiURL
     * @return
     */
    public static String getTongCheReleaseUrl(){

//        return "https://api.mytongche.com";
        return "https://beta.mytongche.com";
//        return "http://alpha.mytongche.com";
    }


}
