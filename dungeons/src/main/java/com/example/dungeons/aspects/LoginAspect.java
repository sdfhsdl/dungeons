package com.example.dungeons.aspects;



import com.example.dungeons.controllers.Response;
import com.example.dungeons.services.FightService;
import com.example.dungeons.services.LogFightService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoginAspect {
    private final LogFightService logFightService;
    private final FightService fightService;
    public LoginAspect(LogFightService logFightService, FightService fightService){
        this.logFightService = logFightService;
        this.fightService = fightService;
    }
    @Before("@annotation(LogFight)")
    public void getLogAttack(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Response response = (Response) args[0];
        Object[] argsForLog = new Object[]{response.getArg(), response.getId(), args[1]};
        logFightService.setLog(argsForLog);

    }
}
