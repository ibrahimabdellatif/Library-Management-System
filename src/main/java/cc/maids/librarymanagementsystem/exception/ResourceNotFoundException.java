package cc.maids.librarymanagementsystem.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private final int id;
    private final String resourceName;

    public ResourceNotFoundException(int id, String resourceName) {
        this.id = id;
        this.resourceName = resourceName;
    }

    @Override
    public String toString() {
        return "ResourceNotFoundException{" +
                "id=" + id +
                ", resourceName='" + resourceName + " Not Found" +'\'' +
                '}';
    }
}
