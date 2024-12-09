package com.example.test0.vue;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.test0.R;
import com.example.test0.controleur.Controle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        //this.controle=Controle.getInst();
    }
    private EditText txtPoids;
    private EditText txtTaille;
    private EditText txtAge;
    private RadioButton rdHomme;
    private RadioButton rdFemme;
    private TextView LBLIMG;
    private ImageView imagesmily;
    private Controle controle;

    /**
     * initialisation des liens avec les objets graphiques
     */
    private void init(){
        txtPoids=(EditText)findViewById(R.id.txtPoids);
        txtTaille=(EditText)findViewById(R.id.txtTaille);
        txtAge=(EditText)findViewById(R.id.txtAge);
        rdHomme=(RadioButton) findViewById(R.id.rdHomme);
        rdFemme=(RadioButton) findViewById(R.id.rdFemme);
        LBLIMG=(TextView)findViewById(R.id.LBLIMG);
        imagesmily=(ImageView) findViewById(R.id.imagesmily);
        this.controle=Controle.getInst(this);
        ecoutecalcul();
        recupProfil();
    }

    /**
     * ecoute event syr boutton calcul
     */
    private void ecoutecalcul(){
        Button btnCalc = (Button) findViewById(R.id.btncalc);
        btnCalc.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
                Integer poids=0;
                Integer taille=0;
                Integer age=0;
                Integer sexe=0;
                //rec donnee saisie
                try {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                }catch (Exception e){};
                if(rdHomme.isChecked()){
                    sexe=1;
                }
                //controle donnee saisie
                if(poids==0||taille==0||age==0){
                    Toast.makeText(MainActivity.this, "Sasie incorrecte", Toast.LENGTH_SHORT).show();
                }else{
                    afficheResult(poids,taille,age,sexe);
                }
            }
        });

    }

    /**
     * affichage de limage
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    private void afficheResult(Integer poids,Integer taille,Integer age,Integer sexe){
        this.controle.creeProfil(poids,taille,age,sexe,this);
        float img=this.controle.getImg();
        String message=this.controle.getMessage();
        //affichage
        if(message=="normal"){
            imagesmily.setImageResource(R.drawable.normal);
            LBLIMG.setTextColor(Color.GREEN);
        }else{
            LBLIMG.setTextColor(Color.RED);
            if(message=="trop faible"){
                imagesmily.setImageResource(R.drawable.maigre);
            }else{
                imagesmily.setImageResource((R.drawable.graisse));
            }
        }
        LBLIMG.setText(String.format("%.01f",img)+" :IMG "+message);

    }
    private void recupProfil(){
        if(controle.getPoids()!=null){
            txtPoids.setText(controle.getPoids().toString());
            txtTaille.setText(controle.getTaille().toString());
            txtAge.setText(controle.getAge().toString());
            rdFemme.setChecked(true);
            if(controle.getSexe()==1){
                rdHomme.setChecked(true);
            }
            ((Button)findViewById(R.id.btncalc)).performClick();
        }
    }
}