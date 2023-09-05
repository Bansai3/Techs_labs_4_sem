package ru.fedotov.dto.users.request_models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import ru.fedotov.jpa.users.status.Status;

public class UpdateUserRequest {
    @Positive(message = "User id must be > 0!")
    private long userId;
    @NotEmpty(message = "New user name must be defined!")
    @Size(min = 3, max = 50, message = "User name length must be >= 3 and <= 50!")
    private String userName;
    @NotEmpty(message = "New password must be defined!")
    @Size(min = 3, max = 100, message = "Password length must be >= 3 and <= 100!")
    private String password;
    private Status status;

    public UpdateUserRequest() {
    }

    public @Positive(message = "User id must be > 0!") long getUserId() {
        return this.userId;
    }

    public @NotEmpty(message = "New user name must be defined!") @Size(min = 3, max = 50, message = "User name length must be >= 3 and <= 50!") String getUserName() {
        return this.userName;
    }

    public @NotEmpty(message = "New password must be defined!") @Size(min = 3, max = 100, message = "Password length must be >= 3 and <= 100!") String getPassword() {
        return this.password;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setUserId(@Positive(message = "User id must be > 0!") long userId) {
        this.userId = userId;
    }

    public void setUserName(@NotEmpty(message = "New user name must be defined!") @Size(min = 3, max = 50, message = "User name length must be >= 3 and <= 50!") String userName) {
        this.userName = userName;
    }

    public void setPassword(@NotEmpty(message = "New password must be defined!") @Size(min = 3, max = 100, message = "Password length must be >= 3 and <= 100!") String password) {
        this.password = password;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UpdateUserRequest)) return false;
        final UpdateUserRequest other = (UpdateUserRequest) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getUserId() != other.getUserId()) return false;
        final Object this$userName = this.getUserName();
        final Object other$userName = other.getUserName();
        if (this$userName == null ? other$userName != null : !this$userName.equals(other$userName)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UpdateUserRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $userId = this.getUserId();
        result = result * PRIME + (int) ($userId >>> 32 ^ $userId);
        final Object $userName = this.getUserName();
        result = result * PRIME + ($userName == null ? 43 : $userName.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        return result;
    }

    public String toString() {
        return "UpdateUserRequest(userId=" + this.getUserId() + ", userName=" + this.getUserName() + ", password=" + this.getPassword() + ", status=" + this.getStatus() + ")";
    }
}
