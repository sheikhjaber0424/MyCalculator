package com.example.mycalculator;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText op1,op2;
    private TextView Result;
    private Button ADD,SUB,MUL,DIV;
    private Button Adv_mode;
    private Button History;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;



    DatabaseReference databaseReference;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        op1 = (EditText) findViewById(R.id.editText);
        op2 = (EditText) findViewById(R.id.editText2);
        Result = (TextView) findViewById(R.id.textView);

        ADD = (Button) findViewById(R.id.button);
        SUB = (Button) findViewById(R.id.button2);
        MUL = (Button) findViewById(R.id.button3);
        DIV = (Button) findViewById(R.id.button4);
        History = (Button) findViewById(R.id.button5);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        Adv_mode = findViewById(R.id.button7);



        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();

        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake(int count) {


                Intent i = new Intent(MainActivity.this,Activity2.class);
                startActivity(i);

                Toast.makeText(MainActivity.this, "Shaked!!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onShake() {

            }
        });









        ADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(op1.getText().toString());
                int b = Integer.parseInt(op2.getText().toString());

                int sum = a+b;
                String s =sum + "";
                Result.setText(s);


            }
        });

        SUB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(op1.getText().toString());
                int b = Integer.parseInt(op2.getText().toString());
                int sub = a - b ;
                String s = sub + "";
                Result.setText(s);


            }

        });


        MUL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double a = Integer.parseInt(op1.getText().toString());
                double b = Integer.parseInt(op2.getText().toString());
                double mul = a * b ;
                String s = mul + "";
                Result.setText(s);


            }

        });



        DIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double a = Integer.parseInt(op1.getText().toString());
                double b = Integer.parseInt(op2.getText().toString());
                double div = a / b ;
                String s = div + "";
                Result.setText(s);


            }

        });

        Adv_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Activity2.class);
                startActivity(i);
            }
        });



    }


    @Override
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
