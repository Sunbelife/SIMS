import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;

/**
 * Created by sun on 16/12/5.
 */
public class LoadData {
    // 用来加载数据库中指定 String 值,减少资源占用。
    public String LoadStringData(String sql,String data, String request) throws Exception {
        String result = "false";
        DBUtil dbUtil = new DBUtil();
        Connection conn = dbUtil.getConn();
        PreparedStatement pstate;
        pstate = dbUtil.getPstmt(conn,sql);
        ResultSet resultSet = dbUtil.getRs(pstate);
        while(resultSet.next()) {
            if (resultSet.getString(request).equals(data)) {
                result = "true";
            }
        }
        dbUtil.close(resultSet,pstate,conn);
        return result;
    }

    // 用来加载数据库中指定 Int 值,减少资源占用。
    public int LoadIntData(String sql, String request) throws Exception {
        int result = 0;
        DBUtil dbUtil = new DBUtil();
        Connection conn = dbUtil.getConn();
        PreparedStatement pstate;
        pstate = dbUtil.getPstmt(conn,sql);
        ResultSet resultSet = dbUtil.getRs(pstate);
        while(resultSet.next()) {
            result = resultSet.getInt(request);
        }
        dbUtil.close(resultSet,pstate,conn);
        return result;
    }

    // 用来做 Query 操作
    public ResultSet RefreshData(String sql) {
        DBUtil dbUtil = new DBUtil();
        Connection conn = dbUtil.getConn();
        PreparedStatement pstate;
        pstate = dbUtil.getPstmt(conn,sql);
        ResultSet resultSet = dbUtil.getRs(pstate);
        return resultSet;
    }

    // 用来做 Update 操作
    public int UpdateData(String sql) {
        DBUtil dbUtil = new DBUtil();
        Connection conn = dbUtil.getConn();
        PreparedStatement pstate;
        pstate = dbUtil.getPstmt(conn,sql);
        int Resultflag = dbUtil.updateRs(pstate);
        return Resultflag;
    }

}

