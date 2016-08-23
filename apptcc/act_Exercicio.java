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
    public int level, j;
    public int numExer; //Numero do Exercicio selecionado
    public String idioma;
    public String[] nomeN = new String[9]; //Palavras Nativas
    public String[] nomeE = new String[9]; //Palavras Estrangeiras
    public int[] ppp = {5,5,5,5,5,5,5,5,5,5}; //Pontos Por Palavra
    public int[] nvlP = {3,3,3,3,3,3,3,3,3,3}; //Nivel da palavra
    public Random r = new Random();
    public int pontos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__exercicio);

        Bundle extras = getIntent().getExtras();
        level = extras.getInt("level");
        numExer  = extras.getInt("exerc");
        SQLiteDatabase db;
        db = this.openOrCreateDatabase("database", MODE_PRIVATE, null);

        clsExercicios classeExerc = new clsExercicios(this.getBaseContext());
        level = classeExerc.getLevel(db);

        verifyBase(db, classeExerc); //Cria/Busca as palavras

        final ProgressBar bp = (ProgressBar) findViewById(R.id.barPoints);
        final TextView txtPontos = (TextView) findViewById(R.id.txtPontos);
        final Button btn = (Button) findViewById(R.id.btnPalavra);
        final Button btnOpt1 = (Button) findViewById(R.id.btnOpt1);
        final Button btnOpt2 = (Button) findViewById(R.id.btnOpt2);
        final Button btnOpt3 = (Button) findViewById(R.id.btnOpt3);
        final Button btnOpt4 = (Button) findViewById(R.id.btnOpt4);

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
                j = (int) btn.getTag();
                if (btnOpt1.getTag() == btn.getTag()) {
                    //Certo
                    pontos = pontos + ppp[j];
                    txtPontos.setText("Pontos: " + (pontos));
                    if(ppp[j] != 1)
                    ppp[j]--;
                    bp.setProgress(pontos);
                    mudaBotoes(btn, btnOpt1, btnOpt2, btnOpt3, btnOpt4);
                } else {//Errou
                    btnOpt1.setBackgroundResource(R.color.Red);
                    pontos = pontos - 5;
                    if(ppp[j] !=5){
                        ppp[j]++;
                    }
                    txtPontos.setText("Pontos: " + (pontos));
                    bp.setProgress(pontos);
                }
            }
        });
        btnOpt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                j = (int) btn.getTag();
                if(btnOpt2.getTag() == btn.getTag()){
                    //Certo
                    pontos = pontos + ppp[j];
                    txtPontos.setText("Pontos: " + (pontos));
                    if(ppp[j] != 1)
                        ppp[j]--;                    bp.setProgress(pontos);
                    mudaBotoes(btn, btnOpt1, btnOpt2, btnOpt3, btnOpt4);
                }else{//Errou
                    btnOpt2.setBackgroundResource(R.color.Red);
                    pontos = pontos - 5;
                    if(ppp[j] !=5){
                        ppp[j]++;
                    }
                    txtPontos.setText("Pontos: " + (pontos));
                    bp.setProgress(pontos);
                }
            }
        });
        btnOpt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                j = (int) btn.getTag();
                if (btnOpt3.getTag() == btn.getTag()){
                    //Certo
                    pontos = pontos + ppp[j];
                    txtPontos.setText("Pontos: " + (pontos));
                    if(ppp[j] != 1)
                        ppp[j]--;
                    bp.setProgress(pontos);
                    mudaBotoes(btn, btnOpt1, btnOpt2, btnOpt3, btnOpt4);
                }else{//Errou
                    btnOpt3.setBackgroundResource(R.color.Red);
                    pontos = pontos - 5;
                    if(ppp[j] !=5){
                        ppp[j]++;
                    }
                    txtPontos.setText("Pontos: " + (pontos));
                    bp.setProgress(pontos);
                }
            }
        });
        btnOpt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                j = (int) btn.getTag();
                if(btnOpt4.getTag() == btn.getTag()){
                    //Certo
                    pontos = pontos + ppp[j];
                    txtPontos.setText("Pontos: " + (pontos));
                    if(ppp[j] != 1)
                        ppp[j]--;
                    bp.setProgress(pontos);
                    mudaBotoes(btn, btnOpt1, btnOpt2, btnOpt3, btnOpt4);
                } else {//Errou
                    btnOpt4.setBackgroundResource(R.color.Red);
                    pontos = pontos - 5;
                    if(ppp[j] !=5){
                        ppp[j]++;
                    }
                    txtPontos.setText("Pontos: " + (pontos));
                    bp.setProgress(pontos);
                }
            }
        });




    }
    public void mudaBotoes(Button btn, Button btnOpt1, Button btnOpt2, Button btnOpt3, Button btnOpt4){
        int r1 = 0;
        int aux;
        btnOpt1.setBackgroundResource(R.color.White);
        btnOpt2.setBackgroundResource(R.color.White);
        btnOpt3.setBackgroundResource(R.color.White);
        btnOpt4.setBackgroundResource(R.color.White);

        r1 = r.nextInt(9 - 0) + 0;

        if(ppp[r1] == 1 || ppp[r1] ==2){
            aux  = r1;
            r1 = r.nextInt(9 - 0) + 0;
            if(r1 == aux){
                r1 = r.nextInt(9 - 0) + 0;
            }
            if(r1 == aux){
                r1 = r.nextInt(9 - 0) + 0;
            }
        }

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
    public void verifyBase(SQLiteDatabase db, Object obj) {
        try {
            clsExercicios clsExerc = new clsExercicios(this.getBaseContext());

            db.execSQL("CREATE TABLE IF NOT EXISTS "
                    + "tb_palavras"
                    + " (id INT(1), level INT(3), language VARCHAR, palavra VARCHAR);");

            if (selecionarDados(db, numExer)) {
                //Encontrou as palavras.... segue o jogo
            } else {
                //Não encontrou, cria
                if (level == 1 && numExer ==1) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1, 1, 'pt', 'Olá'),(2,1,'pt','Bom dia'),(3, 1, 'pt', 'Boa noite'),(4,1,'pt','Desculpe'),(5, 1, 'pt', 'Obrigado'),(6,1,'pt','Tchau'),(7, 1, 'pt', 'Noite'),(8,1,'pt','Dia'),(9, 1, 'pt', 'Tarde'),(10,1,'pt','Nome'),"
                            + " (1, 1, 'en', 'Hello'),(2,1,'en','Good Morning'),(3, 1, 'en', 'Good Night'),(4,1,'en','Sorry'),(5, 1, 'en', 'Thank You'),(6,1,'en','Bye'),(7, 1, 'en', 'Night'),(8,1,'en','Day'),(9, 1, 'en', 'Afternoon'),(10,1,'en','Name')");
                    clsExerc.setLevel(level+1, db);
                }
                else if (level == 2 && numExer ==2) { //Praca de Alim
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1, 2, 'pt', 'Pão'),(2,2,'pt','Suco'),(3, 2, 'pt', 'Café'),(4,2,'pt','Salada'),(5, 2, 'pt', 'Carne'),(6,2,'pt','Frango'),(7, 2, 'pt', 'Batata'),(8,2,'pt','Água'),(9, 2, 'pt', 'Refrigerante'),(10,2,'pt','Chá'),"
                            + " (1, 2, 'en', 'Bread'),(2,2,'en','Juice'),(3, 2, 'en', 'Coffee'),(4,2,'en','Salad'),(5, 2, 'en', 'Meat'),(6,2,'en','Chicken'),(7, 2, 'en', 'Potato'),(8,2,'en','Water'),(9, 2, 'en', 'Soda'),(10,2,'en','Tea')");
                    clsExerc.setLevel(level+1, db);
                }
                    selecionarDados(db, numExer);

            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    private boolean selecionarDados(SQLiteDatabase db, int numEx){
        /*retrieve data from database */
        Cursor c = db.rawQuery("SELECT * FROM tb_palavras where level = "+(numEx)+" and language ='en'" , null); //'"+idioma+"'"
        c.moveToFirst();
        int i = 0;
        if(c.moveToFirst()) {
            while (c.moveToNext()) {
                nomeE[i] = c.getString(3);
                i++;
            }
        }
        Cursor d = db.rawQuery("SELECT * FROM tb_palavras where level = "+(numEx)+" and language ='pt'" , null); //'"+idioma+"'"
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
