### go get 卡 慢 解决方案
    # Enable the go modules feature
    export GO111MODULE="on"
    # Set the GOPROXY environment variable
    export GOPROXY="https://goproxy.io"
    
    
## 下载所有依赖包
    go list -m -json all
    
    go get -d -v ./...

### operator 部署全过程
    https://liqiang.io/post/kubernetes-all-about-crd-part06-kubebuilder-and-operator-sdk-d6e0858e
    
    
### 删除tag为none的资源
    docker images|grep none|awk '{print $3}'|xargs docker rmi

### 解决init 版本问题
    go env -w GOPROXY=https://goproxy.cn
    export GOPROXY=https://goproxy.cn
    https://www.cnblogs.com/haojile/p/13144030.html
    
    
### gcc 安装
    https://www.jianshu.com/p/87ebf6e73576
    
### 疑难杂症
    1.go语言默认代理中国区不能使用 https://www.sohu.com/a/338891700_657921

### operator1.0 的项目
    https://github.com/banzaicloud/kafka-operator
    
### kubebuilder 步骤
    1. kubebuilder create api --group webapp --version v1 --kind Guestbook

### go 国内下载
    http://mirrors.ustc.edu.cn/golang/


### go 项目打包
    
