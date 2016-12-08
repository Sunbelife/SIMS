import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by sun on 16/12/5.
 */
public class Student {
    JButton Search_Btn;
    JButton Manage_Btn;
    JLabel Welcome_Lbl;

    Student(String welCome) {
        JFrame Stu_Frm = new JFrame(welCome);
        Stu_Frm.setLayout(null);
        Stu_Frm.setSize(400,350);
        Stu_Frm.setLocationRelativeTo(null);
        Stu_Frm.setUndecorated(true);

        Search_Btn = new JButton("查询");
        Search_Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Stu_Frm.setVisible(false);
                new Search();
            }
        });
        Search_Btn.setBounds(120,150,160,50);

        Manage_Btn = new JButton("管理");
        Manage_Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Stu_Frm.setVisible(false);
                new Manage();
            }
        });
        Manage_Btn.setEnabled(false);
        Manage_Btn.setBounds(120,200,160,50);

        Welcome_Lbl = new JLabel();
        Welcome_Lbl.setText(welCome + ", 你现在只能查询。");
        Welcome_Lbl.setBounds(120,60,200,50);

        Stu_Frm.add(Welcome_Lbl);
        Stu_Frm.add(Manage_Btn);
        Stu_Frm.add(Search_Btn);
        Stu_Frm.setVisible(true);
        Stu_Frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
