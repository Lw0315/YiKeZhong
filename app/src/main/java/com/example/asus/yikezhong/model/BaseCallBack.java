package com.example.asus.yikezhong.model;

import com.google.gson.internal.$Gson$Types;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;

/**
 * Created by asus on 2017/11/29.
 */

public abstract class BaseCallBack<T> {
    public Type type;
    static Type getSuperclassTypeParameter(Class<?> subclass){
        Type superclass=subclass.getGenericSuperclass();
        if(superclass instanceof Class){
            throw new RuntimeException("Missing type parameter");
        }
        ParameterizedType parameterizedType= (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterizedType.getActualTypeArguments()[0]);
    }
    public BaseCallBack(){
        type=getSuperclassTypeParameter(getClass());
    }
    public abstract void onError(Throwable throwable);
    public abstract void onComplete();
    public abstract void onNext(ResponseBody responseBody,T t) throws IOException;
}
