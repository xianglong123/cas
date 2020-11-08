### 遍历对象的属性拼接数据
    
    
	<insert id="add" parameterType="com.suixingpay.icp.notice.core.orm.smsservice.domain.SmsServiceBatchDetail">
			insert into smsdb.sms_send_detail(detail_id, batch_id, `key`, mobile_mask, channel_id, content, content_count, send_status, mobile_sm, fail_num, weight, price)
        	values (
        	<foreach collection="mList" index="" item="m" separator="">
				<foreach collection="m.phoneList" index="" item="phone" separator="union all">
					select replace(uuid(), '-', ''), #{m.batchId}, #{phone}, #{phone, jdbcType=VARCHAR, typeHandler=com.suixingpay.icp.notice.core.orm.smsservice.typeHandler.MobileMaskTypehandler},
					#{m.channelId}, #{m.content}, #{m.contentCount}, #{m.sendStatus}, #{phone, jdbcType=VARCHAR, typeHandler=com.suixingpay.icp.notice.core.orm.smsservice.typeHandler.MobileSmTypehandler}, #{m.failNum}, #{m.weight}, #{m.price} from dual
				</foreach>
			</foreach>
	</insert>
	
	
### 修改mysql重置分区字段
     Alter table sms_send_detail partition by range(to_days(start_time))(
         partition P737941 values less than (737941) engine = innoDB
         );
         
### mysql聚集索引和非聚集索引
    https://www.cnblogs.com/s-b-b/p/8334593.html


### mybatis整体架构
    基础支持层：
    反射模块，类型转换，日志模块，资源加载，解析器模块，
    数据源模块，事务管理模块，缓存模块，Binding模块
    
    
    
