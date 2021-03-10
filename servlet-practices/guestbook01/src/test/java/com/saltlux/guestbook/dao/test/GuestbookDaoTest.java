package com.saltlux.guestbook.dao.test;

import java.util.List;

import com.saltlux.guestbook.dao.GuestbookDao;
import com.saltlux.guestbook.vo.GuestbookVo;

public class GuestbookDaoTest {

	public static void main(String[] args) {

		//testInsert();
		//testDelete();
		testFindAll();
		
	}

	public static void testFindAll() {
		List<GuestbookVo> list = new GuestbookDao().findAll();

		for (GuestbookVo vo : list) {
			System.out.println(vo);
		}
	}

	public static void testInsert() {
		GuestbookVo vo= new GuestbookVo();
		vo.setName("테스트용");
		vo.setPassword("1234");
		vo.setContents("안녕하세요 호 호 호");
		
		new GuestbookDao().insert(vo);
	}
	public static void testDelete() {
		GuestbookVo vo= new GuestbookVo();
		vo.setNo((long)2);
		vo.setPassword("1234");
		
		new GuestbookDao().delete(vo);
	}
}
