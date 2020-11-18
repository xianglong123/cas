### mysql中table无法插入中文
    解决方案：修改编码集：ALTER TABLE TABLE_NAME CONVERT TO CHARACTER SET utf8mb4;
