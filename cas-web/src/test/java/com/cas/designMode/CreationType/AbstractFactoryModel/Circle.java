package com.cas.designMode.CreationType.AbstractFactoryModel;

public class Circle implements Shape {

   @Override
   public void draw() {
      System.out.println("Inside Circle::draw() method.");
   }
}
