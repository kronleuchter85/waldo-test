package com.waldo.core.utils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.waldo.core.entities.Photo;

public class EXIFHandler extends DefaultHandler {

	private boolean key = false;
	private boolean size = false;
	private boolean lastModified = false;
	
	private Photo photo = null;
	private CallbackHandler handler ;
	
	
	public EXIFHandler(CallbackHandler handler) {
		super();
		this.handler = handler;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		
		/*
		 * starts new photo
		 */
		if (qName.equalsIgnoreCase("contents")) {
			this.photo = new Photo();
		}

		/*
		 * the internal attributes
		 */
		if (qName.equalsIgnoreCase("key")) {
			key = true;
		}

		if (qName.equalsIgnoreCase("size")) {
			size = true;
		}
		
		if (qName.equalsIgnoreCase("lastModified")) {
			lastModified = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		/*
		 * Finishing the photo element
		 */
		if (qName.equalsIgnoreCase("contents")) {
			
			// releasing the photo
			this.handler.onPhoto(this.photo);
			this.photo = null;
		}
		
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		final String content = new String(ch, start, length);
		
		if (key) {
			this.photo.setKey(content);
			key = false;
		}
		
		if (size) {
			this.photo.setSize(Integer.valueOf(content));
			size = false;

		}
		
		if (lastModified) {
			this.photo.setTime(content);
			lastModified = false;

		}

	}

}
