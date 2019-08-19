package rentalapplicant;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.swing.*;
import javax.xml.bind.JAXBException;

class StatusPanel extends JPanel {
    private JLabel jcomp1;
    private JList<String> jcomp2;
    private JButton jcomp3;
    private JSlider Duration;
    private JButton jcomp5;
    private JLabel jcomp7;
    private JLabel jcomp8;
    private static Customer currentCust;
    private static String[] itemList;

    StatusPanel() {
        //construct preComponents
        itemList = fetchOptedItems();

        //construct components
        jcomp1 = new JLabel("MITE Kart");
        jcomp2 = new JList<String>(itemList);
        jcomp3 = new JButton("Remove");
        Duration = new JSlider(10, 72);
        jcomp5 = new JButton("Done");
        jcomp7 = new JLabel("Total Rental Due");
        jcomp8 = new JLabel("0");

        //set components properties
        Duration.setOrientation(JSlider.HORIZONTAL);
        Duration.setMinorTickSpacing(0);
        Duration.setMajorTickSpacing(8);
        Duration.setPaintTicks(true);
        Duration.setPaintLabels(true);

        //adjust size and set layout
        setPreferredSize(new Dimension(351, 289));
        setLayout(null);

        //add components
        add(jcomp1);
        add(jcomp2);
        add(jcomp3);
        add(Duration);
        add(jcomp5);
        add(jcomp7);
        add(jcomp8);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds(135, 45, 75, 25);
        jcomp2.setBounds(30, 90, 175, 125);
        jcomp3.setBounds(215, 90, 100, 25);
        Duration.setBounds(30, 220, 290, 50);
        jcomp5.setBounds(215, 125, 100, 25);
        jcomp7.setBounds(220, 160, 100, 25);
        jcomp8.setBounds(215, 185, 100, 25);


        // On remove
        jcomp3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentCust.getRentedProducts().getProductList().remove(jcomp2.getSelectedIndex());
                Customers custObjectList = null;
                try {
                    // Read and add this customer and update the same here
                    custObjectList = (Customers)XMLJavaReadWriters.readFromXML("Customers", System.getProperty("user.dir")+"\\src\\customers.xml");
                    ArrayList<Customer> cList = (ArrayList<Customer>)custObjectList.getCustomerList();
                    for (Customer value : cList)    // Repalce the old customer details with the new customer details
                        if (value.getUname().equals(currentCust.getUname())
                                && value.getPassword().equals(currentCust.getPassword())) {
                            custObjectList.getCustomerList().remove(value);
                            custObjectList.getCustomerList().add(currentCust);
                            break;
                        }
                    XMLJavaReadWriters.writeToXML("Customers", System.getProperty("user.dir")+"\\src\\customers.xml", custObjectList);

                    //refresh the list
                    jcomp2.setListData(fetchOptedItems());

                } catch (JAXBException | ClassNotFoundException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // On Pressing done
        jcomp5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    productPage.showPage2(currentCust);
                } catch (FileNotFoundException | JAXBException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private String[] fetchOptedItems() {
        ArrayList<Product> products = (ArrayList<Product>)currentCust.getRentedProducts().getProductList();
        String[] retString = new String[products.size()];
        int i = 0;
        for(Product p : products) {
            retString[i] = p.toString();
            i++;
        }
        return retString;
    }

    static void showStatusPanel(Customer customer) throws FileNotFoundException, JAXBException, ClassNotFoundException {

        // Get new customer object. Concurrency maintanence
        Customers custObjectList = (Customers)XMLJavaReadWriters.readFromXML("Customers", System.getProperty("user.dir")+"\\src\\customers.xml");
        ArrayList<Customer> cList = (ArrayList<Customer>)custObjectList.getCustomerList();
        for (Customer c : cList)
            if (c.getUname().equals(customer.getUname())){
                currentCust = c;
                break;
            }

        // Call garbage collector
        System.gc();

        // Show swing applet.
        JFrame frame = new JFrame("Status Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new StatusPanel());
        frame.pack();
        frame.setVisible(true);
    }
}