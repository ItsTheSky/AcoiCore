package info.itsthesky.AcoiCore.tools;

import info.itsthesky.AcoiCore.AcoiCore;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.upperlevel.spigot.book.NmsBookHelper;

import java.awt.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;
import java.util.regex.Pattern;

public class Utils {

    public String join(List<String> strs, String s) {
        StringBuilder f = new StringBuilder();
        for (String st : strs) {
            f.append(st).append(s);
        }
        return f.toString();
    }

    public static boolean isLegacyVersion() {
        int ver = Integer.parseInt(
                Bukkit.getVersion()
                        .split("MC: ")[1]
                        .replace(")", "")
                        .split("\\.")[1]
        );
        return ver <= 12;
    }

    public static String getLegacyVersion() {
        return Bukkit.getVersion()
                .split("MC: ")[1]
                .replace(")", "")
                .split("\\.")[1];
    }

    public static String getPath() {
        return AcoiCore.getInstance().getDataFolder().getPath();
    }

    public static void openBook(Player player, ItemStack book) {
        ItemStack hand = player.getInventory().getItemInOffHand();
        player.getInventory().setItemInMainHand(book);
        player.updateInventory();
        NmsBookHelper.openBook(player, book, false);
        player.getInventory().setItemInMainHand(hand);
        player.updateInventory();
    }

    public static int hexToInt(String hex) {
        hex = hex.replace("#", "");
        return Integer.parseInt(hex, 16);
    }

    public static String getBar(double value, double max, int echelle, String color) {
        double one = value/max;
        one = Math.floor(one * echelle);
        StringBuilder total = new StringBuilder();
        for (int i = 0; i <= one; i++) {
            total.append(Utils.colored(color)).append("■");
        }
        for (int i = 0; i < (echelle - one); i++) {
            total.append("§7").append("□");
        }
        return total.toString();
    }

    public static String now(String pattern) {
        if (pattern == null) {
            pattern = "yyyy/MM/dd HH:mm:ss";
        }
        if (pattern.equalsIgnoreCase("hash")) {
            pattern = "yyyy,MM,dd/HH,mm,ss";
        }
        if (pattern.equalsIgnoreCase("normal")) {
            pattern = "yyyy/MM/dd HH:mm:ss";
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static Integer round(Double number) {
        String t = number.toString().split("\\.")[0];
        return Integer.valueOf(t);
    }

    private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    public static String colored(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    /**
     *
     * @param colorStr e.g. "#FFFFFF"
     * @return
     */
    public static Color hex2Rgb(String colorStr) {
        return new Color (
                Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
                Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
                Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
    }

    public static LinkedHashMap<String, Integer> sortHashMapByValues(
            HashMap<String, Integer> passedMap) {
        List<String> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Integer> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        LinkedHashMap<String, Integer> sortedMap =
                new LinkedHashMap<>();

        for (Integer val : mapValues) {
            Iterator<String> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                String key = keyIt.next();
                Integer comp1 = passedMap.get(key);

                if (comp1.equals(val)) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }

    public static <T, Q> LinkedHashMap<T, Q> reverseMap(LinkedHashMap<T, Q> toReverse) {
        LinkedHashMap<T, Q> reversedMap = new LinkedHashMap<>();
        List<T> reverseOrderedKeys = new ArrayList<>(toReverse.keySet());
        Collections.reverse(reverseOrderedKeys);
        reverseOrderedKeys.forEach((key)->reversedMap.put(key,toReverse.get(key)));
        return reversedMap;
    }

    public static void fillBlocks(World world, Location min, Location max, Material set) {
        for (int x = min.getBlockX(); x < max.getBlockX()+1; x++) {
            for (int y = min.getBlockY(); y < max.getBlockY()+1; y++) {
                for (int z = min.getBlockZ(); z < max.getBlockZ()+1; z++) {
                    Block block = world.getBlockAt(x, y, z);
                    block.setType(set);
                }
            }
        }
    }

    public static List<Integer> integersBetween(int a, int b) {
        List<Integer> list = new ArrayList<>();
        for (int i = a; i < b+1; i++) {
            list.add(i);
        }
        return list;
    }

}

