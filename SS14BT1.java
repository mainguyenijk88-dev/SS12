import java.sql.*;
import java.util.*;

class Student {

    private int id;
    private String name;
    private int age;

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

class StudentManager {

    String url = "jdbc:mysql://localhost:3306/student_db";
    String user = "root";
    String password = "123456";

    public void addStudents(List<Student> students) {

        Connection conn = null;

        try {

            conn = DriverManager.getConnection(url, user, password);

            // bắt đầu transaction
            conn.setAutoCommit(false);

            CallableStatement call =
                    conn.prepareCall("{call add_students(?,?)}");

            for (Student s : students) {

                call.setString(1, s.getName());
                call.setInt(2, s.getAge());

                call.execute();
            }

            // commit nếu thành công
            conn.commit();

            System.out.println("Đã thêm sinh viên thành công.");

        } catch (Exception e) {

            try {

                if (conn != null) {
                    conn.rollback();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            System.out.println("Lỗi khi thêm sinh viên.");

        } finally {

            try {

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

public class SS14BT1 {

    public static void main(String[] args) {

        StudentManager manager = new StudentManager();

        List<Student> students = Arrays.asList(

                new Student(0, "Nguyen Van A", 20),
                new Student(0, "Tran Thi B", 22),
                new Student(0, "Le Van C", 19)

        );

        manager.addStudents(students);
    }
}