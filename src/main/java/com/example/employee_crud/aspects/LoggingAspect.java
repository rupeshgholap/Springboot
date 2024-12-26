package com.example.employee_crud.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution (* com.example.employee_crud.controllers.EmployeeController.getDependent(..))")
    public void dependent(){}

    @Before("dependent()")
    public void dependentCalled()
    {
        System.out.println("getDependent method has been called");
    }
    @After("dependent()")
    public void dependentCompleted()
    {
        System.out.println("getDependent method has been completed execution");
    }


    int getallemployeecount = 0;
    @Before("execution (* com.example.employee_crud.controllers.EmployeeController.getAllEmployee(..))")
    public void countGetAllEmployee(){
        getallemployeecount++;
        System.out.println("getAllEmployee methode has been called for "+getallemployeecount+" Time");
    }
    @After("execution (* com.example.employee_crud.controllers.EmployeeController.getAllEmployee(..))")
    public void afterGetallemployee()
    {
        System.out.println("getAllEmployee method is Excetuted");
    }
}
