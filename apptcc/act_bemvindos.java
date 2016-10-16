package boonedev.apptcc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

/**
 * Created by thain on 15/10/2016.
 */

public class act_bemvindos extends AppCompatActivity {
 protected static final int TIMER_RUNTIME = 10000;

    protected boolean mbActive; //Se a barra ta ativa
    protected ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_bemvindos);

        mProgressBar = (ProgressBar)findViewById(R.id.pbBarra);

        //Criar atividade, cria run para executar
        final Thread timerThread = new Thread(){

            @Override
              public void run (){
                mbActive = true;
                try {
                    int waited = 0;
                    while(mbActive && (waited < TIMER_RUNTIME)){
                        sleep(200);
                        if (mbActive){
                            waited += 200;
                            updateProgress(waited);
                        }
                    }
                } catch (InterruptedException e){
                    //Carro de erro!!
                } finally {
                    onContinue();
                }
            }

        };
        timerThread.start();

    }


        public void updateProgress(final int timePassed) {
            if (null !=mProgressBar){
                final int progress = mProgressBar.getMax() * timePassed / TIMER_RUNTIME;
                mProgressBar.setProgress(progress);
            }
        }
     public  void onContinue(){

     //chamar tela inicial





         }
}
