public class TryWithResources implements AutoCloseable {
    @Override
    public void close() {
        System.out.println("close()");
    }

    static TryWithResources saveSettings() {
        System.out.println("saveSettings()");
        return new TryWithResources();
    }

    public static void main(String[] args) {
        // only this way, needed variable
        try (TryWithResources t = saveSettings()) {
            System.out.println("in try-catch");
        }
    }
}
