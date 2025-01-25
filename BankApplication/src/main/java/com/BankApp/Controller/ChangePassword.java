package com.BankApp.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.BankApp.Model.Cust_Details_Model;
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String cust_pwd = request.getParameter("new_cust_pwd");
		HttpSession session = request.getSession();

		int cust_acc_no = (int) session.getAttribute("cust_acc_no");
		String npwd = request.getParameter("new_cust_pwd");
		String cpwd = request.getParameter("conf_new_cust_pwd");

		if(npwd.equals(cpwd)) {
			try {
				Cust_Details_Model m = new Cust_Details_Model();
				m.setCust_acc_no(cust_acc_no);
				m.setCust_pwd(cust_pwd);
				boolean b = m.changePassword();
				if(b==true) {
					response.sendRedirect("/BankApplication/ChangePasswordSuccess.html");
				}else {
					response.sendRedirect("/BankApplication/ChangePasswordFail.html");
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			((HttpServletResponse) response).sendRedirect("/BankApplication/ChangePasswordFail.html");
		}

	}
}