package index.board;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

	ArrayList<Article> articles = new ArrayList<Article>();

	// 조회
	@RequestMapping(value = "/article", method = RequestMethod.GET)
	public ArrayList<Article> articles() {

		return articles;
	}

	// 추가
	@RequestMapping(value = "/article", method = RequestMethod.POST)
	public String createArticle(@RequestParam(value = "name", defaultValue = "hong") String name,
			@RequestParam(value = "title", defaultValue = "untitled") String title,
			@RequestParam(value = "contents", defaultValue = "nocontent") String contents) {
		articles.add(new Article(name, title, contents));

		return "true";
	}

	// 건별 조회
	@RequestMapping(value = "/article/{name}", method = RequestMethod.GET)
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
	@RequestMapping(value = "/article/{name}", method = RequestMethod.DELETE)
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
	@RequestMapping(value = "/article/{name}", method = RequestMethod.PUT)
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
}