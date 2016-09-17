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
    public SQLiteDatabase db;

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
    public void setTreinoUp(int _level, SQLiteDatabase db) {
        this.level = _level;
        db.execSQL("UPDATE "
                + "usu_inflvl"
                + " set treino = 1 "
                + "where level = "+(level)+";");
    }
    public void setExUp(int _level, SQLiteDatabase db, int pontos) {
        this.level = _level;
        db.execSQL("UPDATE "
                + "usu_inflvl"
                + " set ex1 = "+ (pontos) +" "
                + "where level = "+(level)+";");
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getPontosEx(SQLiteDatabase db, int _level){
        int pontos =0;
        this.level = _level;
        Cursor c = db.rawQuery("SELECT ex1 from usu_inflvl where level = "+(level)+";", null);
        if(c.moveToFirst()) {
            pontos = c.getInt(0);
        }
        return pontos;
    }

    public int getScoreTotal(SQLiteDatabase db, int _level){
        int p1 =0;
        int p2 =0;
        int pt =0;
        this.level = _level;
        Cursor c = db.rawQuery("SELECT treino, ex1 from usu_inflvl where level = "+(level)+";", null);
        if(c.moveToFirst()) {
            p1 = c.getInt(0);
            p2 = c.getInt(1);
        }
        if(p1 == 1){
            pt = 1;
            if(p2 >=100){
                pt=2;
            }
        }
        return pt;
    }
    public int getLevel(SQLiteDatabase db){
        Cursor c = db.rawQuery("SELECT * from usu_info", null);
        if(c.moveToFirst()) {
            level = c.getInt(1);
        }
        return level;
    }

}
