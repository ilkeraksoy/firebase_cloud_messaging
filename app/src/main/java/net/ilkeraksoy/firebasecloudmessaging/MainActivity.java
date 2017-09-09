package net.ilkeraksoy.firebasecloudmessaging;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private TextView tv_gps;
    private Button btn_logToken;

    private String token;

    protected void onCreate(Bundle savedInstanceState) {

        FirebaseMessaging.getInstance().subscribeToTopic("test");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_gps = (TextView) findViewById(R.id.tv_gps);
        btn_logToken = (Button) findViewById(R.id.btn_logToken);
        btn_logToken.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                token = FirebaseInstanceId.getInstance().getToken();

                Log.d("Token", token);
            }
        });

        checkPlayServices();
    }

    private Dialog errorDialog;

    private boolean checkPlayServices() {

        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();

        int resultCode = googleApiAvailability.isGooglePlayServicesAvailable(this);

        if (resultCode != ConnectionResult.SUCCESS) {

            if (googleApiAvailability.isUserResolvableError(resultCode)) {

                tv_gps.setText(googleApiAvailability.getErrorString(resultCode));
            } else {

                tv_gps.setText("This device is not supported");

                finish();
            }

            return false;
        }

        tv_gps.setText("Google Play Services is available.");

        return true;
    }
}