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

        Button btn1Menu = (Button) findViewById(R.id.btn1Menu);

        btn1Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1MenuOnClick();
            }
        });

    }

        public void btn1MenuOnClick() {
            Bundle bundle = new Bundle();
            bundle.putInt("level", lvl);
            Intent nextActivity = new Intent(this, EscolherIdioma.class);
            nextActivity.putExtras(bundle);
            startActivity(nextActivity);
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

            db.execSQL("CREATE TABLE IF NOT EXISTS "
                    + "usu_inflvl"
                    + " (level INT(3), treino INT(3), ex1 INT(3));");

        Cursor c = db.rawQuery("SELECT level FROM usu_inflvl", null);
        if(c.moveToFirst()){

        }
        else{
            db.execSQL("INSERT INTO "
                    + "usu_inflvl"
                    + " (level, treino, ex1)"
                    + " VALUES (1,0,0), (2,0,0), (3,0,0), (4,0,0),(5,0,0), (6,0,0),"
                    + "(7,0,0), (8,0,0), (9,0,0), (10,0,0),(11,0,0), (12,0,0);");
        }



            if (selecionarDados(db)) {
                if(lvl == 0){
                    i = "INICIAR- "+ id + " - " + lvl + " - " + i;
                }
            }else{
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