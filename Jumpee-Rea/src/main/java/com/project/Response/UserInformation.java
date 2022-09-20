package com.project.Response;

public class UserInformation {
    public static String userUniqueEmail;
    //public static String userUniqueAddress;
    public static Long userId;


    public static String getUserUniqueEmail() {
        return userUniqueEmail;
    }

    public static void setUserGlobalEmail(String userUniqueEmail,String userUniqueAddress) {
        UserInformation.userUniqueEmail = userUniqueEmail;
        
    }
}
