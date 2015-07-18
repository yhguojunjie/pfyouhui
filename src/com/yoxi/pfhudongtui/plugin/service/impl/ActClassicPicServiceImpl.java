package com.yoxi.pfhudongtui.plugin.service.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.pfhudongtui.plugin.entity.ActClassic;
import com.yoxi.pfhudongtui.plugin.entity.ActClassicPic;
import com.yoxi.pfhudongtui.plugin.service.ActClassicPicService;
import com.yoxi.jgframework.common.service.impl.CommonServiceImpl;

@Service("actClassicPicService")
@Transactional
public class ActClassicPicServiceImpl extends CommonServiceImpl implements ActClassicPicService {

	@Override
	public void saveClassicPic(ActClassic actClassic,HttpServletRequest request) {
		String screePath = request.getParameter("screePath");
		String[] url = screePath.split(",");
		for(String l : url){
			if("".equals(l))continue;
			ActClassicPic pic = new ActClassicPic();
			pic.setActClassic(actClassic);
			pic.setUrl(l);
			pic.setSeq(1);
			pic.setCreateTime(new Date());
			this.save(pic);
		}
		
	}
	
}