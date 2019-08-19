package rentalapplicant;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class XMLJavaReadWriters {
    /**
     * XMLJavaReadWriters : Class for handling read-write between Java ( object )  - XML files.
     * Class contains two methods :
     * [ Note : They are static - Because, to make them call w.r.t class name.]
     *  1. Object read(String, String)
     *      input : className ( name of class ex : Customers, Products )
     *              path ( file name path as string )
     *      output : reads content from XML file & returns object of given classType. ( classname )
     *
     *  2. void write(String, Object, String)
     *      input : className   - name of class ex : Customers, Products )
     *              path        - file name path as string )
     *              Object      - object which needs to be written
     *      output : writes to XML files specified by path for classType className, & returns void.
     */
    public static Object readFromXML(String className,
                                     String path)
            throws FileNotFoundException, JAXBException, ClassNotFoundException {
        /* Read the content from XML and convert to Java Object */
        // Get class object for specified class
        Class cls = Class.forName(className);

        // Get the File reader ready
        FileInputStream fis = new FileInputStream(path);

        // Create JAXB specifying the Class type as 'cls'
        JAXBContext jaxbContext = JAXBContext.newInstance(cls);

        // Create instance of unmarshaller
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        // Get the object from File Reader.
        return unmarshaller.unmarshal(fis);
    }

    static void writeToXML(String className, String path, Object writeObject)
            throws IOException, JAXBException, ClassNotFoundException {
        /* Write the object to the XML file specified by path */

        // Get class object for specified class
        Class cls = Class.forName(className);

        // Create JAXB specifying the Class type as 'cls'
        JAXBContext context = JAXBContext.newInstance(cls);

        // Create instance of marshaller ( intermediate )
        Marshaller marshaller = context.createMarshaller();

        // Say marshaller that "I want formated type"
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Get the File Writer ready.
        FileOutputStream fos = new FileOutputStream(path);

        // Write object ( writeObject ) to the File Buffer specified by 'fos'
        marshaller.marshal(writeObject, fos);

        // Close file
        fos.close();
    }
}
