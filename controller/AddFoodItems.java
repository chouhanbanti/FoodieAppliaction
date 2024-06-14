	package Developer_Squad.controller;

import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet("/addfooditems")
public class AddFoodItems extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String itemname = req.getParameter("name");
		double price = Double.parseDouble(req.getParameter("price"));
		int id = Integer.parseInt(req.getParameter("id"));
		List<Developer_Squad.dto.FoodItems> al = new ArrayList<>();
		al.add(new Developer_Squad.dto.FoodItems(itemname, price));
		Developer_Squad.dao.FoodItemsCrud foodItemsCrud = new Developer_Squad.dao.FoodItemsCrud();
		foodItemsCrud.save(al, id);
		List<Developer_Squad.dto.FoodItems> list = foodItemsCrud.fetchAll();
		Developer_Squad.dao.RestaurantCrud restaurantCrud = new Developer_Squad.dao.RestaurantCrud();
		Developer_Squad.dto.Restaurant r = restaurantCrud.fetchById(id);
		req.setAttribute("items", r);
		RequestDispatcher dis = req.getRequestDispatcher("fetchitems.jsp");
		dis.forward(req, resp);
	}
}
