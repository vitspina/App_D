package boonedev.apptcc;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class act_Treino extends AppCompatActivity {
    public String[] nomeN = new String[10]; //Palavras Nativas
    public String[] nomeE = new String[10]; //Palavras Estrangeiras
    public int level, i;
    public int numExer; //Numero do Exercicio selecionado
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_treino);
        db = this.openOrCreateDatabase("database", MODE_PRIVATE, null);

        Bundle extras = getIntent().getExtras();
        level = extras.getInt("level");
        numExer  = extras.getInt("exerc");

        final TextView txtPalavraEX = (TextView) findViewById(R.id.txtTreinoEX);
        final TextView txtPalavraNA = (TextView) findViewById(R.id.txtTreinoNA);
        final TextView txtLimite = (TextView) findViewById(R.id.txtLimite);
        final Button btnNext = (Button) findViewById(R.id.btnNext);
        final Button btnPrevious = (Button) findViewById(R.id.btnPrevious);
        final Button btnIrParaExer = (Button) findViewById(R.id.btnIrParaExer);
        i=0;
        final clsExercicios classeExerc = new clsExercicios(this.getBaseContext());
        level = classeExerc.getLevel(db);
        verifyBase(db, classeExerc); //Cria/Busca as palavras
        //selecionarDados(db, numExer);
        btnIrParaExer.setEnabled(false);
        txtPalavraEX.setText(nomeE[i]);
        txtPalavraNA.setText(nomeN[i]);
        txtLimite.setText((i+1)+"/10");
        //btnIrParaExer.setBackground(getResources().getDrawable(R.drawable.lock));
        btnIrParaExer.setVisibility(View.INVISIBLE);


        btnIrParaExer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnIrParaExerOnClick();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i != 9) {
                    i++;
                }else{
                    btnIrParaExer.setVisibility(View.VISIBLE);
                    btnIrParaExer.setEnabled(true);
                    classeExerc.setTreinoUp(level, db);
                }
                txtPalavraEX.setText(nomeE[i]);
                txtPalavraNA.setText(nomeN[i]);
                txtLimite.setText((i+1)+"/10");
            }
        });
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i != 0)
                    i--;
                txtPalavraEX.setText(nomeE[i]);
                txtPalavraNA.setText(nomeN[i]);
                txtLimite.setText((i+1)+"/10");
            }
        });
    }

    public void btnIrParaExerOnClick(){
        Bundle bundle = new Bundle();
        bundle.putInt("level", level);
        bundle.putInt("exerc", numExer);
        Intent nextActivity = new Intent(this, act_Exercicio.class);
        nextActivity.putExtras(bundle);
        startActivity(nextActivity);
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
                if (level == 1 && numExer == 1) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "usu_inflvl"
                            + " (level, treino, ex1)"
                            + " VALUES (1,0,0)");

                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,1,'pt','Olá'),(2,1,'pt','Bom dia'),(3,1,'pt','Boa noite'),(4,1,'pt','Desculpe'),(5,1,'pt','Obrigado'),(6,1,'pt','Tchau'),(7, 1, 'pt', 'Noite'),(8,1,'pt','Dia'),(9, 1, 'pt', 'Tarde'),(10,1,'pt','Nome'),"
                            + " (1,1,'en','Hello'),(2,1,'en','Good Morning'),(3,1,'en','Good Night'),(4,1,'en','Sorry'),(5,1,'en','Thank You'),(6,1,'en','Bye'),(7, 1, 'en', 'Night'),(8,1,'en','Day'),(9, 1, 'en', 'Afternoon'),(10,1,'en','Name')");
                    clsExerc.setLevel(level + 1, db);
                } else if (level == 2 && numExer == 2) { //Praca de Alim
                    db.execSQL("INSERT INTO "
                            + "usu_inflvl"
                            + " (level, treino, ex1)"
                            + " VALUES (2,0,0)");

                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1, 2, 'pt', 'Pão'),(2,2,'pt','Suco'),(3, 2, 'pt', 'Café'),(4,2,'pt','Salada'),(5, 2, 'pt', 'Carne'),(6,2,'pt','Frango'),(7, 2, 'pt', 'Batata'),(8,2,'pt','Água'),(9, 2, 'pt', 'Refrigerante'),(10,2,'pt','Chá'),"
                            + " (1, 2, 'en', 'Bread'),(2,2,'en','Juice'),(3, 2, 'en', 'Coffee'),(4,2,'en','Salad'),(5, 2, 'en', 'Meat'),(6,2,'en','Chicken'),(7, 2, 'en', 'Potato'),(8,2,'en','Water'),(9, 2, 'en', 'Soda'),(10,2,'en','Tea')");
                    clsExerc.setLevel(level + 1, db);
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
                            + " VALUES (1,1,'pt','Diária'),(2,1,'pt','Porteiro'),(3,1,'pt','Camareira'),(4,1,'pt','Gorjeta'),(5,1,'pt','Recepçao'),(6,1,'pt','quarto de solteiro'),(7, 1, 'pt', 'Mala'),(8,1,'pt','piscina'),(9, 1, 'pt', 'serviço de quarto'),(10,1,'pt','quarto de casal'), (11,1,'pt','Quanto foi?'), (12,1,'pt','Saída'), (13,1,'en','Passaporte'), (14,1,'en','Voo'), (15,1,'en','Banheiro'),"
                            + " (1,1,'en','Daily Rate'),(2,1,'en','Porter'),(3,1,'en','Chambermaid'),(4,1,'en','Tip'),(5,1,'en','Front Desk'),(6,1,'en','single room'),(7, 1, 'en', 'Suitcase'),(8,1,'en','swimming pool'),(9, 1, 'en', 'room service'),(10,1,'en','double room'), (11,1,'en','how much'), (12,1,'en','Exit'), (13,1,'en','Passaport'), (14,1,'en','Flight'), (15,1,'en','Rest Room')");
                    clsExerc.setLevel(level+1, db);
                }
                else if (level ==5 && numExer ==5) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,1,'pt','Diária'),(2,1,'pt','Porteiro'),(3,1,'pt','Camareira'),(4,1,'pt','Gorjeta'),(5,1,'pt','Recepçao'),(6,1,'pt','quarto de solteiro'),(7, 1, 'pt', 'Mala'),(8,1,'pt','piscina'),(9, 1, 'pt', 'serviço de quarto'),(10,1,'pt','quarto de casal'), (11,1,'pt','Quanto foi?'), (12,1,'pt','Saída'), (13,1,'en','Passaporte'), (14,1,'en','Voo'), (15,1,'en','Banheiro'),"
                            + " (1,1,'en','Daily Rate'),(2,1,'en','Porter'),(3,1,'en','Chambermaid'),(4,1,'en','Tip'),(5,1,'en','Front Desk'),(6,1,'en','single room'),(7, 1, 'en', 'Suitcase'),(8,1,'en','swimming pool'),(9, 1, 'en', 'room service'),(10,1,'en','double room'), (11,1,'en','how much'), (12,1,'en','Exit'), (13,1,'en','Passaport'), (14,1,'en','Flight'), (15,1,'en','Rest Room')");
                    clsExerc.setLevel(level+1, db);
                }
                selecionarDados(db, numExer);

            }
        } catch (Exception e) {
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
