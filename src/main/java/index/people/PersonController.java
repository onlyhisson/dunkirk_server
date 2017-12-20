package index.people;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

	ArrayList<Person> persons = new ArrayList<Person>();

	// Person obj list 보기
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public ArrayList<Person> persons() {
		return persons;
	}

	@RequestMapping(value = "/persons3", method = RequestMethod.PUT)
	public void putPersonTestdata() {
		persons.add(new Person("name1", "address1", "hobby1", "nationality1"));
		persons.add(new Person("name2", "address2", "hobby2", "nationality2"));
		persons.add(new Person("name3", "address3", "hobby3", "nationality3"));
	}

	// Person obj list 에 추가
	@RequestMapping(value = "/persons", method = RequestMethod.POST)
	public String createPerson(@RequestParam(value = "name", defaultValue = "hong") String name,
			@RequestParam(value = "address", defaultValue = "nowhere") String address,
			@RequestParam(value = "hobby", defaultValue = "nohobby") String hobby,
			@RequestParam(value = "nationality", defaultValue = "nonationality") String nationality) {
		persons.add(new Person(name, address, hobby, nationality));
		return "true";
	}

	// Person obj list 에 조회
	@RequestMapping(value = "/person/{name:.+}", method = RequestMethod.GET)
	public Person getPerson(@PathVariable(value = "name") String name) {
		return findPersonByName(name);
	}

	private Person findPersonByName(String name) {
		for (Person person : persons) {
			if (name.equals(person.getName())) {
				return person;
			}
		}
		return null;
	}

	// Person obj list 에서 삭제
	@RequestMapping(value = "/person/{name:.+}", method = RequestMethod.DELETE)
	public void deletePerson(@PathVariable(value = "name") String name) {
		for (int num = 0; num < persons.size(); num++) {
			Person getPerson = persons.get(num);
			if (name.equals(getPerson.getName())) {
				persons.remove(num);
				return;
			}
		}
	}

	int indexOf(String name) {
		for (int num = 0; num < persons.size(); num++) {
			Person getPerson = persons.get(num);
			if (name.equals(getPerson.getName())) {
				return num;
			}
		}
		return -1;
	}

	// Person obj Update
	@RequestMapping(value = "/persons/{name}", method = RequestMethod.PUT)
	public void changePerson(@PathVariable(value = "name") String name,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "hobby", required = false) String hobby,
			@RequestParam(value = "nationality", required = false) String nationality) {
		// New Data Object
		Person setPerson;
		Person getPerson = findPersonByName(name);

		if (address == null || "".equals(address))
			if (address == null || "".equals(address))
				address = getPerson.getAddress();
		if (hobby == null || "".equals(hobby))
			hobby = getPerson.getHobby();
		if (nationality == null || "".equals(nationality))
			nationality = getPerson.getNationality();

		setPerson = new Person(name, address, hobby, nationality);
		persons.set(indexOf(name), setPerson);
	}
}