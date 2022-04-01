import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Contactos contacto1 = new Contactos("Sarah", "sarah@gmail.com", 30236571);
        Contactos contacto2 = new Contactos("Sara", "sara@gmail.com", 302366321);
        Contactos contacto3 = new Contactos("Richard", "richard@gmail.com", 30264571);

        ArrayList<Contactos> contactosArray = new ArrayList<Contactos>();
        contactosArray.add(contacto1);
        contactosArray.add(contacto2);
        contactosArray.add(contacto3);

        try {
            FileOutputStream fileOutput = new FileOutputStream("Contactos.Data.Array.txt");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);

            outputStream.writeObject(contactosArray);
            outputStream.close();

            PrintWriter out = new PrintWriter("ContactosArrayTEXTO.txt");
            out.println(contactosArray);
            out.close();

            FileInputStream fileInputStream = new FileInputStream("Contactos.Data.Array.txt");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

            ArrayList<Contactos> inputArrayContactos;

                inputArrayContactos = (ArrayList<Contactos>) inputStream.readObject();
                int i = 0;
                for (Contactos c:inputArrayContactos){
                    System.out.println("Contacto: " + (i + 1) );
                    System.out.println(c);
                    i++;
                }

            inputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
