package com.example.test0.modele;

import junit.framework.TestCase;

public class ProfilTest extends TestCase {

    private Profil profil=new Profil(165,67,35,0);
    private float img=(float) 32.2;
    private String message="trop eleve";

    public void testGetImg() {
        assertEquals(img,profil.getImg(),(float) 0.1);
    }

    public void testGetMessage() {
        assertEquals(message,profil.getMessage());
    }
}