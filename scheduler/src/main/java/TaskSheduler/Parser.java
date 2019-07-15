package TaskSheduler;

import javax.xml.bind.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Parser {
    File file = new File("c:/tasks.xml");

    public void writeObjectToXML(TaskDate tasks) {
        try {
            JAXBContext context = JAXBContext.newInstance(TaskDate.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            OutputStream os = new FileOutputStream("c:/tasks.xml");
            marshaller.marshal(tasks, os);
            marshaller.marshal(tasks, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public TaskDate readXMLToObject() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(TaskDate.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();
            return (TaskDate) un.unmarshal(file);
        } catch (JAXBException e) {
            return new TaskDate();
        }
    }
}
