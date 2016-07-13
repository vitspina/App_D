package boonedev.apptcc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class actMap extends AppCompatActivity {
    User usu = new User();
    private int level = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_map);

        level = usu.getLevel();

        Button btnAirport = (Button) findViewById(R.id.btnAirport);
        Button btnRua = (Button) findViewById(R.id.btnRua);
        Button btnHotel = (Button) findViewById(R.id.btnHotel);

        btnAirport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAirportOnClick();
            }
        });

    }

    public void btnAirportOnClick() {
        Bundle bundle = new Bundle();
        bundle.putInt("level", level);
        Intent nextActivity = new Intent(this, act_Exercicio.class);
        nextActivity.putExtras(bundle);
        startActivity(nextActivity);
    }

    @Override
    public void onBackPressed() {
        Intent it = new Intent(this, tela_inicial.class);
        startActivity(it);
    }
}
