package com.BankApp.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.BankApp.Model.Cust_Details_Model;
@WebServlet("/Checkbalance")
public class Checkbalance extends HttpServlet {
	private HttpSession session;
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		session = request.getSession();
		int acc_no = (int) session.getAttribute("cust_acc_no");
		try {
			Cust_Details_Model m = new Cust_Details_Model();
			m.setCust_acc_no(acc_no);
			boolean b = m.checkBalance();
			if(b==true) {
				session.setAttribute("cust_bank_bal", m.getCust_bank_bal());
				response.sendRedirect("/BankApplication/BalanceView.jsp");
			}else {
				response.sendRedirect("/BankApplication/BalanceFail.html");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}