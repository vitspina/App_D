package boonedev.apptcc;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class act_shopping extends AppCompatActivity {
    public int level;
    public int numExer; // Guarda o num. do exercicio
    public SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_shopping);
        final Button btnExercicio = (Button) findViewById(R.id.btnExercicio);
        final Button btnVoltarGroup = (Button) findViewById(R.id.btnVoltarGroup);
        final Button btnTreino = (Button) findViewById(R.id.btnTreino);
        final Button btnInfo = (Button) findViewById(R.id.btnInfo);
        final ImageView rGroup = (ImageView) findViewById(R.id.rGroup);

        db = this.openOrCreateDatabase("database", MODE_PRIVATE, null);
        final clsExercicios obj = new clsExercicios(getBaseContext());
        level = obj.getLevel(db);

        //Apaga os botoes da tela
        btnExercicio.setVisibility(View.INVISIBLE);
        btnTreino.setVisibility(View.INVISIBLE);
        btnVoltarGroup.setVisibility((View.INVISIBLE));
        rGroup.setVisibility(View.INVISIBLE);


        Bundle extras = getIntent().getExtras();
        level = extras.getInt("level");

        btnVoltarGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnExercicio.setVisibility(View.INVISIBLE);
                btnTreino.setVisibility(View.INVISIBLE);
                btnVoltarGroup.setVisibility((View.INVISIBLE));
                rGroup.setVisibility(View.INVISIBLE);
            }
        });


        btnExercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnExercicioOnClick();
            }
        });
        btnTreino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnTreinoOnClick();
            }
        });
        TextView txt = (TextView) findViewById(R.id.textView);


        if(level == 0) {
            Toast.makeText(getApplicationContext(), "Vamos aprender palavras necessarias!", Toast.LENGTH_LONG).show();
        }

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numExer = 1; //Clicou no botao Informacoes
                btnExercicio.setVisibility(View.VISIBLE);
                btnTreino.setVisibility(View.VISIBLE);
                btnVoltarGroup.setVisibility((View.VISIBLE));
                rGroup.setVisibility(View.VISIBLE);
            }
        });

    }

    public void btnExercicioOnClick() {
        Bundle bundle = new Bundle();
        bundle.putInt("level", level);
        bundle.putInt("exerc", numExer);
        Intent nextActivity = new Intent(this, act_Exercicio.class);
        nextActivity.putExtras(bundle);
        startActivity(nextActivity);
    }
    public void btnTreinoOnClick(){
        Bundle bundle = new Bundle();
        bundle.putInt("level", level);
        bundle.putInt("exerc", numExer);
        Intent nextActivity = new Intent(this, act_Treino.class);
        nextActivity.putExtras(bundle);
        startActivity(nextActivity);
    }
}
