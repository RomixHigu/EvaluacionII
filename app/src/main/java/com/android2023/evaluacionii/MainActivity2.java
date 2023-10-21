package com.android2023.evaluacionii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    EditText lat1,lng1,lat2,lng2,lat3,lng3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent2 = getIntent();

        lat1 = findViewById(R.id.txtLat1);
        lng1 = findViewById(R.id.txtLng1);
        lat2 = findViewById(R.id.txtLat2);
        lng2 = findViewById(R.id.txtLng2);
        lat3 = findViewById(R.id.txtLat3);
        lng3 = findViewById(R.id.txtLng3);

        ImageView back = findViewById(R.id.imgBack);
        ImageView close = findViewById(R.id.imgClose2);

        Button aceptar = findViewById(R.id.btnAceptar);

        TextView mensaje = findViewById(R.id.txtMensaje);

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String latDir1 = lat1.getText().toString().trim();
                String lngDir1 = lng1.getText().toString().trim();
                String latDir2 = lat2.getText().toString().trim();
                String lngDir2 = lng2.getText().toString().trim();
                String latDir3 = lat3.getText().toString().trim();
                String lngDir3 = lng3.getText().toString().trim();

                Intent intent3 = new Intent(MainActivity2.this,MainActivity3.class);
                if(!latDir1.isEmpty() && !lngDir1.isEmpty()){
                    if(!latDir2.isEmpty() && !lngDir2.isEmpty()){
                        if(!latDir3.isEmpty() && !lngDir3.isEmpty()){
                            double lati3 = Double.parseDouble(latDir3);
                            double lngi3 = Double.parseDouble(lngDir3);
                            intent3.putExtra("Latitud 3", lati3);
                            intent3.putExtra("Longitud 3", lngi3);
                        }
                        double lati2 = Double.parseDouble(latDir2);
                        double lngi2 = Double.parseDouble(lngDir2);
                        intent3.putExtra("Latitud 2", lati2);
                        intent3.putExtra("Longitud 2", lngi2);
                    }
                    double lati1 = Double.parseDouble(latDir1);
                    double lngi1 = Double.parseDouble(lngDir1);
                    intent3.putExtra("Latitud 1", lati1);
                    intent3.putExtra("Longitud 1", lngi1);

                    startActivity(intent3);
                }else{
                    Toast.makeText(getApplicationContext(), "Ingresa al menos una dirección", Toast.LENGTH_SHORT).show();

                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}