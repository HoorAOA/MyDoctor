package com.mydoctor.customer.utils;


public class AppConstants {

    public static final String PREFERENCE_RESOURCE_NAME = "deliverypulse";
    public static final String DB_NAME = "DeliveryPulseDb";
    public static final String DB_NAME_WITH_VERSION = "delivery_pulse_db_version_";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String ACCEPT_LANGUAGE = "Accept-Language";
    public static final String CONTENT_TYPE_VALUE = "application/json";
    public static final String CONTENT_TYPE_VALUE_HTML = "text/html";
    public static final String ACCEPT_LANGUAGE_VALUE_EN = "en-us";
    public static final String ACCEPT_LANGUAGE_VALUE_AR = "ar";
    public static final String ACCEPT_LANGUAGE_VALUE_AR_SD = "ar-SD";
    public static final String SESSION_KEY = "Authorization";
    public static final String DOCTOR = "doctor";
    public static final String DELIVERY_AGENT_CANCEL = "Delivery Agent";
    public static final String CUSTOMER = "customer";
    public static final String PATIENT = "patient";
    public static final int PASSWORD_LENGTH = 8;
    public static final int MIN_PLACES_SEARCH_CHAR = 3;
    public static final int MIN_MOBILE_NUMBER_LENGTH = 7;
    public static final int MAX_MOBILE_NUMBER_LENGTH = 13;
    public static final String APP_LOCAL_LANGUAGE = "appLanguage";
    public static final String APP_DEFAULT_LANGUAGE = "en-us";
    //ar-ag
    public static final String DEVICE_TOKEN = "DEVICE_TOKEN";
    public static final int STATUS_CODE_SESSION_EXPIRED = 401;
    public static final String IS_SESSION_EXPIRED = "IS_SESSION_EXPIRED";
    public static final String DUTY_STATUS = "DUTY_STATUS";
    public static final String DUTY_STATUS_ON = "DUTY_STATUS_ON";
    public static final String DUTY_STATUS_OFF = "DUTY_STATUS_OFF";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String LOCATION_STREET_NUMBER = "LOCATION_STREET_NUMBER";
    public static final String LOCATION_TIME = "location_time";
    public static final String ACCURACY = "ACCURACY";
    public static final String LOCATION_BROADCAST = Config.APPLICATION_ID + ".LocationBroadcast";
    public static final String NEW_BOOKING_BROADCAST = Config.APPLICATION_ID + ".NewBookingBroadcast";
    public static final String TIMER_BROADCAST = Config.APPLICATION_ID + ".TimerBroadcast";
    public static final String COUNTDOWN_VALUE = "COUNTDOWN_VALUE";
    public static final String IS_TIMER_FINISHED = "IS_TIMER_FINISHED";

    public static final String IS_FOREGROUND_NOTIFICATION = "IS_FOREGROUND_NOTIFICATION";
    public static final String TRIP_STATUS = "TRIP_STATUS";
    public static final String BOOKING_ID = "BOOKING_ID";
    public static final int START_TRIP = 1500;
    public static final int REACHED = 1550;
    public static final int VALID_MAX_SPEED_THRESHOLD = 70;
    public static final double VALID_DISTANCE_THRESHOLD = 0.02800;
    public static final int VALID_ACCURACY_THRESHOLD = 20;
    public static final long FAST_FORWARD = 1;
    public static final long LOCATION_UPDATE_INTERVEL = 5000;
    public static final int MAX_CHAR_PLACE_SEARCH = 20;
    public static final String BROADCAST_MANAGER_FOR_NETWORK_AVAILABLE = "SOCKET_BROADCAST_MANAGER_FOR_NETWORK_AVAILABLE";
    public static final String COUNTRY_CODE = "country_code";
    public static final String PAYMENT_MODE = "PAYMENT_MODE";
    public static final String LOCATION = "LOCATION";
    public static final String PLACES_ID = "placesId";

    public static final String PROMOCODE = "PROMOCODE";
    public static final String PROMO_CODE_RESPONSE_MODEL = "PROMO_CODE_RESPONSE_MODEL";
    public static final String PROMOCODE_DESC = "PROMOCODE_DESC";
    public static final int COUNTRY_CODE_REQUEST_CODE = 200;
    public static final int REQUEST_CODE = 500;
    public static final int REQUEST_READ_CONTACTS = 501;
    public static final int MAX_GOODS_SELECTION = 1000;
    public static final String CATEGORY = "CATEGORY";
    public static final String GOODS = "goods";

