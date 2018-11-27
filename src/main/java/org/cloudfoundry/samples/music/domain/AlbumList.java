package org.cloudfoundry.samples.music.domain;

import java.io.Serializable;

public class AlbumList implements Serializable{
	
	private static final long serialVersionUID = 8839688694741087921L;
	
	private String albumList;
	private String timeStamp;
	
	public AlbumList(String albumList, String timeStamp) {
		this.albumList = albumList;
		this.timeStamp = timeStamp;
	}

	public String getAlbumList() {
		return albumList;
	}
	
	public void setAlbumList(String albumList) {
		this.albumList = albumList;
	}
	
	public String getTimeStamp() {
		return timeStamp;
	}
	
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
}
