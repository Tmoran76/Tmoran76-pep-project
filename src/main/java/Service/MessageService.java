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
    
}
