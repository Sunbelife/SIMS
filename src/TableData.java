import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by sun on 16/12/7.
 */
public class TableData extends AbstractTableModel {
    private String[] columeName = {"ID","姓名","类型","手机号","年龄"};
    ResultSet rs;
    ResultSetMetaData rsmd;

    // isCellEditable
    public int getSelectedRow() {
        return SelectedRow;
    }

    public int getSelectedCol() {
        return SelectedCol;
    }

    int SelectedRow = 0;
    int SelectedCol = 0;

    TableData(ResultSet rs) throws SQLException {
        this.rs = rs;
        rsmd = this.rs.getMetaData();
    }

    @Override
    public int getRowCount() {
        try {
            rs.last();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            return rs.getRow();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        return 5;
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            rs.absolute(rowIndex + 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            return rs.getObject(columnIndex + 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getColumnName(int columnIndex) {
        return columeName[columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }


}