import Axios from "axios";

const MESSAGES_API_URL = "http://localhost:8080/api/messages";

class ApiMessageService {
  getAllMessages() {
    return Axios.get(MESSAGES_API_URL);
  }

  getMessageById(messageId) {
    return Axios.get(MESSAGES_API_URL + "/" + messageId);
  }

  addMessage(message) {
    return Axios.post(MESSAGES_API_URL, message);
  }

  updateMessage(message) {
    return Axios.put(MESSAGES_API_URL + "/" + message.id, message);
  }

  deleteMessage(messageId) {
    return Axios.delete(MESSAGES_API_URL + "/" + messageId);
  }
}
export default new ApiMessageService();
