package com.ruoyi.project.system.config.service;

import com.ruoyi.project.system.config.domain.Config;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 参数配置 服务层
 * 
 * @author ruoyi
 */
public interface IConfigService
{

    /**
     * 查询参数配置信息
     * 
     * @param configId 参数配置ID
     * @return 参数配置信息
     */
     Config selectConfigById(Long configId);

    /**
     * 根据键名查询参数配置信息
     * 
     * @param configKey 参数键名
     * @return 参数键值
     */
     String selectConfigByKey(String configKey);

    /**
     * 查询参数配置列表
     * 
     * @param configs 参数配置信息
     * @return 参数配置集合
     */
     List<Config> selectConfigList(@Param("configs") List<Config> configs);

}
