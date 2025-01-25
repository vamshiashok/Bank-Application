package com.BankApp.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.BankApp.Model.Cust_Details_Model;
@WebServlet("/TransferFund")
public class TransferFund extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int cust_acc_no = (int) session.getAttribute("cust_acc_no");
		String samt = request.getParameter("amt");
		int amt = Integer.parseInt(samt);
		String sraccno = request.getParameter("rec_acc_no");
		int rec_acc_no = Integer.parseInt(sraccno);
		try {
			Cust_Details_Model m = new Cust_Details_Model();
			m.setCust_acc_no(cust_acc_no);
			m.setRec_acc_no(rec_acc_no);
			m.setCust_bank_bal(amt);
			boolean b = m.transferFund();
			if(b==true) {
				response.sendRedirect("/BankApplication/TransferSuccess.html");
			}else {
				response.sendRedirect("/BankApplication/TransferFail.html");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}