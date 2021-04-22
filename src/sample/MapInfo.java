package sample;

public class MapInfo {
    public int [][] maps; // an matrix bidirectional of maps
    public int width; // width of map
    public int height; // height of map
    public Node start; // node start
    public Node end; // node End

    public MapInfo(int [][] maps, int width, int height, Node start, Node end){
        this.maps = maps;
        this.width = width;
        this.height = height;
        this.start = start;
        this.end = end;
    }
}
