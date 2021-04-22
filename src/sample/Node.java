package sample;

public class Node implements Comparable<Node>{
    public Coord coord; // Coordinates
    public Node parent; // node parent
    public int G; // G is a value necessary, is the cost of point of start at actually point
    public int H; // H is a estimated cost in current node at node finish

    public Node(int x, int y){
        this.coord = new Coord(x, y);
    }

    public Node(Coord coord, Node parent, int g, int h){
        this.coord = coord;
        this.parent = parent;
        G = g;
        H = h;
    }

    @Override
    public int compareTo(Node o){
        if (o == null) return -1;
        if (G + H > o.G + o.H)
            return 1;
        else if (G + H < o.G + o.H) return -1;
        return 0;
    }
}
