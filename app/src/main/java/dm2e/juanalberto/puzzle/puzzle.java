package dm2e.juanalberto.puzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class puzzle extends AppCompatActivity {

    TextView nom;
    TextView result;
    ImageView imagen1;
    ImageView imagen2;
    ImageView imagen3;
    ImageView imagen4;
    ImageView imagen5;
    ImageView imagen6;
    ImageView imagen7;
    ImageView imagen8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);
        nom = findViewById(R.id.textoNombre);
        Intent inicio = getIntent();
        String nombre = inicio.getStringExtra("nombre");
        nom.setText(getString(R.string.ordena_el_puzzle) + nombre);
        ImageView[] imagenes = {
                imagen1 = findViewById(R.id.imagen1),
        imagen2 = findViewById(R.id.imagen2),
        imagen3 = findViewById(R.id.imagen3),
        imagen4 = findViewById(R.id.imagen4),
        imagen5 = findViewById(R.id.imagen5),
        imagen6 = findViewById(R.id.imagen6),
        imagen7 = findViewById(R.id.imagen7),
        imagen8 = findViewById(R.id.imagen8)
        };
        List<Integer> listaNumeros = new ArrayList<>();
        Random rd = new Random();
        int numero = 0;
        for(ImageView imagen:imagenes){
            do{
                numero = rd.nextInt(8);
                if(!listaNumeros.contains(numero)){
                    listaNumeros.add(numero);
                    switch (numero){
                        case 1:
                            Drawable imagen = Drawable.createFromPath("@drawable/imagen1");
                            imagen.setImageDrawable(imagen);
                    }
                }
            }while(!listaNumeros.contains(numero));

        }

    }
}