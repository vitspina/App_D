package boonedev.apptcc;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class tela_inicial extends AppCompatActivity {
    public String i = "";
    public int lvl = 0;
    public String id = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        ConfigBanco();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        Button btn = (Button) findViewById(R.id.btn1Menu);
        btn.setText(i);//TODO:lembrar de apagar
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1OnClick();
            }
        });

    }

    public void btn1OnClick() {
        if (lvl == 0) {
            Intent nextActivity = new Intent(this, EscolherIdioma.class);
            startActivity(nextActivity);
        } else {
            Intent nextActivity = new Intent(this, actMap.class);
            startActivity(nextActivity);
        }
    }

    private void ConfigBanco() {
        SQLiteDatabase db;
            db = this.openOrCreateDatabase("database", MODE_PRIVATE, null);

   /* Create a Table in the Database. */
            db.execSQL("CREATE TABLE IF NOT EXISTS "
                    + "usu_info"
                    + " (id INT(1), level INT(3), language VARCHAR);");

            if (selecionarDados(db)) {
                if(lvl == 0){
                    i = "INICIAR- "+ id + " - " + lvl + " - " + i;
                }else//TODO:lembrar de apagar
                {
                    i = "CONTINUAR- "+ id + " - " + lvl + " - " + i;
                }
            }else{
                   /* Insert data to a Table*/
                db.execSQL("INSERT INTO "
                        + "usu_info"
                        + " (id, level, language)"
                        + " VALUES (1, 0, 'none');");

                selecionarDados(db);
            }
    }

    private boolean selecionarDados(SQLiteDatabase db){
        /*retrieve data from database */
        Cursor c = db.rawQuery("SELECT * FROM usu_info", null);
        c.moveToFirst();
        if(c.moveToFirst()){
            id = c.getString(0);
            lvl = c.getInt(1);
            i  = c.getString(2);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}