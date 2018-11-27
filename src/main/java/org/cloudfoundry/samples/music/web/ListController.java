package org.cloudfoundry.samples.music.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import org.cloudfoundry.samples.music.domain.Album;
import org.cloudfoundry.samples.music.domain.AlbumList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/list")
public class ListController {
	
	@Autowired
    QueueService qService;
	
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public void sendAlbumList(@RequestBody @Valid Iterable<Album> albums) {
    	StringBuffer list = new StringBuffer();
    	
    	for(Album album : albums){
    		list.append(album.getTitle());
    		list.append("-");
    		list.append(album.getArtist());
    		list.append("\n");
        }
    	
    	AlbumList albumList = new AlbumList(list.toString(), getDate());
    	qService.publish(albumList);
    }
    
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public AlbumList getAlbumList(){
		return qService.get();
    }
    
    private String getDate(){
		SimpleDateFormat dFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA);
		Date date = new Date();
		return dFormat.format(date);
	}
}
