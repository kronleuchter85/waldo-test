package com.waldo.business;

import com.waldo.core.entities.Photo;
import com.waldo.persistence.PhotoIndexer;

/**
 * @author Gonzalo
 *
 */
public class PhotoService 
{
	
	// this is a singleton instance
	private PhotoIndexer indexer;
	
	public void processPhoto(final Photo photo){
		
		this.indexer.index(photo);
		
	}
}
