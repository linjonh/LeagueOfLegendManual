package com.jaysen.leagueoflegendmanual.util;

import android.util.Log;
import android.util.LogPrinter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/22.
 */

public class StringConverterFactory extends Converter.Factory {

    public static StringConverterFactory create() {
        return new StringConverterFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        Log.i("StringConverterFactory","responseBodyConverter："+type.toString());
        if (String.class == type) {
            return new Converter<ResponseBody, String>() {
                @Override
                public String convert(ResponseBody value) throws IOException {
                    return value.string();
                }
            };
        }
        return super.responseBodyConverter(type, annotations, retrofit);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        Log.i("StringConverterFactory","requestBodyConverter："+type.toString());
        if (String.class == type) {
            return new Converter<String, RequestBody>() {
                @Override
                public RequestBody convert(String value) throws IOException {
                    return RequestBody.create(MediaType.parse("text/plain"), value);
                }
            };
        }
        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }

    @Override
    public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        Log.i("StringConverterFactory","stringConverter: "+type.toString());
        if (String.class == type) {
            return new Converter<Object, String>() {
                @Override
                public String convert(Object value) throws IOException {
                    return (String) value;
                }
            };
        }
        return super.stringConverter(type, annotations, retrofit);
    }
}
