package com.biticket.framework.web.service;

import org.springframework.stereotype.Service;

/**
 * RuoYi首创 js调用 thymeleaf 实现按钮权限可见性
 * 
 * @author biticket
 */
@Service("permission")
public class PermissionService
{
    public String hasPermi(String permission)
    {
        return isPermittedOperator(permission) ? "" : "hidden";
    }

    private boolean isPermittedOperator(String permission)
    {
        //return SecurityUtils.getSubject().isPermitted(permission);
        return true;
    }

}
