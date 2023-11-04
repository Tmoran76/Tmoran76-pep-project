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
        List<Integer> allPosters = dao.getPoster();
        if(message.getMessage_text().length() <1){
            return null;
        }
        else if(message.getMessage_text().length() > 254){
            return null;
        }
        else if(message != null){
            for(int i: allPosters){
                if(i == message.getPosted_by()){
                    return dao.createNewMessage(message);
                }
            }
        }

        return dao.createNewMessage(message);

    }
    public Message messageByID(int message){
        return dao.getMessageByID(message);
    }
    public int deleteMessageByID(int message_id){
        return dao.deleteMessageByID(message_id);
    }
    public Message updateMessageByID(int message_id, Message message){
        List<Message> allMessages = dao.getAllMessages();
        if(message_id >0){
            for(Message e: allMessages){
                if(e.getMessage_id() == message_id){
                    System.out.println(e.getMessage_text());
                    if(!message.getMessage_text().isBlank()){
                        if(message.getMessage_text().length()<255){
                            dao.updateMessageByID(message_id, message);
                            return dao.getMessageByID(message_id);
                        }
                    }
                }
            }
        }
        return null;

    }
    public List<Message> getMessagesByUser(int user){
        return dao.getMessagesFromUser(user);
    }
    
}
