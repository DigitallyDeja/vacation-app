package com.example.d424vacationplanner.UI;

import static android.view.LayoutInflater.from;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.d308vacationplanner_deja.R;
import com.example.d308vacationplanner_deja.entities.Vacations;

import java.util.List;

public class VacationAdapter extends RecyclerView.Adapter<VacationAdapter.VacationViewHolder> {
    private List<Vacations> mVacation;
    private final Context context;
    private final LayoutInflater mInflater;
    public VacationAdapter(Context context){
        mInflater= LayoutInflater.from(context);
        this.context = context;
    }
    public class VacationViewHolder extends RecyclerView.ViewHolder {
        private final TextView vacationItemView;
        public VacationViewHolder(@NonNull View itemView) {
            super(itemView);
            vacationItemView=itemView.findViewById(R.id.textView3);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Vacations current = mVacation.get(position);
                    Intent intent=new Intent(context,VacationDetail.class);
                    intent.putExtra("id", current.getVacationID());
                    intent.putExtra("name", current.getVacationName());
                    intent.putExtra("hotel", current.getHotelName());
                    intent.putExtra("start date", current.getStartDate());
                    intent.putExtra("end date", current.getEndDate());
                    context.startActivity(intent);
                }
            });
        }
    }
    @NonNull
    @Override
    public VacationAdapter.VacationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.vacation_list_item, parent, false);
        return new VacationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VacationAdapter.VacationViewHolder holder, int position) {
        if(mVacation != null){
            Vacations current = mVacation.get(position);
            String name = current.getVacationName();
            holder.vacationItemView.setText(name);
        }
        else{
            holder.vacationItemView.setText("No vacation name");
        }
    }

    @Override
    public int getItemCount() {
        if(mVacation != null){
            return mVacation.size();
        }
        else return 0;

    }

    public void setVacations(List<Vacations> vacations){
        mVacation = vacations;
        notifyDataSetChanged();
    }

}
