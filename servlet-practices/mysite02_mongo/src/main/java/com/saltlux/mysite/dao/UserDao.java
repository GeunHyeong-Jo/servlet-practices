package com.saltlux.mysite.dao;

import java.time.Instant;
import java.util.Arrays;
import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.mongodb.client.model.Filters;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.saltlux.mysite.vo.UserVo;

public class UserDao {// https://www.kenwalger.com/blog/nosql/mongodb-and-java/ //주요참조 사이트

	@SuppressWarnings({ "resource" })
	public MongoCollection<Document> getConnection() {
		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoDatabase db = mongo.getDatabase("webdb");
		MongoCollection<Document> col = db.getCollection("user");
		return col;
	}

	@SuppressWarnings("resource")
	public MongoDatabase getConnectionForUpdate() {
		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoDatabase db = mongo.getDatabase("webdb");
		return db;
	}

	public int getCount() { // no값을 주기 위해서
		Long count = null;
		try (MongoClient client = new MongoClient("localhost", 27017)) {

			MongoDatabase database = client.getDatabase("webdb");
			MongoCollection<Document> collection = database.getCollection("user");
			// System.out.println("총 길이는 : "+collection.countDocuments());
			count = collection.countDocuments();

		} catch (MongoException e) {
			// handle MongoDB exception
		}
		int result = Math.toIntExact(count);
		return result;
	}

	public void Insert(UserVo uservo) {
		MongoCollection<Document> col = getConnection();
		try {
			int count = this.getCount();
			count++;
			Document doc_user = new Document();
			doc_user.append("no", count).append("name", uservo.getName()).append("email", uservo.getEmail())
					.append("password", uservo.getPassword()).append("gender", uservo.getGender())
					.append("joindate", Instant.now().toString());

			col.insertOne(doc_user);
			System.out.println("Successfully inserted documents. \n");

		} catch (Exception e) {
			System.out.println("userdao의 insert에서 오류 : " + e.getStackTrace());
		}

	}

	@SuppressWarnings({ "rawtypes", "null" })
	public UserVo findByNo(Long no) {
		MongoCollection col = getConnection();

		UserVo result = null;
		Document for_update = (Document) col.find(Filters.eq("no", no)).first();
		String json = for_update.toJson();
		System.out.println(json);

		JSONParser jParser = new JSONParser();
		try {
			JSONArray jsonArray = (JSONArray) jParser.parse("[" + json + "]");
			JSONObject obj = (JSONObject) jsonArray.get(0);

			result.setNo((Long) obj.get("no"));
			result.setName((String) obj.get("name"));
			result.setEmail((String) obj.get("email"));
			result.setPassword((String) obj.get("password"));
			result.setGender((String) obj.get("gender"));
			result.setJoinDate((String) obj.get("joinDate"));

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return result;
	}

	@SuppressWarnings("resource")
	public UserVo findByEmailAndPassword(UserVo vo) {

		UserVo result_Vo = new UserVo();
		try {
			// "select no, name from user where email = ? and password = ? ";

			MongoClient client = new MongoClient("localhost", 27017);
			MongoDatabase database = client.getDatabase("webdb");
			MongoCollection<Document> collection = database.getCollection("user");

			Document query = new Document();

			query.append("$and", Arrays.asList(new Document().append("email", vo.getEmail()),
					new Document().append("password", vo.getPassword())));

			Document resultDocument = collection.find(query).first();// 결과물
			System.out.println("userdao의 findByEmailAndPassword에서 쿼리 결과" + resultDocument);

			if (resultDocument != null) {
				int no = (int) resultDocument.get("no");
				String name = (String) resultDocument.get("name");
				System.out.println(name + "  " + no);

				System.out.println("findByEmailAndPassword에서 no = " + no);

				result_Vo.setNo((long) 1);
				result_Vo.setName(name);
				System.out.println("UserDao에서 로그인한 사람 이름 : " + result_Vo.getName());

			} else {
				result_Vo = null;
			}

		} catch (Exception e) {
			System.out.println("userdao의 findByEmailAndPassword에서 오류 발생 : " + e.getMessage());
		}
		return result_Vo;
	}

	public void Update(UserVo uservo) {
		try {
			System.out.println("update메서드에 넘어온 vo : " + uservo.toString());
			MongoCollection<Document> col = getConnection();
			MongoDatabase db = getConnectionForUpdate();

			Long no = uservo.getNo();
			String name = uservo.getName();
			String password = uservo.getPassword();
			String gender = uservo.getGender();

			Document for_update = col.find(Filters.eq("no", no)).first();
			System.out.println("디버깅용 : " + for_update.toJson());// 검색결과 출력

			// update하는 부분
			BasicDBObject query = new BasicDBObject();
			query.put("no", no); // 원본지점

			BasicDBObject newDocument = new BasicDBObject();
			newDocument.put("name", name); // 바꿀데이터부분
			newDocument.append("password", password).append("gender", gender).append("no", no);

			BasicDBObject updateObject = new BasicDBObject();
			updateObject.put("$set", newDocument); // 보내기

			db.getCollection("user").updateOne(query, updateObject); // 최종적으로 업데이트 쿼리

			/// https://kb.objectrocket.com/mongo-db/how-to-update-a-document-in-mongodb-using-java-384
			// TODO : 해당 사이트에 $inc 로 autoincrement 대체 시도해볼것

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String getEmailFindByNo(Long no) {// 세션의 no를 받아 email을 반환
		MongoCollection<Document> col = getConnection();

		String result = null;
		Document for_update = (Document) col.find(Filters.eq("no", no)).first();
		String json = for_update.toJson();
		System.out.println(json);

		JSONParser jParser = new JSONParser();
		try {
			JSONArray jsonArray = (JSONArray) jParser.parse("[" + json + "]");
			JSONObject obj = (JSONObject) jsonArray.get(0);

			result = (String) obj.get("email");

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return result;
	}

	public void delete() { // 안쓸테지만 일단 놓아둔다
		@SuppressWarnings("rawtypes")
		MongoCollection col = getConnection();
		col.deleteOne(Filters.eq("no", 100L));
	}
}
