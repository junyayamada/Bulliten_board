package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import beans.Comment;
import exception.SQLRuntimeException;

public class CommentDao {

	public static void insert(Connection connection, Comment comment) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO comments ( " );
			sql.append(" text");
			sql.append(", user_id");
			sql.append(", message_id");
			sql.append(", created_at");
			sql.append(") VALUES (");
			sql.append(" ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", CURRENT_TIMESTAMP");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, comment.getText());
			ps.setInt(2, comment.getUserId());
			ps.setInt(3, comment.getMessageId());
			ps.executeUpdate(); //insert_date

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


	public List<Comment> getUserComment(Connection connection) {//コメント

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM comments ");
			sql.append("ORDER BY created_at ASC ");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Comment> ret = toCommentList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


	public List<Comment> toCommentList (ResultSet rs)throws SQLException {
	List<Comment> ret = new ArrayList<Comment>();
	try {

		while (rs.next()) {
			int id = rs.getInt("id");
			String text = rs.getString("text");
			int user_id = rs.getInt("user_id");
			int message_id = rs.getInt("message_id");
			Timestamp created_at = rs.getTimestamp("created_at");

			Comment message = new Comment();
			message.setId(id);
			message.setText(text);
			message.setUserId(user_id);
			message.setMessageId(message_id);
			message.setCreatedAt(created_at);

			ret.add(message);
			ret.add(message);
		}
		return ret;
	} finally {
		close(rs);
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


	public void getDelete(Connection connection, Comment comments) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM board.comments ");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, comments.getUserId());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}




//package dao;
//
//import static utils.CloseableUtil.*;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.List;
//
//import beans.Comment;
//import exception.SQLRuntimeException;
//
//public class CommentDao {
//
//
//
//
//
//
//	private Timestamp toCreatedAtList1(ResultSet rs)throws SQLException {
//
//		try {
//			while (rs.next()) {
//				return rs.getTimestamp("created_at");
//			}
//			return null;
//		} finally {
//			close(rs);
//		}
//	}
//
//	private Timestamp toCreatedAtList2(ResultSet rs)throws SQLException {
//
//		try {
//			while (rs.next()) {
//				return rs.getTimestamp("created_at");
//			}
//			return null;
//		} finally {
//			close(rs);
//		}
//	}
//
//