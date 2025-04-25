package com.example.d424vacationplanner.database;

import android.app.Application;

import com.example.d308vacationplanner_deja.dao.ExcursionDAO;
import com.example.d308vacationplanner_deja.dao.VacationDAO;
import com.example.d308vacationplanner_deja.entities.Excursions;
import com.example.d308vacationplanner_deja.entities.Vacations;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private final ExcursionDAO mExcursionDAO;
    private final VacationDAO mVacationDAO;
    private List<Vacations> mAllVacation;
    private List<Excursions> mAllExcursion;

    private static int NUM_OF_THREADS = 4;
    static final ExecutorService dataExecutor = Executors.newFixedThreadPool(NUM_OF_THREADS);

    public Repository(Application app){
        VacationDatabase db=VacationDatabase.getDatabase(app);
        mExcursionDAO=db.excursionDAO();
        mVacationDAO = db.vacationDAO();
    }

    public List<Vacations>getAllVacation(){
        dataExecutor.execute(()->{
            mAllVacation=mVacationDAO.getAllVacation();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return mAllVacation;
    }
    public void insert(Vacations vacations){
        dataExecutor.execute(()->{
            mVacationDAO.insert(vacations);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void update(Vacations vacations){
        dataExecutor.execute(()->{
            mVacationDAO.update(vacations);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void delete(Vacations vacations){
        dataExecutor.execute(()->{
            mVacationDAO.delete(vacations);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Excursions>getAllExcursion(){
        dataExecutor.execute(()->{
            mAllExcursion=mExcursionDAO.getAllExcursion();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return mAllExcursion;
    }

    public List<Excursions>getAssociatedExcursion(int vacationID){
        dataExecutor.execute(()->{
            mAllExcursion=mExcursionDAO.getAllAssociatedExcursion(vacationID);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllExcursion;
    }

    public void insert(Excursions excursions){
        dataExecutor.execute(()->{
            mExcursionDAO.insert(excursions);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void update(Excursions excursions){
        dataExecutor.execute(()->{
            mExcursionDAO.update(excursions);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void delete (Excursions excursions){
        dataExecutor.execute(()->{
            mExcursionDAO.delete(excursions);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
