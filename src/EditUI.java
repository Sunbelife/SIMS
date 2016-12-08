import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by sun on 16/12/8.
 */
public class EditUI extends JFrame {
    JFrame Edit_Frm;
    JButton InsertBtn,CanclBtn;
    JTextField Username_Fid,ID_Fid,Type_Fid,Phone_Fid,Age_Fid;
    JLabel Username_Lbl,ID_Lbl,Type_Lbl,Phone_Lbl,Age_Lbl;

    EditUI (int id,String name,int type,String phone,int age) {
        Edit_Frm = new JFrame("更改数据");
        Edit_Frm.setLocationRelativeTo(null);
        Edit_Frm.setLayout(new FlowLayout());
        Edit_Frm.setBounds(185,322,1000,63);

        ID_Lbl = new JLabel("ID:");
        ID_Fid = new JTextField(3);
        ID_Fid.setText(String.valueOf(id));
        ID_Fid.setEditable(false);
        Username_Lbl = new JLabel("姓名:");
        Username_Fid = new JTextField(4);
        Username_Fid.setText(name);
        Type_Lbl = new JLabel("类型");
        Type_Fid = new JTextField(2);
        Type_Fid.setText(String.valueOf(type));
        Phone_Lbl = new JLabel("电话:");
        Phone_Fid = new JTextField(13);
        Phone_Fid.setText(phone);
        Age_Lbl = new JLabel("年龄");
        Age_Fid = new JTextField(3);
        Age_Fid.setText(String.valueOf(age));
        InsertBtn = new JButton("保存修改");
        CanclBtn = new JButton("返回");

        InsertBtn.addActionListener(new EditListener());
        CanclBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Edit_Frm.dispose();
            }
        });

        Edit_Frm.add(ID_Lbl);
        Edit_Frm.add(ID_Fid);
        Edit_Frm.add(Username_Lbl);
        Edit_Frm.add(Username_Fid);
        Edit_Frm.add(Type_Lbl);
        Edit_Frm.add(Type_Fid);
        Edit_Frm.add(Phone_Lbl);
        Edit_Frm.add(Phone_Fid);
        Edit_Frm.add(Age_Lbl);
        Edit_Frm.add(Age_Fid);
        Edit_Frm.add(InsertBtn);
        Edit_Frm.add(CanclBtn);
        Edit_Frm.setVisible(true);
    }
    class EditListener implements ActionListener {
        public void showResultTable() {
            String[] columnname = new String[] {"ID","TYPE","NAME","PHONE","AGE"};
            String id = ID_Fid.getText();
            String type = Type_Fid.getText();
            String name = "'" + Username_Fid.getText() + "'";
            String phone = "'" + Phone_Fid.getText() + "'";
            String age = Age_Fid.getText();
            String sql = "UPDATE People SET TYPE = " + type + ",NAME = " + name +
                    ",PHONE = " + phone + ",AGE = " + age + " WHERE ID = " + id;
            System.out.println(sql);
            int rs = new LoadData().UpdateData(sql);
            if (rs == 1) {
                new Notice("恭喜","恭喜,保存成功!");
                Edit_Frm.setVisible(false);
            } else {
                new Notice("遗憾","失败咯,是不是改错了东西?");
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            showResultTable();
        }
    }
}
