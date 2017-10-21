package com.example.android.carros_material;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by android on 21/10/2017.
 */

public class datos {
    private static String db ="carros";
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private static ArrayList<Carro> carros = new ArrayList();

    public static void guardarCarros(Carro p) {
       // carros.add(p);
       p.setId(databaseReference.push().getKey());
        databaseReference.child(db).child(p.getId()).setValue(p);

    }

    public static void setCarros(ArrayList<Carro> carros) {
        datos.carros = carros;
    }

    public static ArrayList<Carro> obtenerCarros() {
        return carros;
    }
    public static int fotoAleatoria(ArrayList<Integer> fotos){
        int fotoSel;
        Random r = new Random();
        fotoSel = r.nextInt(fotos.size());
        return fotos.get(fotoSel);
    }
/*
    public static void setPersonas(ArrayList<Persona> per){
        personas=per;
    }

    public static void eliminarPersona(Persona p){
        databaseReference.child(db).child(p.getId()).removeValue();

    }
    public static int ExistePersonaIndex(String cedula){
        Log.i("TEST",""+personas.size());
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getCedula().equals(cedula)){
                return i;
            }
        }
        return -1;

    }
    public static void Modificar(Persona p) {
        databaseReference.child(db).child(p.getId()).setValue(p);
    }
*/
}
