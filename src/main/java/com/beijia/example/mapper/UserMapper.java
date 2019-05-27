package com.beijia.example.mapper;

import com.beijia.example.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Mht
 * @since 2019-05-23
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
