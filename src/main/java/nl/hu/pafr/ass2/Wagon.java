package nl.hu.pafr.ass2;

public class Wagon {
	private String name;
	private Train train;
	
	public Wagon(String name) {
		this.name = name;
	}
	
	public void setTrain(Train train) {
		this.train = train;
	}
	
	public Train getTrain() {
		return this.train;
	}
	
	public String getName() {
		return this.name;
	}
}
