package sample;


import java.util.*;

public class AStar {
    public final static int BAR = 1; // value of bar
    public final static int PATH = 2; // path
    public final static int DIRECT_VALUE = 10; // cost of movement horizontal and vertical
    public final static int OBLIQUE_VALUE = 14; // the cost of movement oblique

    Queue<Node> openList = new PriorityQueue<Node>();
    List<Node> closeList = new ArrayList<Node>();

    /*
    * Start
    * */
    public void start(MapInfo mapInfo){
        if(mapInfo == null) return;
        // clean map
        openList.clear();
        closeList.clear();
        // open map
        openList.add(mapInfo.start);
        moveNodes(mapInfo);
    }

    /*
    * Move the node
    * */
    public void moveNodes(MapInfo mapInfo){
       while(!openList.isEmpty()){
           Node current = openList.poll();
           closeList.add(current);
           addNeighborNodeInOpen(mapInfo, current);
           if(isCoordInClose(mapInfo.end.coord)){
               drawPath(mapInfo.maps, mapInfo.end);
               break;
           }
       }
    }

    /**
     * Draw a path in a two-dimentional array
     * */
    private void drawPath(int[][] maps, Node end){
        if (end == null || maps == null) return;
        System.out.println(" Total cost: " + end.G);
        while(end != null){
            Coord c = end.coord;
            maps[c.y][c.x] = PATH;
            end = end.parent;
        }
    }

    /*
    * add all nodes neighbor in list open
    * */
    public void addNeighborNodeInOpen(MapInfo mapInfo, Node current){
        int x = current.coord.x;
        int y = current.coord.y;

        // left
        addNeighborNodeInOpen(mapInfo, current, x - 1, y, DIRECT_VALUE);
        // right
        addNeighborNodeInOpen(mapInfo, current, x, y - 1, DIRECT_VALUE);
        // current
        addNeighborNodeInOpen(mapInfo, current, x + 1, y, DIRECT_VALUE);
        // next
        addNeighborNodeInOpen(mapInfo, current, x, y + 1, DIRECT_VALUE);
        // up right
        addNeighborNodeInOpen(mapInfo, current, x - 1, y -1, OBLIQUE_VALUE);
        // up left
        addNeighborNodeInOpen(mapInfo, current, x + 1, y - 1, OBLIQUE_VALUE);
        // down right
        addNeighborNodeInOpen(mapInfo, current, x + 1, y + 1, OBLIQUE_VALUE);
        // down left
        addNeighborNodeInOpen(mapInfo, current, x - 1, y + 1, OBLIQUE_VALUE);
    }

    private void addNeighborNodeInOpen(MapInfo mapInfo, Node current, int x, int y, int value){
        if(canAddNodeToOpen(mapInfo, x, y)){
            Node end = mapInfo.end;
            Coord coord = new Coord(x, y);
            int G = current.G + value; // Calculate the value G of the adjacent
            Node child = findNodeInOpen(coord);
            if(child == null){
                int H = calcH(end.coord, coord);
                if(isEndNode(end.coord, coord)){
                    child = end;
                    child.parent = current;
                    child.G = G;
                    child.H = H;
                }else{
                    child = new Node(coord, current, G, H);
                }
                openList.add(child);
            }else if(child.G > G){
                child.G = G;
                child.parent = current;
                openList.add(child);
            }
        }
    }

    /**
     * find the node in the list open
     * */
    private Node findNodeInOpen(Coord coord){
        if(coord == null || openList.isEmpty()) return null;

        for (Node node : openList){
            if(node.coord.equals(coord)){
                return node;
            }
        }
        return  null;
    }

    /**
     * Calculate the value of H
     *
	 */
    private int calcH(Coord end, Coord coord){
        return Math.abs(end.x - coord.x) + Math.abs(end.y - coord.y);
    }

    /**
     * Determine if node is end
     * */
    private boolean isEndNode(Coord end, Coord coord){
        return end.equals(coord);
    }

    /*
    * Determine whether the node can be placed in open list
    * */
    private boolean canAddNodeToOpen(MapInfo mapInfo, int x, int y){
        // whether it is in the map
        if(x < 0 || x >= mapInfo.width || y < 0 || y >= mapInfo.height) return false;
        // determine whether it is an unpassable node
        if(mapInfo.maps[y][x] == BAR) return false;
        // Determine whether the node has a close table
        if(isCoordInClose(x, y)) return false;

        return true;
    }

    /**
     * Determine whether the coodinates are in the close table
     * */
    private boolean isCoordInClose(Coord coord){
        return coord != null && isCoordInClose(coord.x, coord.y);
    }

    /*
    * Determine whether the coordinates are in the close table
    * */
    private boolean isCoordInClose(int x, int y){
        if (closeList.isEmpty()) return false;
        for(Node node : closeList){
            if(node.coord.x == x && node.coord.y == y){
                return true;
            }
        }

        return false;
    }
}
