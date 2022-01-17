package PaooGame.Maps;

public class MapFactory {
    public static int[][] getMap(int level)
    {
        switch (level)
        {
            case 1: return Map.Level1Map;
            case 2: return Map.Level2Map;
            default: return null;

        }
    }
}
