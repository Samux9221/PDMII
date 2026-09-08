package com.example.projetopdmii;

import static java.lang.String.format;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Tela02 extends AppCompatActivity implements MediaPlayer.OnCompletionListener, SeekBar.OnSeekBarChangeListener, Runnable, View.OnClickListener {
    private Toolbar toolbar;
    private ArrayList<Playlist> lista;
    private CardView card1, card2, card3, card4, card5;
    private TextView textoMusicaSelecionada, tempoAtual, tempoRestante;
    private int musica, indiceLista; //esse sera o endereço do arquivo .mp3 que conseguimos descobrir pela R.raw.m1
    private MediaPlayer mediaPlayer; //é uma variavel da classe media player e que tem o objetivo de reproduzir | OBJETO QUE REPRODUZ A MÚSICA
    private SeekBar seekBar;
    private Handler handler;
    private ImageView imgPreview, imgNext;

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

        //Atribuindo a toolbar o "poder" de ser uma actionBar | poder de ter botão de up, por exemplo - atribuo ações.
        setSupportActionBar(toolbar);

        //criar o botão de voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);

        handler = new Handler();

        imgPreview = findViewById(R.id.imageView);
        imgNext = findViewById(R.id.imageView2);

        imgPreview.setOnClickListener(this);
        imgNext.setOnClickListener(this);

        lista = new ArrayList<Playlist>();
        lista.add(new Playlist("Forró do Farol", R.raw.forrodofarol_quincasmoreira)); //criando e passando como parametro um objeto da clesse Playlist
        lista.add(new Playlist("Música teste", R.raw.m1));
        lista.add(new Playlist("Música teste2", R.raw.m2));
        lista.add(new Playlist("Música teste3", R.raw.m3));
        lista.add(new Playlist("Música teste4", R.raw.m4));

        card1 = findViewById(R.id.card1);
        card1.setOnClickListener(this);

        card2 = findViewById(R.id.card2);
        card2.setOnClickListener(this);

        card3 = findViewById(R.id.card3);
        card3.setOnClickListener(this);

        card4 = findViewById(R.id.card4);
        card4.setOnClickListener(this);

        card5 = findViewById(R.id.card5);
        card5.setOnClickListener(this);

        tempoAtual = findViewById(R.id.textView);
        tempoRestante = findViewById(R.id.textView2);

        textoMusicaSelecionada = findViewById(R.id.musicSelec);


        musica = R.raw.forrodofarol_quincasmoreira; //deixa uma musica ja determinada
        textoMusicaSelecionada.setText("Música selecionada: Forró do Farol");



    }

    //recebe um tempo em milisegundos e retorna uma string já estilizada
    public String formatarTempo(int tempo){
        int segundos = tempo / 1000; //transformando de milisegundos para segundos
        int minutos = segundos / 60;
        segundos = segundos % 60;

        String tempoFormatado = String.format("%02d:%02d", minutos, segundos); //2 - seignifica dois digitos; 0 - quando não tiver nada, coloca 0

        return tempoFormatado;
    }

    //metodo que trata de todos os elementos que estao na nossa toolbar (parte de cima)
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        //home = é o id que vem por padrão para a setinha Up da toolbar
        if(id == android.R.id.home){
            finish();
            //se for o botão up que foi clicado, vamos autodestruir esse Activity para a Activity 1 ser a principal
        }
        if(id == R.id.id001){
            //mediaPlayer = objeto que faz executar o MP3
            play();
        }

        if(id == R.id.id003){
            stop();
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

    //METODO QUE É CHAMADO AUTOMATICAMENTE QUAND A MUSICA ACABA
    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        //quando acabar a musica

        handler.removeCallbacks(this); //essa linha vai remover qualquer chamda de 1segundo que tver agendada, ja que a musica acabou
        mediaPlayer.release(); //desocupando memória
        this.mediaPlayer = null;
        seekBar.setProgress(0); //voltando a bolinha da seekbar para o inicio

        indiceLista++; //ja que a musica acabou, pulamos para a proxima

        if(indiceLista >= lista.size()){
            indiceLista = 0;
        }
        textoMusicaSelecionada.setText("Música selecionada: " + lista.get(indiceLista).getNome());
        stop();
        play(); //esse play ja vai pegar o indiceLista com seu valor atualizado

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    //metodo quando tiramos o dedo da seekbar
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //vamos fazer o controle da bolinha para mudar o tempo da musica

        if(mediaPlayer != null){
            mediaPlayer.seekTo(seekBar.getProgress()); //atualiza a seekbar de acordo com o progresso
        }
    }

    //medoto do handler
    @Override
    public void run() {
        if(mediaPlayer != null){
            int tempoTempoAtual = mediaPlayer.getCurrentPosition(); //retorno em milisegundos
            int duracao = mediaPlayer.getDuration();
            int tempoTempoRestante = duracao - tempoTempoAtual;

            tempoAtual.setText(formatarTempo(tempoTempoAtual));
            tempoRestante.setText("-"+formatarTempo(tempoTempoRestante));

            seekBar.setProgress(mediaPlayer.getCurrentPosition()); //lincando a musica na seekbar que adicionamos, pegando a posicao da musica e atualizando a seekbar
            handler.postDelayed(this, 1000);
        }
    }

    @Override
    public void onClick(View view) {
        if(view == card1){
            indiceLista = 0; //primeiro da lista de objetos Playlist que temos
            textoMusicaSelecionada.setText("Música selecionada: " + lista.get(indiceLista).getNome());
            musica = lista.get(indiceLista).getMusica();
        }
        if(view == card2){
            indiceLista = 1;
            textoMusicaSelecionada.setText("Música selecionada: " + lista.get(indiceLista).getNome());
            musica = lista.get(indiceLista).getMusica();
        }
        if(view == card3){
            indiceLista = 2;
            textoMusicaSelecionada.setText("Música selecionada: " + lista.get(indiceLista).getNome());
            musica = lista.get(indiceLista).getMusica();
        }
        if(view == card4){
            indiceLista = 3;
            textoMusicaSelecionada.setText("Música selecionada: " + lista.get(indiceLista).getNome());
            musica = lista.get(indiceLista).getMusica();
        }
        if(view == card5){
            indiceLista = 4;
            textoMusicaSelecionada.setText("Música selecionada: " + lista.get(indiceLista).getNome());
            musica = lista.get(indiceLista).getMusica();
        }

        //Previews e Next
        if(view == imgPreview){
            indiceLista--;

            if(indiceLista < 0){
                indiceLista = lista.size()-1; //lista.size() retorna a quantidade de elementos mesmo
            }
            textoMusicaSelecionada.setText("Música selecionada: " + lista.get(indiceLista).getNome());
            stop();
            play(); //esse play ja vai pegar o indiceLista com seu valor atualizado
        }
        if(view == imgNext){
            indiceLista++;

            if(indiceLista >= lista.size()){
                indiceLista = 0;
            }
            textoMusicaSelecionada.setText("Música selecionada: " + lista.get(indiceLista).getNome());
            stop();
            play(); //esse play ja vai pegar o indiceLista com seu valor atualizado
        }
    }

    //metodo que chamaremos sempre que quiser tocar a musica
    public void play(){

        //atualizando a toolbar, para musica e indice respectivos (Nome de musica e sua posicao na lista)
        int listaIndiceFormatado = indiceLista + 1;
        toolbar.setTitle(lista.get(indiceLista).getNome());
        toolbar.setSubtitle(Integer.toString(listaIndiceFormatado)+" de "+ Integer.toString(lista.size()));

        if(mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(this, lista.get(indiceLista).getMusica()); //criando o mediaPlayer, já que não existia
            //textoMusicaTocando.setText("Música tocando: " + lista.get(indiceLista).getNome());
            mediaPlayer.setOnCompletionListener(this);

            seekBar.setMax(mediaPlayer.getDuration()); //o tamanho maximo da minha seekbar será a duracao da musica
            handler.post(this);

            mediaPlayer.start();
        } else if(!mediaPlayer.isPlaying()){
            //se o mediaPlayer não estiver tocando, vamos dar play
            mediaPlayer.start();

            //atualizando a seekbar
            handler.post(this);
        }
    }

    public void stop(){
        //vai parar caso exista algo, por isso a comparacao
        if(mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release(); //desocupar memoria
            mediaPlayer = null; //desfazemos aquele objeto que uma vez iniciamos
        }
    }

}
