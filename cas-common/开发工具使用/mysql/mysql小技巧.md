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