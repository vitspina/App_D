package boonedev.apptcc;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class act_Exercicio extends AppCompatActivity {
    public int level, j;
    public int numExer, lastP; //Numero do Exercicio selecionado
    public String idioma;
    public String[] nomeN = new String[10]; //Palavras Nativas
    public String[] nomeE = new String[10]; //Palavras Estrangeiras
    public int[] ppp = {5,5,5,5,5,5,5,5,5,5}; //Pontos Por Palavra
    public int[] nvlP = {3,3,3,3,3,3,3,3,3,3}; //Nivel da palavra
    public Random r = new Random();
    public int pontos = 0;
    public boolean controlaCreateBar = false;
    public Canvas c = new Canvas();

    private CustomBarra seekbar;

    private ProgressItem mProgressItem;
    private ArrayList<ProgressItem> progressItemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__exercicio);

        Bundle extras = getIntent().getExtras();
        level = extras.getInt("level");
        numExer  = extras.getInt("exerc");
        final SQLiteDatabase db;
        db = this.openOrCreateDatabase("database", MODE_PRIVATE, null);

         final clsExercicios classeExerc = new clsExercicios(this.getBaseContext());
        level = classeExerc.getLevel(db);
        seekbar = ((CustomBarra) findViewById(R.id.customSeekBar));

        //verifyBase(db, classeExerc); //Cria/Busca as palavras
        selecionarDados(db, numExer); //Busca Palavras

        final ProgressBar bp = (ProgressBar) findViewById(R.id.barPoints);
        final TextView txtPontos = (TextView) findViewById(R.id.txtPontos);
        final Button btn = (Button) findViewById(R.id.btnPalavra);
        final Button btnOpt1 = (Button) findViewById(R.id.btnOpt1);
        final Button btnOpt2 = (Button) findViewById(R.id.btnOpt2);
        final Button btnOpt3 = (Button) findViewById(R.id.btnOpt3);
        final Button btnOpt4 = (Button) findViewById(R.id.btnOpt4);

       /* if(classeExerc.getPontosEx(db, numExer) >= 100){ //Faz o exercício voltar com os pontos anteriores
            pontos = classeExerc.getPontosEx(db, level);
        }*/

        bp.setProgress(0);
        txtPontos.setText("Pontos: " + pontos);
        if(!controlaCreateBar) criaBarraMultiCor();

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
                    if(pontos >= 100){
                        if(classeExerc.getPontosEx(db, level) < 100)
                        classeExerc.setExUp(level, db, pontos);
                        else{
                            classeExerc.setLevel(level+1, db);
                        }
                    }
                    mudaBotoes(btn, btnOpt1, btnOpt2, btnOpt3, btnOpt4);
                    alteraBarraMultiCor();
                } else {//Errou
                    btnOpt1.setBackgroundResource(R.color.Red);
                    pontos = pontos - 5;
                    if(ppp[j] !=5){
                        ppp[j]++;
                    }
                    txtPontos.setText("Pontos: " + (pontos));
                    bp.setProgress(pontos);
                    alteraBarraMultiCor();
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
                    if(pontos >= 100){
                        if(classeExerc.getPontosEx(db, level) < 100)
                            classeExerc.setExUp(level, db, pontos);
                        else{
                            classeExerc.setLevel(level+1, db);
                        }
                    }
                    mudaBotoes(btn, btnOpt1, btnOpt2, btnOpt3, btnOpt4);
                    alteraBarraMultiCor();
                }else{//Errou
                    btnOpt2.setBackgroundResource(R.color.Red);
                    pontos = pontos - 5;
                    if(ppp[j] !=5){
                        ppp[j]++;
                    }
                    txtPontos.setText("Pontos: " + (pontos));
                    bp.setProgress(pontos);
                    alteraBarraMultiCor();
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
                    if(pontos >= 100){
                        if(classeExerc.getPontosEx(db, level) < 100)
                            classeExerc.setExUp(level, db, pontos);
                        else{
                            classeExerc.setLevel(level+1, db);
                        }
                    }
                    mudaBotoes(btn, btnOpt1, btnOpt2, btnOpt3, btnOpt4);
                    alteraBarraMultiCor();
                }else{//Errou
                    btnOpt3.setBackgroundResource(R.color.Red);
                    pontos = pontos - 5;
                    if(ppp[j] !=5){
                        ppp[j]++;
                    }
                    txtPontos.setText("Pontos: " + (pontos));
                    bp.setProgress(pontos);
                    alteraBarraMultiCor();
                }
            }
        });
        btnOpt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                j = (int) btn.getTag();
                if(btnOpt4.getTag() == btn.getTag()) {
                    //Certo
                    pontos = pontos + ppp[j];
                    txtPontos.setText("Pontos: " + (pontos));
                    if(ppp[j] != 1)
                        ppp[j]--;
                    bp.setProgress(pontos);

                    if(pontos >= 100){
                        if(classeExerc.getPontosEx(db, level) < 100)
                            classeExerc.setExUp(level, db, pontos);
                        else{
                            classeExerc.setLevel(level+1, db);
                        }
                    }

                    mudaBotoes(btn, btnOpt1, btnOpt2, btnOpt3, btnOpt4);
                    alteraBarraMultiCor();
                } else {//Errou
                    btnOpt4.setBackgroundResource(R.color.Red);
                    pontos = pontos - 5;
                    if(ppp[j] !=5){
                        ppp[j]++;
                    }
                    txtPontos.setText("Pontos: " + (pontos));
                    bp.setProgress(pontos);
                    alteraBarraMultiCor();
                }
            }
        });




    }
    public void criaBarraMultiCor(){
        int[] pesos = {0,0,0,0,0};
        pesos = verificaPesos();
        progressItemList = new ArrayList<ProgressItem>();
        // red span
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = pesos[0]*10;
        mProgressItem.color = R.color.Red;
        progressItemList.add(mProgressItem);
        //orange
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = pesos[1]*10;
        mProgressItem.color = R.color.Orange;
        progressItemList.add(mProgressItem);
        //orange
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = pesos[2]*10;
        mProgressItem.color = R.color.LightOrange;
        progressItemList.add(mProgressItem);
        //orange
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = pesos[3]*10;
        mProgressItem.color = R.color.Yellow;
        progressItemList.add(mProgressItem);
        //orange
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = pesos[4]*10;
        mProgressItem.color = R.color.Green;
        progressItemList.add(mProgressItem);

        seekbar.initData(progressItemList);
        controlaCreateBar = true;
    }
    public void alteraBarraMultiCor(){
        int[] pesos = {0,0,0,0,0};
        int r,g,y,o;
        pesos = verificaPesos();
        progressItemList.get(0).progressItemPercentage = pesos[0]*10;
        progressItemList.get(1).progressItemPercentage = pesos[1]*10;
        progressItemList.get(2).progressItemPercentage = pesos[2]*10;
        progressItemList.get(3).progressItemPercentage = pesos[3]*10;
        progressItemList.get(4).progressItemPercentage = pesos[4]*10;
        seekbar.initData(progressItemList);
        seekbar.invalidate();

    }
    public int[] verificaPesos(){
        int i =0;
        int[] pesos = {0,0,0,0,0};
        for(i = 0; i<10; i++) {
            if(ppp[i] ==5){
                pesos[0]++;
            }else if(ppp[i] ==4){
                pesos[1]++;
            }else if(ppp[i] ==3){
                pesos[2]++;
            }else if(ppp[i] ==2){
                pesos[3]++;
            }else if(ppp[i] ==1){
                pesos[4]++;
            }
        }
        return pesos;
    }

    public void mudaBotoes(Button btn, Button btnOpt1, Button btnOpt2, Button btnOpt3, Button btnOpt4){


        int r1 = 0;
        int aux;

        btnOpt1.setBackgroundResource(R.color.White);
        btnOpt2.setBackgroundResource(R.color.White);
        btnOpt3.setBackgroundResource(R.color.White);
        btnOpt4.setBackgroundResource(R.color.White);



        do {
            r1 = r.nextInt(9 + 1);
        }
        while(r1 == j);

        if(ppp[r1] == 1 || ppp[r1] ==2){
            aux  = r1;
            r1 = r.nextInt(9 + 1);
            if(r1 == aux){
                r1 = r.nextInt(9 + 1);
            }
            if(r1 == aux){
                r1 = r.nextInt(9 + 1);
            }
        }

        btn.setText(nomeN[r1]);
        btn.setTag(r1);

        int i = 0;
        int[] outro = new int[4];
        int[] nPode = new int[4];

        for(i = 0; i<4; i++) {

            outro[i] = r.nextInt(9 + 1);

            if (outro[i] == r1) {
                if (r1 != 0)
                    outro[i] = outro[i] - 1;
                else {
                    outro[i] = outro[i] + 1;
                }
            }else{
                if(i == 1) {
                    if(outro[i] == outro[i-1]){
                        i--;
                    }
                }
                if(i == 2){
                    if(outro[i]== outro[i-1] || outro[i] == outro[i-2])
                    {
                        i--;
                    }
                }
                if(i==3){
                    if(outro[i]== outro[i-1] || outro[i] == outro[i-2] || outro[i] == outro[i-3])
                    {
                        i--;
                    }
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
                    + " (id INT(3), level INT(3), language VARCHAR, palavra VARCHAR);");

            if (selecionarDados(db, numExer)) {
                //Encontrou as palavras.... segue o jogo
            } else {
                //Não encontrou, cria
                if (level == 1 && numExer ==1) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,1,'pt','Olá'),(2,1,'pt','Bom dia'),(3,1,'pt','Boa noite'),(4,1,'pt','Desculpe'),(5,1,'pt','Obrigado'),(6,1,'pt','Tchau'),(7, 1, 'pt', 'Noite'),(8,1,'pt','Dia'),(9, 1, 'pt', 'Tarde'),(10,1,'pt','Nome'),"
                            + " (1,1,'en','Hello'),(2,1,'en','Good Morning'),(3,1,'en','Good Night'),(4,1,'en','Sorry'),(5,1,'en','Thank You'),(6,1,'en','Bye'),(7, 1, 'en', 'Night'),(8,1,'en','Day'),(9, 1, 'en', 'Afternoon'),(10,1,'en','Name')");
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
                else if (level == 3 && numExer ==3) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,3,'pt','Agencia de Taxi'),(2,3,'pt','Destino'),(3,3,'pt','Preço'),(4,3,'pt','Dinheiro'),(5,3,'pt','Cartao Debito - Credito'),(6,3,'pt','Taxi'),(7, 3, 'pt', 'Mala'),(8,3,'pt','Por favor'),(9, 3, 'pt', 'Companhia Aérea'),(10,3,'pt','Senhor'),"
                            + " (1,3,'en','Taxi Agency'),(2,3,'en','Destination'),(3,3,'en','Price'),(4,3,'en','Cash'),(5,3,'en','Debit - Credit Cards'),(6,3,'en','Taxi Cab'),(7, 3, 'en', 'Suitcase'),(8,3,'en','Please'),(9, 3, 'en', 'Airline'),(10,3,'en','Gentleman')");
                    clsExerc.setLevel(level+1, db);
                }
                else if (level == 4 && numExer ==4) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,1,'pt','Diária'),(2,1,'pt','Porteiro'),(3,1,'pt','Camareira'),(4,1,'pt','Gorjeta'),(5,1,'pt','Recepçao'),(6,1,'pt','quarto de solteiro'),(7, 1, 'pt', 'Mala'),(8,1,'pt','piscina'),(9, 1, 'pt', 'serviço de quarto'),(10,1,'pt','quarto de casal'), (11,1,'pt','Quanto foi?'), (12,1,'pt','Saída'), (13,1,'en','Passaporte'), (14,1,'en','Voo'), (15,1,'en','Banheiro')"
                            + " (1,1,'en','Daily Rate'),(2,1,'en','Porter'),(3,1,'en','Chambermaid'),(4,1,'en','Tip'),(5,1,'en','Front Desk'),(6,1,'en','single room'),(7, 1, 'en', 'Suitcase'),(8,1,'en','swimming pool'),(9, 1, 'en', 'room service'),(10,1,'en','double room'), (11,1,'en','how much'), (12,1,'en','Exit'), (13,1,'en','Passaport'), (14,1,'en','Flight'), (15,1,'en','Rest Room')");
                    clsExerc.setLevel(level+1, db);
                }
                else if (level ==5 && numExer ==5) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,1,'pt','Diária'),(2,1,'pt','Porteiro'),(3,1,'pt','Camareira'),(4,1,'pt','Gorjeta'),(5,1,'pt','Recepçao'),(6,1,'pt','quarto de solteiro'),(7, 1, 'pt', 'Mala'),(8,1,'pt','piscina'),(9, 1, 'pt', 'serviço de quarto'),(10,1,'pt','quarto de casal'), (11,1,'pt','Quanto foi?'), (12,1,'pt','Saída'), (13,1,'en','Passaporte'), (14,1,'en','Voo'), (15,1,'en','Banheiro')"
                            + " (1,1,'en','Daily Rate'),(2,1,'en','Porter'),(3,1,'en','Chambermaid'),(4,1,'en','Tip'),(5,1,'en','Front Desk'),(6,1,'en','single room'),(7, 1, 'en', 'Suitcase'),(8,1,'en','swimming pool'),(9, 1, 'en', 'room service'),(10,1,'en','double room'), (11,1,'en','how much'), (12,1,'en','Exit'), (13,1,'en','Passaport'), (14,1,'en','Flight'), (15,1,'en','Rest Room')");
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
        Cursor c = db.rawQuery("SELECT * FROM tb_palavras where level = "+(numEx)+" and language ='en' order by id" , null); //'"+idioma+"'"
        //c.moveToFirst();
        int i = 0;
        if(c.moveToFirst()) {
            do {
                nomeE[i] = c.getString(3);
                i++;
            }while(c.moveToNext());
        }
        Cursor d = db.rawQuery("SELECT * FROM tb_palavras where level = "+(numEx)+" and language ='pt' order by id" , null); //'"+idioma+"'"
        //d.moveToFirst();
        i = 0;
        if(d.moveToFirst()) {
                do{
                    nomeN[i] = d.getString(3);
                    i++;
                }while (d.moveToNext());
            return true;
        }
        else{
            return false;
        }
    }
}
