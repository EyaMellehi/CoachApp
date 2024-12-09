package com.example.test0.controleur;

import android.content.Context;

import com.example.test0.modele.AccesLocal;
import com.example.test0.modele.Profil;
import com.example.test0.outils.Serializer;

import java.util.Date;

public final class Controle {
    private static Controle inst=null;
    private static Profil profil;
    private static String nomFic="saveprofil";
    private static AccesLocal accesLocal;

    /**
     * constructeur privee
     */
    private  Controle(){
        super();
    }
    /**
     * creation de l'instance
     * @return inst
     */
    public static final Controle getInst(Context context){
        if(Controle.inst==null){
            Controle.inst=new Controle();
            accesLocal=new AccesLocal(context);
            profil = accesLocal.recupDernier();
            //recupSerialize(context);

        }
        return Controle.inst;
    }

    /**
     * cretation du profil
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe :1pour homme et 0 pour femme
     */
    public void creeProfil(Integer poids, Integer taille, Integer age, Integer sexe, Context context){
        profil=new Profil(new Date(), poids,taille,age,sexe);
        accesLocal.ajout(profil);
        //Serializer.serializer(nomFic,profil,context);
    }

    /**
     * recuperation img du profil
     * @return
     */
    public float getImg(){
        return profil.getImg();
    }

    /**
     * recuperation message profil
     * @return
     */
    public String getMessage(){
        return profil.getMessage();
    }
    private static void  recupSerialize(Context context){
        profil=(Profil) Serializer.deSerialize(nomFic,context);
    }
    public Integer getPoids(){
        if(profil==null){
            return null;
        }
        else{
            return profil.getPoids();
        }
    }
    public Integer getTaille(){
        if(profil==null){
            return null;
        }
        else{
            return profil.getTaille();
        }
    }
    public Integer getAge(){
        if(profil==null){
            return null;
        }
        else{
            return profil.getAge();
        }
    }
    public Integer getSexe(){
        if(profil==null){
            return null;
        }
        else{
            return profil.getSexe();
        }
    }
}
