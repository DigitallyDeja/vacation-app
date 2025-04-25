package com.example.d424vacationplanner.UI;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class VacationDetail extends AppCompatActivity {
 String name;
 String hotel;
 int vacationID;
 String startDate;
 String endDate;
 EditText editName;
 EditText editHotel;
 TextView editStart;
 TextView editEnd;
 int numExcursions;
 int excursionID;
 ExcursionDetail excursionDetail;
 Vacations currentVacation;
 Vacations vacations;
 DatePickerDialog.OnDateSetListener startDateVacation;
 DatePickerDialog.OnDateSetListener endDateVacation;
 final Calendar myCalendarVacationStart = Calendar.getInstance();
 final Calendar myCalendarVacationEnd = Calendar.getInstance();

    Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vacation_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        FloatingActionButton fab = findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VacationDetail.this, ExcursionDetail.class);
                intent.putExtra("vacationID", vacationID);
                startActivity(intent);
            }
        });

        vacationID = getIntent().getIntExtra("id", -1);
        editName = findViewById(R.id.titletext);
        editHotel = findViewById(R.id.hoteltext);
        editStart = findViewById(R.id.startdatetext);
        editEnd = findViewById(R.id.enddatetext);
        name = getIntent().getStringExtra("name");
        hotel = getIntent().getStringExtra("hotel");
        startDate = getIntent().getStringExtra("start date");
        endDate = getIntent().getStringExtra("end date");
        editName.setText(name);
        editHotel.setText(hotel);
        editStart.setText(startDate);
        editEnd.setText(endDate);
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        RecyclerView recyclerView = findViewById(R.id.recyclerExcursion);
        repository = new Repository(getApplication());
        final ExcursionAdapter excursionAdapter = new ExcursionAdapter(this);
        recyclerView.setAdapter(excursionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Excursions> filteredExcursions = new ArrayList<>();
        for(Excursions e : repository.getAllExcursion()){
            if(e.getVacationID() == vacationID) filteredExcursions.add(e);
        }
        excursionAdapter.setExcursions(filteredExcursions);

        startDateVacation = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendarVacationStart.set(Calendar.YEAR, year);
                myCalendarVacationStart.set(Calendar.MONTH, month);
                myCalendarVacationStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelStart();
            }
        };

        endDateVacation = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendarVacationEnd.set(Calendar.YEAR, year);
                myCalendarVacationEnd.set(Calendar.MONTH, month);
                myCalendarVacationEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelEnd();
            }
        };

        editStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date;
                //grabbing the value
                String info = editStart.getText().toString();
                if (info.equals("")) info = editStart.getText().toString();
                try {
                    myCalendarVacationStart.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(VacationDetail.this, startDateVacation, myCalendarVacationStart.get(Calendar.YEAR),
                        myCalendarVacationStart.get(Calendar.MONTH), myCalendarVacationStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        editEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date;
                //grabbing the value
                String info = editEnd.getText().toString();
                if (info.equals("")) info = editEnd.getText().toString();
                try {
                    myCalendarVacationEnd.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(VacationDetail.this, endDateVacation, myCalendarVacationEnd.get(Calendar.YEAR),
                        myCalendarVacationEnd.get(Calendar.MONTH), myCalendarVacationEnd.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabelStart(){
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editStart.setText(sdf.format(myCalendarVacationStart.getTime()));
    }

    private void updateLabelEnd(){
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editEnd.setText(sdf.format(myCalendarVacationEnd.getTime()));
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_vacationdetails, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        if (item.getItemId() == R.id.vacationsave) {
            String myFormat = "MM/dd/yy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            try {
                Date vacationStart = sdf.parse(editStart.getText().toString());
                Date vacationEnd = sdf.parse(editEnd.getText().toString());
                if (vacationEnd.before(vacationStart)) {
                    Toast.makeText(VacationDetail.this, "Vacation end date must be after the vacation start date", Toast.LENGTH_LONG).show();
                    return false;
                } else {
                    this.finish();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (vacationID == -1) {
                if (repository.getAllVacation().size() == 0) vacationID = 1;
                else
                    vacationID = repository.getAllVacation().get(repository.getAllVacation().size() - 1).getVacationID() + 1;
                vacations = new Vacations(vacationID, editName.getText().toString(), editHotel.getText().toString(), editStart.getText().toString(), editEnd.getText().toString());
                repository.insert(vacations);
                this.finish();
            } else {
                vacations = new Vacations(vacationID, editName.getText().toString(), editHotel.getText().toString(), editStart.getText().toString(), editEnd.getText().toString());
                repository.update(vacations);
                this.finish();
            }

        }


            if (item.getItemId() == R.id.vacationdelete) {
                for (Vacations v : repository.getAllVacation()) {
                    if (v.getVacationID() == vacationID) currentVacation = v;
                }

                numExcursions = 0;
                for (Excursions e : repository.getAllExcursion()) {
                    if (e.getVacationID() == vacationID) ++numExcursions;
                }

                if (numExcursions == 0) {
                    repository.delete(currentVacation);
                    Toast.makeText(VacationDetail.this, currentVacation.getVacationName() + " was deleted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(VacationDetail.this, "Must delete (cancel) excursions before deleting vacation", Toast.LENGTH_LONG).show();
                }
                return true;
            }
            if (item.getItemId() == R.id.excursionsave) {
                if (vacationID == -1)
                    Toast.makeText(VacationDetail.this, "Please save vacation before adding excursions", Toast.LENGTH_LONG).show();
                else {
                    if (repository.getAllExcursion().isEmpty()) excursionID = 1;
                    else {
                        excursionID = repository.getAllExcursion().get(repository.getAllExcursion().size() - 1).getExcursionID() + 1;
                        RecyclerView recyclerView = findViewById(R.id.recyclerExcursion);
                        repository = new Repository(getApplication());
                        final ExcursionAdapter excursionAdapter = new ExcursionAdapter(this);
                        recyclerView.setAdapter(excursionAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(this));
                        List<Excursions> filteredExcursions = new ArrayList<>();
                        for (Excursions e : repository.getAllExcursion()) {
                            if (e.getVacationID() == vacationID) filteredExcursions.add(e);
                        }
                        excursionAdapter.setExcursions(filteredExcursions);
                        return true;
                    }
                    return true;
                }
            }

                if (item.getItemId() == R.id.share) {
                    String vacayName = editName.getText().toString();
                    String vacayHotel = editHotel.getText().toString();
                    String vacayStart = editStart.getText().toString();
                    String vacayEnd = editEnd.getText().toString();

                    StringBuilder excursionNames = new StringBuilder();
                    for (Excursions e : repository.getAllExcursion()) {
                        if (e.getVacationID() == vacationID) {
                            excursionNames.append(" ").append(e.getExcursionName()).append("\n");
                        }
                    }

                    String combinedInfo = "Accommodations: " + vacayHotel
                    +"\nStart Date: " + vacayStart + " End Date: " + vacayEnd + "\nExcursion: " + excursionNames;
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, combinedInfo);
                    sendIntent.putExtra(Intent.EXTRA_TITLE, vacayName);
                    sendIntent.setType("text/plain");
                    Intent shareIntent = Intent.createChooser(sendIntent, null);
                    startActivity(shareIntent);
                    return true;
                }

                if (item.getItemId() == R.id.alertVacation) {
                    String dateFromScreenEnd = editEnd.getText().toString();
                    String dateFromScreen = editStart.getText().toString();
                    String nameScreen = editName.getText().toString();
                    String myFormat = "MM/dd/yy"; //In which you need put here
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                    Date myStartDate = null;
                    Date myEndDate = null;
                    try {
                        myStartDate = sdf.parse(dateFromScreen);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        Long trigger = myStartDate.getTime();
                        Intent intent = new Intent(VacationDetail.this, MyReceiver.class);
                        intent.putExtra("alert", nameScreen + " vacation is starting today.");
                        PendingIntent sender = PendingIntent.getBroadcast(VacationDetail.this, ++MainActivity.numAlert, intent, PendingIntent.FLAG_ONE_SHOT |PendingIntent.FLAG_IMMUTABLE);
                        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                        alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                    } catch (Exception e) {

                    }

                    try {
                        myEndDate = sdf.parse(dateFromScreenEnd);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        Long triggerEnd = myEndDate.getTime();
                        Intent intentEnd = new Intent(VacationDetail.this, MyReceiver.class);
                        intentEnd.putExtra("alert", nameScreen +" vacation is ending today");
                        PendingIntent senderEnd = PendingIntent.getBroadcast(VacationDetail.this, ++MainActivity.numAlert, intentEnd, PendingIntent.FLAG_ONE_SHOT |PendingIntent.FLAG_IMMUTABLE);
                        AlarmManager alarmManagerEnd = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                        alarmManagerEnd.set(AlarmManager.RTC_WAKEUP, triggerEnd, senderEnd);
                    } catch (Exception e) {

                    }
                    return true;
                }
            return super.onOptionsItemSelected(item);
    }


            @Override
            protected void onResume() {

                super.onResume();
                /*RecyclerView recyclerView = findViewById(R.id.recyclerExcursion);
                final ExcursionAdapter excursionAdapter = new ExcursionAdapter(this);
                recyclerView.setAdapter(excursionAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                List<Excursions> filteredParts = new ArrayList<>();
                for (Excursions e : repository.getAllExcursion()) {
                    if (e.getVacationID() == vacationID) filteredParts.add(e);
                }
                excursionAdapter.setExcursions(filteredParts);*/

                //Toast.makeText(ProductDetails.this,"refresh list",Toast.LENGTH_LONG).show();
            }
        }


