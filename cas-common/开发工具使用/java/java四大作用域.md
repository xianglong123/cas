### java四大作用域
    1.pageScope
    2.requestScope
    3.sessionScope
    4.applicationScope
    
### 四大作用域的区别
    1. page指当前页面有效，在一个jsp页面里有效
    
    2. request 指在一次请求的全过程中有效，即从http请求到服务器处理结束，
    返回响应的整个过程，存放在HttpServletRequest对象中。在这个过程中可以使用forward方式跳转多个jsp。
    在这些页面里你都可以使用这个变量。
    
    3. session是用户全局变量，在整个绘画期间都有效。只要页面不关闭就一直有效（或者直到用户一直未活动导致会话过期，
    默认session过期时间为30分钟，或调用HttpSession的invalidate()方法）。存放在HttpSession对象中
    
    4. application是程序全局变量，对每个用户每个页面都有效。存放在ServletContext对象中。它的存活时间最长，不手工删除，可以一直使用
    
    总结：当数据只需要在下一个forward有用时，用request就够了。
         若数据不只是在下一个forward有用时，就用session.
         上下文，环境信息之类的，用application
         

### 如何使用？
    page里的变量没法从index.jsp传递到test.jsp。只要页面跳转了，它们就不见了。  
    request里的变量可以跨越forward前后的两页。但是只要刷新页面，它们就重新计算了。  
    session的变量一直在累加，开始还看不出区别，只要关闭浏览器，再次重启浏览器访问这页，session里的变量就重新计算了。  
    application里的变量一直在累加，除非你重启tomcat，否则它会一直变大。  
    而作用域规定的是变量的有效期限。  
    
          如果把变量放到pageContext里，就说明它的作用域是page，它的有效范围只在当前jsp页面里。 从把变量放到pageContext开始，到jsp页面结束，你都可以使用这个变量。  
    
          如果把变量放到request里，就说明它的作用域是request，它的有效范围是当前请求周期。 所谓请求周期，就是指从http请求发起，到服务器处理结束，返回响应的整个过程。在这个过程中可能使用forward的方式跳转了多个jsp页面，在这些页面里你都可以使用这个变量。 
    
         如果把变量放到session里，就说明它的作用域是session，它的有效范围是当前会话。 
    所谓当前会话，就是指从用户打开浏览器开始，到用户关闭浏览器这中间的过程。这个过程可能包含多个请求响应。也就是说，只要用户不关浏览器，服务器就有办法知道这些请求是一个人发起的，整个过程被称为一个会话（session），而放到会话中的变量，就可以在当前会话的所有请求里使用。 
    
          如果把变量放到application里，就说明它的作用域是application，它的有效范围是整个应用。 整个应用是指从应用启动，到应用结束。没有说“从服务器启动，到服务器关闭”，是因为一个服务器可能部署多个应用，当然你关闭了服务器，就会把上面所有的应用都关闭了。application作用域里的变量，它们的存活时间是最长的，如果不进行手工删除，它们就一直可以使用。  
         与上述三个不同的是，application里的变量可以被所有用户共用。如果用户甲的操作修改了application中的变量，用户乙访问时得到的是修改后的值。这在其他scope中都是不会发生的，page, request, session都是完全隔离的，无论如何修改都不会影响其他人的数据。


### 如何存值在scop
    1. page 就在js中设置值，类似 var a = 1;
    ------------------------
    2. request 存值
        public void b(HttpServletRequest request) {
            request.setAttribute("x", "x");
        }
    ------------------------
    3. session 存值
        public void c(HttpServletRequest request) {
            request.getSession().setAttribute("xl", "123");
        }
    4. application 上下文存值
    后端
        public void a(HttpServletRequest request) {
            ServletContext application = request.getSession().getServletContext();
            application.setAttribute("name", "xl");
        }
    前端获取值
        application.getAttribute(key);
    
