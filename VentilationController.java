/*
Nguyen Chinh Thai
202419094
 */
final class VentilationController {
    private boolean stopped;

    void stop() {
        stopped = true;
    }

    boolean isStopped() {
        return stopped;
    }
}
