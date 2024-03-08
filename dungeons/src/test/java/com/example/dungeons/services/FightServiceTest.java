package com.example.dungeons.services;


import com.example.dungeons.controllers.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FightServiceTest {

String action = "{\"arg\":\"attack\",\"id\":\"enemy: 8.225601629927667\"}";
    @Test
    void getAction() throws JSONException {
        Gson gson = new Gson();
        Response response = gson.fromJson(action, Response.class);
        System.out.println(response.getArg() + response.getId());
    }
}