package dev.sunriseydy.blog.user.infra.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import dev.sunriseydy.blog.common.constants.BlogSourceTypeConstant;
import dev.sunriseydy.blog.common.exception.CommonException;
import dev.sunriseydy.blog.db.typecho.entity.TypechoUsers;
import dev.sunriseydy.blog.db.typecho.mapper.TypechoUsersMapper;
import dev.sunriseydy.blog.user.api.dto.UserDTO;
import dev.sunriseydy.blog.user.domain.repository.UserRepository;
import dev.sunriseydy.blog.user.domain.vo.TpDbUserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author SunriseYDY
 * @date 2022-04-30 15:03
 */
@Slf4j
@Repository
@ConditionalOnProperty(name = BlogSourceTypeConstant.PROPERTY_NAME, havingValue = BlogSourceTypeConstant.SOURCE_TYPE_TP_DB)
public class TpDbUserRepositoryImpl implements UserRepository {

    @Resource
    private TypechoUsersMapper typechoUsersMapper;

    @Override
    public List<Long> getUserIdList() {
        return typechoUsersMapper.selectList(new QueryWrapper<TypechoUsers>().lambda()
                .select(TypechoUsers::getUid)
        ).stream().map(TypechoUsers::getUid).map(Long::valueOf).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        log.info("get that user:{}", id);

        TypechoUsers typechoUsers = typechoUsersMapper.selectById(id);
        TpDbUserVO userVO = TpDbUserVO.fromTypechoUser(typechoUsers);
        if (userVO == null) {
            throw new CommonException("资源不存在");
        }
        return userVO.toUserDTO();
    }

    @Override
    public UserDTO updateUserById(Long id) {
        log.info("update that user:{}", id);
        return this.getUserById(id);
    }

    @Override
    public void deleteUserById(Long id) {
        log.info("delete that user:{}", id);
    }
}
