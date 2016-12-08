import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by sun on 16/12/6.
 */
public class LoginUi {
    JTextField User_Fid;
    JLabel User_Lbl,Pass_Lbl;
    JButton Login_Btn;
    JPasswordField Password_Fid;
    JLabel Header_Lbl,Wel_Lbl;

    LoginUi(){
        JFrame Login_frm = new JFrame();
        Login_frm.setLayout(null);
        Login_frm.setSize(400,350);
        Login_frm.setLocationRelativeTo(null);

        Header_Lbl = new JLabel();
        Header_Lbl.setText("欢迎登录");
        Header_Lbl.setIcon(new ImageIcon(LoginUi.class.getResource("/images/Login_head.png")));
        Header_Lbl.setBounds(137,30,126,115);
        Wel_Lbl = new JLabel("学生管理系统™");
        Wel_Lbl.setFont(new Font("苹方",Font.ITALIC, 20));
        Wel_Lbl.setBounds(130,110,200,115);
        User_Lbl = new JLabel("用户名:");
        User_Lbl.setBounds(110,202,100,30);
        User_Fid = new JTextField(8);
        User_Fid.setBounds(160,202,130,30);
        Pass_Lbl = new JLabel("密 码:");
        Pass_Lbl.setBounds(110,232,100,30);
        Password_Fid = new JPasswordField(8);
        Password_Fid.setBounds(160,232,130,30);
        Login_Btn = new JButton("进入");
        Login_Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = User_Fid.getText();
                String password = new String(Password_Fid.getPassword());
                if (username.trim().isEmpty() || password.trim().isEmpty()) {
                    Notice PasswrdisEmpty = new Notice("密码或用户名为空","密码或者用户名是空的,重新输入吧");
                } else try {
                    if (!PasswdisTrue()) {
                        Notice PasswrdisEmpty = new Notice("密码或用户名错误","密码或者用户名是错误,重新输入吧");
                    } else {
                        try {
                            if (PasswdisTrue()){
                                Login_frm.setVisible(false);
                                if (CheckType() == 0) {
                                    new Student("同学你好");
                                } else if (CheckType() == 1) {
                                    new Teacher("老师你好");
                                }
                            }
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }});
        Login_Btn.setBounds(140,272,120,40);

        Login_frm.add(Wel_Lbl);
        Login_frm.add(Header_Lbl);
        Login_frm.add(User_Lbl);
        Login_frm.add(User_Fid);
        Login_frm.add(Pass_Lbl);
        Login_frm.add(Password_Fid);
        Login_frm.add(Login_Btn);
        Login_frm.setUndecorated(true);
        Login_frm.setVisible(true);
    }

    public boolean PasswdisTrue() throws SQLException {
        String username = User_Fid.getText();
        String password = new String(Password_Fid.getPassword());
        LoadData loaddata = new LoadData();
        boolean result = false;
        String sql = "Select Password from UserandPassword WHERE Username = \"" + username + "\";";
        try {
            result = new Boolean(loaddata.LoadStringData(sql,password, "Password"));
            System.out.println(result);
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public int CheckType() throws SQLException {
        String username = User_Fid.getText();
        int result = 0;
        String sql = "Select Type from UserandPassword WHERE Username = \"" + username + "\";";
        try {
            result = new LoadData().LoadIntData(sql,"Type");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
