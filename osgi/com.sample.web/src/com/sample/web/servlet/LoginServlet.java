package com.sample.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class LoginServlet extends HttpServlet{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        //WEB开发场景一：使用Request对象得到得到客户端请求数据
        String userName = request.getParameter("userName");
        //WEB开发场景二：操作Session
        request.getSession().setAttribute("userFromA", userName);
        //WEB开发场景三：使用Response返回
        response.setCharacterEncoding("GBK");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<head>");
        out.println("<title>登录返回页面</title>");
        out.println("</head>");
        out.println("<body>");
        out.println(userName + ",您的sessionId：" + request.getSession().getId());
        out.println("</body>");
        out.println("</html>");
        out.flush();
        out.close();        
    }    
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }
}
