package index.people;

public class Group {

	private int groupId;
	private String groupName;
	private String groupOrganisation;

	public Group(int groupId, String groupName, String groupOrganisation) {
		this.groupId = groupId;
		this.groupName = groupName;
		this.groupOrganisation = groupOrganisation;
	}

	public int getGroupId() {
		return this.groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setGroupOrganisation(String groupOrganisation) {
		this.groupOrganisation = groupOrganisation;
	}

	public String getGroupName() {
		return groupName;
	}

	public String getGroupOrganisation() {
		return groupOrganisation;
	}
}
