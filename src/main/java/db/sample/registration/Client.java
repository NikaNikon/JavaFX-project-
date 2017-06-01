package db.sample.registration;

public class Client {
    private int id;
    private String name;
    private String last_name;
    private String login;
    private String pass;
    private String phone;
    private String email;
    public static class Builder{
        //обязательные поля
        private int id;
        private String name;
        private String last_name;
        private String login;
        private String pass;
        //необязательные поля
        private String phone;
        private String email;
        public Builder(){}
        public Builder(String name, String last_name, String login, String pass){
            this.name = name;
            this.last_name = last_name;
            this.login = login;
            this.pass = pass;
        }
        public Builder(int id, String name, String last_name, String login, String pass, String phone, String email){
            this.id = id;
            this.name = name;
            this.last_name = last_name;
            this.login = login;
            this.pass = pass;
            this.phone = phone;
            this.email = email;
        }
        public Builder(Client client){
            this.name = client.name;
            this.last_name = client.last_name;
            this.login = client.login;
            this.pass = client.pass;
            this.phone = client.phone;
            this.email = client.email;
        }
        public Builder setPhone(String phone){
            this.phone = phone;
            return this;
        }
        public Builder setEmail(String email){
            this.email = email;
            return this;
        }
        public Client build(){
            return new Client(this);}
        }
        private Client(Builder builder){
            this.id = builder.id;
            this.name = builder.name;
            this.last_name = builder.last_name;
            this.login = builder.login;
            this.pass = builder.pass;
            this.phone = builder.phone;
            this.email = builder.email;
    }

    public void printClient(){
        System.out.println(id);
        System.out.println(name);
        System.out.println(last_name);
        System.out.println(login);
        System.out.println(pass);
        System.out.println(phone);
        System.out.println(email);
    }
    public int getId() { return id; }
    public String getName(){
        return name;
    }
    public String getLast_name(){
        return last_name;
    }
    public String getLogin(){
        return login;
    }
    public String getPass(){
        return pass;
    }
    public String getPhone(){
        return phone;
    }
    public String getEmail(){
        return email;
    }
}
