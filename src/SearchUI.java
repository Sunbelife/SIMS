import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sun on 16/12/7.
 */
public class SearchUI {
    JFrame Manage_Frm;
    JTextField Username_Fid,ID_Fid,Phone_Fid,Age_Fid,Type_Fid;
    JLabel Username_Lbl,ID_Lbl,Phone_Lbl,Age_Lbl,Type_Lbl;
    JPanel Search_Pal,Control_Pal;
    JScrollPane Data_Pal;
    JButton Search_Btn,Del_Btn,Update_Btn,Change_Btn,Check_Btn;
    JTable data_Jtb;
    TableData model;
    int anchor = GridBagConstraints.CENTER;
    int fill = GridBagConstraints.BOTH;
    Insets insets = new Insets(0, 0, 0, 0);

    SearchUI() {
        Manage_Frm = new JFrame("信息管理");
        Manage_Frm.setSize(600,400);
        Manage_Frm.setLocationRelativeTo(null);
        Manage_Frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Manage_Frm.setLayout(new GridBagLayout());

        // 自动刷新
        Manage_Frm.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                SearcherListener Refresh = new SearcherListener();
                Refresh.showResultTable();
            }

            @Override
            public void windowLostFocus(WindowEvent e) {

            }
        });

        Search_Pal = new JPanel();
        Search_Pal.setLayout(new GridBagLayout());
        Type_Fid = new JTextField(3);
        Type_Lbl = new JLabel("类型:");
        Username_Fid = new JTextField(4);
        ID_Fid = new JTextField(3);
        Phone_Fid = new JTextField(13);
        Age_Fid = new JTextField(3);
        Username_Lbl = new JLabel("姓名:");
        ID_Lbl = new JLabel("ID:");
        Phone_Lbl = new JLabel("电话:");
        Age_Lbl = new JLabel("年龄:");
        Search_Btn = new JButton("刷新");
        Search_Btn.addActionListener(new SearcherListener());
        Control_Pal = new JPanel();
        Control_Pal.setLayout(new GridBagLayout());
        Del_Btn = new JButton("删除");
        Update_Btn = new JButton("插入");
        Change_Btn = new JButton("更改");
        Check_Btn = new JButton("查看");
        Check_Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 保存选择的数据
                int Indexrow = data_Jtb.getSelectedRow();
                if (Indexrow < 0) {
                    return;
                }
                int id = Integer.parseInt(String.valueOf(data_Jtb.getValueAt(Indexrow, 0)));
                String name = (String) data_Jtb.getValueAt(Indexrow, 1);
                int type = Integer.parseInt(String.valueOf(data_Jtb.getValueAt(Indexrow, 2)));
                String phone = (String) data_Jtb.getValueAt(Indexrow, 3);
                int age = Integer.parseInt(String.valueOf(data_Jtb.getValueAt(Indexrow, 4)));
                LoadData loadPic = new LoadData();
                String sql = "SELECT PicPath FROM People where ID = " + id;
                try {
                    String PicPath = loadPic.LoadStringData(sql);
                    new WatchUI(id,name,type,phone,age,PicPath);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        Data_Pal = new JScrollPane();
        TableData data = null;
        data_Jtb = new JTable(data);
        data_Jtb.setFillsViewportHeight(true); // 使用父容器所有高度
//        Data_Pal.setBackground(Color.BLUE);
        Data_Pal.add(data_Jtb);

        Search_Pal.add(ID_Lbl);
        Search_Pal.add(ID_Fid);
        Search_Pal.add(Username_Lbl);
        Search_Pal.add(Username_Fid);
        Search_Pal.add(Type_Lbl);
        Search_Pal.add(Type_Fid);
        Search_Pal.add(Phone_Lbl);
        Search_Pal.add(Phone_Fid);
        Search_Pal.add(Age_Lbl);
        Search_Pal.add(Age_Fid);
        Search_Pal.add(Search_Btn);

        Change_Btn.setEnabled(false);
        Del_Btn.setEnabled(false);
        Update_Btn.setEnabled(false);
        Control_Pal.add(Check_Btn);
        Control_Pal.add(Change_Btn);
        Control_Pal.add(Del_Btn);
        Control_Pal.add(Search_Btn);
        Control_Pal.add(Update_Btn);
        Control_Pal.setVisible(true);

        // gridx 左数第几列, gridy 左数第几行, gridwidth 有几列, gridheigth 有几行
        // weightx 额外的水平空间, weighty 额外的垂直空间, anchor 放置组件的位置, fill 大于组件是否填充
        // insets 与显示区域边缘的最小量,
        // ipadx 最小宽度, ipady 最小垂直距离

        Manage_Frm.add(Search_Pal, new GridBagConstraints(0,1,1,1,100,100,anchor,fill,insets,0,0));
        Manage_Frm.add(Data_Pal, new GridBagConstraints(0,2,1,1,100,500,anchor,fill,insets,0,0));
        Manage_Frm.add(Control_Pal, new GridBagConstraints(0,3,1,1,100,100,anchor,fill,insets,0,0));
        Manage_Frm.setVisible(true);

        new SearcherListener().showResultTable();
    }

    class SearcherListener implements ActionListener {
        public void showResultTable() {
            String[] columnname = new String[] {"ID","TYPE","NAME","PHONE","AGE"};
            String id = ID_Fid.getText();
            String type = Type_Fid.getText();
            String name = "'" + Username_Fid.getText() + "'";
            String phone = "'" + Phone_Fid.getText() + "'";
            String age = Age_Fid.getText();
            String[] inputvalues = new String[] {id,type,name,phone,age};
            String sql = "SELECT * FROM People ";
            boolean isLimited = false;
            boolean isEmpty = true;

           if (Data_Pal != null) Manage_Frm.remove(Data_Pal);

            for(int i = 0; i < columnname.length; i++) {
                if (i == 0) {
                    if (!inputvalues[i].trim().isEmpty()) {
                        sql += "WHERE ID = " + id;
                        isEmpty = false;
                        break;
                    }
                } else if (!inputvalues[i].trim().isEmpty() && !inputvalues[i].trim().equals("''")) {
                    if (!isLimited) {
                        sql += "WHERE " + columnname[i] + " = " + inputvalues[i];
                        isEmpty = false;
                        isLimited = true;
                    }else {
                        sql += " AND " + columnname[i] + " = " + inputvalues[i];
                    }
                }
            }
            System.out.println(sql);
            ResultSet rs = new LoadData().RefreshData(sql);
            try {
                if (!rs.next() && isEmpty) {
                    new InsertUI("/Users/sun/Documents/StuSystem/src/images/PicNotFound.png");
                    new NoticeUI("空的","数据表现在是空的,添加点东西吧!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                model = new TableData(rs);
                data_Jtb = new JTable(model);
                Data_Pal = new JScrollPane(data_Jtb);
                Manage_Frm.add(Data_Pal, new GridBagConstraints(0,2,1,1,100,500,anchor,fill,insets,0,0));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // 刷新 Frame
            Manage_Frm.validate();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            showResultTable();
        }
    }

}

