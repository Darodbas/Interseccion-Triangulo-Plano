package com.example.interseccintriangulo_plano;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

class Punto3D {

    protected float x, y, z;

    public Punto3D(){
        x=0;
        y=0;
        z=0;
    }
    public Punto3D(float x, float y, float z){
        this.x=x;
        this.y=y;
        this.z=z;
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public float getZ(){
        return z;
    }
    public void setX(float x){
        this.x=x;
    }
    public void setY(float y){
        this.y=y;
    }
    public void setZ(float z){
        this.z=z;
    }
}

class Solucion {

protected Punto3D p1,p2,p3;
protected int numPuntos;

    public Solucion() {
        p1 = new Punto3D();
        p1 = new Punto3D();
        p1 = new Punto3D();
        numPuntos = 3;
    }

    public Solucion (Punto3D pun1,Punto3D pun2,Punto3D pun3,int n ) {
    p1=pun1;
    p2=pun2;
    p3=pun3;
    numPuntos=n;
    }
    public int getN(){
        return numPuntos;
    }
    public Punto3D getP1(){
        return p1;
    }
    public Punto3D getP2(){
        return p2;
    }
    public Punto3D getP3(){
        return p3;
    }
}



public class MainActivity extends AppCompatActivity {

    protected EditText etV1x;
    protected EditText etV1y;
    protected EditText etV1z;
    protected EditText etV2x;
    protected EditText etV2y;
    protected EditText etV2z;
    protected EditText etV3x;
    protected EditText etV3y;
    protected EditText etV3z;
    protected EditText etPlano;
    protected Punto3D p1;
    protected Punto3D p2;
    protected Punto3D p3;
    protected float z;
    protected Solucion sol;

    protected Button btCalcular;

    protected TextView nPuntos;
    protected TextView punto1;
    protected TextView punto2;
    protected TextView punto3;
    protected int num;

