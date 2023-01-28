package com.lms.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.dto.ModuleDto;
import com.lms.api.model.Course;
import com.lms.api.model.Module;
import com.lms.api.model.Video;
import com.lms.api.repository.CourseRepository;
import com.lms.api.repository.ModuleRepository;
import com.lms.api.repository.VideoRepository;

@RestController
public class ModuleController {
	@Autowired
	private ModuleRepository moduleRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private VideoRepository videRepository;
	
/*
 * post module
 */
	@PostMapping("/module/insert/{cid}")
	public Module postModule(@RequestBody Module module,@PathVariable("cid")Long cid) {
		Course course=courseRepository.getById(cid);
		module.setCourse(course);
		return moduleRepository.save(module);
	}
	/*
	 * get all modules by courseId
	 */
	@GetMapping("/module/{cid}")
	public List<ModuleDto> getModulesByCid(@PathVariable("cid")Long cid) {
		List<Module> list=moduleRepository.getByCourseId(cid);
		List<ModuleDto> dtoList=new ArrayList<>();
		
		for(Module module:list) {
			ModuleDto moduleDto=new ModuleDto();
			List<Video> listVid=videRepository.getByModuleId(module.getId());
			moduleDto.setId(module.getId());
			moduleDto.setName(module.getName());
			moduleDto.setSequence(module.getSequence());
			moduleDto.setVideo(listVid);
			dtoList.add(moduleDto);
			}
		return dtoList;
		
	}
	@GetMapping("/module/alterante/{cid}")
	public List<ModuleDto> getModulesByCidAlternate(@PathVariable("cid")Long cid){
		/*
		 * step 1 fetch all modules based on cid
		 */
		List<Module> list=moduleRepository.getByCourseId(cid);
		List<ModuleDto> listDto=new ArrayList<>();
		/*
		 * fetch all videos for given course id
		 */
		List<Video> listVideos=videRepository.getByCourseId(cid);
		/*
		 * iterate through each module to fetch list of videos for that module
		 */
		list.stream().forEach(m->{
			ModuleDto dto=new ModuleDto();
			List<Video> listVideo=listVideos.parallelStream()
					.filter(v->v.getModule().getId().equals(m.getId()))
					.collect(Collectors.toList());
			
			dto.setId(m.getId());
			dto.setName(m.getName());
			dto.setSequence(m.getSequence());
			dto.setVideo(listVideo);
			listDto.add(dto);
		});
		return listDto;
	}
	
	

}
