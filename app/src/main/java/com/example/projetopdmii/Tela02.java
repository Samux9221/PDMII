package com.example.projetopdmii;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Tela02 extends AppCompatActivity implements MediaPlayer.OnCompletionListener, SeekBar.OnSeekBarChangeListener, Runnable, View.OnClickListener {
    private Toolbar toolbar;
    private boolean flag;
    private int musica; //esse sera o endereço do arquivo .mp3 que conseguimos descobrir pela R.raw.m1
    private Button btn;
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private Handler handler;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela02); //essa linha mapeia o arquivo XML
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        toolbar = findViewById(R.id.toolbar);

        //Atribuindo a toolbar o "poder" de ser uma actionBar
        setSupportActionBar(toolbar);

        //criar o botão de voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);

        handler = new Handler();

        //botão temporario
        btn = findViewById(R.id.button2);
        btn.setOnClickListener(this);
        flag = false;
        musica = R.raw.forrodofarol_quincasmoreira; //deixa uma musica ja determinada
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        //home = é o id que vem por padrão para a setinha Up da toolbar
        if(id == android.R.id.home){
            finish();
            //se for o botão up que foi clicado, vamos autodestruir esse Activity para a Activity 1 ser a principal
        }
        if(id == R.id.id001){
            //mediaPlayer = objeto que faz executar o MP3
            if(mediaPlayer == null){
                mediaPlayer = MediaPlayer.create(this, musica); //criando o mediaPlayer, já que não existia
                mediaPlayer.setOnCompletionListener(this);

                seekBar.setMax(mediaPlayer.getDuration()); //o tamanho maximo da minha seekbar será a duracao da musica
                handler.post(this);

                mediaPlayer.start();
            } else if(!mediaPlayer.isPlaying()){
                //se o mediaPlayer não estiver tocando, vamos dar play
                mediaPlayer.start();
            }

        }

        if(id == R.id.id003){
            if(mediaPlayer != null && mediaPlayer.isPlaying()){
                mediaPlayer.stop();
                mediaPlayer.release(); //desocupar memoria
                mediaPlayer = null; //desfazemos aquele objeto que uma vez iniciamos
                //mediaPlayer.start();
            }
        }

        if(id == R.id.id002){
            if(mediaPlayer != null && mediaPlayer.isPlaying()){
                mediaPlayer.pause();
            }
        }
        return false;
    }

    //esse metodo vai inflar o menu na toolbar
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu); //R classe de apoio que usamos, menu a pasta que criamos, menu o arquivo dentro da pasta menu, e depois da virgula é o menu do parametro
        return true;
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        //quando acabar a musica

        mediaPlayer.release(); //desocupando memória
        mediaPlayer = null;
        seekBar.setProgress(0); //voltando a bolinha da seekbar para o inicio


    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //vamos fazer o controle da bolinha para mudar o tempo da musica

        if(mediaPlayer != null){
            mediaPlayer.seekTo(seekBar.getProgress());
        }
    }

    //medoto do handler
    @Override
    public void run() {
        if(mediaPlayer != null){
            seekBar.setProgress(mediaPlayer.getCurrentPosition()); //lincando a musica na seekbar que adicionamos, pegando a posicao da musica e atualizando a seekbar
            handler.postDelayed(this, 1000);
        }
    }

    @Override
    public void onClick(View view) {
        if(view == btn){
            if(!flag){
                musica = R.raw.forrodofarol_quincasmoreira;
                flag = true;
            }else {
                musica = R.raw.m1;
                flag = false;
            }
        }
    }
}