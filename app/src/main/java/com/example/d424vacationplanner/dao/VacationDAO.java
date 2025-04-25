package com.example.d424vacationplanner.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.d308vacationplanner_deja.entities.Vacations;

import java.util.List;
@Dao
public interface VacationDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Vacations vacations);

    @Update
    void update(Vacations vacations);

    @Delete
    void delete(Vacations vacations);

    @Query("SELECT * FROM VACATION ORDER BY vacationID ASC")
    List<Vacations> getAllVacation();
}
