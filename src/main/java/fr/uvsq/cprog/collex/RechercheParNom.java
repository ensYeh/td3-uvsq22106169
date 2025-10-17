package fr.uvsq.cprog.collex;

public class RechercheParNom implements Commande {

    private final NomMachine nommachine;
    private final Dns dns;

    public RechercheParNom(NomMachine nommachine, Dns dns) {
        this.nommachine = nommachine;
        this.dns = dns;
    }

    @Override
    public void execute() {
        AdresseIP ip = dns.getItem(nommachine);
        if (ip != null) {
            System.out.println(ip);
        } else {
            System.out.println("Machine inconnue : " + nommachine);
        }
    }
}