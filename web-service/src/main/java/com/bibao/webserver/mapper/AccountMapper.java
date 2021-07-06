package com.bibao.webserver.mapper;

import com.bibao.webserver.model.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author astupidcoder
 * @since 2021-06-20
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

}
