import java.io.*;

public class Thing implements Serializable {
    private String name;
    private String description;

    public Thing(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void serialize() {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("thing.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.print("Serialized data is saved in employee.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public Thing deSerialize(){
        Thing t = null;
        try {
            FileInputStream fileIn = new FileInputStream("thing.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            t = (Thing) in.readObject();
            in.close();
            fileIn.close();

            return t;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Thing class not found");
            c.printStackTrace();
            return null;
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Thing{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}