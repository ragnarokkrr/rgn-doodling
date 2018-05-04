package ragna.c04;

public class Asset {
    public enum AssetType { BOND, STOCK};

    private final AssetType type;
    private final int value;

    public Asset(AssetType type, int value) {
        this.type = type;
        this.value = value;
    }

    public AssetType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "type=" + type +
                ", value=" + value +
                '}';
    }
}
