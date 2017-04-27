package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

import beans.Message;
import beans.UserMessage;
import dao.MessageDao;
import dao.UserMessageDao;

public class MessageService {

	public void register(Message messages) {

		Connection connection = null;
		try {
			connection = getConnection();

			MessageDao messageDao = new MessageDao();
			messageDao.insert(connection, messages);

			commit (connection);
		} catch (RuntimeException e) {
			rollback (connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}


	public Timestamp getCreatedAt() {//カテゴリ

		Connection connection = null;

		try {
			connection = getConnection();

			UserMessageDao messageDao = new UserMessageDao();
			Timestamp ret = messageDao.getCreatedAt(connection);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}


	public List<UserMessage> getMessage() {

		Connection connection = null;
		try {
			connection = getConnection();

			UserMessageDao messageDao = new UserMessageDao();
			List<UserMessage> ret = messageDao.getUserMessages(connection);

			commit(connection);
			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}


	public void delete(Message messages) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserMessageDao messageDao = new UserMessageDao();
			messageDao.getDelete(connection, messages);

			commit (connection);
		} catch (RuntimeException e) {
			rollback (connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
}