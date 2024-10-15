package ru.fedotov.dto.users.request_models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CreateUserRequest {
    @NotEmpty
    @Size(min = 3, max = 50, message = "User name length must be >= 3 and <= 50!")
    private String userName;
    @NotEmpty
    @Size(min = 3, max = 100, message = "Password length must be >= 3 and <= 100!")
    private String password;

    @Positive(message = "Owner Id must be > 0!")
    private Long ownerId;

    public CreateUserRequest() {
    }

    public @NotEmpty @Size(min = 3, max = 50, message = "User name length must be >= 3 and <= 50!") String getUserName() {
        return this.userName;
    }

    public @NotEmpty @Size(min = 3, max = 100, message = "Password length must be >= 3 and <= 100!") String getPassword() {
        return this.password;
    }

    public @Positive(message = "Owner Id must be > 0!") Long getOwnerId() {
        return this.ownerId;
    }

    public void setUserName(@NotEmpty @Size(min = 3, max = 50, message = "User name length must be >= 3 and <= 50!") String userName) {
        this.userName = userName;
    }

    public void setPassword(@NotEmpty @Size(min = 3, max = 100, message = "Password length must be >= 3 and <= 100!") String password) {
        this.password = password;
    }

    public void setOwnerId(@Positive(message = "Owner Id must be > 0!") Long ownerId) {
        this.ownerId = ownerId;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CreateUserRequest)) return false;
        final CreateUserRequest other = (CreateUserRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$userName = this.getUserName();
        final Object other$userName = other.getUserName();
        if (this$userName == null ? other$userName != null : !this$userName.equals(other$userName)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final Object this$ownerId = this.getOwnerId();
        final Object other$ownerId = other.getOwnerId();
        if (this$ownerId == null ? other$ownerId != null : !this$ownerId.equals(other$ownerId)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CreateUserRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $userName = this.getUserName();
        result = result * PRIME + ($userName == null ? 43 : $userName.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $ownerId = this.getOwnerId();
        result = result * PRIME + ($ownerId == null ? 43 : $ownerId.hashCode());
        return result;
    }

    public String toString() {
        return "CreateUserRequest(userName=" + this.getUserName() + ", password=" + this.getPassword() + ", ownerId=" + this.getOwnerId() + ")";
    }
}
