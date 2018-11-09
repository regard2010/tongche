package tongche_test.api;

import net.sf.json.JSONObject;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.InterFaceUtil;
import utils.MySqlUtil;
import utils.TestUrl;

import static utils.InterFaceUtil.sendPostUrl;

public class App_Api_001_Success_Test extends MySqlUtil{

    //登录
    @Test
    @Parameters({"userName"})
    public void userLogin(String userName) {
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/user/login","securityCode=157935&tel=" + userName + "");
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","登录成功");
    }

    //登录获取验证码
    @Test
    @Parameters({"userName"})
    public void userGetSecurityCode(String userName) {
        JSONObject jsonObject1 = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/user/getSecurityCode","type=1&tel=" + userName + "");
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject1,"200","发送成功");
        JSONObject jsonObject2 = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/user/getSecurityCode","type=1&tel=" + userName + "");
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject2,"-209","操作频繁，请稍后再试");
    }

    //登录成功后APP端添加签名
    public static String AppSign(String user) {
        String userIdSql = "SELECT `id` from `user` where `tel`='" + user + "';";
        String userId = MySqlUtil.getInstance().mySqlCURD(userIdSql);
        String sign = "&urid="+userId+"&token=123";
        return sign;
    }

    //获取用户的userId
    public static String getUserId(String user){
        String userIdSql = "SELECT `id` from `user` where `tel`='" + user + "';";
        String userId = MySqlUtil.getInstance().mySqlCURD(userIdSql);
        return userId;
    }

    //驾驶员首页,不同状态全部要列一遍驾驶员状态
    @Test
    @Parameters({"mixDriver"})
    public void driverHomePage(String mixDriver) {
        userLogin(mixDriver);
        String userId = getUserId(mixDriver);
        String sign = AppSign(mixDriver);
//        String url = TestUrl.getTongCheReleaseUrl() + "/driver/homePage?userId=" + userId + "" + sign;
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/driver/homePage","userId=" + userId + "" + sign);
//        InterFaceUtil.getInstance().AssertGet(url,"123");
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"20107","获取成功");
    }

    //可用车辆列表
    @Test
    @Parameters({"mixDriver"})
    public void vehicleTrucks(String mixDriver) {
        userLogin(mixDriver);
        String userId = getUserId(mixDriver);
        String sign = AppSign(mixDriver);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/vehicle/trucks","userId=" + userId + "" + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","获取成功");
    }

    //上班和下班匹配使用
    @Test
    @Parameters({"noOrderDriver"})
    public void driverOnwork(String noOrderDriver) {
        userLogin(noOrderDriver);
        String userId = getUserId(noOrderDriver);
        String sign = AppSign(noOrderDriver);
        String vehicleIdSql = "SELECT `vehicleId` from `drivervehicle` where `driverId` = (SELECT `id` from `driver` where `accountId` = " + userId + ") and `selected` = 1;";
        String vehicleId = MySqlUtil.getInstance().mySqlCURD(vehicleIdSql);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/driver/onwork","userId=" + userId + "&vehicleId="+ vehicleId + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","上班成功");
    }

    //下班和上班匹配使用
    @Test
    @Parameters({"noOrderDriver"})
    public void driverOffwork(String noOrderDriver) {
        userLogin(noOrderDriver);
        String userId = getUserId(noOrderDriver);
        String sign = AppSign(noOrderDriver);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/driver/offwork","userId=" + userId + "&keepin=1" + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","下班成功");
    }

    //排队，排队需要把账号先置为上班状态
    @Test
    @Parameters({"noOrderDriver"})
    public void driverOnqueue(String noOrderDriver) {
        driverOnwork(noOrderDriver);
        String userId = getUserId(noOrderDriver);
        String sign = AppSign(noOrderDriver);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/driver/onqueue","userId=" + userId + "&lng=121.514751&lat=31.314329" + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","排队成功");
    }

    //取消排队，取消排队后，将账号置为下班状态
    @Test
    @Parameters({"noOrderDriver"})
    public void driverOffqueue(String noOrderDriver) {
        String userId = getUserId(noOrderDriver);
        String sign = AppSign(noOrderDriver);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/driver/offqueue","userId=" + userId + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","退出排队成功");
        driverOffwork(noOrderDriver);
    }

    //排队列表，先让搅拌车驾驶员上班，然后进入排队，然后调用排队列表接口，调用完成后，将账户置为下班，可扩展到（1、搅拌车驾驶员和泵车驾驶员；2、发货单位和租赁单位）
    @Test
    @Parameters({"noOrderDriver"})
    public void vehicleGetVehicleQueue(String noOrderDriver) {
        driverOnqueue(noOrderDriver);
        String userId = getUserId(noOrderDriver);
        String sign = AppSign(noOrderDriver);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/vehicle/getVehicleQueue","userId=" + userId + "&pageSize=10&pageIndex=1" + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","获取成功");
        driverOffqueue(noOrderDriver);
    }

    //获取用户信息（管理员），还有普通员工
    @Test
    @Parameters({"userName"})
    public void userGetUserInfoForAdministrator(String userName) {
        userLogin(userName);
        String userId = getUserId(userName);
        String sign = AppSign(userName);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/user/getUserInfo","userId=" + userId + "&tel="+ userName + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","ok");
    }

    //获取用户信息（驾驶员）可扩展到（1、搅拌车驾驶员和泵车驾驶员；2、发货单位和租赁单位）
    @Test
    @Parameters({"mixDriver"})
    public void userGetUserInfoForDriver(String mixDriver) {
        userLogin(mixDriver);
        String userId = getUserId(mixDriver);
        String sign = AppSign(mixDriver);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/user/getUserInfo","userId=" + userId + "&tel="+ mixDriver + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","ok");
    }

    //获取收货单位来车
    @Test
    @Parameters({"receiveUserName"})
    public void vehicleGetCarBySh(String receiveUserName) {
        userLogin(receiveUserName);
        String userId = getUserId(receiveUserName);
        String sign = AppSign(receiveUserName);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/vehicle/getCarBySh","userId=" + userId + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","成功");
    }

    //更换车辆成功
    @Test
    @Parameters({"noOrderDriver"})
    public void driverChangeVehicleSuccess(String noOrderDriver) {
        String userId = getUserId(noOrderDriver);
        String sign = AppSign(noOrderDriver);
        String vehicleIdSql = "SELECT `vehicleId` from `drivervehicle` where `driverId` = (SELECT `id` from `driver` where `accountId` = " + userId + ") and `selected` = 0 limit 1;";
        String vehicleId = MySqlUtil.getInstance().mySqlCURD(vehicleIdSql);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/driver/changeVehicle","userId=" + userId + "&vehicleId=" + vehicleId + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","更换车辆成功");
    }

    //更换车辆失败，现在后台没有停用功能，不会出现停用车辆
    @Test
    @Parameters({"noOrderDriver"})
    public void driverChangeVehicleFalse(String noOrderDriver) {
        String userId = getUserId(noOrderDriver);
        String sign = AppSign(noOrderDriver);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/driver/changeVehicle","userId=" + userId + "&vehicleId=34" + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"500","车辆无效!");
    }

    //租赁单位车辆数字
    @Test
    @Parameters({"rentUserName"})
    public void vehicleGetCarCountByZlApp(String rentUserName) {
        String userId = getUserId(rentUserName);
        String sign = AppSign(rentUserName);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/vehicle/getCarCountByZlApp","userId=" + userId + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","成功");
    }

    //任务详情，租赁驾驶员，查看发货单位任务详情
    @Test
    @Parameters({"noOrderDriver"})
    public void taskGetByIdForRentDriver(String noOrderDriver) {
        String userId = getUserId(noOrderDriver);
        String sign = AppSign(noOrderDriver);
        String taskIdSql = "SELECT `id` from `task` where `deliverCompanyId` = (select `deliverCompanyId` from `user` where `id` = '" + userId + "') order by `id` desc limit 1;";
        String taskId = MySqlUtil.getInstance().mySqlCURD(taskIdSql);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/task/getById","userId=" + userId + "&id=" + taskId + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"201","无权查看");
    }

    //任务详情，发货单位管理员，查看发货单位任务详情
    @Test
    @Parameters({"userName"})
    public void taskGetByIdForSendAdministrator(String userName) {
        String userId = getUserId(userName);
        String sign = AppSign(userName);
        String taskIdSql = "SELECT `id` from `task` where `deliverCompanyId` = (select `companyId` from `user` where `id` = '" + userId + "') order by `id` desc limit 1;";
        String taskId = MySqlUtil.getInstance().mySqlCURD(taskIdSql);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/task/getById","userId=" + userId + "&id=" + taskId + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","获取成功");
    }

    //获取推荐员的用户信息
    @Test
    @Parameters({"userName"})
    public void userRecommendUserInfo(String userName) {
        String userId = getUserId(userName);
        String sign = AppSign(userName);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/user/recommendUserInfo","recommendUserId=" + userId + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","获取成功");
    }

    //警告报表
    @Test
    @Parameters({"rentUserName"})
    public void alertList(String rentUserName) {
        String userId = getUserId(rentUserName);
        String sign = AppSign(rentUserName);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/alert/list","userId=" + userId + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","ok");
    }

    //上报警告，插入操作，需要进行清除数据，但是数据库权限未放开，暂时不处理，保留
    @Test
    @Parameters({"mixDriver"})
    public void alertStore(String mixDriver) {
        String userId = getUserId(mixDriver);
        String sign = AppSign(mixDriver);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/alert/store","userId=" + userId + "&type=1&lat=30.123&lng=121.321&locale=自动接口地址&speed=120" + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","ok");
        //TODO 当获得数据库写权限时,可以将添加的记录删除，保证测试数据不会影响正式使用
    }



    //查询公司
    @Test
    @Parameters({"mixDriver"})
    public void companyFindByName(String mixDriver) {
        String sign = AppSign(mixDriver);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/company/findByName","companyType=0&companyName=上海建工" + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","查询成功");
    }

    //泵车签收信息，已经泵送过的小票不能继续修改
    @Test
    @Parameters({"mixDriver"})
    public void itemOrderPreparePump(String mixDriver) {
        String userId = getUserId(mixDriver);
        String sign = AppSign(mixDriver);
        String itemOrderIdSql = "SELECT `id` from `itemorder` where `driverId` = (select `id` from `driver` where `accountId` = " + userId + ") order by `id` desc limit 1;";
        String itemOrderId = MySqlUtil.getInstance().mySqlCURD(itemOrderIdSql);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/itemOrder/preparePump","userId="+ userId + "&itemOrderId=" + itemOrderId + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"500","不能修改!");
    }

    //TODO 需要自己写一个解密方法
    //车辆二维码内容
    //参数由vehicleId=615&time=1530268870&signature=签名规则如下
    //xDzdMT9+bEv7z44y38a2akJd1V1kIGTMhZUalJYCM58=和时间戳 md5
    @Test
    @Parameters({"mixDriver"})
    public void backendQrforvehicle(String mixDriver) {
        String sign = AppSign(mixDriver);
        String vehicleIdSql = "SELECT `id` from `vehicle` order by `id` desc limit 1;";
        String vehicleId = MySqlUtil.getInstance().mySqlCURD(vehicleIdSql);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/backend/qrforvehicle","vehicleId=" + vehicleId + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","成功");
    }









    /****************************************************************************发货单位****************************************************************************/

    //警告
    @Test
    @Parameters({"mixDriver2"})
    public void alertInterfacciami(String mixDriver2){
        String sign = AppSign(mixDriver2);
        String vehicleIdSql = "SELECT `id` from `vehicle` where `currentDriver` = (select `accountId` from `driver` where `accountId`=(select `id` from `user` where `tel`='" + mixDriver2 + "'));";
        String vehicleId = MySqlUtil.getInstance().mySqlCURD(vehicleIdSql);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/alert/interfacciami","vehicleId=" + vehicleId + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","查询成功");
    }

    //车辆详情
    @Test
    @Parameters({"mixDriver2"})
    public void vehicleGetVehicleDetail(String mixDriver2){
        String sign = AppSign(mixDriver2);
        String vehicleIdSql = "SELECT `id` from `vehicle` where `currentDriver` = (select `accountId` from `driver` where `accountId`=(select `id` from `user` where `tel`='" + mixDriver2 + "'));";
        String vehicleId = MySqlUtil.getInstance().mySqlCURD(vehicleIdSql);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/vehicle/getVehicleDetail","id=" + vehicleId + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","获取车辆详情成功");
    }

    //车辆加油记录
    @Test
    @Parameters({"mixDriver2"})
    public void vehicleFueledGetVehicleFuelRecord(String mixDriver2){
        String vehicleIdSql = "SELECT `id` from `vehicle` where `currentDriver` = (select `accountId` from `driver` where `accountId`=(select `id` from `user` where `tel`='" + mixDriver2 + "'));";
        String vehicleId = MySqlUtil.getInstance().mySqlCURD(vehicleIdSql);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/vehicleFueled/getVehicleFuelRecord","id=" + vehicleId +"&pageIndex=1&pageSize=10");
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","成功");
    }

    //发货单位地图页车辆下的最近小票
    @Test
    @Parameters({"mixOrderDriver"})
    public void vehicleNearItemOrder(String mixOrderDriver){
        String sign = AppSign(mixOrderDriver);
        String vehicleIdSql = "SELECT `id` from `vehicle` where `currentDriver` = (select `accountId` from `driver` where `accountId`=(select `id` from `user` where `tel`='" + mixOrderDriver + "'));";
        String vehicleId = MySqlUtil.getInstance().mySqlCURD(vehicleIdSql);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/vehicle/nearItemOrder","vehicleId=" + vehicleId +"&pageIndex=1&pageSize=10" +sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","成功");
    }

    //发货单位地图页所有车辆
    @Test
    @Parameters({"userName"})
    public void vehicleMapVehicleFh(String userName){
        String userId = getUserId(userName);
        String sign = AppSign(userName);
        String companyIdSql = "SELECT `companyId` from `user` where `id` = '"+ userId +"';";
        String companyId = MySqlUtil.getInstance().mySqlCURD(companyIdSql);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/vehicle/mapVehicleFh","userId=" + userId +"&companyId=" + companyId +sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","成功");
    }

    //发货单位地图页车辆列表
    @Test
    @Parameters({"userName"})
    public void vehicleMapVehicleListByFh(String userName) {
        String userId = getUserId(userName);
        String sign = AppSign(userName);
        String companyIdSql = "SELECT `companyId` from `user` where `id` = '"+ userId +"';";
        String companyId = MySqlUtil.getInstance().mySqlCURD(companyIdSql);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/vehicle/mapVehicleListByFh","userId=" + userId +"&companyId=" + companyId + "&pageIndex=1&pageSize=10" +sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","成功");
    }

    //任务车辆队列
    @Test
    @Parameters({"mixDriver"})
    public void taskVehicleQueue(String mixDriver) {
        String userId = getUserId(mixDriver);
        String sign = AppSign(mixDriver);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/task/vehicleQueue","userId=" + userId + "&pageIndex=1&pageSize=10" + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","成功");
    }

    //发货单位车辆数量
    @Test
    @Parameters({"userName"})
    public void vehicleGetCarCountByFhApp(String userName) {
        String userId = getUserId(userName);
        String sign = AppSign(userName);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/vehicle/getCarCountByFhApp","userId=" + userId + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","成功");
    }

    //地图模式，车辆列表 状态有3个：1、work任务车辆；2、queue排队车辆；3、empty空闲车辆
    @Test
    @Parameters({"userName"})
    public void vehicleGetCarByFhApp(String userName) {
        userLogin(userName);
        String userId = getUserId(userName);
        String sign = AppSign(userName);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/vehicle/getCarByFhApp","userId=" + userId + "&type=work" + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","成功");
    }


    /****************************************************************************驾驶员****************************************************************************/

    //驾驶员签收
    @Test
    public static  void itemOrderUpdateItem(){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>自动签收开始<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        for(int i = 0 ; i <= 9 ; i++){
            String userName = "1789999996" + i;
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>1789999996" + i + ":签收开始<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            JSONObject jsonObject1 = sendPostUrl(TestUrl.getTongCheTestUrl() + "/user/login","securityCode=157935&tel=" + userName + "");
            InterFaceUtil.getInstance().AssertJSONPost(jsonObject1,"200","登录成功");
            String userId = getUserId(userName);
            String sign = AppSign(userName);
            String itemOrderIdSql = "SELECT `id` from `itemorder` where `driverId` = (select `id` from `driver` where `accountId` = '" + userId + "') order by id desc limit 1;";
            String itemOrderId = MySqlUtil.getInstance().mySqlCURD(itemOrderIdSql);
            JSONObject jsonObject2 = sendPostUrl(TestUrl.getTongCheTestUrl() + "/itemOrder/updateItem",
                    "userId=" + userId + "&itemOrderId=" + itemOrderId + "&signImgId=&reasonId=&sign=1&hcQuantity=&qrQuantity=18&syQuantity=&remarks=" + sign);
            InterFaceUtil.getInstance().AssertJSONPost(jsonObject2,"200","ok");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>1789999996" + i + ":签收完毕<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        }
    }


