package com.example.android.carros_material;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Detalle_Carro extends AppCompatActivity {
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Carro p;
    private String placa,id;
    private int color,modelo,marca;
    private Bundle bundle;
    private TextView pla,col,mod,mar,prec;
    double precio;
    private Intent i;
    private int fot;
    private ImageView foto;
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle__carro);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        res = this.getResources();
        String [] modelo_string = res.getStringArray(R.array.array_madelo);
        String [] marca_string = res.getStringArray(R.array.array_marca);
        String [] color_string = res.getStringArray(R.array.array_color);
        collapsingToolbarLayout=(CollapsingToolbarLayout) findViewById(R.id.collapsingtoolbard);
        foto = (ImageView) findViewById(R.id.fotocarro);
        pla = (TextView) findViewById(R.id.txt_tbl_placa);
        col = (TextView) findViewById(R.id.txt_tbl_color);
        mod = (TextView) findViewById(R.id.txt_tbl_modelo);
        mar = (TextView) findViewById(R.id.txt_tbl_marca);
        prec = (TextView) findViewById(R.id.txt_tbl_precio);
        i = getIntent();
        bundle = i.getBundleExtra("datos");
        placa = bundle.getString("placa");
        precio = bundle.getDouble("precio");
        id = bundle.getString("id");
        color = bundle.getInt("color");
        System.out.println(color+"color detalle");
        modelo = bundle.getInt("modelo");
        marca = bundle.getInt("marca");
        fot = bundle.getInt("foto");

        pla.setText(placa);
        prec.setText(precio+"");
        mar.setText(marca_string[marca]);
        col.setText(color_string[color]);
        mod.setText(modelo_string[modelo]);
        foto.setImageDrawable(ResourcesCompat.getDrawable(res,fot,null));
        collapsingToolbarLayout.setTitle("Modelo: "+modelo_string[modelo]);
    }
    public void Eliminar(View v){
        String positivo,negativo;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(res.getString(R.string.titulo_eliminar_mensaje));
        builder.setMessage(res.getString(R.string.eliminar_mensaje));
        positivo = res.getString(R.string.si_eliminar_mensaje);
        negativo = res.getString(R.string.no_eliminar_mensaje);



        builder.setPositiveButton(positivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Carro p = new Carro(id);
                p.Eliminar();
                onBackPressed();

            }
        });
        builder.setNegativeButton(negativo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();



    }
    public void onBackPressed(){
        finish();
        Intent i = new Intent(Detalle_Carro.this,Principal.class);
        startActivity(i);
    }
    public void ModificarCarro(View v) {
        Intent i = new Intent(Detalle_Carro.this,Modificar.class);
        i.putExtra("datos",bundle);
        startActivity(i);


    }
}
