package com.blz.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "Login Servlet Testing", urlPatterns = { "/LoginServlet" }, initParams = {
		@WebInitParam(name = "user", value = "Sreekar"), @WebInitParam(name = "password", value = "$Sree1998@") })

public class LoginServlet extends HttpServlet {
	private static final String FIRST_NAME_PATTERN = "^[A-Z]{1}[a-zA-Z]{2,}$";
	private static final String PASSWORD_PATTERN = "^(?=.*[@#$%^&+=])(?=.*[0-9])(?=.*[A-Z]).{8,}$";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user = request.getParameter("user");
		boolean validateFirstName = validateFirstName(user);
		boolean checkFirstName = checkFirstName(request, response, validateFirstName);
		String pwd = request.getParameter("pwd");
		boolean validatePassword = validatePassword(pwd);
		boolean checkPassword = checkPassword(request, response, validatePassword);
		System.out.println("validatePassword " + validatePassword + " " + checkPassword);
		String userID = getServletConfig().getInitParameter("user");
		String password = getServletConfig().getInitParameter("password");

		if (checkFirstName == true && checkPassword == true) {
			if (userID.equals(user) && password.equals(pwd)) {
				request.setAttribute("user", user);
				request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
			} else {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
				PrintWriter out = response.getWriter();
				out.println("<font color=red>Either user name or password is wrong.</font>");
				rd.include(request, response);
			}
		}
	}

	private boolean checkFirstName(HttpServletRequest request, HttpServletResponse response, boolean validateFirstName)
			throws IOException, ServletException {
		if (validateFirstName == false) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>user name is Incorrect</font>");
			rd.include(request, response);
			return false;
		}
		return true;
	}

	private boolean checkPassword(HttpServletRequest request, HttpServletResponse response, boolean validatePassword)
			throws IOException, ServletException {
		if (validatePassword == false) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>Password is Incorrect</font>");
			rd.include(request, response);
			return false;
		}
		return true;
	}

	private boolean validateFirstName(String firstName) {
		Pattern check = Pattern.compile(FIRST_NAME_PATTERN);
		boolean value = check.matcher(firstName).matches();
		return value;
	}

	private boolean validatePassword(String password) {
		Pattern check = Pattern.compile(PASSWORD_PATTERN);
		boolean value = check.matcher(password).matches();
		return value;
	}

}
