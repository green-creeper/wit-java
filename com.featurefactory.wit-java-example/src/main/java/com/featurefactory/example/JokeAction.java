package com.featurefactory.example;

import com.featurefactory.witjava.ActionHandler;
import com.featurefactory.witjava.model.ChatContext;

import java.util.*;


public class JokeAction implements ActionHandler{

    private List<String> jokes;
    private Random randomGenerator;

    public JokeAction() {
        jokes = Arrays.asList(
                "What is a robot’s favorite type of music? \n Heavy metal!",
                " Why did the robot go back to robot school? \n Because his skills were getting a little rusty!",
                "What do you get when you cross a robot and a tractor? \n A trans-farmer!");
        randomGenerator = new Random();
    }

    @Override
    public ChatContext run(Map<String, List<Map<String, Object>>> entities, ChatContext context) {
        if(entities.containsKey("intent") && entities.get("intent").get(0).get("value").equals("joke")){
            context.setValue("joke", getRandomJoke());
        }
        return context;
    }

    private String getRandomJoke() {
        int index = randomGenerator.nextInt(jokes.size());
        return jokes.get(index);
    }
}
