package com.medivision.common;

public interface ResponseMessage {
    //public static final

    // HTTP Status 200
    String SUCCES = "Succes.";

    // HTTP Status 400
    String VALIDATION_FAILED = "Validation Failed.";
    String NOT_EXISTED_USER = "This user does not exist.";
    String NOT_EXISTED_BOARD = "This board does not exist";
    String NOT_EXISTED_STUDY = "This study does not exist";
    String NOT_EXISTED_REPORT = "This report does not exist";

    String SPARE_REPORT_ALREADY_EXIST = "Spare report already exist";
    String REPORT_ALREADY_FULL = "Report already exist";


    // HTTP Status 401
    String SIGN_IN_FAIL = "Login Inforam.";
    String AUTHORIZATION_FAILED = "Authorization Failed.";

    // HTTP Status 403
    String NO_PERMISSION = "Do not have Permission.";

    // HTTP Status 500
    String DATABASE_ERROR= "Database error.";
}
