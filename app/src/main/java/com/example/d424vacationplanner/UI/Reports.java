package com.example.d424vacationplanner.UI;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.d424vacationplanner.R;
import com.example.d424vacationplanner.database.Repository;
import com.example.d424vacationplanner.entities.Vacations;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Reports extends AppCompatActivity {

    private Repository repository;
    private TextView reportResults;
    private int vacationID;
    private Button startButton;
    private Button endButton;
    private Button generateReport;
    private TextView startText;
    private TextView endText;

    private final Calendar myCalendarVacationStart = Calendar.getInstance();
    private final Calendar myCalendarVacationEnd = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        startButton = findViewById(R.id.buttonStart);
        endButton = findViewById(R.id.buttonEnd);
        generateReport = findViewById(R.id.buttonRpt);
        startText = findViewById(R.id.startText);
        endText = findViewById(R.id.endText);
        reportResults = findViewById(R.id.reportResults);

        repository = new Repository(getApplication());
        vacationID = getIntent().getIntExtra("vacationID", -1);

        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        startButton.setOnClickListener(v -> {
            new DatePickerDialog(
                    Reports.this,
                    (view, year, month, dayOfMonth) -> {
                        myCalendarVacationStart.set(Calendar.YEAR, year);
                        myCalendarVacationStart.set(Calendar.MONTH, month);
                        myCalendarVacationStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        startText.setText(sdf.format(myCalendarVacationStart.getTime()));
                    },
                    myCalendarVacationStart.get(Calendar.YEAR),
                    myCalendarVacationStart.get(Calendar.MONTH),
                    myCalendarVacationStart.get(Calendar.DAY_OF_MONTH)
            ).show();
        });

        endButton.setOnClickListener(v -> {
            new DatePickerDialog(
                    Reports.this,
                    (view, year, month, dayOfMonth) -> {
                        myCalendarVacationEnd.set(Calendar.YEAR, year);
                        myCalendarVacationEnd.set(Calendar.MONTH, month);
                        myCalendarVacationEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        endText.setText(sdf.format(myCalendarVacationEnd.getTime()));
                    },
                    myCalendarVacationEnd.get(Calendar.YEAR),
                    myCalendarVacationEnd.get(Calendar.MONTH),
                    myCalendarVacationEnd.get(Calendar.DAY_OF_MONTH)
            ).show();
        });

        generateReport.setOnClickListener(v -> {
            String startDateString = startText.getText().toString();
            String endDateString = endText.getText().toString();

            if (startDateString.isEmpty() || endDateString.isEmpty()) {
                Toast.makeText(this, "Please select both start and end dates!", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                Date startDate = sdf.parse(startDateString);
                Date endDate = sdf.parse(endDateString);

                if (startDate != null && endDate != null) {
                    List<Location> allLocation = new ArrayList<>();

                    for (Vacations vacation : repository.getAllVacation()) {
                        allLocation.add(vacation);
                    }

                    SimpleDateFormat timestampFormat = new SimpleDateFormat("MM/dd/yy hh:mm a", Locale.US);
                    String currentTimestamp = timestampFormat.format(new Date());
                    StringBuilder report = new StringBuilder();
                    report.append("Report Generated: ").append(currentTimestamp).append("\n\n");

                    for (Location location : allLocation) {
                        Date eventDate = sdf.parse(location.getDate());

                        if (eventDate != null) {
                            if (!eventDate.before(startDate) && !eventDate.after(endDate)) {
                                report.append(location.getLocationDetails()).append("\n\n");
                            }
                        }
                    }

                    if (report.length() > 0) {
                        reportResults.setText(report.toString());
                    } else {
                        Toast.makeText(this, "No events found within the range.", Toast.LENGTH_SHORT).show();
                        reportResults.setText("");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Dates are not chosen", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

