package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

   Button [][] buttons= new Button[3][3];

   boolean playerXTurn =true;

   int roundCount=0;

   int playerXPoints =0, playerOPoints =0;

   TextView textViewPlayerX, textViewPlayerO ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defination();

        onclick();

    }

    private void defination(){
        textViewPlayerX =findViewById(R.id.playerX_tx);
        textViewPlayerO =findViewById(R.id.playerO_tx);
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                String buttonId="button_"+i+j;
                int resID=getResources().getIdentifier(buttonId,"id",getPackageName());
                buttons[i][j]=findViewById(resID);
            }
        }
    }

    public void reset(View view) {
      reset();
    }

    private void reset(){
        if(roundCount>0){
            resetButtons();
            roundCount=0;
            playerXTurn =true;
        }else {
            if(playerXPoints>playerOPoints){
                Toast.makeText(MainActivity.this,"the winner is player X",Toast.LENGTH_LONG).show();
            }
            else if(playerXPoints<playerOPoints){
                Toast.makeText(MainActivity.this,"the winner is player O",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(MainActivity.this,"There is no winning player",Toast.LENGTH_LONG).show();
            }
            textViewPlayerX.setText("Player X: 0");
            textViewPlayerO.setText("Player O: 0");
            playerXPoints =0;
            playerOPoints =0;
        }
    }

    private void onclick(){
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Button button=((Button) v);
                        if(roundCount<9){
                            if(playerXTurn){
                                button.setText("X");
                                button.setClickable(false);
                                if( checkTheWinner("X")){
                                    Toast.makeText(MainActivity.this,"the winner is player X",Toast.LENGTH_LONG).show();
                                    roundCount=0;
                                    playerXPoints++;
                                    resetButtons();
                                    textViewPlayerX.setText("Player X: "+ playerXPoints);
                                }
                                else {
                                    playerXTurn =false;
                                    roundCount++;
                                }
                            }
                            else {
                                button.setText("O");
                                button.setClickable(false);
                                if(checkTheWinner("O")){
                                    Toast.makeText(MainActivity.this,"the winner is player O",Toast.LENGTH_LONG).show();
                                    roundCount=0;
                                    playerOPoints++;
                                    resetButtons();
                                    textViewPlayerO.setText("Player O: "+ playerOPoints);
                                    playerXTurn =true;
                                }
                               else {
                                    playerXTurn =true;
                                    roundCount++;
                                }
                            }

                        }
                        if(roundCount==9) {
                            Toast.makeText(MainActivity.this,"There is no winning player",Toast.LENGTH_LONG).show();
                            roundCount=0;
                            resetButtons();
                            playerXTurn =true;
                        }
                         }
                });
            }
        }
    }

    private boolean checkTheWinner(String player){
        for (int i=0;i<3;i++){
            int counter=0;
            for (int j=0;j<3;j++){
                if(buttons[i][j].getText().toString().equals(player)){
                    counter++;
                }
            }
            if(counter==3){
                return true;
            }
        }

        for (int i=0;i<3;i++){
            int counter=0;
            for (int j=0;j<3;j++){
                if(buttons[j][i].getText().toString().equals(player)){
                    counter++;
                }
            }
            if(counter==3){
                return true;
            }
        }

        if((buttons[0][0].getText().toString().equals(player))&&(buttons[1][1].getText().toString().equals(player))&&
                (buttons[2][2].getText().toString().equals(player))){
                  return true;
        }

        if((buttons[0][2].getText().toString().equals(player))&&(buttons[1][1].getText().toString().equals(player))&&
                (buttons[2][0].getText().toString().equals(player))){
            return true;
        }

        return false;
    }

    private void resetButtons(){
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                buttons[i][j].setText("");
                buttons[i][j].setClickable(true);
            }
        }
    }
}
