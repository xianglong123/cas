package com.cas.owner.dataStructrue.queue.doubleSideQueue;

public class FirstLastListQueue {
	private  FirstLastQueue fll = new FirstLastQueue();
 
	public void push(Object obj){
		fll.insertLast(obj);
	}
	
	public Object pop() throws Exception{
		return fll.deleteFirst();
	}
	
	public void display(){
		fll.display();
	}
	
	public static void main(String[] args) throws Exception{
		FirstLastListQueue fllq = new FirstLastListQueue();
		fllq.push(1);
		fllq.push(2);
		fllq.push(3);
		fllq.display();
		System.out.println(fllq.pop());
		fllq.display();
		fllq.push(4);
		fllq.display();
	}
}