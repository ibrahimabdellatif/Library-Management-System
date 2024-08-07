package cc.maids.librarymanagementsystem.exception;

public class UserNotFoundException extends RuntimeException {
    private final String message;
    private final int id;

    public UserNotFoundException(int id){
        this.id=id;
        this.message="User not found";
    }

    @Override
    public String toString() {
        return "UserNotFoundException{" +
                "message='" + message + '\'' +
                ", id=" + id +
                '}';
    }
}
