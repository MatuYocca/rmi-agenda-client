package com.mayocca.clases;

import com.mayocca.interfaces.Agenda;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.util.ArrayList;

public class ClientMain {

    public static void main(String[] args) {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        if (System.getSecurityManager() == null) {
            System.setProperty("java.security.policy", "file:./app.policy");
            System.setSecurityManager(new SecurityManager());
        }

        try {
            Agenda agenda = (Agenda) Naming.lookup("rmi://localhost:1099/agendaserver");

            int op;

            while (true) {
                System.out.print("Menú de opciones\n---\n" +
                        "1. Mostrar todos los contactos\n" +
                        "2. Buscar contactos por nombre\n" +
                        "3. Agregar un contacto\n" +
                        "4. Eliminar todos los contactos\n" +
                        "0. Salir\n" +
                        "> ");

                try {
                    op = Integer.parseInt(input.readLine());
                } catch (NumberFormatException e) {
                    System.out.println("Ingreso inválido. Intente de nuevo");
                    continue;
                }

                System.out.println("---");

                switch (op) {
                    case 1:
                        System.out.println("Nombre\tApellido\tNumero\n---");
                        for (Contact c : agenda.getContacts()) {
                            //for (int i = 0; i < agenda.getContacts().size(); ++i) {

                            System.out.print(c.nombre + '\t');
                            System.out.print(c.apellido + '\t');
                            System.out.println(c.numero);
                        }
                        break;
                    case 2:
                        System.out.print("Parámetro de búsqueda (Nombre/Apellido)> ");
                        ArrayList<Contact> r = agenda.getContactsByName(input.readLine());
                        System.out.println("Nombre\tApellido\tNumero\n---");
                        for (Contact c : r) {
                            System.out.print(c.nombre + '\t');
                            System.out.print(c.apellido + '\t');
                            System.out.println(c.numero);
                        }
                        System.out.println("ENTER para continuar...");
                        input.readLine();
                        //System.out.print("\n\n\n\n\n\n\n\n\n\n");
                        break;
                    case 3:
                        System.out.print("Nombre> ");
                        String nom = input.readLine();
                        System.out.print("Apellido> ");
                        String ape = input.readLine();
                        System.out.print("Numero> ");
                        String num = input.readLine();
                        Contact c = new Contact(nom, ape, num);
                        agenda.addContact(c);
                        System.out.println("Contacto agregado.");
                        System.out.println("ENTER para continuar...");
                        input.readLine();
                        //System.out.print("\n\n\n\n\n\n\n\n\n\n");
                        break;
                    case 4:
                        agenda.clearContacts();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Ingreso inválido. Intente de nuevo.");
                }

                if (op == 0)
                    break;
            }

        } catch (Exception e) {
            System.out.println("Exception while trying to receive contacts.");
            e.printStackTrace();
        }

    }

}
