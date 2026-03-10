create schema quanlysv;
use quanlysv;
-- CREATE TABLE

CREATE TABLE Students (
                          student_id INT AUTO_INCREMENT PRIMARY KEY,
                          full_name VARCHAR(100) NOT NULL,
                          date_of_birth DATE NOT NULL,
                          email VARCHAR(100) NOT NULL UNIQUE
);

-- STORED PROCEDURE: get_all_students

DELIMITER //

CREATE PROCEDURE get_all_students()
BEGIN
    SELECT * FROM Students;
END //

DELIMITER ;

-- STORED PROCEDURE: add_student

DELIMITER //

CREATE PROCEDURE add_student(
    IN in_full_name VARCHAR(100),
    IN in_date_of_birth DATE,
    IN in_email VARCHAR(100)
)
BEGIN
    INSERT INTO Students(full_name, date_of_birth, email)
    VALUES(in_full_name, in_date_of_birth, in_email);
END //

DELIMITER ;

-- STORED PROCEDURE: update_student

DELIMITER //

CREATE PROCEDURE update_student(
    IN in_id INT,
    IN in_full_name VARCHAR(100),
    IN in_date_of_birth DATE,
    IN in_email VARCHAR(100)
)
BEGIN
    UPDATE Students
    SET
        full_name = in_full_name,
        date_of_birth = in_date_of_birth,
        email = in_email
    WHERE student_id = in_id;
END //

DELIMITER ;

-- STORED PROCEDURE: find_student_by_id

DELIMITER //

CREATE PROCEDURE find_student_by_id(
    IN in_id INT
)
BEGIN
    SELECT *
    FROM Students
    WHERE student_id = in_id;
END //

DELIMITER ;
-- STORED PROCEDURE: delete_student

DELIMITER //

CREATE PROCEDURE delete_student(
    IN in_id INT
)
BEGIN
    DELETE FROM Students
    WHERE student_id = in_id;
END //

DELIMITER ;

-- TEST CALL STORED PROCEDURES

-- Thêm sinh viên
CALL add_student('Nguyen Van A','2000-05-20','a@gmail.com');
CALL add_student('Tran Thi B','2001-03-10','b@gmail.com');

-- Xem tất cả sinh viên
CALL get_all_students();

-- Tìm sinh viên theo ID
CALL find_student_by_id(1);

-- Cập nhật sinh viên
CALL update_student(
        1,
        'Nguyen Van A Updated',
        '2000-05-20',
        'updated@gmail.com'
     );

-- Xóa sinh viên
CALL delete_student(2);

-- Kiểm tra lại danh sách
CALL get_all_students();