package com.example.projetopdmii;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Activity3 extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnCompletionListener {

    private ArrayList<Playlist> listaMusica;
    private ViewPager2 viewPager;
    private ArrayList<Slide> lista;
    private Button button;
    private MediaPlayer mediaPlayer;
    private DrawerLayout drawer;
    private NavigationView navigation;
    private ActionBarDrawerToggle action;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.linearPrincipal), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //musica de fundo
        listaMusica = new ArrayList<Playlist>();
        listaMusica.add(new Playlist("Música teste2", R.raw.forrodofarol_quincasmoreira));
        //

        mediaPlayer = MediaPlayer.create(this, listaMusica.get(0).getMusica());
        mediaPlayer.start();

        viewPager = findViewById(R.id.viewPager);

        button = findViewById(R.id.button2);
        button.setOnClickListener(this);

        toolbar = findViewById(R.id.toolbar);

        //Atribuindo a toolbar o "poder" de ser uma actionBar | poder de ter botão de up, por exemplo - atribuo ações.
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.iddrawer);
        navigation = findViewById(R.id.navigation);

        action = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);

        //tornando ele sensivel a eventos
        drawer.addDrawerListener(action);

        //sincronizando a action
        action.syncState();

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.idhome){
                    drawer.closeDrawers();
                    startActivity(new Intent(Activity3.this, TelaHome.class));
                    return true;
                };
                if(menuItem.getItemId() == R.id.idtela03){
                    drawer.closeDrawers();
                    return false;
                };
                if(menuItem.getItemId() == R.id.idmusica){
                    drawer.closeDrawers();
                    startActivity(new Intent(Activity3.this, Tela02.class));
                    return true;
                };

                return false;
            }
        });

        //instanciar a lista, para que ela exista na memoria
        lista = new ArrayList<Slide>();
        lista.add(new Slide("Slide 1", R.drawable.dolphin, "O verdadeiro divisor de águas da marca no Brasil. É um hatch compacto/médio super tecnológico, focado em mobilidade urbana eficiente, com excelente espaço interno e ótimo custo-benefício para quem quer entrar no mundo elétrico.", "BYD Dolphin"));
        lista.add(new Slide("Slide 2", R.drawable.dolphinmini, "O modelo de entrada da marca. Trata-se de um subcompacto urbano ágil, ideal para o dia a dia nas grandes cidades, unindo dimensões compactas para estacionar facilmente, visual moderno e economia impressionante.", "BYD Dolphin Mini"));
        lista.add(new Slide("Slide 3", R.drawable.songplus, "Um dos SUVs híbridos mais vendidos do país. Ele combina um motor a combustão com um elétrico, oferecendo autonomia estendida sem ansiedade de recarga. É espaçoso, muito confortável e focado no público familiar.", "BYD Song Plus (DM-i)"));
        lista.add(new Slide("Slide 4", R.drawable.yuanplus, "Um SUV compacto totalmente elétrico com um design jovial e interior futurista inspirado em academias de ginástica. Oferece boa altura do solo, excelente desempenho e um pacote tecnológico robusto de segurança.", "BYD Yuan Plus"));
        lista.add(new Slide("Slide 5", R.drawable.seal, "Um sedã esportivo de alto desempenho que impressiona pela aceleração digna de supercarro e visual aerodinâmico marcante. Une luxo, sofisticação e muita potência em uma proposta 100% elétrica.", "BYD Seal"));
        lista.add(new Slide("Slide 6", R.drawable.tanbyd, "O SUV topo de linha da marca. Com tração integral (AWD) e capacidade para até 7 lugares, o Tan entrega luxo executivo, acabamento primoroso, alta performance e muita segurança para famílias grandes.", "BYD Tan EV"));

        //passando para o construtor de SlideAdapter nossa lista
        SlideAdapter adapter = new SlideAdapter(lista);
        viewPager.setAdapter(adapter);
    }


    @Override
    public void onClick(View view) {
        if(view == button){
            mediaPlayer.stop();
            mediaPlayer.release(); //desocupar memoria
            mediaPlayer = null; //desfazemos aquele objeto que uma vez iniciamos
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    //METODO QUE É CHAMADO AUTOMATICAMENTE QUAND A MUSICA ACABA
    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        mediaPlayer.release();

    }
}