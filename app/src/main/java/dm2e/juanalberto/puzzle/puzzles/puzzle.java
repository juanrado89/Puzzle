package dm2e.juanalberto.puzzle.puzzles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import dm2e.juanalberto.puzzle.R;
import dm2e.juanalberto.puzzle.datos.UsuariosDatos;
import dm2e.juanalberto.puzzle.models.UsuarioPuzzle;

public class puzzle extends AppCompatActivity {

    TextView nom;
    TextView result;
    GridLayout gridLayout;
    int contador = 0;

    private ImageView imagenSeleccionada1;
    private ImageView imagenSeleccionada2;
    private static final String CONTADOR= "contador";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);

        gridLayout = findViewById(R.id.imagenes);
        result = findViewById(R.id.puntuacion);

        if (savedInstanceState != null) {
            contador = savedInstanceState.getInt(CONTADOR);
            result.setText(getString(R.string.numero_de_intentos) + contador);
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

            for (int i = 0; i < imagenes.length; i++) {
                Bitmap bitmap = savedInstanceState.getParcelable("drawable_" + i);
                imagenes[i].setImageBitmap(bitmap);

                imagenes[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onImageClick((ImageView) view);
                    }
                });
            }
            for(int i = 0; i < imagenes.length; i++){
                gridLayout.removeView(imagenes[i]);
                Drawable drawable = ResourcesCompat.getDrawable(getResources(), listaImagenes.get(i), null);
                imagenes[i].setTag(drawable);
                gridLayout.addView(imagenes[i], i);

            }

        }else{
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

        nom = findViewById(R.id.textoNombre);

        Intent inicio = getIntent();
        String nombre = inicio.getStringExtra("nombre");
        nom.setText(getString(R.string.ordena_el_puzzle) + nombre);





    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(CONTADOR, contador);

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

        for (int i = 0; i < imagenes.length; i++) {
            Drawable drawable = imagenes[i].getDrawable();
            outState.putParcelable("drawable_" + i, drawableToBitmap(drawable));
        }
    }
    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
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
                UsuariosDatos us = new UsuariosDatos(this,"puzzle_uno");
                Intent inicio = getIntent();
                String nombre = inicio.getStringExtra("nombre");
                UsuarioPuzzle usuario = new UsuarioPuzzle(0,nombre,contador);
                List<UsuarioPuzzle> comprobacion = us.getUsuarioByName(usuario);
                if(comprobacion.isEmpty()){
                    us.insertarUsuarios(nombre,contador);
                }else{
                    if(comprobacion.get(0).getPuntuacion() > contador){
                        usuario.setId(comprobacion.get(0).getId());
                        us.actualizarPuntuacion(usuario);
                    }
                }
                mensaje.show();
                Intent puzzle2 = new Intent(this, PuzzleDos.class);
                puzzle2.setAction("envio.nombre");
                puzzle2.putExtra("nombre",nombre);
                startActivity(puzzle2);
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
            Bitmap bitmap = drawableToBitmap(imageView.getDrawable());
            Bitmap comprobacion = drawableToBitmap((Drawable) imageView.getTag());

            if (bitmap != null && comprobacion != null) {
                if (!bitmap.sameAs(comprobacion)) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }
}