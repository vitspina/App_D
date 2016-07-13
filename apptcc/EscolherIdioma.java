package boonedev.apptcc;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class EscolherIdioma extends AppCompatActivity {
public String lang = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolher_idioma);

        Button btnEN = (Button)findViewById(R.id.btnIngles) ;
        Button btnPT = (Button)findViewById(R.id.btnPortugues) ;
        Button btnES = (Button)findViewById(R.id.btnEspanhol) ;

        btnEN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lang = "EN";
                NextAct(lang);
            }
        });
        btnPT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lang = "PT";
        //        NextAct(lang);
            }
        });
        btnES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lang = "ES";
        //        NextAct(lang);
            }
        });

    }
    private void NextAct(String langu){
        salvaInfo(langu);
        Intent nextActivity = new Intent(this, actMap.class);
        startActivity(nextActivity);
        this.closeContextMenu();
    }

    private void salvaInfo(String lan){

        SQLiteDatabase db;
        db = this.openOrCreateDatabase("database", MODE_PRIVATE, null);
        db.execSQL("UPDATE  "
                + "usu_info"
                + " set language = '"+ lan +"', "
                + "     level = 1;");
    }

}
