package com.example.joker.summary.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.joker.summary.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpActivity extends Activity {
    TextView tvMessage;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcp);

        tvMessage = (TextView) findViewById(R.id.tvMessage);
        btnSend = (Button) findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        startTcpClient();
                    }
                }).start();
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                startTcpServer();
            }
        }).start();

    }

    private void startTcpServer(){
        try {
            Boolean endFlag = false;
            ServerSocket ss = new ServerSocket(12345);
            while (!endFlag) {
                // 等待客户端连接
                Socket s = ss.accept();
                BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                //注意第二个参数据为true将会自动flush，否则需要需要手动操作output.flush()
                PrintWriter output = new PrintWriter(s.getOutputStream(),true);
                String message = input.readLine();
                Log.d("Tcp Demo", "message from Client:" + message);
                output.println("message received!");
                //output.flush();
                if("shutDown".equals(message)){
                    endFlag=true;
                }
                s.close();
            }
            ss.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startTcpClient(){
        try {
            Socket s = new Socket("localhost", 12345);
            // outgoing stream redirect to socket
            OutputStream out = s.getOutputStream();
            // 注意第二个参数据为true将会自动flush，否则需要需要手动操作out.flush()
            PrintWriter output = new PrintWriter(out, true);
            output.println("Hello IdeasAndroid!");
            BufferedReader input = new BufferedReader(new InputStreamReader(s
                    .getInputStream()));
            // read line(s)
            String message = input.readLine();
            Log.d("Tcp Demo", "message From Server:" + message);
            s.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
