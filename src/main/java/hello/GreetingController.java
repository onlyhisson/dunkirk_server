package hello;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	ArrayList<Person> persons = new ArrayList<Person>();
	ArrayList<Article> articles = new ArrayList<Article>();
	ArrayList<Comment> comments = new ArrayList<Comment>();

	/*
	 * #############################################################################
	 */

	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	/*
	 * #############################################################################
	 */

	// Person obj list 보기
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public ArrayList<Person> persons() {
		return persons;
	}
	
	@RequestMapping(value = "/persons3", method = RequestMethod.PUT)
	public void persons3() {
		persons.add(new Person("name1", "address1", "hobby1", "nationality1"));
		persons.add(new Person("name2", "address2", "hobby2", "nationality2"));
		persons.add(new Person("name3", "address3", "hobby3", "nationality3"));
	}

	// Person obj list 에 추가
	@RequestMapping(value = "/persons", method = RequestMethod.POST)
	public String createPerson(
			@RequestParam(value = "name", defaultValue = "hong") String name,
			@RequestParam(value = "address", defaultValue = "nowhere") String address,
			@RequestParam(value = "hobby", defaultValue = "nohobby") String hobby,
			@RequestParam(value = "nationality", defaultValue = "nonationality") String nationality) {
		persons.add(new Person(name, address, hobby, nationality));
		return "true";
	}
	
	// Person obj list 에 조회
	@RequestMapping(value = "/person/detail", method = RequestMethod.POST)
	public Person getPerson(@RequestParam (value = "name") String name) {
		int num;
		for (num = 0; num < persons.size(); num++) {
			Person getPerson = persons.get(num);
			if (name.equals(getPerson.getName())) {
				break;
			}
		}

		return persons.get(num);
	}

	// Person obj list 에서 삭제
	@RequestMapping(value = "/person/delete", method = RequestMethod.POST)
	public void deletePerson(@RequestParam(value = "name") String name) {
		int num;
		for (num = 0; num < persons.size(); num++) {
			Person getPerson = persons.get(num);
			if (name.equals(getPerson.getName())) {
				break;
			}
		}
		persons.remove(num);
	}

	// Person obj Update
	@RequestMapping(value = "/persons/{name}", method = RequestMethod.PUT)
	public void changePerson(
			@PathVariable(value = "name") String name,
			@RequestParam(value = "address", required=false) String address,
			@RequestParam(value = "hobby", required=false) String hobby,
			@RequestParam(value = "nationality", required=false) String nationality) {
		
		// to be changed Object's index number
		int num;
		// New Data Object
		Person setPerson;
		// Old Date Object
		Person getPerson;

		for (num = 0; num < persons.size(); num++) {
			getPerson = persons.get(num);
			if (name.equals(getPerson.getName())) {
				break;
			}
		}
		
		//load to be changed Object
		getPerson = persons.get(num);
		
		/*
		System.out.println("=========================================");
		System.out.println(address + "  " + hobby + "  " + nationality);
		System.out.println("=========================================");
		*/
		
		if(address == null || "".equals(address))
			if(address == null || "".equals(address))
			address = getPerson.getAddress();
		if(hobby == null || "".equals(hobby))
			hobby = getPerson.getHobby();
		if(nationality == null || "".equals(nationality))
			nationality = getPerson.getNationality();
		
		setPerson = new Person(name, address, hobby, nationality);
		
		//persons.remove(num);	
		persons.set(num, setPerson);
	}

	/*
	 * #############################################################################
	 */
	
	//조회 
	@RequestMapping(value = "/articles", method = RequestMethod.GET)
	public ArrayList<Article> articles() {

		return articles;
	}

	//추가 
	@RequestMapping(value = "/articles", method = RequestMethod.POST)
	public String createArticle(@RequestParam(value = "name", defaultValue = "hong") String name,
			@RequestParam(value = "title", defaultValue = "untitled") String title,
			@RequestParam(value = "contents", defaultValue = "nocontent") String contents) {
		articles.add(new Article(name, title, contents));
		
		return "true";
	}
	
	//건별 조회 
	@RequestMapping(value = "/articles/{name}", method = RequestMethod.GET)
	public Article getArticle(@PathVariable String name) {
		int num;
		for (num = 0; num < articles.size(); num++) {
			Article getArticle = articles.get(num);
			if (name.equals(getArticle.getName())) {
				break;
			}
		}
		return articles.get(num);
	}
	
	// Person obj list 에서 삭제
		@RequestMapping(value = "/articles/{name}", method = RequestMethod.DELETE)
		public void deleteArticle(@PathVariable(value = "name") String name) {
			int num;
			for (num = 0; num < articles.size(); num++) {
				Article getArticle = articles.get(num);
				if (name.equals(getArticle.getName())) {
					break;
				}
			}
			articles.remove(num);
		}
		
		// 수정 
		@RequestMapping(value = "/articles/{name}", method = RequestMethod.PUT)
		public void changeArticle(@PathVariable(value = "name") String name,
				@RequestParam(value = "title", defaultValue = "untitled") String title,
				@RequestParam(value = "contents", defaultValue = "nocontent") String contents) {
			
			int num;
			Article setArticle = new Article(name, title, contents);

			for (num = 0; num < articles.size(); num++) {
				Article getArticle = articles.get(num);
				if (name.equals(getArticle.getName())) {
					break;
				}
			}
			articles.set(num, setArticle);
		}
		
		/*
		 * #############################################################################
		 */
		
		//조회 
		@RequestMapping(value = "/comments", method = RequestMethod.GET)
		public ArrayList<Comment> comments() {

			return comments;
		}

		//추가 
		@RequestMapping(value = "/comments", method = RequestMethod.POST)
		public String createComment(@RequestParam(value = "name", defaultValue = "hong") String name,
				@RequestParam(value = "comment", defaultValue = "nocomment") String comment) {
			comments.add(new Comment(name, comment));
			
			return "true";
		}
		
		
		//건별 조회 
		@RequestMapping(value = "/comments/{name}", method = RequestMethod.GET)
		public Comment getComment(@PathVariable String name) {
			int num;
			for (num = 0; num < comments.size(); num++) {
				Comment getComment = comments.get(num);
				if (name.equals(getComment.getName())) {
					break;
				}
			}
			return comments.get(num);
		}
		
		// Person obj list 에서 삭제
			@RequestMapping(value = "/comments/{name}", method = RequestMethod.DELETE)
			public void deleteComment(@PathVariable(value = "name") String name) {
				int num;
				for (num = 0; num < comments.size(); num++) {
					Comment getComment = comments.get(num);
					if (name.equals(getComment.getName())) {
						break;
					}
				}
				comments.remove(num);
			}
			
			// 수정 
			@RequestMapping(value = "/comments/{name}", method = RequestMethod.PUT)
			public void changeComment(@PathVariable(value = "name") String name,
					@RequestParam(value = "comment", defaultValue = "nocomment") String comment) {
				
				int num;
				Comment setComment = new Comment(name, comment);

				for (num = 0; num < comments.size(); num++) {
					Comment getComment = comments.get(num);
					if (name.equals(getComment.getName())) {
						break;
					}
				}
				comments.set(num, setComment);
			}

}