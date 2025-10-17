package fr.uvsq.cprog.collex;

public class RechercheParIP implements Commande {

    private final AdresseIP ip;
    private final Dns dns;

    public RechercheParIP(AdresseIP ip, Dns dns) {
        this.ip = ip;
        this.dns = dns;
    }

    @Override
    public void execute() {
        NomMachine machine = dns.getItem(ip);
        if (machine != null) {
            System.out.println(machine);
        } else {
            System.out.println("IP inconnue : " + ip);
        }
    }
}