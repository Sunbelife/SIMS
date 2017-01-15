import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by sun on 2016/12/15.
 */
public class ResigsterUI {
    JTextField User_Fid;
    JLabel User_Lbl,Pass_Lbl,SePass_Lbl;
    JButton Resigster_Btn,Exit_Btn;
    ButtonGroup Type_Btn;
    JRadioButton Stu_Type_Btn,Tea_Type_Btn;
    JPasswordField Password_Fid,SePassword_Fid;
    JLabel Header_Lbl,Wel_Lbl;
    JFrame Resigster_frm;

    ResigsterUI() {
        Resigster_frm = new JFrame();
        Resigster_frm.setLayout(null);
        Resigster_frm.setSize(350,400);
        Resigster_frm.setLocationRelativeTo(null);

        Header_Lbl = new JLabel();
        Header_Lbl.setText("欢迎注册");
        Header_Lbl.setIcon(new ImageIcon("/Users/sun/Documents/StuSystem/src/images/Login_head.png"));
        Header_Lbl.setBounds(110,30,126,115);
        Wel_Lbl = new JLabel("欢迎注册到学生管理系统™");
        Wel_Lbl.setFont(new Font("苹方",Font.ITALIC, 20));
        Wel_Lbl.setBounds(60,110,300,115);
        User_Lbl = new JLabel("用户名:");
        User_Lbl.setBounds(100,202,100,30);
        User_Fid = new JTextField(8);
        User_Fid.setBounds(150,202,130,30);
        Pass_Lbl = new JLabel("密  码:");
        Pass_Lbl.setBounds(100,232,100,30);
        Password_Fid = new JPasswordField(8);
        Password_Fid.setBounds(150,232,130,30);
        SePass_Lbl = new JLabel("确  认:");
        SePass_Lbl.setBounds(100,262,100,30);
        SePassword_Fid = new JPasswordField(8);
        SePassword_Fid.setBounds(150,262,130,30);


        Stu_Type_Btn = new JRadioButton("学生",true);
        Stu_Type_Btn.setLocation(100,302);
        Stu_Type_Btn.setSize(60,20);

        Tea_Type_Btn = new JRadioButton("老师",false);
        Tea_Type_Btn.setLocation(170,302);
        Tea_Type_Btn.setSize(100,20);

        ButtonGroup Type_Btn = new ButtonGroup();
        Type_Btn.add(Stu_Type_Btn);
        Type_Btn.add(Tea_Type_Btn);

        Resigster_Btn = new JButton("注册");
        Resigster_Btn.setBounds(150,342,80,40);
        Resigster_Btn.addActionListener(new VerifyPassword());

        Exit_Btn = new JButton("退出");
        Exit_Btn.setBounds(310,350,40,40);
        Exit_Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Resigster_frm.dispose();
                new LoginUI();
            }
        });

        Resigster_frm.add(Wel_Lbl);
        Resigster_frm.add(Header_Lbl);
        Resigster_frm.add(User_Lbl);
        Resigster_frm.add(User_Fid);
        Resigster_frm.add(Pass_Lbl);
        Resigster_frm.add(Password_Fid);
        Resigster_frm.add(SePass_Lbl);
        Resigster_frm.add(SePassword_Fid);
        Resigster_frm.add(Tea_Type_Btn);
        Resigster_frm.add(Stu_Type_Btn);
        Resigster_frm.add(Resigster_Btn);
        Resigster_frm.add(Exit_Btn);
        Resigster_frm.setUndecorated(true);
        Resigster_frm.setVisible(true);
    }

    public static void main(String args[]) {
        new ResigsterUI();
    }

    class VerifyPassword implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = User_Fid.getText();
            String password = new String(Password_Fid.getPassword());
            String sepassword = new String(SePassword_Fid.getPassword());

            int Type = 0;
            if (Stu_Type_Btn.isSelected()) {
                Type = 0;
            } else if (Tea_Type_Btn.isSelected()){
                Type = 1;
            }

            System.out.println(username + " pass: " + password);
            if (isChinese(username) || isChinese(password)) {
                new NoticeUI("密码或用户名不合法","密码或者用户名不合法,重新输入吧");
            } else if (username.trim().isEmpty() || password.trim().isEmpty() || sepassword.trim().isEmpty()) {
                new NoticeUI("密码或用户名为空", "密码或者用户名是空的\n重新输入吧");
                User_Fid.setText("");
                Password_Fid.setText("");
                SePassword_Fid.setText("");
            }else if (!password.equals(sepassword)) {
                new NoticeUI("密码验证失败", "两次密码不同\n重新输入吧");
                Password_Fid.setText("");
                SePassword_Fid.setText("");
            } else if (password.length() < 6) {
                new NoticeUI("密码验证失败", "密码太短了\n最少要大于6位喔,重新输入吧~");
            } else {
                if (resigster(username,password,Type) == 1) {
                    new NoticeUI("成功", "注册成功,\n你的账号是:" + username + "密码是:" + password + "类型是:" + Type);
                    Resigster_frm.dispose();
                } else {
                    new NoticeUI("失败", "可能是账号密码有问题,换个输入吧");
                    User_Fid.setText("");
                    Password_Fid.setText("");
                    SePassword_Fid.setText("");
                }
            }
        }

        private int resigster(String username, String password, int Type) {
            username =  "'" + username + "'";
            password =  "'" + password + "'";
            String sql = "INSERT INTO UserandPassword VALUES (" + username + "," + password + "," + Type + ")";
            System.out.println(sql);
            LoadData resigsterData = new LoadData();
            return resigsterData.UpdateData(sql);
        }

        private int isrepeat(String username) {
            //

            return 0;
        }
    }
    // 根据 Unicode 编码判断
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }

    // 完整的判断中文汉字和符号
    public static boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }
}
