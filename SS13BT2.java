import java.sql.*;
import java.util.Scanner;

public class SS13BT2 {

    static final String URL = "jdbc:mysql://localhost:3306/quanlysv";
    static final String USER = "root";
    static final String PASSWORD = "123456";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("===== MENU =====");
            System.out.println("1. Hien thi danh sach sinh vien");
            System.out.println("2. Them moi sinh vien");
            System.out.println("3. Thoat");

            int choice = 0;

            try {
                System.out.print("Nhap lua chon: ");
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Nhap sai kieu du lieu, vui long nhap lai!");
                continue;
            }

            switch (choice) {

                case 1:
                    showStudents();
                    break;

                case 2:
                    addStudent(sc);
                    break;

                case 3:
                    System.out.println("Thoat chuong trinh!");
                    return;

                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }

    // HIỂN THỊ DANH SÁCH SINH VIÊN

    public static void showStudents() {

        try (
                Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                CallableStatement call = conn.prepareCall("{call get_all_students()}");
                ResultSet rs = call.executeQuery();
        ) {

            System.out.println("===== DANH SACH SINH VIEN =====");

            while (rs.next()) {

                int id = rs.getInt("student_id");
                String name = rs.getString("full_name");
                Date dob = rs.getDate("date_of_birth");
                String email = rs.getString("email");

                System.out.println(id + " | " + name + " | " + dob + " | " + email);
            }

        } catch (Exception e) {
            System.out.println("Loi khi hien thi sinh vien!");
            e.printStackTrace();
        }
    }

    // THÊM SINH VIÊN

    public static void addStudent(Scanner sc) {

        try {

            System.out.print("Nhap ten sinh vien: ");
            String name = sc.nextLine();

            if (name.isEmpty()) {
                System.out.println("Ten khong duoc de trong!");
                return;
            }

            System.out.print("Nhap ngay sinh (yyyy-mm-dd): ");
            String dob = sc.nextLine();

            System.out.print("Nhap email: ");
            String email = sc.nextLine();

            if (email.isEmpty()) {
                System.out.println("Email khong duoc de trong!");
                return;
            }

            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            CallableStatement call = conn.prepareCall("{call add_student(?,?,?)}");

            call.setString(1, name);
            call.setDate(2, Date.valueOf(dob));
            call.setString(3, email);

            call.execute();

            System.out.println("Them sinh vien thanh cong!");

            conn.close();

        } catch (IllegalArgumentException e) {

            System.out.println("Sai dinh dang ngay sinh! (yyyy-mm-dd)");

        } catch (Exception e) {

            System.out.println("Loi khi them sinh vien!");
            e.printStackTrace();
        }
    }
}