    public Solucion calculaInterseccion(Punto3D p1,Punto3D p2,Punto3D p3,float z){

        int puntos=3;

        Punto3D p1r = new Punto3D();
        Punto3D p2r = new Punto3D();
        Punto3D p3r = new Punto3D();

        if((p1.getZ()>z && p2.getZ()>z)||(p1.getZ()<z && p2.getZ()<z)) {
            puntos-=1;
            p1r = new Punto3D(-1,-1,-1);

        }else if(p1.getZ()==z) {

            p1r=p1;

        }else{
                float l1 = (z - p1.getZ()) / (p1.getZ() - p2.getZ());
                p1r.setX(p1.getX()+l1*(p1.getX()-p2.getX()));
                p1r.setY(p1.getY()+l1*(p1.getY()-p2.getY()));
                p1r.setZ(p1.getZ()+l1*(p1.getZ()-p2.getZ()));
        }


        if((p2.getZ()>z && p3.getZ()>z)||(p2.getZ()<z && p3.getZ()<z)) {
            puntos-=1;
            p2r = new Punto3D(-1,-1,-1);
        }else if (p2.getZ()==z) {
            p2r=p2;
        }else{
            float l2 = (z - p2.getZ()) / (p2.getZ() - p3.getZ());
            p2r.setX(p2.getX()+l2*(p2.getX()-p3.getX()));
            p2r.setY(p2.getY()+l2*(p2.getY()-p3.getY()));
            p2r.setZ(p2.getZ()+l2*(p2.getZ()-p3.getZ()));
        }

        if((p1.getZ()>z && p3.getZ()>z)||(p1.getZ()<z && p3.getZ()<z)) {
            puntos-=1;
            p3r = new Punto3D(-1,-1,-1);
        }else if (p3.getZ()==z) {
            p3r=p3;
        }else{
            float l3 = (z - p1.getZ()) / (p1.getZ() - p3.getZ());
            p3r.setX(p1.getX()+l3*(p1.getX()-p3.getX()));
            p3r.setY(p1.getY()+l3*(p1.getY()-p3.getY()));
            p3r.setZ(p1.getZ()+l3*(p1.getZ()-p3.getZ()));
        }
        if(puntos == 3 && !(p1.getZ()==p2.getZ() && p2.getZ()==p3.getZ())){
            puntos =2;
        }
        return new Solucion(p1r,p2r,p3r,puntos);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        etV1x = findViewById(R.id.etV1x);
        etV1y = findViewById(R.id.etV1y);
        etV1z = findViewById(R.id.etV1z);
        etV2x = findViewById(R.id.etV2x);
        etV2y = findViewById(R.id.etV2y);
        etV2z = findViewById(R.id.etV2z);
        etV3x = findViewById(R.id.etV3x);
        etV3y = findViewById(R.id.etV3y);
        etV3z = findViewById(R.id.etV3z);
        etPlano = findViewById(R.id.etPlanoz);
        btCalcular = findViewById(R.id.btCalcular);
        nPuntos = findViewById(R.id.tvMostrarN);
        punto1 = findViewById(R.id.tvPunto1);
        punto2 = findViewById(R.id.tvPunto2);
        punto3 = findViewById(R.id.tvPunto3);


        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                punto1.setText("");
                punto2.setText("");
                punto3.setText("");
                nPuntos.setText("");


                if(etV1x.getText().length()>0 && etV1y.getText().length()>0 && etV1z.getText().length()>0 &&
                        etV2x.getText().length()>0 && etV2y.getText().length()>0 && etV2z.getText().length()>0 &&
                        etV3x.getText().length()>0 && etV3y.getText().length()>0 && etV3z.getText().length()>0 &&
                        etPlano.getText().length()>0) {


                    num = 0;
                    p1 = new Punto3D(Float.parseFloat(etV1x.getText().toString()), Float.parseFloat(etV1y.getText().toString()), Float.parseFloat(etV1z.getText().toString()));
                    p2 = new Punto3D(Float.parseFloat(etV2x.getText().toString()), Float.parseFloat(etV2y.getText().toString()), Float.parseFloat(etV2z.getText().toString()));
                    p3 = new Punto3D(Float.parseFloat(etV3x.getText().toString()), Float.parseFloat(etV3y.getText().toString()), Float.parseFloat(etV3z.getText().toString()));
                    z = Float.parseFloat(etPlano.getText().toString());
                    sol = new Solucion();
                    sol = calculaInterseccion(p1, p2, p3, z);



                    if (sol.getP1().getX() >= 0) {
                        punto1.setText("(" + sol.getP1().getX() + ", " + sol.getP1().getY() + ", " + sol.getP1().getZ() + ")");
                        num++;
                    }
                    if (sol.getP2().getX() >= 0 && ((sol.getP2().getX() != sol.getP1().getX()) || (sol.getP2().getY() != sol.getP1().getY()) || (sol.getP2().getZ() != sol.getP1().getZ()))) {
                        punto2.setText("(" + sol.getP2().getX() + ", " + sol.getP2().getY() + ", " + sol.getP2().getZ() + ")");
                        num++;
                    }
                    if (sol.getP3().getX() >= 0 && (((sol.getP3().getX() != sol.getP1().getX()) || (sol.getP3().getY() != sol.getP1().getY()) || (sol.getP3().getZ() != sol.getP1().getZ())) && ((sol.getP3().getX() != sol.getP2().getX()) || (sol.getP3().getY() != sol.getP2().getY()) || (sol.getP3().getZ() != sol.getP2().getZ())))) {
                        punto3.setText("(" + sol.getP3().getX() + ", " + sol.getP3().getY() + ", " + sol.getP3().getZ() + ")");
                        num++;
                    }
                    nPuntos.setText(Integer.toString(num));

                }else{
                    Context context = getApplicationContext();
                    CharSequence text = "Todos los campos deben rellenarse";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
    }
}