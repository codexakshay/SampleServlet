package com.blz.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		PrintWriter output = response.getWriter();
		output.println("<html><body>" + "<h1>Hello " + fname + " " + lname + "</h1>" + "</body></html>");
		output.flush();
		output.close();
	}
}