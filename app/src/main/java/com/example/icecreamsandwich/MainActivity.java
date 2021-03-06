package com.example.icecreamsandwich;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Instanzvariablen eingefügt
    public TextView textView;
    public TextView serverReply;
    public Button button;
    public Button button2;
    public static EditText sendMessage;
    static String input = null;
    static String output = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        serverReply = (TextView) findViewById(R.id.textView2);
        button = (Button) findViewById(R.id.button);
        button = (Button) findViewById(R.id.button2);
        sendMessage = (EditText) findViewById(R.id.editText);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button:
                try {
                    sendMessageToServer(sendMessage.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

        }
        switch (v.getId()) {

            case R.id.button2:
                try {
                    calculate();
                    serverReply.setText(output);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

        }
    }


    private void sendMessageToServer(final String msg) throws IOException {

        final Handler handler = new Handler();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    Socket s = new Socket("se2-isys.aau.at", 53212);

                    OutputStream out = s.getOutputStream();

                    PrintWriter output = new PrintWriter(out);

                    output.println(msg);
                    output.flush();
                    BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    final String st = input.readLine();

                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            if (st.trim().length() != 0)
                                serverReply.setText(st);
                        }
                    });

                    output.close();
                    out.close();
                    s.close();
                } catch (Exception e) {
                    serverReply.setText(e.getMessage());
                }
            }
        });

        thread.start();
    }

    private String calculate() throws IOException {
        input = sendMessage.getText().toString();
        int counter = 0;
        char[] splitted = input.toCharArray();
        char[] case1 = new char[input.length()];
        char[] case0 = new char[input.length()];


        // Hallo
        for (int i = 0; i < splitted.length; i++) {
            counter++;

            switch (counter % 2) {
                case 0:
                    if (splitted[i] == '0'){
                        case1[i] = 'j';
                    }else {
                        int castToInt = (int) splitted[i] + 48;
                        char assci = (char) castToInt;
                        case1[i] = assci;
                    }
                    break;

                case 1:
                        case0[i] = splitted[i];
                    break;
            }
        }
        output = Character.toString(case0[0]);

        for (int i = 1; i < input.length(); i++) {
            if (i % 2 == 0) {
                output = output + case0[i];
            } else {
                output = output + case1[i];
            }
        }

        return output;
    }
}
