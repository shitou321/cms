import java.io.*;
import java.sql.*;

public class DbUtils {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://123.126.34.6:3307/cms";
    static final String USER = "root";
    static final String PASS = "mqpass0o0";
    static final String path = "F:\\3.txt";
    static final String serCode = "default";
    static final String password = "k2xvHUmCHWw=";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象..." + conn.getClass().getClassLoader().getParent().getParent());
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT *  FROM zzz ";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while (rs.next()) {
                //empid
                int id = rs.getInt("id");
                String falg = rs.getString("flag");
                String usercode = rs.getString("usercode");
                String username = rs.getString("username");
                String orgcode = rs.getString("orgcode");
                String mobile = rs.getString("mobile");
                String email = rs.getString("email");
                String sex = rs.getString("sex");


                /*String str = "INSERT INTO org_employee (EMPID,EMPCODE,OPERATORID,USERID,EMPNAME,GENDER,OEMAIL,MOBILENO,CREATETIME,LASTMODYTIME,ORGID,TENANT_ID) VALUES"
                        + "('" + id + "','" + usercode + "','" + id + "','" + usercode + "','" + username + "','" + sex +
                        "','" + email + "','" + mobile + "'," + "NOW(),NOW(),'" + orgcode + "','" + serCode + "');";*/
                /*String str = "INSERT INTO cap_user (OPERATOR_ID,TENANT_ID,USER_ID,PASSWORD,USER_NAME,AUTHMODE,STATUS,UNLOCKTIME,MENUTYPE,LASTLOGIN,EMAIL,CREATEUSER,CREATETIME)"
                        +"VALUES ('"+id+"','"+serCode+"','"+usercode+"','"+password+"','"+username+"','local','"+falg+"',NOW(),'"+serCode+"',NOW(),'"+email+"','sysadmin',NOW());";*/


                //String str = "INSERT INTO org_emporg(ORGID,EMPID,ISMAIN,TENANT_ID) VALUES " + "('" + orgcode + "','" + id + "','y','default');";

                String str = usercode.substring(2, usercode.length());
                // 输出数据
                BufferedWriter out = new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(path, true)));
                out.write(str + "\r\n");
                out.close();

            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
