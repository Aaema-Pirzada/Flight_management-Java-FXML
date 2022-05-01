package test.ars;



import java.util.Comparator;
import java.util.stream.IntStream;

public class FlightsLinkedList<E> extends  Node {
    private Node head;
    private int size;

    public FlightsLinkedList() {
        super();

    }

    public String search(Flight f, int f_no){
        String s="";
        if (head != null) {
            Node currentNode = head;
            while (currentNode.getNext() != null) {
                if(currentNode.getItem().flightNumber == f_no)
                {
                     s =currentNode.getItem().flightNumber + ","+currentNode.getItem().airlineName
                    +","+currentNode.getItem().source+","+currentNode.getItem().destination
                            +","+currentNode.getItem().capacity;
                     return s;
                }
            }
        }
        return s;
    }
    public void add(Flight flight, Passengers passengers) {
        Node n = new Node(flight, passengers);
        Node nodes = new Node();
        if (this.head == null) {
            head = n;
            size = 1;
        } else {
            Node currentNode = head;
            nodes.add(head);
            if (currentNode.getNext() == null) {

                nodes.add(n);

            }
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
                nodes.add(currentNode);

            }
            currentNode.setNext(n);

            if (nodes.size(n) > 0) {
                sorted(nodes, new SortByFlightNumber());//sorting asc:flight number
                // Reshuffling the next node linkage after sort
                IntStream.range(0, nodes.size(n)).forEach(nd -> {
                    if (nd > 0 && nd < nodes.size(n) - 1) {
                        nodes.get(nd,n).setNext(nodes.get(nd + 1,n));
                    } else if (nd == 0) {
                        head = nodes.get(0,n);
                        head.setNext(nodes.get(1,n));
                    } else {
                        nodes.get(nd,n).setNext(null);
                    }

                });
            }
        }

    }

    private void sorted(Node nodes, SortByFlightNumber sortByFlightNumber) {
        int a=0;
        int b=0;
        a=sortByFlightNumber.compare(nodes.next, nodes);
        b=sortByFlightNumber.compare(nodes.next, nodes.next.next);
        if(a>b)
        {
            Node temp=new Node();
            temp=nodes.next.next;
            nodes.next.next=nodes.next;
            nodes.next=temp;

        }

    }

    public Node get(int flightNumber) {
        if (head != null) {
            Node currentNode = head;
            while (currentNode.getNext() != null) {

                currentNode = currentNode.getNext();
                if (currentNode.item.flightNumber == flightNumber) {
                    return currentNode;
                }
            }
            if (currentNode.item.flightNumber == flightNumber) {
                return currentNode;
            }

        }

        return null;
    }
    /**
     * To display the FlightLinkedList contents
     */
    public void display() {

        if (head != null) {
            Node currentNode = head;
            while (currentNode.getNext() != null) {
                System.out.println(" "+ currentNode.getItem().getAirlineName()+ "  "+currentNode.getItem().getFlightNumber()
                        +"  "+currentNode.getItem().getCapacity()+"  "+currentNode.getItem().getDestination()
                        +"  "+currentNode.getItem().getSource());
                currentNode = currentNode.getNext();
            }
            // System.out.println(""+ currentNode.getItem().getAirlineName()+ "  "+currentNode.getItem().getFlightNumber()
            //       +"  "+currentNode.getItem().getCapacity()+"  "+currentNode.getItem().getDestination()
            //     +"  "+currentNode.getItem().getSource());
        }
    }

}

class SortByFlightNumber implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {

        return o1.item.flightNumber - o2.item.flightNumber;
    }

}