    public static final String COUNTRY_SUDAN = "SD";

    public static final String COUNTRY_SOUTH_SUDAN = "SS";

    public static final int OTP_LENGTH = 4;
    public static final String ESTIMATION_ID = "ESTIMATION_ID";

    public static final String IS_CALL_FROM_TRACK_ORDER = "is_call_from_track_order";
    public static final String IS_DELIVERED = "isDelivered";
    public static final String NET_BANKING = "Net Banking";
    public static final String CASH_ON_DELIVERY = "Cash On Delivery";
    public static final int GOODS_REQUEST_CODE = 150;
    public static final int PAYMENT_TYPE_REQUEST_MODE = 155;
    public static final int PROMO_CODE_REQUEST_CODE = 156;
    public static final int SENDER_REQUEST_CODE = 157;
    public static final int BAD_RATING_THRESHOLD = 2;
    public static final int FARE_VIEW_TYPE_ONE = 220;
    public static final int FARE_VIEW_TYPE_TWO = 221;
    public static final int EMERGENCY_OPTION_REQUEST_CODE = 222;
    public static final int FAVOURITE_PICKUP_LOCATION_REQUEST_CODE = 223;
    public static final int FAVOURITE_DELIVERY_LOCATION_REQUEST_CODE = 224;
    public static final int FAVOURITE_BUY_ME_DELIVERY_LOCATION_REQUEST_CODE = 225;
    public static final int ADD_CREDIT = 226;
    public static final int SEND_CREDIT = 227;
    public static final int CANCELLATION_OPTION_REQUEST_CODE = 298;
    public static final int CANCEL_JOB_REQUEST_CODE = 356;
    public static final int VERIFY_OTP_REQUEST_CODE = 272;
    public static final int ONLINE_WITH_SHIPMENT_REQUEST = 312;
    public static final int ONLINE_WITHOUT_SHIPMENT_REQUEST = 313;
    public static final int OFFLINE = 314;

    public static final String SHIPMENT = "shipment";


    public static final String PHONE_NO = "PHONE_NO";
    public static final String OTP_ID = "OTP_ID";

    public static final String FARE_DIALOG_FRAGMENT = "fareBottomSheet";
    public static final String REJECT_DIALOG_FRAGMENT = "rejectBottomSheet";
    public static final String CALL_DIALOG_FRAGMENT = "callBottomSheet";

    public static final String LOCATION_ADDRESS = "LOCATION ADDRESS";
    public static final String FAV_LOCATION_ADDRESS_LIST = "FAV_LOCATION_ADDRESS_LIST";
    public static final String IS_PICKUP = "IS_PICKUP";
    public static final String REQUEST_SENT = "requestSent";
    public static final String IS_BUY_ME = "buyme";
    public static final String BUY_ME = "Buy Me";
    public static final String SUBBOOKINGS_LIST = "subBookingsList";

    public static final String IMAGE_FILE = "IMAGE_FILE";
    public static final String FILE_EXTENSION = "FILE_EXTENSION";

    public static final double DEFAULT_LATITUDE = 15.5872528;
    public static final double DEFAULT_LONGITUDE = 32.5254827;

    public static final String WEB_VIEW_TYPE = "WEB_VIEW_TYPE";
    public static final int ABOUT_US = 1;
    public static final int COPYRIGHT = 2;
    public static final int PRIVACY_POLICY = 3;
    public static final int TERMS_AND_CONDITION = 4;

    public static final int ABOUT_US_DELIVERY_AGENT = 11;
    public static final int COPYRIGHT_DELIVERY_AGENT = 12;
    public static final int PRIVACY_POLICY_DELIVERY_AGENT = 13;
    public static final int TERMS_AND_CONDITION_DELIVERY_AGENT = 14;

    public static final String PRIVACY_POLICY_URL_SUFFIX = "customer-privacy-policy";
    public static final String TERMS_AND_CONDITION_URL_SUFFIX = "customer-terms-and-conditions";
    public static final String ABOUT_US_URL_SUFFIX = "about-us";
    public static final String COPYRIGHT_URL_SUFFIX = "copyrights";
    public static final String PRIVACY_POLICY_DA_URL_SUFFIX = "agent-privacy-policy";
    public static final String TERMS_AND_CONDITION_DA_URL_SUFFIX = "agent-terms-and-conditions";
    public static final String ABOUT_US_DA_URL_SUFFIX = "about-us";
    public static final String COPYRIGHT_DA_URL_SUFFIX = "copyrights";

