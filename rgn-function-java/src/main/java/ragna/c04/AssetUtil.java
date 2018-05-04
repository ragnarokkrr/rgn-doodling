package ragna.c04;

import java.util.Arrays;
import java.util.List;
import ragna.c04.Asset.AssetType;

public class AssetUtil {

    static final List<Asset> ASSETS = Arrays.asList(
      new Asset(AssetType.BOND, 1000),
      new Asset(AssetType.BOND, 2000),
      new Asset(AssetType.STOCK, 3000),
      new Asset(AssetType.STOCK, 4000)
    );

    public static int totalAssetValues (final List<Asset> assets) {
        return assets.stream()
                .mapToInt(Asset::getValue)
                .sum();
    }

    public static int totalBondValues(final List<Asset> assets) {
        return assets.stream()
                .mapToInt(asset ->
                    asset.getType() == AssetType.BOND ? asset.getValue() : 0)
                .sum();
    }

    public static int totalStockValues(final List<Asset> assets) {
        return assets.stream()
                .mapToInt(asset ->
                    asset.getType() == AssetType.STOCK ? asset.getValue() : 0)
                .sum();
    }

    public static void main(String[] args) {
        System.out.println("Total of all assets: " + totalAssetValues(ASSETS));
        System.out.println("Total of bonds: " + totalBondValues(ASSETS));
        System.out.println("Total of stocks: " + totalStockValues(ASSETS));
    }

}
