package boonedev.apptcc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class act_Airport extends AppCompatActivity {
public int level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__airport);
        final Button btnExercicio = (Button) findViewById(R.id.btnExercicio);
        final Button btnInfo = (Button) findViewById(R.id.btnInfo);
        btnExercicio.setVisibility(View.INVISIBLE);
        Bundle extras = getIntent().getExtras();
        level = extras.getInt("level");

        btnExercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnExercicioOnClick();
            }
        });
        TextView txt = (TextView) findViewById(R.id.textView);
        if(level == 0) {
            txt.setText(R.string.actAirportFirst);
        }

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnExercicio.setVisibility(View.VISIBLE);
            }
        });

    }

    public void btnExercicioOnClick() {
        Bundle bundle = new Bundle();
        bundle.putInt("level", level);
        Intent nextActivity = new Intent(this, act_Exercicio.class);
        nextActivity.putExtras(bundle);
        startActivity(nextActivity);
    }

}
