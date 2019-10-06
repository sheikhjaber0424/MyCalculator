package com.example.mycalculator;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

    private Button SIN,COS,TAN,COT,SQUARE,AC,ln,SQRT,LOG;
    private EditText op;
    private TextView ANS;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        op= (EditText) findViewById(R.id.editText3);
        ANS = (TextView) findViewById(R.id.textANS);

        SIN = (Button) findViewById(R.id.buttonSIN);
        COS = (Button) findViewById(R.id.buttonCOS);
        TAN = (Button) findViewById(R.id.buttonTAN);
        COT = (Button) findViewById(R.id.buttonCOT);
        SQUARE=(Button) findViewById(R.id.buttonsq);
        AC= (Button) findViewById(R.id.buttonAC);
        ln = (Button) findViewById(R.id.buttonlln);
        SQRT = (Button) findViewById(R.id.buttonsqrt);
        LOG = (Button) findViewById(R.id.buttonlog);





        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();

        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake(int count) {


                Intent i = new Intent(Activity2.this,MainActivity.class);
                startActivity(i);

                Toast.makeText(Activity2.this, "Shaked!!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onShake() {

            }
        });








        SIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double a = Double.parseDouble(op.getText().toString());
                double sine=Math.toRadians(a);
                double S=Math.sin(sine);
                String S1 = String.format("%f",S);
                ANS.setText(S1);
            }
        });

        COS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double a = Double.parseDouble(op.getText().toString());
                double c=Math.toRadians(a);

                double S=Math.cos(c);
                String S1 = String.format("%f",S);
                ANS.setText(S1);
            }
        });
        TAN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double a = Double.parseDouble(op.getText().toString());
                double t=Math.toRadians(a);

                double S=Math.tan(t);
                String S1 = String.format("%f",S);
                ANS.setText(S1);
            }
        });
        COT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double a = Double.parseDouble(op.getText().toString());
                double co=Math.toRadians(a);
                double S=(1/(Math.tan(co)));
                String S1 = String.format("%f",S);
                ANS.setText(S1);


            }
        });

        SQUARE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(op.getText().toString());
                int sum=a*a;

                String S = sum+"";
                ANS.setText(S);
            }
        });
        AC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ANS.setText(null);
                op.setText(null);

            }
        });


        ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double a = Double.parseDouble(op.getText().toString());
                double S=Math.log(a);
                String S1 = String.format("%f",S);
                ANS.setText(S1);
            }
        });

        SQRT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double a = Double.parseDouble(op.getText().toString());
                double S=Math.sqrt(a);
                String S1 = String.format("%f",S);
                ANS.setText(S1);

            }
        });

        LOG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double a = Double.parseDouble(op.getText().toString());
                double S=Math.log10(a);
                String S1 = String.format("%f",S);
                ANS.setText(S1);
            }
        });
    }


    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }
}
