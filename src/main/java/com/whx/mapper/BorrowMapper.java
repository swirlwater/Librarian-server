package com.whx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whx.pojo.Borrow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wu
 * @since 2023-01-07
 */
@Mapper
public interface BorrowMapper extends BaseMapper<Borrow> {

    void agreeLend(@Param("id") Integer id,@Param("date") Date date);

    void requestRepaid(@Param("id") Integer id);

    void agreeRepaid(@Param("id") Integer id,@Param("date") Date date);

    void cancelLend(@Param("id") Integer id);

    void cancelRepaid(@Param("id") Integer id);
}
