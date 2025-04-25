package com.example.d424vacationplanner.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.d308vacationplanner_deja.R;
import com.example.d308vacationplanner_deja.database.Repository;
import com.example.d308vacationplanner_deja.entities.Excursions;
import com.example.d308vacationplanner_deja.entities.Vacations;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class VacationList extends AppCompatActivity {
private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vacation_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VacationList.this, VacationDetail.class);
                startActivity(intent);
            }
        });


        RecyclerView recyclerView=findViewById(R.id.listRecyclerView);
        repository = new Repository(getApplication());
        List<Vacations> allVacations = repository.getAllVacation();
        final VacationAdapter vacationAdapter=new VacationAdapter(this);
        recyclerView.setAdapter(vacationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        vacationAdapter.setVacations(allVacations);


        // System.out.println(getIntent().getStringExtra("home"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_vacation_list, menu);
        return true;
    }

    @Override
    protected void onResume(){
        super.onResume();
        List<Vacations> allVacations=repository.getAllVacation();
        RecyclerView recyclerView= findViewById(R.id.listRecyclerView);
        final VacationAdapter vacationAdapter = new VacationAdapter(this);
        recyclerView.setAdapter(vacationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        vacationAdapter.setVacations(allVacations);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.cart){
            repository= new Repository(getApplication());
            //Toast.makeText(VacationList.this, "put in vacation data", Toast.LENGTH_LONG).show();
            Vacations vacations=new Vacations(0, "San Juan, Puerto Rico", "Hilton", "05/21/25","05/24/25");
            repository.insert(vacations);
            Excursions excursions=new Excursions(0, "Scuba Diving",1 ,"05/22/25");
            repository.insert(excursions);
            vacations=new Vacations(0, "Port au Prince, Haiti", "Marriott", "06/09/25","06/12/25");
            repository.insert(vacations);
            excursions=new Excursions(0, "Museum Tour",2 ,"06/10/25");
            repository.insert(excursions);
            vacations=new Vacations(0, "Punta Cana, Dominican Republic", "Holiday Inn", "08/08/25","08/13/25");
            repository.insert(vacations);
            excursions=new Excursions(0, "Horseback Riding",3,"08/11/25");
            repository.insert(excursions);
            vacations=new Vacations(0, "Montego Bay, Jamaica", "Hyatt", "09/25/25","09/30/25");
            repository.insert(vacations);
            excursions=new Excursions(0, "Bamboo Rafting",4 ,"09/30/25");
            repository.insert(excursions);
            return true;
        }
        if(item.getItemId() == android.R.id.home){
            this.finish();
            return true;
        }
        return true;
    }
}