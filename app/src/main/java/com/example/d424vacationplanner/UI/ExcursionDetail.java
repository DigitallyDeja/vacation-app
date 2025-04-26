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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.d424vacationplanner.R;
import com.example.d424vacationplanner.database.Repository;
import com.example.d424vacationplanner.entities.Excursions;
import com.example.d424vacationplanner.entities.Vacations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class ExcursionDetail extends AppCompatActivity {
    String name;
    String date;
    int excursionID;
    int vacationID;
    EditText editName;
    EditText editDate;
    Repository repository;
    Excursions excursions;
    Excursions currentExcursion;
    DatePickerDialog.OnDateSetListener startExcursion;
    final Calendar myCalendarExcursion = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excursion_detail2);
        Button nextButton = findViewById(R.id.report);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExcursionDetail.this, Reports.class);
                intent.putExtra("vacationID", vacationID);
                startActivity(intent);
            }
        });

        repository = new Repository(getApplication());
        editName = findViewById(R.id.excursionName);
        editDate = findViewById(R.id.date);
        name = getIntent().getStringExtra("name");
        editName.setText(name);
        date = getIntent().getStringExtra("date");
        editDate.setText(date);
        excursionID = getIntent().getIntExtra("id", -1);
        vacationID = getIntent().getIntExtra("vacationID", -1);

        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

         ArrayList<Vacations> vacationsArrayList = new ArrayList<>();
        vacationsArrayList.addAll(repository.getAllVacation());
        ArrayList<Integer> vacationIdList = new ArrayList<>();
        for (Vacations vacations : vacationsArrayList) {
            vacationIdList.add(vacations.getVacationID());
        }
        ArrayAdapter<Integer> vacationIdAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,vacationIdList);
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(vacationIdAdapter);

        startExcursion = (view, year, month, dayOfMonth) -> {
            myCalendarExcursion.set(Calendar.YEAR, year);
            myCalendarExcursion.set(Calendar.MONTH, month);
            myCalendarExcursion.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };

        editDate.setOnClickListener(v -> {
            Date date;
            String info = editDate.getText().toString();
            if (info.equals("")) info = editDate.getText().toString();
            try {
                myCalendarExcursion.setTime(sdf.parse(info));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            new DatePickerDialog(ExcursionDetail.this, startExcursion, myCalendarExcursion.get(Calendar.YEAR),
                    myCalendarExcursion.get(Calendar.MONTH), myCalendarExcursion.get(Calendar.DAY_OF_MONTH)).show();
        });
    }
    private void updateLabel(){
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editDate.setText(sdf.format(myCalendarExcursion.getTime()));
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_excursiondetails, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        if (item.getItemId() == R.id.excursionsave) {
            String myFormat = "MM/dd/yy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            Vacations selectedVacation = null;
            for (Vacations v : repository.getAllVacation()) {
                if (v.getVacationID() == vacationID) selectedVacation= v;
            }

            if (selectedVacation == null) {
                Toast.makeText(this, "Invalid vacation ID. Please select a valid vacation.", Toast.LENGTH_LONG).show();
                return true;
            }
            try{
                    Date excursionDate = sdf.parse(editDate.getText().toString());
                    Date vacationStart = sdf.parse(selectedVacation.getStartDate());
                    Date vacationEnd = sdf.parse(selectedVacation.getEndDate());
                    if (excursionDate.before(vacationStart) || excursionDate.after(vacationEnd)) {
                        Toast.makeText(ExcursionDetail.this, "Your excursion must be in between the vacation start and end date.", Toast.LENGTH_LONG).show();
                        return false;
                    }
            }
            catch(ParseException e){
                e.printStackTrace();
            }
            if (excursionID == -1) {
                if (repository.getAllExcursion().size() == 0)
                    excursionID = 1;
                else excursionID = repository.getAllExcursion().get(repository.getAllExcursion().size() - 1).getExcursionID() + 1;
                excursions = new Excursions(excursionID, editName.getText().toString(), vacationID, editDate.getText().toString());
                repository.insert(excursions);
            } else {
                excursions = new Excursions(excursionID, editName.getText().toString(), vacationID, editDate.getText().toString());
                repository.update(excursions);
            }
            this.finish();
            return true;
        }

        if (item.getItemId() == R.id.excursiondelete) {
            currentExcursion = null;

            for (Excursions e : repository.getAllExcursion()) {
                if (e.getExcursionID() == excursionID) {
                    currentExcursion = e;
                    break;
                }
            }


            if (currentExcursion != null) {
                repository.delete(currentExcursion);
                Toast.makeText(ExcursionDetail.this, currentExcursion.getExcursionName() + " was deleted", Toast.LENGTH_LONG).show();
                this.finish();
            }
            else {
                Toast.makeText(this,  " there are no excursions to delete", Toast.LENGTH_LONG).show();
                }
            return true;
        }
        if (item .getItemId() == R.id.alertExcursion){
            String dateFromScreen = editDate.getText().toString();
            String myFormat = "MM/dd/yy";
            String alert = "Excursion: " + editName.getText().toString() + " is scheduled for today ";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            Date myDate = null;
            try {
                myDate = sdf.parse(dateFromScreen);
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                Long trigger = myDate.getTime();
                Intent intent = new Intent(ExcursionDetail.this, MyReceiver.class);
                intent.putExtra("alert", alert);
                PendingIntent sender = PendingIntent.getBroadcast(ExcursionDetail.this, ++MainActivity.numAlert, intent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
            } catch (Exception e) {

            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

}