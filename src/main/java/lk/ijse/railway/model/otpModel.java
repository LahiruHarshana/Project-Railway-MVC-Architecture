package lk.ijse.railway.model;

import lk.ijse.railway.dto.OTP;

public class otpModel {

    static OTP ootp;

    public static OTP setValues(OTP otp) {
        ootp=otp;

        return ootp;

    }
    public static OTP getValue(){
        return ootp;
    }
}
