package Developer_Squad.controller;

import java.io.IOException;
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

@WebServlet("/delete")
public class DeleteFoodItem extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		FoodItemsCrud foodItemsCrud = new FoodItemsCrud();
		FoodItems foodItems = foodItemsCrud.delete(id);
		try {
			if (foodItems != null) {
				RestaurantCrud restaurantCrud = new RestaurantCrud();
				Restaurant r = restaurantCrud.fetchById(foodItems.getRestaurant().getId());
				req.setAttribute("items", r);
				RequestDispatcher dis = req.getRequestDispatcher("fetchitems.jsp");
				dis.forward(req, resp);
				System.out.println(id);

			} else {
				System.out.println("else");
			}
		} catch (Exception e) {
			System.out.println("catch");
		}

	}
}
