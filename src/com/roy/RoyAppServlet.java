package com.roy;

import static java.lang.Math.pow;
import java.io.IOException;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class RoyAppServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		
		
		
		final double PI= 3.14 ;
		int radius = 50 ;
		double res1;
		res1= PI* pow(radius,2);
		
		String result1 = "<h1>Area of circle with redius "+radius+" is "+res1+" square cm.  ";
		
		/* double deg=30.0;
		double radian= Math.toRadians(deg);
		double res2= ; 
		String result2 = "<h1>Length of opposite where angle B is 30 degrees and Hypotenuse length is 50 cm is  cm.  ";
		
	*/
		
	
		double res3= pow (20,13);
		String result3 = "<h1>power of 20 with exp of 13 is "+res3+".  ";
	
		resp.setContentType("text/html");
		resp.getWriter().println(result1);
		/*resp.getWriter().println(result2); */
		resp.getWriter().println(result3);
	}




}
