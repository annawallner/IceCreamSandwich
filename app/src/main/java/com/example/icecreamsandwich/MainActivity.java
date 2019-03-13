package com.example.icecreamsandwich;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Instanzvariablen eingefügt
    public TextView textView;
    public TextView textView2;
    public Button button;
    public EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instanzvariablen mit den Variablen aus dem XML Dokument verbunden
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);

        //Text eingefügt
        textView.setText("Gib deine Matrikelnummer ein");
        textView2.setText("Antwort vom Server");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Button = editText.getText().toString();
            }
        });


    }

}
