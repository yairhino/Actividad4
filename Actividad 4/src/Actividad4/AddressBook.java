package Actividad4;
import java.io.*;
import java.util.*;

public class AddressBook {
    private Map<String, String> contacts;
    private String fileName;

    public AddressBook(String fileName) {
        this.contacts = new HashMap<>();
        this.fileName = fileName;
    }

    // Método para cargar los contactos desde el archivo
    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    contacts.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para guardar los contactos en el archivo
    public void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, String> entry : contacts.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para listar los contactos
    public void list() {
        System.out.println("Contactos:");
        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    // Método para crear un nuevo contacto
    public void create(String phoneNumber, String name) {
        contacts.put(phoneNumber, name);
        save(); // Guarda automáticamente después de crear
    }

    // Método para borrar un contacto
    public void delete(String phoneNumber) {
        contacts.remove(phoneNumber);
        save(); // Guarda automáticamente después de borrar
    }

    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook("contacts.csv");
        addressBook.load();

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("1. Listar contactos");
            System.out.println("2. Crear contacto");
            System.out.println("3. Borrar contacto");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume la nueva línea después de nextInt()

            switch (choice) {
                case 1:
                    addressBook.list();
                    break;
                case 2:
                    System.out.print("Ingrese el número de teléfono: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Ingrese el nombre: ");
                    String name = scanner.nextLine();
                    addressBook.create(phoneNumber, name);
                    System.out.println("Contacto creado con éxito.");
                    break;
                case 3:
                    System.out.print("Ingrese el número de teléfono a borrar: ");
                    phoneNumber = scanner.nextLine();
                    addressBook.delete(phoneNumber);
                    System.out.println("Contacto borrado con éxito.");
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                    break;
            }
        } while (choice != 4);
    }
}