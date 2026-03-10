import java.sql.*;
import java.util.Scanner;

public class SS13BT3{

    static final String URL = "jdbc:mysql://localhost:3306/quanlysv";
    static final String USER = "root";
    static final String PASSWORD = "123456";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== MENU =====");
            System.out.println("1. Hien thi danh sach sinh vien");
            System.out.println("2. Them moi sinh vien");
            System.out.println("3. Sua sinh vien");
            System.out.println("4. Xoa sinh vien");
            System.out.println("5. Thoat");

            int choice;

            try {
                System.out.print("Nhap lua chon: ");
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Nhap sai kieu du lieu! Nhap lai.");
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
                    updateStudent(sc);
                    break;

                case 4:
                    deleteStudent(sc);
                    break;

                case 5:
                    System.out.println("Thoat chuong trinh!");
                    return;

                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }

    // HIỂN THỊ SINH VIÊN

    public static void showStudents() {

        try (
                Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                CallableStatement call = conn.prepareCall("{call get_all_students()}");
                ResultSet rs = call.executeQuery();
        ) {

            System.out.println("\n===== DANH SACH SINH VIEN =====");

            while (rs.next()) {

                int id = rs.getInt("student_id");
                String name = rs.getString("full_name");
                Date dob = rs.getDate("date_of_birth");
                String email = rs.getString("email");

                System.out.println(id + " | " + name + " | " + dob + " | " + email);
            }

        } catch (Exception e) {
            System.out.println("Loi hien thi sinh vien!");
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

    // SỬA SINH VIÊN

    public static void updateStudent(Scanner sc) {

        try {

            System.out.print("Nhap ID sinh vien can sua: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("Nhap ten moi: ");
            String name = sc.nextLine();

            if (name.isEmpty()) {
                System.out.println("Ten khong duoc de trong!");
                return;
            }

            System.out.print("Nhap ngay sinh moi (yyyy-mm-dd): ");
            String dob = sc.nextLine();

            System.out.print("Nhap email moi: ");
            String email = sc.nextLine();

            if (email.isEmpty()) {
                System.out.println("Email khong duoc de trong!");
                return;
            }

            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            CallableStatement call =
                    conn.prepareCall("{call update_student(?,?,?,?)}");

            call.setInt(1, id);
            call.setString(2, name);
            call.setDate(3, Date.valueOf(dob));
            call.setString(4, email);

            call.execute();

            System.out.println("Cap nhat sinh vien thanh cong!");

            conn.close();

        } catch (NumberFormatException e) {

            System.out.println("ID phai la so!");

        } catch (IllegalArgumentException e) {

            System.out.println("Sai dinh dang ngay! yyyy-mm-dd");

        } catch (Exception e) {

            System.out.println("Loi khi cap nhat!");
            e.printStackTrace();
        }
    }

    // XÓA SINH VIÊN

    public static void deleteStudent(Scanner sc) {

        try {

            System.out.print("Nhap ID sinh vien can xoa: ");
            int id = Integer.parseInt(sc.nextLine());

            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            CallableStatement call =
                    conn.prepareCall("{call delete_student(?)}");

            call.setInt(1, id);

            call.execute();

            System.out.println("Xoa sinh vien thanh cong!");

            conn.close();

        } catch (NumberFormatException e) {

            System.out.println("ID phai la so!");

        } catch (Exception e) {

            System.out.println("Loi khi xoa sinh vien!");
            e.printStackTrace();
        }
    }
}