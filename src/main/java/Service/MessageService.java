package Service;

import java.util.List;

import DAO.MessageDao;
import Model.Message;

public class MessageService {
    MessageDao dao;

    public MessageService(){
        dao = new MessageDao();
    }
    public List<Message> getAllMessages(){
        return dao.getAllMessages();        
    }
    public Message newMessage(Message message){
        if(message.getMessage_text() == null){
            return null;
        }
        else if(message.getMessage_text().length() > 255){
            return null;
        }

        return dao.createNewMessage(message);

    }
    
}
