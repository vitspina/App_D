package boonedev.apptcc;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class act_Exercicio extends AppCompatActivity {
    public int level;
    public String idioma;
    public String[] nomeN = new String[9]; //Palavras Nativas
    public String[] nomeE = new String[9]; //Palavras Estrangeiras

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__exercicio);

        Bundle extras = getIntent().getExtras();
        level = extras.getInt("level");
        SQLiteDatabase db;
        db = this.openOrCreateDatabase("database", MODE_PRIVATE, null);

        verifyBase(db);
        final Button btn = (Button) findViewById(R.id.button);
        clsExercicios teste = new clsExercicios(this.getBaseContext());
        teste.setLevel(level);

        final int i =5;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Random r = new Random();
                //int r1 = r.nextInt(0 - 9) + 9;
                btn.setText(nomeN[i]);
            }
        });


    }

    private void verifyBase(SQLiteDatabase db) {
        try {


            db.execSQL("CREATE TABLE IF NOT EXISTS "
                    + "tb_palavras"
                    + " (id INT(1), level INT(3), language VARCHAR, palavra VARCHAR);");

            if (selecionarDados(db)) {
                if (level == 0) {
                    level = 1;
                }
            } else {
                if (level == 0) {
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
        Cursor c = db.rawQuery("SELECT * FROM tb_palavras where level = "+(level+1)+" and language ='en'" , null); //'"+idioma+"'"
        c.moveToFirst();
        int i = 0;
        if(c.moveToFirst()) {
            while (c.moveToNext()) {
                nomeE[i] = c.getString(3);
                i++;
            }
        }
        Cursor d = db.rawQuery("SELECT * FROM tb_palavras where level = "+(level+1)+" and language ='pt'" , null); //'"+idioma+"'"
        d.moveToFirst();
        i = 0;
        if(d.moveToFirst()) {
            while (d.moveToNext()) {
                nomeN[i] = d.getString(3);
                i++;
            }
            return true;
        }
        else{
            return false;
        }
    }
}
