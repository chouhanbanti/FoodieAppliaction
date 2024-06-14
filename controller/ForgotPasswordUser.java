package Developer_Squad.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Developer_Squad.dao.UserCrud;
import Developer_Squad.dto.User;

@WebServlet("/forgotpassworduser")
public class ForgotPasswordUser extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String cpassword = req.getParameter("password");
		UserCrud userCrud = new UserCrud();
	    User user;
		try {
			user = userCrud.fetchByEmail(email);
			if(user !=null) {
		    	user.setPassword(cpassword);
		    	user.setConfirm_password(cpassword);
		    	userCrud.update(user);
		    	RequestDispatcher dis = req.getRequestDispatcher("login.jsp");
		    	dis.forward(req, resp);
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

}
