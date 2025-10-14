package fr.uvsq.cprog.collex;

import java.util.Objects;

public class DnsItem {
    
    private final NomMachine nomMachine;
    private final AdresseIP adresseIp;

    public DnsItem(NomMachine nomMachine, AdresseIP adresseIp) {
        if (nomMachine == null || adresseIp == null) {
            throw new IllegalArgumentException("NomMAchine et AdresseIP ne peuvent être nuls");
        }

        this.nomMachine = nomMachine;
        this.adresseIp = adresseIp;
    }

    public NomMachine getNommachine() {
        return nomMachine;
    }

    public AdresseIP getIp() {
        return adresseIp;
    }

    @Override
    public String toString() {
        return adresseIp + " " + nomMachine;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        DnsItem d = (DnsItem) obj;
        return Objects.equals(nomMachine,d.nomMachine) && Objects.equals(adresseIp,d.adresseIp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomMachine, adresseIp);
    }
 
}
