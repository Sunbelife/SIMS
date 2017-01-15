import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by sun on 16/12/8.
 */
public class EditUI extends JFrame {
    JFrame Edit_Frm;
    JPanel Data_Pal;
    ImagePal Image_Pal;
    JButton Insert_Btn, Cancl_Btn,Brow_Btn;
    JTextField Username_Fid,ID_Fid,Type_Fid,Phone_Fid,Age_Fid;
    JLabel Username_Lbl,ID_Lbl,Type_Lbl,Phone_Lbl,Age_Lbl;
    String Path;

    EditUI (int id,String name,int type,String phone,int age,String PicPath) {
        this.Path = PicPath;
        Edit_Frm = new JFrame("更改数据");
        Edit_Frm.setLocationRelativeTo(null);
        Edit_Frm.setLayout(null);
        Edit_Frm.setBounds(483,156,300,450);

        Data_Pal = new JPanel();
        Data_Pal.setBounds(80,200,150,190);
        Data_Pal.setLayout(new GridLayout(6,2));

        Image_Pal = new ImagePal(PicPath);
        Image_Pal.setBounds(85,20,130,160);
//        Image_Pal.setBackground(Color.black);

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
        Insert_Btn = new JButton("保存修改");
        Insert_Btn.setBounds(60,380,80,30);
        Cancl_Btn = new JButton("返回");
        Cancl_Btn.setBounds(160,380,80,30);

        Brow_Btn = new JButton("…");
        Brow_Btn.setBounds(230,158,20,20);
        Brow_Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int Age = Integer.parseInt(Age_Fid.getText());
                String Phone = Phone_Fid.getText();
                String User = Username_Fid.getText();
                int ID = Integer.parseInt(ID_Fid.getText());
                int Type = Integer.parseInt(Type_Fid.getText());
                Edit_Frm.dispose();
                FileChooser PicChoser = new FileChooser();
                String outPath = PicChoser.getPath();
                if (PicChoser.getPath() == null) {
                    new EditUI(ID,User,Type,Phone,Age,Path);
                }else new EditUI(ID,User,Type,Phone,Age,outPath);
            }
        });

        Insert_Btn.addActionListener(new EditListener());
        Cancl_Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Edit_Frm.dispose();
            }
        });

        Data_Pal.add(ID_Lbl);
        Data_Pal.add(ID_Fid);
        Data_Pal.add(Username_Lbl);
        Data_Pal.add(Username_Fid);
        Data_Pal.add(Type_Lbl);
        Data_Pal.add(Type_Fid);
        Data_Pal.add(Phone_Lbl);
        Data_Pal.add(Phone_Fid);
        Data_Pal.add(Age_Lbl);
        Data_Pal.add(Age_Fid);

        Edit_Frm.add(Brow_Btn);
        Edit_Frm.add(Image_Pal);
        Edit_Frm.add(Insert_Btn);
        Edit_Frm.add(Cancl_Btn);
        Edit_Frm.add(Data_Pal);
        Edit_Frm.setVisible(true);
    }

    class ImagePal extends JPanel {
        private BufferedImage image;

        public ImagePal(String Path) {
            if (Path == null) {
                try {
                    image = ImageIO.read(new File("/Users/sun/Documents/StuSystem/src/images/PicNotFound.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
//                System.out.println(Path);
                try {
                    image = ImageIO.read(new File(Path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void paintComponent(Graphics g) {
            g.drawImage(image, 0, 0, 130, 160, null);
        }
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
                    ",PHONE = " + phone + ",AGE = " + age + ",PicPath = \"" + Path + "\" WHERE ID = " + id;
            System.out.println(sql);
            int rs = new LoadData().UpdateData(sql);
            if (rs == 1) {
                new NoticeUI("恭喜","恭喜,保存成功!");
                Edit_Frm.dispose();
            } else {
                new NoticeUI("遗憾","失败咯,是不是改错了东西?");
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (VerifyEmptyListner() == 0) {
                new NoticeUI("错误", "插入的值中有空的");
            } else {
                showResultTable();
            }
        }
    }
    private int VerifyEmptyListner() {
        if (ID_Fid.getText().trim().isEmpty() || Username_Fid.getText().trim().isEmpty()
                || Phone_Fid.getText().trim().isEmpty() || Age_Fid.getText().trim().isEmpty() ||
                Type_Fid.getText().trim().isEmpty())
        {
            return 0;
        }
        return 1;
    }
}
