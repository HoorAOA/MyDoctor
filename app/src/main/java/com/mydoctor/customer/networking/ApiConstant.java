package com.mydoctor.customer.networking;

public class ApiConstant {

    static final String LOGOUT_API_URL = "/secure/users/logout";
    static final String LOGIN_DOCTOR_API_URL = "/doctor/sign-in";
    static final String LOGIN_PATIENT_API_URL = "/patient/auth";
    static final String REGISTER_DOCTOR_API_URL = "/doctor/add-doctor";
    static final String REGISTER_PATIENT_API_URL = "/patient/add-patient";
    static final String SPECIALIZATION_API_URL = "/specialization/add-specialization";
    static final String ADD_MEDICAL_API_URL = "/patient/add-medical-exam/{userId}";
    static final String GET_APPOINTMENTS_API_URL = "/appointments/";
    static final String ADD_APPOINTMENT_API_URL = "/appointments/add-appointment/";
    static final String GET_ALL_CLINICS = "/clinic/get-all-clinics/";
    static final String DELETE_APPOINTMENT = "/appointments/delete-appointment/{appointmentId}";
    static final String GET_ALL_PATIENT_MEDICALS = "/patient/get-all-medical-examinations/{userId}";

    private ApiConstant() {
        throw new IllegalStateException("ApiConstant class");
    }


}