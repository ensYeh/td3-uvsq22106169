package fr.uvsq.cprog.collex;

public class Quitter implements Commande {

    @Override
    public void execute() {
        System.exit(0);
    }
}