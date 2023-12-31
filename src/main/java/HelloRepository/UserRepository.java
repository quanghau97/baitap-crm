package HelloRepository;
/**
 * Chứa tất cả các câu query liên quan đến bảng user
 * 
 * select : đại diện cho chữ find
 * where : đại diện bởi chữ by
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import helloservlet.config.MysqlConfig;
import helloservlet.entity.UserEntity;

public class UserRepository {
	
	public List<UserEntity>findByEmailAndPassword(String email, String password){
		// Bước 2: Chuẩn bị câu query (truy vấn)
				String query ="SELECT *\r\n"
						+ "FROM users u \r\n"
						+ "WHERE u.email = ? AND u.password = ? ";
				
				// Bước 3: Mở kết nối cơ sở dữ liệu 
				Connection connection = MysqlConfig.getConnection();
				// tạo list UserEntity để lưu trữ từng dòng dữ liệu query được
				List<UserEntity> listUser = new ArrayList<UserEntity>();
				
				//Bước 4: Truyền câu query vào CSDL vừa mở kết nối thông qua PrepareStatement
				try {
				PreparedStatement preparedStatement =  connection.prepareStatement(query);
				// Gán giá trị tham số dấu chấm ? trong câu query
				preparedStatement.setString(1, email);
				preparedStatement.setString(2, password);
				
				// Bước 5: thực thi câu query
				// Có 2 cách thực thi: 
				// executeQuery : Dành cho câu truy vấn là câu SELECT => luôn trả ra ResultSet
				// exxcuteUpdate : Dành cho tất cả câu truy vấn còn lại ngoài câu SELECT. VD : INSERT, UPDATE, DELETE,...
				ResultSet resultSet =  preparedStatement.executeQuery();
				
				
				// Bước 6: Duyệt từng dòng dữ liệu query được và gán vào List<UserEntity>
				while (resultSet.next()) {
					UserEntity entity = new UserEntity();
					entity.setId(resultSet.getInt("id")); // resultSet.getInt(Tên cột Database) Lấy giá trị
					// của cột id gán vào thuộc tính id của userentity
					entity.setFullname(resultSet.getString("fullname"));
					
					listUser.add(entity);

				}
				
				}catch (Exception e) {
					System.out.println("Lỗi findByEmailAndPassword " + e.getLocalizedMessage());
		
	}
				return listUser;
	}
	
}

				


