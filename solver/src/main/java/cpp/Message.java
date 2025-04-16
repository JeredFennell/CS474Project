package cpp;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Message {

    public static final int PROFILER_PROTOCOL_VERSION = 3;

    public enum NodeStatus {
        SOLVED(0),        // Node representing a solution
        FAILED(1),        // Node representing failure
        BRANCH(2),        // Node representing a branch
        SKIPPED(3);       // Skipped by backjumping

        private final int value;

        NodeStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static NodeStatus fromValue(int value) {
            for (NodeStatus status : values()) {
                if (status.value == value) return status;
            }
            throw new IllegalArgumentException("Unknown NodeStatus value: " + value);
        }
    }

    public enum MsgType {
        NODE(0),
        DONE(1),
        START(2),
        RESTART(3);

        private final int value;

        MsgType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static MsgType fromValue(int value) {
            for (MsgType type : values()) {
                if (type.value == value) return type;
            }
            throw new IllegalArgumentException("Unknown MsgType value: " + value);
        }
    }

    private MsgType type;
    private int node;
    private int parent;
    private int restartCount;
    private int alt;
    private int kids;
    private NodeStatus status;

    private boolean haveLabel = false;
    private String label;

    private boolean haveNogood = false;
    private String nogood;

    private boolean haveInfo = false;
    private String info;

    private boolean haveVersion = false;
    private int version;

    public boolean isNode() {
        return type == MsgType.NODE;
    }

    public boolean isDone() {
        return type == MsgType.DONE;
    }

    public boolean isStart() {
        return type == MsgType.START;
    }

    public boolean isRestart() {
        return type == MsgType.RESTART;
    }

    public int getNodeUID() {
        return node;
    }

    public void setNodeUID(int node) {
        this.node = node;
    }

    public int getParentUID() {
        return parent;
    }

    public void setParentUID(int parent) {
        this.parent = parent;
    }

    public int getRestartCount() {
        return restartCount;
    }

    public void setRestartCount(int restartCount) {
        this.restartCount = restartCount;
    }

    public int getAlt() {
        return alt;
    }

    public void setAlt(int alt) {
        this.alt = alt;
    }

    public int getKids() {
        return kids;
    }

    public void setKids(int kids) {
        this.kids = kids;
    }

    public NodeStatus getStatus() {
        return status;
    }

    public void setStatus(NodeStatus status) {
        this.status = status;
    }

    public void setLabel(String label) {
        haveLabel = true;
        this.label = label;
    }

    public void setInfo(String info) {
        haveInfo = true;
        this.info = info;
    }

    public void setNogood(String nogood) {
        haveNogood = true;
        this.nogood = nogood;
    }

    public void setVersion(int version) {
        haveVersion = true;
        this.version = version;
    }

    public boolean hasVersion() {
        return haveVersion;
    }

    public int getVersion() {
        return version;
    }

    public boolean hasLabel() {
        return haveLabel;
    }

    public String getLabel() {
        return label;
    }

    public boolean hasNogood() {
        return haveNogood;
    }

    public String getNogood() {
        return nogood;
    }

    public boolean hasInfo() {
        return haveInfo;
    }

    public String getInfo() {
        return info;
    }

    public void setType(MsgType type) {
        this.type = type;
    }

    public MsgType getType() {
        return type;
    }

    public void reset() {
        haveLabel = false;
        haveNogood = false;
        haveInfo = false;
        haveVersion = false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Message{");
        sb.append("type=").append(type);
        if (isNode()) {
            sb.append(", node=").append(node);
            sb.append(", parent=").append(parent);
            sb.append(", restartCount=").append(restartCount);
            sb.append(", alt=").append(alt);
            sb.append(", kids=").append(kids);
            sb.append(", status=").append(status);
        }
        if (haveLabel) {
            sb.append(", label='").append(label).append('\'');
        }
        if (haveNogood) {
            sb.append(", nogood='").append(nogood).append('\'');
        }
        if (haveInfo) {
            sb.append(", info='").append(info).append('\'');
        }
        if (haveVersion) {
            sb.append(", version=").append(version);
        }
        sb.append('}');
        return sb.toString();
    }

    public static class MessageMarshalling {

        private enum Field {
            LABEL(0),
            NOGOOD(1),
            INFO(2),
            VERSION(3);

            private final int value;

            Field(int value) {
                this.value = value;
            }

            public int getValue() {
                return value;
            }

            public static Field fromValue(int value) {
                for (Field field : values()) {
                    if (field.value == value) return field;
                }
                throw new IllegalArgumentException("Unknown Field value: " + value);
            }
        }

        private final Message msg = new Message();

        private static void serializeType(List<Byte> data, MsgType f) {
            data.add((byte) f.getValue());
        }

        private static void serializeField(List<Byte> data, Field f) {
            data.add((byte) f.getValue());
        }

        private static void serialize(List<Byte> data, int i) {
            data.add((byte) ((i >> 24) & 0xFF));
            data.add((byte) ((i >> 16) & 0xFF));
            data.add((byte) ((i >> 8) & 0xFF));
            data.add((byte) (i & 0xFF));
        }

        private static void serialize(List<Byte> data, NodeStatus s) {
            data.add((byte) s.getValue());
        }

        private static void serialize(List<Byte> data, String s) {
            serialize(data, s.length());
            for (char c : s.toCharArray()) {
                data.add((byte) c);
            }
        }

        private static MsgType deserializeMsgType(ListIterator<Byte> it) {
            return MsgType.fromValue(it.next());
        }

        private static Field deserializeField(ListIterator<Byte> it) {
            return Field.fromValue(it.next());
        }

        private static int deserializeInt(ListIterator<Byte> it) {
            int b1 = Byte.toUnsignedInt(it.next());
            int b2 = Byte.toUnsignedInt(it.next());
            int b3 = Byte.toUnsignedInt(it.next());
            int b4 = Byte.toUnsignedInt(it.next());

            return (b1 << 24) | (b2 << 16) | (b3 << 8) | b4;
        }

        private static NodeStatus deserializeStatus(ListIterator<Byte> it) {
            return NodeStatus.fromValue(it.next());
        }

        private static String deserializeString(ListIterator<Byte> it) {
            int size = deserializeInt(it);
            StringBuilder result = new StringBuilder(size);
            for (int i = 0; i < size; i++) {
                result.append((char) (byte) it.next());
            }
            return result.toString();
        }

        public Message makeNode(int node, int parent, int restartCount, int alt, int kids, NodeStatus status) {
            msg.reset();
            msg.setType(MsgType.NODE);

            msg.setNodeUID(node);
            msg.setParentUID(parent);
            msg.setRestartCount(restartCount);

            msg.setAlt(alt);
            msg.setKids(kids);
            msg.setStatus(status);

            return msg;
        }

        public void makeStart(String info) {
            msg.reset();
            msg.setType(MsgType.START);
            msg.setVersion(PROFILER_PROTOCOL_VERSION);
            msg.setInfo(info);
        }

        public void makeRestart(String info) {
            msg.reset();
            msg.setType(MsgType.RESTART);
            msg.setInfo(info);
        }

        public void makeDone() {
            msg.reset();
            msg.setType(MsgType.DONE);
        }

        public Message getMessage() {
            return msg;
        }

        public List<Byte> serialize() {
            List<Byte> data = new ArrayList<>();
//            int dataSize = 1 + (msg.isNode() ? 4 * 8 + 1 : 0) +
//                    (msg.hasLabel() ? 1 + 4 + msg.getLabel().length() : 0) +
//                    (msg.hasNogood() ? 1 + 4 + msg.getNogood().length() : 0) +
//                    (msg.hasInfo() ? 1 + 4 + msg.getInfo().length() : 0);
//            data.ensureCapacity(dataSize);

            serializeType(data, msg.getType());
            if (msg.isNode()) {
                int n_uid = msg.getNodeUID();
                serialize(data, n_uid);
                serialize(data, msg.getRestartCount());
                serialize(data, 0); // thread id
                int p_uid = msg.getParentUID();
                serialize(data, p_uid);
                serialize(data, msg.getRestartCount());
                serialize(data, 0); // thread id
                serialize(data, msg.getAlt());
                serialize(data, msg.getKids());
                serialize(data, msg.getStatus());
            }

            if (msg.hasVersion()) {
                serializeField(data, Field.VERSION);
                serialize(data, msg.getVersion());
            }
            if (msg.hasLabel()) {
                serializeField(data, Field.LABEL);
                serialize(data, msg.getLabel());
            }
            if (msg.hasNogood()) {
                serializeField(data, Field.NOGOOD);
                serialize(data, msg.getNogood());
            }
            if (msg.hasInfo()) {
                serializeField(data, Field.INFO);
                serialize(data, msg.getInfo());
            }
            return data;
        }

        public void deserialize(byte[] data, int size) {
            ListIterator<Byte> it = toByteList(data).listIterator();
            msg.setType(deserializeMsgType(it));
            if (msg.isNode()) {
                int nid = deserializeInt(it);
                msg.setNodeUID(nid);
                deserializeInt(it); // restartCount
                deserializeInt(it); // thread id

                nid = deserializeInt(it);
                msg.setParentUID(nid);
                deserializeInt(it); // restartCount
                deserializeInt(it); // thread id

                msg.setAlt(deserializeInt(it));
                msg.setKids(deserializeInt(it));
                msg.setStatus(deserializeStatus(it));
            }

            msg.reset();

            while (it.hasNext()) {
                Field f = deserializeField(it);
                switch (f) {
                    case VERSION:
                        msg.setVersion(deserializeInt(it));
                        break;
                    case LABEL:
                        msg.setLabel(deserializeString(it));
                        break;
                    case NOGOOD:
                        msg.setNogood(deserializeString(it));
                        break;
                    case INFO:
                        msg.setInfo(deserializeString(it));
                        break;
                    default:
                        break;
                }
            }
        }

        private List<Byte> toByteList(byte[] bytes) {
            List<Byte> byteList = new ArrayList<>(bytes.length);
            for (byte b : bytes) {
                byteList.add(b);
            }
            return byteList;
        }
    }
}