package P02.full_credit;

import java.util.Scanner;
public class ToDo {
    public static void main(String[] args){
    int numItems=5;
    if(args.length>0){
    numItems=Integer.parseInt(args[0]);
    } 
    Scanner sc=new Scanner(System.in);
     Item[] list=new Item[numItems];
     for(int i=0; i<numItems; i++){
        System.out.println("Task no." + i);
       String task=sc.nextLine();
       System.out.println("Priority order");
       int priority=sc.nextInt();
    //    Item tmpItem=new Item();
    //    tmpItem.task=task;
    //    tmpItem.priority=priority;
       list[i].task=task;
       list[i].priority=priority;

     }
    }
  
  
}
