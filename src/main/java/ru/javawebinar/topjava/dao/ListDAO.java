package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;

public class ListDAO implements DataAccessObject {

    @Override
    public void add(Meal meal) {
        meal.setId(MealsUtil.nextId());
        MealsUtil.getMeals().add(meal);
    }

    @Override
    public void delete(long id) {
        MealsUtil.getMeals().removeIf(meal -> meal.getId() == id);

    }

    @Override
    public void edit(Meal meal) {
        Meal mealToEdit = MealsUtil.getMealById(meal.getId());
        mealToEdit.setCalories(meal.getCalories());
        mealToEdit.setDescription(meal.getDescription());
        mealToEdit.setDateTime(meal.getDateTime());
    }
}
