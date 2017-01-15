import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by sun on 16/12/7.
 */
public class NoticeUI extends JDialog {
    NoticeUI(String title, String Message) {
        final JDialog Emptyerror = new JDialog();
        JLabel message_Lbl = new JLabel(Message);
        message_Lbl.setBounds(90,20,300,100);
        Emptyerror.setTitle(title);
        Emptyerror.setLayout(null);
        JButton Exit_Btn = new JButton("好的");
        Exit_Btn.setBounds(100,150,100,40);
        Exit_Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Emptyerror.dispose();
            }
        });
        Emptyerror.setSize(300,250);
        Emptyerror.setLocationRelativeTo(null);
        Emptyerror.add(message_Lbl);
        Emptyerror.add(Exit_Btn);
        Emptyerror.setVisible(true);
    }
}
