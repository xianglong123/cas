package com.cas.designMode.Behavioral.ChainModel;

public abstract class AbstractLogger {
   public static int INFO = 1;
   public static int DEBUG = 2;
   public static int ERROR = 3;

   protected int level;

   //责任链中的下一个元素
   protected AbstractLogger nextLogger;

   public void setNextLogger(AbstractLogger nextLogger){
      this.nextLogger = nextLogger;
   }

   // 父类的核心方法
   public void logMessage(int level, String message){
      // 如果满足责任要求则执行
      if(this.level <= level){
         write(message);
         return ;
      }

      // 有下面的责任链则向下走......
      if(nextLogger !=null){
         nextLogger.logMessage(level, message);
      }
   }

   abstract protected void write(String message);

}
