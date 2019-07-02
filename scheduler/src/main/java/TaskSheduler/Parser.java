package TaskSheduler;

import javax.xml.bind.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class Parser {
    File file = new File("c:/tasks.xml");

    public void writeObjectToXML(ArrayList<Task> tasks) {
        try {
            JAXBContext context = JAXBContext.newInstance(TaskDate.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            OutputStream os = new FileOutputStream("c:/tasks.xml");
            marshaller.marshal(tasks, os);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> readXMLToObject() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Task.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (ArrayList<Task>) un.unmarshal(file);


        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            return new ArrayList<Task>();
        }
        return null;
    }
}
