package com.ruoyi.project.wallet.userTransSerial.service;

import com.ruoyi.project.wallet.userTransSerial.domain.UserTransSerial;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 交易流水 服务层
 * 
 * @author ruoyi
 * @date 2018-08-24
 */
public interface IUserTransSerialService 
{
	/**
     * 查询交易流水信息
     * 
     * @param transId 交易流水ID
     * @return 交易流水信息
     */
	 UserTransSerial selectUserTransSerialById(Long transId);
	
	/**
     * 查询交易流水列表
     * 
     * @param userTransSerial 交易流水信息
     * @return 交易流水集合
     */
	 List<UserTransSerial> selectUserTransSerialList(UserTransSerial userTransSerial);
	
	/**
     * 新增交易流水
     * 
     * @param userTransSerial 交易流水信息
     * @return 结果
     */
	 int insertUserTransSerial(UserTransSerial userTransSerial);
	
	/**
     * 修改交易流水
     * 
     * @param userTransSerial 交易流水信息
     * @return 结果
     */
	 int updateUserTransSerial(UserTransSerial userTransSerial);
		
	/**
     * 删除交易流水信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	 int deleteUserTransSerialByIds(String ids);

	/**
	 * 批量保存用户交易记录
	 * @param userTransSerials
	 */
    void insertBatch(List<UserTransSerial> userTransSerials);

    BigDecimal selectMemberPreDayOutById(Integer memberId);

	/**
	 * 获取制定类型的记录
	 * @param userTransSerial
     * @param status
     * @return
	 */
    List<UserTransSerial> selectUserTransSerialListByStatus(Integer userTransSerial, ArrayList status);

	BigDecimal selectUserTransSerialListByStatusTotal(Integer userId, List<Integer> status);

    BigDecimal selectUserRewardTotal(Integer userId, List<Integer> status);

	/**
	 * 批量更新记录
	 * @param list
	 */
	void updateBatchTransStatus(List<UserTransSerial> list);
}
