

package application;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

	/**
	* Käyttöliittymä josta annetaan robotille käskyjä
	*
	* @author Paula ja Johanna
	* @version 1.0
	*/
	public class MainPC extends Application implements MainGUI_IF{

		/**
		 * Tuo kontrollerin käyttöliittymään
		 */
		Sijainnit_IF controller;

		/**
		 * Alustaa Socket olion nulliksi
		 */
		private static Socket s = null;
		/**
		 * Alustaa DataOutPutStream olion nulliksi
		 */
		private static DataOutputStream out = null;

		/**
		 * Luo yhteyden robottiin
		 */
		public static void Connection(){

			try{
				s = new Socket("10.0.1.1", 1111);
				System.out.println("yhteys muodostetu");
				out = new DataOutputStream(s.getOutputStream());
			}catch (IOException e){
				System.out.println("Yhteyden muodostus ei onnistu");
				e.printStackTrace();
			}
		}

		/**
		 * Antaa robotille tiedon käyttöliittymästä valituista x ja y arvoista
		 * @param x akselin koordinaatti
		 * @param y akselin koordinaatti
		 */
		public static void write(double x, double y){

				try {
					out.writeDouble(x);
					out.writeDouble(y);
					out.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		/**
		 * Antaa roobotille tiedon, jos käyttöliittymän stop nappia painetaan
		 * @param value palauttaa true tai false arvon
		 */
		public static void writeBoolean(boolean value){
			try {
				out.writeBoolean(value);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


		/**
		 * Sulkee yhteyden robottiin
		 */
		public static void connClose(){
			if(s != null && out != null){
				try {
					s.close();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}



		/*
		 * Luo yhteydet Modeliin ja Kontrolleriin
		 */
		public void init(){
			Model_IF model = new Model();
			controller = new Sijainnit(this, model);}

		/*
		 * Käyttöliittymä käynnistyy, luo yhteydet
		 */
		@Override
		public void start(Stage primaryStage) {
			try {

				primaryStage.setTitle("FX- Laatikonhaku");

				HBox root = createHBox();
				Scene scene = new Scene(root);

				//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * Luo valintaikkunan
		 * @return HBox
		 */
		private HBox createHBox() {

	        HBox hBox = new HBox();
	        hBox.setPadding(new Insets(15, 12, 15, 12)); // marginaalit ylä, oikea, ala, vasen
	        hBox.setSpacing(10);   // noodien välimatka 10 pikseliä


	        ListView<String> laatikkolista = new ListView<String>();
	        laatikkolista.setPrefSize(120, 120);

	        ObservableList<String> laatikot = FXCollections.observableArrayList ();
	        laatikot.add("Laatikko2");
	        laatikot.add("Laatikko3");
	        laatikot.add("Laatikko4");
	        laatikot.add("Laatikko5");
	        laatikot.add("Laatikko6");
	        laatikkolista.setItems(laatikot);


	        VBox vaateboxi = new VBox();
	        vaateboxi.getChildren().addAll(new Label("Laatikot"), laatikkolista);


	        GridPane ostajaGridi =  new GridPane();

	        ostajaGridi.setHgap(10);
	        ostajaGridi.setVgap(10);
	        ostajaGridi.setPadding(new Insets(0, 10, 0, 10));

	        Label laatikonTiedot = new Label("Laatikon sijainti:");
	        ostajaGridi.add(laatikonTiedot, 0, 0, 2, 1);

	        Text virhe = new Text();
	        ostajaGridi.add(virhe, 0, 3);

	        Text X = new Text("X koordinaatti varastossa: ");
	        ostajaGridi.add(X, 0, 4);

	        Text sijainti1 = new Text();
	        ostajaGridi.add(sijainti1, 0, 5);

	        Text Y = new Text("Y koordinaatti varastossa: ");
	        ostajaGridi.add(Y, 0, 6);

	        Text sijainti2 = new Text();
	        ostajaGridi.add(sijainti2, 0, 7);

	        Image image = new Image("file:///C:/Users/Paula/Documents/Koulu/Leego/Ohjelmointiprojekti/robo1.jpeg");

			ImageView iv1 = new ImageView();
			iv1.setImage(image);
			iv1.setFitWidth(500);
			iv1.setPreserveRatio(true);
			iv1.setSmooth(true);
			iv1.setCache(true);

			Image img = new Image("file:///C:/Users/Paula/Documents/Koulu/Leego/Ohjelmointiprojekti/robo2.jpeg");

			ImageView iv2 = new ImageView(img);
			iv2.setImage(img);
			iv2.setFitWidth(500);
			iv2.setPreserveRatio(true);
			iv2.setSmooth(true);
			iv2.setCache(true);


	        BorderPane buttonPane = new BorderPane();
	        buttonPane.setMaxHeight(120);

	        Button buttonOsta = new Button("Hae");
	        buttonPane.setTop(buttonOsta);
	        buttonOsta.setPrefSize(100, 20);
	        buttonOsta.setOnAction(new EventHandler<ActionEvent>() {



	            /*
	             * Toiminto mitä tapahtuu kun valitaan joki laatikko
	             * Tiedot siirtyvät controllerille
	             */
	            @Override
	            public void handle(ActionEvent e) {


	                virhe.setText(laatikkolista.getSelectionModel().getSelectedItem());

	                controller.setName(laatikkolista.getSelectionModel().getSelectedItem());
	                hBox.getChildren().remove(iv1);
	            	hBox.getChildren().add(iv2);
	                sijainti1.setText(new Double(controller.getX()).toString());
	                sijainti2.setText(new Double(controller.getY()).toString());
	                MainPC.write(controller.getX(), controller.getY());


	                MainPC.writeBoolean(false);

  	            }


	        });

	        buttonPane.setMaxHeight(120);
	        Button buttonStop = new Button("STOP");
	        buttonPane.setBottom(buttonStop);
	        buttonStop.setPrefSize(100, 20);
	        buttonStop.setOnAction(new EventHandler<ActionEvent>() {

				/*
				 * Stopnapista tapahtuva toiminto,
				 * eli robotti pysähtyy
				 */
				@Override
				public void handle(ActionEvent e) {

					System.out.println("NYT LOPETAN");

					MainPC.writeBoolean(true);
				}

	        });



	        hBox.getChildren().addAll(vaateboxi, ostajaGridi, buttonPane, buttonStop);
	        hBox.getChildren().add(iv1);
	        return hBox;}





		/**
		 * Avaa yhteydet
		 * @param args
		 */
		public static void main(String[] args)  {

			MainPC.Connection();

			launch(args);

			MainPC.connClose();

		}
	}
