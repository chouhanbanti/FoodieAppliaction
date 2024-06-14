package Developer_Squad.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Developer_Squad.dao.FoodItemsCrud;
import Developer_Squad.dao.RestaurantCrud;
import Developer_Squad.dto.FoodItems;
import Developer_Squad.dto.Restaurant;

@WebServlet("")
public class AddMore extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		writer.print("Yes");
		Developer_Squad.dao.RestaurantCrud restaurantCrud = new RestaurantCrud();
		List<Restaurant> ls = restaurantCrud.fetchAll();
		req.setAttribute("list", ls);
		RequestDispatcher dis = req.getRequestDispatcher("fetch.jsp");
		dis.forward(req, resp);
	}
}
