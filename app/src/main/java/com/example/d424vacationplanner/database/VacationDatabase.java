package com.example.d424vacationplanner.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.d308vacationplanner_deja.dao.ExcursionDAO;
import com.example.d308vacationplanner_deja.dao.VacationDAO;
import com.example.d308vacationplanner_deja.entities.Excursions;
import com.example.d308vacationplanner_deja.entities.Vacations;



@Database(entities = {Vacations.class, Excursions.class}, version= 5, exportSchema = false)
public abstract class VacationDatabase extends RoomDatabase {
    public abstract VacationDAO vacationDAO();
    public abstract ExcursionDAO excursionDAO();
    private static volatile VacationDatabase INSTANCE;

    static VacationDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (VacationDatabase.class){
                if(INSTANCE == null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),VacationDatabase.class, "VacationDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
