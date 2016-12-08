/**
 * Created by sun on 16/12/6.
 */
public class Teacher extends Student {
    public Teacher(String welCome) {
        super(welCome);
        Manage_Btn.setEnabled(true);
        Welcome_Lbl.setText(welCome + ", 您可以查询或管理学生信息。");
    }

    public static void main(String args[]) {
        new Teacher("老师你好, ");
    }
}
