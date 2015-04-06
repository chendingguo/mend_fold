package com.livemore.service;

import java.util.List;

import mybatis.page.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livemore.model.Picture;
import com.livemore.model.SystemModule;
import com.livemore.persistence.PictureMapper;

@Service
public class PictureService {
	@Autowired
	private PictureMapper pictureMapper;

	public int insert(Picture picture) {
		return pictureMapper.insert(picture);

	}

	public Picture selectByPrimaryKey(Long id) {
		return pictureMapper.selectByPrimaryKey(id);
	}

	public List<Picture> selectPictureList(Picture picture) {
		return pictureMapper.selectPictureList(picture);
	}

	public int deleteByPrimaryKey(Long id) {
		return pictureMapper.deleteByPrimaryKey(id);
	}

	public List<Picture> selectPictureListByPage(Picture picture) {
		return pictureMapper.selectPictureListByPage(picture);
	}

	public int updateByPrimaryKey(Picture picture) {
		return pictureMapper.updateByPrimaryKey(picture);
	}

	public int updateByPrimaryKeySelective(Picture picture) {
		return pictureMapper.updateByPrimaryKeySelective(picture);
	}

	public long selectTotalCount(Picture picture) {
		return pictureMapper.selectTotalCount(picture);
	}

}
