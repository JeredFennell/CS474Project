package cpp;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 * This class is a Java representation of
 * the <a href="https://github.com/MiniZinc/cpp-integration">C++ Connector</a> class
 * to communicate with cp-profiler, now part of MiniZincIDE.
 * Please refer to the README.md file of the original project for more information.
 * The Java code is a direct translation of the C++ code, so adapting it to Java
 * must be quite straightforward.
 *
 */
public class Connector {

    public static class Option<T> {
        private T value;
        private boolean present = false;

        public boolean isValid() {
            return present;
        }

        public void set(T value) {
            this.value = value;
            present = true;
        }

        public void unset() {
            present = false;
        }

        public T getValue() {
            if (!present) throw new IllegalStateException("Value not present");
            return value;
        }
    }

    public static class Node {
        private final Connector connector;
        private final int node;
        private final int parent;
        private final int restartCount;
        private final int alt;
        private final int kids;
        private final Message.NodeStatus status;
        private final Option<String> label = new Option<>();
        private final Option<String> nogood = new Option<>();
        private final Option<String> info = new Option<>();

        public Node(int node, int parent, int restartCount, int alt, int kids, Message.NodeStatus status, Connector connector) {
            this.connector = connector;
            this.node = node;
            this.parent = parent;
            this.restartCount = restartCount;
            this.alt = alt;
            this.kids = kids;
            this.status = status;
        }

        public Option<String> getLabel() {
            return label;
        }

        public Node setLabel(String label) {
            this.label.set(label);
            return this;
        }

        public Option<String> getNogood() {
            return nogood;
        }

        public Node setNogood(String nogood) {
            this.nogood.set(nogood);
            return this;
        }

        public Option<String> getInfo() {
            return info;
        }

        public Node setInfo(String info) {
            this.info.set(info);
            return this;
        }

        public int getAlt() {
            return alt;
        }

        public int getKids() {
            return kids;
        }

        public Message.NodeStatus getStatus() {
            return status;
        }

        public void send() throws IOException {
            Connector.sendNode(connector, this);
        }
    }

    private final Message.MessageMarshalling marshalling;
    private final int port;
    private Socket socket;
    private boolean connected;

    public Connector(int port) {
        this.port = port;
        this.connected = false;
        this.marshalling = new Message.MessageMarshalling();
    }

    public boolean isConnected() {
        return connected;
    }

    public void connect() throws IOException {
        socket = new Socket("localhost", port);
        connected = true;
    }

    public void sendRawMsg(byte[] buf) throws IOException {
        // Each message starts with a four-byte integer encoding the size of the remainder of the message in bytes
        int bufSize = buf.length;
        byte[] sizeBuffer = new byte[4 + bufSize];
        for (int i = 0; i < 4; i++) {
            sizeBuffer[i] = (byte) (bufSize >>> (i * 8));
        }
        System.arraycopy(buf, 0, sizeBuffer, 4, bufSize);
        socket.getOutputStream().write(sizeBuffer);
        socket.getOutputStream().flush();
    }

    public void start(String filePath, int executionId, boolean hasRestarts) throws IOException {
        String baseName = new File(filePath).getName();

        String info = String.format(
                "{\"has_restarts\": %b, \"name\": \"%s\", \"execution_id\": %d}",
                hasRestarts, baseName, executionId
        );
        marshalling.makeStart(info);
        sendOverSocket();
    }

    public void restart(int restartId) throws IOException {
        String info = String.format("{\"restart_id\": %d}", restartId);
        marshalling.makeRestart(info);
        sendOverSocket();
    }

    public void done() throws IOException {
        marshalling.makeDone();
        sendOverSocket();
    }

    public void disconnect() throws IOException {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }

    private void sendOverSocket() throws IOException {
        if (!connected) return;

        List<Byte> buf = marshalling.serialize();
        byte[] byteArray = new byte[buf.size()];
        for (int i = 0; i < buf.size(); i++) {
            byteArray[i] = buf.get(i);
        }

        sendRawMsg(byteArray);
    }

    public void sendNode(Node node) throws IOException {
        if (!connected) return;

        Message msg = marshalling.makeNode(node.node, node.parent, node.restartCount,
                node.getAlt(), node.getKids(), node.getStatus());

        if (node.getLabel().isValid()) msg.setLabel(node.getLabel().getValue());
        if (node.getNogood().isValid()) msg.setNogood(node.getNogood().getValue());
        if (node.getInfo().isValid()) msg.setInfo(node.getInfo().getValue());
        sendOverSocket();
    }

    public Node createNode(int node, int parent, int restartCount, int alt, int kids, Message.NodeStatus status) {
        return new Node(node, parent, restartCount, alt, kids, status, this);
    }

    public static void sendNode(Connector connector, Node node) throws IOException {
        connector.sendNode(node);
    }
}