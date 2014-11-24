package com.roy;


import java.io.IOException;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class RoyAppServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		Stock stk = new Stock();
		
		
		String result1 = "<h1>IN PROGRESS";
		
		resp.setContentType("text/html");
		resp.getWriter().println(result1);
	
	}




}
