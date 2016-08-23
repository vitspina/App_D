package boonedev.apptcc;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vitor.oliveira on 13/07/2016.
 */

public class clsExercicios extends SQLiteOpenHelper{

    private static final String NOME_BD = "database";
    private static final int VERSAO_BD = 2;

    private int level;
    public String idioma;
    public String[] nomeN = new String[9]; //Palavras Nativas
    public String[] nomeE = new String[9]; //Palavras Estrangeiras

    public clsExercicios(Context context){
        super(context, NOME_BD, null, VERSAO_BD);
       // this.contexto = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Setters
    public void setLevel(int _level, SQLiteDatabase db) {
        this.level = _level;
        db.execSQL("UPDATE "
                + "usu_info"
                + " set level = "+(level)+";");
    }
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getLevel(SQLiteDatabase db){
        Cursor c = db.rawQuery("SELECT * from usu_info", null);
        c.moveToFirst();
        if(c.moveToFirst()) {
            level = c.getInt(1);
        }
        return level;
    }
}
