package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.User;
import dao.UserDao;

public class ManagementService{


	public void register(User user){
		Connection connection = null;
		try {
			connection = getConnection();
			UserDao.insert(connection, user);

			commit(connection);
		}catch (RuntimeException e){
			rollback(connection);
			throw e;
		}catch (Error e){
			rollback(connection);
			throw e;
		}finally{
			close(connection);
		}
	}

	public List<User> getManagement() {
		Connection connection = null;
		try {
			connection = getConnection();
			UserDao manageDao = new UserDao();
			List<User> ret = manageDao.getUserManagement(connection);

			commit(connection);
			return ret;

		}catch (RuntimeException e){
			rollback(connection);
			throw e;
		}catch (Error e){
			rollback(connection);
			throw e;
		}finally{
			close(connection);
		}
	}
}