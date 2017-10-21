package com.example.android.carros_material;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Principal extends AppCompatActivity implements AdaptadorCarro.OnCarroClickListener  {
    private RecyclerView listado;
    private ArrayList<Carro> carros;
    private Resources res;
    private AdaptadorCarro adapter;
    private LinearLayoutManager llm;
    private Intent i;
    private DatabaseReference databaseReference;
    private final String BD= "carros";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        res = this.getResources();
        listado = (RecyclerView) findViewById(R.id.opciones);
        ArrayList<Integer>  fotos = new ArrayList<>();
        fotos.add(R.drawable.imagen1);
        fotos.add(R.drawable.imagen2);
        fotos.add(R.drawable.imagen3);
        //carros = datos.obtenerCarros();
        carros = new ArrayList<>();
        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new AdaptadorCarro(this.getApplicationContext(),carros,this);
        listado.setLayoutManager(llm);
        listado.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(BD).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                carros.clear();
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Carro p = snapshot.getValue(Carro.class);
                        carros.add(p);
                    }
                }
                adapter.notifyDataSetChanged();
                datos.setCarros(carros);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(Principal.this,Guardar_Carros.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCarroClick(Carro p) {
        Intent i = new Intent(Principal.this,Detalle_Carro.class);
        Bundle b = new Bundle();
        b.putInt("marca",p.getMarca());
        b.putInt("modelo",p.getModelo());
        b.putString("placa",p.getPlaca());
        b.putDouble("precio",p.getPrecio());
        b.putDouble("color",p.getPrecio());
        b.putInt("foto",p.getFoto());
        i.putExtra("datos",b);
        startActivity(i);

    }
}
