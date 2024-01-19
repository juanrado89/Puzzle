package dm2e.juanalberto.puzzle.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dm2e.juanalberto.puzzle.BBDD.UsuariosHelper;
import dm2e.juanalberto.puzzle.models.UsuarioPuzzle;

public class UsuariosDatos {
    private UsuariosHelper db;
    private String nombreTabla;
    public UsuariosDatos(Context context, String nombreTabla) {
        this.nombreTabla = nombreTabla;
        db = new UsuariosHelper(context);
    }
    public void insertarUsuarios(String nombre, int puntuacion){
        SQLiteDatabase sldb = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombreUsuario",nombre);
        values.put("puntuacion",puntuacion);
        sldb.insert(nombreTabla,null,values);
        sldb.close();
    }
    public void actualizarPuntuacion(UsuarioPuzzle usuario){
        SQLiteDatabase sldb = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("puntuacion",usuario.getPuntuacion());
        String where = "id" + nombreTabla + " = ?";
        String[] argumento = {String.valueOf(usuario.getId())};
        sldb.update(nombreTabla,values,where,argumento);
    }

    public List<UsuarioPuzzle> getUsuarioByName(UsuarioPuzzle usuario){
        SQLiteDatabase sldb = db.getWritableDatabase();
        String sentencia = "select * from " + nombreTabla + " where nombreUsuario like ?;";
        String[] argumentos = {usuario.getNombreUsuario()};
        List<UsuarioPuzzle> lista = new ArrayList<>();
        Cursor cursor = sldb.rawQuery(sentencia,argumentos);
        if(!cursor.isClosed() && cursor.moveToFirst()){
            while(cursor.moveToNext()){
                lista.add(new UsuarioPuzzle(cursor.getInt(0),cursor.getString(1) ,cursor.getInt(2)));
            }
        }
        cursor.close();
        sldb.close();
        return lista;
    }
    public List<UsuarioPuzzle> getAllUsuarios(){
        SQLiteDatabase sldb = db.getWritableDatabase();
        String sentencia = "select * from " + nombreTabla + ";";
        List<UsuarioPuzzle> lista = new ArrayList<>();
        Cursor cursor = sldb.rawQuery(sentencia,null);
        if(!cursor.isClosed() && cursor.moveToFirst()){
            while(cursor.moveToNext()){
                lista.add(new UsuarioPuzzle(cursor.getInt(0),cursor.getString(1) ,cursor.getInt(2)));
            }
        }
        cursor.close();
        sldb.close();
        return lista;
    }


}
