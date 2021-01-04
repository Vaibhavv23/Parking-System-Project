
public class Location {
  String carno,locstatus;
  int lapno,trackno;
public String getCarno() {
	return carno;
}
public void setCarno(String carno) {
	this.carno = carno;
}
public String getLocstatus() {
	return locstatus;
}
public void setLocstatus(String locstatus) {
	this.locstatus = locstatus;
}
public int getLapno() {
	return lapno;
}
public void setLapno(int lapno) {
	this.lapno = lapno;
}
public int getTrackno() {
	return trackno;
}
public void setTrackno(int trackno) {
	this.trackno = trackno;
}
public Location(String carno, String locstatus, int lapno, int trackno) {
	super();
	this.carno = carno;
	this.locstatus = locstatus;
	this.lapno = lapno;
	this.trackno = trackno;
}
@Override
public String toString() {
	return "Location [carno=" + carno + ", locstatus=" + locstatus + ", lapno=" + lapno + ", trackno=" + trackno + "]";
}
  
}
