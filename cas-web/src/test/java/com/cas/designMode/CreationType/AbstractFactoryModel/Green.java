package com.cas.designMode.CreationType.AbstractFactoryModel;

public class Green implements Color {

   @Override
   public void fill() {
      System.out.println("Inside Green::fill() method.");
   }
}
