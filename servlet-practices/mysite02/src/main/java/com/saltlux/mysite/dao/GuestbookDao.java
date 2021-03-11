package com.saltlux.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.saltlux.mysite.vo.GuestbookVo;

public class GuestbookDao {

	public boolean delete(GuestbookVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "delete from guestbook where no = ? and password=?";
			pstmt = conn.prepareStatement(sql);
			// 4. 바인딩
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());

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

	public boolean insert(GuestbookVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "insert into guestbook values(null, ?, ?, ?,now())";

			pstmt = conn.prepareStatement(sql);
			// 4. 바인딩
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContents());

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

	public List<GuestbookVo> findAll() {
		List<GuestbookVo> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// JDBC코드
		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "select no, name, date_format(reg_date, '%Y-%m-%d %H:%i:%s'), contents from guestbook order by reg_date desc";
			pstmt = conn.prepareStatement(sql);
			// 4. 바인딩

			// 5. SQL문 실행
			rs = pstmt.executeQuery();

			// 6. 데이터 가져오기
			while (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String reg_date = rs.getString(3);
				// String reg_date = rs.getTimestamp(3).toString();
				String contents = rs.getString(4);
				GuestbookVo vo = new GuestbookVo();

				vo.setNo(no);
				vo.setName(name);
				vo.setReg_date(reg_date);
				vo.setContents(contents);

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

	private Connection getConnection() throws SQLException {// 중복된 코드를 줄이고 실수를 방지
		Connection conn = null;
		// 1. JDBC Driver 로딩
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://localhost:3306/webdb?characterEncoding=utf8&serverTimezone=Asia/Seoul";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;

	}

}
