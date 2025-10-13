package fr.uvsq.cprog.collex;

public class DnsItem {
    
    private NomMachine nommachine;
    private AdresseIP adresseip;

    public DnsItem(NomMachine nommachine, AdresseIP adresseip) {
        if (nommachine == null || adresseip == null) {
            throw new IllegalArgumentException("NomMAchine et AdresseIP ne peuvent être nuls");
        }

        this.nommachine = nommachine;
        this.adresseip = adresseip;
    }

    public NomMachine getNommachine() {
        return nommachine;
    }

    public AdresseIP getIp() {
        return adresseip;
    }

    @Override
    public String toString() {
        return adresseip + " " + nommachine;
    }

}
