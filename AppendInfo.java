package rentalapplicant;

//package project_household;

import java.util.List; 
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "houselist")
@XmlAccessorType (XmlAccessType.FIELD)
public class AppendInfo
{
    @XmlElement(name = "houselist")
    private List<Products> houseList = null;
 
    public List<Products> getHouselist() {
        return houseList;
    }
 
    public void setHouselist(List<Products> houseList) {
        this.houseList = houseList;
    }
}