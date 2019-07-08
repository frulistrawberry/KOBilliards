package com.kobilliards.net;

import android.text.TextUtils;

import com.kobilliards.constants.Config;
import com.kobilliards.constants.PreferenceConstant;
import com.kobilliards.utils.AppUtils;
import com.kobilliards.utils.MD5Utils;
import com.kobilliards.utils.PhoneUtils;
import com.kobilliards.utils.SPUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CommonParamsInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
            if (request.body() instanceof FormBody) {
                FormBody.Builder bodyBuilder = new FormBody.Builder();
                FormBody formBody = (FormBody) request.body();
                //把原来的参数添加到新的构造器，（因为没找到直接添加，所以就new新的）
                for (int i = 0; i < formBody.size(); i++) {
                    bodyBuilder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
                }
                formBody = bodyBuilder
                        .addEncoded("channelCode", Config.CHANNEL_CODE)
                        .addEncoded("timestamp", System.currentTimeMillis() + "")
                        .addEncoded("authToken", SPUtils.getString(PreferenceConstant.TOKEN))
                        .addEncoded("deviceInfo", PhoneUtils.getDeviceId(AppUtils.getAppContext()))
                        .addEncoded("sign",sign(formBody))
                        .build();

                request = request.newBuilder().post(formBody).build();
            }
            return chain.proceed(request);
    }

    private String sign(FormBody body){
        String channelCode = "";
        String bizContent = "";
        try {
            for (int i = 0; i < body.size(); i++) {
                if (URLDecoder.decode(body.name(i),"utf-8").equals("channelCode")) {
                    channelCode = URLDecoder.decode(body.value(i), "utf-8");
                }
                if (URLDecoder.decode(body.name(i),"utf-8").equals("bizContent")){
                    bizContent = URLDecoder.decode(body.value(i), "utf-8");
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StringBuilder sign = new StringBuilder(channelCode);
        if(!TextUtils.isEmpty(sign)){
            sign.append(bizContent);
        }
        sign.append(Config.SIGN_KEY);

        return MD5Utils.MD5(sign.toString());
    }
}
