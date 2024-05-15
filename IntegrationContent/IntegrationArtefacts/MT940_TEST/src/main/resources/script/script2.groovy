
def Message processData(Message message) {
    def body = message.getBody();
    
    def root = body.split('$');
    
    
    message.setProperty("test",root);
    return message;
}