import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;

public class SS14BT2 {

    private static String url = "jdbc:mysql://localhost:3306/test";
    private static String user = "root";
    private static String password = "123456";

    public static void deleteStudentsByAge(int age) {

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            // bật transaction
            conn.setAutoCommit(false);

            CallableStatement stmt = conn.prepareCall("{CALL delete_students_by_age(?)}");

            stmt.setInt(1, age);

            int deletedRows = stmt.executeUpdate();

            conn.commit();

            System.out.println("Số sinh viên đã bị xóa: " + deletedRows);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        deleteStudentsByAge(21);

    }
}