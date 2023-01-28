package com.lms.api.controller;







import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.repository.ModuleRepository;
import com.lms.api.repository.VideoRepository;
import com.lms.api.dto.CourseStats;
import 	com.lms.api.model.Module;
import com.lms.api.model.Video;

@RestController

public class VideoController {
	@Autowired
	private ModuleRepository moduleRepository;
	@Autowired
	private VideoRepository videRepository;
	
	/*
	 * post video
	 */
	@PostMapping("/video/insert/{mid}")
	public Video postVideo(@PathVariable("mid")Long mid,@RequestBody Video video) {
	Module module=moduleRepository.getById(mid);
	video.setModule(module);
	return videRepository.save(video);
	}
	/*
	 * numofmodules
	 * 
	 * numofvideos
	 * contentduration in hr min
	 */
	@GetMapping("/course/video/stats/{cid}")
	public CourseStats courseStatsByVideo(@PathVariable("cid")Long cid) {
		
		CourseStats dto=new CourseStats();
		
		
		List<Module> listModule=moduleRepository.getByCourseId(cid);
		List<Video> listVideo=videRepository.getByCourseId(cid);
		if(listVideo!=null) {
		dto.setNumberOfVideos(listVideo.size());
		}
		
		if(listModule!=null) {
		dto.setNumberOfModules(listModule.size());
		}
		
		/*
		 * calculate total duration of videos
		 */
		List<String> durationList=listVideo.stream().map(v->v.getDuration())
				.collect(Collectors.toList());
		
		System.out.println(durationList);//[8.30, 7.80, 10.00]
		
		int totalHours=0;
		int totalMinutes=0;
		int totalSeconds=0;
		/*
		 * string s1="10.05" //10.5=
		 */
		for(String d:durationList) {
			totalMinutes=totalMinutes+Integer.parseInt(d.split("\\.")[0]);
			totalSeconds=totalSeconds+Integer.parseInt(d.split("\\.")[1]);
		}
		totalMinutes=totalMinutes+(totalSeconds/60);
		totalSeconds=totalSeconds%60;
		
		totalHours=(int) totalMinutes/60;
		totalMinutes=(int) totalMinutes%60;
		dto.setContentDuration(totalHours+" hrs "+totalMinutes+" mins "+totalSeconds+" secs");
		
	   
		return dto;
		
	}
	
	
	
}
