package com.library.entity.xml;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LibraryReturn {
	public String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
