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
        btnIrParaExer.setBackground(getResources().getDrawable(R.drawable.lock));


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

                //FALTA TAXI

                //Hotel
                //Recepção
                 if (level == 4 && numExer == 4) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,1,'pt','Recepção'),(2,1,'pt','Cumprimento'),(3,1,'pt','Check-in'),(4,1,'pt','Requisitar'),(5,1,'pt','Forma de Pagamento'),(6,1,'pt','Formulário'),(7, 1, 'pt', 'Chamar o Carregador de Bagagem'),(8,1,'pt','Pegar a Bagagem'),(9, 1, 'pt', 'Pegar a Chave'),(10,1,'pt','subir as escadas')"
                            + " (1,1,'en','Reception'),(2,1,'en','Greeting'),(3,1,'en','Check-in'),(4,1,'en','Request'),(5,1,'en','Payment Form'),(6,1,'en','Forms'),(7, 1, 'en', 'Call the Bellboy'),(8,1,'en','Pick up the Luggage'),(9, 1, 'en', 'Pick up the Key'),(10,1,'en','Up Stairs')");
                    clsExerc.setLevel(level+1, db);
                }

                //Quarto
                if (level == 5 && numExer == 5) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,1,'pt','Quarto'),(2,1,'pt','Telefone'),(3,1,'pt','Cama'),(4,1,'pt','Banheiro'),(5,1,'pt','Travesseiro'),(6,1,'pt','Mesa'),(7, 1, 'pt', 'Ar Condicionado'),(8,1,'pt','Televisao'),(9, 1, 'pt', 'Espelho'),(10,1,'pt','Janela')"
                            + " (1,1,'en','Room'),(2,1,'en','Telefone'),(3,1,'en','Bed'),(4,1,'en','Bathroom'),(5,1,'en','Pillow'),(6,1,'en','Desk'),(7, 1, 'en', 'AC'),(8,1,'en','Television'),(9, 1, 'en', 'Mirror'),(10,1,'en','Window')");
                    clsExerc.setLevel(level+1, db);
                }

                //Restaurante
                if (level == 6 && numExer == 6) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,1,'pt','Restaurante'),(2,1,'pt','Cardapio'),(3,1,'pt','Mesa'),(4,1,'pt','Garfo'),(5,1,'pt','Faca'),(6,1,'pt','Colher'),(7, 1, 'pt', 'Chefe de Cozinha'),(8,1,'pt','Sobremesa'),(9, 1, 'pt', 'Vinho'),(10,1,'pt','Garçom')"
                            + " (1,1,'en','Restaurant'),(2,1,'en','Menu'),(3,1,'en','Table'),(4,1,'en','Fork'),(5,1,'en','Knife'),(6,1,'en','Spoon'),(7, 1, 'en', 'Chef'),(8,1,'en','Dessert'),(9, 1, 'en', 'Wine'),(10,1,'en','Waiter')");
                    clsExerc.setLevel(level+1, db);
                }
                //Escola
                //Secretaria

                if (level == 7 && numExer == 7) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,1,'pt','Folha'),(2,1,'pt','Tesoura'),(3,1,'pt','Apontador'),(4,1,'pt','Lousa'),(5,1,'pt','Giz'),(6,1,'pt','Livro'),(7, 1, 'pt', 'Apagador'),(8,1,'pt','Lápis'),(9, 1, 'pt', 'Caneta'),(10,1,'pt','Caderno')"
                            + " (1,1,'en','Leaf'),(2,1,'en','Scissors'),(3,1,'en','Sharpener'),(4,1,'en','Blackboard'),(5,1,'en','Chalk'),(6,1,'en','Book'),(7, 1, 'en', 'Eraser'),(8,1,'en','Pencil'),(9, 1, 'en', 'Pen'),(10,1,'en','Notebook')");
                    clsExerc.setLevel(level+1, db);
                }
                //	Matérias / Nome
                if (level == 8 && numExer == 8) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,1,'pt','Secretaria'),(2,1,'pt','Matematica'),(3,1,'pt','Fisica'),(4,1,'pt','Geografia'),(5,1,'pt','Arts'),(6,1,'pt','Logica'),(7, 1, 'pt', 'Quimica'),(8,1,'pt','Biologia'),(9, 1, 'pt', 'Filosofia'),(10,1,'pt','Diretor')"
                            + " (1,1,'en','Secretary'),(2,1,'en','Math'),(3,1,'en','Physics'),(4,1,'en','Geography'),(5,1,'en','Arts'),(6,1,'en','Logic'),(7, 1, 'en', 'Biology'),(8,1,'en','Chemistry'),(9, 1, 'en', 'Philosophy'),(10,1,'en','Principal')");
                    clsExerc.setLevel(level+1, db);
                }

                //Materiais/ Números
                if (level == 9 && numExer == 9) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,1,'pt','Um'),(2,1,'pt','Dois'),(3,1,'pt','Tres'),(4,1,'pt','Quatro'),(5,1,'pt','Cinco'),(6,1,'pt','Seis'),(7, 1, 'pt', 'Sete'),(8,1,'pt','Oito'),(9, 1, 'pt', 'Nove'),(10,1,'pt','Dez')"
                            + " (1,1,'en','One'),(2,1,'en','Two'),(3,1,'en','Three'),(4,1,'en','Four'),(5,1,'en','Five'),(6,1,'en','Six'),(7, 1, 'en', 'Seven'),(8,1,'en','Eight'),(9, 1, 'en', 'Nine'),(10,1,'en','Ten')");
                    clsExerc.setLevel(level+1, db);

                }
                //Hospital
                //Doenças (Nome de doenças)
                if (level == 10 && numExer == 10) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,1,'pt','Alergia'),(2,1,'pt','Bronquite'),(3,1,'pt','Câimbra'),(4,1,'pt','Gripe'),(5,1,'pt','Tosse'),(6,1,'pt','Dor de cabeça'),(7, 1, 'pt', 'Leucemia'),(8,1,'pt','Dor de Barriga'),(9, 1, 'pt', 'Febre'),(10,1,'pt','Dor de Garganta')"
                            + " (1,1,'en','Allergy'),(2,1,'en','Bronchitis'),(3,1,'en','Cramp'),(4,1,'en','Flu'),(5,1,'en','Couch'),(6,1,'en','Headache'),(7, 1, 'en', 'Leukemia'),(8,1,'en','Stomach Ache'),(9, 1, 'en', 'Fever'),(10,1,'en','Sore Throat')");
                    clsExerc.setLevel(level+1, db);
                }

                //Doutores/ (médico/enfermeiro. etc)
                if (level == 11 && numExer == 11) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,1,'pt','Medico'),(2,1,'pt','Enfermeira'),(3,1,'pt','Psicologo'),(4,1,'pt','Psiquiatra'),(5,1,'pt','Cirurgiao'),(6,1,'pt','Medicamentos'),(7, 1, 'pt', 'Neurologista'),(8,1,'pt','Pediatra'),(9, 1, 'pt', 'Anestesista'),(10,1,'pt','Residente')"
                            + " (1,1,'en','Doctor'),(2,1,'en','Nurse'),(3,1,'en','Psychologist'),(4,1,'en','Psychiatrist'),(5,1,'en','Surgeon'),(6,1,'en','Medicines'),(7, 1, 'en', 'Neurologist'),(8,1,'en','Pediatrician'),(9, 1, 'en', 'Anaesthetist'),(10,1,'en','Resident')");
                    clsExerc.setLevel(level+1, db);
                }

                //Remedios
                if (level == 12 && numExer == 12) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,1,'pt','Remédio de ervas'),(2,1,'pt','Remédio Geriátrico'),(3,1,'pt','Remédio Obstétrico'),(4,1,'pt','Remédio Pediátrico'),(5,1,'pt','Remédio Veterinário'),(6,1,'pt','Remédio Preventivo'),(7, 1, 'pt', 'Remédio Homeopáticos'),(8,1,'pt','Medicamentos da Saúde Pública'),(9, 1, 'pt', 'Bula de Remédio'),(10,1,'pt','Remédio')"
                            + " (1,1,'en','Herbal medicine'),(2,1,'en','Geriatric Medicine'),(3,1,'en','Obstetric Medicine'),(4,1,'en','Paediatric Medicine'),(5,1,'en','Veterinary Medicine'),(6,1,'en','Preventive Medicine'),(7, 1, 'en', 'Homeopathic Medicine'),(8,1,'en','Public Health Medicine'),(9, 1, 'en', 'Drug Leaflet'),(10,1,'en','Medicine')");
                    clsExerc.setLevel(level+1, db);
                }

                //Park
                if (level == 13 && numExer == 13) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,1,'pt','Campo'),(2,1,'pt','Churrasqueira'),(3,1,'pt','Fonte'),(4,1,'pt','Banco'),(5,1,'pt','Grama'),(6,1,'pt','Árvore'),(7, 1, 'pt', 'Vento'),(8,1,'pt','Parque Infantil'),(9, 1, 'pt', 'Bebedouro'),(10,1,'pt','Bicicleta')"
                            + " (1,1,'en','Field'),(2,1,'en','Fountain'),(3,1,'en','Bench'),(4,1,'en','Bank'),(5,1,'en','Grass'),(6,1,'en','Tree'),(7, 1, 'en', ''),(8,1,'en','Playground'),(9, 1, 'en', 'Drinking fountain),(10,1,'en','Bike')");
                    clsExerc.setLevel(level+1, db);
                }
                //Esportes
                if (level == 14 && numExer == 14) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,1,'pt','Atletismo'),(2,1,'pt','Boxe'),(3,1,'pt','Ciclismo'),(4,1,'pt','Futebol'),(5,1,'pt','Handebol'),(6,1,'pt','Por do ‘Ginastica Artistica'),(7, 1, 'pt', Xadrez),(8,1,'pt','Parque ‘Voleibol’),(9, 1, 'pt', 'Natação'),(10,1,'pt','Hipismo')"
                            + " (1,1,'en','Track and Field'),(2,1,'en','Boxing'),(3,1,'en','Cycling'),(4,1,'en','Soccer'),(5,1,'en','Handball'),(6,1,'en','Artistic Gymnastic'),(7, 1, 'en', 'Chess'),(8,1,'en','Volleyball'),(9, 1, 'en', 'Swimming'),(10,1,'en','Horse racing')");
                    clsExerc.setLevel(level+1, db);
                }

                //Animais
                if (level == 15 && numExer == 15) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,1,'pt','Abelha'),(2,1,'pt','Cachorro'),(3,1,'pt','Gato'),(4,1,'pt','Coelho'),(5,1,'pt','Borboleta'),(6,1,'pt','Por do ‘Besouro'),(7, 1, 'pt', ‘Macaco’),(8,1,'pt','Mosca’),(9, 1, 'pt', 'Pássaro'),(10,1,'pt','Pato')"
                            + " (1,1,'en','Track and ‘Bee'),(2,1,'en','Dog'),(3,1,'en','Cat'),(4,1,'en','Rabbit'),(5,1,'en','Butterfly'),(6,1,'en','Beetle'),(7, 1, 'en', 'Monkey'),(8,1,'en','Fly'),(9, 1, 'en', 'Bird'),(10,1,'en','Duck')");
                    clsExerc.setLevel(level+1, db);
                }
                //Mercado
                //As frutas
                if (level == 16 && numExer == 16) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,1,'pt','Maçã'),(2,1,'pt','Goiaba'),(3,1,'pt','Melão'),(4,1,'pt','Abacaxi'),(5,1,'pt','Uva'),(6,1,'pt','Pera'),(7, 1, 'pt', 'Morango'),(8,1,'pt','Manga'),(9, 1, 'pt', 'Limão'),(10,1,'pt','Laranja')"
                            + " (1,1,'en','Apple'),(2,1,'en','Guava'),(3,1,'en','Melon'),(4,1,'en','Pineapple'),(5,1,'en','Grape'),(6,1,'en','Pear'),(7, 1, 'en', 'Strawberry'),(8,1,'en','Mango'),(9, 1, 'en', 'Lime'),(10,1,'en','Orange')");
                    clsExerc.setLevel(level+1, db);
                }

                //Bebidas
                if (level == 17 && numExer == 17) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,1,'pt','Café'),(2,1,'pt','Chá'),(3,1,'pt','Leite'),(4,1,'pt','Iogurte'),(5,1,'pt','Suco'),(6,1,'pt','Água'),(7, 1, 'pt', 'Cerveja'),(8,1,'pt','Vinho'),(9, 1, 'pt', 'Refrigerante'),(10,1,'pt','Licor')"
                            + " (1,1,'en','Coffee'),(2,1,'en','Tea'),(3,1,'en','Milk'),(4,1,'en','Yogurte'),(5,1,'en','Juice'),(6,1,'en','Water'),(7, 1, 'en', 'Beer'),(8,1,'en','Wine'),(9, 1, 'en', 'Soda'),(10,1,'en','Liquer')");
                    clsExerc.setLevel(level+1, db);
                }

                //Comidas
                if (level == 18 && numExer == 18) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,1,'pt','Carne'),(2,1,'pt','Pao'),(3,1,'pt','Bolo'),(4,1,'pt','Batata'),(5,1,'pt','Torta'),(6,1,'pt','Macarrão'),(7, 1, 'pt', 'Peixe'),(8,1,'pt','Cenoura'),(9, 1, 'pt', 'Arroz'),(10,1,'pt','Manteiga')"
                            + " (1,1,'en','Meat'),(2,1,'en','Bread'),(3,1,'en','Cake'),(4,1,'en','Potato'),(5,1,'en','Pie'),(6,1,'en','Noodles'),(7, 1, 'en', 'Finsh'),(8,1,'en','Carrots'),(9, 1, 'en', 'Rice'),(10,1,'en','Butter')");
                    clsExerc.setLevel(level+1, db);
                }

                 //Empresa
                //Profissões
                if (level == 19 && numExer == 19) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,1,'pt','Advogado'),(2,1,'pt','Bombeiro'),(3,1,'pt','Padeiro'),(4,1,'pt','Motorista'),(5,1,'pt','Jardineiro'),(6,1,'pt','Garçom'),(7, 1, 'pt', 'Engenheiro'),(8,1,'pt','Professor'),(9, 1, 'pt', 'Fazendeiro'),(10,1,'pt','Cabeleireiro')"
                            + " (1,1,'en','Lawyer'),(2,1,'en','Fireman'),(3,1,'en','Baker'),(4,1,'en','Driver'),(5,1,'en','Gardener'),(6,1,'en','Waiter'),(7, 1, 'en', 'Eginner'),(8,1,'en','Teacher'),(9, 1, 'en', 'Farmer'),(10,1,'en','Hairdresser')");
                    clsExerc.setLevel(level+1, db);
                }

                //Equipamentos
                if (level == 20 && numExer == 20) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,1,'pt','Cronograma'),(2,1,'pt','Almoxarifado'),(3,1,'pt','Maquina'),(4,1,'pt','Lucro'),(5,1,'pt','Calculadora'),(6,1,'pt','Mesa de Escritório'),(7, 1, 'pt', 'Stapler'),(8,1,'pt','Pasta'),(9, 1, 'pt', 'Computador'),(10,1,'pt','Fio')"
                            + " (1,1,'en','Schedule'),(2,1,'en','Warehouse'),(3,1,'en','Machine'),(4,1,'en','Profits'),(5,1,'en','Caculator'),(6,1,'en','Desktop'),(7, 1, 'en', 'Stapler'),(8,1,'en','Folder'),(9, 1, 'en', 'Computer'),(10,1,'en','Wire')");
                    clsExerc.setLevel(level+1, db);
                }

                //Horas
                if (level == 21 && numExer == 21) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,1,'pt','8:05'),(2,1,'pt','02:10'),(3,1,'pt','06:15'),(4,1,'pt','03:20'),(5,1,'pt','09:25'),(6,1,'pt','01:30'),(7, 1, 'pt', '04:35'),(8,1,'pt','05:40'),(9, 1, 'pt', '07:45'),(10,1,'pt','10:50')"
                            + " (1,1,'en','Eight Five'),(2,1,'en','Two Ten'),(3,1,'en','Six Fifteen'),(4,1,'en','Three Twenty'),(5,1,'en','Nine Twenty-Five'),(6,1,'en','One Thirty'),(7, 1, 'en', 'Four-Thirty-Five'),(8,1,'en','Five Forty'),(9, 1, 'en', 'Seven Forty-Five'),(10,1,'en','Ten Fifty')");
                    clsExerc.setLevel(level+1, db);
                }

                //Shopping
                //Lojas (Nome de roupas)
                if (level == 22 && numExer == 22) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,1,'pt','Saia'),(2,1,'pt','Vestido'),(3,1,'pt','Casaco'),(4,1,'pt','Blusa'),(5,1,'pt','Calça'),(6,1,'pt','Camisa de manga'),(7, 1, 'pt', 'Jaqueta'),(8,1,'pt','Blusa de Frio'),(9, 1, 'pt', 'Bermuda'),(10,1,'pt','Gravata')"
                            + " (1,1,'en','Skirt'),(2,1,'en','Dress'),(3,1,'en','Coat'),(4,1,'en','Blouse'),(5,1,'en','Pants'),(6,1,'en','Shirt'),(7, 1, 'en', 'Jacket'),(8,1,'en','Sweater'),(9, 1, 'en', 'Shorts'),(10,1,'en','Tie')");
                    clsExerc.setLevel(level+1, db);
                }

                //Dinheiro (contando dinheiro e troco)
                if (level == 23 && numExer == 23) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,1,'pt','Troco'),(2,1,'pt','Centavos'),(3,1,'pt','Gorjeta'),(4,1,'pt','Fique com o Troco'),(5,1,'pt','Quanto Custa?'),(6,1,'pt','Barganha'),(7, 1, 'pt', 'Desconto'),(8,1,'pt','Cartão de Crédito'),(9, 1, 'pt', 'A Vista'),(10,1,'pt','Promocao')"
                            + " (1,1,'en','change'),(2,1,'en','Cents'),(3,1,'en','Tip'),(4,1,'en','Keep the Change'),(5,1,'en','How Much?'),(6,1,'en','Bargain'),(7, 1, 'en', 'Discount'),(8,1,'en','Credit Card'),(9, 1, 'en', 'In Cash'),(10,1,'en','Promotion')");
                    clsExerc.setLevel(level+1, db);
                }

                //Sapatos (Nome de sapatos)
                if (level == 24 && numExer == 24) { //Informacoes
                    db.execSQL("INSERT INTO "
                            + "tb_palavras"
                            + " (id, level, language, palavra)"
                            + " VALUES (1,1,'pt','Tênis'),(2,1,'pt','Sandálias'),(3,1,'pt','Chinelo'),(4,1,'pt','Botas'),(5,1,'pt','Salto Alto'),(6,1,'pt','Meia'),(7, 1, 'pt', 'Sapatilha'),(8,1,'pt','Sapatos'),(9, 1, 'pt', 'Polaina'),(10,1,'pt','Sapato Social')"
                            + " (1,1,'en','Sneakers'),(2,1,'en','Sandals'),(3,1,'en','Flip Flops'),(4,1,'en','Boots'),(5,1,'en','High Heels'),(6,1,'en','Sock'),(7, 1, 'en', 'Ballet Flats'),(8,1,'en','Shoes'),(9, 1, 'en', 'Gaiter'),(10,1,'en','Social Shoe')");
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