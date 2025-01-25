package com.BankApp.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.BankApp.Model.Cust_Details_Model;

@WebServlet("/Loan")
public class Loan extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int cust_acc_no = (int) session.getAttribute("cust_acc_no");
		try {
			Cust_Details_Model m = new Cust_Details_Model();
			m.setCust_acc_no(cust_acc_no);
			boolean b = m.applyLoan();
			if(b==true) {
				session.setAttribute("cust_name", m.getCust_name());
				session.setAttribute("cust_email", m.getCust_email());
				response.sendRedirect("/BankApplication/LoanSuccess.jsp");
			}else {
				response.sendRedirect("/BankApplication/LoanFail.html");
			}
		}
		catch (Exception e) {
			e.printStackTrace();	
		}
	}

}