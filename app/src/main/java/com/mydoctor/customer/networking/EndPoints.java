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

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

interface EndPoints {

    @DELETE(ApiConstant.LOGOUT_API_URL)
    Call<CommonSuccessMessageModel> logoutUrl(@HeaderMap Map<String, String> headerMap);

    @POST(ApiConstant.LOGIN_DOCTOR_API_URL)
    Call<RegisterDoctorResponseModel> loginDoctorUrl(@HeaderMap Map<String, String> headerMap, @Body LoginRequestModel loginDoctorRequestModel);

    @POST(ApiConstant.LOGIN_PATIENT_API_URL)
    Call<RegisterPatientResponseModel> loginPatientUrl(@HeaderMap Map<String, String> headerMap, @Body LoginRequestModel loginPatientRequestModel);

    @POST(ApiConstant.REGISTER_DOCTOR_API_URL)
    Call<RegisterDoctorResponseModel> registerDoctorUrl(@HeaderMap Map<String, String> headerMap, @Body RegisterDoctorRequestModel registerDoctorRequestModel);

    @POST(ApiConstant.REGISTER_PATIENT_API_URL)
    Call<RegisterPatientResponseModel> registerPatientUrl(@HeaderMap Map<String, String> headerMap, @Body RegisterPatientRequestModel registerPatientRequestModel);

    @POST(ApiConstant.SPECIALIZATION_API_URL)
    Call<AddSpecializationResponseModel> addSpecializationUrl(@HeaderMap Map<String, String> headerMap, @Body SpecializationRequestModel specializationRequestModel);

    @POST(ApiConstant.ADD_MEDICAL_API_URL)
    Call<NewMedicalResponseModel> addMedicalExaminationUrl(@HeaderMap Map<String, String> headerMap, @Body NewMedicalRequestModel specializationRequestModel, @Path("userId") String userId);

    @GET(ApiConstant.GET_APPOINTMENTS_API_URL)
    Call<GetAllAppointmentsResponseModel> getAllAppointmentsUrl(@HeaderMap Map<String, String> headerMap);

    @POST(ApiConstant.ADD_APPOINTMENT_API_URL)
    Call<AddAppointmentResponseModel> addAppointmentUrl(@HeaderMap Map<String, String> headerMap, @Body AddAppointmentRequestModel addAppointmentRequestModel);

    @GET(ApiConstant.GET_ALL_CLINICS)
    Call<GetAllClinicsResponseModel> getAllClinicsUrl(@HeaderMap Map<String, String> headerMap);

    @DELETE(ApiConstant.DELETE_APPOINTMENT)
    Call<DeleteAppointmentsResponseModel> deleteAppointmentUrl(@HeaderMap Map<String, String> headerMap, @Path("appointmentId") String appointmentId);

    @GET(ApiConstant.GET_ALL_PATIENT_MEDICALS)
    Call<GetPatientMedicalsResponseModel> getAllPatientMedicalsUrl(@HeaderMap Map<String, String> headerMap, @Path("userId") String userId);
}
