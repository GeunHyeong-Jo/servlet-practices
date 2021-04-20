package com.saltlux.mysite.dao;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.saltlux.mysite.vo.GuestbookVo;

public class GuestbookDao {
	public int getCount() { // 모든글의 갯수
		Long count = null;
		try (MongoClient client = new MongoClient("localhost", 27017)) {

			MongoDatabase database = client.getDatabase("webdb");
			MongoCollection<Document> collection = database.getCollection("guestbook");
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
			MongoCollection<Document> collection = database.getCollection("guestbook");
			count = collection.countDocuments();

		} catch (MongoException e) {
			// handle MongoDB exception
		}

		return count;
	}

	public void delete(GuestbookVo vo) {
		try {
			// "delete from guestbook where no = ? and password=?"; ";

			MongoClient client = new MongoClient("localhost", 27017);
			MongoDatabase database = client.getDatabase("webdb");
			MongoCollection<Document> collection = database.getCollection("guestbook");

			Document doc = new Document().append("no", vo.getNo()).append("password", vo.getPassword());
			collection.deleteOne(doc);

		} catch (Exception e) {
			System.out.println("GuestbookDao의 delete에서 오류 발생 : " + e.getMessage());
		}

	}

	public void insert(GuestbookVo vo) {

		try (MongoClient client = new MongoClient("localhost", 27017)) {
			MongoDatabase database = client.getDatabase("webdb");
			MongoCollection<Document> collection = database.getCollection("guestbook");

			Long count = this.getCountLong();
			count++;

			Document document = new Document();
			document.append("no", count).append("name", vo.getName()).append("password", vo.getPassword())
					.append("contents", vo.getContents()).append("reg_date", Instant.now().toString());
			collection.insertOne(document);
			System.out.println("Successfully inserted documents");
		} catch (Exception e) {
			System.out.println("Board insert에서 오류발생 : " + e.getMessage());
		}
	}

	public List<GuestbookVo> findAll() {
		List<GuestbookVo> list = new ArrayList<>();

		try (MongoClient client = new MongoClient("localhost", 27017)) {

			MongoDatabase database = client.getDatabase("webdb");
			MongoCollection<Document> collection = database.getCollection("guestbook");
			ArrayList<Document> arr = new ArrayList<Document>();

			Document query = new Document();
			Document sort = new Document();
			sort.append("no", -1.0);

			collection.find(query).sort(sort).into(arr);

			for (Document doc : arr) {

				GuestbookVo vo = new GuestbookVo();
				Long i = doc.getLong("no");
				vo.setNo(i);
				vo.setName((String) doc.get("name"));
				vo.setPassword((String) doc.get("password"));
				vo.setContents((String) doc.get("contents"));
				// Date date = (Date) doc.get("reg_date");
				vo.setReg_date((String) doc.get("reg_date"));
				list.add(vo);
				System.out.println(doc);

			}
		} catch (MongoException e) {
			// handle MongoDB exception
		}

		return list;
	}

}
