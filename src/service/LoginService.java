package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;

import beans.User;
import dao.UserDao;
import utils.CipherUtil;

public class LoginService {
	public User login(String account, String password) {

		Connection connection = null;
		try {
			connection = getConnection();
			UserDao userDao = new UserDao();
			String encPassword = CipherUtil.encrypt(password);
			User users = userDao.getUser(connection, account, encPassword);

			commit(connection);
			return users;
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
}