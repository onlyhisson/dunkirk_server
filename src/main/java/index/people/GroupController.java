package index.people;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupController {

	ArrayList<Group> groups = new ArrayList<Group>();

	/* select groups */
	@RequestMapping(value = "/group", method = RequestMethod.GET)
	public ArrayList<Group> groups() {
		return groups;
	}

	/* input sample data */
	@RequestMapping(value = "/group/sampledata", method = RequestMethod.PUT)
	public boolean putGroupTestData() {
		groups.add(new Group(1, "myeongseong", "church"));
		groups.add(new Group(2, "lg", "company"));
		groups.add(new Group(3, "seoul", "university"));
		
		return true;
	}
}
