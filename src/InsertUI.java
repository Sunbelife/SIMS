import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by sun on 16/12/7.
 */
public class InsertUI extends JFrame {
    JFrame Insert_Frm;
    JButton InsertBtn,CanclBtn;
    JTextField Username_Fid,ID_Fid,Type_Fid,Phone_Fid,Age_Fid;
    JLabel Username_Lbl,ID_Lbl,Type_Lbl,Phone_Lbl,Age_Lbl;

    InsertUI () {
        Insert_Frm = new JFrame("插入数据");
        Insert_Frm.setLocationRelativeTo(null);
        Insert_Frm.setLayout(new FlowLayout());
        Insert_Frm.setBounds(185,322,1000,63);

        ID_Lbl = new JLabel("ID:");
        ID_Fid = new JTextField(3);
        Username_Lbl = new JLabel("姓名:");
        Username_Fid = new JTextField(4);
        Type_Lbl = new JLabel("类型");
        Type_Fid = new JTextField(2);
        Phone_Lbl = new JLabel("电话:");
        Phone_Fid = new JTextField(13);
        Age_Lbl = new JLabel("年龄");
        Age_Fid = new JTextField(3);
        InsertBtn = new JButton("插入");
        CanclBtn = new JButton("返回");

        InsertBtn.addActionListener(new InsertionListener());
        CanclBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Insert_Frm.dispose();
            }
        });
        Insert_Frm.add(ID_Lbl);
        Insert_Frm.add(ID_Fid);
        Insert_Frm.add(Username_Lbl);
        Insert_Frm.add(Username_Fid);
        Insert_Frm.add(Type_Lbl);
        Insert_Frm.add(Type_Fid);
        Insert_Frm.add(Phone_Lbl);
        Insert_Frm.add(Phone_Fid);
        Insert_Frm.add(Age_Lbl);
        Insert_Frm.add(Age_Fid);
        Insert_Frm.add(InsertBtn);
        Insert_Frm.add(CanclBtn);
        Insert_Frm.setVisible(true);
    }
    class InsertionListener implements ActionListener {
        public void showResultTable() {
            String[] columnname = new String[] {"ID","TYPE","NAME","PHONE","AGE"};
            String id = ID_Fid.getText();
            String type = Type_Fid.getText();
            String name = "'" + Username_Fid.getText() + "'";
            String phone = "'" + Phone_Fid.getText() + "'";
            String age = Age_Fid.getText();
            String[] inputvalues = new String[] {id,type,name,phone,age};
            int index = 0;
            String sql = "INSERT INTO People ";
            String cosql = "(ID";
            String vasql = "values(" + id;
            for(int i = 1; i < columnname.length; i++) {
                if (!inputvalues[i].trim().isEmpty()) {
                    cosql += "," + columnname[i];
                    vasql += "," + inputvalues[i];
                }
            }
            sql += cosql + ")" + vasql + ")";
            System.out.println(sql);
            int rs = new LoadData().UpdateData(sql);
            if (rs == 1) {
                new Notice("恭喜你","恭喜,成功一条数据");
            } else {
                new Notice("遗憾","这条数据添加失败咯");
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            showResultTable();
        }
    }
}
