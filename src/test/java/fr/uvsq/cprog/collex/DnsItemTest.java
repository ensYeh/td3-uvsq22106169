package fr.uvsq.cprog.collex;

import static org.junit.Assert.*;

import org.junit.Test;

public class DnsItemTest {
    
    @Test(expected = IllegalArgumentException.class) 
    public void test_nommachine_null(){
        new DnsItem(null,new AdresseIP("193.51.31.90"));    
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_adresseIP_null() {
        new DnsItem(new NomMachine("www", "uvsq.fr"), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_adresseIP_NomMachine_null() {
        new DnsItem(null, null);
    }

    @Test
    public void test_getters() {
        NomMachine nm = new NomMachine("www", "uvsq.fr");
        AdresseIP ip = new AdresseIP("193.51.31.90");
        DnsItem item = new DnsItem(nm,ip);

        assertEquals(nm, item.getNomMachineobj());
        assertEquals(ip, item.getAdresseIpobj());
    }

    @Test
    public void test_toString() {
        NomMachine nm = new NomMachine("www", "uvsq.fr");
        AdresseIP ip = new AdresseIP("193.51.31.90");
        DnsItem item = new DnsItem(nm, ip);

        assertEquals("193.51.31.90 www.uvsq.fr", item.toString());
    }

    @Test
    public void test_equals_hashCode() {
        DnsItem item1 = new DnsItem(new NomMachine("www", "uvsq.fr"), new AdresseIP("193.51.31.90"));
        DnsItem item2 = new DnsItem(new NomMachine("www", "uvsq.fr"), new AdresseIP("193.51.31.90"));
        DnsItem item3 = new DnsItem(new NomMachine("poste", "uvsq.fr"), new AdresseIP("193.51.31.90"));

        assertEquals(item1, item2);
        assertEquals(item1.hashCode(), item2.hashCode());
        assertNotEquals(item1, item3);
    }

}