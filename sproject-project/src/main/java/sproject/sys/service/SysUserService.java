package sproject.sys.service;

import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;
import sproject.exception.ParamException;
import sproject.sys.dao.SysUserMapper;
import sproject.sys.model.SysUser;
import sproject.sys.param.UserParam;
import sproject.util.BeanValidator;
import sproject.util.IpUtil;
import sproject.util.MD5Util;
import sproject.util.PasswordUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    public void save(UserParam param) {
        BeanValidator.check(param);
        if(checkTelephoneExist(param.getTelephone(), param.getId())) {
            throw new ParamException("电话已被占用");
        }
        if(checkEmailExist(param.getMail(), param.getId())) {
            throw new ParamException("邮箱已被占用");
        }
        String password = PasswordUtil.randomPassword();
        //TODO:
        password = "12345678";
        String encryptedPassword = MD5Util.encrypt(password);
        SysUser user = SysUser.builder().username(param.getUsername()).telephone(param.getTelephone()).mail(param.getMail())
                .password(encryptedPassword).deptId(param.getDeptId()).status(param.getStatus()).remark(param.getRemark()).build();

        //todo
        user.setOperator("system");
        user.setOperateIp("127.0.0.1");
        user.setOperateTime(new Date());

        // TODO: sendEmail

        sysUserMapper.insertSelective(user);
    }

    public void update(UserParam param) {
        BeanValidator.check(param);
        if(checkTelephoneExist(param.getTelephone(), param.getId())) {
            throw new ParamException("电话已被占用");
        }
        if(checkEmailExist(param.getMail(), param.getId())) {
            throw new ParamException("邮箱已被占用");
        }
        SysUser before = sysUserMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新的用户不存在");
        SysUser after = SysUser.builder().id(param.getId()).username(param.getUsername()).telephone(param.getTelephone()).mail(param.getMail())
                .deptId(param.getDeptId()).status(param.getStatus()).remark(param.getRemark()).build();
        //todo
        after.setOperator("system");
        after.setOperateIp("127.0.0.1");
        after.setOperateTime(new Date());
        sysUserMapper.updateByPrimaryKeySelective(after);
    }

    public boolean checkEmailExist(String mail, Integer userId) {
        return false;
    }

    public boolean checkTelephoneExist(String telephone, Integer userId) {
        return false;
    }

    public SysUser findByKeyword(String keyword) {
        //todo
        return null;
    }
}
