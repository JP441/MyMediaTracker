module org.jp441.mymediatracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires java.net.http;
    requires org.json;
    requires io.github.cdimascio.dotenv.java;


    opens org.jp441.mymediatracker to javafx.fxml;
    exports org.jp441.mymediatracker;
}