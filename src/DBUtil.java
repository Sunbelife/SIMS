import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sun on 16/12/6.
 */
public class DBUtil {//构造方法
    public DBUtil(){}
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");//jdbc
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }//加载驱动程序);
    }

    public static Connection getConn(){
        Connection conn =null;
        try {
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/SIMS?useSSL=false&characterEncoding=utf8","root","haha0308");//url+用户+密码
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static PreparedStatement getPstmt(Connection conn, String sql){
        PreparedStatement pstmt = null;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pstmt;
    }

    public static ResultSet getRs(PreparedStatement pstmt){
        ResultSet rs = null;
        try {
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    public static int updateRs(PreparedStatement pstmt){
        int Resultflag = 0;
        boolean isSuccessed = true;
        try {
            Resultflag = pstmt.executeUpdate();
            Resultflag = 1;
        } catch (SQLException e) {
            Resultflag = 0;
//            e.printStackTrace();
        }
        return Resultflag;
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stmt=null;
        }

        if(conn!= null){
            try {
                conn.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
            conn=null;
        }
    }
}
