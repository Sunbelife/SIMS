import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by sun on 2016/12/26.
 */
public class WatchUI {
    JFrame Watch_Frm;
    JPanel Data_Pal;
    ImagePal Image_Pal;
    JLabel ID_Lbl,Username_Lbl,Type_Lbl,Phone_Lbl,Age_Lbl;
    JLabel ID_Fid,Username_Fid,Type_Fid,Phone_Fid,Age_Fid;
    JButton Cancl_Btn;
    String Path = null;

    public WatchUI(int id, String name, int type, String phone, int age, String PicPath) {
        Path = PicPath;

        Watch_Frm = new JFrame("查看详情");
        Watch_Frm.setLocationRelativeTo(null);
        Watch_Frm.setLayout(null);
        Watch_Frm.setBounds(483,156,300,450);

        Data_Pal = new JPanel();
        Data_Pal.setBounds(80,200,150,190);
        Data_Pal.setLayout(new GridLayout(6,2));

        Image_Pal = new ImagePal(PicPath);
        Image_Pal.setBounds(85,20,130,160);
//        Image_Pal.setBackground(Color.black);

        ID_Lbl = new JLabel("ID:");
        ID_Fid = new JLabel();
        ID_Fid.setText(String.valueOf(id));
        Username_Lbl = new JLabel("姓名:");
        Username_Fid = new JLabel();
        Username_Fid.setText(name);
        Type_Lbl = new JLabel("类型");
        Type_Fid = new JLabel();
        if (type == 1) {
            Type_Fid.setText("老师");
        } else {
            Type_Fid.setText("学生");
        }

        Phone_Lbl = new JLabel("电话:");
        Phone_Fid = new JLabel();
        Phone_Fid.setText(phone);
        Age_Lbl = new JLabel("年龄");
        Age_Fid = new JLabel();
        Age_Fid.setText(String.valueOf(age));

        Cancl_Btn = new JButton("返回");
        Cancl_Btn.setBounds(120,380,80,30);
        Cancl_Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Watch_Frm.dispose();
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

        Watch_Frm.add(Image_Pal);
        Watch_Frm.add(Cancl_Btn);
        Watch_Frm.add(Data_Pal);
        Watch_Frm.setVisible(true);
    }

    class ImagePal extends JPanel {
        private BufferedImage image;
        public ImagePal(String Path) {
            System.out.println(Path);
            if (Path == null) {
                try {
                    image = ImageIO.read(new File("/Users/sun/Documents/StuSystem/src/images/PicNotFound.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println(Path);
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
}