    public static final String IS_BROADCAST_BOOKING = "isBroadcastBooking";
    public static final String IS_TIMER_ON = "isTimerOn";
    public static final String BROADCAST_BOOKING_TIME = "broadcastBookingTime";

    public static final String IS_PAST_ORDER = "isPastOrder";
    public static final String IS_ACTIVE_ORDER = "isActiveOrder";
    public static final String IS_EMERGENCY = "IS_EMERGENCY";
    public static final String ACTIVE = "Active";

    public static final String PAST = "Past";

    public static final String UNKNOWN = "UNKNOWN";

    public static final String SIGNATURE_PATH = "SIGNATURE_PATH";

    public static final String COMMON_MESSAGE = "commonMessage";

    public static final String ACTION_MESSAGE = "actionMessage";

    public static final String LOGOUT = "logout";

    public static final String DELETE = "delete";

    public static final String REJECT = "reject";

    public static final String VERSION_CHECK = "versionCheck";

    public static final String LOCATION_NOT_FOUND = "locationNotFound";

    public static final String EMERGENCY_REASON = "EMERGENCY_REASON";

    public static final String EMERGENCY_REASON_MODEL = "EMERGENCY_REASON_MODEL";

    public static final String CANCELLATION_REASON = "CANCELLATION_REASON";

    public static final String IMAGE_PATH = "imagePath";
    public static final String EXTRA_IS_LOCAL_IMAGE_PATH = "EXTRA_IS_LOCAL_IMAGE_PATH";

    public static final String DEVICE_TYPE_VERSION_API = "android";

    public static final String POSITIVE = "positive";

    public static final String NEGATIVE = "negative";

    public static final String REGISTRATION = "registration";

    public static final String REQUEST_PERMISSION = "REQUEST_PERMISSION";
    public static final String OPTIONAL_RELEASE_AVAILABLE = "OPTIONAL";
    public static final String NO_NEW_RELEASE = "NOT_REQUIRED";
    public static final String MANDATORY_RELEASE = "MANDATORY";
    public static final String UNSUPPORTED_RELEASE = "UNSUPPORTED_RELEASE";
    public static final String VERSION_UPDATE = "VERSION_UPDATE";
    public static final String VERSION_UPDATE_FORCE = "VERSION_UPDATE_FORCE";
    public static final String VERSION_ERROR = "VERSION_UPDATE_FORCE";

    public static final String UPDATE_NOW = "update _now";
    public static final String UPDATE_LATER = "update_later";
    public static final String EXIT = "exit";
    public static final String RETRY = "retry";
    public static final String META_DATA_KEY = "metaDataKey";
    public static final String USER_KEY = "userKey";

    public static final String OK = "OK";
    public static final int MIN_BATTERY_PERCENTAGE_VALUE = 80;
    public static final String FORGOT_PASS = "forgotPassword";
    public static final String BOOKING_DETAILS = "bookingDetails";

    public static final String BOOKING_DISPLAY_DETAILS = "bookingDisplayDetails";
    public static final int MANDATORY_FIELD_COUNT = 3;

    public static final String FILE_DETAIL = "BOOKING_DISPLAY_DETAILS";

    public static final String WEIGHT_VALUE_ZERO = "0 Kg";

    public static final int PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 113;

    public static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    public static final int REQUEST_LOCATION = 199;
    public static final int REQUEST_PICKUP_LOCATION = 262;
    public static final int REQUEST_DELIVERY_LOCATION = 263;
    public static final int REQUEST_CASH_NOT_COLLECTED = 265;
    public static final int REQUEST_BUY_ME_DELIVERY = 266;


    public static final String OS = "Android";
    public static final String META_DATA_API_FAILURE = "META_DATA_API_FAILURE";
    public static final String FAILURE = "FAILURE";
    public static final String IS_TUTORIAL_SHOWN = "is_tutorial_shown";
    public static final String IS_CHAT_SCREEN_VISIBLE = "is_chat_screen_visible";

