package kr.fingate.gs.common;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;

public class Item {
    String path = "";
    JsonFieldType type;
    String desc = "";
    List<Item> children = new ArrayList<>();
    State state = State.NONE;

    public enum State {
        IGNORED,
        OPTIONAL,
        NONE
    }

    public Item(String path, JsonFieldType type, String desc, State state, List<Item> children) {
        this.path = path;
        this.type = type;
        this.desc = desc;
        this.state = state;
        this.children = children;
    }

    public Item(String path, JsonFieldType type, String desc, State state) {
        this.path = path;
        this.type = type;
        this.desc = desc;
        this.state = state;
    }

    public Item(String path, List<Item> children) {
        this.path = path;
        this.children = children;
    }

    public static Item field(String path, JsonFieldType type, String desc, State state, Item... children) {
        return new Item(path, type, desc, state, Arrays.asList(children));
    }

    public static Item field(String path, JsonFieldType type, String desc, State state) {
        return new Item(path, type, desc, state);
    }

    public static Item field(String path, JsonFieldType type, String desc, Item... children) {
        return new Item(path, type, desc, State.NONE, Arrays.asList(children));
    }

    public static Item field(String path, JsonFieldType type, String desc) {
        return new Item(path, type, desc, State.NONE);
    }

    public static Item field(String path, List<Item> children) {
        return new Item(path, children);
    }

    public static Item field(String path, Item... children) {
        return new Item(path, Arrays.asList(children));
    }

    public static Item field(Item... children) {
        return new Item("", Arrays.asList(children));
    }

    public FieldDescriptor toField() {
        FieldDescriptor f = PayloadDocumentation.fieldWithPath(this.path)
                .type(this.type)
                .description(this.desc);
        switch (this.state) {
            case IGNORED:
                return f.ignored();
            case OPTIONAL:
                return f.optional();
            default:
                return f;
        }
    }

    public List<Item> toFlatList(String superPath) {
        List<Item> list = new ArrayList<>();
        if (superPath != null && !"".equals(superPath)) {
            this.path = superPath + "." + this.path;
        }
        if (this.type != null || this.children == null || this.children.size() < 1) {
            list.add(this);
        }

        for (Item child : children) {
            String addStr = "";
            if(this.type != null && this.type.equals(JsonFieldType.ARRAY)) {
                addStr = "[]";
            }
            list.addAll(child.toFlatList(this.path + addStr)); // 재귀
        }
        return list;
    }

    public List<Item> toFlatList() {
        return toFlatList("");
    }

    public List<FieldDescriptor> build() {
        List<FieldDescriptor> list = new ArrayList<>();
        for (Item i : toFlatList()) {
            list.add(i.toField());
        }
        return list;
    }
    public FieldDescriptor[] toFields() {
        List<FieldDescriptor> list = new ArrayList<>();
        for (Item i : toFlatList()) {
            list.add(i.toField());
        }
        return list.toArray(new FieldDescriptor[0]);
    }
}