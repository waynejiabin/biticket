package com.biticket.project.wallet.userTransSerial.mapper;

import com.biticket.project.wallet.userTransSerial.domain.UserTransSerial;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 交易流水 数据层
 * 
 * @author biticket
 * @date 2018-08-24
 */
public interface UserTransSerialMapper 
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
     * 删除交易流水
     * 
     * @param transId 交易流水ID
     * @return 结果
     */
	 int deleteUserTransSerialById(Long transId);
	
	/**
     * 批量删除交易流水
     * 
     * @param transIds 需要删除的数据ID
     * @return 结果
     */
	 int deleteUserTransSerialByIds(String[] transIds);

	/**
	 * 批量插入
	 * @param userTransSerials
	 */
	void insertBatch(@Param("serialList") List<UserTransSerial> userTransSerials);

	/**
	 * 返回前天产量
	 * @param memberId
	 * @return
	 */
    BigDecimal selectMemberPreDayOutById(Integer memberId);

	/**
	 * 推广奖励列表
	 * @param userId
	 * @param status
	 * @return
	 */
    List<UserTransSerial> selectUserTransSerialListByStatus(@Param("userId") Integer userId, @Param("status") ArrayList status);

	/**
	 * 统计推广奖励
	 * @param userId
	 * @param status
	 * @return
	 */
	BigDecimal selectUserTransSerialListByStatusTotal(@Param("userId") Integer userId, @Param("status") List<Integer> status);

	/**
	 * 状态批量更新
	 * @param userTransSerials
	 */
    void updateBatchTransStatus(@Param("serialList") List<UserTransSerial> userTransSerials);
}