import javafx.event.*;
import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class AllLightsOn extends Application{
	private String ON = "-fx-background-color: yellow;-fx-background-insets: 0, 1";
	private String OFF = "-fx-background-color: blue;-fx-background-insets: 0, 1";
	int on = 0;
	int off = 25;
	String id;
	Button button[][]=new Button[5][5];
    Button resetButton = new Button("Reset");
    Button countOnButton = new Button("On: " + on);
    Button countOffButton = new Button("Off: " + off);
    BorderPane root = new BorderPane();
	//GridPane grid = new GridPane();
	TilePane grid = new TilePane();
	HBox box = new HBox();
	
	
	public static void main(String [] args){
		Application.launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception{
		primaryStage.setTitle("All Lights On");
		primaryStage.setScene(new Scene(root, 550, 550));
		primaryStage.setResizable(false);
		root.setCenter(grid);
		grid.setHgap(5);
		grid.setVgap(5);
		root.setBottom(box);
		//root.setRight(resetButton);
		box.getChildren().addAll(countOnButton, countOffButton, resetButton);
		grid.setPrefColumns(5);
		grid.setPrefRows(5);
		fillTile(grid);
		  //Action for reset button
        resetButton.setOnAction(event ->reset());
  
		primaryStage.show();
	}

	public void reset() {
       	for(int m =0; m<5; m++){
       		for(int n=0; n<5; n++){
       			button[m][n].setStyle(OFF);
       		 }
       	 }
       	countButtons(button);
        countOnButton.setText("On: "+ on);
        countOffButton.setText("Off: "+ off);
	}
	

	public void fillTile(TilePane grid) {
		for(int i=0; i<5; i++ ){
			for(int j =0; j<5; j++){
				button[i][j] = new Button();
				button[i][j].setPrefSize(100, 100);
				button[i][j].applyCss();
				button[i][j].setStyle(OFF);
				button[i][j].setId(i+","+j);
				
				//with lambda and setOnAction
//				button[i][j].setOnAction(e -> {
//					Button clickedButton = (Button) e.getSource();
//				});
				
				//without lambda and setOnAction
//				button[i][j].setOnAction(new EventHandler<ActionEvent>(){
//					@Override
//					public void handle(ActionEvent e) {
//						Button clickedButton = (Button) e.getSource();
//					}
//				});
				
				//with lambda and addEventHandler
				button[i][j].addEventHandler(ActionEvent.ACTION, e -> btnClick( (Button) e.getSource() ));
				
				//without lambda and addEventHandler like in lecture
//				button[i][j].addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
//			        @Override
//			        public void handle(ActionEvent e) {
//			        	Button clickedButton = (Button) e.getSource();
//			        }
//			    });
				grid.getChildren().add(button[i][j]);
				
				
			}
		}
		
	}

	public void btnClick(Button b) {
		Button clickedButton;
        Button buttonBelow;
        Button rightButton;
        Button leftButton;
        Button aboveButton;
        clickedButton = b;
		clickedButton.setStyle(ON);
		id = clickedButton.getId();
		
		//Turn off neighboring lights:
        String col = String.valueOf(id.charAt(0));
        String row = String.valueOf(id.charAt(2));
      //if cell is in first row, then not possible to turn on light above cell
        if(row.equals("0")){
       	 buttonBelow = button[Integer.parseInt(col)][Integer.parseInt(row)+1];
       	 changeColor(buttonBelow);

//       	 //if not in last column, invert button to right of clicked button
       	 if(col.equals("4")== false ){
       		 rightButton =button[Integer.parseInt(col)+1][Integer.parseInt(row)];
       		 changeColor(rightButton);
       	 }
       	 //if not in first column, invert button to left of clicked button
       	 if(col.equals("0") ==false){
       		 leftButton = button[Integer.parseInt(col)-1][Integer.parseInt(row)];
       		 changeColor(leftButton);	 
       	 }
        }else if(row.equals("4")){
       	 aboveButton = button[Integer.parseInt(col)][Integer.parseInt(row)-1];
       	 changeColor(aboveButton);
       	 
       	 //if not in last column, invert button to right of clicked button
       	 if(col.equals("4")== false ){
       		 rightButton =button[Integer.parseInt(col)+1][Integer.parseInt(row)];
       		 changeColor(rightButton);
       	 }
       	 //if not in first column, invert button to left of clicked button
       	 if(col.equals("0") ==false){
       		 leftButton = button[Integer.parseInt(col)-1][Integer.parseInt(row)];
       		 changeColor(leftButton);	 
       	 }
       	 
       	 
        	}else{
          	 buttonBelow = button[Integer.parseInt(col)][Integer.parseInt(row)+1];	
          	 aboveButton = button[Integer.parseInt(col)][Integer.parseInt(row)-1];
          	 changeColor(aboveButton);
          	 changeColor(buttonBelow);
          	 //if not in last column, invert button to right of clicked button
          	 if(col.equals("4")== false ){
          		 rightButton =button[Integer.parseInt(col)+1][Integer.parseInt(row)];
          		 changeColor(rightButton);
          	 }
          	 //if not in first column, invert button to left of clicked button
          	 if(col.equals("0") ==false){
          		 leftButton = button[Integer.parseInt(col)-1][Integer.parseInt(row)];
          		 changeColor(leftButton);	 
          	 }
        	}//end else
        countButtons(button);//Count on and off buttons
        countOnButton.setText("On: "+ on);
        countOffButton.setText("Off: "+ off);
	
        
		
	} //end btnClick


	
	
	
	

	public void countButtons(Button[][] button) {
		on =0;
		off=0;
        for (int l = 0; l < 5; l++)
        {
            for (int k = 0; k < 5; k++)
            {   
           	 if(button[l][k].getStyle().equals(ON))
           	 on++;
           	 else off++;
            }
        } 
		
	}

	public void changeColor(Button b) {
		 String color;
	   	 color = b.getStyle();//invert cell below
	   	 if(color.equals(OFF)) b.setStyle(ON);
	   	 else b.setStyle(OFF);
		
	}
}
