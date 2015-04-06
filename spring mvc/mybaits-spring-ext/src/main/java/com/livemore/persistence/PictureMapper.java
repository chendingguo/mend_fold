package com.livemore.persistence;

import java.util.List;

import com.livemore.model.Picture;

public interface PictureMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Picture record);

    int insertSelective(Picture record);

    Picture selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Picture record);

    int updateByPrimaryKey(Picture record);
    List<Picture> selectPictureList(Picture picture);
    
    List<Picture> selectPictureListByPage(Picture picture);
    
    long selectTotalCount(Picture picture);
    
}