/****************************************************************************员工管理PHP_更新于2018.9.13****************************************************************************/

    //获取司机列表
    @Test
    @Parameters({"userName"})
    public void userGetdriverlist(String userName){
        String userId = getUserId(userName);
        String sign = AppSign(userName);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/user/getdriverlist","userId=" + userId + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","成功");
    }



    //新增司机
    @Test
    @Parameters({"rentUserName","newUser"})
    public void userAddDriver(String rentUserName,String newUser){
        userLogin(rentUserName);
        String userId = getUserId(rentUserName);
        String sign = AppSign(rentUserName);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/user/adddriver","userId=" + userId + "&driverPhone=" + newUser + "&userName=自动生成&img=default/default.png" + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","成功");
    }

    //设置用户角色和用户姓名
    @Test
    @Parameters({"newUser"})
    public void userSetUserType(String newUser){
        userLogin(newUser);
        String userId = getUserId(newUser);
        String sign = AppSign(newUser);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/user/setusertype","id=" + userId + "&userName=自动生成&userType=1" + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","成功");
    }

/****************************************************************************权限管理PHP_更新于2018.9.13****************************************************************************/

    //设置用户角色和用户姓名（new:权限管理）
    @Test
    @Parameters({"userName"})
    public void userListmembersFaHuo(String userName){
        String userId = getUserId(userName);
        String sign = AppSign(userName);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheTestUrl() + "/user/listmembers","userId=" + userId + "&backend=1" + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","成功");
    }

    //设置用户角色和用户姓名（new:权限管理）
    @Test
    @Parameters({"rentUserName"})
    public void userListmembersZuLin(String rentUserName){
        String userId = getUserId(rentUserName);
        String sign = AppSign(rentUserName);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheTestUrl() + "/user/listmembers","userId=" + userId + "&backend=1" + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","成功");
    }

    //设置用户角色和用户姓名（new:权限管理）
    @Test
    @Parameters({"receiveUserName"})
    public void userListmembersShouHuo(String receiveUserName){
        String userId = getUserId(receiveUserName);
        String sign = AppSign(receiveUserName);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheTestUrl() + "/user/listmembers","userId=" + userId + "&backend=1" + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","成功");
    }

    //设置用户角色和用户姓名（new:权限管理）
    @Test
    @Parameters({"noOrderDriver"})
    public void userListmembersDriver(String noOrderDriver){
        String userId = getUserId(noOrderDriver);
        String sign = AppSign(noOrderDriver);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheTestUrl() + "/user/listmembers","userId=" + userId + "&backend=1" + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"500","用户ID错误");
    }

