import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class FileChooser{
    String Path;
    FileChooser() {
        JFileChooser File_Chooser = new JFileChooser("/Users/sun/Documents/StuSystem/src/Pics/");
        File_Chooser.setAcceptAllFileFilterUsed(false);
        File_Chooser.setMultiSelectionEnabled(false);
        File_Chooser.addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                // 文件过滤
                return f.getName().toLowerCase().endsWith(".png");
            }

            @Override
            public String getDescription() {
                return null;
            }
        });

        File_Chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        if (JFileChooser.APPROVE_OPTION == File_Chooser.showDialog(new JLabel(), "选择图片")) {
            // 用户点击了确定
            Path = File_Chooser.getSelectedFile().getAbsolutePath();
        }
    }
    public String getPath() {
        return Path;
    }
}