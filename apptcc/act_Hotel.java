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

public class act_Hotel extends AppCompatActivity {
    public int level;
    public int numExer; // Guarda o num. do exercicio
    public SQLiteDatabase db;

    @Override
    public void onResume()
    {
        super.onResume();
        final Button btnRestaur = (Button) findViewById(R.id.btnRestaur);
        final Button btnQuarto = (Button) findViewById(R.id.btnQuarto);

        final clsExercicios obj = new clsExercicios(getBaseContext());
        level = obj.getLevel(db);

        if(level == 5)
            btnQuarto.setBackgroundResource(R.drawable.ichotel2a);
        if(level ==6) {
            btnQuarto.setBackgroundResource(R.drawable.ichotel2a);
            btnRestaur.setBackgroundResource(R.drawable.ichotel3a);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_hotel);

        final Button btnExercicio = (Button) findViewById(R.id.btnExercicio);
        final Button btnVoltarGroup = (Button) findViewById(R.id.btnVoltarGroup);
        final Button btnTreino = (Button) findViewById(R.id.btnTreino);
        final Button btnRecepcao = (Button) findViewById(R.id.btnRecepcao);
        final Button btnQuarto = (Button) findViewById(R.id.btnQuarto);
        final Button btnRestaur = (Button) findViewById(R.id.btnRestaur);
        final ImageView rGroup = (ImageView) findViewById(R.id.rGroup);

        db = this.openOrCreateDatabase("database", MODE_PRIVATE, null);
        final clsExercicios obj = new clsExercicios(getBaseContext());
        level = obj.getLevel(db);

        if(level == 5)
            btnQuarto.setBackgroundResource(R.drawable.ichotel2a);
        if(level ==6) {
            btnQuarto.setBackgroundResource(R.drawable.ichotel2a);
            btnRestaur.setBackgroundResource(R.drawable.ichotel3a);
        }
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

        btnRecepcao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numExer = 4;
                btnExercicio.setVisibility(View.VISIBLE);
                btnTreino.setVisibility(View.VISIBLE);
                btnVoltarGroup.setVisibility((View.VISIBLE));
                rGroup.setVisibility(View.VISIBLE);
            }
        });
        btnQuarto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numExer = 5;
                level = obj.getLevel(db);
                if(level < numExer) {
                    Toast.makeText(getApplicationContext(), "Terminar nível anterior primeiro!", Toast.LENGTH_LONG).show();
                }else {
                    btnExercicio.setVisibility(View.VISIBLE);
                    btnTreino.setVisibility(View.VISIBLE);
                    btnVoltarGroup.setVisibility((View.VISIBLE));
                    rGroup.setVisibility(View.VISIBLE);
                }
            }
        });
        btnRestaur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numExer = 6;
                level = obj.getLevel(db);
                if(level < numExer) {
                    Toast.makeText(getApplicationContext(), "Terminar nível anterior primeiro!", Toast.LENGTH_LONG).show();
                }else {
                    btnExercicio.setVisibility(View.VISIBLE);
                    btnTreino.setVisibility(View.VISIBLE);
                    btnVoltarGroup.setVisibility((View.VISIBLE));
                    rGroup.setVisibility(View.VISIBLE);
                }
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
