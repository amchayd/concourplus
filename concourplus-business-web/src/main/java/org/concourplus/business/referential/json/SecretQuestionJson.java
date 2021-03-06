package org.concourplus.business.referential.json;

import org.concourplus.dto.referential.SecretQuestionDTO;

public class SecretQuestionJson {
	private long id;
	private String code;
	private String question;

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

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public SecretQuestionDTO objToDto() {
		SecretQuestionDTO secretQuestion = new SecretQuestionDTO();
		secretQuestion.setCode(this.code);
		secretQuestion.setQuestion(this.question);
		return secretQuestion;
	}

}
