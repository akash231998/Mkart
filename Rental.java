package rentalapplicant;

//import javax.xml.bind.annotation.XmlAccessType;
//import javax.xml.bind.annotation.XmlAccessorType;
//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlRootElement;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//@XmlRootElement(name = "rentals")
//@XmlAccessorType(XmlAccessType.FIELD)
//class Rentals {
//  @XmlElement(name = "rental")
//  private List<Rental> rentalList = null;
//
//  static double getRentalCost() {
//      return rentalCost;
//  }
//
//  Rentals() {
//      rentalCost = 0;
//      rentalList = new ArrayList<Rental>();
//  }
//
//  List<Rental> getRentalList() {
//      return rentalList;
//  }
//
//  void setRentalList(List<Rental> rentalList) {
//      this.rentalList = rentalList;
//  }
//
//}
//
//@XmlRootElement(name = "rental")
//@XmlAccessorType(XmlAccessType.FIELD)
//class Rental {
//  @XmlElement
//  private Product product;
//  private int count;
//  Rental(){}
//  Rental(Product p) {
//      this.product = p;
//      this.count = 1;
//  }
//
//  void updateCost(int months) {
//      Rentals.rentalCost += product.getPrice() * months + product.getPrice() * (15. / months) * 0.0625;
//      this.count++;
//  }
//
//  public Product getProduct() {
//      return product;
//  }
//
//  public void setProduct(Product product) {
//      this.product = product;
//  }
//
//  public int getCount() {
//      return count;
//  }
//
//  public void setCount(int count) {
//      this.count = count;
//  }
//}