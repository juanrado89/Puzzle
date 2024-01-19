package dm2e.juanalberto.puzzle.BBDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UsuariosHelper extends SQLiteOpenHelper {
    private final String creaPuzzle1 =
            "CREATE TABLE puzzle_uno (\n" +
                    "    idPuzzle_uno INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    nombreUsuario TEXT,\n" +
                    "    puntuacion NUMBER\n" +
                    ");" ;
    private final String creaPuzzle2 =
            "CREATE TABLE puzzle_dos (\n" +
                    "    idPuzzle_dos INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    nombreUsuario TEXT,\n" +
                    "    puntuacion NUMBER\n" +
                    ");" ;
    public UsuariosHelper(@Nullable Context context) {
        super(context, "usuarios", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.rawQuery(creaPuzzle1,null);
        db.rawQuery(creaPuzzle2,null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS puzzle_uno;");
        db.execSQL("DROP TABLE IF EXISTS puzzle_dos;");
        onCreate(db);
    }
}
