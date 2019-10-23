package com.ssm.tpssystem.utils;

public class Transform {
    public static String versionToStatus(Integer version,Integer reject_code){
        String status;
        if(version == 1)
            status = "REQUSTED";
        else if (version == 2)
            status = "PENDING";
        else if (version == 3)
            status = "PROCESSED";
        else if (version == 4){
            if(reject_code == null)
                status = "ACCEPTED";
            else
                status = "REJECTED";
        }
        else
            status = "NO VERSION";
        return status;
    }
}
