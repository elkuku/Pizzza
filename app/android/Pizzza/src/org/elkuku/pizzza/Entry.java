package org.elkuku.pizzza;

public class Entry {
	private long id;

	private long catid;

	private String title;

	private String description;

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

	@Override
	public String toString()
	{
		return String.format("%s\n    %s", title, description);
	}

}
