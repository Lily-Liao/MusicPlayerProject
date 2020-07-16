package com.example.a1051647_final_project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private Button btnStop,btnPlay,btnPause,btnEnd;
    private MediaPlayer mediaPlayer;
    private TextView txtShow,txtShow2,txtShow3;
    private ImageButton  btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8;
    int[] songfile=new int[]{R.raw.fear,R.raw.dont_wanna_cry,R.raw.thanks,R.raw.ohmy,
    R.raw.home,R.raw.universe,R.raw.singforyou,R.raw.forlife};
    String[] songname=new String[]{"Fear","Don't Wanna Cry","Thanks","Oh!My","Home","Universe","Sing For You","For Life"};

    private Boolean falgPause=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtShow=(TextView)findViewById(R.id.txtShow);
        txtShow2=(TextView)findViewById(R.id.txtShow2);
        txtShow3=(TextView)findViewById(R.id.txtShow3);
        //btnFront=(Button)findViewById(R.id.btnFront);
        btnStop=(Button)findViewById(R.id.btnStop);
        btnPlay=(Button)findViewById(R.id.btnPlay);
        btnPause=(Button)findViewById(R.id.btnPause);
        //btnNext=(Button)findViewById(R.id.btnNext);
        btnEnd=(Button)findViewById(R.id.btnEnd);

        btn1=(ImageButton)findViewById(R.id.btn1);
        btn2=(ImageButton)findViewById(R.id.btn2);
        btn3=(ImageButton)findViewById(R.id.btn3);
        btn4=(ImageButton)findViewById(R.id.btn4);
        btn5=(ImageButton)findViewById(R.id.btn5);
        btn6=(ImageButton)findViewById(R.id.btn6);
        btn7=(ImageButton)findViewById(R.id.btn7);
        btn8=(ImageButton)findViewById(R.id.btn8);

        Typeface tf=Typeface.createFromAsset(getAssets(),"materialdesignicons-webfont.ttf");

        //btnFront.setTypeface(tf);
        btnStop.setTypeface(tf);
        btnPlay.setTypeface(tf);
        btnPause.setTypeface(tf);
        //btnNext.setTypeface(tf);
        btnEnd.setTypeface(tf);
        mediaPlayer=new MediaPlayer();

        btn1.setOnClickListener(Listener);
        btn2.setOnClickListener(Listener);
        btn3.setOnClickListener(Listener);
        btn4.setOnClickListener(Listener);
        btn5.setOnClickListener(Listener);
        btn6.setOnClickListener(Listener);
        btn7.setOnClickListener(Listener);
        btn8.setOnClickListener(Listener);

        btnStop.setOnClickListener(myListener);
        btnPlay.setOnClickListener(myListener);
        btnPause.setOnClickListener(myListener);
        btnEnd.setOnClickListener(myListener);

    }

    private ImageButton.OnClickListener Listener=new ImageButton.OnClickListener(){
      public void onClick(View v){
          switch (v.getId()){
              case R.id.btn1:
                  playSong(0);
                  txtShow2.setText("0");
                  txtShow3.setText("歌曲信息"+"\n"+"歌手：Seventeen"+"\n"+"專輯名稱：An Ode"+"\n"+"發行年代：2019");
                  break;
              case R.id.btn2:
                  playSong(1);
                  txtShow2.setText("1");
                  txtShow3.setText("歌曲信息"+"\n"+"歌手：Seventeen"+"\n"+"專輯名稱：A|1"+"\n"+"發行年代：2017");
                  break;
              case R.id.btn3:
                  playSong(2);
                  txtShow2.setText("2");
                  txtShow3.setText("歌曲信息"+"\n"+"歌手：Seventeen"+"\n"+"專輯名稱：Director's Cut"+"\n"+"發行年代：2018");
                  break;
              case R.id.btn4:
                  playSong(3);
                  txtShow2.setText("3");
                  txtShow3.setText("歌曲信息"+"\n"+"歌手：Seventeen"+"\n"+"專輯名稱：You Make Me Day"+"\n"+"發行年代：2018");
                  break;
              case R.id.btn5:
                  playSong(4);
                  txtShow2.setText("4");
                  txtShow3.setText("歌曲信息"+"\n"+"歌手：Seventeen"+"\n"+"專輯名稱：You Make My Dawn"+"\n"+"發行年代：2019");
                  break;
              case R.id.btn6:
                  playSong(5);
                  txtShow2.setText("5");
                  txtShow3.setText("歌曲信息"+"\n"+"歌手：EXO"+"\n"+"專輯名稱：Universe"+"\n"+"發行年代：2017");
                  break;
              case R.id.btn7:
                  playSong(6);
                  txtShow2.setText("6");
                  txtShow3.setText("歌曲信息"+"\n"+"歌手：EXO"+"\n"+"專輯名稱：Sing For You"+"\n"+"發行年代：2015");
                  break;
              case R.id.btn8:
                  playSong(7);
                  txtShow2.setText("7");
                  txtShow3.setText("歌曲信息"+"\n"+"歌手：EXO"+"\n"+"專輯名稱：For Life"+"\n"+"發行年代：2016");
                  break;
          }
      }
    };
    private void playSong(int song){

        mediaPlayer.reset();
        mediaPlayer=MediaPlayer.create(this,songfile[song]);
        mediaPlayer.start();
        txtShow.setText("歌名："+songname[song]);
        falgPause=false;
    };

    private Button.OnClickListener myListener=new Button.OnClickListener(){
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.btnStop:
                    Toast toast =Toast.makeText(SecondActivity.this,"結束音樂",Toast.LENGTH_LONG);
                    toast.show();
                    if(mediaPlayer.isPlaying()){
                        mediaPlayer.reset();
                    }
                    break;
                case R.id.btnPlay:
                    if(falgPause==true){
                        mediaPlayer.start();
                        falgPause=false;
                        Toast toast1 =Toast.makeText(SecondActivity.this,"接續播放",Toast.LENGTH_LONG);
                        toast1.show();
                    }else{
                        Toast toast2 =Toast.makeText(SecondActivity.this,"重頭播放",Toast.LENGTH_LONG);
                        toast2.show();
                        int a= Integer.parseInt(txtShow2.getText().toString());
                        playSong(a);
                    }
                    break;
                case R.id.btnPause:
                    Toast toast3 =Toast.makeText(SecondActivity.this,"暫停",Toast.LENGTH_LONG);
                    toast3.show();
                    mediaPlayer.pause();
                    falgPause=true;
                    break;
                case R.id.btnEnd:
                    Toast toast4 =Toast.makeText(SecondActivity.this,"結束音樂播放程式",Toast.LENGTH_LONG);
                    toast4.show();
                    mediaPlayer.release();
                    finish();
                    break;
            }
        }
    };

}
