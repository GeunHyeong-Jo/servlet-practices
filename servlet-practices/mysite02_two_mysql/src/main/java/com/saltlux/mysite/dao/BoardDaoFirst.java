package com.saltlux.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.saltlux.mysite.vo.BoardVo;
import com.saltlux.mysite.vo.PagerVo;

public class BoardDaoFirst {// https://drsggg.tistory.com/222

	public int updateOrder(Long g_no, Long o_no) { // 원글의 g_no, o_no를 입력받는다
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		// JDBC코드
		try {
			conn = getConnection();
			// 3. SQL 준비
			String sql = "update board SET o_no=o_no+1 WHERE g_no = ? AND o_no >= ?";
			pstmt = conn.prepareStatement(sql);
			// 4. 바인딩
			pstmt.setLong(1, g_no);
			pstmt.setLong(2, o_no);

			// 5. SQL문 실행
			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {// 자원 정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	public boolean updateView(Long title_no) { // 조회수를 증가
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;

		// JDBC코드
		try {
			conn = getConnection();
			// 3. SQL 준비
			String sql = "update board SET views =views+1 WHERE NO=?";
			pstmt = conn.prepareStatement(sql);
			// 4. 바인딩
			pstmt.setLong(1, title_no);
			// 5. SQL문 실행
			int count = pstmt.executeUpdate();

			result = count == 1;
		} catch (SQLException e) {
			// 1. 사과
			// 2. log
			System.out.println("error: " + e);
			// 3. 안전하게 종료
		} finally {// 자원 정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public boolean delete(BoardVo vo) {// 게시글의 삭제
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "delete from board where no = ? and user_no= ?";
			pstmt = conn.prepareStatement(sql);
			// 4. 바인딩
			pstmt.setLong(1, vo.getNo());
			pstmt.setLong(2, vo.getUser_no());

			// 5. SQL문 실행
			int count = pstmt.executeUpdate();

			// 6. 결과
			result = count == 1; // 아래 코드처럼 써도 되는데 이렇게 쓰는게 권장된다.
//			if(count==1) {
//				result=true;
//			}
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {// 자원 정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	public boolean insert(BoardVo vo) { // 글쓰기, 답글쓰기
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "insert into board values(null, ?, ?, 0,now(),?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			// 4. 바인딩
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getAuthor());
			pstmt.setString(3, vo.getContent());
			pstmt.setLong(4, vo.getG_no());
			pstmt.setLong(5, vo.getO_no());
			pstmt.setLong(6, vo.getDepth());
			pstmt.setLong(7, vo.getUser_no());

			// 5. SQL문 실행
			int count = pstmt.executeUpdate();

			// 6. 결과
			result = count == 1; // 아래 코드처럼 써도 되는데 이렇게 쓰는게 권장된다.
//			if(count==1) {
//				result=true;
//			}
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {// 자원 정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public boolean update(BoardVo vo) { // 글 수정
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "update board set title=?, content=? where no = ? ";

			pstmt = conn.prepareStatement(sql);
			// 4. 바인딩
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getNo());

			// 5. SQL문 실행
			int count = pstmt.executeUpdate();

			// 6. 결과
			result = count == 1; // 아래 코드처럼 써도 되는데 이렇게 쓰는게 권장된다.
//			if(count==1) {
//				result=true;
//			}
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {// 자원 정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private Connection getConnection() throws SQLException {// 중복된 코드를 줄이고 실수를 방지
		Connection conn = null;
		// 1. JDBC Driver 로딩
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://localhost:3306/repl_db?characterEncoding=utf8&serverTimezone=Asia/Seoul";
			conn = DriverManager.getConnection(url, "user1", "test123");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;

	}

}
