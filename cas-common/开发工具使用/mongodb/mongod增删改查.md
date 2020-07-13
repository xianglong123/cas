## mongodb 基本操作
    查：db.getCollection(‘集合名’).find({‘字段名’:‘字段属性’})；
    改：db.集合名.update({条件},{$set:{更改}})
    增：db.集合名.insert（{字段名:"字段值"，字段名:"字段值"}）
    删：db.user.remove({user_id: "4"})