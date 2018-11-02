package com.ruoyi.project.system.config.mapper;

import com.ruoyi.project.system.config.domain.Config;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 参数配置 数据层
 * 
 * @author ruoyi
 */
public interface ConfigMapper
{
    /**
     * 查询参数配置信息
     * 
     * @param config 参数配置信息
     * @return 参数配置信息
     */
    Config selectConfig(Config config);

    /**
     * 查询参数配置列表
     * 
     * @param config 参数配置信息
     * @return 参数配置集合
     */
    List<Config> selectConfigList(@Param("configs") List<Config> config);

    /**
     * 根据键名查询参数配置信息
     * 
     * @param configKey 参数键名
     * @return 参数配置信息
     */
     Config checkConfigKeyUnique(String configKey);

}