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
        verifyBase(db);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Setters
    public void setLevel(int level) {
        this.level = level;
    }
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    private void verifyBase(SQLiteDatabase db) {
        try {


            db.execSQL("CREATE TABLE IF NOT EXISTS "
                    + "tb_palavras"
                    + " (id INT(1), level INT(3), language VARCHAR, palavra VARCHAR);");

            if (selecionarDados(db)) {
                if (level == 1) {

                }
            } else {
                if (level == 1) {
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1, 1, 'pt', 'Olá'),(2,1,'pt','Bom dia'),(3, 1, 'pt', 'Boa noite'),(4,1,'pt','Desculpe'),(5, 1, 'pt', 'Obrigado'),(6,1,'pt','Tchau'),(7, 1, 'pt', 'Noite'),(8,1,'pt','Dia'),(9, 1, 'pt', 'Tarde'),(10,1,'pt','Nome'),"
                            + " (1, 1, 'en', 'Hello'),(2,1,'en','Good Morning'),(3, 1, 'en', 'Good Night'),(4,1,'en','Sorry'),(5, 1, 'en', 'Thank You'),(6,1,'en','Bye'),(7, 1, 'en', 'Night'),(8,1,'en','Day'),(9, 1, 'en', 'Afternoon'),(10,1,'en','Name')");

                    selecionarDados(db);
                }
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    private boolean selecionarDados(SQLiteDatabase db){
        /*retrieve data from database */
        Cursor c = db.rawQuery("SELECT * FROM tb_palavras where level = "+level+" and language = '"+idioma+"'", null);
        c.moveToFirst();
        if(c.moveToFirst()) {
            int i = 0;
            while (c.moveToNext()) {
                nomeN[i] = c.getString(3);
                i++;
            }
        return true;
        }
        else{
            return false;
        }
    }
}
