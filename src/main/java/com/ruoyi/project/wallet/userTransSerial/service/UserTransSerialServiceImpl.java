package com.ruoyi.project.wallet.userTransSerial.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ruoyi.project.system.config.domain.Config;
import com.ruoyi.project.system.config.mapper.ConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.wallet.userTransSerial.mapper.UserTransSerialMapper;
import com.ruoyi.project.wallet.userTransSerial.domain.UserTransSerial;
import com.ruoyi.common.support.Convert;

/**
 * 交易流水 服务层实现
 * 
 * @author ruoyi
 * @date 2018-08-24
 */
@Service
public class UserTransSerialServiceImpl implements IUserTransSerialService 
{
	private static String recommendRateKey ="sys.kingo.recommend.rate";
	private static String standardValueKey ="sys.kingo.release.standard.value";

	@Autowired
	private UserTransSerialMapper userTransSerialMapper;

	@Autowired
	ConfigMapper configMapper;
	/**
     * 查询交易流水信息
     * 
     * @param transId 交易流水ID
     * @return 交易流水信息
     */
    @Override
	public UserTransSerial selectUserTransSerialById(Long transId)
	{
	    return userTransSerialMapper.selectUserTransSerialById(transId);
	}
	
	/**
     * 查询交易流水列表
     * 
     * @param userTransSerial 交易流水信息
     * @return 交易流水集合
     */
	@Override
	public List<UserTransSerial> selectUserTransSerialList(UserTransSerial userTransSerial)
	{
	    return userTransSerialMapper.selectUserTransSerialList(userTransSerial);
	}
	
    /**
     * 新增交易流水
     * 
     * @param userTransSerial 交易流水信息
     * @return 结果
     */
	@Override
	public int insertUserTransSerial(UserTransSerial userTransSerial)
	{
	    return userTransSerialMapper.insertUserTransSerial(userTransSerial);
	}
	
	/**
     * 修改交易流水
     * 
     * @param userTransSerial 交易流水信息
     * @return 结果
     */
	@Override
	public int updateUserTransSerial(UserTransSerial userTransSerial)
	{
	    return userTransSerialMapper.updateUserTransSerial(userTransSerial);
	}

	/**
     * 删除交易流水对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteUserTransSerialByIds(String ids)
	{
		return userTransSerialMapper.deleteUserTransSerialByIds(Convert.toStrArray(ids));
	}

	/**
	 * 批量更新
	 * @param userTransSerials
	 */
	@Override
	public void insertBatch(List<UserTransSerial> userTransSerials) {
	userTransSerialMapper.insertBatch(userTransSerials);
	}

	/**
	 *
	 * @param memberId
	 * @return
	 */
	@Override
	public BigDecimal selectMemberPreDayOutById(Integer memberId) {

		return  userTransSerialMapper.selectMemberPreDayOutById(memberId);
	}

	@Override
	public List<UserTransSerial> selectUserTransSerialListByStatus(Integer userTransSerial, ArrayList status) {
		return userTransSerialMapper.selectUserTransSerialListByStatus( userTransSerial,  status) ;
	}

	@Override
	public BigDecimal selectUserTransSerialListByStatusTotal(Integer userId, List<Integer> status) {
		return userTransSerialMapper.selectUserTransSerialListByStatusTotal( userId,  status);
	}

	@Override
	public BigDecimal selectUserRewardTotal(Integer userId, List<Integer> status) {
		BigDecimal rewardTotal = new BigDecimal(0);
		BigDecimal total = userTransSerialMapper.selectUserTransSerialListByStatusTotal( userId,  status);

		/*if(total.compareTo(BigDecimal.ZERO)==0){
			return  new BigDecimal(0);
		}*/
		List<Config> configs = new ArrayList<>();
		Config config = new Config();

		config.setConfigKey(recommendRateKey);
		configs.add(config);
		config = new Config();

		config.setConfigKey(standardValueKey);
		configs.add(config);


		List<Config> list =configMapper.selectConfigList(configs);
		Config recommendRate = (Config)list.stream().filter(key->recommendRateKey.equals(key.getConfigKey())).findFirst().get();

		BigDecimal rate =recommendRate==null?new BigDecimal(0.15):new BigDecimal(recommendRate.getConfigValue());
		Config standardValue = list.stream().filter(key->standardValueKey.equals(key.getConfigKey())).findFirst().get();
		BigDecimal standard =standardValue==null?new BigDecimal(5000):new BigDecimal(standardValue.getConfigValue());

		rewardTotal = total.divide(rate,4,BigDecimal.ROUND_DOWN).divide(standard,4,BigDecimal.ROUND_DOWN);
		return  rewardTotal ;
	}

	@Override
	public void updateBatchTransStatus(List<UserTransSerial> userTransSerials) {
		userTransSerialMapper.updateBatchTransStatus(userTransSerials);
	}


}
