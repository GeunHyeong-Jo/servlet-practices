package com.saltlux.mysite.dao;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.simple.JSONObject;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
import com.saltlux.mysite.vo.BoardVo;
import com.saltlux.mysite.vo.GuestbookVo;
import com.saltlux.mysite.vo.UserVo;

public class TestDao {

	public static void main(String[] args) {
		BoardVo vo = new BoardVo();
		
		try (MongoClient client = new MongoClient("localhost", 27017)) {

			MongoDatabase database = client.getDatabase("webdb");
			MongoCollection<Document> collection = database.getCollection("board");
			Long count = 0L;
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

}
