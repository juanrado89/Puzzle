package dm2e.juanalberto.puzzle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class puzzle extends AppCompatActivity {

    TextView nom;
    TextView result;
    GridLayout gridLayout;
    int contador = 0;

    private ImageView imagenSeleccionada1;
    private ImageView imagenSeleccionada2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);

        nom = findViewById(R.id.textoNombre);
        result = findViewById(R.id.puntuacion);
        Intent inicio = getIntent();
        String nombre = inicio.getStringExtra("nombre");
        nom.setText(getString(R.string.ordena_el_puzzle) + nombre);

        gridLayout = findViewById(R.id.imagenes);
        ImageView[] imagenes = {
                findViewById(R.id.imagen1),
                findViewById(R.id.imagen2),
                findViewById(R.id.imagen3),
                findViewById(R.id.imagen4),
                findViewById(R.id.imagen5),
                findViewById(R.id.imagen6),
                findViewById(R.id.imagen7),
                findViewById(R.id.imagen8)
        };

        Integer[] fotos = {
                R.drawable.imagen1,
                R.drawable.imagen2,
                R.drawable.imagen3,
                R.drawable.imagen4,
                R.drawable.imagen5,
                R.drawable.imagen6,
                R.drawable.imagen7,
                R.drawable.imagen8
        };
        List<Integer> listaImagenes = Arrays.asList(fotos);
        for(int i = 0; i < imagenes.length;i++){
            Drawable drawable = ResourcesCompat.getDrawable(getResources(), listaImagenes.get(i), null);
            imagenes[i].setTag(drawable);
        }

        Collections.shuffle(listaImagenes);

        for (int i = 0; i < listaImagenes.size(); i++) {
            Drawable drawable = ResourcesCompat.getDrawable(getResources(), listaImagenes.get(i), null);
            imagenes[i].setImageDrawable(drawable);
            imagenes[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onImageClick((ImageView) view);

                }
            });
        }

        result.setText(getString(R.string.numero_de_intentos) + contador);


    }

    private void onImageClick(ImageView imageView) {

        if (imagenSeleccionada1 == null) {
            imagenSeleccionada1 = imageView;
            imageView.setBackgroundColor(Color.BLUE);
        } else {
            imagenSeleccionada2 = imageView;
            intercambiarImagenes(imagenSeleccionada1, imagenSeleccionada2);
            contador++;
            result.setText(getString(R.string.numero_de_intentos) + contador);
            imagenSeleccionada1.setBackgroundColor(Color.TRANSPARENT);
            imagenSeleccionada1 = null;
            imagenSeleccionada2 = null;
            if(verificarCoincidenciaDrawables()){
                Toast mensaje = Toast.makeText(getApplicationContext(),"Has ganado!!!", Toast.LENGTH_LONG);
                mensaje.show();
            }
        }
    }

    private void intercambiarImagenes(ImageView imagen1, ImageView imagen2) {
        Drawable tempDrawable = imagen1.getDrawable();
        imagen1.setImageDrawable(imagen2.getDrawable());
        imagen2.setImageDrawable(tempDrawable);
    }

    private boolean verificarCoincidenciaDrawables() {
        ImageView[] imagenes = {
                findViewById(R.id.imagen1),
                findViewById(R.id.imagen2),
                findViewById(R.id.imagen3),
                findViewById(R.id.imagen4),
                findViewById(R.id.imagen5),
                findViewById(R.id.imagen6),
                findViewById(R.id.imagen7),
                findViewById(R.id.imagen8)
        };

        for (ImageView imageView : imagenes) {
            Drawable drawable = imageView.getDrawable();
            Drawable comprobacion = (Drawable) imageView.getTag();

            if (drawable != null && comprobacion != null) {

                if (!drawable.getConstantState().equals(comprobacion.getConstantState())) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }
}