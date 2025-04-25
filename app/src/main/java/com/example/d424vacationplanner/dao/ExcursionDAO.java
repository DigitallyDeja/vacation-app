package com.example.d424vacationplanner.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.example.d424vacationplanner.entities.Excursions;

import java.util.List;

@Dao
public interface ExcursionDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Excursions excursions);

    @Update
    void update(Excursions excursions);

    @Delete
    void delete(Excursions excursions);

    @Query("SELECT * FROM EXCURSION ORDER BY excursionID ASC")
    List<Excursions> getAllExcursion();

    @Query("SELECT * FROM EXCURSION WHERE vacationID=:vaca ORDER BY excursionID ASC")
    List<Excursions> getAllAssociatedExcursion(int vaca);
}
