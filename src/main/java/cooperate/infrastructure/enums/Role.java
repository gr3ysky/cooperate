package cooperate.infrastructure.enums;

public enum Role {
    SuperUser(1),
    Admin(2),
    Member(3);
    private int value;

    Role(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
