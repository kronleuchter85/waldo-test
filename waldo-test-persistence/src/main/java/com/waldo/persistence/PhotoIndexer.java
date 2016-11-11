package com.waldo.persistence;

import com.waldo.core.entities.Photo;

/**
 * 
 * This is a class with implementation of the indexer connectivity. It has
 * its behavior sinchronized  because it shares the same connection with all invocations
 * and is a singleton, so, just one invocation can be done from the same application instance at the time.
 * 
 * @author Gonzalo
 *
 */
public class PhotoIndexer {
	
	
	public synchronized void index(Photo photo){
		
		// 1) connect to indexer, usually did in a singleton connection factory
		// 2) execute the statement for index by key serializing the payload using the Serialized representation 
		// of the object, for this is util the Serializable implementation in the class Photo.
		//
		//
		// this should be something like this:
		//
		// code: indexer.index( photo.getKey() , SerializationUtils.serialize(photo) );
		
	}
	
}
