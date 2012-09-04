package org.elkuku.pizzza.types;


public class TEntry {

	private long id;
	private long catid;

	private String title;
	private String description;

	private float price_peq;
	private float price_med;
	private float price_gra;

	private int favorite;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCatid() {
		return catid;
	}

	public void setCatid(long catid) {
		this.catid = catid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice_peq() {
		return price_peq;
	}

	public void setPrice_peq(float price_peq) {
		this.price_peq = price_peq;
	}

	public float getPrice_med() {
		return price_med;
	}

	public void setPrice_med(float price_med) {
		this.price_med = price_med;
	}

	public float getPrice_gra() {
		return price_gra;
	}

	public void setPrice_gra(float price_gra) {
		this.price_gra = price_gra;
	}

	public int getFavorite() {
		return favorite;
	}

	public void setFavorite(int favorite) {
		this.favorite = favorite;
	}
}
