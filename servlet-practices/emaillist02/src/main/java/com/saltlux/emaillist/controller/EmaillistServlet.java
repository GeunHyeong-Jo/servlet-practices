package com.saltlux.emaillist.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saltlux.emaillist.dao.EmaillistDao;
import com.saltlux.emaillist.vo.EmaillistVo;
import com.saltlux.web.mvc.WebUtil;

/**
 * Servlet implementation class EmaillistServlet
 */
public class EmaillistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public EmaillistServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action= request.getParameter("a");
		
		if("form".equals(action)) {
			WebUtil.forward("/WEB-INF/views/form.jsp",request,response);
		}else if("add".equals(action)){
			response.getWriter().print("add");
		}else {
			List<EmaillistVo> list = new EmaillistDao().findAll();
			//forwarding = request dispatch = request extension
			request.setAttribute("list", list);		
			WebUtil.forward("/WEB-INF/views/index.jsp",request,response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
