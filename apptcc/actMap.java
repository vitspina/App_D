package boonedev.apptcc;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class actMap extends AppCompatActivity {
    User usu = new User();
    private int level = 0;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_map);

        level = usu.getLevel();
         if (level == 0) {
             Toast.makeText(getApplicationContext(), "Vamos começar nossa diversao pelo aeroporto!", Toast.LENGTH_LONG).show();
         }

        db = this.openOrCreateDatabase("database", MODE_PRIVATE, null);

        Button btnAirport = (Button) findViewById(R.id.btnAirport);
        //Button btnRua = (Button) findViewById(R.id.btnRua);
        Button btnHotel = (Button) findViewById(R.id.btnHotel);
        ImageView imgStarAirp = (ImageView) findViewById(R.id.imgAirpStars);
        btnAirport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAirportOnClick();
            }
        });

        clsExercicios classeExerc = new clsExercicios(this.getBaseContext());
        int aux;
        aux = classeExerc.getScoreTotal(db, 1);
        switch (aux){
            case 0:
            case 1:
            imgStarAirp.setBackground(getResources().getDrawable(R.drawable.stars0_3));
            case 2:
                imgStarAirp.setBackground(getResources().getDrawable(R.drawable.stars1_2));
        }

    }

    public void btnAirportOnClick() {
        Bundle bundle = new Bundle();
        bundle.putInt("level", level);
        Intent nextActivity = new Intent(this, act_Airport.class);
        nextActivity.putExtras(bundle);
        startActivity(nextActivity);
    }

    @Override
    public void onBackPressed() {
        Intent it = new Intent(this, tela_inicial.class);
        startActivity(it);
    }
}
