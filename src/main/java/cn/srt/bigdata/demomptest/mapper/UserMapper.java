package cn.srt.bigdata.demomptest.mapper;

import cn.srt.bigdata.demomptest.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * Mapper继承BaseMapper:实现封装的增删改查方法
 */

@Repository
public interface UserMapper extends BaseMapper<User> {

}
