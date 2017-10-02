package com.zhjl.tech.channel.model.channel;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 存证信息表（完备业务表）：存储存证文件的详细信息
 *
 */
@Data
@TableName("temp_order")
public class TempOrder implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 索引 */
	@TableId(type = IdType.AUTO)
	private Integer id;

	/** 更新时间 */
	@TableField(value = "update_time")
	private Date updateTime;

	/**  */
	@TableField(value = "access_key")
	private String accessKey;

	/**  */
	private String sign;

	/**  */
	@TableField(value = "sign_type")
	private String signType;

	/**  */
	private String random;

	/** 订单创建时间 */
	@TableField(value = "create_time")
	private Date createTime;

	/** [上链数据]存证记录版本。格式：1-2^16递增版本号.  */
	private String version;

	/**  */
	@TableField(value = "original_ordersn")
	private String originalOrdersn;

	/** [上链数据]订单号,全平台唯一标识. */
	private String ordersn;

	/** [上链数据]渠道来源 */
	@TableField(value = "channel_id")
	private String channelId;

	/** 交易标识。渠道用户标识。 */
	@TableField(value = "channel_userid")
	private String channelUserid;

	/** 交易标识。渠道存证订单唯一标识 */
	@TableField(value = "channel_ordersn")
	private String channelOrdersn;

	/** [上链数据]服务商 */
	@TableField(value = "provinder_id")
	private String provinderId;

	/** [上链数据]入链情况。1入链，0不入链 */
	private String chained;

	/** [上链数据]钱包网络地址。表示终端用户方式 */
	@TableField(value = "wallet_addr")
	private String walletAddr;

	/** [上链数据]用户钱包公钥。 */
	@TableField(value = "public_key")
	private String publicKey;

	/** [上链数据]客户签名。客户使用本人的钱包中私钥，对该该存证记录的字段进行计算 */
	@TableField(value = "attest_sign")
	private String attestSign;

	/** [上链数据]存证类型标识。1文件存证，2hash存证 */
	@TableField(value = "attest_type")
	private String attestType;

	/** [上链数据]业务类型。数据文件、视屏、音频、压缩等方式 */
	@TableField(value = "biz_type")
	private String bizType;

	/** [上链数据]源文件名 */
	@TableField(value = "source_file_name")
	private String sourceFileName;

	/** [上链数据]文件类型 */
	@TableField(value = "source_file_type")
	private String sourceFileType;

	/** [上链数据]源文件长度 */
	@TableField(value = "source_file_length")
	private String sourceFileLength;

	/** [上链数据]文件签名。使用本人钱包私钥对该数据进行计算 */
	@TableField(value = "source_file_sign")
	private String sourceFileSign;

	/** [上链数据]文件哈希值。sha256 */
	@TableField(value = "source_file_hash")
	private String sourceFileHash;

	/** 源文件存储地址 */
	@TableField(value = "source_file_addr")
	private String sourceFileAddr;

	/** 加密情况。1加密，0不加密 */
	private String encrypted;

	/** 加密算法 */
	@TableField(value = "encrypt_alog")
	private String encryptAlog;

	/** 加密秘钥 */
	@TableField(value = "encrypt_key")
	private String encryptKey;

	/** [上链数据]权属人类型。1自然人，2法人，0其他 */
	@TableField(value = "owner_type")
	private String ownerType;

	/** [上链数据]权属人身份标识。身份证号、社会信用代码 */
	@TableField(value = "owner_id")
	private String ownerId;

	/** [上链数据]权属人名称 */
	@TableField(value = "owner_name")
	private String ownerName;

	/** 代理人 */
	@TableField(value = "agent_name")
	private String agentName;

	/** 代理人电话 */
	@TableField(value = "agent_phone")
	private String agentPhone;

	/** 代理人邮箱 */
	@TableField(value = "agent_email")
	private String agentEmail;

	/** [上链数据]存证起始时间 */
	@TableField(value = "origin_time")
	private Date originTime;

	/** [上链数据]当前存证起始时间 */
	@TableField(value = "start_time")
	private Date startTime;

	/** [上链数据]当前存证持续时间(天),从start_time开始算. */
	private String duration;

	/** [上链数据]存证描述信息 */
	private String description;

	/** [上链数据]本次存证订单费用. */
	private String price;

	/** 请求标识，请求发起时间。格式：20170728172911 */
	@TableField(value = "request_time")
	private String requestTime;

	/** 存证文件名 */
	@TableField(value = "attest_file_name")
	private String attestFileName;

	/** 存证文件地址 */
	@TableField(value = "attest_file_addr")
	private String attestFileAddr;

	/**  */
	private String state;


}
