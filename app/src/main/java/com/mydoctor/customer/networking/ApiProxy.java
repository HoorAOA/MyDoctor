package com.mydoctor.customer.networking;
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
import com.mydoctor.customer.utils.networkingutils.DataCallback;

import retrofit2.http.Body;

public interface ApiProxy {

    void logout(DataCallback<CommonSuccessMessageModel> dataCallback);

    void loginDoctor(DataCallback<RegisterDoctorResponseModel> dataCallback, @Body LoginRequestModel loginRequestModel);

    void loginPatient(DataCallback<RegisterPatientResponseModel> dataCallback, @Body LoginRequestModel loginRequestModel);

    void registerDoctor(DataCallback<RegisterDoctorResponseModel> dataCallback, @Body RegisterDoctorRequestModel registerDoctorRequestModel);

    void registerPatient(DataCallback<RegisterPatientResponseModel> dataCallback, @Body RegisterPatientRequestModel registerPatientRequestModel);

    void addSpecialization(DataCallback<AddSpecializationResponseModel> dataCallback, @Body SpecializationRequestModel specializationRequestModel);

    void addMedicalExamination(DataCallback<NewMedicalResponseModel> dataCallback, @Body NewMedicalRequestModel newMedicalRequestModel, String userId);

    void getAllAppointments(DataCallback<GetAllAppointmentsResponseModel> dataCallback);

    void addAppointment(DataCallback<AddAppointmentResponseModel> dataCallback, @Body AddAppointmentRequestModel addAppointmentRequestModel);

    void getAllClinics(DataCallback<GetAllClinicsResponseModel> dataCallback);

    void deleteAppointment(DataCallback<DeleteAppointmentsResponseModel> dataCallback, String appointmentId);

    void getAllPatientMedicals(DataCallback<GetPatientMedicalsResponseModel> dataCallback, String userId);

}
