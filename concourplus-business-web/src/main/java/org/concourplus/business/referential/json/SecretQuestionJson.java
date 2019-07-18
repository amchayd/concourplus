package org.concourplus.business.referential.json;

import org.concourplus.dto.referential.SecretQuestionDTO;

public class SecretQuestionJson {
	private long id;
	private String question;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public SecretQuestionDTO objToDto() {
		SecretQuestionDTO secretQuestion = new SecretQuestionDTO();
		secretQuestion.setQuestion(this.question);
		return secretQuestion;
	}

}
