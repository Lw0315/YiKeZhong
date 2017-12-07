package com.example.asus.yikezhong.model;

import java.io.File;

/**
 * Created by asus on 2017/12/1.
 */

public interface IShiPinModel {
    void ShiPin(String uid, File videoFile,File coverFile, String videoDesc, String latitude,String longitude,ShiPinCallBack shiPinCallBack);
}


