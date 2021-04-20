package com.saltlux.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.saltlux.mysite.vo.BoardVo;
import com.saltlux.mysite.vo.PagerVo;

public class BoardDao {// https://drsggg.tistory.com/222

	public int getCount() { // 모든글의 갯수
		Long count = null;
		try (MongoClient client = new MongoClient("localhost", 27017)) {
			MongoDatabase database = client.getDatabase("webdb");
			MongoCollection<Document> collection = database.getCollection("board");
			// System.out.println("총 길이는 : "+collection.countDocuments());
			count = collection.countDocuments();

		} catch (MongoException e) {
			// handle MongoDB exception
		}
		int result = Math.toIntExact(count);

		return result;
	}

	public Long getCountLong() { // 모든글의 갯수
		Long count = null;
		try (MongoClient client = new MongoClient("localhost", 27017)) {
			MongoDatabase database = client.getDatabase("webdb");
			MongoCollection<Document> collection = database.getCollection("board");
			// System.out.println("총 길이는 : "+collection.countDocuments());
			count = collection.countDocuments();

		} catch (MongoException e) {
			// handle MongoDB exception
		}

		return count;
	}

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

	public void updateView(Long title_no) { // 조회수를 증가
		try (MongoClient client = new MongoClient("localhost", 27017)) {

			MongoDatabase database = client.getDatabase("webdb");
			@SuppressWarnings("rawtypes")
			MongoCollection collection = database.getCollection("board");

			Document getquery = (Document) collection.find(Filters.eq("no", title_no)).first();
			String json = getquery.toJson();
			JSONParser jParser = new JSONParser();
			JSONArray jsonArray = (JSONArray) jParser.parse("[" + json + "]");
			JSONObject obj = (JSONObject) jsonArray.get(0);
			int views = (int) obj.get("views");
			views++;
			BasicDBObject query = new BasicDBObject();
			query.put("no", title_no); // 원본지점

			BasicDBObject newDocument = new BasicDBObject();
			newDocument.put("views", views); // 바꿀데이터부분

			BasicDBObject updateObject = new BasicDBObject();
			updateObject.put("$set", newDocument); // 보내기

			database.getCollection("board").updateOne(query, updateObject); // 최종적으로 업데이트 쿼리

		} catch (MongoException | ParseException e) {
			System.out.println("updateView에서 오류 : " + e.getStackTrace());
		}

	}

	public void delete(BoardVo vo) {// 게시글의 삭제
		try (MongoClient client = new MongoClient("localhost", 27017)) {

			MongoDatabase database = client.getDatabase("webdb");
			@SuppressWarnings("rawtypes")
			MongoCollection collection = database.getCollection("board");
			collection.deleteOne(Filters.and(Filters.eq("no", vo.getNo()), Filters.eq("user_no", vo.getUser_no())));
			// Filter를 이렇게 중첩시키는지는 모르겠다 ㅠㅠ

		} catch (Exception e) {
			System.out.println("board.delete 에서 오류 : " + e.getStackTrace());
		}

	}

	public void insert(BoardVo vo) { // 글쓰기, 답글쓰기
		try (MongoClient client = new MongoClient("localhost", 27017)) {

			MongoDatabase database = client.getDatabase("webdb");
			MongoCollection<Document> collection = database.getCollection("board");
			Long count = this.getCountLong();
			count++;
			Document document = new Document();
			document.append("no", count).append("title", vo.getTitle()).append("author", vo.getAuthor())
					.append("views", 0).append("reg_date", Instant.now().toString()).append("content", vo.getContent())
					.append("g_no", vo.getG_no()).append("o_no", vo.getO_no()).append("depth", vo.getDepth())
					.append("user_no", vo.getUser_no());
			collection.insertOne(document);
			System.out.println("Successfully inserted documents");
		} catch (Exception e) {
			System.out.println("Board insert에서 오류발생 : " + e.getStackTrace());
		}

	}

	public void update(BoardVo vo) { // 글 수정

		try (MongoClient client = new MongoClient("localhost", 27017)) {

			MongoDatabase database = client.getDatabase("webdb");
			// MongoCollection<Document> collection = database.getCollection("board");

			BasicDBObject query = new BasicDBObject();
			query.put("no", vo.getNo()); // where 기능

			BasicDBObject newDocument = new BasicDBObject();
			newDocument.put("title", vo.getTitle()); // 바꿀데이터부분
			newDocument.put("content", vo.getContent());

			BasicDBObject updateObject = new BasicDBObject();
			updateObject.put("$set", newDocument); // 보내기

			database.getCollection("board").updateOne(query, updateObject);

		} catch (MongoException e) {

		}

	}

	public List<BoardVo> findAll(PagerVo pagerVo) { // 전체 리스트 출력을 위해서

		List<BoardVo> list = new ArrayList<>();
		try (MongoClient client = new MongoClient("localhost", 27017)) {
			MongoDatabase database = client.getDatabase("webdb");
			MongoCollection<Document> collection = database.getCollection("board");
			ArrayList<Document> arr = new ArrayList<Document>();
			Document query = new Document();

			Document sort = new Document();

			sort.append("g_no", -1.0);
			sort.append("o_no", 1.0);

//			int start= (pagerVo.getNowPage() - 1) * pagerVo.getCntPerPage()-1;
//			int end =pagerVo.getCntPerPage();
			collection.find(query).sort(sort).into(arr);

			for (Document doc : arr) {
				BoardVo vo = new BoardVo();
				vo.setNo(doc.getLong("no"));
				vo.setTitle(doc.getString("title"));
				vo.setAuthor(doc.getString("author"));
				vo.setViews(doc.getLong("view"));
				vo.setReg_date(doc.getString("reg_date"));
				vo.setContent(doc.getString("contents"));
				vo.setG_no(doc.getLong("g_no"));
				vo.setO_no(doc.getLong("o_no"));
				vo.setDepth(doc.getLong("o_no"));
				vo.setUser_no(doc.getLong("user_no"));
				list.add(vo);
			}

		} catch (MongoException e) {
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
