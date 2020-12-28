#### 父工程名字
    rootProject.name = 'cas-cloud'
    rootProject.name 为构建分配一个名称，该名称将覆盖在构建目录所在目录后命名该构建的默认行为。建议设置固定名称，因为如果共享项目，则文件夹可能会更改，例如，作为Git存储库的根目录。

#### 子组件
    include 'cas-web'
    include 'cas-dao'
    include 'cas-service'
    include 'cas-core'
    include 'cas-test'
    include 'cas-common'
    include("lib")定义构建由一个名为的子项目组成，该子项目lib包含实际的代码和构建逻辑。可以通过其他include(…​)语句添加更多子项目。

### settings.gradle
    设置文件以定义构建名称和子项目

