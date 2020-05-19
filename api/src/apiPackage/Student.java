package apiPackage;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author user
 */
public class Student implements Serializable{
    private String studName;
    private String studId;
    private double studMark;
    private String course;
    private String studGrade;
    
    public Student() {
        this.studId="NULL";
        this.studMark=0;
            
    }
    
    public Student(String id, double mark)
    {
        this.studId=id;
        this.studMark=mark;
    }
    
    public void setName(String name)
    {
        this.studName=name;
    }
    
    public String getName()
    {
        return studName;
    }
    
     public void setCourse(String course)
    {
        this.course=course;
    }
    
    public String getCourse()
    {
        return course;
    }
    public void setId(String id)
    {
        this.studId=id;
    }
    public void setMark(double mark)
    {
        this.studMark=mark;
    }
    
    public String getId()
    {
        return studId;
    }
    
    public double getMark()
    {
        return studMark;
    }
    
     public String setGrade(double mark)
    {
       if(mark>=90)
       {
           this.studGrade="A+";
       return "A+";
       }else if(mark>=85&&mark<90){
           this.studGrade="A";
       return "A";}
       else if(mark>=80&&mark<85)
       { this.studGrade="A-";
       return "A-";
       }else if(mark>=75&&mark<80){
           this.studGrade="B+";
       return "B+";}
       else if(mark>=70&&mark<75){
           this.studGrade="B";
       return "B";}
       else if(mark>=65&&mark<70){
           this.studGrade="B-";
       return "B-";}
       
       else if(mark>=60&&mark<65){
           this.studGrade="C+";
       return "C+";}
       
       else if(mark>=50&&mark<60)
       {this.studGrade="C";
       return "C";
       }
       else if(mark>=45&&mark<50){
           this.studGrade="C-";
       return "C-";}
       
       else if(mark>=40&&mark<45)
       {    this.studGrade="D";
       return "D";
       }else if(mark>30&&mark<=40)
       {
           this.studGrade="f(X)";
       return "f(x)";
       }else 
       {
           this.studGrade="F";
       return "F";
       }}

    
    public String getGrade()
    {
        return studGrade;
    }
    public void displayStudInfo()
    {
        System.out.println("Students' id " + getId());
        System.out.print("\t\tStudents' mark "+ getMark());
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
            
            
}
