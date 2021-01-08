package com.cas.owner.designMode.Behavioral.ChainModel;

public class ChainPatternDemo {

   private static AbstractLogger getChainOfLoggers(){

      AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
      AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
      AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

      errorLogger.setNextLogger(fileLogger);
      fileLogger.setNextLogger(consoleLogger);

      // 这反的是最重的责任对象
      return errorLogger;
   }

   public static void main(String[] args) {
      // 最重的责任权重
      AbstractLogger loggerChain = getChainOfLoggers();

      loggerChain.logMessage(AbstractLogger.INFO, "This is an information.");
      System.out.println();

      loggerChain.logMessage(AbstractLogger.DEBUG,
         "This is a debug level information.");
      System.out.println();

      loggerChain.logMessage(AbstractLogger.ERROR,
         "This is an error information.");
      System.out.println();
   }
}
