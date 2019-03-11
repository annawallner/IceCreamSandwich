package com.example.icecreamsandwich;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Instanzvariablen eingefügt
    public TextView textView;
    public TextView textView2;
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instanzvariablen mit den Variablen aus dem XML Dokument verbunden
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        button = (Button) findViewById(R.id.button);
    }
}