    public static final String CHANGE_PSWD_SUCCESS = "CHANGE_PSWD_SUCCESS";
    public static final String ACTIVITY_NAME = "activityName";
    public static final String ACTIVITY_SENDER = "activitySender";
    public static final String ACTIVITY_RECEIVER = "activityReceiver";
    public static final String ACTIVITY_PROFILE = "activityProfile";
    public static final String FROM_ACTIVITY = "fromActivity";
    public static final String PLUS = "+";

    public static final String FILE_TYPE_IMAGE = "image";

    public static final String UPLOAD_TYPE_PROFILE = "avatar";
    public static final String UPLOAD_TYPE_FILE = "file";
    public static final String UPLOAD_TYPE = "UPLOAD_TYPE";


    /**
     * Chat constants
     */

    public static final String CHAT_FILE_AUDIO = "audio";
    public static final String CHAT_FILE_VIDEO = "video";
    public static final String CHAT_FILE_DOCUMENT = "document";
    public static final String CHAT_FILE_IMAGE = "image";
    public static final String CHAT_FILE_TYPE_TEXT = "text";
    public static final int IMAGE_PICKER_CODE = 1003;
    public static final String ROLE_DELIVERY_AGENT = "deliveryagent";
    public static final String CHAT_APPLICABLE_ROLE_TRANSPORTER = "transporter";
    public static final String ROLE_CUSTOMER = "customer";
    public static final String CHAT_APPLICABLE_ROLE_ADMIN = "admin";
    public static final int SENT_CHAT_MSG_VIEW = 1;
    public static final int RECEIVED_CHAT_MSG_VIEW = 2;
    public static final int CHAT_DATE_VIEW = 3;
    public static final int SENT_AUDIO_CHAT_MSG_VIEW = 4;
    public static final int RECEIVED_AUDIO_CHAT_MSG_VIEW = 5;
    public static final int SENT_CHAT_IMAGE_VIEW = 6;
    public static final int RECEIVED_CHAT_IMAGE_VIEW = 7;
    public static final String CURRENT_AUDIO_CHATS_JSON = "CURRENT_AUDIO_CHATS_JSON";
    public static final String CURRENT_IMAGE_CHATS_JSON = "CURRENT_IMAGE_CHATS_JSON";
    public static final String MEDIA_TYPE = "media_type";
    public static final int MEDIA_NOT_UPLOADED = 1;
    public static final int MEDIA_UPLOAD_DOWNLOAD_IN_PROGRESS = 2;
    public static final int MEDIA_UPLOAD_FAILED = 3;
    public static final int MEDIA_UPLOAD_DOWNLOAD_COMPLETE = 4;
    public static final String CHAT_MEDIA_UPLOAD_BROADCAST = Config.APPLICATION_ID + ".AudioChatUploadBroadCast";
    public static final String CHAT_MEDIA_DOWNLOAD_BROADCAST = Config.APPLICATION_ID + ".MediaChatDownloadBroadCast";
    public static final String CHAT_UPLOADED_MEDIA_JSON = "CHAT_UPLOADED_MEDIA_JSON";
    public static final String CHAT_MEDIA_UPLOAD_SUCCESS = "CHAT_MEDIA_UPLOAD_SUCCESS";
    public static final String CHAT_DOWNLOADED_MEDIA_JSON = "CHAT_DOWNLOADED_MEDIA_JSON";
    public static final String CHAT_MEDIA_DOWNLOAD_SUCCESS = "CHAT_MEDIA_DOWNLOAD_SUCCESS";
    public static final String IS_FROM_CHAT_ACTIVITY = "IS_FROM_CHAT_ACTIVITY";
    public static final String CHAT_USER_NAME = "CHAT_USER_NAME";
    public static final String RECEIVER_ID = "RECEIVER_ID";
    public static final String IS_FROM_START_DELIVERY = "IS_FROM_START_DELIVERY";


    public static final String TENANT_ADMIN = "TENANT ADMIN";
    public static final String ADMIN = "ADMIN";

