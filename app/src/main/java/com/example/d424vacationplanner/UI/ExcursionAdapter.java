package com.example.d424vacationplanner.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.d308vacationplanner_deja.R;
import com.example.d308vacationplanner_deja.entities.Excursions;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ExcursionAdapter extends RecyclerView.Adapter<ExcursionAdapter.ExcursionViewHolder> {
    private List<Excursions> mExcursion;
    private final Context context;
    private final LayoutInflater mInflater;

    class ExcursionViewHolder extends RecyclerView.ViewHolder {
        private final TextView excursionItemView;

        private final TextView excursionItemView2;

        private ExcursionViewHolder(View itemView) {
            super(itemView);
            excursionItemView = itemView.findViewById(R.id.textView4);
            excursionItemView2 = itemView.findViewById(R.id.textView5);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Excursions current = mExcursion.get(position);
                    Intent intent = new Intent(context, ExcursionDetail.class);
                    intent.putExtra("id", current.getExcursionID());
                    intent.putExtra("name", current.getExcursionName());
                    intent.putExtra("date", current.getExcursionDate());
                    intent.putExtra("vacationID", current.getVacationID());
                    context.startActivity(intent);
                }
            });
        }
    }

    public ExcursionAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ExcursionAdapter.ExcursionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.excursion_list_item, parent, false);
        return new ExcursionViewHolder(itemView);
    }
@Override
    public void onBindViewHolder(@NotNull ExcursionViewHolder holder, int position){
        if(mExcursion != null){
            Excursions current =mExcursion.get(position);
            String name = current.getExcursionName();
            int vacationID = current.getVacationID();
            holder.excursionItemView.setText(name);
            holder.excursionItemView2.setText(Integer.toString(vacationID));
        }
        else{
            holder.excursionItemView.setText("No excursion name");
            holder.excursionItemView2.setText("No product ID");
        }
}
    public void setExcursions(List<Excursions> excursions){
        mExcursion = excursions;
        notifyDataSetChanged();
    }
    public int getItemCount(){
        if(mExcursion !=null){
            return mExcursion.size();
        }
        else return 0;
    }
}








