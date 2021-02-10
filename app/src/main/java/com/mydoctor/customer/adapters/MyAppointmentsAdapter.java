package com.mydoctor.customer.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.mydoctor.customer.R;
import com.mydoctor.customer.models.AppointmentsModel;
import com.mydoctor.customer.utils.Logger;
import com.mydoctor.customer.utils.ui.AbstractActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAppointmentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private final Context context;
    private AppointmentDeleteListener appointmentDeleteListener;
    private List<AppointmentsModel> appointmentsModelList = new ArrayList<>();

    public MyAppointmentsAdapter(Context context) {
        this.context = context;
    }

    public void setAppointmentListener(AppointmentDeleteListener appointmentDeleteListener) {

        this.appointmentDeleteListener = appointmentDeleteListener;
    }

    public interface AppointmentDeleteListener {

        void onAppointmentDelete(String Id, int index);

        void isAppointmentAvailable(boolean isAvailable);
    }

    public void removeItem(int index) {

        appointmentsModelList.remove(index);

        notifyItemRemoved(index);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case ITEM:
                View appointmentsList = inflater.inflate(R.layout.item_appointment, parent, false);
                viewHolder = new AppointmentViewHolder(appointmentsList, context);
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
        AppointmentViewHolder appointmentViewHolder = ((AppointmentViewHolder) holder);
        appointmentViewHolder.setValues();
        appointmentViewHolder.bind();
    }



    public void setAppointmentsList(List<AppointmentsModel> appointmentsList) {

        this.appointmentsModelList = appointmentsList;

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (appointmentsModelList == null || appointmentsModelList.isEmpty()) {
            appointmentDeleteListener.isAppointmentAvailable(false);
            return 0;
        } else {
            appointmentDeleteListener.isAppointmentAvailable(true);
            return appointmentsModelList.size();
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {

        LoadingViewHolder(View itemView) {
            super(itemView);
        }
    }

    class AppointmentViewHolder extends RecyclerView.ViewHolder {

        private final Context context;

        @BindView(R.id.txt_time)
        TextView txtTimeAgo;

        @BindView(R.id.txt_message)
        TextView txtMessage;

        @BindView(R.id.txt_alert)
        TextView txtAlert;

        @BindView(R.id.img_delete_appointment)
        ImageView imgDeleteAppointment;

        AppointmentViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
        }

        void bind() {
            imgDeleteAppointment.setOnClickListener((View v) ->
                    appointmentDeleteListener.onAppointmentDelete(appointmentsModelList.get(getAdapterPosition()).getId(), getAdapterPosition()));
        }

        void setValues() {
            txtAlert.setText(appointmentsModelList.get(getAdapterPosition()).getClinicName());
            txtMessage.setText(appointmentsModelList.get(getAdapterPosition()).getAppointmentKind());
            txtTimeAgo.setText(appointmentsModelList.get(getAdapterPosition()).getAppointmentDate().substring(0,appointmentsModelList.get(getAdapterPosition()).getAppointmentDate().indexOf("T")));
        }
    }
}
