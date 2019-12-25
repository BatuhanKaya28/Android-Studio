package com.example.ktbilgiyarisi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class OrtaActivity extends AppCompatActivity {

    DataHelper dataHelper;
    TextView sorularr,puann,isim_oyun,gecsayi;
    ImageButton dogrug,yanlisg,anasayfag;

    RelativeLayout gec;
    int gecc;

    Random r = new Random();
    int n;
    int points = 0;
    int SKIP_NUMBER=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orta);

        dataHelper = new DataHelper(this);
        gecsayi = (TextView) findViewById(R.id.gecsayi);
        sorularr = (TextView) findViewById(R.id.sorular);
        isim_oyun = (TextView) findViewById(R.id.isim_oyun);
        puann = (TextView) findViewById(R.id.puan);
        dogrug = (ImageButton) findViewById(R.id.dogrug);
        yanlisg = (ImageButton) findViewById(R.id.yanlisg);
        anasayfag = (ImageButton) findViewById(R.id.anasayfayagit);
        gec = (RelativeLayout) findViewById(R.id.gec);

        anasayfag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrtaActivity.this, MainActivity.class));
                finish();
            }
        });

        gecsayi.setText(""+dataHelper.receiveDataInt("Geç",SKIP_NUMBER));
        isim_oyun.setText(dataHelper.receiveDataString("İsim","Kullanici"));

        final String[] arrayQ = {getString(R.string.o1),
                getString(R.string.o2),
                getString(R.string.o3),
                getString(R.string.o4),
                getString(R.string.o5),
                getString(R.string.o6),};
        final Boolean[] arrayA = {true,true,false,false,false,true};

        final ArrayList<String> sorular = new ArrayList<String>(Arrays.asList(arrayQ));
        final ArrayList<Boolean>cevaplar = new ArrayList<Boolean>(Arrays.asList(arrayA));

        n = r.nextInt(sorular.size());
        sorularr.setText(sorular.get(n)); // Soruların Karışık Olmasını Sağlar

        gec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gecsayi.setText(""+dataHelper.receiveDataInt("Geç",SKIP_NUMBER));
                gecc = dataHelper.receiveDataInt("Geç",SKIP_NUMBER);
                if(dataHelper.receiveDataInt("Geç",SKIP_NUMBER) == 0){
                    Toast.makeText(OrtaActivity.this,"0 Geçme Hakkınız Var!",Toast.LENGTH_SHORT).show();
                } else{
                    gecc--;
                    sorular.remove(n);
                    cevaplar.remove(n);
                    if(sorular.size() == 0){
                        result();
                    }else{
                        n = r.nextInt(sorular.size());
                        sorularr.setText(sorular.get(n));
                        dataHelper.saveDataInt("Geç",gecc);

                    }
                }
            }
        });

        dogrug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cevaplar.get(n))
                {
                    points++;
                    sorular.remove(n);
                    cevaplar.remove(n);
                    puann.setText("Skor: "+points);
                    if(sorular.size() == 0)
                    {
                        result();
                    }
                    else {
                        n = r.nextInt(sorular.size());
                        sorularr.setText(sorular.get(n));

                    }
                } else {
                    result();
                }
            }
        });

        yanlisg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!cevaplar.get(n))
                {
                    points++;
                    sorular.remove(n);
                    cevaplar.remove(n);
                    puann.setText("Skor: "+points);
                    if(sorular.size() == 0)
                    {
                        result();
                    }
                    else {
                        n = r.nextInt(sorular.size());
                        sorularr.setText(sorular.get(n));

                    }
                } else {
                    result();
                }
            }
        });

    }

    private void result() {
        dataHelper.saveDataInt("PUAN Orta",points);
        startActivity(new Intent(OrtaActivity.this,OrtaResultActivity.class));
        finish();
    }
}
