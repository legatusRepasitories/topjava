package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.DataAccessObject;
import ru.javawebinar.topjava.dao.ListDAO;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    DataAccessObject dao = new ListDAO();

    private static String ADD_OR_EDIT = "meal.jsp";
    private static String LIST_USER = "meals.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("doGet");
        request.setCharacterEncoding("UTF-8");

        String forward = LIST_USER;

        String action = request.getParameter("action");
        if (action != null) {
            if (action.equalsIgnoreCase("delete")) {
                long id = Long.parseLong(request.getParameter("mealId"));
                dao.delete(id);
                forward = LIST_USER;
            }
            else if (action.equalsIgnoreCase("edit")) {
                long id = Long.parseLong(request.getParameter("mealId"));
                Meal meal = MealsUtil.getMealById(id);
                forward = ADD_OR_EDIT;
                request.setAttribute("meal", meal);
            } else if (action.equalsIgnoreCase("add")){
                System.out.println("add");
                Meal meal = new Meal();
                forward = ADD_OR_EDIT;
                request.setAttribute("meal", meal);
            }
        }


        List<MealTo> mealTos = MealsUtil.getFilteredWithExcess(MealsUtil.getMeals(), null, null, 2000);
        request.setAttribute("mealList", mealTos);
        request.getRequestDispatcher(forward).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        String sID = request.getParameter("id");
        meal.setDateTime(LocalDateTime.parse(request.getParameter("dateTime"), formatter));
        meal.setDescription(request.getParameter("description"));
        meal.setCalories(Integer.parseInt(request.getParameter("calories")));

        if (Integer.parseInt(sID) == 0) {
            dao.add(meal);
        } else {
            meal.setId(Integer.parseInt(sID));
            dao.edit(meal);
        }

        List<MealTo> mealTos = MealsUtil.getFilteredWithExcess(MealsUtil.getMeals(), null, null, 2000);
        request.setAttribute("mealList", mealTos);
        request.getRequestDispatcher("meals.jsp").forward(request, response);

    }
}
