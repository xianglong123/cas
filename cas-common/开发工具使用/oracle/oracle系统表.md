## v$locked_object (列出系统每个事务获取的所有锁，它显示哪些会话在什么对象和什么模式下持有DML锁)
    XIDUSN	NUMBER	撤消段号
    
    XIDSLOT	NUMBER	插槽号
    
    XIDSQN	NUMBER	序列号
    
    OBJECT_ID	NUMBER	对象ID被锁定
    
    SESSION_ID	NUMBER	会话ID
    
    ORACLE_USERNAME	VARCHAR2(30)	Oracle用户名
    
    OS_USER_NAME	VARCHAR2(30)	操作系统用户名
    
    PROCESS	VARCHAR2(24)	操作系统进程ID
    
## oracle 当前数据库锁sql
    select b.owner TABLEOWNER, b.object_name TABLENAME, c.OSUSER LOCKBY,
           c.USERNAME LOGINID, c.sid SID, c.SERIAL# SERIAL from v$locked_object a,dba_objects b, v$session c where b.object_id = a.object_id AND a.SESSION_ID =c.sid;

## 杀死sql进程
    alter system kill session 'SID, SERIAL';