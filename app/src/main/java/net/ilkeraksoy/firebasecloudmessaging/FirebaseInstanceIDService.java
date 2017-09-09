package net.ilkeraksoy.firebasecloudmessaging;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.jaredrummler.android.device.DeviceName;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {

        String token = FirebaseInstanceId.getInstance().getToken();
        String deviceName= DeviceName.getDeviceName();
        sendRegistrationToServer(deviceName, token);
        Log.d("Refreshed token", token);
    }

    private void sendRegistrationToServer(String deviceName, String token) {

        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("Device", deviceName)
                .add("Token", token)
                .build();

        Request request = new Request.Builder()
                .url("http://localhost/register.php")
                .post(body)
                .build();

        try {

            client.newCall(request).execute();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}