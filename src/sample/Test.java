package sample;

public class Test {
    public static void main(String[] args){
        int[][] maps = {
                { 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
                { 0 , 0 , 0 , 0 , 0 , 0 , 1 , 1 , 1 , 1 , 1 , 0 , 0 , 1 , 0 },
                { 1 , 1 , 1 , 1 , 1 , 1, 1 , 0 , 0 , 0 , 0 , 0 , 0 , 1 , 0 },
                { 0 , 0 , 0 , 1 , 0 , 0 , 0 , 0 , 0 , 1 , 1 , 0 , 0 , 1 , 0 },
                { 0 , 0 , 0 , 1 , 1 , 1 , 0 , 0 , 0 , 1 , 0 , 0 , 0 , 1 , 0 },
                { 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 1 , 0 , 0 , 0 , 1 , 0 },
                { 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 }
        };

        MapInfo info = new MapInfo(maps, maps[0].length, maps.length, new Node(1, 1), new Node(4, 5));
        new AStar().start(info);
        printMap(maps);
    }

    /*
    * Print the map
    * */
    public static void printMap(int[][] maps){
        for (int i = 0; i < maps.length; i++){
            for (int j = 0; j < maps[i].length; j++){
                String m = maps[i][j] + " ";
                System.out.print(
                        m.replace("2", "+")
                        .replace("1", "#")
                        .replace("0", ".")
                );
            }
            System.out.println();
        }
    }
}
