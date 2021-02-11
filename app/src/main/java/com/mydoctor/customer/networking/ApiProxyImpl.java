package com.mydoctor.customer.networking;

import android.content.Context;

import com.mydoctor.customer.models.AddAppointmentRequestModel;
import com.mydoctor.customer.models.AddAppointmentResponseModel;
import com.mydoctor.customer.models.AddSpecializationResponseModel;
import com.mydoctor.customer.models.CommonSuccessMessageModel;
import com.mydoctor.customer.models.DeleteAppointmentsResponseModel;
import com.mydoctor.customer.models.GetAllAppointmentsResponseModel;
import com.mydoctor.customer.models.GetAllClinicsResponseModel;
import com.mydoctor.customer.models.GetPatientMedicalsResponseModel;
import com.mydoctor.customer.models.LoginRequestModel;
import com.mydoctor.customer.models.NewMedicalRequestModel;
import com.mydoctor.customer.models.NewMedicalResponseModel;
import com.mydoctor.customer.models.RegisterDoctorRequestModel;
import com.mydoctor.customer.models.RegisterPatientRequestModel;
import com.mydoctor.customer.models.RegisterDoctorResponseModel;
import com.mydoctor.customer.models.RegisterPatientResponseModel;
import com.mydoctor.customer.models.SpecializationRequestModel;
import com.mydoctor.customer.utils.HeaderParams;
import com.mydoctor.customer.utils.networkingutils.APiCaller;
import com.mydoctor.customer.utils.networkingutils.ApiClient;
import com.mydoctor.customer.utils.networkingutils.DataCallback;

import retrofit2.Retrofit;

import static com.mydoctor.customer.utils.database.FileUtils.getFile;

public class ApiProxyImpl implements ApiProxy {

    private static ApiProxyImpl instance;
    private final Retrofit client;
    private Context context;

    private ApiProxyImpl() {
        client = ApiClient.getClient();
    }

    public static synchronized ApiProxyImpl getInstance() {
        if (instance == null) {
            instance = new ApiProxyImpl();
        }
        return instance;
    }

    @Override
    public void logout(DataCallback<CommonSuccessMessageModel> dataCallback) {
        APiCaller.getInstance().getData(() -> client.create(EndPoints.class).logoutUrl(HeaderParams.addHeaderParamsSessionKey()), dataCallback);
    }

    @Override
    public void loginDoctor(DataCallback<RegisterDoctorResponseModel> dataCallback, LoginRequestModel loginRequestModel) {
        APiCaller.getInstance().getData(() -> client.create(EndPoints.class).loginDoctorUrl(HeaderParams.addHeaderParams(), loginRequestModel), dataCallback);
    }

    @Override
    public void loginPatient(DataCallback<RegisterPatientResponseModel> dataCallback, LoginRequestModel loginRequestModel) {
        APiCaller.getInstance().getData(() -> client.create(EndPoints.class).loginPatientUrl(HeaderParams.addHeaderParams(), loginRequestModel), dataCallback);
    }

    @Override
    public void registerDoctor(DataCallback<RegisterDoctorResponseModel> dataCallback, RegisterDoctorRequestModel registerDoctorRequestModel) {
        APiCaller.getInstance().getData(() -> client.create(EndPoints.class).registerDoctorUrl(HeaderParams.addHeaderParams(), registerDoctorRequestModel), dataCallback);
    }

    @Override
    public void registerPatient(DataCallback<RegisterPatientResponseModel> dataCallback, RegisterPatientRequestModel registerPatientRequestModel) {
        APiCaller.getInstance().getData(() -> client.create(EndPoints.class).registerPatientUrl(HeaderParams.addHeaderParams(), registerPatientRequestModel), dataCallback);
    }

    @Override
    public void addSpecialization(DataCallback<AddSpecializationResponseModel> dataCallback, SpecializationRequestModel specializationRequestModel) {
        APiCaller.getInstance().getData(() -> client.create(EndPoints.class).addSpecializationUrl(HeaderParams.addHeaderParams(), specializationRequestModel), dataCallback);
    }

    @Override
    public void addMedicalExamination(DataCallback<NewMedicalResponseModel> dataCallback, NewMedicalRequestModel newMedicalRequestModel, String userId) {
        APiCaller.getInstance().getData(() -> client.create(EndPoints.class).addMedicalExaminationUrl(HeaderParams.addHeaderParamsSessionKey(), newMedicalRequestModel, userId), dataCallback);
    }

    @Override
    public void getAllAppointments(DataCallback<GetAllAppointmentsResponseModel> dataCallback) {
        APiCaller.getInstance().getData(() -> client.create(EndPoints.class).getAllAppointmentsUrl(HeaderParams.addHeaderParamsSessionKey()), dataCallback);
    }

    @Override
    public void addAppointment(DataCallback<AddAppointmentResponseModel> dataCallback, AddAppointmentRequestModel addAppointmentRequestModel) {
        APiCaller.getInstance().getData(() -> client.create(EndPoints.class).addAppointmentUrl(HeaderParams.addHeaderParamsSessionKey(), addAppointmentRequestModel), dataCallback);
    }

    @Override
    public void getAllClinics(DataCallback<GetAllClinicsResponseModel> dataCallback) {
        APiCaller.getInstance().getData(() -> client.create(EndPoints.class).getAllClinicsUrl(HeaderParams.addHeaderParams()), dataCallback);
    }

    @Override
    public void deleteAppointment(DataCallback<DeleteAppointmentsResponseModel> dataCallback, String appointmentId) {
        APiCaller.getInstance().getData(() -> client.create(EndPoints.class).deleteAppointmentUrl(HeaderParams.addHeaderParamsSessionKey(), appointmentId), dataCallback);
    }

    @Override
    public void getAllPatientMedicals(DataCallback<GetPatientMedicalsResponseModel> dataCallback, String userId) {
        APiCaller.getInstance().getData(() -> client.create(EndPoints.class).getAllPatientMedicalsUrl(HeaderParams.addHeaderParams(), userId), dataCallback);
    }
}
