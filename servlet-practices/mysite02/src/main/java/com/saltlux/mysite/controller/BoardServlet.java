package com.saltlux.mysite.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saltlux.mysite.dao.BoardDao;
import com.saltlux.mysite.vo.BoardVo;
import com.saltlux.mysite.vo.UserVo;
import com.saltlux.web.mvc.WebUtil;

public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// TODO 나중에 코드 줄일때 여기에 리스트 얻어서 넘기는 코드 옮기기


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("a");
		HttpSession session = request.getSession();

		if ("writeform".equals(action)) {
			WebUtil.forward("/WEB-INF/views/board/write.jsp", request, response);
		} else if ("write".equals(action)) {
			BoardDao boardDao= new BoardDao();
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			
			Long g_no= boardDao.getGno();
			//여기에 파라미터로 받아와서 넣어준다
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			BoardVo vo = new BoardVo();
			
			vo.setTitle(title);
			vo.setAuthor(authUser.getName());
			vo.setContent(content);
			vo.setG_no(g_no);
			vo.setO_no(1L);
			vo.setDepth(0L);
			vo.setUser_no(authUser.getNo());
			
			boardDao.insert(vo);
			
			List<BoardVo> list = new ArrayList<>();
			list = new BoardDao().findAll();
			request.setAttribute("list", list);
			// board의 정보를 모두 넘겨주게 된다

			WebUtil.redirect(request.getContextPath()+"/board", request, response);
		} else if ("modify".equals(action)) {

			WebUtil.forward("/WEB-INF/views/board/modify.jsp", request, response);

		} else if ("delete".equals(action)) {
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			
			Long delNo= Long.parseLong(request.getParameter("no"));
			BoardDao boardDao= new BoardDao();
			BoardVo vo= new BoardVo();
			vo.setNo(delNo);
			vo.setUser_no(authUser.getNo());
			
			boardDao.delete(vo);
			
			
			List<BoardVo> list = new ArrayList<>();
			list = boardDao.findAll();
			request.setAttribute("list", list);

			WebUtil.redirect(request.getContextPath()+"/board", request, response);
			
			
		} else if ("view".equals(action)) {
			Long no = Long.parseLong(request.getParameter("no"));
			BoardVo vo = new BoardDao().findByNo(no);

			request.setAttribute("board", vo);

			WebUtil.forward("/WEB-INF/views/board/view.jsp", request, response);
		} else {

			List<BoardVo> list = new ArrayList<>();
			list = new BoardDao().findAll();
			request.setAttribute("list", list);
			// board의 정보를 모두 넘겨주게 된다

			if (session == null) {
				WebUtil.forward("/WEB-INF/views/board/index.jsp", request, response);
				return;
			}
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			if (authUser == null) {
				WebUtil.forward("/WEB-INF/views/board/index.jsp", request, response);
				return;
			}
			// Long no = authUser.getNo();
			WebUtil.forward("/WEB-INF/views/board/index.jsp", request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
