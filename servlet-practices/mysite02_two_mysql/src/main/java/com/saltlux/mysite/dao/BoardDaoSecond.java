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

public class BoardDaoSecond {// https://drsggg.tistory.com/222

	// TODO 페이징 처리 하면 종료!!!

	public int getCount() { // 모든글의 갯수
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// JDBC코드
		try {
			conn = getConnection();
			// 3. SQL 준비
			String sql = "select count(*) from board";
			pstmt = conn.prepareStatement(sql);
			// 4. 바인딩
			// 5. SQL문 실행
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			System.out.println("error: " + e);
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
		return count;
	}

	public List<BoardVo> findAll(PagerVo pagerVo) { // 전체 리스트 출력을 위해서
		List<BoardVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// JDBC코드
		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "select no,title, author,views, date_format(reg_date, '%Y-%m-%d %H:%i:%s'), content, g_no,o_no,depth, user_no from board "
					+ " order by g_no DESC, o_no ASC limit ?, ?";

			pstmt = conn.prepareStatement(sql);
			// 4. 바인딩
			pstmt.setInt(1, (pagerVo.getNowPage() - 1) * pagerVo.getCntPerPage());
			pstmt.setInt(2, pagerVo.getCntPerPage());
			// 5. SQL문 실행
			rs = pstmt.executeQuery();

			// 6. 데이터 가져오기
			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String author = rs.getString(3);
				Long views = rs.getLong(4);
				String reg_date = rs.getString(5);
				String contents = rs.getString(6);
				Long g_no = rs.getLong(7);
				Long o_no = rs.getLong(8);
				Long depth = rs.getLong(9);
				Long user_no = rs.getLong(10);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setAuthor(author);
				vo.setViews(views);
				vo.setViews(views);
				vo.setReg_date(reg_date);
				vo.setContent(contents);
				vo.setG_no(g_no);
				vo.setO_no(o_no);
				vo.setDepth(depth);
				vo.setUser_no(user_no);

				list.add(vo);
			}
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
		return list;
	}

	public BoardVo findByNo(Long boardNo) { // 게시글 조회 등
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo vo = new BoardVo();
		// JDBC코드
		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "select no,title, author,views, date_format(reg_date, '%Y-%m-%d %H:%i:%s'), content, g_no,o_no,depth, user_no from board where no=?";
			pstmt = conn.prepareStatement(sql);
			// 4. 바인딩
			pstmt.setLong(1, boardNo);

			// 5. SQL문 실행
			rs = pstmt.executeQuery();

			// 6. 데이터 가져오기
			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String author = rs.getString(3);
				Long views = rs.getLong(4);
				String reg_date = rs.getString(5);
				String contents = rs.getString(6);
				Long g_no = rs.getLong(7);
				Long o_no = rs.getLong(8);
				Long depth = rs.getLong(9);
				Long user_no = rs.getLong(10);

				vo.setNo(no);
				vo.setTitle(title);
				vo.setAuthor(author);
				vo.setViews(views);
				vo.setReg_date(reg_date);
				vo.setContent(contents);
				vo.setG_no(g_no);
				vo.setO_no(o_no);
				vo.setDepth(depth);
				vo.setUser_no(user_no);
			}

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
		return vo;
	}

	public Long getMaxGno() { // 제일 높은 g_no를 얻어온다

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Long result = null;
		// JDBC코드
		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "SELECT MAX(g_no) FROM board ";
			pstmt = conn.prepareStatement(sql);
			// 5. SQL문 실행
			rs = pstmt.executeQuery();

			// 6. 데이터 가져오기
			while (rs.next()) {
				result = rs.getLong(1);
			}

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

	private Connection getConnection() throws SQLException {// 중복된 코드를 줄이고 실수를 방지
		Connection conn = null;
		// 1. JDBC Driver 로딩
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://localhost:3307/repl_db?characterEncoding=utf8&serverTimezone=Asia/Seoul";
			conn = DriverManager.getConnection(url, "user1", "test123");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;

	}

}
