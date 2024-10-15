package ru.fedotov.dto.users.request_dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import ru.fedotov.jpa.users.status.Status;

public class UpdateUserDto {
    @Positive
    private long userId;
    @NotEmpty
    private String userName;
    @NotEmpty
    private String password;
    private Status status;

    public UpdateUserDto() {
    }

    public @Positive long getUserId() {
        return this.userId;
    }

    public @NotEmpty String getUserName() {
        return this.userName;
    }

    public @NotEmpty String getPassword() {
        return this.password;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setUserId(@Positive long userId) {
        this.userId = userId;
    }

    public void setUserName(@NotEmpty String userName) {
        this.userName = userName;
    }

    public void setPassword(@NotEmpty String password) {
        this.password = password;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UpdateUserDto)) return false;
        final UpdateUserDto other = (UpdateUserDto) o;
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
        return other instanceof UpdateUserDto;
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
        return "UpdateUserDto(userId=" + this.getUserId() + ", userName=" + this.getUserName() + ", password=" + this.getPassword() + ", status=" + this.getStatus() + ")";
    }
}
