package rentalapplicant;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class    : Customers
 * Purpose  : Holding List of Customer class objects.
 * Thus easy way to write to XML file.
 */
@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
class Customers {
    @XmlElement(name = "customer")
    private List<Customer> customerList = null;

    Customers() {
        customerList = new ArrayList<Customer>();
    }

    List<Customer> getCustomerList() {
        return customerList;
    }

    void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
}

/**
 * Class    : Customer
 * Purpose  : All attributes of customer and methods
 */
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
class Customer {
    @XmlElement
    private String name;
    @XmlElement
    private String uname;
    @XmlElement
    private String password;

    private Products rentedProducts;

    Customer() {
        this.rentedProducts = new Products();
    }

    Customer(String name, String uname, String password) {
        this.name = name;
        this.uname = uname;
        this.password = password;
        this.rentedProducts = new Products();
    }

    @Override
    public String toString() {
        return "Customer Name :" + name;
    }

    public String getName() {
        return name;
    }

    Products getRentedProducts() {
        return rentedProducts;
    }

    String getUname() {
        return uname;
    }

    String getPassword() {
        return password;
    }

    double getRentalAmount(){
        double sum = 0;
        for(Product prd:rentedProducts.getProductList())
            sum += prd.getPrice() * prd.getMonths() + prd.getPrice() * 0.05 * 20. / prd.getMonths();
        System.out.println(sum);
        return sum;
    }
}