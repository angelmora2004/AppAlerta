package com.example.fapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtNum1;
    private EditText edtNum2;
    private TextView txtResultado;
    private Button btnSumar, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtNum1 = (EditText) findViewById(R.id.edtNum1);
        edtNum2 = (EditText) findViewById(R.id.estNum2);
        txtResultado = (TextView) findViewById(R.id.txtResultado);
        btnSumar = (Button) findViewById(R.id.btnSumar);
        btnSalir = (Button) findViewById(R.id.btnSalir);
        btnSumar.setOnClickListener(this);
        btnSalir.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSumar:
                int numero1 = Integer.parseInt(edtNum1.getText().toString());
                int numero2 = Integer.parseInt(edtNum2.getText().toString());
                int suma = numero1 + numero2;
                txtResultado.setText(String.valueOf(suma));
                break;
            case R.id.btnSalir:
                finish();
                break;
        }
    }
}