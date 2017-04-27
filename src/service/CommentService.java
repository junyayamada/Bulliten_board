package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Comment;
import beans.UserComment;
import dao.CommentDao;
import dao.UserCommentDao;

public class CommentService {


	public void register(Comment comment) {
		Connection connection = null;
		try {
			connection = getConnection();

			CommentDao commentDao = new CommentDao();
			commentDao.insert(connection, comment);

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


	public List<UserComment> getComment() {

		Connection connection = null;
		try {
			connection = getConnection();

			UserCommentDao commentDao = new UserCommentDao();
			List<UserComment> ret = commentDao.getUserComment(connection);

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


//	public List<UserMessage> getMessage(String start, String end, String category) {//投稿
//
//		Connection connection = null;
//		try {
//			connection = getConnection();
//
//			UserMessageDao messageDao = new UserMessageDao();
//			List<UserMessage> ret = messageDao.getUserMessages(connection, start, end, category);
//
//			commit(connection);
//			return ret;
//		} catch (RuntimeException e) {
//			rollback(connection);
//			throw e;
//		} catch (Error e) {
//			rollback(connection);
//			throw e;
//		} finally {
//			close(connection);
//		}
//	}


	public void delete(Comment comments) { //削除

		Connection connection = null;
		try {
			connection = getConnection();

			UserCommentDao commentDao = new UserCommentDao();
			commentDao.getDelete(connection, comments);

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




//package service;
//
//import static utils.CloseableUtil.*;
//import static utils.DBUtil.*;
//
//import java.sql.Connection;
//import java.util.List;
//
//import beans.Comment;
//import dao.CommentDao;
//
//public class CommentService {
//
//	public void register(Comment text) {
//		Connection connection = null;
//		try {
//			connection = getConnection();
//
//			CommentDao messageDao = new CommentDao();
//			List<Comment> ret = messageDao.getUserComment(connection);
//
//			commit (connection);
//		} catch (RuntimeException e) {
//			rollback (connection);
//			throw e;
//		} catch (Error e) {
//			rollback(connection);
//			throw e;
//		} finally {
//			close(connection);
//		}
//	}
//
//
//	public List<Comment> getNewComment() {
//
//		Connection connection = null;
//		try {
//			connection = getConnection();
//
//			CommentDao commentDao = new CommentDao();
//			List<Comment> ret = commentDao.getUserComment(connection);
//
//			commit(connection);
//			return ret;
//		} catch (RuntimeException e) {
//			rollback(connection);
//			throw e;
//		} catch (Error e) {
//			rollback(connection);
//			throw e;
//		} finally {
//			close(connection);
//		}
//	}
//
