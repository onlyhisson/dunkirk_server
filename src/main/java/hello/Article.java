package hello;

public class Article {

	private final String name;
	private final String title;
	private final String contents;

	public Article(String name, String title, String contents) {
		this.name = name;
		this.title = title;
		this.contents = contents;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getContents() {
		return contents;
	}
}
