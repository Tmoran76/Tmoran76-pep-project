package Controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import DAO.MessageDao;
import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    MessageService ms;
    AccountService as;
    public SocialMediaController(){
        ms = new MessageService();
        as = new AccountService();
    }
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("/messages", this::getAllMessages);
        app.post("/register", this::createNewAccount);
        app.post("/login", this::verifyAccount);
        app.post("/messages", this::postMessage);
        app.get("/messages/{message_id}", this::getMessageByID);
        app.delete("/messages/{message_id}", this::deleteMessageByID);

        return app;
    }

    /**
     

     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getAllMessages(Context context) {
        context.json(ms.getAllMessages());
    }
    private void createNewAccount(Context context) throws JsonMappingException, JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(context.body(), Account.class);
        Account newAccount = as.newAccount(account);
        if(newAccount == null){
            context.status(400);
        }
        else {
            context.json(newAccount);
        }

    }
    private void verifyAccount(Context context) throws JsonMappingException, JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(context.body(), Account.class);    
        Account checkAccount = as.verAccount(account);
        if(checkAccount == null){
            context.status(401);
        }
        else{
            context.json(checkAccount);
        }
    }
    private void postMessage(Context context) throws JsonMappingException, JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        Message message = om.readValue(context.body(), Message.class);
        Message newMessage = ms.newMessage(message);
        if(newMessage == null){
            context.status(400);
        }
        else{
            context.json(newMessage);
        }
    }
    private void getMessageByID(Context context){
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        Message message = ms.messageByID(message_id);
        if (message == null){
            context.body();
        }
        else {
            context.json(message);
        }               
    }
    private void deleteMessageByID(Context context){
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        Message message = ms.messageByID(message_id);
        int messagesDeleted = ms.deleteMessageByID(message_id);        
        if (messagesDeleted == 0){
            context.body();
        }
        else {
            context.json(message);
        }
    }
    

    


}