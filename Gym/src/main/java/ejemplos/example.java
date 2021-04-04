package ejemplos;

import clases.User;
import dao.DAOUserMongo;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @autor shah
 */
public class example {

    //Pasar datos entre actividades
    /*
    //Scene1
    @FXML
    private void sendData(MouseEvent event) {
        // Step 1
        User u = mainScreenTable.getSelectionModel().getSelectedItems().get(0);
        // Step 2
        Node node = (Node) event.getSource();
        // Step 3
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            // Step 4
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MainScreen.fxml"));
            // Step 5
            stage.setUserData(u);
            // Step 6
            Scene scene = new Scene(root);
            stage.setScene(scene);
            // Step 7
            stage.show();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
    }

    //Scene2
    @FXML
    private void receiveData(MouseEvent event) {
        // Step 1
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        // Step 2
        User u = (User) stage.getUserData();
        // Step 3
        reservationUserDni.setText(u.getDni());
        reservationUserName.setText(u.getName());
        reservationUserLastname.setText(u.getLastname());
    }*/

    /*
        mainScreenAddBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    App.setRoot("insertUser");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });*/


    //DAOUserMongo.java
    /*
    public String dateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }


    public static void main(String[] args) throws ParseException {

        try (MongoClient mongoClient = MongoClients.create()) {
            MongoDatabase database = mongoClient.getDatabase("gym");
            MongoCollection<Document> collection = database.getCollection("users");

            User user = new User("12345678X", "shah", "sawar", 12, 1);
            collection.insertOne(toDoc(user));
        }

        User user = new User("1234", "user1", "userlastname", new SimpleDateFormat("dd-MM-yyyy").parse("10-10-2020"));
        User user2 = new User("1235", "user2", "userlastname", new SimpleDateFormat("dd-MM-yyyy").parse("11-10-2020"));
        User user3 = new User("1236", "user3", "userlastname", new SimpleDateFormat("dd-MM-yyyy").parse("12-10-2020"));
        User user4 = new User("1237", "user4", "userlastname", new SimpleDateFormat("dd-MM-yyyy").parse("13-10-2020"));
        User user5 = new User("1237", "user4", "userlastname", new SimpleDateFormat("dd-MM-yyyy").parse("13-10-2020"));
        DAOUserMongo d = new DAOUserMongo();
        d.insert(user);
        d.insert(user2);
        d.insert(user3);
        d.insert(user4);
        d.insert(user5);

        System.out.println("The last id " +d.getLastUserId());

        db.users.update(
           { user_code: 1 },
           { $push: { reservations: 1 } }
        );

        db.users.updateOne(
          { user_code: 1 },
          { $set: { "list.$[ele].reservation_code": 123 } },
          { arrayFilters: [ { "ele.user_code": 1 } ] }
        )

        db.users.find({$and:[{user_code:1}, {"reservations.workout_plane": true}]})


        User userTmp = new User("123456789", "user1", "userlastname", new SimpleDateFormat("dd-MM-yyyy").parse("13-10-2020"));
        DAOUserMongo mongo = new DAOUserMongo();
        //User userTmp = mongo.getByIdentifier(1);
        //mongo.insert(userTmp);
        //mongo.delete(userTmp);
    }*/


    /*
    //DAOReservationMongo update
    UpdateResult result = collection.updateOne(filter, update, options);
    System.out.println(result.toString());

    Document doc = collection.find(eq("reservations.reservation_code", 123)).first();
        System.out.println(doc.getInteger("reservation_code"));

    Document mydoc = collection.find(eq("user_code", reservation.getUserCode())).first();
        if(mydoc.containsKey("reservations"))

    {
        List<Document> listTmp = mydoc.getList("reservations", Document.class);

        for (Document documentTmp : listTmp) {
            if (documentTmp.getInteger("reservation_code") == integer) {
                System.out.println(documentTmp.getInteger("reservation_code"));
                System.out.println(documentTmp.getInteger("user_code"));
                System.out.println(documentTmp.getString("room_name"));
            }
        }


    public static void main(String[] args) throws ParseException {


        ConnectionMongo conn = new ConnectionMongo();
        //MongoCollection<Document> collection = conn.start();

        //Document cursor = collection.deleteOne(eq("reservations.reservation_code", 1)).first();


        //Reservation r = new Reservation(123, new SimpleDateFormat("dd-MM-yyyy").parse("10-10-2020"), 2, "sala fitness", true);
        //DAOReservationMongo d = new DAOReservationMongo();
        //d.insert(r);
        //d.update(r, 123);
        /*
        MongoCollection<Document> collection = ConnectionMongo.start();
        Document myDoc = collection.find(eq("reservations.user_code", 1)).sort(new Document("_id", -1)).first();+

        System.out.println(myDoc.getInteger("reservation_code"));


}*/


}
