## mysql高级查询
    
    select ... 聚合函数 from 表名
    where ...
    group by ...
    having ...
    order by ...
    limit ...;
    eg1 : 找出表中的最大攻击力的值？
    
    　　　　　　select max(attack) from sanguo;
    
    　　　　eg2 : 表中共有多少个英雄？
    
    　　　　　　select count(name) as number from sanguo;
    
    　　　　eg3 : 蜀国英雄中攻击值大于200的英雄的数量
    
    　　　　　　select count(id) from sanguo where country='蜀国' and attack>200;
    
    　　2、group by :给查询的结果进行分组
    
    　　　　 eg1 : 计算每个国家的平均攻击力
    
    　　　　　　select country,avg(attack) from sanguo group by country;
    
    　　　　eg2 : 所有国家的男英雄中 英雄数量最多的前2名的 国家名称及英雄数量
    
    　　　　　　select country,count(id) as number from sanguo where gender='M' group by country order by number DESC limit 2;
    
    　　　　注意：group by后字段名必须要为select后的字段，查询字段和group by后字段不一致,则必须对该字段进行聚合处理(聚合函数)
    
    　　3、having语句：对分组聚合后的结果进行进一步筛选
    
    　　　　eg1 : 找出平均攻击力大于105的国家的前2名,显示国家名称和平均攻击力
    
    　　　　　　select country,avg(attack) from sanguo group by country having avg(attack)>105 order by avg(attack) DESC limit 2;
    
    　　　　注意：
    
    　　　　　　1）having语句通常与group by联合使用
    
    　　　　　　2）having语句存在弥补了where关键字不能与聚合函数联合使用的不足,where只能操作表中实际存在的字段,having操作的是聚合函数生成的显示列
    
    　　4、distinct语句：不显示字段重复值
    
    　　　　eg1 : 表中都有哪些国家
    
    　　　　　　 select distinct name,country from sanguo;
    
    　　　　eg2 : 计算一共有多少个国家
    
    　　　　　　select count(distinct country) from sanguo;
    
    　　　　注意：
    
    　　　　　　1）distinct和from之间所有字段都相同才会去重
    
    　　　　　　2）distinct不能对任何字段做聚合处理
    
    　　5、查询表记录时做数学运算
    
    　　　　　　运算符 ： + - * / % **
    
    　　　　eg1: 查询时显示攻击力翻倍
    
    　　　　　　select name,attack*2 from sanguo;
    
    　　　　eg2: 更新蜀国所有英雄攻击力 * 2
    
    　　　　　　update sanguo set attack=attack*2 where country='蜀国';

### mysql执行计划
    explain select * from sms_batch where batch_id = '123'