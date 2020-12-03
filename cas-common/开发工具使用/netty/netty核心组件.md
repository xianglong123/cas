#### netty 核心组件

##### bootstrap and serverBootStrap
![avatar](bootstrap.png)

##### Future and ChannelFuture
![avatar](future.png)

##### channel
![avatar](channel.png)
![avatar](channel2.png)

##### selector
![avatar](selector.png)

##### channelHandler
![avatar](channelHandler.png)
![avatar](channelHandler2.png)
![avatar](channelHandler_with_children_method.png)

##### channelPipeline
![avatar](channelPipeline.png)
![avatar](pipeline调用链.png)
![avatar](channelPipelineAdd.png)

##### channelHandlerContext
![avatar](channelhandlerContext.png)

##### channelOption
![avatar](channelOption.png)

##### EventLoopGroup and NioEventLoopGroup
![avatar](EventLoop1.png)
![avatar](EventLoop2.png)
![avatar](EventLoop3.jpg)

##### Unpooled
![avatar](Unpooled.png)
、、、

        ByteBuf buffer = Unpooled.buffer(10);

        for (int i = 0; i < 10; i++) {
            buffer.writeByte(i);
        }

        System.out.println("capacity=" + buffer.capacity()); // 10

        // 输出

        for (int i = 0; i < buffer.capacity(); i++) {
            System.out.println(buffer.readByte());
        }
        
、、、

