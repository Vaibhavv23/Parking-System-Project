
public class Packinfo {
String pack;
int price,days;
public String getPack() {
	return pack;
}
public void setPack(String pack) {
	this.pack = pack;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public int getDays() {
	return days;
}
public void setDays(int days) {
	this.days = days;
}
public Packinfo(String pack, int price, int days) {
	super();
	this.pack = pack;
	this.price = price;
	this.days = days;
}

}
