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
    String aux;

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        final Button btnIcHotel = (Button) findViewById(R.id.btnIcHotel);
        final Button btnIcEscola = (Button) findViewById(R.id.btnIcEscola);
        final Button btnIcParque = (Button) findViewById(R.id.btnIcParque);
        final Button btnIcEmpresa = (Button) findViewById(R.id.btnIcEmpresa);
        final Button btnIcMerc = (Button) findViewById(R.id.btnIcMerc);
        final Button btnIcHosp = (Button) findViewById(R.id.btnIcHosp);

        final clsExercicios obj = new clsExercicios(getBaseContext());
        level = obj.getLevel(db);
        if (level == 0) {
            Toast.makeText(getApplicationContext(), "Vamos começar nossa diversao pelo aeroporto!", Toast.LENGTH_LONG).show();
        }
        if(level >= 4 && level <= 6){
            btnIcHotel.setBackgroundResource(R.drawable.ichotel);
            btnIcHotel.setTag("ok");
        }
        if(level >=7 && level <=12){
            btnIcHotel.setBackgroundResource(R.drawable.ichotel);
            btnIcHotel.setTag("ok");
            btnIcEscola.setBackgroundResource(R.drawable.icescola);
            btnIcEscola.setTag("ok");
            btnIcHosp.setBackgroundResource(R.drawable.ichosp);
            btnIcHosp.setTag("ok");
        }
        if(level >=13 && level <=15){
            btnIcHotel.setBackgroundResource(R.drawable.ichotel);
            btnIcHotel.setTag("ok");
            btnIcEscola.setBackgroundResource(R.drawable.icescola);
            btnIcEscola.setTag("ok");
            btnIcHosp.setBackgroundResource(R.drawable.ichosp);
            btnIcHosp.setTag("ok");
            btnIcParque.setBackgroundResource(R.drawable.icparque);
            btnIcParque.setTag("ok");
        }
        if(level >= 16 && level <=18){
            btnIcHotel.setBackgroundResource(R.drawable.ichotel);
            btnIcHotel.setTag("ok");
            btnIcEscola.setBackgroundResource(R.drawable.icescola);
            btnIcEscola.setTag("ok");
            btnIcHosp.setBackgroundResource(R.drawable.ichosp);
            btnIcHosp.setTag("ok");
            btnIcParque.setBackgroundResource(R.drawable.icparque);
            btnIcParque.setTag("ok");
            btnIcMerc.setBackgroundResource(R.drawable.icmerc);
            btnIcMerc.setTag("ok");
        }
        if(level >=19)
        {
            btnIcHotel.setBackgroundResource(R.drawable.ichotel);
            btnIcHotel.setTag("ok");
            btnIcEscola.setBackgroundResource(R.drawable.icescola);
            btnIcEscola.setTag("ok");
            btnIcHosp.setBackgroundResource(R.drawable.ichosp);
            btnIcHosp.setTag("ok");
            btnIcParque.setBackgroundResource(R.drawable.icparque);
            btnIcParque.setTag("ok");
            btnIcMerc.setBackgroundResource(R.drawable.icmerc);
            btnIcMerc.setTag("ok");
            btnIcEmpresa.setBackgroundResource(R.drawable.icempresa);
            btnIcEmpresa.setTag("ok");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_map);
        db = this.openOrCreateDatabase("database", MODE_PRIVATE, null);
        level = usu.getLevel(db);

        Button btnIcAirp = (Button) findViewById(R.id.btnicairp);
        final Button btnIcHotel = (Button) findViewById(R.id.btnIcHotel);
        final Button btnIcEscola = (Button) findViewById(R.id.btnIcEscola);
        final Button btnIcParque = (Button) findViewById(R.id.btnIcParque);
        final Button btnIcEmpresa = (Button) findViewById(R.id.btnIcEmpresa);
        final Button btnIcMerc = (Button) findViewById(R.id.btnIcMerc);
        final Button btnIcHosp = (Button) findViewById(R.id.btnIcHosp);
        Button btnAirport = (Button) findViewById(R.id.btnAirport);
        Button btnHotel = (Button) findViewById(R.id.btnHotel);
        Button btnEscola = (Button) findViewById(R.id.btnescola);
        //ImageView imgStarAirp = (ImageView) findViewById(R.id.imgAirpStars);

        btnIcHotel.setTag("locked");
        btnIcEscola.setTag("locked");
        btnIcParque.setTag("locked");
        btnIcEmpresa.setTag("locked");
        btnIcMerc.setTag("locked");
        btnIcHosp.setTag("locked");
        //Botões
        btnAirport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAirportOnClick();
            }
        });
        btnIcAirp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAirportOnClick();
            }
        });
        btnHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aux = btnIcHotel.getTag().toString();
                btnHotelOnClick(aux);
            }
        });
        btnIcHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aux = btnIcHotel.getTag().toString();
                btnHotelOnClick(aux);
            }
        });
        btnIcEscola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aux = btnIcEscola.getTag().toString();
                btnEscolaOnClick(aux);
            }
        });
        btnEscola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aux = btnIcEscola.getTag().toString();
                btnEscolaOnClick(aux);
            }
        });



        if (level == 0) {
            Toast.makeText(getApplicationContext(), "Vamos começar nossa diversao pelo aeroporto!", Toast.LENGTH_LONG).show();
        }
        if(level >= 4 && level <= 6){
            btnIcHotel.setBackgroundResource(R.drawable.ichotel);
            btnIcHotel.setTag("ok");
        }
        if(level >=7 && level <=12){
            btnIcEscola.setBackgroundResource(R.drawable.icescola);
            btnIcEscola.setTag("ok");
            btnIcHosp.setBackgroundResource(R.drawable.ichosp);
            btnIcHosp.setTag("ok");
        }
        if(level >=13 && level <=15){
            btnIcParque.setBackgroundResource(R.drawable.icparque);
            btnIcParque.setTag("ok");
        }
        if(level >= 16 && level <=18){
            btnIcMerc.setBackgroundResource(R.drawable.icmerc);
            btnIcMerc.setTag("ok");
        }
        if(level >=19)
        {
            btnIcEmpresa.setBackgroundResource(R.drawable.icempresa);
            btnIcEmpresa.setTag("ok");
        }

        clsExercicios classeExerc = new clsExercicios(this.getBaseContext());
        int aux;
        aux = classeExerc.getScoreTotal(db, 1);
        /*switch (aux){
            case 0:
            case 1:
            imgStarAirp.setBackground(getResources().getDrawable(R.drawable.stars0_3));
            case 2:
                imgStarAirp.setBackground(getResources().getDrawable(R.drawable.stars1_2));
        }*/

    }
    public void btnAirportOnClick() {
        Bundle bundle = new Bundle();
        bundle.putInt("level", level);
        Intent nextActivity = new Intent(this, act_Airport.class);
        nextActivity.putExtras(bundle);
        startActivity(nextActivity);
    }
    public void btnHotelOnClick(String valida){
        if(valida == "ok"){
            Bundle bundle = new Bundle();
            bundle.putInt("level", level);
            Intent nextActivity = new Intent(this, act_Hotel.class); //HOTEL
            nextActivity.putExtras(bundle);
            startActivity(nextActivity);
        }else{
            Toast.makeText(getApplicationContext(), "Para acessar, termine as atividades no aeroporto !", Toast.LENGTH_LONG).show();
        }
    }
    public void btnEscolaOnClick(String valida){
        if(valida == "ok"){
            Bundle bundle = new Bundle();
            bundle.putInt("level", level);
            Intent nextActivity = new Intent(this, act_Escola.class); //HOTEL
            nextActivity.putExtras(bundle);
            startActivity(nextActivity);
        }else{
            Toast.makeText(getApplicationContext(), "Para acessar, termine as atividades no Hotel !", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent it = new Intent(this, tela_inicial.class);
        startActivity(it);
    }
}
