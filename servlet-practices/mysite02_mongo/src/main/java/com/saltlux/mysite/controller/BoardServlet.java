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
import com.saltlux.mysite.vo.PagerVo;
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

		} else if ("reply".equals(action)) { // 원글보다 높은 orderNo를 1씩 추가하고 그자리에 답글을 집어 넣어 준다
			String boardNo = request.getParameter("boardNo");
			// view에서 hidden으로 받아 오기
			
			request.setAttribute("boardNo", boardNo); // write.jsp로 넘겨준다
			
			WebUtil.forward("/WEB-INF/views/board/write.jsp", request, response);
			//WebUtil.redirect(request.getContextPath() + "/board?a=write&no=" + boardNo, request, response);
		} else if ("write".equals(action)) {
			String referer = request.getHeader("Referer");
			System.out.println("이전의 경로 : " + referer);
			Long g_no = 0L;
			Long o_no = 1L;
			Long depth = 0L;// 여기까지 신규등록일 경우를 대비한 초기화 상태

			BoardDao boardDao = new BoardDao();
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			
			// writeform이 문자열의 마지막이면 신규 등록
			if (referer.substring(referer.length() - 9, referer.length()).equals("writeform")) {// 신규등록
				g_no = boardDao.getMaxGno() + 1; // 자동으로 Group No를 증가한다

			} else { // 답글작성
				BoardVo vo = new BoardVo();
				Long boardNo = Long.parseLong(request.getParameter("reply"));// 답을 달 원글
				vo = boardDao.findByNo(boardNo);// 답을 달 글의 정보 요청

				//원글의 정보
				g_no= vo.getG_no();
				o_no = vo.getO_no()+1L;
				depth = vo.getDepth()+1L;
				
				boardDao.updateOrder(g_no, o_no); // 추가하기 전에 원글의 뒷부분에 orderno를 늘려준다
			}

			String title = request.getParameter("title");
			String content = request.getParameter("content");

			BoardVo vo = new BoardVo();

			vo.setTitle(title);
			vo.setAuthor(authUser.getName());
			vo.setContent(content);
			vo.setG_no(g_no);
			vo.setO_no(o_no);
			vo.setDepth(depth);
			vo.setUser_no(authUser.getNo());

			boardDao.insert(vo);

//			List<BoardVo> list = new ArrayList<>();
//			list = new BoardDao().findAll();
//			request.setAttribute("list", list);
			// board의 정보를 모두 넘겨주게 된다

			WebUtil.redirect(request.getContextPath() + "/board", request, response);
		} else if ("modifyform".equals(action))

		{

			UserVo authUser = (UserVo) session.getAttribute("authUser"); // 세션의 no
			String boardNo = request.getParameter("no");// 수정할 글의 번호
			System.out.println("수정할 글의 번호 : " + boardNo);
			BoardVo vo = new BoardDao().findByNo(Long.parseLong(boardNo));

			if (!authUser.getNo().equals(vo.getUser_no())) { // 글의 작성자와 세션의 유저와 비교
				System.out.println("인증안된 접근");
				WebUtil.redirect(request.getContextPath() + "/board", request, response);
				return;
			}
			request.setAttribute("board", vo); // vo를 넘겨줌

			WebUtil.forward("/WEB-INF/views/board/modify.jsp", request, response);
		} else if ("modify".equals(action)) {

			Long no = Long.parseLong(request.getParameter("no"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			BoardVo vo = new BoardVo();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setContent(content);
			//System.out.println(vo);  //디버그용 
			new BoardDao().update(vo);

			WebUtil.redirect(request.getContextPath() + "/board", request, response);
		} else if ("delete".equals(action)) {
			UserVo authUser = (UserVo) session.getAttribute("authUser");

			Long delNo = Long.parseLong(request.getParameter("no"));
			BoardDao boardDao = new BoardDao();
			BoardVo vo = new BoardVo();
			vo.setNo(delNo);
			vo.setUser_no(authUser.getNo());

			boardDao.delete(vo);
			List<BoardVo> list = new ArrayList<>();
			WebUtil.redirect(request.getContextPath() + "/board", request, response);

		} else if ("view".equals(action)) {
			Long no = Long.parseLong(request.getParameter("no"));
			BoardVo vo = new BoardDao().findByNo(no);
			new BoardDao().updateView(no);
			request.setAttribute("board", vo);

			WebUtil.forward("/WEB-INF/views/board/view.jsp", request, response);
		} else {

			int totalCount=0;
		
			BoardDao boardDao= new BoardDao();
			int nowPage=1;
			List<BoardVo> list =null;
			if(request.getParameter("p") != null) {
				nowPage = Integer.parseInt(request.getParameter("p"));	
			}
			
			totalCount = boardDao.getCount();
			PagerVo pagerVo= new PagerVo(totalCount,5,nowPage);
			list = boardDao.findAll(pagerVo);
			
			
			
			
			
			
			
			request.setAttribute("list", list);
			request.setAttribute("pagerVo", pagerVo);
			
			
			
			
			
			
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
