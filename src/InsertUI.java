import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by sun on 16/12/7.
 */
public class InsertUI extends JFrame {
    JFrame Insert_Frm;
    JPanel Data_Pal;
    ImagePal Image_Pal;
    JButton InsertBtn, CanclBtn,Brow_Btn;
    JTextField Username_Fid, ID_Fid, Type_Fid, Phone_Fid, Age_Fid;
    JLabel Username_Lbl, ID_Lbl, Type_Lbl, Phone_Lbl, Age_Lbl;
    String Path;

    InsertUI(String Path) {
        this.Path = Path;
        Insert_Frm = new JFrame("插入数据");
        Insert_Frm.setLayout(null);
        Insert_Frm.setBounds(483,156,300,450);

        Data_Pal = new JPanel();
        Data_Pal.setBounds(80,200,150,190);
        Data_Pal.setLayout(new GridLayout(6,2));

        Image_Pal = new ImagePal(Path);
        Image_Pal.setBounds(85,20,130,160);

        ID_Lbl = new JLabel("ID:");
        ID_Fid = new JTextField(3);
        Username_Lbl = new JLabel("姓名:");
        Username_Fid = new JTextField(6);
        Type_Lbl = new JLabel("类型");
        Type_Fid = new JTextField(2);
        Phone_Lbl = new JLabel("电话:");
        Phone_Fid = new JTextField(13);
        Age_Lbl = new JLabel("年龄");
        Age_Fid = new JTextField(3);
        InsertBtn = new JButton("插入");
        InsertBtn.setBounds(60,380,80,30);
        CanclBtn = new JButton("返回");
        CanclBtn.setBounds(160,380,80,30);

        InsertBtn.addActionListener(new InsertionListener());

        CanclBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Insert_Frm.dispose();
            }
        });

        Brow_Btn = new JButton("…");
        Brow_Btn.setBounds(230,158,20,20);
        Brow_Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Insert_Frm.dispose();
                FileChooser PicChoser = new FileChooser();
                String temp_Path = PicChoser.getPath();
                new InsertUI(temp_Path);
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

        Insert_Frm.add(Image_Pal);
        Insert_Frm.add(InsertBtn);
        Insert_Frm.add(CanclBtn);
        Insert_Frm.add(Data_Pal);
        Insert_Frm.add(Brow_Btn);
        Insert_Frm.setVisible(true);
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

    private int VerifyEmptyListner() {
        if (ID_Fid.getText().trim().isEmpty() || Username_Fid.getText().trim().isEmpty()
        || Phone_Fid.getText().trim().isEmpty() || Age_Fid.getText().trim().isEmpty() ||
                Type_Fid.getText().trim().isEmpty())
        {
            return 0;
        }
        return 1;
    }

    class InsertionListener implements ActionListener {
        public void showResultTable() {
            String[] columnname = new String[]{"ID", "TYPE", "NAME", "PHONE", "AGE","PicPath"};
            String id = ID_Fid.getText();
            String type = Type_Fid.getText();
            String name = "'" + Username_Fid.getText() + "'";
            String phone = "'" + Phone_Fid.getText() + "'";
            String age = Age_Fid.getText();
            String picpath = "\"" + Path + "\"";
            String[] inputvalues = new String[]{id, type, name, phone, age, picpath};
            String sql = "INSERT INTO People ";
            String cosql = "(ID";
            String vasql = "values(" + id;
            for (int i = 1; i < columnname.length; i++) {
                if (!inputvalues[i].trim().isEmpty()) {
                    cosql += "," + columnname[i];
                    vasql += "," + inputvalues[i];
                }
            }
            sql += cosql + ")" + vasql + ")";
            System.out.println(sql);
            int rs = new LoadData().UpdateData(sql);
            if (rs == 1) {
                new NoticeUI("恭喜你", "恭喜,成功一条数据");
                Insert_Frm.dispose();
            } else {
                new NoticeUI("遗憾", "这条数据添加失败咯");
                Insert_Frm.dispose();
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
}


