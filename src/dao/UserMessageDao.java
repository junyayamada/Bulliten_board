package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import beans.Message;
import beans.UserMessage;
import exception.SQLRuntimeException;

public class UserMessageDao {

	public List<UserMessage> getUserMessages(Connection connection) { //投稿

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM user_message ");
			sql.append(" ORDER BY created_at DESC");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret = toUserMessageList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


	public List<UserMessage> getUserMessages(Connection connection, String start, String end, String category) { //投稿

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM user_message ");
//			sql.append("where  created_at >= ? and created_at <= ? ");
			if (!StringUtils.isEmpty(category)) {
				sql.append("and category = ?");
			}
			sql.append(" ORDER BY created_at DESC");
			ps = connection.prepareStatement(sql.toString());

//			ps.setString(1, start);
//			ps.setString(2, end);
//			if (!StringUtils.isEmpty(category)) {
//				ps.setString(3, category);
//			}

			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret = toUserMessageList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


	public Timestamp getCreatedAt(Connection connection) { //投稿時間取得

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM messages ");
			sql.append(" ORDER BY created_at limit 1" );

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			return  toCreatedAtList(rs);
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


	private Timestamp toCreatedAtList(ResultSet rs)throws SQLException {

		try {
			while (rs.next()) {
				return rs.getTimestamp("created_at");
			}
			return null;
		} finally {
			close(rs);
		}
	}


	private List<UserMessage> toUserMessageList(ResultSet rs)throws SQLException {//投稿

		List<UserMessage> ret = new ArrayList<UserMessage>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				int user_id = rs.getInt("user_id");
				int branch_id = rs.getInt("branch_id");
				int department_id = rs.getInt("department_id");
				String name = rs.getString("name");
				String account = rs.getString("account");
				String subject = rs.getString("subject");
				String text = rs.getString("text");
				String category = rs.getString("category");
				Timestamp created_at = rs.getTimestamp("created_at");

				UserMessage message = new UserMessage();
				message.setId(id);
				message.setUserId(user_id);
				message.setBranchId(branch_id);
				message.setDepartmentId(department_id);
				message.setName(name);
				message.setAccount(account);
				message.setSubject(subject);
				message.setCategory(category);
				message.setText(text);
				message.setCreatedAt(created_at);

				ret.add(message);
			}
			return ret;
		} finally {
			close(rs);
		}
	}


	public List<UserMessage> getUserMessage(Connection connection) {//カテゴリ

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT DISTINCT category FROM messages ");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret = toCategoryList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


	private List<UserMessage> toCategoryList(ResultSet rs)throws SQLException {//カテゴリ

		List<UserMessage> ret = new ArrayList<UserMessage>();
		try {
			while (rs.next()) {

				String category = rs.getString("category");

				UserMessage message = new UserMessage();
				message.setCategory(category);

				ret.add(message);
			}
			return ret;
		} finally {
			close(rs);
		}
	}


	public void getDelete(Connection connection, Message message) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM board.messages ");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, message.getUserId());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}