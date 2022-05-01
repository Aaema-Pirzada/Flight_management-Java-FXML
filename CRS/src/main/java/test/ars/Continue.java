package test.ars;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;


public class Continue implements EventHandler<ActionEvent>  {

    @Override
    public void handle(ActionEvent actionEvent) {
        Image image = null;
        try {
            image = new Image(new FileInputStream("src/main/java/test/ars/image.jpg"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ImageView imageView = new ImageView(image);
        imageView.setX(300);
        imageView.setY(250);
        Label ltxt = new Label(" MENU ");
        Button Read = new Button(" Read ");
        Button FINFO = new Button(" Flight_info ");
        Button PINFO = new Button(" Passenger_info ");
        PINFO.setWrapText(true);
        FINFO.setWrapText(true);
        Button Add = new Button(" Add/Edit ");
        Add.setWrapText(true);
        Button Reserve = new Button(" Reserve ");
        Button Cancel = new Button(" Cancel ");
        Button Check = new Button(" Check ");
        Button Search = new Button(" Search ");
        Button Exit = new Button(" Exit ");
        Read.setOnAction(actionEvent1 -> {Read();});
        Exit.setOnAction(actionEvent1 -> {
            Platform.exit();
            System.exit(0);
        });
        Check.setOnAction(actionEvent1 -> {checking();});
        Search.setOnAction(actionEvent1 -> { P_search();});
        Cancel.setOnAction(actionEvent1 ->  {Cancellation();});
        Reserve.setOnAction(actionEvent1 -> {reservation();});
        FINFO.setOnAction(actionEvent1 -> {F_INFO();});
        PINFO.setOnAction(actionEvent1 -> {P_INFO();});
        Add.setOnAction(actionEvent1 -> {Add_Flight();});
        VBox root = new VBox();
        root.getChildren().addAll(ltxt,Read, FINFO, PINFO,Add,Reserve,Cancel,Check,Search,Exit, imageView);

        Stage stage = new Stage();
        stage.setTitle("ARS");
        stage.setScene(new Scene(root, 500, 500, Color.BLACK));
        stage.show();
    }

    public void Read() {
        try {
            READ();
            Text reading =new Text();
            reading.setText("DATA IS BEING READ");
            reading.setX(25);
            reading.setY(25);
            Button Exit = new Button(" Exit ");
            Exit.setOnAction(actionEvent1 -> {
                Platform.exit();
                System.exit(0);
            });
            Button Back = new Button(" Back ");
            Back.setOnAction( new Continue());
            VBox root = new VBox();
            root.getChildren().addAll(reading,Back, Exit);
            Stage stage = new Stage();
            stage.setTitle("Reading");
            stage.setScene(new Scene(root, 500, 500, Color.BLACK));
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    void READ() throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/test/ars/Flights.txt"));
        String line = null;
        Flight F = new Flight(0,"","","",0);
        FlightsLinkedList<Flight> f = new FlightsLinkedList<Flight>();
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            F.flightNumber= Integer.parseInt(values[0]);
            F.airlineName=values[1];
            F.source=values[2];
            F.destination=values[3];
            F.capacity= Integer.parseInt(values[4]);
            f.add(F,null);
            f.display();
        }
        br.close();

        BufferedReader br1 = new BufferedReader(new FileReader("src/main/java/test/ars/Passenger.txt"));
        String line1 = null;
        Passengers P = new Passengers(0,0,"","","",null);

        while ((line1 = br1.readLine()) != null) {
            String[] values = line1.split(",");
            P.flightNumber= Integer.parseInt(values[0]);
            P.ticketNumber= Integer.parseInt(values[1]);
            P.fullName=values[2];
            P.passportNumber=values[3];
            P.nationality=values[4];
            P.birthDate= new SimpleDateFormat("dd/MM/yyyy").parse(values[5]);
            System.out.print(P.fullName +"  "+ P.passportNumber+ "  " +P.nationality+"  " + P.ticketNumber+"  "+P.flightNumber+"  "+values[5]+"\n");
        }
        br1.close();


    }

    void checking() {
        Stage s= new Stage();
        Text pass =new Text();
        TextField Flight_no = new TextField("Enter Ticket_no");
        Button submit = new Button("Enter");
        submit.setOnAction(actionEvent1 -> {String F_no = Flight_no.getText();
            String Flights= null;
            try {
                String passenger = Search1(Integer.parseInt(F_no));
                pass.setText(passenger);
                pass.setX(25);
                pass.setY(25);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });
        Button Exit = new javafx.scene.control.Button(" Exit ");
        Exit.setOnAction(actionEvent1 -> {
            Platform.exit();
            System.exit(0);
        });
        Button Back = new Button(" Back ");
        Back.setOnAction( new Continue());
        VBox root = new VBox();
        root.getChildren().addAll(Flight_no,submit,pass,Back, Exit);
        Stage stage = new Stage();
        stage.setTitle("IS RESERVED ?");
        stage.setScene(new Scene(root, 500, 500, Color.BLACK));
        stage.show();

    }

    String Search1(int f_no) throws Exception{
        BufferedReader br1 = new BufferedReader(new FileReader("src/main/java/test/ars/Passenger.txt"));
        String line1 = null;
        String result ="";
        Passengers P = new Passengers(0,0,"","","",null);
        while ((line1 = br1.readLine()) != null) {
            String[] values = line1.split(",");
            P.flightNumber= Integer.parseInt(values[0]);
            P.ticketNumber= Integer.parseInt(values[1]);
            P.fullName=values[2];
            P.passportNumber=values[3];
            P.nationality=values[4];
            P.birthDate= new SimpleDateFormat("dd/MM/yyyy").parse(values[5]);
            if (P.ticketNumber==f_no)
            {
                result="Resevered";
            } else if ( P.ticketNumber!=f_no) {
                result= "Not Resevered";
            }
        }
        br1.close();
        return result;

    }

    void P_search() {
        Stage s= new Stage();
        Text pass =new Text();
        TextField Flight_no = new TextField("Enter Ticket NO");
        Button submit = new Button("Enter");
        submit.setOnAction(actionEvent1 -> {String T_no = Flight_no.getText();
            String Flights= null;
            try {
                String passenger = Search(Integer.parseInt(T_no));
                pass.setText(passenger);
                pass.setX(25);
                pass.setY(25);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });
        Button Exit = new javafx.scene.control.Button(" Exit ");
        Exit.setOnAction(actionEvent1 -> {
            Platform.exit();
            System.exit(0);
        });
        Button Back = new Button(" Back ");
        Back.setOnAction( new Continue());
        VBox root = new VBox();
        root.getChildren().addAll(Flight_no,submit,pass,Back, Exit);
        Stage stage = new Stage();
        stage.setTitle("Passenger Search");
        stage.setScene(new Scene(root, 500, 500,Color.BLACK));
        stage.show();

    }
    String Search(int f_no) throws Exception{
        BufferedReader br1 = new BufferedReader(new FileReader("src/main/java/test/ars/Passenger.txt"));
        String line1 = null;
        String result ="";
        Passengers P = new Passengers(0,0,"","","",null);
        while ((line1 = br1.readLine()) != null) {
            String[] values = line1.split(",");
            P.flightNumber= Integer.parseInt(values[0]);
            P.ticketNumber= Integer.parseInt(values[1]);
            P.fullName=values[2];
            P.passportNumber=values[3];
            P.nationality=values[4];
            P.birthDate= new SimpleDateFormat("dd/MM/yyyy").parse(values[5]);
            if (P.ticketNumber==f_no)
            {
                result = result + "  "+values[0] +"  "+ values[1] + "  "+values[2] + "  "+values[3]+"  "+
                        values[4] +"  "+ values[5]+"\n";
            }
        }
        br1.close();
        return result;

    }
    void Cancellation() {
        TextField Ticket = new TextField("Enter Ticket_no");
        Text pass = new Text();
        Button submit = new Button("Cancel");
        submit.setOnAction(actionEvent1 -> { String a = Ticket.getText();
            BufferedReader br1 = null;
            try {
                br1 = new BufferedReader(new FileReader("src/main/java/test/ars/Passenger.txt"));
                String line1 = null;
                String result ="";
                int t = Integer.parseInt(a);
                Passengers P = new Passengers(0,0,"","","",null);
                System.out.print("Name   PassNO     Nationality    TicNO    FlightNO    Birthdate ");
                while ((line1 = br1.readLine()) != null) {
                    String[] values = line1.split(",");
                    P.flightNumber= Integer.parseInt(values[0]);
                    P.ticketNumber= Integer.parseInt(values[1]);
                    P.fullName=values[2];
                    P.passportNumber=values[3];
                    P.nationality=values[4];
                    P.birthDate= new SimpleDateFormat("dd/MM/yyyy").parse(values[5]);
                    if (P.flightNumber == t)
                    {
                        result = result + "  "+values[0] +"  "+ values[1] + "  "+values[2] + "  "+values[3]+"  "+
                                values[4] +"  "+ values[5]+"\n";
                    }
                }
                br1.close();
                String text = cancelled(result);
                pass.setText(text);

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }


        });

        Button Exit = new javafx.scene.control.Button(" Exit ");
        Exit.setOnAction(actionEvent1 -> {
            Platform.exit();
            System.exit(0);
        });
        Button Back = new Button(" Back ");
        Back.setOnAction( new Continue());
        VBox root = new VBox();
        root.getChildren().addAll(Ticket,submit,pass,Back, Exit);
        Stage stage = new Stage();
        stage.setTitle("Passenger information");
        stage.setScene(new Scene(root, 500, 500, Color.BLACK));
        stage.show();

    }

    public String cancelled(String a) throws IOException {
        // PrintWriter object for output.txt
        PrintWriter pw = new PrintWriter("Passengers.txt");

        // BufferedReader object for input.txt
        BufferedReader br1 = new BufferedReader(new FileReader("src/main/java/test/ars/Passenger.txt"));
        Passengers P = new Passengers(0,0,"","","",null);
        String line1 = br1.readLine();

        // loop for each line of input.txt
        while(line1 != null)
        {
            boolean flag = false;

            String line2 = a;
            if(line1.equals(line2))
            {
                flag = true;
                break;
            }

            if(!flag)
                pw.println(line1);

            line1 = br1.readLine();

        }

        pw.flush();

        // closing resources
        br1.close();
        pw.close();

        System.out.println("File operation performed successfully");
        return "CANCELLED RESEVATION";
    }

     void reservation() {
        Random rand = new Random() ;
        int Ticketno = rand.nextInt(9999999);
        TextField Flight_no = new TextField("Enter Flight_no");
        TextField name = new TextField("Enter Name");
        TextField pas_no = new TextField("Enter Passport nO");
        TextField Nationality = new TextField("Enter Nationality");
        TextField b_date = new TextField("Enter Birthdate (dd/MM/yyyy)");
        Text ok = new Text();
        Button submit = new Button("Reserve");
        submit.setOnAction(actionEvent1 -> {
            try {
                Passengers p = new Passengers(Integer.parseInt(Flight_no.getText()), Ticketno,name.getText()
                        , pas_no.getText(), Nationality.getText(), new SimpleDateFormat("dd/MM/yyyy").parse(b_date.getText()));
                String Filepath = "src/main/java/test/ars/Passengers.txt";
                String fileadd = "\n" +Flight_no.getText()+"," +Ticketno+","+name.getText()+","
                        +pas_no.getText()+","+ Nationality.getText()+","+b_date.getText()+"\n";
                ok.setText("DONE");
                System.out.print(fileadd);
                File file = new File(Filepath);
                FileWriter fr = new FileWriter(file, true);
                fr.write(fileadd);
                //fr.close();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Button Exit = new Button(" Exit ");
        Exit.setOnAction(actionEvent1 -> {
            Platform.exit();
            System.exit(0);
        });
        Button Back = new Button(" Back ");
        Back.setOnAction( new Continue());
        VBox root = new VBox();
        root.getChildren().addAll(Flight_no, name, pas_no, Nationality,b_date,submit,ok ,Back, Exit);
        Stage stage = new Stage();
        stage.setTitle("Reservation");
        stage.setScene(new Scene(root, 500, 500, Color.BLACK));
        stage.show();
    }

    void F_INFO() {
        try {
            String Flights= Display();
            Text flights =new Text();
            flights.setText(Flights);
            flights.setX(25);
            flights.setY(25);
            Button Exit = new Button(" Exit ");
            Exit.setOnAction(actionEvent1 -> {
                Platform.exit();
                System.exit(0);
            });
            Button Back = new Button(" Back ");
            Back.setOnAction( new Continue());
            VBox root = new VBox();
            root.getChildren().addAll(flights, Back, Exit);
            Stage stage = new Stage();
            stage.setTitle("Flight Information");
            stage.setScene(new Scene(root, 500, 500, Color.BLACK));
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }
    String Display() throws Exception
    {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/test/ars/Flights.txt"));
        String line = null;
        String result = "";
        Flight F = new Flight(0,"","","",0);
        FlightsLinkedList<Flight> f = new FlightsLinkedList<Flight>();
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            F.flightNumber= Integer.parseInt(values[0]);
            F.airlineName=values[1];
            F.source=values[2];
            F.destination=values[3];
            F.capacity= Integer.parseInt(values[4]);
            f.add(F,null);
            result = result + "  "+values[0] +"  "+ values[1] + "  "+values[2] + "  "+values[3]+"  "+
                    values[4] +"  "+"\n";
        }
        br.close();
        return result;
    }

     void P_INFO() {
        Stage s= new Stage();
        Text pass =new Text();
        TextField Flight_no = new TextField("Enter Flight_no");
        Button submit = new Button("Enter");
        submit.setOnAction(actionEvent1 -> {String F_no = Flight_no.getText();
            String Flights= null;
            try {
                String passenger = Search3(Integer.parseInt(F_no));
                pass.setText(passenger);
                pass.setX(25);
                pass.setY(25);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });
        Button Exit = new javafx.scene.control.Button(" Exit ");
        Exit.setOnAction(actionEvent1 -> {
            Platform.exit();
            System.exit(0);
        });
        Button Back = new Button(" Back ");
        Back.setOnAction( new Continue());
        VBox root = new VBox();
        root.getChildren().addAll(Flight_no,submit,pass,Back, Exit);
        Stage stage = new Stage();
        stage.setTitle("Passenger information");
        stage.setScene(new Scene(root, 500, 500, Color.BLACK));
        stage.show();

    }

    String Search3(int f_no) throws Exception{
        BufferedReader br1 = new BufferedReader(new FileReader("src/main/java/test/ars/Passenger.txt"));
        String line1 = null;
        String result ="";
        Passengers P = new Passengers(0,0,"","","",null);
        System.out.print("Name   PassNO     Nationality    TicNO    FlightNO    Birthdate ");
        while ((line1 = br1.readLine()) != null) {
            String[] values = line1.split(",");
            P.flightNumber= Integer.parseInt(values[0]);
            P.ticketNumber= Integer.parseInt(values[1]);
            P.fullName=values[2];
            P.passportNumber=values[3];
            P.nationality=values[4];
            P.birthDate= new SimpleDateFormat("dd/MM/yyyy").parse(values[5]);
            if (P.flightNumber == f_no)
            {
                result = result + "  "+values[0] +"  "+ values[1] + "  "+values[2] + "  "+values[3]+"  "+
                        values[4] +"  "+ values[5]+"\n";
            }
        }
        br1.close();
        return result;

    }

    public void Add_Flight() {
        TextField Flight_no = new TextField("Enter Flight_no");
        TextField Flight_name = new TextField("Enter Flight name");
        TextField Src = new TextField("Enter Source");
        TextField Dest = new TextField("Enter Destination");
        TextField Cap = new TextField("Enter Capacity");
        Text New_flight2 = new Text();
        Text New_flight = new Text();

        Button submit = new Button("Add");
        submit.setOnAction(actionEvent1 -> {
            String F_no = Flight_no.getText();
            String name = Flight_name.getText();
            String src = Src.getText();
            String dest = Dest.getText();
            String cap = Cap.getText();
            String Flights = null;
            try {
                String passenger = Search4(Integer.parseInt(F_no), name, src, dest, Integer.parseInt(cap));
                New_flight.setText(passenger);
                New_flight.setX(25);
                New_flight.setY(25);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });



        Button Exit = new javafx.scene.control.Button(" Exit ");
        Exit.setOnAction(actionEvent1 -> {
            Platform.exit();
            System.exit(0);
        });
        Button Back = new Button(" Back ");
        Back.setOnAction(new Continue());
        VBox root = new VBox();
        root.getChildren().addAll(Flight_no, Flight_name, Src, Dest, Cap, submit, New_flight, New_flight2, Back, Exit);
        Stage stage = new Stage();
        stage.setTitle("ADDING FLIGHT");
        stage.setScene(new Scene(root, 500, 500, Color.BLACK));
        stage.show();

    }



    String Search4(int f_no, String name, String src, String dest, int cap) throws Exception {
        String line = null;
        String result = "";
        Flight F = new Flight(0, "", "", "", 0);
        FlightsLinkedList<Flight> f = new FlightsLinkedList<Flight>();
        F.flightNumber = f_no;
        F.airlineName = name;
        F.source = src;
        F.destination = dest;
        F.capacity = cap;
        f.add(F, null);
        String Filepath = "src/main/java/test/ars/Flights.txt";
        String fileadd = F.flightNumber + "," + F.airlineName + "," + F.source + "," + F.destination + "," +
                F.capacity + "\n";
        File file = new File(Filepath);
        FileWriter fr = new FileWriter(file, true);
        fr.write(fileadd);
        result = "ADDED FLIGHT " + result + "  " + F.flightNumber + "  " + F.airlineName + "  " + F.source + "  " + F.destination + "  " +
                F.capacity + "  " + "\n";


        fr.close();
        return result;
    }
}
