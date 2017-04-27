package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.User;
import exception.NoRowsUpdatedRuntimeException;
import exception.SQLRuntimeException;

public class UserDao {

	private List<User> toUserList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String account = rs.getString("account");
				String password = rs.getString("password");
				int branch_id = rs.getInt("branch_id");
				int department_id = rs.getInt("department_id");
				int is_stoped = rs.getInt("is_stoped");

				User users = new User();
				users.setId(id);
				users.setName(name);
				users.setAccount(account);
				users.setPassword(password);
				users.setBranchId(branch_id);
				users.setDepartmentId(department_id);
				users.setIsStoped(is_stoped);

				ret.add(users);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	//ログイン
	public User getUser(Connection connection, String account, String encPassword) {
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE account = ? AND password = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, account);
			ps.setString(2, encPassword);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	//ユーザー登録
	public static void insert(Connection connection, User users) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO users ( ");
			sql.append("  name");
			sql.append(", account");
			sql.append(", password");
			sql.append(", branch_id");
			sql.append(", department_id");
			sql.append(", created_at");
			sql.append(", updated_at");

			sql.append(") VALUES (");
			sql.append("  ?"); //name
			sql.append(", ?"); //account
			sql.append(", ?"); //password
			sql.append(", ?"); //branch_id
			sql.append(", ?"); //department_id
			sql.append(", CURRENT_TIMESTAMP"); //created_at
			sql.append(", CURRENT_TIMESTAMP"); //updateed_at
			sql.append(") ");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, users.getName());
			ps.setString(2, users.getAccount());
			ps.setString(3, users.getPassword());
			ps.setInt(4, users.getBranchId());
			ps.setInt(5, users.getDepartmentId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	//ユーザー編集 doPost
	public void update(Connection connection, User users) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET");
			sql.append("  name = ?");
			sql.append(", account = ?");

			if (!users.getPassword().isEmpty()) {
				sql.append(", password = ?");
			}
			sql.append(", branch_id = ?");
			sql.append(", department_id = ?");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, users.getName());
			ps.setString(2, users.getAccount());
			if (!users.getPassword().isEmpty()) {
				ps.setString(3, users.getPassword());
				ps.setInt(4, users.getBranchId());
				ps.setInt(5, users.getDepartmentId());
				ps.setInt(6, users.getId());
			} else {
				ps.setInt(3, users.getBranchId());
				ps.setInt(4, users.getDepartmentId());
				ps.setInt(5, users.getId());

			}

			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	//ユーザー編集 doGet
	public User getUser(Connection connection, int id) {
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE id = ?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


	//ユーザー停止・復活
	public void updateStatus(Connection connection, User users) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET");
			sql.append(" is_stoped = ?");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, users.getIsStoped());
			ps.setInt(2, users.getId());
			ps.executeUpdate();

			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		}catch (SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}




	public User getSuffer(Connection connection, String account) {
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE account = ?";

			ps = connection.prepareStatement(sql);
			ps.setString(1, account);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


	public List<User> getUserManagement(Connection connection){
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users ");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<User> ret = toUserList(rs);

			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


	//削除
	public void delete(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM board.users");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, user.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}