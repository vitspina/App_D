package boonedev.apptcc;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class act_Exercicio extends AppCompatActivity {
    public int level;
    public String idioma;
    public String[] nomeN = new String[9]; //Palavras Nativas
    public String[] nomeE = new String[9]; //Palavras Estrangeiras
    public Random r = new Random();
    public int pontos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__exercicio);

        Bundle extras = getIntent().getExtras();
        level = extras.getInt("level");
        SQLiteDatabase db;
        db = this.openOrCreateDatabase("database", MODE_PRIVATE, null);

        verifyBase(db);
        final ProgressBar bp = (ProgressBar) findViewById(R.id.barPoints);
        final TextView txtPontos = (TextView) findViewById(R.id.txtPontos);
        final Button btn = (Button) findViewById(R.id.btnPalavra);
        final Button btnOpt1 = (Button) findViewById(R.id.btnOpt1);
        final Button btnOpt2 = (Button) findViewById(R.id.btnOpt2);
        final Button btnOpt3 = (Button) findViewById(R.id.btnOpt3);
        final Button btnOpt4 = (Button) findViewById(R.id.btnOpt4);
        clsExercicios teste = new clsExercicios(this.getBaseContext());
        teste.setLevel(level);
        bp.setProgress(0);
        txtPontos.setText("Pontos: " + pontos);
        mudaBotoes(btn, btnOpt1, btnOpt2, btnOpt3, btnOpt4);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Random r = new Random();
                //int r1 = r.nextInt(0 - 9) + 9;
            }
        });
        btnOpt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnOpt1.getTag() == btn.getTag()) {
                    //Certo
                    pontos = pontos +10;
                    txtPontos.setText("Pontos: " + (pontos));
                    bp.setProgress(pontos);
                    mudaBotoes(btn, btnOpt1, btnOpt2, btnOpt3, btnOpt4);
                } else {
                    pontos = pontos -10;
                    txtPontos.setText("Pontos: " + (pontos));
                    bp.setProgress(pontos);
                }
            }
        });
        btnOpt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnOpt2.getTag() == btn.getTag()){
                    //Certo
                    pontos = pontos +10;
                    txtPontos.setText("Pontos: " + (pontos));
                    bp.setProgress(pontos);
                    mudaBotoes(btn, btnOpt1, btnOpt2, btnOpt3, btnOpt4);
                }else{
                    pontos = pontos -10;
                    txtPontos.setText("Pontos: " + (pontos));
                    bp.setProgress(pontos);
                }
            }
        });
        btnOpt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnOpt3.getTag() == btn.getTag()){
                    //Certo
                    pontos = pontos +10;
                    txtPontos.setText("Pontos: " + (pontos));
                    bp.setProgress(pontos);
                    mudaBotoes(btn, btnOpt1, btnOpt2, btnOpt3, btnOpt4);
                }else{
                    pontos = pontos -10;
                    txtPontos.setText("Pontos: " + (pontos));
                    bp.setProgress(pontos);
                }
            }
        });
        btnOpt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnOpt4.getTag() == btn.getTag()){
                    //Certo
                    pontos = pontos +10;
                    txtPontos.setText("Pontos: " + (pontos));
                    bp.setProgress(pontos);
                    mudaBotoes(btn, btnOpt1, btnOpt2, btnOpt3, btnOpt4);
                } else {
                    pontos = pontos -10;
                    txtPontos.setText("Pontos: " + (pontos));
                    bp.setProgress(pontos);
                }
            }
        });




    }
    public void mudaBotoes(Button btn, Button btnOpt1, Button btnOpt2, Button btnOpt3, Button btnOpt4){
        int r1 = 0;
        r1 = r.nextInt(9 - 0) + 0;

        btn.setText(nomeN[r1]);
        btn.setTag(r1);

        int i = 0;
        int[] outro = new int[4];

        for(i = 0; i<4; i++) {

            outro[i] = r.nextInt(9 - 0) + 0;
            if (outro[i] == r1) {
                if (r1 != 0)
                    outro[i] = outro[i] - 1;
                else {
                    outro[i] = outro[i] + 1;
                }
            }
        }
        int rand = 0;
        rand = r.nextInt(4 - 0) + 0;
        //btns[rand] = r1;
        switch (rand){
            case 0:
                btnOpt1.setText(nomeE[r1]);
                btnOpt1.setTag(r1);
                btnOpt2.setText(nomeE[outro[1]]);
                btnOpt2.setTag("");
                btnOpt3.setText(nomeE[outro[2]]);
                btnOpt3.setTag("");
                btnOpt4.setText(nomeE[outro[3]]);
                btnOpt4.setTag("");
                break;
            case 1:
                btnOpt1.setText(nomeE[outro[0]]);
                btnOpt1.setTag("");
                btnOpt2.setText(nomeE[r1]);
                btnOpt2.setTag(r1);
                btnOpt3.setText(nomeE[outro[2]]);
                btnOpt3.setTag("");
                btnOpt4.setText(nomeE[outro[3]]);
                btnOpt4.setTag("");
                break;
            case 2:
                btnOpt1.setText(nomeE[outro[0]]);
                btnOpt1.setTag("");
                btnOpt2.setText(nomeE[outro[1]]);
                btnOpt3.setTag("");
                btnOpt3.setText(nomeE[r1]);
                btnOpt3.setTag(r1);
                btnOpt4.setText(nomeE[outro[3]]);
                btnOpt4.setTag("");
                break;
            case 3:
                btnOpt1.setText(nomeE[outro[0]]);
                btnOpt1.setTag("");
                btnOpt2.setText(nomeE[outro[1]]);
                btnOpt2.setTag("");
                btnOpt3.setText(nomeE[outro[2]]);
                btnOpt3.setTag("");
                btnOpt4.setText(nomeE[r1]);
                btnOpt4.setTag(r1);
                break;
        }
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
