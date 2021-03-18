package jstlel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class _01Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 값
		int iVal = 10;
		long lVal = 10;
		float fVal = 3.14f;
		boolean bVal = true;
		String sVal = "가나다라마바사";

		//객체 테스트
		UserVo userVo= new UserVo();
		userVo.setNo(10L);
		userVo.setName("조근형");
		
		Object obj = null;
		
		//Map을 사용하여 여러 값을 한번에 넘기기
		Map<String, Object> map= new HashMap<>();
		map.put("iVal", iVal);
		map.put("lVal", lVal);
		map.put("fVal", fVal);
		map.put("bVal", bVal);
		map.put("sVal", sVal);
		
		request.setAttribute("iVal", iVal); // new Integer(ival) 이걸 자동으로 iVal로 변환해준다.
		request.setAttribute("lVal", lVal);
		request.setAttribute("fVal", fVal);
		request.setAttribute("bVal", bVal);
		request.setAttribute("sVal", sVal);
		
		request.setAttribute("map", map);
		request.setAttribute("vo", userVo);
		request.setAttribute("obj", obj);
		request.getRequestDispatcher("WEB-INF/views/01.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
