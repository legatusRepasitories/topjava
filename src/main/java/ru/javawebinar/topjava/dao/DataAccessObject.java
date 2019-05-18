package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

public interface DataAccessObject {
    void add(Meal meal);
    void delete(long id);
    void edit(Meal meal);
}
