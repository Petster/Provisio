package entity;

public class Room {
	private long id;
	private String title = "";
	private Boolean breakfast;
	private Boolean wifi;
	private Boolean fitness;
	private Boolean store;
	private Boolean nosmoke;
	private Boolean mobile;
	private String room_highlights = "";
	private String image = "";
	private int price;
	
	public Room() {}
	
	public Room(long id, String title, Boolean breakfast, Boolean wifi, Boolean fitness, Boolean store, Boolean nosmoke, Boolean mobile, String room_highlights, String image, int price) {
		this.id = id;
		this.title = title;
		this.breakfast = breakfast;
		this.wifi = wifi;
		this.fitness = fitness;
		this.store = store;
		this.nosmoke = nosmoke;
		this.mobile = mobile;
		this.room_highlights = room_highlights;
		this.image = image;
		this.price = price;
	}
	
	public Room(String title, Boolean breakfast, Boolean wifi, Boolean fitness, Boolean store, Boolean nosmoke, Boolean mobile, String room_highlights, String image, int price) {
		this.title = title;
		this.breakfast = breakfast;
		this.wifi = wifi;
		this.fitness = fitness;
		this.store = store;
		this.nosmoke = nosmoke;
		this.mobile = mobile;
		this.room_highlights = room_highlights;
		this.image = image;
		this.price = price;
	}
	
	public void setID(long newID) {
		this.id = newID;
	}
	
	public void setTitle(String newTitle) {
		this.title = newTitle;
	}
	
	public void setBreakfast(Boolean b) {
		this.breakfast = b;
	}
	
	public void setWifi(Boolean b) {
		this.wifi = b;
	}
	
	public void setFitness(Boolean b) {
		this.fitness = b;
	}
	
	public void setStore(Boolean b) {
		this.store = b;
	}
	
	public void setNoSmoke(Boolean b) {
		this.nosmoke = b;
	}
	
	public void setMobile(Boolean b) {
		this.mobile = b;
	}
	
	public void setRoomHighlights(String newHighlights) {
		this.room_highlights = newHighlights;
	}
	
	public void setImage(String newImage) {
		this.image = newImage;
	}
	
	public void setPrice(int newPrice) {
		this.price = newPrice;
	}
	
	public long getID() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Boolean getBreakfast() {
		return breakfast;
	}
	
	public Boolean getWifi() {
		return wifi;
	}
	
	public Boolean getFitness() {
		return fitness;
	}
	
	public Boolean getStore() {
		return store;
	}
	
	public Boolean getNoSmoke() {
		return nosmoke;
	}
	
	public Boolean getMobile() {
		return mobile;
	}
	
	public String getHighlights() {
		return room_highlights;
	}
	
	public String getImage() {
		return image;
	}
	
	public int getPrice() {
		return price;
	}
}
