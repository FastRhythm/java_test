package com.zhjl.tech.channel.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * 存证信息表（完备业务表）：存储存证文件的详细信息
 *
 */
@Data
public class AttestXqDTO implements Serializable {

	/*#################请求必传参数####################*/
	/** 本次请求参数的签名 MD5*/
	@NotBlank(message="请求签名类型不能为空")
	@Size(max=10, message = "请求签名类型不能超过10字符.")
	private String signType;

	/** 本次请求参数的签名 */
	@NotBlank(message="请求参数签名不能为空")
	@Size(max=24, message = "请求参数签名不能超过24字符.")
	private String sign;

	/** 请求标识，固定值 */
	@NotBlank(message="请求标识不能为空")
	@Size(max=44, message = "请求标识不能超过44字符.")
	private String accessKey;

	//随机数
	@NotBlank(message="随机数不能为空")
	@Size(max=32, message = "请求标识不能超过32字符.")
	private String random;

	/*##################用户存证信息######################*/

	@NotBlank(message="请求时间不能为空")
	@Size(max=20, message = "请求时间不能超过20字符.")
	private String requestTime;

	/** [上链数据]渠道来源 */
	@NotBlank(message="渠道id不能为空")
	@Size(max=3, message = "渠道id不能超过3字符.")
	private String channelId;

	/** 交易标识。渠道用户标识。 */
	@NotBlank(message="渠道用户ID")
	@Size(max=50, message = "渠道用户ID不能超过50字符.")
	private String channelUserid;

	/** 交易标识。渠道存证订单唯一标识 */
	@NotBlank(message="渠道订单号不能为空")
	@Size(max=50, message = "渠道订单号不能超过50字符.")
	private String channelOrdersn;

	/** 交易标识。表示存证原存证号 */
	@NotBlank(message="原存证号不能为空")
	@Size(max=18, message = "原存证号不能超过18字符.")
	private String originalOrdersn;

	/** [上链数据]入链情况。1入链，0不入链 */
	@NotBlank(message="是否入链情况不能为空")
	@Size(max=1, message = "是否入链情况不能超过1字符.")
	private String chained;

	/** [上链数据]业务类型。数据文件、视屏、音频、压缩等方式 */
	@NotBlank(message="业务类型不能为空")
	@Size(max=2, message = "业务类型不能超过2字符.")
	private String bizType;

	/** [上链数据]源文件名 */
	@NotBlank(message="文件名称不能为空")
	@Size(max=40, message = "文件名称不能超过40字符.")
	private String sourceFileName;

	/** [上链数据]文件类型 */
	@NotBlank(message="文件类型不能为空")
	@Size(max=10, message = "文件类型不能超过10字符..")
	private String sourceFileType;

	/** [上链数据]源文件长度 */
	@NotBlank(message="文件长度不能为空")
	@Size(max=20, message = "文件长度不能超过20字符.")
	private String sourceFileLength;

	/** [上链数据]文件哈希值。sha256 */
	@NotBlank(message="文件哈希值不能为空")
	@Size(max=64, message = "文件哈希值不能超过64字符.")
	private String sourceFileHash;


	/** [上链数据]权属人类型。1自然人，2法人，0其他 */
	@NotBlank(message="权属人类型不能为空")
	@Size(max=1, message = "权属人类型不能超过1字符.")
	private String ownerType;

	/** [上链数据]权属人身份标识。身份证号、社会信用代码 */
	@NotBlank(message="权属人身份标识不能为空")
	@Size(max=18, message = "权属人身份标识不能超过18字符.")
	private String ownerId;

	/** [上链数据]权属人名称 */
	@NotBlank(message="权属人姓名不能为空")
	@Size(max=64, message = "权属人姓名不能超过64字符.")
	private String ownerName;

	/** 代理人 */
	@NotBlank(message="代理人不能为空")
	@Size(max=32, message = "代理人不能超过32字符.")
	private String agentName;

	/** 代理人电话 */
	@NotBlank(message="代理人电话不能为空")
	@Size(max=13, message = "代理人电话不能超过13字符.")
	private String agentPhone;

	/** 代理人邮箱 */
	@NotBlank(message="代理人邮箱不能为空")
	@Size(max=100, message = "代理人邮箱不能超过100字符.")
	private String agentEmail;

	/** [上链数据]当前存证持续时间(天),从start_time开始算. */
	@NotBlank(message="存证时间长度不能为空")
	@Size(max=5, message = "存证时间长度不能超过5字符.")
	private String duration;

	/** [上链数据]存证描述信息 */
	@NotBlank(message="存证描述信息不能为空")
	@Size(max=200, message = "存证描述信息不能超过200字符.")
	private String description;

	/** [上链数据]本次存证订单费用. */
	@NotBlank(message="存证订单价格不能为空")
	@Size(max=10, message = "存证订单价格不能超过10字符.")
	private String price;


}
