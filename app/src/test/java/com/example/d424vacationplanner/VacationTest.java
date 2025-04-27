package com.example.d424vacationplanner;

import static org.junit.Assert.*;

import com.example.d424vacationplanner.entities.Vacations;

import org.junit.Test;

public class VacationTest {

    @Test
    public void vacationCreatedSuccessfully() {
        Vacations vacation = new Vacations(1, "Miami", "Grand Hotel", "06/01/2025", "06/07/2025");

        assertEquals("Miami", vacation.getVacationName());
        assertEquals("Grand Hotel", vacation.getHotelName());
        assertEquals("06/01/2025", vacation.getStartDate());
        assertEquals("06/07/2025", vacation.getEndDate());
    }
}