/****************************************************************************个人中心PHP_更新于2018.9.13****************************************************************************/

    //获取个人信息以及本单位信息，发货管理员
    @Test
    @Parameters({"userName"})
    public void userProfile(String userName){
        userLogin(userName);
        String userId = getUserId(userName);
        String sign = AppSign(userName);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/user/profile","userId=" + userId + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","ok");
    }

    //用户登出
    @Test
    @Parameters({"userName"})
    public void userLogout(String userName){
        userLogin(userName);
        String userId = getUserId(userName);
        String sign = AppSign(userName);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/user/logout","userId=" + userId + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","成功");
    }


    //用户更新个人信息
    @Test
    @Parameters({"userName"})
    public void userUpdateinfo(String userName){
        userLogin(userName);
        String userId = getUserId(userName);
        String sign = AppSign(userName);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/user/updateinfo","userId=" + userId + "&userName=&deviceId=&deviceType=1" + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","成功");
    }

/****************************************************************************单位管理PHP_更新于2018.9.14****************************************************************************/

    //获取本单位的合作单位列表
    @Test
    @Parameters({"userName"})
    public void contactGetlist(String userName){
        userLogin(userName);
        String userId = getUserId(userName);
        String sign = AppSign(userName);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/contact/getlist","userId=" + userId + "&companyType=" + "0" + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","成功");
    }


    //录入新车
    @Test
    @Parameters({"rentUserName"})
    public void addVertical(String rentUserName){
        userLogin(rentUserName);
        String userId = getUserId(rentUserName);
        String sign = AppSign(rentUserName);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/vehicle/add","userId=" + userId + "&maxCapacity=25&drivingLicense=&carLicenseNo=%E6%B2%AAX8850I&carNumber=9908&carType=1" + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","成功");
    }


