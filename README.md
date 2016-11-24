# wit-java
Java client library for Wit.ai

### The library is under development so you are welcome to do pull requests


#### Getting started with library

Init client

```java
WitClient client = new WitClientBuilder(config.getWitToken())
                       .addActionHandler("getJoke", new JokeAction())
                       .setMessageHandler(new BotMessageHandler(this)).build();
```

Implement handlers

```java
public class BotMessageHandler implements MessageHandler {

    @Override
    public void sendMessage(String message, Map<String, Object> chatMetadata) {
        Bot.sentMessage(message);
    }
}

public class JokeAction implements ActionHandler{

    @Override
    public ChatContext run(Map<String, List<Map<String, Object>>> entities, ChatContext context) {

        if(entities.containsKey("joke")){
            String joke = JokeDAO.getRandomJoke();
            context.setValue("joke", joke);
        }
        return context;
    }
}
```

Process incoming messages


```java
            String session = sessions.containsKey(getChatId())? sessions.get(getChatId()):UUID.randomUUID().toString();
            boolean isContinue = client.converse(message, session, context);

            if(isContinue){
                sessions.put(getChatId(), session);
            } else {
                sessions.remove(getChatId());
            }
```

