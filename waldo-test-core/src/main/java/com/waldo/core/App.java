package com.waldo.core;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.SAXException;

import com.waldo.business.PhotoService;
import com.waldo.core.entities.Photo;
import com.waldo.core.utils.CallbackHandler;
import com.waldo.core.utils.EXIFHandler;

/**
 * @author Gonzalo
 *
 */
public class App {

	// singleton instance of sercice
	private PhotoService service;
	
	public void processExif() {

		/*
		 * First we invoke by REST the URL http://s3.amazonaws.com/waldo-recruiting and obtaining the EXIF
		 * specification of photos. In this example i
		 */
		final DefaultHttpClient httpClient = new DefaultHttpClient();
		final HttpGet getRequest = new HttpGet(	"http://s3.amazonaws.com/waldo-recruiting");
		getRequest.addHeader("accept", "application/xml");

		try {

			final HttpResponse response = httpClient.execute(getRequest);
			final InputStream stream = response.getEntity().getContent();

			/*
			 * after that we parse the content. For parsing we can use a
			 * stream/event based parser or a DOM based parser. For simplicity
			 * can be used a DOM parser but if performance is important then a
			 * SAX (push based event stream) or STAX (pull based event stream)
			 * must be used. So, lets supose we used a SAX parser, We will have
			 * a continuous event invocation of each entry in the EXIF
			 * specification of this way..
			 * 
			 * more information: http://www.oracle.com/technetwork/java/intro-140052.html
			 */

			final SAXParserFactory factory = SAXParserFactory.newInstance();
			final SAXParser saxParser = factory.newSAXParser();

			final EXIFHandler handler = new EXIFHandler(new CallbackHandler() {

				public void onPhoto(Photo photo) {

					service.processPhoto(photo);
				}
			});
			
			saxParser.parse(stream, handler);
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}

	}
}
