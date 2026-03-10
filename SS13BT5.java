import java.sql.*;
import java.util.Scanner;

public class SS13BT5 {

    static final String URL = "jdbc:mysql://localhost:3306/movie_db";
    static final String USER = "root";
    static final String PASSWORD = "123456";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== MOVIE MANAGEMENT =====");
            System.out.println("1. Them phim");
            System.out.println("2. Liet ke phim");
            System.out.println("3. Sua phim");
            System.out.println("4. Xoa phim");
            System.out.println("5. Thoat");

            int choice;

            try {
                System.out.print("Nhap lua chon: ");
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Nhap sai dinh dang!");
                continue;
            }

            switch (choice) {

                case 1:
                    addMovie(sc);
                    break;

                case 2:
                    listMovies();
                    break;

                case 3:
                    updateMovie(sc);
                    break;

                case 4:
                    deleteMovie(sc);
                    break;

                case 5:
                    System.out.println("Thoat chuong trinh!");
                    return;

                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }

    // THEM PHIM

    public static void addMovie(Scanner sc) {

        try {

            System.out.print("Nhap tieu de phim: ");
            String title = sc.nextLine();

            System.out.print("Nhap dao dien: ");
            String director = sc.nextLine();

            System.out.print("Nhap nam phat hanh: ");
            int year = Integer.parseInt(sc.nextLine());

            if (title.isEmpty() || director.isEmpty()) {
                System.out.println("Thong tin khong duoc de trong!");
                return;
            }

            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            CallableStatement call =
                    conn.prepareCall("{call add_movie(?,?,?)}");

            call.setString(1, title);
            call.setString(2, director);
            call.setInt(3, year);

            call.execute();

            System.out.println("Them phim thanh cong!");

            conn.close();

        } catch (Exception e) {

            System.out.println("Du lieu nhap khong hop le!");
        }
    }

    // LIET KE PHIM

    public static void listMovies() {

        try {

            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            CallableStatement call =
                    conn.prepareCall("{call list_movies()}");

            ResultSet rs = call.executeQuery();

            System.out.println("\n===== DANH SACH PHIM =====");

            while (rs.next()) {

                int id = rs.getInt("id");
                String title = rs.getString("title");
                String director = rs.getString("director");
                int year = rs.getInt("release_year");

                System.out.println(id + " | " + title + " | " + director + " | " + year);
            }

            conn.close();

        } catch (Exception e) {

            System.out.println("Loi hien thi phim!");
            e.printStackTrace();
        }
    }

    // SUA PHIM

    public static void updateMovie(Scanner sc) {

        try {

            System.out.print("Nhap ID phim: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("Nhap tieu de moi: ");
            String title = sc.nextLine();

            System.out.print("Nhap dao dien moi: ");
            String director = sc.nextLine();

            System.out.print("Nhap nam moi: ");
            int year = Integer.parseInt(sc.nextLine());

            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            CallableStatement call =
                    conn.prepareCall("{call update_movie(?,?,?,?)}");

            call.setInt(1, id);
            call.setString(2, title);
            call.setString(3, director);
            call.setInt(4, year);

            call.execute();

            System.out.println("Cap nhat phim thanh cong!");

            conn.close();

        } catch (Exception e) {

            System.out.println("Du lieu khong hop le!");
        }
    }

    // XOA PHIM

    public static void deleteMovie(Scanner sc) {

        try {

            System.out.print("Nhap ID phim can xoa: ");
            int id = Integer.parseInt(sc.nextLine());

            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            CallableStatement call =
                    conn.prepareCall("{call delete_movie(?)}");

            call.setInt(1, id);

            call.execute();

            System.out.println("Xoa phim thanh cong!");

            conn.close();

        } catch (Exception e) {

            System.out.println("ID khong hop le!");
        }
    }
}