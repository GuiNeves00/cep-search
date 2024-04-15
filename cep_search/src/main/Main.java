package main;

import exceptions.NonexistentCEP;
import models.JsonGenerator;
import models.Searcher;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        // initializing variables and instantiating objects
        Searcher searcher = new Searcher();
        Scanner scan = new Scanner(System.in);
        JsonGenerator generator = new JsonGenerator();

        // menu loop
        while(true) {
            try {
                System.out.println("Type a CEP to search");
                String search = scan.nextLine();
                // simple validation
                if (search.length() != 8 || !searcher.isNumeric(search)) {
                    System.out.println("Invalid CEP (less than 8 digits or not numeric)");
                    continue;
                }

                String json = searcher.receiveFrom(search); // http request
                generator.createItem(json); // creates models.CepRecord obj and models.Cep obj, then saves models.Cep to a List

                // menu control
                System.out.println("1 - Keep searching | 0 - Exit");
                short option = scan.nextShort();
                scan.nextLine();
                if (option == 0) {
                    generator.saveItens();
                    break;
                }
            } catch (NonexistentCEP e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
