import java.sql.*;
import java.util.Scanner;

class TaskManagement {

    String url = "jdbc:mysql://localhost:3306/todo_db";
    String user = "root";
    String password = "123456";

    public Connection getConnection() throws Exception {
        return DriverManager.getConnection(url, user, password);
    }

    // thêm task
    public void addTask(String taskName, String status) {

        try {

            if(taskName.isEmpty() || status.isEmpty()){
                System.out.println("Du lieu khong duoc de trong!");
                return;
            }

            Connection conn = getConnection();

            CallableStatement call =
                    conn.prepareCall("{call add_task(?,?)}");

            call.setString(1, taskName);
            call.setString(2, status);

            call.execute();

            System.out.println("Them cong viec thanh cong");

            conn.close();

        } catch (Exception e) {

            System.out.println("Loi them cong viec");
        }
    }

    // list tasks
    public void listTasks() {

        try {

            Connection conn = getConnection();

            CallableStatement call =
                    conn.prepareCall("{call list_tasks()}");

            ResultSet rs = call.executeQuery();

            System.out.println("\n===== DANH SACH CONG VIEC =====");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("task_name") + " | " +
                                rs.getString("status")
                );
            }

            conn.close();

        } catch (Exception e) {

            System.out.println("Loi hien thi");
        }
    }

    // update status
    public void updateTaskStatus(int taskId, String status) {

        try {

            Connection conn = getConnection();

            CallableStatement call =
                    conn.prepareCall("{call update_task_status(?,?)}");

            call.setInt(1, taskId);
            call.setString(2, status);

            call.execute();

            System.out.println("Cap nhat thanh cong");

            conn.close();

        } catch (Exception e) {

            System.out.println("Loi cap nhat");
        }
    }

    // delete task
    public void deleteTask(int taskId) {

        try {

            Connection conn = getConnection();

            CallableStatement call =
                    conn.prepareCall("{call delete_task(?)}");

            call.setInt(1, taskId);

            call.execute();

            System.out.println("Xoa thanh cong");

            conn.close();

        } catch (Exception e) {

            System.out.println("Loi xoa");
        }
    }

    // search task
    public void searchTaskByName(String taskName) {

        try {

            Connection conn = getConnection();

            CallableStatement call =
                    conn.prepareCall("{call search_task_by_name(?)}");

            call.setString(1, taskName);

            ResultSet rs = call.executeQuery();

            System.out.println("\n===== KET QUA TIM KIEM =====");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("task_name") + " | " +
                                rs.getString("status")
                );
            }

            conn.close();

        } catch (Exception e) {

            System.out.println("Loi tim kiem");
        }
    }

    // statistics
    public void taskStatistics() {

        try {

            Connection conn = getConnection();

            CallableStatement call =
                    conn.prepareCall("{call task_statistics()}");

            ResultSet rs = call.executeQuery();

            System.out.println("\n===== THONG KE =====");

            while (rs.next()) {

                System.out.println(
                        rs.getString("status") +
                                " : " +
                                rs.getInt("total")
                );
            }

            conn.close();

        } catch (Exception e) {

            System.out.println("Loi thong ke");
        }
    }
}

public class SS13BT6 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        TaskManagement task = new TaskManagement();

        while (true) {

            System.out.println("\n===== TODO LIST =====");
            System.out.println("1. Them cong viec");
            System.out.println("2. Hien thi cong viec");
            System.out.println("3. Cap nhat trang thai");
            System.out.println("4. Xoa cong viec");
            System.out.println("5. Tim kiem cong viec");
            System.out.println("6. Thong ke");
            System.out.println("7. Thoat");

            try {

                System.out.print("Chon: ");
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:

                        System.out.print("Ten cong viec: ");
                        String name = sc.nextLine();

                        System.out.print("Trang thai (done/chua xong): ");
                        String status = sc.nextLine();

                        task.addTask(name, status);
                        break;

                    case 2:
                        task.listTasks();
                        break;

                    case 3:

                        System.out.print("Nhap ID: ");
                        int id = Integer.parseInt(sc.nextLine());

                        System.out.print("Trang thai moi: ");
                        String st = sc.nextLine();

                        task.updateTaskStatus(id, st);
                        break;

                    case 4:

                        System.out.print("Nhap ID can xoa: ");
                        int deleteId = Integer.parseInt(sc.nextLine());

                        task.deleteTask(deleteId);
                        break;

                    case 5:

                        System.out.print("Nhap ten cong viec: ");
                        String search = sc.nextLine();

                        task.searchTaskByName(search);
                        break;

                    case 6:
                        task.taskStatistics();
                        break;

                    case 7:
                        System.out.println("Thoat chuong trinh!");
                        return;

                    default:
                        System.out.println("Lua chon sai!");
                }

            } catch (Exception e) {

                System.out.println("Nhap sai dinh dang!");
            }
        }
    }
}