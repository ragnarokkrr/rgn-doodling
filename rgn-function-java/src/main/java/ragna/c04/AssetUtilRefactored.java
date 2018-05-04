package ragna.c04;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import ragna.c04.Asset.AssetType;


// Refactoring to Separate a Key Concern
public class AssetUtilRefactored {


    static final List<Asset> ASSETS = Arrays.asList(
            new Asset(AssetType.BOND, 1000),
            new Asset(AssetType.BOND, 2000),
            new Asset(AssetType.STOCK, 3000),
            new Asset(AssetType.STOCK, 4000)
    );

    public static int totalAssetValues(final List<Asset> assets,
            final Predicate<Asset> assetSelector) {
        return assets.stream().filter(assetSelector).mapToInt(Asset::getValue).sum();
    }


    public static void main(String[] args) {
        System.out.println("Total of assets: " + totalAssetValues(ASSETS, asset -> true));
        System.out.println("Total of bonds: " + totalAssetValues(ASSETS, asset -> asset.getType() == AssetType.BOND));
        System.out.println("Total of stock: " + totalAssetValues(ASSETS, asset -> asset.getType() == AssetType.STOCK));
    }
}
