import java.sql.*;
import java.util.Scanner;

public class SS13BT4 {

    static final String URL = "jdbc:mysql://localhost:3306/quanlysv";
    static final String USER = "root";
    static final String PASSWORD = "123456";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            try {

                System.out.print("Nhap ten sinh vien can tim: ");
                String name = sc.nextLine();

                if (name.trim().isEmpty()) {
                    System.out.println("Ten khong duoc de trong! Nhap lai.");
                    continue;
                }

                Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

                CallableStatement call =
                        conn.prepareCall("{call search_student(?)}");

                call.setString(1, name);

                ResultSet rs = call.executeQuery();

                System.out.println("\n===== KET QUA TIM KIEM =====");

                boolean found = false;

                while (rs.next()) {

                    found = true;

                    int id = rs.getInt("student_id");
                    String fullName = rs.getString("full_name");
                    Date dob = rs.getDate("date_of_birth");
                    String email = rs.getString("email");

                    System.out.println(id + " | " + fullName + " | " + dob + " | " + email);
                }

                if (!found) {
                    System.out.println("Khong tim thay sinh vien!");
                }

                conn.close();

                break;

            } catch (Exception e) {

                System.out.println("Loi khi tim kiem sinh vien!");
                e.printStackTrace();
            }
        }
    }
}