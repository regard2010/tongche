package utils;

import org.apache.log4j.Logger;

import java.sql.*;

public class MySqlUtil {

    public static Logger logger = Logger.getLogger(MySqlUtil.class);
        final String RELEASE_DB_URL = "jdbc:mysql://192.144.159.20:3306/tongche?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        final String TEST_DB_URL = "jdbc:mysql://123.207.173.134:3306/tongche?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    final String BETA_DB_URL = "jdbc:mysql://192.144.172.213:3306/tongche?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        final String TEST_USER = "tongche";
        final String TEST_PASS = "Pass^w0rd";
    final String BETA_USER = "tongche";
    final String BETA_PASS = "Pass^w0rdtongche";

    //使用静态内部类创建外部类对象
    private static class MySql{
        private static MySqlUtil mySqlUtil = new MySqlUtil();
    }

    //获取InterFaceUtil实例
    public static MySqlUtil getInstance(){
        return MySqlUtil.MySql.mySqlUtil;
    }

    /**
     * 数据库相关连接信息请修改url中的数据
     * @param sql 查询或者更新的第一个单词请用大写,SELECT或者UPDATE
     * @return
     */
    public String mySqlCURD(String sql){
        Connection conn = null;
        String result = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            logger.info("加载MYSQL驱动成功");
            Thread.sleep(1000);
//            conn = DriverManager.getConnection(TEST_DB_URL,TEST_USER,TEST_PASS);
            conn = DriverManager.getConnection(BETA_DB_URL,BETA_USER,BETA_PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
            if(sql.contains("SELECT")){
                ResultSet rs = stmt.executeQuery(sql);
                Thread.sleep(1000);
                while(rs.next()){
                    result = rs.getString(1);
                    logger.info("数据库[查询]操作执行成功，查询结果为:"+result);
                    Thread.sleep(1000);
                }
            }else if(sql.contains("UPDATE")){
                int updateFlag = stmt.executeUpdate(sql);
                if(updateFlag >= 1){
                    logger.info("数据库[更新]操作执行成功，更新了:["+ updateFlag +"]条记录");
                }else{
                    logger.info("数据库[更新]操作执行成功，但是只更新了:["+ updateFlag +"]条记录");
                }
            }else if(sql.contains("INSERT")){
                int insertFlag = stmt.executeUpdate(sql);
                if(insertFlag >= 1){
                    logger.info("数据库[新建]操作执行成功，新建了:["+ insertFlag +"]条记录");
                }else{
                    logger.info("数据库[新建]操作执行成功，但是只新建了:["+ insertFlag +"]条记录");
                }
            }else if(sql.contains("DELETE")){
                int deleteCount = stmt.executeUpdate(sql);
                if(deleteCount >= 1){
                    logger.info("数据库[删除]操作执行成功，删除了:["+ deleteCount +"]条记录");
                }else{
                    logger.info("数据库[删除]操作执行成功，但是只删除了:["+ deleteCount +"]条记录");
                }
            }
        }catch(SQLException e){
            logger.error("数据库相关操作错误",e);
        }catch(Exception e){
            logger.error("数据库无法操作",e);
        }finally{
            try {
                conn.close();
            } catch (SQLException e) {
                logger.error("数据库无法被关闭",e);
            }
        }
        return result;
    }

