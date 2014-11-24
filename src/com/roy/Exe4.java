package com.roy;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class Exe4 extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		
		
		
		resp.setContentType("text/html");
		resp.getWriter().println("MY NEW SERVLET");
	
		
	}
	

}