    public static final String DEFAULT_COUNTRY_CODE = "+249";
    public static final String DATE_TIME = "dateTime";
    public static final String PICKUP_LOCATION = "pickupLocation";
    public static final String DELIVERY_LOCATION = "deliveryLocation";
    public static final String PICKUP_LATITUDE = "pickupLatitude";
    public static final String PICKUP_LONGITUDE = "pickupLongitude";
    public static final String DELIVERY_LATITUDE = "deliveryLatitude";
    public static final String DELIVERY_LONGITUDE = "deliveryLongitude";
    public static final String DELIVERY_LOCATION_ID = "DELIVERY_LOCATION_ID";
    public static final String PICKUP_LOCATION_ID = "PICKUP_LOCATION_ID";
    public static final String PICKUP_TIME = "pickupTime";
    public static final String SENDER_MODEL = "senderModel";
    public static final String RECEIVER_MODEL = "receiverModel";
    public static final String VERIFY = "verify";
    public static final String REMOVE = "remove";
    public static final int RECEIVER_RESULT_CODE = 26;
    public static final int CHANGE_PICKUP_LOCATION_REQUEST_CODE = 29;
    public static final int CHANGE_DELIVERY_LOCATION_REQUEST_CODE = 31;

    public static final String IS_NAVIGATED_TO_SETTINGS = "IS_NAVIGATED_TO_SETTINGS";

    public static final int DEFAULT_DISPLACEMENT_VALUE = 50;

    public static final String NEW_ORDER_ASSIGNED_BY_ADMIN = "TNJA";
    public static final String NEW_BOOKING_AVAILABLE = "TNBA";
    public static final String ORDER_CANCEL_BY_CUSTOMER = "TBCBC";
    public static final String ORDER_CANCEL_BY_ADMIN = "TBCBA";
    public static final String STATUS_UPDATE_BY_ADMIN = "TBUBA";
    public static final String DELIVERY_COMPLETE = "TDC";
    public static final String CHAT_MESSAGE_DELIVERY_AGENT = "TNMDA";
    public static final String CHAT_MESSAGE_CUSTOMER = "TNMC";
    public static final String OTP_FOR_VERIFICATION = "TVO";
    public static final String OTP_FOR_PASS_RECOVERY = "TFPO";
    public static final String DELIVERY_AGENT_ON_WAY = "TAOPU";
    public static final String DELIVERY_AGENT_ARRIVED = "TAAPU";
    public static final String DELIVERY_PICKUP_STARTED = "TDIP";
    public static final String DELIVERY_AGENT_UNAVAILABLE = "TUFDP";
    public static final String DELIVERY_AGENT_IN_EMERGENCY = "TEBDA";
    public static final String UPDATE_ABOUT_TEMP_PASS = "TRFTP";
    public static final String UPDATE_ABOUT_PAYMENT_STATUS = "MTPS";
    public static final String DELIVERY_AGENT_ACCEPT_BOOKING = "TBABA";
    public static final String OTP_CUSTOMER_TO_START_DELIVERY = "BBSO";
    public static final String BOOKING_INVOICE = "TBI";
    public static final String ORDER_CANCEL_BY_DELIVERY_AGENT = "TBCDA";
    public static final String CHAT_MESSAGE_FROM_ADMIN = "TNMA";
    public static final String CHAT_MESSAGE_FROM_BUSINESS_CUSTOMER = "TNMBC";
    public static final String ADDITIONAL_CHARGES_ADDED = "TBSA";
    public static final String MESSAGE_TYPE_BUY_ME_SURCHARGE_ADDED = "BMTBSA";
    public static final String BUYME_BOOKING_NOTI = "TBBA";
    public static final String AGENT_ID = "AGENT_ID";

    public static final String CANCEL_BOOKING_SUCCESS = "CANCEL_BOOKING_SUCCESS";
    public static final String LOCAL_BROADCAST_MANAGER_FOR_CRITICAL_BATTERY = "BROADCAST_MANAGER_FOR_CRITICAL_BATTERY";
    public static final String PUSH_NOTIFICATION = "pushNotification";
    public static final String PUSH_NOTIFICATION_FLAG = "pushNotification";

    public static final String RATING_COMPLETED = "RATING_COMPLETED";
    public static final String SERVICE_MODEL = "SERVICE_MODEL";
    public static final String EXTRA_SERVICE_ID = "SERVICE_ID";
    public static final String EXTRA_CATEGORY_ID = "CATEGORY_ID";
    public static final String EXTRA_BOOKING_DETAIL_MODEL = "EXTRA_BOOKING_DETAIL_MODEL";
    public static final String EXTRA_EDIT_ADDRESS = "EXTRA_EDIT_ADDRESS";
    public static final String SENDER_RECEIVER_MODEL = "SENDER_RECEIVER_MODEL";
    public static final String EXTRA_BOOKING_ID = "EXTRA_BOOKING_ID";
    public static final String EXTRA_PAYMENT_TYPE_ID = "EXTRA_PAYMENT_TYPE_ID";
    public static final String EXTRA_ROUND_TRIP = "ROUND_TRIP";