/****************************************************************************围栏PHP_更新于2018.11.8****************************************************************************/

    //获取工地工程列表
    @Test
    @Parameters({"userName"})
    public void siteList(String userName){
        userLogin(userName);
        String userId = getUserId(userName);
        String sign = AppSign(userName);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/site/list","urid=" + userId + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","成功");
    }

    //更新工程
    @Test
    @Parameters({"userName"})
    public void siteProjectUpdate(String userName){
        userLogin(userName);
        String userId = getUserId(userName);
        String sign = AppSign(userName);
        String projectIdSql = "";
        String projectId = MySqlUtil.getInstance().mySqlCURD(projectIdSql);
        String projectNameSql = "";
        String projectName = MySqlUtil.getInstance().mySqlCURD(projectNameSql);
        String longitudeSql = "";
        String longitude = MySqlUtil.getInstance().mySqlCURD(longitudeSql);
        String latitudeSql = "";
        String latitude = MySqlUtil.getInstance().mySqlCURD(latitudeSql);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() +
                "/site/projectupdate","urid=" + userId + "&projectId=" + projectId + "&projectName=" + projectName + "&longitude=" + longitude + "&latitude=" + latitude + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","成功");
    }

    //更新围栏
    @Test
    @Parameters({"userName"})
    public void siteUpdate(String userName){
        userLogin(userName);
        String userId = getUserId(userName);
        String sign = AppSign(userName);
        String siteIdSql = "";
        String siteId = MySqlUtil.getInstance().mySqlCURD(siteIdSql);
        String siteNameSql = "";
        String siteName = MySqlUtil.getInstance().mySqlCURD(siteNameSql);
        String typeSql = "";
        String type = MySqlUtil.getInstance().mySqlCURD(typeSql);
        String scopeSql = "";
        String scope = MySqlUtil.getInstance().mySqlCURD(scopeSql);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() +
                "/site/update","urid=" + userId + "&siteId=" + siteId + "&siteName=" + siteName + "&type=" + type + "&scope=" + scope + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","成功");
    }

    //新建围栏
    @Test
    @Parameters({"userName"})
    public void siteAdd(String userName){
        userLogin(userName);
        String userId = getUserId(userName);
        String sign = AppSign(userName);
        String siteNameSql = "";
        String siteName = MySqlUtil.getInstance().mySqlCURD(siteNameSql);
        String typeSql = "";
        String type = MySqlUtil.getInstance().mySqlCURD(typeSql);
        String scopeSql = "";
        String scope = MySqlUtil.getInstance().mySqlCURD(scopeSql);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() +
                "/site/update","urid=" + userId + "&siteName=" + siteName + "&type=" + type + "&scope=" + scope + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","成功");
    }

