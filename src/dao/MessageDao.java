package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Message;
import exception.SQLRuntimeException;

public class MessageDao {

	public void insert (Connection connection, Message messages) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO messages (");
			sql.append("  subject");
			sql.append(", category");
			sql.append(", text");
			sql.append(", user_id");
			sql.append(", created_at");
			sql.append(") VALUES (");

			sql.append(" ?"); //subject
			sql.append(", ?"); //text
			sql.append(", ?"); //category
			sql.append(", ?"); //userId
			sql.append(", CURRENT_TIMESTAMP"); //created_at
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, messages.getSubject());
			ps.setString(2, messages.getCategory());
			ps.setString(3, messages.getText());
			ps.setInt(4, messages.getUserId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}