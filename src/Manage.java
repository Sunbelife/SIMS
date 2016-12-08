import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by sun on 16/12/7.
 */
public class Manage extends Search {
    JFrame Manage_Frm;
    int Succount = 0;

    Manage() {
        Manage_Frm = new JFrame();
        Update_Btn.setEnabled(true);
        Update_Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InsertUI insertUI = new InsertUI();
                insertUI.ID_Fid.setText(ID_Fid.getText());
                ID_Fid.setText("");
                insertUI.Type_Fid.setText(Type_Fid.getText());
                Type_Fid.setText("");
                insertUI.Username_Fid.setText(Username_Fid.getText());
                Username_Fid.setText("");
                insertUI.Phone_Fid.setText(Phone_Fid.getText());
                Phone_Fid.setText("");
                insertUI.Age_Fid.setText(Age_Fid.getText());
                Age_Fid.setText("");
            }
        });
        Del_Btn.setEnabled(true);
        Del_Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] Indexrow = data_Jtb.getSelectedRows();
                for (int i : Indexrow) {
                    int id = (int) model.getValueAt(i, 0);
                    System.out.println("id" + id);
                    String sql = "DELETE FROM PEOPLE WHERE ID = " + id;
                    LoadData loadData = new LoadData();
                    Succount += loadData.UpdateData(sql);
                }
                new Notice("更新结果","共" + Indexrow.length + "条,成功删除" + Succount + "条数据。");
                Succount = 0;
            }
        });
        Change_Btn.setEnabled(true);
        Change_Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 保存选择的数据
                int Indexrow = data_Jtb.getSelectedRow();
                int id = (int) data_Jtb.getValueAt(Indexrow, 0);
                String name = (String) data_Jtb.getValueAt(Indexrow, 1);
                int type = (int) data_Jtb.getValueAt(Indexrow, 2);
                String phone = (String) data_Jtb.getValueAt(Indexrow, 3);
                int age = (int) data_Jtb.getValueAt(Indexrow, 4);
                // 传递给 EditUI
                new EditUI(id,name,type,phone,age);
            }
        });
        Manage_Frm.validate();
    }
}
