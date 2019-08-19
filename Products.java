package rentalapplicant;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class    : Products
 * Purpose  : Holding List of Product class objects.
 *            Thus easy way to write to XML file.
 */
@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class Products {
    @XmlElement(name = "product")
    private List<Product> productList = null;

    Products(){
        productList = new ArrayList<Product>();
    }

    public List<Product> getProductList() {
        return this.productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

}

/**
 * Class    : Product
 * Purpose  : All attributes & methods of Product class.
 */
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
class Product {
    private String name, brand, desc;
    private int price, months;
    Product(){}

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public Product(String name, String brand, String desc, int price) {
        this.name = name;
        this.brand = brand;
        this.desc = desc;
        this.price = price;
        this.months = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return brand+" "+name+" | "+price+"/Month";
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}