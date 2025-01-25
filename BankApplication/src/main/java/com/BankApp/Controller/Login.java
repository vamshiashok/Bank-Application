package com.BankApp.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.BankApp.Model.Cust_Details_Model;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private HttpSession session;
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String id = request.getParameter("cust_id");
		String pwd = request.getParameter("cust_pwd");
		session = request.getSession(true);
		try {
			Cust_Details_Model m = new Cust_Details_Model();
			m.setCust_id(id);
			m.setCust_pwd(pwd);
			
			boolean b = m.login();
			if(b==true) {
				session.setAttribute("cust_acc_no", m.getCust_acc_no());
				response.sendRedirect("/BankApplication/Home.html");
			}else {
				response.sendRedirect("/BankApplication/Error.html");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}