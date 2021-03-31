package test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/TestMain")
public class TestMain extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");// 한글로 하면 오류가 생겨서 응답 헤더를 추가
		List<GuestbookVo> list = new ArrayList<>();
		list = (new GuestbookDao().findAll());

		JSONObject jsonObject = new JSONObject();
		JSONObject data = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		for (int i = 0; i < list.size(); i++) {
			data.put("이름", list.get(i).getName());
			data.put("내용", list.get(i).getContents());
			jsonArray.add(data);
		}
		jsonObject.put("회원정보", data);

		request.setAttribute("jsonObject", jsonObject);
		WebUtil.forward("xml_table.jsp", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
