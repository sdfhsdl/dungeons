package com.example.dungeons.services;

import com.example.dungeons.models.fighter.Fighter;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class LogFightService {
    private String allLog = "";
    private String lastActionLog = "";
    public void setLog(Object[] args){
        Fighter activeFighter = (Fighter)args[2];
        String nameActiveFighter = activeFighter.getName();
        createActionToLog(new String[]{(String)args[0], (String)args[1], nameActiveFighter});
    }
    public String getAllLog(){
        return allLog;
    }
    public String getLastActionLog(){
        return lastActionLog;
    }
    private void createActionToLog(String[] args){
        lastActionLog = "\n" + "________________________" + "\n" + args[2] + " " + args[0] + " " + args[1];
        allLog += lastActionLog + "\n";
    }
}
