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
    
}
