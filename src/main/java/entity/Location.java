package entity;

public class Location {
	private long id;
	private String address = "";
	private String title = "";
	
	public Location() {}
	
	public Location(long id, String address, String title) {
		this.id = id;
		this.address = address;
		this.title = title;
	}
	
	public Location(String address, String title) {
		this.address = address;
		this.title = title;
	}
	
	public void setID(long newID) {
		this.id = newID;
	}
	
	public void setAddress(String newAddress) {
		this.address = newAddress;
	}
	
	public void setTitle(String newTitle) {
		this.title = newTitle;
	}
	
	public long getID() {
		return id;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getTitle() {
		return title;
	}
}
