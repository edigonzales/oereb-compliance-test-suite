package ch.so.agi.oereb.cts;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;


import ch.so.agi.oereb.cts.check.CheckFactory;
import ch.so.agi.oereb.cts.model.CheckVars;
import ch.so.agi.oereb.cts.model.CheckVarsBuilder;
import ch.so.agi.oereb.cts.model.Parameter;
import ch.so.agi.oereb.cts.probe.Probe;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("JavaFX App");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        //grid.setGridLinesVisible(true);
        
            grid.setStyle("-fx-font-family: \"Frutiger 55 Roman\";");
        
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.NEVER);        
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);

        grid.getColumnConstraints().addAll(col1, col2);
        
        Text scenetitle = new Text("ÖREB-Kataster Compliance Test Suite");
        scenetitle.setFont(Font.font(Font.getDefault().getName(), FontWeight.NORMAL, 20));
        scenetitle.setStyle("-fx-font-family: Roboto; -fx-font-size: 20;");

        grid.add(scenetitle, 0, 0, 2, 1);

        Label baseUrlLbl = new Label("Base Url:");
        grid.add(baseUrlLbl, 0, 1);

        TextField baseUrlLblTxtFld = new TextField();
        grid.add(baseUrlLblTxtFld, 1, 1);

//        Button browseBtn = new Button("Browse");
//        grid.add(browseBtn, 2, 1);

        Label emptyLbl = new Label("GetEGRID Parameter");
        emptyLbl.setFont(Font.font("Roboto", FontWeight.BOLD, Font.getDefault().getSize()));
        emptyLbl.setStyle("-fx-font-family: Roboto; -fx-font-size: 13;");

        grid.add(emptyLbl, 0, 2, 2, 1);
        
        Label pw = new Label("Password:");
        grid.add(pw, 0, 3);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 3);

        Scene scene = new Scene(grid, 600, 400);
        scene.getStylesheets().add("http://fonts.googleapis.com/css?family=Roboto");
        primaryStage.setScene(scene);
        

        primaryStage.setScene(scene);
        primaryStage.show();

//        Label label = new Label("File:");
//        TextField tf = new TextField();
//        Button btn = new Button("Browse");
//        btn.setOnAction(e -> {
//            FileChooser file = new FileChooser();
//            file.setTitle("Open File");
//            file.showOpenDialog(primaryStage);
//        });
//        HBox root = new HBox();
//        // root.getChildren().add(file);
//
//        root.setSpacing(20);
//        root.getChildren().addAll(label, tf, btn);
//        Scene scene = new Scene(root, 350, 100);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("FileChooser Example");
//        primaryStage.show();

//        Label outputDirectoryTFLabel = new Label("Output directory");
//
//        TextField outputDirectoryTextField = new TextField();
//
//        DirectoryChooser directoryChooser = new DirectoryChooser();
//        directoryChooser.setInitialDirectory(new File("src"));
//
//        Button button = new Button("Select Directory");
//        button.setOnAction(e -> {
//            File selectedDirectory = directoryChooser.showDialog(primaryStage);
//
//            System.out.println(selectedDirectory.getAbsolutePath());
//        });
//
//
//        HBox hBox = new HBox(outputDirectoryTFLabel, outputDirectoryTextField, button);
//        //HBox hBox = new HBox(button1, button2);
//        Scene scene = new Scene(hBox, 960, 600);
//
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

    public static void main(String[] args) throws IOException, InterruptedException {

//System.setProperty("prism.lcdtext", "false");
//System.setProperty("prism.text", "t2k");


        launch(args);

//        FlatLightLaf.setup();
//
//        JFrame frame = new JFrame("My First GUI");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(300,300);
//        JButton button = new JButton("Press");
//        frame.getContentPane().add(button); // Adds Button to content pane of frame
//        frame.setVisible(true);

//        String baseUrl = "https://prozessor-oereb.ur.ch/oereb/";
//        String eastNorthCoord = "2690481.2,1195464.8";
//        String gnssCoord = "46.90413,8.62621";
//      
//        XmlMapper xmlMapper = new XmlMapper();
//        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
//        xmlMapper.registerModule(new JavaTimeModule());
//      
//        {
//            List<CheckVars> checksVars = new ArrayList<>();
//            {
//                var checkVars = new CheckVarsBuilder("ch.so.agi.oereb.cts.check.HttpStatusMatch")
//                        .addParameter(new Parameter("statusCode", "201")).build();
//                checksVars.add(checkVars);
//            }
//            {
//                var checkVars = new CheckVarsBuilder("ch.so.agi.oereb.cts.check.SchemaValidation")
//                        .addParameter(new Parameter("xsd", "oereb_v2_0/Extract.xsd")).build();
//                checksVars.add(checkVars);
//            }
//
//            Probe probe = new Probe(baseUrl + "getegrid/xml/?EN=" + eastNorthCoord, checksVars);
//            probe.setDescription("fubar");
//            probe.run();
//
//            String probeResultXml = xmlMapper.writeValueAsString(probe.getProbeResult());
//            System.out.println(probeResultXml);
//            for (Result result : probe.getProbeResult().getResults()) {
//                String xml = xmlMapper.writeValueAsString(result);
//                // System.out.println(xml);
//
//            }
//        }      

//        JAXBContext jaxbContext = JAXBContext.newInstance(Suite.class);
//        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//        jaxbUnmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
//
//        File suiteFile = new File("src/test/data/suite.xml");
//        XmlSuite suite = (XmlSuite) jaxbUnmarshaller.unmarshal(suiteFile);
//         
//        System.out.println(suite.getProbes().get(0).getChecks().size());
//
//        XmlProbe xmlProbe = suite.getProbes().get(0);
//        IProbe probe = ProbeFactory.getProbe(xmlProbe.getClassName());
//        System.out.println(probe.getClass());

        System.out.println("Hallo Welt");
    }
}