/****************************************************************************订单PHP_更新于2018.11.8****************************************************************************/

    //新建预订单
    @Test
    @Parameters({"userName"})
    public void orderAdd(String userName){
        userLogin(userName);
        String sign = AppSign(userName);
        String deliverCompanyIdSql = "SELECT `companyId` from `user` where tel='"+ userName + "';";
        String deliverCompanyId = MySqlUtil.getInstance().mySqlCURD(deliverCompanyIdSql);
        String receiveCompanyIdSql = "SELECT `receiveCompanyId` from `project` where `deliverCompanyId`=(SELECT `companyId` from `user` where tel='13764230015') AND `receiveCompanyId` != 0 ORDER BY `id` DESC LIMIT 1;";
        String receiveCompanyId = MySqlUtil.getInstance().mySqlCURD(receiveCompanyIdSql);
        String tpz = "C10";
        String slumpStr = "100±30";
        String pouringMethod = "泵送";
        String pouringPosition = "测试部位";
        String planQuantity = "100";
        String projectIdSql = "SELECT `receiveCompanyId` from `project` where `deliverCompanyId`=(SELECT `companyId` from `user` where tel='13764230015') AND `receiveCompanyId` != 0 ORDER BY `id` DESC LIMIT 1;";
        String projectId = MySqlUtil.getInstance().mySqlCURD(projectIdSql);
        String projectAddSql = "";
        String projectAdd = MySqlUtil.getInstance().mySqlCURD(projectAddSql);
        String planTimeSql = "";
        String planTime = MySqlUtil.getInstance().mySqlCURD(planTimeSql);
        String remarksSql = "";
        String remarks = MySqlUtil.getInstance().mySqlCURD(remarksSql);
        String contactNameSql = "";
        String contactName = MySqlUtil.getInstance().mySqlCURD(contactNameSql);
        String contactTelSql = "";
        String contactTel = MySqlUtil.getInstance().mySqlCURD(contactTelSql);
        JSONObject jsonObject = sendPostUrl(TestUrl.getTongCheReleaseUrl() + "/order/add",
                "deliverCompanyId=" + deliverCompanyId + "&receiveCompanyId=" + receiveCompanyId + "&tpz=" + tpz + "&slumpStr=" + slumpStr +
                        "&pouringMethod=" + pouringMethod + "&pouringPosition=" + pouringPosition + "&planQuantity=" + planQuantity + "&projectId=" + projectId +
                        "&projectAdd=" + projectAdd + "&planTime=" + planTime + "&remarks=" + remarks + "&contactName=" + contactName + "&contactTel=" + contactTel + sign);
        InterFaceUtil.getInstance().AssertJSONPost(jsonObject,"200","成功");
    }

}















