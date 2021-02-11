package com.mydoctor.customer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mydoctor.customer.R;
import com.mydoctor.customer.models.AppointmentsModel;
import com.mydoctor.customer.models.MedicalModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyMedicalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private final Context context;
    private List<MedicalModel> medicalModelList = new ArrayList<>();

    public MyMedicalAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case ITEM:
                View appointmentsList = inflater.inflate(R.layout.item_appointment, parent, false);
                viewHolder = new MedicalViewHolder(appointmentsList, context);
                break;
            case LOADING:
                View loadingViewHolder = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingViewHolder(loadingViewHolder);
                break;
            default:
                break;
        }
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MedicalViewHolder medicalViewHolder = ((MedicalViewHolder) holder);
        medicalViewHolder.setValues();
    }



    public void setMedicalList(List<MedicalModel> medicalModelList) {

        this.medicalModelList = medicalModelList;

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
            return medicalModelList.size();
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {

        LoadingViewHolder(View itemView) {
            super(itemView);
        }
    }

    class MedicalViewHolder extends RecyclerView.ViewHolder {

        private final Context context;

        @BindView(R.id.txt_time)
        TextView txtTimeAgo;

        @BindView(R.id.txt_message)
        TextView txtMessage;

        @BindView(R.id.txt_alert)
        TextView txtAlert;

        @BindView(R.id.img_delete_appointment)
        ImageView imgDeleteAppointment;

        MedicalViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
        }

        void setValues() {
            txtAlert.setText(medicalModelList.get(getAdapterPosition()).getExamName());
            txtMessage.setText(medicalModelList.get(getAdapterPosition()).getExamResult());
            txtTimeAgo.setVisibility(View.GONE);
            imgDeleteAppointment.setVisibility(View.GONE);
        }
    }
}
