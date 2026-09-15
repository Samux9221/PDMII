package com.example.projetopdmii;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class Activity3 extends AppCompatActivity {

    private ViewPager2 viewPager;
    private ArrayList<Slide> lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        viewPager = findViewById(R.id.viewPager);

        //instanciar a lista, apra que ela exista na memoria
        lista = new ArrayList<Slide>();
        lista.add(new Slide("Slide 1", R.drawable.forro1, "O verdadeiro divisor de águas da marca no Brasil. É um hatch compacto/médio super tecnológico, focado em mobilidade urbana eficiente, com excelente espaço interno e ótimo custo-benefício para quem quer entrar no mundo elétrico.", "BYD Dolphin"));
        lista.add(new Slide("Slide 2", R.drawable.forro2_1, "O modelo de entrada da marca. Trata-se de um subcompacto urbano ágil, ideal para o dia a dia nas grandes cidades, unindo dimensões compactas para estacionar facilmente, visual moderno e economia impressionante.", "BYD Dolphin Mini"));
        lista.add(new Slide("Slide 3", R.drawable.contry, "Um dos SUVs híbridos mais vendidos do país. Ele combina um motor a combustão com um elétrico, oferecendo autonomia estendida sem ansiedade de recarga. É espaçoso, muito confortável e focado no público familiar.", "BYD Song Plus (DM-i)"));
        lista.add(new Slide("Slide 4", R.drawable.jazz2, "Um SUV compacto totalmente elétrico com um design jovial e interior futurista inspirado em academias de ginástica. Oferece boa altura do solo, excelente desempenho e um pacote tecnológico robusto de segurança.", "BYD Yuan Plus"));
        lista.add(new Slide("Slide 5", R.drawable.jazz2, "Um sedã esportivo de alto desempenho que impressiona pela aceleração digna de supercarro e visual aerodinâmico marcante. Une luxo, sofisticação e muita potência em uma proposta 100% elétrica.", "BYD Seal"));
        lista.add(new Slide("Slide 6", R.drawable.jazz2, "O SUV topo de linha da marca. Com tração integral (AWD) e capacidade para até 7 lugares, o Tan entrega luxo executivo, acabamento primoroso, alta performance e muita segurança para famílias grandes.", "BYD Tan EV"));

        //passando par ao construtor de SlideAdapter nossa lista
        SlideAdapter adapter = new SlideAdapter(lista);
        viewPager.setAdapter(adapter);
    }
}