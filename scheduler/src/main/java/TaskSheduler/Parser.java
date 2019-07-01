package TaskSheduler;

import javax.xml.bind.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class Parser {
    File file = new File("c:/tasks.xml");

    public void writeObjectToXML(Task task) {
        try {
            JAXBContext context = JAXBContext.newInstance(Task.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            OutputStream os = new FileOutputStream( "c:/tasks.xml" );
            marshaller.marshal(task, os);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Task> readXMLToObject(){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Task.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (Task) un.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
