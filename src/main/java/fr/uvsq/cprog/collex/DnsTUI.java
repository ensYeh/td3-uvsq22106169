package fr.uvsq.cprog.collex;

import java.util.*;

public class DnsTUI {

    private final Scanner scanner = new Scanner(System.in);

    public Commande nextCommande() {
        String ligne = scanner.nextLine().trim();

        if (ligne.isEmpty()) {
            return null;
        }

        return null;
    }


    public void affiche(String message) {
        System.out.println(message);
    }
}