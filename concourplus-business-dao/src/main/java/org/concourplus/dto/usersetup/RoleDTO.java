package org.concourplus.dto.usersetup;

public class RoleDTO {
	private long id;
	private String code;
	private String label;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "{code:" + code + ", label:" + label + "}";
	}

}
