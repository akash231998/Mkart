package rentalapplicant;

import jdk.internal.util.xml.impl.XMLWriter;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import javax.xml.bind.JAXBException;

public class productPage extends JPanel {
    private JLabel jcomp1;
    private static JButton buttonRent;
    private static JList<String> jcomp3;
    private JLabel jcomp4;
    private static JLabel labelRentalDue;
    private static JButton buttonStatus;
    private JLabel jcomp7;
    private static JComboBox<String> comboBoxCostItem;
    private JMenuBar jcomp9;

    public productPage() throws FileNotFoundException, JAXBException, ClassNotFoundException {
        //construct preComponents
        String[] jProductList = fetchProducts();
        String[] jcomp8Items = {"12", "18", "24", "36"};


        // Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem printItem = new JMenuItem("Print");
        fileMenu.add(printItem);
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);
        JMenu helpMenu = new JMenu("Help");
        JMenuItem contentsItem = new JMenuItem("Contents");
        helpMenu.add(contentsItem);
        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);

        //construct components
        jcomp1 = new JLabel("MITE Kart");
        buttonRent = new JButton("Rent");
        jcomp3 = new JList<>(jProductList);
        jcomp4 = new JLabel("Total Balance");
        labelRentalDue = new JLabel("0");
        buttonStatus = new JButton("Status");
        jcomp7 = new JLabel("Duration");
        comboBoxCostItem = new JComboBox<>(jcomp8Items);
        jcomp9 = new JMenuBar();
        jcomp9.add(fileMenu);
        jcomp9.add(helpMenu);

        // List
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(jcomp3);
        jcomp3.setLayoutOrientation(JList.VERTICAL);

        //adjust size and set layout
        setPreferredSize(new Dimension(353, 288));
        setLayout(null);

        //add components
        add(jcomp1);
        add(buttonRent);
        add(scrollPane);
        add(jcomp4);
        add(labelRentalDue);
        add(buttonStatus);
        add(jcomp7);
        add(comboBoxCostItem);
        add(jcomp9);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds(130, 35, 60, 30);
        buttonRent.setBounds(165, 240, 100, 25);
        jcomp3.setBounds(45, 85, 180, 140);
        jcomp4.setBounds(240, 160, 85, 25);
        labelRentalDue.setBounds(245, 190, 70, 20);
        buttonStatus.setBounds(45, 240, 100, 25);
        jcomp7.setBounds(240, 85, 80, 25);
        comboBoxCostItem.setBounds(240, 115, 100, 25);
        jcomp9.setBounds(-5, 0, 360, 25);
        scrollPane.setBounds(45, 85, 180, 140);
    }

    static void showPage2(Customer customer) throws FileNotFoundException, JAXBException, ClassNotFoundException {
        JFrame frame = new JFrame("Product Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new productPage());
        frame.pack();
        frame.setVisible(true);
        labelRentalDue.setText(String.valueOf(customer.getRentalAmount()));
        buttonRent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedItemIndex = jcomp3.getSelectedIndex();
                Products products = null;
                try {
                    // Get selected product
                    try {
                        int months = Integer.parseInt(String.valueOf(comboBoxCostItem.getSelectedItem()));
                        products = (Products) XMLJavaReadWriters.readFromXML("Products", System.getProperty("user.dir") + "\\src\\product.xml");
                        Product selectedItem = products.getProductList().get(selectedItemIndex);

                        // add to user's account
                        Product addP = new Product();
                        addP.setName(selectedItem.getName());
                        addP.setBrand(selectedItem.getBrand());
                        addP.setDesc(selectedItem.getDesc());
                        addP.setPrice(selectedItem.getPrice());
                        addP.setMonths(Integer.parseInt(String.valueOf(months)));
                        customer.getRentedProducts().getProductList().add(addP);

                        // Set Rental Amount
                        double currRentalAmount = customer.getRentalAmount();
                        labelRentalDue.setText(String.valueOf(currRentalAmount));

                        // Write User content to file.
                        Customers custObjectList = (Customers)XMLJavaReadWriters.readFromXML("Customers", System.getProperty("user.dir")+"\\src\\customers.xml");
                        ArrayList<Customer> cList = (ArrayList<Customer>)custObjectList.getCustomerList();
                        for (Customer value : cList)    // Repalce the old customer details with the new customer details
                            if (value.getUname().equals(customer.getUname())
                                    && value.getPassword().equals(customer.getPassword())) {
                                custObjectList.getCustomerList().remove(value);
                                custObjectList.getCustomerList().add(customer);
                                break;
                            }
                        XMLJavaReadWriters.writeToXML("Customers", System.getProperty("user.dir")+"\\src\\customers.xml", custObjectList);
                    }catch (ArrayIndexOutOfBoundsException ignored){}
                } catch (FileNotFoundException | JAXBException | ClassNotFoundException ex) {
                    System.out.println(" PAGE 2 : ProductPage.java : Rental Item Selection Error ");
                    ex.getMessage();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        buttonStatus.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               try {
                   StatusPanel.showStatusPanel(customer);
               } catch (FileNotFoundException | JAXBException | ClassNotFoundException ex) {
                   ex.printStackTrace();
               }
           }
       });
    }

    private static float calculatePrice(float amount, float months) {
        return (float) (amount * months + amount * (15 / months) * 0.0625);
    }

    private String[] fetchProducts() throws FileNotFoundException, JAXBException, ClassNotFoundException {
        Products products = (Products) XMLJavaReadWriters.readFromXML("Products", System.getProperty("user.dir") + "\\src\\product.xml");
        ArrayList<Product> lst = (ArrayList<Product>) products.getProductList();
        String[] productStringList = new String[lst.size()];
        int i = 0;
        for (Product p : lst) {
            productStringList[i] = p.toString();
            i++;
        }
        return productStringList;
    }
}