package com.example.projetopdmii;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Runnable {

    private Handler handler;
    private int x;
    private TextView text;
    private String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        x = 0;
        handler = new Handler();
        handler.postDelayed(this, 2000);

        text = findViewById(R.id.textView3);
        string = new String("Carregando");
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, Tela02.class));
    }

    @Override
    public void run() {
        x++;

        text.setText(text.getText() + ".");

        handler.postDelayed(this, 1000);

        if(x == 5){
            startActivity(new Intent(this, Tela02.class));
            this.finish();
        }
    }
}