    public static final String BROADCAST_BOOKING_ID = "BROADCAST_BOOKING_ID";
    public static final String EXTRA_BOOKING_CODE = "EXTRA_BOOKING_CODE";
    public static final String EXTRA_BOOKING_STATUS_ID = "EXTRA_BOOKING_STATUS_ID";
    public static final String EXTRA_BOOKING_GOODS_LIST = "EXTRA_BOOKING_GOODS_LIST";
    public static final String EXTRA_ESTIMATION_FARE = "EXTRA_ESTIMATION_FAIR";
    public static final String EXTRA_BOOKING_REQUEST_MODEL = "EXTRA_BOOKING_REQUEST_MODEL";
    public static final String EXTRA_BOOKING_DISPLAY_MODEL = "EXTRA_BOOKING_DISPLAY_MODEL";
    public static final String EXTRA_PARAMETER = "extraParams";
    public static final String MESSAGE = "message";
    public static final String HEADER = "HEADER";
    public static final String CONTENT_IMAGE = "Content Image";
    public static final int PAGINATION_LIMIT = 20;
    public static final String EXTRA_SOCIAL_NETWORK_MODEL = "EXTRA_SOCIAL_NETWORK_MODEL";
    public static final String EXTRA_REQUEST_UPDATE = "EXTRA_REQUEST_UPDATE";

    public static final String DELIVERY_STATUS = "DELIVERY_STATUS";

    public static final String ERROR = "ERROR";
    public static final String IS_CASH_ON_DELIVERY = "is_cash_on_delivery";
    public static final String IS_CASH_ON_PICKUP = "is_cash_on_pickup";
    public static final String QUESTION_TYPE_MCQ = "MCQ";
    public static final String QUESTION_TYPE_SCQ = "SCQ";
    public static final String PROFILE_IMAGE = "profile_image";
    public static final String TYPE_START_TRIP = "startTrip";
    public static final String EXTRA_BOOKING_FILE_ID = "EXTRA_BOOKING_FILE_ID";
    static final String CURRENT_CHAT_BOOKING_ID = "CURRENT_CHAT_BOOKING_ID";
    public static final String UNIT_TYE_WEIGHT = "Weight";
    public static final String UNIT_TYE_VOLUME = "Volume";
    public static final String ESTIMATE = "estimate";
    public static final String ADDITIONAL_CHARGE = "additionalCharge";
    public static final String ADDITIONAL_FARE = "additionalFare";
    public static final String ADVANCE_PAYMENT = "advance_payment";
    public static final String CANCELLATION_CHARGES = "cancellation_charges";
    public static final String TOTAL_AMOUNT = "totalAmount";
    public static final String CURRENCY = "currency";


    public static final String MOBILE_NUMBER_VERIFICATION = "PhoneNumber";
    public static final String EMAIL_VERIFICATION = "Email";

    public static final String NOTIFICATION_ID = "notificationId";
    public static final int NOTIFICATION_DISMISS_ID = 512;


    public static final int CASH_NOT_RECEIVED = 320;
    public static final int CASH_RECEIVED = 321;
    public static final int RESULT_CODE_CASH = 322;

    public static final String NOTE = "note";
    public static final String BUY_ME_LIST = "buymelist";
    public static final String CUSTOMER_ACCEPT_BUY_ME_BOOKING = "CUSTOMER_ACCEPT_BUY_ME_BOOKING";
    public static final String BUYME_USER_ACCEPTANCE_STATUS_PENDING = "Pending";
    public static final String BUYME_USER_ACCEPTANCE_STATUS_IN_PROGRESS = "In Progress";
    public static final String BUYME_USER_ACCEPTANCE_STATUS_ACCEPTED = "Accepted";
    public static final String BUYME_USER_ACCEPTANCE_STATUS_REJECTED = "Rejected";
    public static final String BUYME_USER_ACCEPTANCE_STATUS_NEW = "New";

    private AppConstants() {
        throw new IllegalStateException("AppConstants class");
    }

    public enum MessageType {
        GENERAL,
        ERROR,
        UPDATE,
        SUCCESS,
    }

