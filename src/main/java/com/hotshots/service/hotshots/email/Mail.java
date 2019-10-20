package com.hotshots.service.hotshots.email;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Mail {

	private String from;
	private String to;
	private String subject;
	private String content;
	private List<EmailContext> ContextData;

	public Mail(String from, String to, String subject, String content, List<EmailContext> contextData) {
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.content = content;
		ContextData = contextData;
	}

	public Mail() {
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}
	

	public List<EmailContext> getContextData() {
		return ContextData;
	}

	public void setContextData(List<EmailContext> contextData) {
		ContextData = contextData;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Mail{" + "from='" + from + '\'' + ", to='" + to + '\'' + ", subject='" + subject + '\'' + ", content='"
				+ content + '\'' + '}';
	}
}
