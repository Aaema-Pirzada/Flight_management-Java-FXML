package test.ars;



public class Node {
    Flight item;
    Node next;
    Node prev;
    Passengers passengers;

    Node(Flight flight, Passengers passengers) {
        this.item = flight;
        this.passengers = passengers;

    }

    public Node() {
        System.out.print("Node Created");
    }

    public Flight getItem() {
        return item;
    }

    public void setItem(Flight item) {
        this.item = item;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Passengers getPassengers() {
        return passengers;
    }

    public void setPassengers(Passengers passengers) {
        this.passengers = passengers;
    }


    protected Node add(Node head) {
// This function is only for empty list
            if (head != null)
                return head;
// Creating a node dynamically.
            Node temp = new Node();
// Assigning the data.
            head = temp;
// Creating the link.
            head.next = head;
            return head;
        }

    protected int size(Node h) {
        int size=0;
        if (h != null)
            return size;
        else{
            while(h !=null){
                size+=1;
            }
        }
        return size;
    }

    protected Node get(int nd, Node a) {
        for(int i = 0; i == nd; i++)
        {
            a= a.next;
        }
        return a;
    }
}