    public enum JobDutyStatusType {
        ONLINE_WITH_SHIPMENT_REQUEST,
        ONLINE_WITHOUT_SHIPMENT_REQUEST,
        OFFLINE
    }


    public static class PaymentTypeId {
        public static final int CASH_ON_DELIVERY = 1;
        public static final int PAY_VIA_CARD = 2;
        public static final int CREDIT = 3;
        public static final int CASH_ON_PICKUP = 4;

        private PaymentTypeId() {

        }
    }

    public static class PaymentMode {
        public static final String CASH = "Cash On Delivery";

        private PaymentMode() {

        }
    }

    public static class PaymentStatus {
        public static final String FAILURE = "Failure";
        public static final String SUCCESS = "Successful";

        public static final String PAYMENT_PENDING = "Payment Pending";
        public static final String PAYMENT_SUCCESS = "Payment Success";
        public static final String PAYMENT_FAILED = "Payment Failed";

        private PaymentStatus() {
        }
    }

    public static class LocationType {
        public static final String HOME = "HOME";
        public static final String WORK = " WORK";
        public static final String OTHER = "OTHER";
        public static final String FOOTER = "FOOTER";

        private LocationType() {
        }
    }


    public static class GeoCodeStatus {
        public static final String OK = "OK";

        private GeoCodeStatus() {
        }
    }

    /**
     * HttpHeaderField to set header data
     */
    public static class HttpHeaderField {
        public static final String AUTHENTICATION = "Authorization";
        public static final String CONTENT_TYPE = "Content-Type";
        public static final String ACCEPT_TYPE = "Accept";
        public static final String ACCEPT_LANGAUGE = "Accept-Language";
        public static final String ACCEPT_ENCODING = "Accept-Encoding";

        private HttpHeaderField() {
        }
    }

    public class ShipmentType {
        public static final String ACTIVE = "Active";
        public static final String PAST = "Past";

        private ShipmentType() {
        }
    }

    public class DeliveryStatus {

        public static final String STATUS_NEW_REQUEST_ID = "1000";
        public static final String STATUS_REGRET_STATUS_ID = "1100";
        public static final String STATUS_CANCELED_ID = "1200";
        public static final String STATUS_CASH_COLLECT_FAIL_ID = "1201";
        public static final String STATUS_ASSIGNED_ID = "1300";
        public static final String STATUS_REJECTED_ID = "1310";
        public static final String STATUS_IN_PROGRESS_ID = "1400";
        public static final String STATUS_ACCEPTED_ID = "1400";
        public static final String STATUS_WAITING_FOR_CUSTOMER_ACCEPTANCE_ID = "1410";
        public static final String STATUS_CUSTOMER_ACCEPTED_ID = "1420";
        public static final String STATUS_AGENT_ON_WAY_ID = "1500";
        public static final String STATUS_EMERGENCY_ID = "1600";
        public static final String STATUS_ARRIVED_ID = "1700";
        public static final String STATUS_NO_SHOW_ID = "1750";
        public static final String STATUS_STARTED_ID = "1800";
        public static final String STATUS_REACHED_SECOND_DESTINATION_ID = "1900";
        public static final String STATUS_RETURN_ID = "2000";
        public static final String STATUS_DELIVERED_ID = "2100";
        public static final String STATUS_COMPLETED_ID = "2200";


        private DeliveryStatus() {
        }
    }

    public class Constant {
        public static final String SMALL_IMAGE = "_small";
        public static final String MARKER_IMAGE = "_marker";
        public static final String CATEGORY_ID = "1";
        public static final String SERVICE_ID_PACKAGE = "1";
        public static final String SERVICE_ID_PACKAGE_ROUNDTRIP = "306882756326540992";
        public static final String SERVICE_ID_BUYME = "256127675781163568";
        public static final String SERVICE_ID_BUYME_ROUNDTRIP = "306885313443901138";
        public static final String SERVICE_RESTAURANT = "2";
        public static final int CONSTANT_GLIDE_TIMEOUT = 120000;
        public static final String ERROR_FACEBOOK_USER_NOT_REGISTER = "users.facebookUserNotRegistered";
        public static final String ERROR_GOOGLE_USER_NOT_REGISTER = "users.googleUserNotRegistered";
        public static final String PACKAGE_ID = "2";

        public static final String PAY_VIA_CARD = "2";
        public static final String CASH_ON_DELIVERY = "1";

        private Constant() {
        }
    }


}
