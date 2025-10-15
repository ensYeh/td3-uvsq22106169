package fr.uvsq.cprog.collex;

import static org.junit.Assert.*;

import org.junit.Test;

public class NomMachineTest {

    @Test(expected = IllegalArgumentException.class)
    public void test_nommachine_vide() {
        new NomMachine("", "uvsq.fr");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_domaine_vide() {
        new NomMachine("www", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_nom_domaine_vide() {
        new NomMachine("", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_nommachine_null() {
        new NomMachine(null, "uvsq.fr");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_domaine_null() {
        new NomMachine("www", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_nom_domaine_null() {
        new NomMachine(null, null);
    }

    @Test
    public void test_NomQualifie() {
        NomMachine nm = new NomMachine("www", "uvsq.fr");
        assertEquals("www.uvsq.fr", nm.getNomQualifie());
    }

    @Test
    public void test_equals_hashCode() {
        NomMachine nm1 = new NomMachine("www", "uvsq.fr");
        NomMachine nm2 = new NomMachine("www", "uvsq.fr");
        NomMachine nm3 = new NomMachine("poste", "uvsq.fr");

        assertEquals(nm1, nm2);
        assertEquals(nm1.hashCode(), nm2.hashCode());
        assertNotEquals(nm1, nm3);
    }

    @Test
    public void test_toString() {
        NomMachine nm = new NomMachine("www", "uvsq.fr");
        assertEquals("www.uvsq.fr", nm.toString());
    }

}