package index.board;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

	ArrayList<Comment> comments = new ArrayList<Comment>();

	// 조회
	@RequestMapping(value = "/comment", method = RequestMethod.GET)
	public ArrayList<Comment> comments() {

		return comments;
	}

	// 추가
	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public String createComment(@RequestParam(value = "name", defaultValue = "hong") String name,
			@RequestParam(value = "comment", defaultValue = "nocomment") String comment) {
		comments.add(new Comment(name, comment));

		return "true";
	}

	// 건별 조회
	@RequestMapping(value = "/comment/{name}", method = RequestMethod.GET)
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
	@RequestMapping(value = "/comment/{name}", method = RequestMethod.DELETE)
	public void deleteComment(@PathVariable(value = "name") String name) {
		for (int num = 0; num < comments.size(); num++) {
			Comment getComment = comments.get(num);
			if (name.equals(getComment.getName())) {
				comments.remove(num);
			}
		}

	}

	// 수정
	@RequestMapping(value = "/comment/{name}", method = RequestMethod.PUT)
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