    /**
     * 直接执行SQL语句，带数据源
     * @param dataSource
     * @param sql
     * @return
     */
    public String mySqlCURD_URI(String dataSource,String sql){
        Connection conn = null;
        String url = "jdbc:mysql://123.207.173.134:3306/" + dataSource + "?user=tongche&password=Pass^w0rd&useUnicode=true&characterEncoding=UTF8&useSSL=true";

        String result = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            logger.info("加载MYSQL驱动成功");
            Thread.sleep(1000);
            conn = DriverManager.getConnection(url);
//            Statement stmt = conn.createStatement();
            PreparedStatement stmt = conn.prepareStatement(sql);
            if(sql.contains("SELECT")){
                ResultSet rs = stmt.executeQuery(sql);
                Thread.sleep(1000);
                while(rs.next()){
                    result = rs.getString(1);
                    logger.info("数据库[查询]操作执行成功，查询结果为:"+result);
                    System.out.println("数据库[查询]操作执行成功，查询结果为:"+result);
                    Thread.sleep(1000);
                }
            }else if(sql.contains("UPDATE")){
                int updateFlag = stmt.executeUpdate(sql);
                if(updateFlag >= 1){
                    logger.info("数据库[更新]操作执行成功，更新了:["+ updateFlag +"]条记录");
                    System.out.println("数据库[更新]操作执行成功，更新了:["+ updateFlag +"]条记录");
                }else{
                    logger.info("数据库[更新]操作执行成功，但是只更新了:["+ updateFlag +"]条记录");
                    System.out.println("数据库[更新]操作执行成功，但是只更新了:["+ updateFlag +"]条记录");
                }
            }else if(sql.contains("INSERT")){
                int insertFlag = stmt.executeUpdate(sql);
                if(insertFlag >= 1){
                    logger.info("数据库[新建]操作执行成功，新建了:["+ insertFlag +"]条记录");
                    System.out.println("数据库[新建]操作执行成功，新建了:["+ insertFlag +"]条记录");
                }else{
                    logger.info("数据库[新建]操作执行成功，但是只新建了:["+ insertFlag +"]条记录");
                    System.out.println("数据库[新建]操作执行成功，但是只新建了:["+ insertFlag +"]条记录");
                }
            }else if(sql.contains("DELETE")){
                int deleteCount = stmt.executeUpdate(sql);
                if(deleteCount >= 1){
                    logger.info("数据库[删除]操作执行成功，删除了:["+ deleteCount +"]条记录");
                    System.out.println("数据库[删除]操作执行成功，删除了:["+ deleteCount +"]条记录");
                }else{
                    logger.info("数据库[删除]操作执行成功，但是只删除了:["+ deleteCount +"]条记录");
                    System.out.println("数据库[删除]操作执行成功，但是只删除了:["+ deleteCount +"]条记录");
                }
            }
        }catch(SQLException e){
            logger.error("数据库相关操作错误",e);
            System.out.println("数据库相关操作错误");
        }catch(Exception e){
            logger.error("数据库无法操作",e);
            System.out.println("数据库无法操作");
        }finally{
            try {
                conn.close();
            } catch (SQLException e) {
                logger.error("数据库无法被关闭",e);
                System.out.println("数据库无法被关闭");
            }
        }
        return result;
    }

    /**
     *  本地数据库
     * @param sql
     * @return
     */
    public String mySqlCURD_URI_Localhost(String sql){
        Connection conn = null;
        String url = "jdbc:mysql://127.0.0.1:3306/db_ssm?user=root&password=123456&useUnicode=true&characterEncoding=utf-8&useSSL=false";

        String result = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            logger.info("加载MYSQL驱动成功");
            Thread.sleep(1000);
            conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(sql);
            if(sql.contains("SELECT")){
                ResultSet rs = stmt.executeQuery(sql);
                Thread.sleep(1000);
                while(rs.next()){
                    result = rs.getString(1);
                    logger.info("数据库[查询]操作执行成功，查询结果为:"+result);
                    System.out.println("数据库[查询]操作执行成功，查询结果为:"+result);
                    Thread.sleep(1000);
                }
            }else if(sql.contains("UPDATE")){
                int updateFlag = stmt.executeUpdate(sql);
                if(updateFlag >= 1){
                    logger.info("数据库[更新]操作执行成功，更新了:["+ updateFlag +"]条记录");
                    System.out.println("数据库[更新]操作执行成功，更新了:["+ updateFlag +"]条记录");
                }else{
                    logger.info("数据库[更新]操作执行成功，但是只更新了:["+ updateFlag +"]条记录");
                    System.out.println("数据库[更新]操作执行成功，但是只更新了:["+ updateFlag +"]条记录");
                }
            }else if(sql.contains("INSERT")){
                int insertFlag = stmt.executeUpdate(sql);
                if(insertFlag >= 1){
                    logger.info("数据库[新建]操作执行成功，新建了:["+ insertFlag +"]条记录");
                    System.out.println("数据库[新建]操作执行成功，新建了:["+ insertFlag +"]条记录");
                }else{
                    logger.info("数据库[新建]操作执行成功，但是只新建了:["+ insertFlag +"]条记录");
                    System.out.println("数据库[新建]操作执行成功，但是只新建了:["+ insertFlag +"]条记录");
                }
            }else if(sql.contains("DELETE")){
                int deleteCount = stmt.executeUpdate(sql);
                if(deleteCount >= 1){
                    logger.info("数据库[删除]操作执行成功，删除了:["+ deleteCount +"]条记录");
                    System.out.println("数据库[删除]操作执行成功，删除了:["+ deleteCount +"]条记录");
                }else{
                    logger.info("数据库[删除]操作执行成功，但是只删除了:["+ deleteCount +"]条记录");
                    System.out.println("数据库[删除]操作执行成功，但是只删除了:["+ deleteCount +"]条记录");
                }
            }
        }catch(SQLException e){
            logger.error("数据库相关操作错误",e);
            System.out.println("数据库相关操作错误");
        }catch(Exception e){
            logger.error("数据库无法操作",e);
            System.out.println("数据库无法操作");
        }finally{
            try {
                conn.close();
            } catch (SQLException e) {
                logger.error("数据库无法被关闭",e);
                System.out.println("数据库无法被关闭");
            }
        }
        return result;
    }
}
