package fr.uvsq.cprog.collex;

public class AjouterItem implements Commande {

    private final Dns dns ;
    private final String domaine;
    private final String ip;
    private final String nom;

    public AjouterItem(Dns dns, String ip, String nom, String domaine) {
        this.dns = dns;
        this.ip = ip;
        this.nom = nom;
        this.domaine = domaine;
    }

    @Override
    public void execute() {
        try {
            dns.addItem(ip, nom, domaine);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}