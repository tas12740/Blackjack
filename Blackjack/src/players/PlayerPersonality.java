package players;

public class PlayerPersonality extends Player {
	private int lim;
	private double percent;

	public PlayerPersonality(int lim, double percent) {
		super();
		this.lim = lim;
		this.percent = percent;
	}

	public int getLimit() {
		return lim;
	}

	public double getPercent() {
		return this.percent;
	}
}
