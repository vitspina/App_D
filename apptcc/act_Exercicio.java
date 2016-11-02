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
                    if(pontos >= 10){
                        if(classeExerc.getPontosEx(db, level) < 10)
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
                    if(pontos >= 10){
                        if(classeExerc.getPontosEx(db, level) < 10)
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
                    if(pontos >= 10){
                        if(classeExerc.getPontosEx(db, level) < 10)
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

                    if(pontos >= 10){
                        if(classeExerc.getPontosEx(db, level) < 10)
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
                else if (level == 4 && numExer == 4) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,4,'pt','Recepção'),(2,4,'pt','Cumprimento'),(3,4,'pt','Check-in'),(4,4,'pt','Requisitar'),(5,4,'pt','Forma de Pagamento'),(6,4,'pt','Formulário'),(7, 4, 'pt', 'Chamar o Carregador de Bagagem'),(8,4,'pt','Pegar a Bagagem'),(9, 4, 'pt', 'Pegar a Chave'),(10,4,'pt','subir as escadas'),"

                            + " (1,4,'en','Reception'),(2,4,'en','Greeting'),(3,4,'en','Check-in'),(4,4,'en','Request'),(5,4,'en','Payment Form'),(6,4,'en','Forms'),(7, 4, 'en', 'Call the Bellboy'),(8,4,'en','Pick up the Luggage'),(9, 4, 'en', 'Pick up the Key'),(10,4,'en','Up Stairs')");
                    clsExerc.setLevel(level+1, db);
                }//Quarto
                else if (level == 5 && numExer == 5) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,5,'pt','Quarto'),(2,5,'pt','Telefone'),(3,5,'pt','Cama'),(4,5,'pt','Banheiro'),(5,5,'pt','Travesseiro'),(6,5,'pt','Mesa'),(7,5, 'pt', 'Ar Condicionado'),(8,5,'pt','Televisao'),(9,5, 'pt', 'Espelho'),(10,5,'pt','Janela'),"

                            + " (1,5,'en','Room'),(2,5,'en','Telefone'),(3,5,'en','Bed'),(4,5,'en','Bathroom'),(5,5,'en','Pillow'),(6,5,'en','Desk'),(7,5, 'en', 'AC'),(8,5,'en','Television'),(9,5, 'en', 'Mirror'),(10,5,'en','Window')");
                    clsExerc.setLevel(level+1, db);
                }//Restaurante
                else if (level == 6 && numExer == 6) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,6,'pt','Restaurante'),(2,6,'pt','Cardápio'),(3,6,'pt','Mesa'),(4,6,'pt','Garfo'),(5,6,'pt','Faca'),(6,6,'pt','Colher'),(7,6, 'pt', 'Chefe de Cozinha'),(8,1,'pt','Sobremesa'),(9, 1, 'pt', 'Vinho'),(10,6,'pt','Garçom'),"

                            + " (1,6,'en','Restaurant'),(2,6,'en','Menu'),(3,6,'en','Table'),(4,6,'en','Fork'),(5,6,'en','Knife'),(6,6,'en','Spoon'),(7,6, 'en', 'Chef'),(8,6,'en','Dessert'),(9,6, 'en', 'Wine'),(10,6,'en','Waiter')");
                    clsExerc.setLevel(level+1, db);
                }//Escola//Secretaria
                else if (level == 7 && numExer == 7) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,7,'pt','Folha'),(2,7,'pt','Tesoura'),(3,7,'pt','Apontador'),(4,7,'pt','Lousa'),(5,7,'pt','Giz'),(6,7,'pt','Livro'),(7,7, 'pt', 'Apagador'),(8,7,'pt','Lápis'),(9,7, 'pt', 'Caneta'),(10,7,'pt','Caderno'),"

                            + " (1,7,'en','Leaf'),(2,7,'en','Scissors'),(3,7,'en','Sharpener'),(4,7,'en','Blackboard'),(5,7,'en','Chalk'),(6,7,'en','Book'),(7,7, 'en', 'Eraser'),(8,7,'en','Pencil'),(9,7, 'en', 'Pen'),(10,7,'en','Notebook')");
                    clsExerc.setLevel(level+1, db);
                }//	Matérias / Nome
                else if (level == 8 && numExer == 8) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,8,'pt','Secretaria'),(2,8,'pt','Matematica'),(3,8,'pt','Fisica'),(4,8,'pt','Geografia'),(5,8,'pt','Arts'),(6,8,'pt','Logica'),(7, 8, 'pt', 'Quimica'),(8,8,'pt','Biologia'),(9,8, 'pt', 'Filosofia'),(10,8,'pt','Diretor'),"

                            + " (1,8,'en','Secretary'),(2,8,'en','Math'),(3,8,'en','Physics'),(4,8,'en','Geography'),(5,8,'en','Arts'),(6,8,'en','Logic'),(7,8, 'en', 'Biology'),(8,8,'en','Chemistry'),(9,8, 'en', 'Philosophy'),(10,8,'en','Principal')");
                    clsExerc.setLevel(level+1, db);
                }//Materiais/ Números
                else if (level == 9 && numExer == 9) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,9,'pt','Um'),(2,9,'pt','Dois'),(3,9,'pt','Tres'),(4,9,'pt','Quatro'),(5,9,'pt','Cinco'),(6,9,'pt','Seis'),(7,9, 'pt', 'Sete'),(8,9,'pt','Oito'),(9,9, 'pt', 'Nove'),(10,9,'pt','Dez'),"

                            + " (1,9,'en','One'),(2,9,'en','Two'),(3,9,'en','Three'),(4,9,'en','Four'),(5,9,'en','Five'),(6,9,'en','Six'),(7,9, 'en', 'Seven'),(8,9,'en','Eight'),(9,9, 'en', 'Nine'),(10,9,'en','Ten')");
                    clsExerc.setLevel(level+1, db);
                }//Hospital//Doenças (Nome de doenças)
                else if (level == 10 && numExer == 10) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,10,'pt','Alergia'),(2,10,'pt','Bronquite'),(3,10,'pt','Câimbra'),(4,10,'pt','Gripe'),(5,10,'pt','Tosse'),(6,10,'pt','Dor de cabeça'),(7,10, 'pt', 'Leucemia'),(8,10,'pt','Dor de Barriga'),(9, 10, 'pt', 'Febre'),(10,10,'pt','Dor de Garganta'),"

                            + " (1,10,'en','Allergy'),(2,10,'en','Bronchitis'),(3,10,'en','Cramp'),(4,10,'en','Flu'),(5,10,'en','Couch'),(6,10,'en','Headache'),(7, 10, 'en', 'Leukemia'),(8,10,'en','Stomach Ache'),(9,10, 'en', 'Fever'),(10,10,'en','Sore Throat')");
                    clsExerc.setLevel(level+1, db);
                }//Doutores/ (médico/enfermeiro. etc)
                else if (level == 11 && numExer == 11) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,11,'pt','Medico'),(2,11,'pt','Enfermeira'),(3,11,'pt','Psicologo'),(4,11,'pt','Psiquiatra'),(5,11,'pt','Cirurgiao'),(6,11,'pt','Medicamentos'),(7,11, 'pt', 'Neurologista'),(8,11,'pt','Pediatra'),(9,11, 'pt', 'Anestesista'),(10,11,'pt','Residente'),"

                            + " (1,11,'en','Doctor'),(2,11,'en','Nurse'),(3,11,'en','Psychologist'),(4,11,'en','Psychiatrist'),(5,11,'en','Surgeon'),(6,11,'en','Medicines'),(7,11, 'en', 'Neurologist'),(8,11,'en','Pediatrician'),(9,11, 'en', 'Anaesthetist'),(10,11,'en','Resident')");
                    clsExerc.setLevel(level+1, db);
                }//Remedios
                else if (level == 12 && numExer == 12) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,12,'pt','Remédio de ervas'),(2,12,'pt','Remédio Geriátrico'),(3,12,'pt','Remédio Obstétrico'),(4,12,'pt','Remédio Pediátrico'),(5,12,'pt','Remédio Veterinário'),(6,12,'pt','Remédio Preventivo'),(7,12, 'pt', 'Remédio Homeopáticos'),(8,12,'pt','Medicamentos da Saúde Pública'),(9,12, 'pt', 'Bula de Remédio'),(10,12,'pt','Remédio'),"

                            + " (1,12,'en','Herbal medicine'),(2,12,'en','Geriatric Medicine'),(3,12,'en','Obstetric Medicine'),(4,12,'en','Paediatric Medicine'),(5,12,'en','Veterinary Medicine'),(6,12,'en','Preventive Medicine'),(7,12, 'en', 'Homeopathic Medicine'),(8,12,'en','Public Health Medicine'),(9, 12, 'en', 'Drug Leaflet'),(10,12,'en','Medicine')");
                    clsExerc.setLevel(level+1, db);
                }//Park
                else if (level == 13 && numExer == 13) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,13,'pt','Campo'),(2,13,'pt','Churrasqueira'),(3,13,'pt','Fonte'),(4,13,'pt','Banco'),(5,13,'pt','Grama'),(6,13,'pt','Árvore'),(7, 13, 'pt', 'Vento'),(8,13,'pt','Parque Infantil'),(9,13, 'pt', 'Bebedouro'),(10,13,'pt','Bicicleta'),"

                            + " (1,13,'en','Field'),(2,13,'en','Fountain'),(3,13,'en','Bench'),(4,13,'en','Bank'),(5,13,'en','Grass'),(6,13,'en','Tree'),(7,13, 'en', ''),(8,13,'en','Playground'),(9,13, 'en', 'Drinking fountain),(10,13,'en','Bike')");
                    clsExerc.setLevel(level+1, db);
                }//Esportes
                else if (level == 14 && numExer == 14) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,14,'pt','Atletismo'),(2,14,'pt','Boxe'),(3,14,'pt','Ciclismo'),(4,14,'pt','Futebol'),(5,14,'pt','Handebol'),(6,14,'pt','Por do ‘Ginastica Artistica'),(7,14, 'pt', Xadrez),(8,14,'pt','Parque ‘Voleibol’),(9,14, 'pt', 'Natação'),(10,14,'pt','Hipismo'),"

                            + " (1,14,'en','Track and Field'),(2,14,'en','Boxing'),(3,14,'en','Cycling'),(4,14,'en','Soccer'),(5,14,'en','Handball'),(6,14,'en','Artistic Gymnastic'),(7, 14, 'en', 'Chess'),(8,14,'en','Volleyball'),(9,14, 'en', 'Swimming'),(10,14,'en','Horse racing')");
                    clsExerc.setLevel(level+1, db);
                }//Animais
                else if (level == 15 && numExer == 15) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,15,'pt','Abelha'),(2,15,'pt','Cachorro'),(3,15,'pt','Gato'),(4,15,'pt','Coelho'),(5,15,'pt','Borboleta'),(6,15,'pt','Por do ‘Besouro'),(7,15, 'pt', ‘Macaco’),(8,15,'pt','Mosca’),(9,15, 'pt', 'Pássaro'),(10,15,'pt','Pato'),"

                            + " (1,15,'en','Track and ‘Bee'),(2,15,'en','Dog'),(3,15,'en','Cat'),(4,15,'en','Rabbit'),(5,15,'en','Butterfly'),(6,15,'en','Beetle'),(7,15, 'en', 'Monkey'),(8,15,'en','Fly'),(9,15, 'en', 'Bird'),(10,15,'en','Duck')");
                    clsExerc.setLevel(level+1, db);
                }//Mercado//As frutas
                else if (level == 16 && numExer == 16) {
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,16,'pt','Maçã'),(2,16,'pt','Goiaba'),(3,16,'pt','Melão'),(4,16,'pt','Abacaxi'),(5,16,'pt','Uva'),(6,16,'pt','Pera'),(7,16, 'pt', 'Morango'),(8,16,'pt','Manga'),(9,16, 'pt', 'Limão'),(10,16,'pt','Laranja'),"

                            + " (1,16,'en','Apple'),(2,16,'en','Guava'),(3,16,'en','Melon'),(4,16,'en','Pineapple'),(5,16,'en','Grape'),(6,16,'en','Pear'),(7,16, 'en', 'Strawberry'),(8,16,'en','Mango'),(9,16, 'en', 'Lime'),(10,16,'en','Orange')");
                    clsExerc.setLevel(level+1, db);
                }//Bebidas
                else if (level == 17 && numExer == 17) {
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,17,'pt','Café'),(2,17,'pt','Chá'),(3,17,'pt','Leite'),(4,17,'pt','Iogurte'),(5,17,'pt','Suco'),(6,17,'pt','Água'),(7,17, 'pt', 'Cerveja'),(8,17,'pt','Vinho'),(9,17, 'pt', 'Refrigerante'),(10,17,'pt','Licor'),"

                            + " (1,17,'en','Coffee'),(2,17,'en','Tea'),(3,17,'en','Milk'),(4,17,'en','Yogurte'),(5,17,'en','Juice'),(6,17,'en','Water'),(7,17, 'en', 'Beer'),(8,17,'en','Wine'),(9, 17, 'en', 'Soda'),(10,17,'en','Liquer')");
                    clsExerc.setLevel(level+1, db);
                }//Comidas
                else if (level == 18 && numExer == 18) {
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,18,'pt','Carne'),(2,18,'pt','Pao'),(3,18,'pt','Bolo'),(4,18,'pt','Batata'),(5,18,'pt','Torta'),(6,18,'pt','Macarrão'),(7,18, 'pt', 'Peixe'),(8,18,'pt','Cenoura'),(9,18, 'pt', 'Arroz'),(10,18,'pt','Manteiga'),"

                            + " (1,18,'en','Meat'),(2,18,'en','Bread'),(3,18,'en','Cake'),(4,18,'en','Potato'),(5,18,'en','Pie'),(6,18,'en','Noodles'),(7,18, 'en', 'Finsh'),(8,18,'en','Carrots'),(9,18, 'en', 'Rice'),(10,18,'en','Butter')");
                    clsExerc.setLevel(level+1, db);
                } // Profissões
                else if (level == 19 && numExer == 19) {
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,19,'pt','Advogado'),(2,19,'pt','Bombeiro'),(3,19,'pt','Padeiro'),(4,19,'pt','Motorista'),(5,19,'pt','Jardineiro'),(6,19,'pt','Garçom'),(7,19, 'pt', 'Engenheiro'),(8,19,'pt','Professor'),(9,19, 'pt', 'Fazendeiro'),(10,19,'pt','Cabeleireiro'),"

                            + " (1,19,'en','Lawyer'),(2,19,'en','Fireman'),(3,19,'en','Baker'),(4,19,'en','Driver'),(5,19,'en','Gardener'),(6,19,'en','Waiter'),(7, 19, 'en', 'Eginner'),(8,19,'en','Teacher'),(9,19, 'en', 'Farmer'),(10,19,'en','Hairdresser')");
                    clsExerc.setLevel(level+1, db);
                }//Equipamentos
                else if (level == 20 && numExer == 20) {
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,20,'pt','Cronograma'),(2,20,'pt','Almoxarifado'),(3,20,'pt','Maquina'),(4,20,'pt','Lucro'),(5,20,'pt','Calculadora'),(6,20,'pt','Mesa de Escritório'),(7,20, 'pt', 'Stapler'),(8,20,'pt','Pasta'),(9,20, 'pt', 'Computador'),(10,20,'pt','Fio'),"

                            + " (1,20,'en','Schedule'),(2,20,'en','Warehouse'),(3,20,'en','Machine'),(4,20,'en','Profits'),(5,20,'en','Caculator'),(6,20,'en','Desktop'),(7,20, 'en', 'Stapler'),(8,20,'en','Folder'),(9,20, 'en', 'Computer'),(10,20,'en','Wire')");
                    clsExerc.setLevel(level+1, db);
                }

                else if (level == 21 && numExer == 21) {
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,21,'pt','8:05'),(2,21,'pt','02:10'),(3,21,'pt','06:15'),(4,21,'pt','03:20'),(5,21,'pt','09:25'),(6,21,'pt','01:30'),(7,21, 'pt', '04:35'),(8,21,'pt','05:40'),(9,21, 'pt', '07:45'),(10,21,'pt','10:50'),"

                            + " (1,21,'en','Eight Five'),(2,21,'en','Two Ten'),(3,21,'en','Six Fifteen'),(4,21,'en','Three Twenty'),(5,21,'en','Nine Twenty-Five'),(6,21,'en','One Thirty'),(7,21, 'en', 'Four-Thirty-Five'),(8,21,'en','Five Forty'),(9,21, 'en', 'Seven Forty-Five'),(10,21,'en','Ten Fifty')");
                    clsExerc.setLevel(level+1, db);
                }
                else if (level == 22 && numExer == 22) {
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,22,'pt','Saia'),(2,22,'pt','Vestido'),(3,22,'pt','Casaco'),(4,1,'pt','Blusa'),(5,22,'pt','Calça'),(6,22,'pt','Camisa de manga'),(7,22, 'pt', 'Jaqueta'),(8,22,'pt','Blusa de Frio'),(9,22, 'pt', 'Bermuda'),(10,22,'pt','Gravata'),"

                            + " (1,22,'en','Skirt'),(2,22,'en','Dress'),(3,22,'en','Coat'),(4,22,'en','Blouse'),(5,22,'en','Pants'),(6,22,'en','Shirt'),(7,22, 'en', 'Jacket'),(8,22,'en','Sweater'),(9,22, 'en', 'Shorts'),(10,22,'en','Tie')");
                    clsExerc.setLevel(level+1, db);
                }
                else if (level == 23 && numExer == 23) {
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,23,'pt','Troco'),(2,23,'pt','Centavos'),(3,23,'pt','Gorjeta'),(4,23,'pt','Fique com o Troco'),(5,23,'pt','Quanto Custa?'),(6,23,'pt','Barganha'),(7,23, 'pt', 'Desconto'),(8,23,'pt','Cartão de Crédito'),(9,23, 'pt', 'A Vista'),(10,23,'pt','Promocao'),"

                            + " (1,23,'en','change'),(2,23,'en','Cents'),(3,23,'en','Tip'),(4,23,'en','Keep the Change'),(5,23,'en','How Much?'),(6,23,'en','Bargain'),(7,23, 'en', 'Discount'),(8,23,'en','Credit Card'),(9,23, 'en', 'In Cash'),(10,23,'en','Promotion')");
                    clsExerc.setLevel(level+1, db);
                }
                else if (level == 24 && numExer == 24) {
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,24,'pt','Tênis'),(2,24,'pt','Sandálias'),(3,24,'pt','Chinelo'),(4,24,'pt','Botas'),(5,24,'pt','Salto Alto'),(6,24,'pt','Meia'),(7,24, 'pt', 'Sapatilha'),(8,24,'pt','Sapatos'),(9,24, 'pt', 'Polaina'),(10,24,'pt','Sapato Social'),"

                            + " (1,24,'en','Sneakers'),(2,24,'en','Sandals'),(3,24,'en','Flip Flops'),(4,24,'en','Boots'),(5,24,'en','High Heels'),(6,24,'en','Sock'),(7,24, 'en', 'Ballet Flats'),(8,24,'en','Shoes'),(9,24, 'en', 'Gaiter'),(10,24,'en','Social Shoe')");
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
                if(i <10) {
                    nomeE[i] = c.getString(3);
                    i++;
                }
            }while(c.moveToNext());
        }
        Cursor d = db.rawQuery("SELECT * FROM tb_palavras where level = "+(numEx)+" and language ='pt' order by id" , null); //'"+idioma+"'"
        //d.moveToFirst();
        i = 0;
        if(d.moveToFirst()) {
            do{
                if(i <10) {
                    nomeN[i] = d.getString(3);
                    i++;
                }
            }while (d.moveToNext());
            return true;
        }
        else{
            return false;
        }
    }
}
